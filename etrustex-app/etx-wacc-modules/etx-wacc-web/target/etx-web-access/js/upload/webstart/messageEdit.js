var fileIdsListJSON = "";
var encodedIdSelectedPayloadFile = "";
//var encodedSignature = "";
var signedBundle = "";
var signature = "";
var dataToSign = "";
var currentFileList = "";
var eTrustExEdmaMdDocumentList = [];

function validateAndSend() {
  $("#compMsg").attr("action", 'saveSentMessage.do');
  cleanErrorMessages();

   blockPage();
    pingForUpload();

    if (isFormValid())  {
      submitFormWS();
    } else {
      unblockPage();
    }
}

function isFormValid() {

  fileIdsListJSON = $('#fileIdsListJSON').val();
  encodedIdSelectedPayloadFile = $('#encodedIdSelectedPayloadFile').val();
  //encodedSignature=  $('#encodedSignature').val();

  signedBundle = $('#xmlSignedBundle').val();
  signature = $('#xmlExtractedSignature').val();
  dataToSign = $('#xmlDataToSign').val();

  var isValid = true;
  if ($('#subject').val().length == 0) {
    isValid = false;
    displayError('message.subjectErrorMessage');
  }

  if ($('#recipient').val().length == 0) {
    isValid = false;
    displayError('message.recipientErrorMessage');
  }
  if(fileIdsListJSON==""){
    isValid=false;
    displayError('message.attachmentListEmpty');
  }
  var errors = validateForm();
  if (errors.length > 0) {
    isValid = false;
    errors.forEach(function(msgErrorCode) {
      displayError(msgErrorCode);
    });
  }



  return isValid;
}

function pingForUpload() {
  pingIntervalId = window.setInterval(function () {
    ping(pingUrl,
      null,
      function () {
        stopPinging();
        window.location.href = partyUrl;
      })
  }, 10 * 60 * 1000);
}

function saveMessage() {
  $("#compMsg").attr("action", 'saveDraftMessage.do');
  cleanErrorMessages();

    blockPage();
    pingForUpload();

    if (isFormSaveMessageValid()) {
      submitFormWS()
    } else {
      unblockPage();
    }
}

function isFormSaveMessageValid() {
  var isValid = true;

  fileIdsListJSON = $('#fileIdsListJSON').val();
  encodedIdSelectedPayloadFile = $('#encodedIdSelectedPayloadFile').val();
  //encodedSignature=  $('#encodedSignature').val();
  signedBundle = $('#signedBundle').val();
  dataToSign = $('#dataToSign').val();
  signature = $('#signature').val();

  if ($('#recipient').val().length == 0) {
    isValid = false;
    displayError('message.recipientErrorMessage');
  }

  return isValid;
}

function cleanErrorMessages() {
  var errorsTable = document.getElementById('errors-table');
  var rowCount = errorsTable.rows.length;
  for (var i = rowCount - 1; i >= 0; i--) {
    errorsTable.deleteRow(i);
  }
}

function displayError(err) {
  stopPinging();
  var table = document.getElementById('errors-table');
  var body = table.getElementsByTagName('tbody')[0];
  var tr = document.createElement('tr');
  var td = document.createElement('td');
  td.className = "error-text";
  td.innerHTML = messages[err];
  tr.appendChild(td);
  body.appendChild(tr);
}

function submitFormWS() {

  var form = document.getElementById('compMsg');
  //form.encodedSignature.value = encodedSignature;
  form.signedBundle = signedBundle;
  form.dataToSign = dataToSign;
  form.signature = signature;

  form.fileIdsListJSON.value = fileIdsListJSON;
  form.encodedIdSelectedPayloadFile.value = encodedIdSelectedPayloadFile;
  if (typeof(getMessageMetadata) === 'function') {
    form.messageMetadata.value = getMessageMetadata();
  }

  stopPinging();
  form.submit();
}

function cancelMessage() {
  var cancelMsg = messages["message.cancelMessage"];
  var r = confirm(cancelMsg);
  if (r == true) {
    window.location.href = partyUrl;
  }
}

function disableSaveSendButtons() {
  $('[id^=save-btn-]').each(function() {
    $(this).addClass("hidden-btn-disabled");
    $(this).removeClass("hidden-btn");
    $(this).removeAttr("onclick");
  });

  $('[id^=send-btn-]').each(function() {
    $(this).addClass("hidden-btn-disabled");
    $(this).removeClass("hidden-btn");
    $(this).removeAttr("onclick");

  });
}

function enableSaveSendButtons() {
  $('[id^=save-btn-]').each(function() {
    $(this).removeClass("hidden-btn-disabled");
    $(this).addClass("hidden-btn");
    $(this).unbind('click');
    $(this).click(saveMessage);
  });

  $('[id^=send-btn-]').each(function() {
    $(this).removeClass("hidden-btn-disabled");
    $(this).addClass("hidden-btn");
    $(this).unbind('click');
    $(this).click(validateAndSend);
  });
}


function disableMessageInputFields() {
  $("#compMsg :input").prop("disabled", true);
}

//only for FF or CH, on IE < 9 onmouseout was used
$(document).on('paste', 'textarea', function (event) {
  var _this = this;
  setTimeout(function () {
    if ($(_this).val().length > 4000) {
      $(_this).val($(_this).val().substr(0, 4000));
    }
  }, 100);
});


$(document).ready(function() {
  getConfigMailDistribution();
  checkMandatoryFields();

  $("#recipient").combobox();
  $("#msgInstrument").combobox();

});
