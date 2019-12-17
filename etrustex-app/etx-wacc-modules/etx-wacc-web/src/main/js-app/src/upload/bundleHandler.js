import * as XAdES from 'xadesjs';
import * as forge from 'node-forge';
import {
  appendNewItemToNode, appendNewItemToNodeWithValue, b64ToBinary, removePFXComments,
  SignXml
} from "../utils/signatureHelper";
import {I18Message, I18Tooltip} from "../common/i18";
import {FileStatus} from "../common/fileStatus";
import {saveMessage} from './messageEdit';

/* object that stores information about the bundle */
export class BundleHandler {
  constructor() {
    this.filesInfo = new Map()
    this.totalSize = 0
    this.toUpload = new Map()
    this.existingAttachmentsPaths = new Map()
    this.signature = null
    this.signedBundle = null
    this.dataToSign = null
    this.bundleId = 'xmldsig-' + Math.random().toString(36)
    this.counter = 0 // this counter is used to generate a unique id for each file added
    this.subject = ''
    this.comments = ''
    this.validationErrors = []
  }

  isReadyForSend(options) {
    this.validationErrors = []
    return new Promise((resolve, reject) => {
      let passed = true

      if (this.subject.trim().length == 0) {
        this.validationErrors.push(I18Message.MESSAGE_SUBJECT_ERROR_MESSAGE)
        passed = false
      }
      if (this.filesInfo.size == 0) {
        this.validationErrors.push(I18Message.MESSAGE_ATTACHMENT_LIST_EMPTY)
        passed = false
      }
      if(this.filesInfo.size > 500) {
        this.validationErrors.push(I18Message.MESSAGE_ATTACHMENT_LIST_TOO_LONG)
        passed = false
      }

      if(options.metadataValidationFn) {
        let errors = options.metadataValidationFn()
        if(errors.length > 0) {
          passed = false
          this.validationErrors.push(...errors)
        }
      }

      let shouldBeSignedChk = document.getElementById('sing-message-chk')
      if (shouldBeSignedChk.checked) {

        let selectedIdentity = GlobalAccess.crypto.getSelectedIdentity()

        let identitySelectionValidationErrors = GlobalAccess.crypto.validateIdentitySelection(selectedIdentity, true)
        if(identitySelectionValidationErrors.length > 0) {
          passed = false
          this.validationErrors = this.validationErrors.concat(identitySelectionValidationErrors)
        }
      }
      if (passed) {
        resolve()
      } else {
        reject(this.validationErrors)
      }
    })
  }

  isReadyForSave() {
    this.validationErrors = []
    let readyForSavePromise = new Promise((resolve, reject) => {
      let passed = true

      if (this.subject.trim().length == 0) {
        this.validationErrors.push(I18Message.MESSAGE_SUBJECT_ERROR_MESSAGE)
        passed = false
      }
      let shouldBeSignedChk = document.getElementById('sing-message-chk')
      if (
        shouldBeSignedChk.checked &&
        !shouldBeSignedChk.getAttribute("disabled")) {
        this.validationErrors.push(I18Message.MESSAGE_SHOULD_BE_SIGNED_ERROR_MESSAGE)
        passed = false
      }
      if (passed) {
        resolve()
      } else {
        reject(this.validationErrors)
      }
    })
    return readyForSavePromise
  }


  getNextId() {
    return 'fileId_' + this.counter++
  }

  //for compatibility with the sendMessage
  getFileIds() {
    var fileIds = [];
    for (let key of this.filesInfo.keys()) {
      fileIds.push(this.filesInfo.get(key).fileIdFromNode);
    }
    return fileIds;
  }

  getFileInfos () {
    return this.filesInfo.values()
  }

  loadAlreadyUploadedFile(currentAttachment) {

    let status, uploadStatus
    if(retentionExpired) { // the retentionExpired variable is set in the messageEditContainer.jsp
      status = FileStatus.EXPIRED
      uploadStatus = 'ko'
    } else {
      status = FileStatus.UPLOAD_SUCCESS
      uploadStatus = 'ok'
    }
    let fileInfo = {
      fileName: currentAttachment.fileName,
      filePath: currentAttachment.path,
      fileSize: currentAttachment.fileSize,
      uploadStatus: uploadStatus,
      generatedFileId: currentAttachment.fileReferenceId,
      contentChecksumMethod: currentAttachment.contentChecksumMethod,
      contentChecksum: currentAttachment.contentChecksum,
      transmissionChecksumMethod: currentAttachment.transmissionChecksumMethod,
      transmissionChecksum: currentAttachment.transmissionChecksum,
      fileIdFromNode: currentAttachment.id,
      comment: currentAttachment.comment,
      confidential: currentAttachment.confidential,
      //isPayload : false
      //mimeType : "application/pdf"
      status: status

    }
    return this.addFile(fileInfo)
  }

