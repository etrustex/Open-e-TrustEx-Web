import * as forge from "node-forge";
import {I18Message} from "../common/i18";

let selectedIdentity = false;
export function getSelectedIdentity () { return selectedIdentity }
export function setSelectedIdentity (identity) { selectedIdentity = identity }
export function validateIdentitySelection(selectedIdentity, checkValidityRange) {
  let validationErrors = []
  if (!selectedIdentity) {
    if($('#certificateInput').prop('files')[0] == undefined) {
      validationErrors.push(I18Message.MESSAGE_CERTIFICATE_SELECT)
    }
    if($('#certificatePassword').val().trim().length == 0) {
      validationErrors.push(I18Message.MESSAGE_CERTIFICATE_INSERT_PASSWORD)
    }
    if(validationErrors.length == 0) {
      if ($('#p12-identities').hasClass('collapse')) {
        validationErrors.push(I18Message.MESSAGE_CERTIFICATE_LOAD_IDENTITIES)
      } else {
        validationErrors.push(I18Message.MESSAGE_CERTIFICATE_SELECT_IDENTITY)
      }
    }
  } else if(checkValidityRange) {
    var now = new Date().getTime(),
      validity = selectedIdentity.cert.validity;
    if (validity.notBefore.getTime() > now || validity.notAfter.getTime() < now) {
      validationErrors.push( I18Message.MESSAGE_CERTIFICATE_SELECT_IDENTITY_VALID )
    }
  }
  return validationErrors
}

export const CERTIFICATE_VALIDITY = Object.freeze({
  EXPIRED: Symbol(),
  NOT_YET_VALID: Symbol(),
  VALID: Symbol(),
})
export function checkValidityTimeRange(cert) {
    let now = new Date().getTime()
    let validity = cert.validity
    if (validity.notBefore.getTime() > now) {
      return CERTIFICATE_VALIDITY.NOT_YET_VALID
    }
    if(validity.notAfter.getTime() < now) {
      return CERTIFICATE_VALIDITY.EXPIRED
  }
  return CERTIFICATE_VALIDITY.VALID
}

export function PKCSReader(options) {
  return new Promise((resolve, reject) => {
    let certificateFile = options.certificateFile,
      fr = new FileReader()

    fr.onload = () => {
      let b64 = forge.util.binary.base64.encode(new Uint8Array(fr.result)),
        pkcs12Der = forge.util.decode64(b64),
        localKeyId = null,
        pkcs12Asn1,
        pkcs12

      try {
        pkcs12Asn1 = forge.asn1.fromDer(pkcs12Der)
        pkcs12 = forge.pkcs12.pkcs12FromAsn1(pkcs12Asn1, false, options.password)

        let identities = new Map()

        pkcs12.safeContents.forEach((safeContents) => {
          safeContents.safeBags.forEach((safeBag) => {

            if (safeBag.attributes.localKeyId) {
              localKeyId = forge.util.bytesToHex(
                safeBag.attributes.localKeyId[0])
            } else {
              // no local key ID, skip bag
              return
            }
            // this bag has a private key
            let identity = identities.get(localKeyId)
            if(!identity) {
              identity = {}
              identities.set(localKeyId, identity)
            }

            if (safeBag.type === forge.pki.oids.pkcs8ShroudedKeyBag) {
              identity.privateKey = safeBag.key
            } else if (safeBag.type === forge.pki.oids.certBag) {
              identity.cert = safeBag.cert
              identity.notBefore = safeBag.cert.validity.notBefore
              identity.notAfter = safeBag.cert.validity.notAfter

              if(typeof(safeBag.attributes.friendlyName) !== 'undefined' && typeof(safeBag.attributes.friendlyName[0]) !== 'undefined' ) {
                identity.friendlyName = safeBag.attributes.friendlyName[0]
              } else {
                identity.friendlyName = 'not defined'
              }

              identity.publicKey = safeBag.cert.publicKey
              identity.subject = []
              safeBag.cert.subject.attributes.forEach((item) => {
                identity.subject.push(`${item.shortName}, ${item.value}`)
              })
            }
          })
        })
        resolve(identities)
      } catch (err) {
        console.error(err)
        reject( I18Message.MESSAGE_PKCS12_GENERIC )
      }
    }
    fr.readAsArrayBuffer(certificateFile)
  })
}
