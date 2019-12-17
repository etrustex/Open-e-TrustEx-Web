import * as XAdES from "xadesjs";
/* we are keeping the signature and the signed document separated as it was done in the
 * in the webStart app, we should probably keep the signature inside the signed bundle instead...
 */
export let verifySignature = (signedXml, signatureXml)  => {
  //let document = XAdES.Parse(signedXml)
  //let signature = XAdES.Parse(signatureXml)



  let parser = new DOMParser()
  let xmlDoc = parser.parseFromString(signedXml,"text/xml")
  let xmlSignature = parser.parseFromString(signatureXml, "text/xml")

  let extractedSignedBundle = xmlDoc.getElementsByTagName("signedBundle")[0]
  let extractedSignature = xmlSignature.getElementsByTagName("ds:Signature")[0]
  // Edge workaround
  if(!extractedSignature) {
    extractedSignature = xmlSignature.getElementsByTagName("Signature")[0]
  }

  extractedSignedBundle.appendChild(extractedSignature)

  let serializer = new XMLSerializer()
  let signedBundleSTR = serializer.serializeToString(extractedSignedBundle)

  return verifySignedDocument(signedBundleSTR)
}

export let verifySignedDocument = (xmlSignedDocument) => {

  let verificationPromise = new Promise((resolve) => {
    /*let crypto = window.crypto || window.msCrypto
    XAdES.Application.setEngine("W3 WebCrypto module", crypto) // the || part is for IE11 compatibility*/
    let signedDocument = XAdES.Parse(xmlSignedDocument)
    let xmlSignature = signedDocument.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");

    let signedXml = new XAdES.SignedXml(signedDocument);
    signedXml.LoadXml(xmlSignature[0]);
    signedXml.Verify()
      .then(function (res) {
        resolve(res)//((res ? "Valid" : "Invalid") + " signature");
      })
      .catch(function (e) {
        //alert(e.message);
        resolve(false)
      });

  })
  return verificationPromise

}