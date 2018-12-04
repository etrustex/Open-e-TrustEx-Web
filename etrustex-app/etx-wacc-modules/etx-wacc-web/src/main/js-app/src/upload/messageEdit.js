import { BundleHandler } from "./bundleHandler"
import {
  inputPasswordBody,
  plainDialogBody,
  PromptDialog, PromptDialogBtn,
  textAreaBody
} from "../utils/promptDialog"
import { P12 } from "../utils/p12"
import {ProgressMode, Progress, ProgressBar} from "../utils/progress"
import { AsymmetricCipher } from "../crypto/asymmetricCipher"
import {roundToCipherBlockMultipleAES, UploadQueue} from "./uploadQueue"
import {EdmaMetadata} from "./edmaMetadata";

export let bundleHandler = new BundleHandler(),
  eTrustExEdmaMdDocumentList = []


/* Prevent browser from loading a drag-and-dropped file */
window.addEventListener("dragover", (e) => {
  e = e || event
  e.preventDefault()
}, false)
window.addEventListener("drop", (e) => {
  e = e || event
  e.preventDefault()
}, false)

let currentFileList = ""
let lastIca = null
let currentIca = null
let currentAttachments = null
let addingFolder = false
let metadataHandler = null
let referrerUrl = ""

$(document).ready(() => {

  if(document.getElementById('edmaMetadataSection') != null) metadataHandler = new EdmaMetadata()

  getReferrerUrl()

  bundleHandler.subject = document.getElementById('subject').value //first initialization needed when opening a draft
  document.querySelector('#subject').onchange = (event) => {
    bundleHandler.subject = event.target.value
  }

  bundleHandler.comments = document.getElementById('message.content').value //first initialization needed when opening a draft
  document.querySelector('#message\\.content').onchange = (event) => {
    bundleHandler.comments = event.target.value
  }

  // TODO: handle persistence of the Sign the message checkbox during save as draft?
  //bundleHandler.shouldBeSigned = document.getElementById('sing-message-chk').value

  $('[data-toggle="tooltip"]').tooltip()

  if (retentionExpired) {
    disableMessageInputFields()
  } else {
    loadMessageIca(icaDetailsVO)
    const integrityCheck = JSON.parse(icaDetailsVO).integrityCode != 'MODERATE'
    $('#sing-message-chk').prop('checked', integrityCheck).attr("disabled", integrityCheck)
  }

  $("#recipient").on('change', () => {
    updateMessageRecipient(localPartyNodeName, $("#recipient").val())
  })

  let attachmentsEncoded = $('#encodedFileElements').val()
  let isEncryptionRequired = $('#isEncryptionRequired').val()
  isEncryptionRequired = isEncryptionRequired == 'true'

  if (attachmentsEncoded != undefined && attachmentsEncoded != null && attachmentsEncoded != '') {
    currentFileList = b64DecodeUnicode(attachmentsEncoded)
    currentAttachments = JSON.parse(currentFileList)
    currentAttachments.forEach((currentAttachment) => {
      let fileId = bundleHandler.loadAlreadyUploadedFile(currentAttachment)
      let fileInfo = bundleHandler.getFileInfo(fileId)
      buildFileRow(fileId, fileInfo)
      updateUploadStatus(fileInfo)
    })
  }

  $('#addFolder').on('click', () => {
    let input = $('#addFiles')
    addingFolder = true
    input.attr('directory', '')
    input.attr('webkitdirectory', '')
    input.click()
  })

  $('#addFiles').on('click', (evt) => {
    let input = $('#addFiles')
    if (!addingFolder) {
      input.removeAttr('directory', '')
      input.removeAttr('webkitdirectory', '')
    } else {
      addingFolder = false
    }
  })

  $('#addFiles').fileupload({
    autoUpload: false,
    add: (e, data) => {
      data.files.forEach((file) => {
        let path = ''
        if (file.webkitRelativePath.length >0) {
          path = file.webkitRelativePath.slice(0, 0 - file.name.length )
        } else if(file.relativePath != undefined && file.relativePath.length >0) {
          path = file.relativePath
        }
        let fileInfo = {
          file: file,
          fileName: file.name,
          filePath: path,
          fileSize: file.size,
          comment: '',
          confidential: false
        }

        let fileId = bundleHandler.addFile(fileInfo)

        buildFileRow(fileId, fileInfo)
      })
    }
  })

  $('#uploadFiles').on('click', () => {
    let senderPartyId = $('#selectedPartyId').val()

    if (bundleHandler.hasFilesToUpload()) {

      let totalBytes = bundleHandler.getTotalBytesToUpload(isEncryptionRequired ? roundToCipherBlockMultipleAES : (bytes) => bytes)

      let progress = new Progress(ProgressMode.UPLOAD, totalBytes),
        uploadQueue = new UploadQueue({localPartyId: senderPartyId, totalBytes: totalBytes})

      uploadQueue.on('done', () => {
        progress.done()
        bundleHandler.requeueFailedFilesForUpload()
      })
      uploadQueue.on('processing', (data) => progress.update(ProgressBar.PROCESSING, data))
      uploadQueue.on('uploading', (data) => progress.update(ProgressBar.PROCESSING, progress.update(ProgressBar.TRANSFER, data)))
      uploadQueue.on('uploadend', (fileInfo) => {
        updateUploadStatus(fileInfo)
      })

      document.querySelector('#cancelUploadBtn').addEventListener('click', () => {
        $('#cancelUploadBtn').prop('disabled', true)
        uploadQueue.abortSignal = true
      })

      if (isEncryptionRequired) {
        let encryptionCertificateX509SubjectName, asymmetricCipher
        $.get("getCertificate?senderPartyId=" + senderPartyId, (data) => {
          let allData = JSON.parse(data)
          let publicKey = allData.publicKey
          encryptionCertificateX509SubjectName = allData.subjectDn
          asymmetricCipher = new AsymmetricCipher(publicKey, true)
        }).done(() => {
          uploadQueue.initForEncryption(encryptionCertificateX509SubjectName, asymmetricCipher).start()
        })
      } else {
        uploadQueue.start()
      }
    } else {
      let
        dialogTitle = "No files selected",
        dialogBody = plainDialogBody("You need to upload at least one file"),
        dialogButtons = [ PromptDialogBtn.OK ]
      PromptDialog({title: dialogTitle, body: dialogBody, buttons: dialogButtons})
    }
  })


  $('#resetUploadFiles').on('click', () => {
    $('#fileList').empty()
    bundleHandler.removeAll()
  })


  $("#recipient").combobox()
  $("#msgInstrument").combobox()

  /* Send and save as draft buttons */
  $("[name='sendMessageButton']").click(validateAndSend)
  $("[name='saveMessageAsDraftButton']").click(saveMessage)
  $("[name='cancelMessageButton']").click(cancelEditMessage)
})

