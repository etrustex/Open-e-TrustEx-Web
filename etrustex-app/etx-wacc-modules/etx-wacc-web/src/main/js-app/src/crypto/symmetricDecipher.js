import * as forge from 'node-forge';

export class SymmetricDecipher {

  constructor(out, key) {
    this.out = out
    this.key = key
    this.decipher = forge.cipher.createDecipher('AES-CBC', this.key)
    var iv = forge.util.createBuffer('').getBytes()
    this.decipher.start({iv: iv})
    this.checksummer = forge.md.sha512.create()
    this.md = null
    this.firstCall = true
  }

  getChecksumMethod() {
    return 'SHA-512'
  }

  getMD() {
    console.log('Getting transmission checksum!')
    if(this.md == null) {
      this.md = this.checksummer.digest().toHex().toUpperCase()
    }
    return this.md
  }

  update(fileSegment) {

    if(!this.firstCall) {
      var decryptedBytes = this.decipher.output.getBytes()
      //this.out.addData(decryptedBytes) //for when we'll use streams
      this.out.append(decryptedBytes)
    } else {
      this.firstCall=false
    }

    //console.log('updating the cipher with ' + bytes)
    var bytes = forge.util.createBuffer(fileSegment).getBytes()
    this.checksummer.update(bytes)

    var buffer = forge.util.createBuffer(bytes)
    this.decipher.update(buffer)
  }

  done() {
    this.decipher.finish()
    var decryptedBytes = this.decipher.output.getBytes()
    //this.out.addData(decryptedBytes) //for when we'll use streams
    this.out.append(decryptedBytes)
  }
}
