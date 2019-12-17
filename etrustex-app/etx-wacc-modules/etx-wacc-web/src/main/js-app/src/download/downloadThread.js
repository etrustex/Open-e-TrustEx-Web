import {DownloadHandler} from "./downloadHandler";
import {EventEmitter} from "../utils/eventEmitter";
import * as forge from 'node-forge';
import { hexToUtf8 } from '../utils/utf8Utils';
import {FileStatus} from "../common/fileStatus";
import {I18Message} from "../common/i18";

export class DownloadThread extends EventEmitter {
  constructor({file, zip, privateKey, digestsAndEncryptedBits}) {
    super()
    this.file = file
    this.zip = zip
    this.isEncrypted = privateKey != undefined
    this.privateKey = privateKey
    this.digestsAndEncryptedBits = digestsAndEncryptedBits
  }

  /*
    * IMPORTANT!!!
    * In current version of Firefox, to stream from server with fetch()
    * the following prefs must be enabled in about:config
    * javascript.options.streams
    * dom.streams.enabled
    *
  */
  fetchInputStream() {
    let self = this;
    return new Promise(function(resolve, reject) {
      if (self.abortSignal) {
        reject('abortSignal')
        return
      }

      let downloadingFromSent = document.getElementById('downloadingFromSent') != null && document.getElementById('downloadingFromSent').value=="true"
      let requestUrl = 'downloadAttachment.do?attachmentId=' + self.file.id + (downloadingFromSent? '&downloadAsSender=true' : '')

      let request = new Request( requestUrl, {
        method: 'GET',
        credentials: 'include',
        cache: 'reload'
      })
      fetch(request/*, {signal}*/)
      // Retrieve its body as ReadableStream
        .then((response) => {

          try {
            //Checksum verification
            let checksumAndRandomKey = self.digestsAndEncryptedBits.get(response.headers.get('referenceId'))

            let withoutRandomizedInit = true

            if (checksumAndRandomKey) {
              self.contentChecksum = checksumAndRandomKey.digestValue
              self.contentChecksumMethod = checksumAndRandomKey.digestMethod
              self.encryptedRandomBitsBase64 = checksumAndRandomKey.encryptedKey
              if (self.encryptedRandomBitsBase64.length > 0) {
                withoutRandomizedInit = false
              }
            }
            if(withoutRandomizedInit) {
              // for unsigned and encrypted messages
              //  and for compatibility with signatures without random initialization
              self.encryptedRandomBitsBase64 = response.headers.get('encryptedRandomBitsBase64')
            }

            //self.contentChecksumMethod = response.headers.get('contentChecksumMethod')
            self.transmissionChecksum = response.headers.get('transmissionChecksumHex').toUpperCase()
            self.transmissionChecksumMethod = response.headers.get('transmissionChecksumMethod')

            //self.encryptedRandomBitsBase64 = response.headers.get('encryptedRandomBitsBase64')
            self.fileSize = response.headers.get('fileSize')
            try {
              self.randomBits = self.isEncrypted ? self.privateKey.decrypt(forge.util.decode64(self.encryptedRandomBitsBase64)) : false
            } catch (error) {
              reject('wrong-decipher-key')
              self.emit('transfer-failure', messages[I18Message.MESSAGE_DECRYPTION_FAILED_WRONG_KEY])
              return
            }

            //self.filename = self.getFileName(response.headers.get('Content-Disposition'))
            self.filename = hexToUtf8(response.headers.get('filename'))
            self.filePath = hexToUtf8(response.headers.get('filePath'))
            self.reader = response.body.getReader()

            self.downloadHandler = new DownloadHandler({
              isEncrypted: self.isEncrypted,
              fileSize: self.fileSize,
              contentChecksum: self.contentChecksum,
              contentChecksumMethod: self.contentChecksumMethod,
              transmissionChecksum: self.transmissionChecksum,
              transmissionChecksumMethod: self.transmissionChecksumMethod,
              randomBits: self.randomBits,
              withoutRandomizedInit: withoutRandomizedInit
            })

            self.downloadHandler.on('progress', (data) => self.emit('progress-processing', data))

            // Streaming to zip (works awfully without the streamsaver)
            // self.zip.file(self.filePath + self.filename, self.downloadHandler.outFileStream.getNodeStream()/*, {binary: true}*/)

            // Start the reader
            self.pump(resolve, reject)
          } catch (error) {
            console.error(error)
            self.file.status = FileStatus.FAILED
            // self.zip.remove(self.filePath + self.filename) //for when we'll use streams
            self.emit('status-update', self.file)

            resolve(error)
          }
        })
    })
  }

  pump(resolve, reject) {
    this.reader.read()
      .then(result => {
        if (result.done) {
          this.downloadHandler.done().then( () => {
            this.zip.file(this.filePath + this.filename, this.downloadHandler.outFileStream.getBlob()/*getUint8Array()*/, {binary: true})
            this.file.status = FileStatus.SUCCESS
            this.emit('status-update', this.file)
            resolve()
          }).catch(() => {
            this.zip.file(this.filePath + FileStatus.UNTRUSTED + '_' + this.filename, this.downloadHandler.outFileStream.getBlob()/*getUint8Array()*/, {binary: true})
            this.file.status = FileStatus.UNTRUSTED
            this.emit('status-update', this.file)
            resolve()
          })
        } else {
          this.emit('progress-transfer', result.value.length)
          this.downloadHandler.processChunks(result.value).then(() => this.pump(resolve, reject))
        }
      })
      .catch( error => {
        console.error(error)
        this.file.status = FileStatus.FAILED
        // this.zip.remove(self.filePath + self.filename) //for when we'll use streams
        this.emit('status-update', this.file)
        resolve(error)
      })
  }

  // getFileName(disposition) {
  //   if (disposition && disposition.indexOf('attachment') !== -1) {
  //     let filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
  //     let matches = filenameRegex.exec(disposition);
  //     if (matches != null && matches[1]) {
  //       return matches[1].replace(/['"]/g, '')
  //     }
  //   }
  // }

  get abortSignal() {
    return this.constructor.abortSignal;
  }
}

DownloadThread.abortSignal = false