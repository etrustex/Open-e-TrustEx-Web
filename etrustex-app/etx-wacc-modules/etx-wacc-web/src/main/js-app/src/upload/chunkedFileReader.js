import { EventEmitter } from "../utils/eventEmitter";

export class ChunkedFileReader extends EventEmitter{

  constructor({file, chunkSize = 64 * 1024, chunkHandler, successHandler, errorHandler}) {
    super()
    this.file = file
    this.fileSize = file.size
    this.offset = 0
    this.chunkSize = chunkSize
    this.chunkHandler = chunkHandler
    this.successHandler = successHandler
    this.errorHandler = errorHandler
  }

  handleEvent(evt) {
    console.log('File size:' + this.fileSize)
    console.log('load: ' + this.offset)
    console.log('byte length: ' + evt.target.result.byteLength)

    this.emit('progress', evt.target.result.byteLength)

    if (evt.target.error == null) {
      this.offset += evt.target.result.byteLength
      this.chunkHandler({fileSegment: evt.target.result, isLast: this.offset >= this.fileSize})

    } else {
      this.errorHandler(evt.target.error)
      return
    }
    if (this.offset >= this.fileSize) {
      this.successHandler(this.file)
      return
    }

    this.readBlock()
  }

  readBlock() {
    var r = new FileReader()
    var blob = this.file.slice(this.offset, this.chunkSize + this.offset)

    //console.log('Reading chunk of ' + chunkSize + ' bites, from ' + this.offset + ' to ' + (this.offset+chunkSize) + '. Blob size: ' + blob.size )

    r.addEventListener('loadend', this)

    r.readAsArrayBuffer(blob)
  }
}