let buildFileRow = (fileId, fileInfo) => {
  let tr = $('<tr/>'), uploadTd = $('<td/>'), fileNameTd = $('<td/>'), fileSizeTd = $('<td/>'),
    confidentialTd = $('<td/>'), commentTd = $('<td/>'), removeTd = $('<td/>')
  tr.attr('id', fileId)
  tr.append(uploadTd)
  let uploadSpan = $('<span/>')
  uploadSpan.addClass("upload-status glyphicon glyphicon-upload")
  uploadTd.append(uploadSpan)

  tr.append(fileNameTd)
  fileNameTd.append(fileInfo.filePath + fileInfo.fileName)

  tr.append(fileSizeTd)
  fileSizeTd.append((fileInfo.fileSize / 1024).toFixed(2))

  tr.append(confidentialTd)
  let confidentialSpan = $('<span/>')
  confidentialTd.append(confidentialSpan)
  if(fileInfo.confidential) {
    confidentialSpan.addClass("attachment confidential")
  } else {
    confidentialSpan.addClass("attachment non-confidential")
  }
  confidentialSpan.attr('data-toggle', 'tooltip').attr('title', 'Set confidential')
    .on('click', () => { toggleConfidential(fileId, confidentialSpan) })

  tr.append(commentTd)
  let commentSpan = $('<span/>')
  commentTd.append(commentSpan)
  commentSpan.addClass("attachment-comment glyphicon glyphicon-comment")
    .attr('data-toggle', 'tooltip').attr('title', 'Comments')
    .on('click', (evt) => handleComment(fileId, commentSpan))
  if (fileInfo.comment) {
    commentSpan.css('color', 'darkorange')
  } else {
    commentSpan.css('color', 'dodgerblue')
  }

  // hiding the confidential and comment spans when there is no metadata handler
  if(metadataHandler == null) {
    confidentialSpan.hide()
    commentSpan.hide()
  }


  tr.append(removeTd)
  let removeSpan = $('<span/>')
  removeTd.append(removeSpan)
  removeSpan.addClass("attachment-remove glyphicon glyphicon-remove")
    .css('color', 'red')
    .attr('data-toggle', 'tooltip').attr('title', 'Remove file')
    .on('click', (evt) => removeAttachment(fileId))

  $('#fileList').append(tr)
  // data.files[i].uploadName = Math.floor(Math.random() * 1000) + '_' + data.files[i].name
}

