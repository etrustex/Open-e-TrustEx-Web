import * as forge from 'node-forge';

export class DataStream {
  constructor(fileSize) {
    this.writtenLength = 0

    this.SEGMENT_LENGTH = 1024*1024*5 //5MB block size

    this.dataSegments = []

    // this.data = new StreamingOut().createStream()
    this.fileSize = fileSize
    this.checksummer = forge.md.sha512.create()
    this.md = null
  }

  initChecksummer(randomBits) {
    // the checksum for encrypted files should not reveal any secret
    // small files could be guessed by an attacher by brute force and
    // verified against the checksum if we do not initialize it with some
    // random bits!
    this.checksummer.update(randomBits)
    return this
  }

  append(bytes) {
    for (let i = 0; i < bytes.length; i++) {
      let blockIndex = Math.floor(this.writtenLength/this.SEGMENT_LENGTH)
      if(!this.dataSegments[blockIndex]) {
        this.dataSegments.push(new Uint8Array(Math.min(this.SEGMENT_LENGTH, this.fileSize - this.dataSegments.length*this.SEGMENT_LENGTH)))
      }
      this.dataSegments[blockIndex][this.writtenLength++ % this.SEGMENT_LENGTH] = bytes.charCodeAt(i)
    }
    this.checksummer.update(bytes)
    // console.log(`ChunkSize: ${bytes.length}`)
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

  /**
   * ETRUSTEX-4700 we need to trim the last segment to exact length of the written data
   * as otherwise we would end up with added NUL blocks in the files after decryption.
   * We do not know the exact size of the file until decryption ends due to the padding
   */
  trimLastSegment() {
    let lastSegmentIndex = this.dataSegments.length-1,
      lengthOfSegmentsBeforeLast = lastSegmentIndex * this.SEGMENT_LENGTH,
      lastSegmentWrittenLength = this.writtenLength - lengthOfSegmentsBeforeLast

    if(this.dataSegments[lastSegmentIndex].length > lastSegmentWrittenLength) {
      this.dataSegments[lastSegmentIndex] = this.dataSegments[lastSegmentIndex].slice(0, lastSegmentWrittenLength)
    }
  }

  getBlob(type) {
    this.trimLastSegment()
    if(!this.blob) {
      if (!type) {
        type = "application/octet-stream"
      }
      this.blob = new Blob(this.dataSegments, {type: type})
      this.dataSegments=null
    }
    return this.blob
  }
}