import {BundleHandler} from "./bundleHandler"
import {plainDialogBody, PromptDialog, PromptDialogBtn, textAreaBody} from "../utils/promptDialog"
import {Progress, ProgressBar, ProgressMode} from "../utils/progress"
import {AsymmetricCipher} from "../crypto/asymmetricCipher"
import {roundToCipherBlockMultipleAES, UploadQueue} from "./uploadQueue"
import {EdmaMetadata, validateEdmaMetadata} from "./edmaMetadata";
import {formatFileSize} from "../utils/formatting";
import {I18Label, I18Message} from "../common/i18";
import {FileStatus} from "../common/fileStatus";
import {arrangeIntoTree, treeFolderStructure, cleanTree} from "../utils/fsTreeUtils";

export let bundleHandler = new BundleHandler(),
  eTrustExEdmaMdDocumentList = [];

/* Prevent browser from loading a drag-and-dropped file */
window.addEventListener("dragover", (e) => {
  e = e || event;
  e.preventDefault()
}, false);
window.addEventListener("drop", (e) => {
  e = e || event;
  e.preventDefault()
}, false)

let currentFileList = ""
let currentAttachments = null
let addingFolder = false
let metadataHandler = null
let referrerUrl = ""
let icaDetails
let isEncryptionRequired = true

