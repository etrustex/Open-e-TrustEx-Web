import {EventEmitter} from "../utils/eventEmitter";
import {FileStream} from "../crypto/fileStream";
import {SymmetricDecipher} from "../crypto/symmetricDecipher";
import * as forge from 'node-forge';

/*
 * emits 'progress' event after each chunk is processed
 */
export class DownloadHandler extends EventEmitter {
  constructor({ isEncrypted = false, fileSize, contentChecksum, contentChecksumMethod, transmissionChecksum, transmissionChecksumMethod, randomBits }) {
    super()
    this.isEncrypted = isEncrypted
    this.isSigned = contentChecksum != undefined // TODO: check whether the message should be signed in a reliable way!
    this.fileSize = fileSize
    this.contentChecksum = contentChecksum
    this.contentChecksumMethod = contentChecksumMethod
    this.transmissionChecksum = transmissionChecksum
    this.transmissionChecksumMethod = transmissionChecksumMethod
    this.randomBits = randomBits
    this.outFileStream = new FileStream(fileSize)
    this.decipher = isEncrypted ? new SymmetricDecipher(this.outFileStream, randomBits) : null

    if (this.transmissionChecksumMethod != 'SHA-512') {
      console.log('WARNING! Unrecognized checksum method!')
    }
  }

  processChunk(bytes) {
    return new Promise((resolve, reject) => this._processChunk(bytes, resolve, reject))
  }

  _processChunk(bytes, resolve, reject) {
    if (this.isEncrypted) {
      this.decipher.update(bytes)
    } else {
      this.outFileStream.append(forge.util.createBuffer(bytes).getBytes())
    }

    this.emit('progress', bytes.length)

    resolve()
  }

  done() {
    return new Promise((resolve, reject) => {

      if (this.isEncrypted) {
        this.decipher.done()
        const computedContentChecksum = this.outFileStream.getMD()
        const computedTransmissionChecksum = this.decipher.getMD()

        if (computedTransmissionChecksum == this.transmissionChecksum && // the transmission checksum is ok
          (!this.isSigned || computedContentChecksum == this.contentChecksum) // if the message is signed, also the content checksum must be ok!
        ) {
          resolve({checksum: computedContentChecksum})
        } else {
          reject({checksum: computedContentChecksum}) // TODO: check which checksum did not match
        }
      } else {
        const computedContentChecksum = this.outFileStream.getMD()
        if (computedContentChecksum == this.transmissionChecksum) {
          resolve({checksum: computedContentChecksum})
        } else {
          reject({checksum: computedContentChecksum})
        }
      }
    })
  }
}