  addFile(fileInfo) {

    if(this.filesInfo.size == 0) { //disabling the saveAsDraft
      $("[name='saveMessageAsDraftButton']").off('click').prop('title', messages[I18Tooltip.TOOLTIP_DRAFT_CANNOT_SAVE] ).removeClass('hidden-btn').addClass('hidden-btn-disabled')
    }

    let fileId = this.getNextId();
    this.filesInfo.set(fileId, fileInfo);
    if (!fileInfo.uploadStatus) {
      this.toUpload.set(fileId, fileInfo);
    }
    fileInfo.fileId = fileId

    //handling homonyms
    fileInfo.originalFilename=fileInfo.fileName
    let homonymsCounter = 0

    while (this.existingAttachmentsPaths.has(fileInfo.filePath+this.rename(fileInfo.fileName, homonymsCounter))) {
        homonymsCounter++
    }

    if(homonymsCounter >0) {
      let newName = this.rename(fileInfo.fileName, homonymsCounter)
      console.log('renaming file ' + fileInfo.filePath + fileInfo.fileName + ' to ' + newName)
      fileInfo.fileName = newName
    }
    this.existingAttachmentsPaths.set(fileInfo.filePath + fileInfo.fileName, true)

    this.totalSize+=fileInfo.fileSize
    return fileId
  }

  rename(fileName, counter) {
    if (counter == 0) return fileName

    let regExp = /(.+)(\.[^\.]+$)|(.+$)/
    var matches = regExp.exec(fileName)
    if(matches[2] != undefined) {
      return matches[1]+'_'+counter+matches[2] // there is at least a dot inside the file name (and not at the first character)
    } else {
      return matches[3]+'_'+counter // there are no dots or the only dot is the first character
    }

  }

  requeueFailedFilesForUpload() {
    this.filesInfo.forEach((value, key, map) => {
      if(value.uploadStatus != 'ok') {
        this.toUpload.set(key, value)
      }
    })
  }

  getFileInfo(fileId) {
    return this.filesInfo.get(fileId);
  }

  hasFilesToUpload() {
    return this.toUpload.size
  }

  getNextToUpload() {
    if (this.toUpload.size > 0) {
      for (let key of this.toUpload.keys()) {
        let res = this.toUpload.get(key)
        this.toUpload.delete(key)
        return res;
      }
    }
  }

  getTotalBytesToUpload() {
    //return [this.toUpload.values()].map(fileInfo => fileInfo.file.size).reduce((sum, fileSize) => sum + roundFunction(fileSize))
    let sum = 0
    this.toUpload.forEach((value, key, map) => {
      sum += value.fileSize
    })
    return sum
  }

  removeFile(fileId) {
    let fileInfo = this.filesInfo.get(fileId)
    this.filesInfo.delete(fileId)
    this.toUpload.delete(fileId)
    if(fileInfo != null) {
      this.existingAttachmentsPaths.delete(fileInfo.filePath + fileInfo.fileName)
      this.totalSize -= fileInfo.fileSize
    }

    if(this.filesInfo.size == 0) { // enabling the save as draft
      this.enableSaveAsDraft()
    }
  }

  removeAll() {
    this.filesInfo.clear()
    this.toUpload.clear()
    this.existingAttachmentsPaths.clear()
    this.totalSize = 0
    this.enableSaveAsDraft()
  }

  enableSaveAsDraft() {
    $("[name='saveMessageAsDraftButton']").click(saveMessage).prop('title', '').removeClass('hidden-btn-disabled').addClass('hidden-btn')
  }