$(document).ready(() => {

  if (document.getElementById('edmaMetadataSection') != null) {
    metadataHandler = new EdmaMetadata()
  }

  getReferrerUrl()

  bundleHandler.subject = document.getElementById('subject').value //first initialization needed when opening a draft
  document.querySelector('#subject').onchange = (event) => {
    bundleHandler.subject = event.target.value
  }

  bundleHandler.comments = document.getElementById('message.content').value //first initialization needed when opening a draft
  document.querySelector('#message\\.content').onchange = (event) => {
    bundleHandler.comments = event.target.value
  }

  let attachmentsEncoded = $('#encodedFileElements').val()

  isEncryptionRequired = !($('#isEncryptionRequired').val() == 'false')

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

  updateTotals()
  updatePartials()

  $('#sing-message-chk').change(() => {
    $('#p12-input').collapse($('#sing-message-chk').prop('checked') ? 'show' : 'hide')
  })


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
        if (file.webkitRelativePath.length > 0) {
          path = file.webkitRelativePath.slice(0, 0 - file.name.length)
        } else if (file.relativePath != undefined && file.relativePath.length > 0) {
          path = file.relativePath
        }
        let fileInfo = {
          file: file,
          fileName: file.name,
          filePath: path,
          fileSize: isEncryptionRequired ? roundToCipherBlockMultipleAES(file.size) : file.size,
          comment: '',
          confidential: false
        }

        let fileId = bundleHandler.addFile(fileInfo)
        arrangeIntoTree(fileInfo)
      })
      updateTotals()
      updatePartials()
      buildTable()
    }

  })

  $('#hide-show-sucessful').click( () => {
    hideSuccessfulUploadedFiles()
  })

  $('#uploadFiles').on('click', () => {
    let senderPartyId = $('#selectedPartyId').val()


    if (bundleHandler.filesInfo.size > 500) {
      $('#upload-total-files-error').show()
      return
    } else {
      $('#upload-total-files-error').hide()
    }

    if (bundleHandler.hasFilesToUpload()) {

      let totalBytes = bundleHandler.getTotalBytesToUpload()

      let progress = new Progress(ProgressMode.UPLOAD, totalBytes),
        uploadQueue = new UploadQueue({localPartyId: senderPartyId, totalBytes: totalBytes})

      uploadQueue.on('done', () => {
        progress.done()
        bundleHandler.requeueFailedFilesForUpload()

        $('#hide-show-sucessful').show()
      })
      uploadQueue.on('processing', (data) => progress.update(ProgressBar.PROCESSING, data))
      uploadQueue.on('uploading', (data) => progress.update(ProgressBar.PROCESSING, progress.update(ProgressBar.TRANSFER, data)))
      uploadQueue.on('uploadend', (fileInfo) => {
        updateUploadStatus(fileInfo)
      })

      document.querySelector('#cancelUploadBtn').addEventListener('click', () => {
        // $('#cancelUploadBtn').prop('disabled', true)
        uploadQueue.abortSignal = true
        progress = new Progress(ProgressMode.CANCEL)
        while(bundleHandler.hasFilesToUpload()) {
          let file = bundleHandler.getNextToUpload()
          file.uploadStatus = 'ko'
          file.status = FileStatus.CANCELLED
          updateUploadStatus(file)
        }
      })

      if (isEncryptionRequired) {
        let encryptionCertificateX509SubjectName, asymmetricCipher
        $.get('getCertificate', {senderParty: icaDetails.senderParty,receiverParty: $('#recipient').val()}, (data) => {
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
        dialogTitle = messages[I18Label.LABEL_NO_FILE_SELECTED],
        dialogBody = plainDialogBody(messages[I18Message.MESSAGE_UPLOAD_AT_LEAST_ONE_FILE]),
        dialogButtons = [ PromptDialogBtn.OK ]
      PromptDialog({title: dialogTitle, body: dialogBody, buttons: dialogButtons})
    }
  })

  $('#resetUploadFiles').on('click', () => {
    $('#fileList').empty()
    $('#upload-total-files-error').hide()
    bundleHandler.removeAll()
    cleanTree()
    updateTotals()
    updatePartials()
  })



  $("#recipient").combobox()
  $("#msgInstrument").combobox()

  setButtonsEventsListeners()

  if (retentionExpired) {
    GlobalAccess.common.disableMessageInputFields()
    GlobalAccess.common.disableSaveSendButtons()
    disableAttachmentListEditing()
  } else {
    loadMessageIca(icaDetailsVO).then( () => {
      const integrityCheck = icaDetails.integrityCode != 'MODERATE'
      $('#sing-message-chk').prop('checked', integrityCheck).attr("disabled", integrityCheck)
      if (integrityCheck) {
        $('#p12-input').collapse('show')
      }
    })
  }
})

let setButtonsEventsListeners = () => {
  $("[name='sendMessageButton']").click(() => {
    $.blockUI({ message: '' })
    validateAndSend()
  })
  $("[name='saveMessageAsDraftButton']").click(() => {
    $.blockUI({ message: '' })
    saveMessage()
  })
  $("[name='cancelMessageButton']").click(cancelEditMessage)
}

let hideSuccessfulUploadedFiles = () => {
  if($('#hide-show-sucessful').prop('checked')) {
    $(".status-success").parent().parent().parent().parent().parent().hide()
    $(".status-failed").parent().parent().parent().parent().parent()  .show()
    // $('li').has('table').has('tbody').has('tr').has('td').has('span').has('.status-failed').show()
  } else {
    $(".status-success").parent().parent().parent().parent().parent().show()
  }
}

let buildFileRow = (fileId, fileInfo) => {

  let tr = document.createElement("tr"), fileNameTd = document.createElement("td"), fileSizeTd = document.createElement("td"),
    confidentialTd = document.createElement("td"), commentTd = document.createElement("td"),  statusTd = document.createElement("td"), removeTd = document.createElement("td")
  tr.setAttribute('id', fileId)

  tr.append(fileNameTd)
  fileNameTd.append(fileInfo.fileName)

  fileSizeTd.className = "text-right"
  tr.append(fileSizeTd)
  fileSizeTd.append(formatFileSize(fileInfo.fileSize))

  tr.append(confidentialTd)
  let confidentialSpan = document.createElement("span")
  confidentialTd.append(confidentialSpan)
  if (fileInfo.confidential) {
    confidentialSpan.className = "attachment confidential"
  } else {
    confidentialSpan.className = "attachment non-confidential"
  }
  confidentialSpan.setAttribute('data-toggle', 'tooltip')
  confidentialSpan.setAttribute('title', 'Set confidential')
  confidentialSpan.onclick = function(evt) {
    toggleConfidential(fileId, confidentialSpan)
  }

  tr.append(commentTd)
  let commentSpan = document.createElement("span")
  commentTd.append(commentSpan)
  commentSpan.className = "attachment-comment glyphicon glyphicon-comment"
  commentSpan.setAttribute('data-toggle', 'tooltip')
  commentSpan.setAttribute('title', 'Comments')
  // commentSpan.click('click', (evt) => handleComment(fileId, commentSpan))
  commentSpan.onclick = function (evt) {
    handleComment(fileId, commentSpan)
  }
  if (fileInfo.comment) {
    commentSpan.style.color = 'darkorange'
  } else {
    commentSpan.style.color = 'dodgerblue'
  }

  // hiding the confidential and comment spans when there is no metadata handler
  if (metadataHandler == null) {
    confidentialSpan.style.visibility = 'hidden'
    commentSpan.style.visibility = 'hidden'
  }

  statusTd.className = 'status-col'
  tr.append(statusTd)
  let statusSpan = document.createElement("span")
  statusSpan.className = "status-span"
  statusTd.append(statusSpan)
  if (fileInfo.uploadStatus == 'ok') {
    statusSpan.classList.remove('status-failed')
    statusSpan.classList.remove('text-danger')
    statusSpan.classList.add('text-success','status-success')
    statusSpan.setAttribute('data-toggle','tooltip')
    statusSpan.setAttribute('title',fileInfo.status.tooltip)
    statusSpan.append(fileInfo.status.name)
  }
  if (fileInfo.uploadStatus == 'ko') {
    statusSpan.classList.remove('status-success')
    statusSpan.classList.remove('text-success')
    statusSpan.classList.add('text-danger','status-failed')
    statusSpan.setAttribute('data-toggle','tooltip')
    statusSpan.setAttribute('title',fileInfo.status.tooltip)
    statusSpan.append(fileInfo.status.name)
  }
  tr.append(removeTd)
  let removeSpan = document.createElement("span")
  removeTd.append(removeSpan)
  removeSpan.className = "attachment-remove glyphicon glyphicon-remove"
  removeSpan.style.color = 'red'
  removeSpan.setAttribute('data-toggle', 'tooltip')
  removeSpan.setAttribute('title', messages[I18Label.LABEL_REMOVE_FILE])
  removeSpan.onclick = function(e) {
    removeAttachment(fileId)
  }

  return tr
}


function buildTable() {
  $('#fileList').empty()
  $('#fileList').append(to_ul(treeFolderStructure))
  hideSuccessfulUploadedFiles()
}

function to_ul(branches) {
  let ul = document.createElement('ul')
  ul.style.marginLeft = '-10px'
  for (let i=0, n=branches.length; i<n; i++) {
    let branch = branches[i]
    let folderName = document.createTextNode(branch.name)
    let li

    if (branch.isDir) {
      li = document.createElement('li')
      let img = document.createElement('img')
      img.width = '16'
      img.height = '16'
      img.src = 'images/img/icon_upload.png'
      img.style.marginRight = '5px'
      li.appendChild(img)
      let folderNameSpan = document.createElement("span")
      folderNameSpan.style.fontWeight = 'bold'
      folderNameSpan.appendChild(folderName)
      // li.appendChild(folderName)
      li.appendChild(folderNameSpan)
      let removeSpan = document.createElement("span")
      li.appendChild(removeSpan)
      removeSpan.className = 'attachment-remove glyphicon glyphicon-remove'
      removeSpan.style.color = 'red'
      removeSpan.style.marginLeft = '5px'
      removeSpan.setAttribute('data-toggle', 'tooltip')
      removeSpan.setAttribute('title', messages[I18Label.LABEL_REMOVE_FILE])
      removeSpan.onclick = function(e) {
        removeFolder(folderName.nodeValue)
      }
      li.appendChild(to_ul(branch.children))
    } else {
      li = document.createElement('li')
      let tr = buildFileRow(branch.fileInfo.fileId,branch.fileInfo)
      let tbl = document.createElement("table")
      tbl.style.cssText = "display: inline-table;"
      let tblBody = document.createElement("tbody")
      tbl.append(tblBody)
      tblBody.append(tr)
      li.append(tbl)
    }
    ul.appendChild(li)
  }
  return ul
}

let updateUploadStatus = (fileInfo) => {
  if (fileInfo.uploadStatus == 'ok') {
    $('#' + fileInfo.fileId + ' .status-span')
      .removeClass("status-failed")
      .removeClass('text-danger')
      .addClass('text-success status-success')//.css('color', 'green')
      .attr('data-toggle', 'tooltip').attr('title', fileInfo.status.tooltip)
      .text(fileInfo.status.name)
    if($('#hide-show-sucessful').prop('checked')) {
      $('.status-success').parent().parent().parent().parent().parent().hide()
    }
  } else {
    $('#' + fileInfo.fileId + ' .status-span')
      .removeClass("status-success")
      .removeClass('text-success')
      .addClass('text-danger status-failed')//.css('color', 'green')
      .attr('data-toggle', 'tooltip').attr('title', fileInfo.status.tooltip)
      .text(fileInfo.status.name)
  }
  updatePartials()
}

let disableTableIcons = (icons) => {
  icons.attr('disabled', true).css('cursor', 'not-allowed').off()
}
let disableAttachmentListEditing = () => {
  $('#addFiles').css('cursor', 'not-allowed').parent().attr("disabled", true)

  //Disabling Drag&Drop for the attachments
  $('body').on('drop', function (e) { return false })
  disableTableIcons($('.attachment'))
  disableTableIcons($('.attachment-comment'))
  disableTableIcons($('.attachment-remove'))


}

let toggleConfidential = (fileId, confidentialSpan) => {
  let fileInfo = bundleHandler.getFileInfo(fileId)
  fileInfo.confidential = !fileInfo.confidential

  if (fileInfo.confidential) {
    confidentialSpan.classList.remove("non-confidential")
    confidentialSpan.classList.add("confidential")
  } else {
    confidentialSpan.classList.remove("confidential")
    confidentialSpan.classList.add("non-confidential")
  }
}

let removeFolder = (folderName) => {
  findAndRemoveFolder(treeFolderStructure, folderName)
  buildTable()
}
let removeAttachment = (fileId) => {
  bundleHandler.removeFile(fileId)
  findAndRemoveFile(treeFolderStructure, fileId)
  buildTable()
  updateTotals()
  updatePartials()
}

let findAndRemoveFolder = (folderStructure, folderName) => {
  for (let i=0, n=folderStructure.length; i<n; i++) {
    let branch = folderStructure[i]
    if (branch != undefined) {
      let currentFolderName = branch.name
      if (branch.isDir) {
        if (currentFolderName == folderName) {
          folderStructure.splice(folderStructure.indexOf(branch),1)
          removeOrphanFiles(branch.children)
        } else {
          findAndRemoveFolder(branch.children, folderName)
        }
      }
    }
  }
}

let removeOrphanFiles = (folder) => {
  for (let i=0, n=folder.length; i<n; i++) {
    let branch = folder[i]
    branch.isDir ? removeOrphanFiles(branch.children) : removeAttachment(branch.fileInfo.fileId)
  }
}

function findAndRemoveFile(folderStructure, fileId) {
  let n = folderStructure == undefined ? 0 : folderStructure.length
  for (let i=0; i<n; i++) {
    let branch = folderStructure[i];
    if ( branch != undefined ) {
      if (!branch.isDir && branch.fileInfo.fileId == fileId) {
        folderStructure.length == 1 ? folderStructure.splice(branch) : folderStructure.splice(folderStructure.indexOf(branch),1)
      } else {
        findAndRemoveFile(branch.children, fileId)
      }
    }
  }
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
        commentSpan.style.color = 'darkorange'
      } else {
        commentSpan.style.color = 'dodgerblue'
      }
    }
  })
}

