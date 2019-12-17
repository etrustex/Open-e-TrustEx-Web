import * as forge from 'node-forge';

export class SymmetricCipher {
  constructor(out) {
    this.out = out
    this.key = forge.random.getBytesSync(32)
    this.cipher = forge.cipher.createCipher('AES-CBC', this.key)
    var iv = forge.util.createBuffer('').getBytes()
    this.cipher.start({iv: iv})
    this.checksummer = forge.md.sha512.create()
    this.checksummer.update(this.key) //the content checksum should be initalized with the secret to avoid revealing info!
    this.md = null
  }

  getChecksumMethod () {
    return 'SHA-512'
  }

  getMD () {
    console.debug('Getting transmission checksum!')
    if (this.md == null) {
      this.md = this.checksummer.digest().toHex().toUpperCase()
    }
    return this.md
  }

  update (fileSegment, isLast) {

    //console.log('updating the cipher with ' + bytes);
    let bytes = forge.util.createBuffer(fileSegment).getBytes()
    this.checksummer.update(bytes)

    let buffer = forge.util.createBuffer(bytes)
    this.cipher.update(buffer)

    if (isLast) {
      this.cipher.finish()
    }
    let encryptedBytes = this.cipher.output.getBytes()
    this.out.append(encryptedBytes)
  }
}