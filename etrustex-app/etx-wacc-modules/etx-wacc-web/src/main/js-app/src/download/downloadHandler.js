import {EventEmitter} from "../utils/eventEmitter"
//import {FileStream} from "../utils/fileStream"
import {DataStream} from "../utils/dataStream"
import {SymmetricDecipher} from "../crypto/symmetricDecipher"
import * as forge from 'node-forge'

/*
 * emits 'progress' event after each chunk is processed
 */
export class DownloadHandler extends EventEmitter {
  constructor({ isEncrypted = false, fileSize, contentChecksum, contentChecksumMethod, transmissionChecksum, transmissionChecksumMethod, randomBits, withoutRandomizedInit = false }) {
    super()
    this.blockSize = 65536
    this.isEncrypted = isEncrypted
    this.isSigned = contentChecksum != undefined // TODO: check whether the message should be signed in a reliable way!
    this.fileSize = fileSize
    this.contentChecksum = contentChecksum
    this.contentChecksumMethod = contentChecksumMethod
    this.transmissionChecksum = transmissionChecksum
    this.transmissionChecksumMethod = transmissionChecksumMethod
    this.randomBits = randomBits
    this.outFileStream = new DataStream(fileSize)
    if(!withoutRandomizedInit) {
      this.outFileStream.initChecksummer(randomBits)
    }
    // this.outFileStream = new FileStream(fileSize).createStream() //for when we'll use streams
    this.decipher = isEncrypted ? new SymmetricDecipher(this.outFileStream, randomBits) : null

    if (this.transmissionChecksumMethod != 'SHA-512') {
      console.log('WARNING! Unrecognized checksum method!')
    }
  }


  processChunk(bytes) {
    if (this.isEncrypted) {
      this.decipher.update(bytes)
    } else {
      // this.outFileStream.addData(forge.util.createBuffer(bytes).getBytes()) //for when we'll use streams
      this.outFileStream.append(forge.util.createBuffer(bytes).getBytes())
    }

    this.emit('progress', bytes.length)
  }

  recursiveProcessChunk(data, i, iterations, resolveProcessChunks) {
    new Promise((resolve) => {
      this.processChunk(data.slice(i * this.blockSize, Math.min((i + 1) * this.blockSize, data.length)))
      resolve()
    }).then(() => {
      if(i+1 < iterations) {
        let self = this
        setTimeout(() => self.recursiveProcessChunk(data, i+1, iterations, resolveProcessChunks), 1)
      } else {
        resolveProcessChunks()
      }
    })
  }

  processChunks(data) {
    let iterations = Math.ceil(data.length/this.blockSize)
    if(iterations > 1) console.log(`large slice (${data.length} bytes), splitting in ${iterations} iterations`)
    return new Promise((resolve) => {
      this.recursiveProcessChunk(data, 0, iterations, resolve)
    })
  }

  done() {
    return new Promise((resolve, reject) => {

      let computedContentChecksum,
        computedTransmissionChecksum

      if (this.isEncrypted) {
        this.decipher.done()
        //this.outFileStream.finish() //for the streaming
        computedContentChecksum = this.outFileStream.getMD()
        computedTransmissionChecksum = this.decipher.getMD()
      } else {
        //this.outFileStream.finish() // for the streaming
        computedContentChecksum = this.outFileStream.getMD()
        computedTransmissionChecksum = computedContentChecksum
      }
      if (computedTransmissionChecksum == this.transmissionChecksum && // the transmission checksum is ok
        (!this.isSigned || computedContentChecksum == this.contentChecksum) // if the message is signed, also the content checksum must be ok!
      ) {
        resolve({checksum: computedContentChecksum})
      } else {
        reject({checksum: computedContentChecksum}) // TODO: check which checksum did not match
      }
    })
  }
}