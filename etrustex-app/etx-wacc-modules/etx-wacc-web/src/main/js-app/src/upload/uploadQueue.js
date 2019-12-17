import {Uploader} from "./uploader";
import {DataStream} from "../utils/dataStream";
import {ChunkedFileReader} from "./chunkedFileReader";
import {SymmetricCipher} from "../crypto/symmetricCipher";
import * as forge from 'node-forge';
import {EventEmitter} from "../utils/eventEmitter";
import {bundleHandler} from "./messageEdit";
import {FileStatus} from "../common/fileStatus";

export class UploadQueue extends EventEmitter {

  constructor({localPartyId, parallelThreads = 5, totalBytes}) {
    super()
    this.localPartyId = localPartyId
    this.parallelThreads = parallelThreads
    this.requiresEncryption = false
    this.runningThreads = 0
    this.threadsLaunched = 0
    this.threadsCompleted = 0
    this.totalBytes = totalBytes
    this.readBytes = 0
    this.uploadedBytes = 0
    this.abortSignal = false
  }

  updateProgress(readBytes) {
    this.readBytes += readBytes
    this.emit('processing', this.readBytes / this.totalBytes * 100)
  }

  initForEncryption(encryptionCertificateX509SubjectName, asymmetricCipher) {
    this.requiresEncryption = true
    this.encryptionCertificateX509SubjectName = encryptionCertificateX509SubjectName
    this.asymmetricCipher = asymmetricCipher
    return this
  }

  end() {
    this.runningThreads--
    this.threadsCompleted++
    console.log('thread ended... runningThreads:' + this.runningThreads)


    if (!this.abortSignal && bundleHandler.hasFilesToUpload()) {
      this.launch()
    } else if(this.threadsCompleted == this.threadsLaunched) {
      this.emit('done')
    }
  }

  start() {
    for (let i = 0; i < this.parallelThreads; i++) {
      //let fileInfo = bundleHandler.getNextToUpload()
      if (bundleHandler.hasFilesToUpload()) {
        this.launch()
      }
    }
  }

