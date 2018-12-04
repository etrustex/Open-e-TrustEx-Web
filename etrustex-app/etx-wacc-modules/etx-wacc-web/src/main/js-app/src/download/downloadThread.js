import {DownloadHandler} from "./downloadHandler";
import {EventEmitter} from "../utils/eventEmitter";
import * as forge from 'node-forge';
import { hexToUtf8 } from '../utils/utf8Utils';

/*import 'promise-polyfill/src/polyfill';
import 'whatwg-fetch';*/

export class DownloadThread extends EventEmitter {
  constructor({fileId, filename, filePath, zip, privateKey, targetContentChecksums}) {
    super()
    this.fileId = fileId
    this.filename = filename
    this.filePath = filePath
    this.zip = zip
    this.isEncrypted = privateKey != undefined
    this.privateKey = privateKey
    this.targetContentChecksums = targetContentChecksums
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

      let request = new Request('downloadAttachment.do?attachmentId=' + self.fileId, {
        method: 'GET',
        credentials: 'include',
        cache: 'reload'
      })

      fetch(request/*, {signal}*/)
      // Retrieve its body as ReadableStream
        .then(response => {
          //Checksum verification
          self.contentChecksum = self.targetContentChecksums.get(response.headers.get('referenceId')) //response.headers.get('contentChecksumHex')
          self.contentChecksumMethod = response.headers.get('contentChecksumMethod')
          self.transmissionChecksum = response.headers.get('transmissionChecksumHex').toUpperCase()
          self.transmissionChecksumMethod = response.headers.get('transmissionChecksumMethod')
          self.encryptedRandomBitsBase64 = response.headers.get('encryptedRandomBitsBase64')
          self.fileSize = response.headers.get('fileSize')
          try {
            self.randomBits = self.isEncrypted ? self.privateKey.decrypt(forge.util.decode64(self.encryptedRandomBitsBase64)) : false
          } catch(error) {
            reject('wrong-decipher-key')
            self.emit('transfer-failure', 'Decryption failed.<br>The provided p12 file may not contain the correct key to decrypt the attachments of this message.')
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
            randomBits: self.randomBits})

          self.downloadHandler.on('progress', (data) => self.emit('progress-processing', data))

          // Start the reader
          self.pump(resolve, reject)
        })
    })
  }

  pump(resolve, reject) {
    this.reader.read()
      .then(result => {
        if (result.done) {
          this.downloadHandler.done().then( () => {
            this.zip.file(this.filePath + this.filename, this.downloadHandler.outFileStream.getUint8Array(), {binary: true})
            resolve()
          }).catch(() => {
            this.zip.file(this.filePath + 'UNTRUSTED_' + this.filename, this.downloadHandler.outFileStream.getUint8Array(), {binary: true})
            resolve()
          })
        } else {
          this.emit('progress-transfer', result.value.length)
          this.downloadHandler.processChunk(result.value)
            .then( () => this.pump(resolve, reject) )
        }
      })
      .catch( error => {
        console.error(error)
        reject(error)
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