let loadMessageIca = (icaDetailsVO) => {
  return new Promise((resolve, reject) => {
    let selectPartyDialogHtml = $('#recipient-select-container').html()

    $('#recipient-select-container').remove()

    icaDetails = icaDetailsVO ? JSON.parse(icaDetailsVO) : false

    if(!icaDetails) {
      PromptDialog({
        title: messages[I18Label.LABEL_SELECT_REMOTE_DIALOG],
        body: selectPartyDialogHtml,
        buttons: [PromptDialogBtn.OK, PromptDialogBtn.CANCEL],
        beforeOkFn: () => {
          let remotePartyNodeName = $("#recipient-select").val()
          $('#recipient-displayName').text($("#recipient-select option:selected").text())
          $('#recipient').val(remotePartyNodeName)

          $.ajax({
            url: url('reloadMessageIca.do'),
            type: 'GET',
            data: {localPartyNodeName: localPartyNodeName, remotePartyNodeName: remotePartyNodeName},
            dataType: 'json',
            cache: false,
            success: (res) => {
              icaDetails = res
              isEncryptionRequired = icaDetails.confidentialityCode != "PUBLIC"
              resolve()
            },
            error: (xhr, status, error) => {
              console.log(error)
              reject()
            }
          })
        },
        beforeCancelFn: () => {
          window.location.href = referrerUrl
          reject()
        }
      })

    } else {
      if (icaDetails.receiverParty) {
        $('#recipient').val(icaDetails.receiverParty)
      }

      resolve()
    }
  })
}