  launch() {
    this.threadsLaunched++
    this.runningThreads++
    let fileInfo = bundleHandler.getNextToUpload()

    if(fileInfo.fileSize > 1024*1024*500) { // checking file size limit (500 MB)
      fileInfo.uploadStatus = 'ko'
     // fileInfo.tooltip = 'The file exceeds the maximum size of 500 MB'
      fileInfo.status = FileStatus.UPLOAD_FAILED_EXCEED_MAXIMUM_SIZE
      this.readBytes += fileInfo.fileSize
      this.emit('processing', this.readBytes / this.totalBytes * 100)
      this.emit('uploadend', fileInfo)
      this.emit('uploading', (this.uploadedBytes += fileInfo.fileSize) / this.totalBytes * 100)
      this.end()
      return
    }

    if(forbiddenUploadFileExtensions) {
      if(fileInfo.fileName.toUpperCase().match(new RegExp(`.+\\.(${forbiddenUploadFileExtensions})$`))) {
        fileInfo.uploadStatus = 'ko'
        fileInfo.status = FileStatus.UPLOAD_FAILED_EXTENSION_NOT_ALLOWED
        this.readBytes += fileInfo.fileSize
        this.emit('processing', this.readBytes / this.totalBytes * 100)
        this.emit('uploadend', fileInfo)
        this.emit('uploading', (this.uploadedBytes += fileInfo.fileSize) / this.totalBytes * 100)
        this.end()
        return
      }
    }

    if(fileInfo.fileSize === 0) { // checking for empty files
      fileInfo.uploadStatus = 'ko'
      fileInfo.status = FileStatus.UPLOAD_FAILED_EMPTY_FILE
      this.emit('uploadend', fileInfo)
      this.end()
      return
    }

    if (fileInfo.fileName.length > 250) { // checking if file name exceeds 250 characters
      fileInfo.uploadStatus = 'ko'
      fileInfo.status = FileStatus.UPLOAD_FAILED_EXCEED_MAXIMUM_FILE_NAME_LENGTH
      this.readBytes += fileInfo.fileSize
      this.emit('processing', this.readBytes / this.totalBytes * 100)
      this.emit('uploadend', fileInfo)
      this.emit('uploading', (this.uploadedBytes += fileInfo.fileSize) / this.totalBytes * 100)
      this.end()
      return
    }

    if (this.runningThreads > this.parallelThreads) {
      console.warn('Too many threads running!')
    }
    console.log(this.runningThreads + ' threads running...')

    let outFileStream,
      successHandler,
      chunkHandler,
      localPartyId = this.localPartyId,
      encryptionCertificateX509SubjectName = this.encryptionCertificateX509SubjectName,
      asymmetricCipher = this.asymmetricCipher,
      queue = this

    if (this.requiresEncryption) {

      fileInfo.isEncrypted = true //TODO: move this initialization somewhere else? maybe remove altogether from the fileInfo since it is global...

      outFileStream = new DataStream(roundToCipherBlockMultipleAES(fileInfo.file.size))

      let cipher = new SymmetricCipher(outFileStream)
      successHandler = () => {
        fileInfo.encryptedKey = forge.util.encode64(asymmetricCipher.encrypt(cipher.key))
        fileInfo.contentChecksumMethod = cipher.getChecksumMethod()
        fileInfo.contentChecksum = cipher.getMD()

        fileInfo.transmissionChecksumMethod = outFileStream.getChecksumMethod()
        fileInfo.transmissionChecksum = outFileStream.getMD()

        let uploader = new Uploader({
          fileStream: outFileStream,
          localPartyId: localPartyId,
          fileInfo: fileInfo,
          encryptionCertificateX509SubjectName: encryptionCertificateX509SubjectName
        })

        uploader.upload()
          .then((data) => {
            this.end()
            this.emit('uploadend', fileInfo)
            this.emit('uploading', (this.uploadedBytes += fileInfo.fileSize) / this.totalBytes * 100)
          })

      }
      //new SuccessHandlerWithEncryption(cipher, outFileStream, this.asymmetricCipher, toLaunch, file.name, this.localPartyId, this.encryptionCertificateX509SubjectName, this)
      chunkHandler = (segment) => {
        console.log('handling chunk segment')
        cipher.update(segment.fileSegment, segment.isLast)
        if (segment.isLast) {
          console.log('chunk segment is last...')
        }

        queue.updateProgress(segment.fileSegment.byteLength)
      }
      //new ChunkHandlerCipher(cipher)
    } else { // no encryption required
      outFileStream = new DataStream(fileInfo.file.size)
      fileInfo.fileSize = outFileStream.fileSize

      successHandler = () => {

        fileInfo.contentChecksumMethod = outFileStream.getChecksumMethod()
        fileInfo.contentChecksum = outFileStream.getMD()
        fileInfo.transmissionChecksumMethod = outFileStream.getChecksumMethod()
        fileInfo.transmissionChecksum = outFileStream.getMD()

        let uploader = new Uploader({
          fileStream: outFileStream,
          localPartyId: localPartyId,
          fileInfo: fileInfo
        })

        uploader.upload()
          .then(() => {
            this.end()
            this.emit('uploadend', fileInfo)
            this.emit('uploading', (this.uploadedBytes += fileInfo.fileSize) / this.totalBytes * 100)
          })
          .catch(() => {
            this.end()
            this.emit('uploadend', fileInfo)
            this.emit('uploading', (this.uploadedBytes += fileInfo.fileSize) / this.totalBytes * 100)
          })
          // Only working in Chrome 63+
          // .finally(() => {
          //   this.emit('uploadend', fileInfo)
          //   this.emit('uploading', (this.uploadedBytes += fileInfo.fileSize) / this.totalBytes * 100)
          // })
      }

      chunkHandler = (segment) => {
        console.log('handling chunk segment')
        outFileStream.append(forge.util.createBuffer(segment.fileSegment).getBytes())
        if (segment.isLast) {
          console.log('chunk segment is last...')
        }

        queue.updateProgress(segment.fileSegment.byteLength)
      }
      //new ChunkHandlerClearText(outFileStream)
    }
    let chunkFileReader = new ChunkedFileReader({
      file: fileInfo.file,
      chunkHandler: chunkHandler,
      successHandler: successHandler
      // TODO: error handler is missing!!!
    })

    chunkFileReader.readBlock()
  }
}

export let roundToCipherBlockMultipleAES = function (size) {
  if(size === 0) return 0
  let cipherBlockSize = 16 // should be the size of AES blocks...
  return Math.ceil((size + 1) / cipherBlockSize) * cipherBlockSize
}
