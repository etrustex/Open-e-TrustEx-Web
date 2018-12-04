/* we are keeping the signature and the signed document separated as it was done in the
 * in the webStart app, we should probably keep the signature inside the signed bundle instead...
 */
var verifySignature = function (signedXml, signatureXml) {
  //let document = XAdES.Parse(signedXml);
  //let signature = XAdES.Parse(signatureXml);

  let parser = new DOMParser();
  let xmlDoc = parser.parseFromString(signedXml,"text/xml")
  let xmlSignature = parser.parseFromString(signatureXml, "text/xml")

  let extractedSignedBundle = xmlDoc.getElementsByTagName("signedBundle")[0]
  let extractedSigneture = xmlSignature.getElementsByTagName("ds:Signature")[0]

  extractedSignedBundle.appendChild(extractedSigneture)

  let serializer = new XMLSerializer()
  let signedBundleSTR = serializer.serializeToString(extractedSignedBundle)

  return verifySignedDocument(signedBundleSTR)
}
var verifySignedDocument = function(xmlSignedDocument) {

  let defer = $.Deferred();

  let signedDocument = XAdES.Parse(xmlSignedDocument);
  parser = new DOMParser();

  let xmlSignature = signedDocument.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");

  let signedXml = new XAdES.SignedXml(signedDocument);
  signedXml.LoadXml(xmlSignature[0]);
  signedXml.Verify()
    .then(function (res) {
      defer.resolve(res)//((res ? "Valid" : "Invalid") + " signature");
    })
    .catch(function (e) {
      //alert(e.message);
      defer.resolve(false)
    });

  return defer.promise();

}