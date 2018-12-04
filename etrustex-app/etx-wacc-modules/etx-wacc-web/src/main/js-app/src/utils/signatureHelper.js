import * as XAdES from "xadesjs";

export let SignXml = function (xmlString, privateKey, certificate, algorithm, singedBundleId) {
  let signedXml;
  return Promise.resolve().then(() => {
    var xmlDoc = XAdES.Parse(xmlString);
    signedXml = new XAdES.SignedXml();
    return signedXml.Sign(algorithm, privateKey, xmlDoc,
      {
        //keyValue: keys.publicKey,
        x509: [certificate],
        references: [{
          hash: "SHA-256", transforms: ["enveloped"],
          id: singedBundleId
        }],
        /*productionPlace: {
          country: "Country",
          state: "State",
          city: "City",
          code: "Code",
        },
        signerRoles: {
        claimed: ["Data producer"]
       },*/

        signingCertificate: certificate
      })
  }).then(() => signedXml.toString()); // cannot keep it as xml since it is not a regular XML document (e.g., does not implement getElementsByTagName)
}
export let removePFXComments = function (pem) {
  let lines = pem.split('\n');
  let encoded = '';
  for (let i = 0; i < lines.length; i++) {
    if (lines[i].trim().length > 0 &&
      lines[i].indexOf('-----BEGIN CERTIFICATE-----') < 0 &&
      lines[i].indexOf('-----END CERTIFICATE') < 0 &&
      lines[i].indexOf('-----BEGIN RSA PRIVATE KEY-----') < 0 &&
      lines[i].indexOf('-----BEGIN RSA PUBLIC KEY-----') < 0 &&
      lines[i].indexOf('-----BEGIN PUBLIC KEY-----') < 0 &&
      lines[i].indexOf('-----END PUBLIC KEY-----') < 0 &&
      lines[i].indexOf('-----BEGIN PRIVATE KEY-----') < 0 &&
      lines[i].indexOf('-----END PRIVATE KEY-----') < 0 &&
      lines[i].indexOf('-----END RSA PRIVATE KEY-----') < 0 &&
      lines[i].indexOf('-----END RSA PUBLIC KEY-----') < 0) {
      encoded += lines[i].trim();
    }
  }
  return encoded;
}
export let b64ToBinary = function (base64) {
  let raw = atob(base64);
  let rawLength = raw.length;
  let array = new Uint8Array(new ArrayBuffer(rawLength));

  for (let i = 0; i < rawLength; i++) {
    array[i] = raw.charCodeAt(i);
  }
  return array;
}
export let appendNewItemToNode = function (document, node, childTagName) {
  var res = document.createElement(childTagName);
  if (node) {
    node.appendChild(res);
  }
  return res;
}
export let appendNewItemToNodeWithValue = function (document, node, childTagName, value) {
  var res = appendNewItemToNode(document, node, childTagName);
  var newText = document.createTextNode(value);
  res.appendChild(newText);
  return res;
}