let validateAndSend = () => {

  if (bundleHandler.hasFilesToUpload()) {
    $.unblockUI()
    PromptDialog({
      title: messages[I18Label.LABEL_UPLOAD_NOT_FINISHED],
      body: plainDialogBody(messages[I18Message.MESSAGE_UPLOAD_FILES_BEFORE_SENDING_MESSAGE]),
      buttons: [PromptDialogBtn.OK]
    })
    return
  }

  $("#compMsg").attr("action", 'saveSentMessage.do')
  GlobalAccess.common.cleanErrorMessages()

  let validationOptions = {}
  if (metadataHandler != null) {
    validationOptions.metadataValidationFn = validateEdmaMetadata
  }

  bundleHandler.isReadyForSend(validationOptions).then(() => {
    if ($('#sing-message-chk').prop('checked')) {
      bundleHandler.sign(GlobalAccess.crypto.getSelectedIdentity()).then(() => {
        submitForm()
      })
    } else {
      submitForm()
    }
  }).catch((validationErrors) => {
    $.unblockUI()
    validationErrors.forEach((msgErrorCode) => {
      GlobalAccess.common.displayError(msgErrorCode)
    })
  })
}

let updateTotals = () => {
  document.getElementById('uploadSize').innerText = formatFileSize(bundleHandler.totalSize)
  document.getElementById('totalFiles').innerText = bundleHandler.filesInfo.size.toString()
}

