P12 = function () {
  var defer = $.Deferred();

  this.input = $('<input/>')
    .attr('type', "file")
    .attr('id', "certificateInput")
    .hide()
    .change(function() {
      var certificateFile = $(this).prop('files')[0];
      var fr = new FileReader();
      fr.onload = function () {
        var password = prompt("Please enter password");
        var b64 = forge.util.binary.base64.encode(new Uint8Array(fr.result));
        var p12Der = forge.util.decode64(b64);
        var keysAndCert= getKeysAndCert(p12Der, password);

        defer.resolve(keysAndCert);
      };

      fr.readAsArrayBuffer( certificateFile );
    });

  this.input.trigger("click");

  return defer.promise();
};

getKeysAndCert = function (pkcs12Der, password) {
  this.cert = null;
  this.privateKey = null;
  this.publicKey = null;
  this.localKeyId = null;

  var res  = {};

  var pkcs12Asn1 = forge.asn1.fromDer(pkcs12Der);
  var pkcs12 = forge.pkcs12.pkcs12FromAsn1(pkcs12Asn1, false, password);

  var bags = pkcs12.getBags({bagType: forge.pki.oids.certBag});
  res.certificate = bags[forge.pki.oids.certBag][0];
  res.keys = {publicKey: res.certificate.cert.publicKey};

  for (var sci = 0; sci < pkcs12.safeContents.length; ++sci) {
    var safeContents = pkcs12.safeContents[sci];
    //console.log('safeContents ' + (sci + 1));

    for (var sbi = 0; sbi < safeContents.safeBags.length; ++sbi) {
      var safeBag = safeContents.safeBags[sbi];
      //console.log('safeBag.type: ' + safeBag.type);

      var localKeyId = null;
      if (safeBag.attributes.localKeyId) {
        if (this.localKeyId != null) {
          console.warn('Multiple localKeyIds found!');
        }
        this.localKeyId = forge.util.bytesToHex(
          safeBag.attributes.localKeyId[0]);

      } else {
        // no local key ID, skip bag
        continue;
      }

      // this bag has a private key
      if (safeBag.type === forge.pki.oids.pkcs8ShroudedKeyBag) {
        console.log('found private key');
        res.keys.privateKey = safeBag.key;
        ;

      } else if (safeBag.type === forge.pki.oids.certBag) {
        // this bag has a certificate
        //this.localKeyId.certChain.push(safeBag.cert);
      }
    }
  }
  return res;
};

/*getPrivateKey = function (pkcs12Der, password) {
  this.cert = null;
  this.privateKey = null;
  this.localKeyId = null;

  var pkcs12Asn1 = forge.asn1.fromDer(pkcs12Der);
  var pkcs12 = forge.pkcs12.pkcs12FromAsn1(pkcs12Asn1, false, password);


  for (var sci = 0; sci < pkcs12.safeContents.length; ++sci) {
    var safeContents = pkcs12.safeContents[sci];

    for (var sbi = 0; sbi < safeContents.safeBags.length; ++sbi) {
      var safeBag = safeContents.safeBags[sbi];

      var localKeyId = null;
      if (safeBag.attributes.localKeyId) {
        if(this.localKeyId != null) {
          console.warn('Multiple localKeyIds found!');
        }
        this.localKeyId = forge.util.bytesToHex(
          safeBag.attributes.localKeyId[0]);

      } else {
        // no local key ID, skip bag
        continue;
      }

      // this bag has a private key
      if (safeBag.type === forge.pki.oids.pkcs8ShroudedKeyBag) {
        console.log('found private key');
        return safeBag.key;

      } else if (safeBag.type === forge.pki.oids.certBag) {
        // this bag has a certificate
        //this.localKeyId.certChain.push(safeBag.cert);
      }
    }
  }
};
*/