let updateUploadStatus = (fileInfo) => {
  if (fileInfo.uploadStatus == 'ok') {
    $('#' + fileInfo.fileId + ' .upload-status').removeClass("glyphicon-upload").addClass("glyphicon-thumbs-up").css('color', 'green')
      .attr('data-toggle', 'tooltip').attr('title', 'Upload Successful')
  } else {
    $('#' + fileInfo.fileId + ' .upload-status').removeClass("glyphicon-upload").addClass("glyphicon-thumbs-down").css('color', 'red')
      .attr('data-toggle', 'tooltip').attr('title', 'Upload Failed')
  }
}

let toggleConfidential = (fileId, confidentialSpan) => {
  let fileInfo = bundleHandler.getFileInfo(fileId)
  fileInfo.confidential = !fileInfo.confidential

  if (fileInfo.confidential) {
    confidentialSpan.removeClass("non-confidential").addClass("confidential")
  } else {
    confidentialSpan.removeClass("confidential").addClass("non-confidential")
  }
}
let removeAttachment = (fileId) => {
  bundleHandler.removeFile(fileId)
  document.querySelector('#' + fileId).remove()
}
let handleComment = (fileId, commentSpan) => {
  let fileInfo = bundleHandler.getFileInfo(fileId),
    dialogBody = textAreaBody(fileInfo.comment, 'comment-text-area')

  PromptDialog({
    title: fileInfo.fileName + " comment",
    body: dialogBody,
    buttons: [PromptDialogBtn.OK, PromptDialogBtn.CANCEL],
    beforeOkFn: () => {
      fileInfo.comment = $('#comment-text-area').val()
      if (fileInfo.comment) {
        commentSpan.css('color', 'darkorange')
      } else {
        commentSpan.css('color', 'dodgerblue')
      }
    }
  })
}

let loadMessageIca = (ica) => {
  if (ica != "") {
    ica = JSON.parse(ica)
    lastIca = ica
    currentIca = ica
  }
}

let updateMessageRecipient = (localPartyNodeName, remotePartyNodeName) => {
  if (remotePartyNodeName != null) {
    $.ajax({
      url: url('reloadMessageIca.do'),
      type: 'GET',
      data: {localPartyNodeName: localPartyNodeName, remotePartyNodeName: remotePartyNodeName},
      dataType: 'json',
      cache: false,
      success: (ica) => {
        enableUploadButton()
        checkLoadedIca(ica)
      },
      error: (xhr, status, error) => { }
    })
  } else {
    disableUploadButton()
    checkLoadedIca(null)
  }
}

let validateAndSend = () => {

  if (bundleHandler.hasFilesToUpload()) {
    PromptDialog({
      title: 'Upload not finished!',
      body: plainDialogBody('Some files were added but not uploaded.<br> Please upload the remaining files before sending the message.'),
      buttons: [PromptDialogBtn.OK]
    })
    return
  }

  $("#compMsg").attr("action", 'saveSentMessage.do')
  cleanErrorMessages()

  let validationOptions = {}
  if (metadataHandler!= null) {
    validationOptions.metadataValidationFn = metadataHandler.validateForm
  }

  bundleHandler.isReadyForSend(validationOptions).then(() => {

    if ($('#sing-message-chk').prop('checked')) {
      new Promise((resolve) => openP12ForSigningAndSubmit(resolve, false))
        .then(proceed => {
          if (proceed) {
            submitForm()
          }
        })
    } else {
      submitForm()
    }
  }).catch((validationErrors) => {
    validationErrors.forEach((msgErrorCode) => {
      displayError(msgErrorCode)
    })
  })
}


