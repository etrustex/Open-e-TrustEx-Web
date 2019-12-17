import * as forge from 'node-forge';

export class AsymmetricCipher {
  constructor(key, isKeyPublic) {

    if (isKeyPublic) {
      let pem = '-----BEGIN PUBLIC KEY-----' + key + '-----END PUBLIC KEY-----';
      this.key = forge.pki.publicKeyFromPem(pem)
    } else {
      let pem = '-----BEGIN PRIVATE KEY-----' + key + '-----END PRIVATE KEY-----';
      this.key = forge.pki.privateKeyFromPem(pem)
    }
  }

  encrypt(data) {
    return this.key.encrypt(data, 'RSAES-PKCS1-V1_5')
  }

  decrypt(data) {
    return this.key.decrypt(data)
  }
}