let updatePartials = () => {
  document.getElementById('filesToUpload').innerText = `${bundleHandler.filesInfo.size - $('.status-success').length}`
}

export let saveMessage = () => {
  if (bundleHandler.hasFilesToUpload()) {
    PromptDialog({
      title: messages[I18Label.LABEL_UPLOAD_NOT_FINISHED],
      body: plainDialogBody(messages[I18Message.MESSAGE_UPLOAD_FILES_BEFORE_SAVING_MESSAGE]),
      buttons: [PromptDialogBtn.OK]
    })
    $.unblockUI()
    return
  }

  $("#compMsg").attr("action", 'saveDraftMessage.do')
  GlobalAccess.common.cleanErrorMessages()

  bundleHandler.isReadyForSave()
    .then( () => {
      submitForm()
    })
    .catch((validationErrors) => {
      $.unblockUI()
      validationErrors.forEach((msgErrorCode) => {
        GlobalAccess.common.displayError(msgErrorCode);
      })
    })
}



let cancelMessage = () => {
  let cancelMsg = messages[I18Message.MESSAGE_CANCEL_MESSAGE]
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
    title: messages[I18Label.LABEL_CANCEL_MESSAGE],
    body: plainDialogBody(messages[I18Message.MESSAGE_CANCEL_EDIT_MESSAGE]),
    buttons: [ PromptDialogBtn.YES, PromptDialogBtn.NO],
  }).then((action) => {
    if (action == PromptDialogBtn.YES) {
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
