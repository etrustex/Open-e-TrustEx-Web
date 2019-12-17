import * as forge from 'node-forge';
import * as streamBrowserify from "stream-browserify";

/**
 * This class is not used yet as the writable streams are currently not available in browsers
 * This class is meant to replace the dataStream class in the future
 */
export class FileStream {
  constructor(fileSize, cipherKey = null) {
    this.resolveNewDataPromise
    this.updateNewDataPromise()
    this.stream = null
    this.fileSize = fileSize
    this.checksummer = forge.md.sha512.create()
    this.md = null
    if((cipherKey != null)) {
      // the checksum for encrypted files should not reveal any secret
      // small files could be guessed by an attacher by brute force and
      // verified against the checksum if we do not initialize it with some
      // random bits!
      this.checksummer.update(cipherKey)
    }
  }

  createStream() {
    let thisStreamingOut = this
    this.stream = new ReadableStream({
      start(controller) {
        // The following function handles each data chunk
        function push() {
          // "done" is a Boolean and value a "Uint8Array"
          thisStreamingOut.getNewDataPromise().then((data) => {
            // Is there no more data to read?
            if (data != null) {
              // Get the data and send it to the browser via the controller
              controller.enqueue(data)
              push()
            } else {
              controller.close()
            }
          })
        }
        push()
      }
    })
    return this
  }

  initChecksummer(randomBits) {
    // the checksum for encrypted files should not reveal any secret
    // small files could be guessed by an attacher by brute force and
    // verified against the checksum if we do not initialize it with some
    // random bits!
    this.checksummer.update(randomBits)
    return this
  }
  getNewDataPromise() {
    return this.newDataPromise
  }
  finish() {
    this.resolveNewDataPromise(null)
  }
  updateNewDataPromise() {
    this.newDataPromise = new Promise((resolve) => { this.resolveNewDataPromise = resolve })
  }
  addData(data) {
    this.checksummer.update(data)
    let resolveData = this.resolveNewDataPromise
    this.updateNewDataPromise()
    resolveData(data)
  }

  getMD() {
    if(this.md == null ) {
      this.md = this.checksummer.digest().toHex().toUpperCase()
      // this.data.finish()
    }
    this.checksummer= null
    return this.md
  };

  getChecksumMethod() {
    return 'SHA-512'
  }

  getNodeStream() {
    let reader = this.stream.getReader()
    let rs = streamBrowserify.Readable()

    rs._read = () => {
      reader.read().then( ({ value, done }) =>
        rs.push(done ? null : new Buffer(value))
      )
    }

    return rs
  }

  // getUint8Array() {
  //   if(!this.byteArray) {
  //     var bytes = new Uint8Array(this.data.length)
  //     for (var i = 0; i < this.data.length; i++)
  //       bytes[i] = this.data.charCodeAt(i)
  //
  //     this.byteArray = bytes
  //   }
  //
  //   return this.byteArray
  // }
}