  getSignableBundle() {
    let rootNode, currentNode
    let doc = document.implementation.createDocument(/*'urn:eu:europa:ec:etrustex:signature:v1.0'*/null, 'xml', null)


    rootNode = appendNewItemToNode(doc, null, 'signedBundle')//xmlSignableBundle.createElement('signedBundle')
    //rootNode.setAttribute('xmlns', 'urn:eu:europa:ec:etrustex:signature:v1.0')
    rootNode.setAttribute('Id', this.bundleId);

    for (let key of this.filesInfo.keys()) {
      currentNode = appendNewItemToNode(doc, rootNode, 'document')

      let fileInfo = this.getFileInfo(key)
      appendNewItemToNodeWithValue(doc, currentNode, 'id', fileInfo.generatedFileId)
      appendNewItemToNodeWithValue(doc, currentNode, 'fileName', fileInfo.fileName)
      appendNewItemToNodeWithValue(doc, currentNode, 'filePath', fileInfo.filePath)
      appendNewItemToNodeWithValue(doc, currentNode, 'digestMethod', fileInfo.contentChecksumMethod)
      appendNewItemToNodeWithValue(doc, currentNode, 'digestValue', fileInfo.contentChecksum)
      if(fileInfo.encryptedKey) {
        // encrypted transmission need to use the random bits to initialize the content checksum so that it does not reveal any secret!
        appendNewItemToNodeWithValue(doc, currentNode, 'encryptedKey', fileInfo.encryptedKey)
      }
    }
    return rootNode
  }

  /* TODO: improve the sign method */
  sign(identity) {

    let _thisBundle = this

    let signPromise = new Promise((resolve, reject) => {

      const privateKey = identity.privateKey
      //const publicKey = keysAndCert.keys.publicKey
      const certificate = identity.cert

      const signableBundleXML = _thisBundle.getSignableBundle()
      let serializer = new XMLSerializer()
      let signableBundleSTR = serializer.serializeToString(signableBundleXML)
      /*signableBundleXML.toString();*/

      // convert a Forge private key to an ASN.1 RSAPrivateKey
      const rsaPrivateKey = forge.pki.privateKeyToAsn1(privateKey)
      // wrap an RSAPrivateKey ASN.1 object in a PKCS#8 ASN.1 PrivateKeyInfo
      const privateKeyInfo = forge.pki.wrapRsaPrivateKey(rsaPrivateKey)
      // convert a PKCS#8 ASN.1 PrivateKeyInfo to PEM (i.e., private key in textual form:)
      const pem = forge.pki.privateKeyInfoToPem(privateKeyInfo)

      const pemCertificate = forge.pki.certificateToPem(certificate, 80)
      //console.log('pemCertificate: ' + pemCertificate)

      const keyForSigning = b64ToBinary(removePFXComments(pem))
      //const keyForSigning = pem2der(preparePem(pem)); // Buffer is not defined (in function pem2der)
      const certificateForSigning = removePFXComments(pemCertificate)

      /*
       * Note for the Chrome browser. the subtle module is only available when accessing secure locations:
       * https://www.chromium.org/blink/webcrypto
       * */
      XAdES.Application.crypto.subtle.importKey("pkcs8", keyForSigning,
        {
          name: "RSASSA-PKCS1-v1_5",
          modulusLength: 2048, //can be 1024, 2048, or 4096,
          publicExponent: new Uint8Array([1, 0, 1]),
          hash: {name: "SHA-256"},
        },
        false,
        ["sign"]
      ).then(function (privateKey) {
        //console.log('imported private key: ' + privateKey)
        // Call sign function
        SignXml(signableBundleSTR, privateKey, certificateForSigning, {
          name: "RSASSA-PKCS1-v1_5",
          hash: {name: "SHA-256"}
        }, _thisBundle.bundleId).then(
          function (signedXmlSTR) {
            //should add the signature and allow to send the message...

            console.log('The signed XML: ' + signedXmlSTR);

            let parser = new DOMParser()
            let signedXml = parser.parseFromString(signedXmlSTR, "text/xml")


            let signature = signedXml.getElementsByTagName("ds:Signature")[0]
            let signedBundle = signedXml.getElementsByTagName("signedBundle")[0]
            // edge workaround
            if(!signature) {
              signature = signedXml.getElementsByTagName("Signature")[0]
            }
            // let signature = signedBundle.getElementsByTagName("ds:Signature")[0]

            let serializer = new XMLSerializer()

            _thisBundle.signedBundle = serializer.serializeToString(signedBundle)
            signedBundle.removeChild(signature)
            _thisBundle.signature = serializer.serializeToString(signature)
            _thisBundle.dataToSign = serializer.serializeToString(signedBundle)

            resolve()

          }
        ).catch(function (error) {
          console.log(error)
          reject(error)
        })
      }).catch(function (error) {
        console.log(error)
      })
    })
    return signPromise
  }
}