let openP12ForSigningAndSubmit = (resolveOpened, alert) => {
  let
    dialogTitle = 'Signature required!',
    dialogBody = inputPasswordBody({
      message: 'Please insert password and provide the p12 file for signing',
      alert: alert,
      passwordId: 'certificatePassword'
    }),
    dialogButtons = [PromptDialogBtn.OK, PromptDialogBtn.CANCEL]

  let password

  PromptDialog({
    title: dialogTitle,
    body: dialogBody,
    buttons: dialogButtons,
    beforeOkFn: () => {
      password = $('#certificatePassword').val()
      new P12({password: password, checkValidityRange: true}).then((keysAndCert) => { // needs to be in the beforeOkFn because the P12 opens a dialog and has to be in an used initiated event
        bundleHandler.sign(keysAndCert).then(() => {
          console.log('resolved opened')
          resolveOpened(true)
        })
      }).catch((error) => { // wrong password!
        console.log('catch')
        let errorMessage
        switch (error) {
          case 'certificate-expired-or-in-the-future':
            errorMessage = 'The certificate provided is expired or not yet valid!'
            break
          case 'wrong-password':
            errorMessage = 'Wrong password!'
            break
          default:
            errorMessage = 'Unkown error while opening the P12!'
        }
        openP12ForSigningAndSubmit(resolveOpened, errorMessage)
      })
    }
  }).then((action) => {
    if (action == PromptDialogBtn.CANCEL) {
      resolveOpened(false)
    }
  })
}


let saveMessage = () => {

  if (bundleHandler.hasFilesToUpload()) {
    PromptDialog({
      title: 'Upload not finished!',
      body: plainDialogBody('Some files were added but not uploaded.<br> Please upload the remaining files before saving the message.'),
      buttons: [PromptDialogBtn.OK]
    })
    return
  }

  $("#compMsg").attr("action", 'saveDraftMessage.do')
  cleanErrorMessages()

  bundleHandler.isReadyForSave().then(
    () => {
      submitForm()
    }
  ).catch((validationErrors) => {
    validationErrors.forEach((msgErrorCode) =>{
      displayError(msgErrorCode);
    })
  })
}


let cleanErrorMessages = () => {
  let errorsTable = document.getElementById('errors-table')
  let rowCount = errorsTable.rows.length
  for (let i = rowCount - 1; i >= 0; i--) {
    errorsTable.deleteRow(i)
  }
}

let displayError = (err) => {
  let table = document.getElementById('errors-table')
  let body = table.getElementsByTagName('tbody')[0]
  let tr = document.createElement('tr')
  let td = document.createElement('td')
  td.className = "error-text"
  td.innerHTML = messages[err]
  tr.appendChild(td)
  body.appendChild(tr)
}


let cancelMessage = () => {
  let cancelMsg = messages["message.cancelMessage"]
  let r = confirm(cancelMsg)
  if (r == true) {
    window.location.href = partyUrl
  }
}
//
// let disableSaveSendButtons = () => {
//   $('[id^=save-btn-]').each(() => {
//     $(this).addClass("hidden-btn-disabled")
//     $(this).removeClass("hidden-btn")
//     $(this).removeAttr("onclick")
//   })
//
//   $('[id^=send-btn-]').each(() => {
//     $(this).addClass("hidden-btn-disabled")
//     $(this).removeClass("hidden-btn")
//     $(this).removeAttr("onclick")
//
//   })
// }


let disableMessageInputFields = () => {
  $("#compMsg :input").prop("disabled", true)
}

//only for FF or CH, on IE < 9 onmouseout was used
$(document).on('paste', 'textarea', (event) => {
  let _this = this
  setTimeout(() => {
    if ($(_this).val().length > 4000) {
      $(_this).val($(_this).val().substr(0, 4000))
    }
  }, 100)
})

let submitForm = () => {
  if (metadataHandler != null) {
    $('#messageMetadata').val(metadataHandler.getMessageMetadata())
  }

  $('#fileIdsListJSON').val(JSON.stringify(bundleHandler.getFileIds()))
  // signature data
  $('#xmlSignedBundle').val(bundleHandler.signedBundle)
  $('#xmlExtractedSignature').val(bundleHandler.signature)
  $('#xmlDataToSign').val(bundleHandler.dataToSign)

  $('#compMsg').submit()
}

let cancelEditMessage = () => {
  PromptDialog({
    title:'Cancel message',
    body: plainDialogBody('Do you want to cancel your changes ?'),
    buttons: [ PromptDialogBtn.SAVE_AS_DRAFT, PromptDialogBtn.OK ],
  }).then((action) => {
    if (action == PromptDialogBtn.SAVE_AS_DRAFT) {
      saveMessage()
    } else {
      window.location.href = referrerUrl
    }
  })
}

let getReferrerUrl = () => {

  referrerUrl = document.referrer
  if (referrerUrl == undefined || referrerUrl == "") {
     referrerUrl = document.getElementById('inboxUrl').href
  }
}
