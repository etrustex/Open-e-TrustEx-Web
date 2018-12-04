import * as forge from 'node-forge';

export class FileStream {
  constructor(fileSize) {
    this.data = ''
    this.fileSize = fileSize
    this.checksummer = forge.md.sha512.create()
    this.md = null
  }

  append(bytes) {
    this.data += bytes
    this.checksummer.update(bytes)
  }

  getMD() {
    console.log('data length: ' + this.data.length + ' - file size: ' + this.fileSize)
    if(this.data.length != this.fileSize) {
      console.warn('Computing the message digest with incomplete data!')
    }
    if(this.md == null ) {
      this.md = this.checksummer.digest().toHex().toUpperCase()
    }
    this.checksummer= null
    return this.md
  };

  getChecksumMethod() {
    return 'SHA-512'
  }

  getBlob(type) {
    if(!this.blob) {
      if (!type) {
        type = "application/octet-stream"
      }
      var bytes = new Uint8Array(this.data.length)
      for (var i = 0; i < this.data.length; i++)
        bytes[i] = this.data.charCodeAt(i)

      this.blob = new Blob([bytes], {type: type})
    }
    return this.blob
  }

  getUint8Array() {
    if(!this.byteArray) {
      var bytes = new Uint8Array(this.data.length)
      for (var i = 0; i < this.data.length; i++)
        bytes[i] = this.data.charCodeAt(i)

      this.byteArray = bytes
    }

    return this.byteArray
  }
}