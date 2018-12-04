import * as forge from 'node-forge';

export function P12(options) {

  let that=this
  let promise  = new Promise((resolve, reject) =>
  {
    that.input = $('<input/>')
      .attr('type', "file")
      .attr('id', "certificateInput")
      .hide()
      .change(function () {
        var certificateFile = $(this).prop('files')[0]
        var fr = new FileReader()
        fr.onload = function () {
          var b64 = forge.util.binary.base64.encode(new Uint8Array(fr.result))
          var pkcs12Der = forge.util.decode64(b64)

          let localKeyId = null
          let res = {}

          try {
            let pkcs12Asn1 = forge.asn1.fromDer(pkcs12Der)
            let pkcs12 = forge.pkcs12.pkcs12FromAsn1(pkcs12Asn1, false, options.password)

            let bags = pkcs12.getBags({bagType: forge.pki.oids.certBag})
            res.certificate = bags[forge.pki.oids.certBag][0]
            res.keys = {publicKey: res.certificate.cert.publicKey}

            if(options.checkValidityRange) {
              let now = new Date().getTime()
              let validity = res.certificate.cert.validity
              if(validity.notBefore.getTime() > now || validity.notAfter.getTime() < now) {
                reject('certificate-expired-or-in-the-future')
              }
            }

            for (var sci = 0; sci < pkcs12.safeContents.length; ++sci) {
              var safeContents = pkcs12.safeContents[sci]
              //console.log('safeContents ' + (sci + 1))

              for (var sbi = 0; sbi < safeContents.safeBags.length; ++sbi) {
                var safeBag = safeContents.safeBags[sbi]
                //console.log('safeBag.type: ' + safeBag.type)

                if (safeBag.attributes.localKeyId) {
                  if (localKeyId != null) {
                    console.warn('Multiple localKeyIds found!')
                  }
                  localKeyId = forge.util.bytesToHex(
                    safeBag.attributes.localKeyId[0])

                } else {
                  // no local key ID, skip bag
                  continue
                }

                // this bag has a private key
                if (safeBag.type === forge.pki.oids.pkcs8ShroudedKeyBag) {
                  console.log('found private key')
                  res.keys.privateKey = safeBag.key
                  ;

                } else if (safeBag.type === forge.pki.oids.certBag) {
                  // this bag has a certificate
                  //this.localKeyId.certChain.push(safeBag.cert)
                }
              }
            }
            resolve(res)
          } catch (err) {
            reject("wrong-password")
          }
        };

        fr.readAsArrayBuffer(certificateFile)
      })
  })
  this.input.trigger("click")

  return promise
};