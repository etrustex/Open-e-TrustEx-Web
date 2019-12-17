function validateForm() {
    var errors = [];

    if ($('#senderOrganisationName').val().length > 0) {
        if ($('#senderCountry').val().length == 0) {
            errors.push('message.countryErrorMessage');
        }
    }

    if ($('#senderContactPerson').val().length == 0) {
        errors.push('message.contactPersonErrorMessage');
    }

    if ($('#senderEmail').val().length == 0) {
        errors.push('message.emailErrorMessage');
    } else {
      if (!isEmail($('#senderEmail').val())) {
        errors.push('error.admin.wrongEmail');
      }
    }

    if ($('#caseNumber').val().length == 0) {
        errors.push('message.please.enter.case.number');
    }

    if ($('#msgInstrument').val().length == 0) {
        errors.push('message.instrumentErrorMessage');
    }

    if ($('#msgDistributionList').val().length) {
      if ( !validateMultiRecipients($('#msgDistributionList').val(), errors) ) {
        $('#msgDistributionList').addClass("ui-state-error");
      } else {
        $('#msgDistributionList').removeClass("ui-state-error");
      }
    }

    return errors;
}

function getMessageMetadata() {

    var ETrustExEdmaMd = {
        subject: null,
        reqForInfoNumber: null,
        fileNumber: null,
        inAttentionOf: null,
        inboundDate: null,
        language: null,
        outboundMetaData: null,
        inboundMetaData: null,
        outboundDate: null,
        comment: null
    };
    var ETrustExEdmaMdOutbound = {
        to: null,
        registrationNumber: null
    };
    var ETrustExEdmaMdInbound = {
        transferStatus: null,
        documents: [],
        onBehalfOf: null,
        sender: null,
        from: null,
        yourReference: null,
        instrument: null,
        distributionList: null
    };


    ETrustExEdmaMd.subject = document.getElementById("subject").value;
    var e = document.getElementById("lang");
    var lang = e.options[e.selectedIndex].value;
    ETrustExEdmaMd.language = lang;
    //skip the inbound and outbound date
    ETrustExEdmaMd.fileNumber = document.getElementById("caseNumber").value;

    //outbound metadata
    ETrustExEdmaMdOutbound.registrationNumber = document.getElementById("recipient").value;

    ETrustExEdmaMd.outboundMetaData = ETrustExEdmaMdOutbound;


    //inbound metadata
    ETrustExEdmaMdInbound.transferStatus = "SENT";
    var documents = [];
    var currentFiles;

    if (currentFileList.length == 0) {
        currentFileList = "{}";
    }

    currentFiles = JSON.parse(currentFileList);


    if (!jQuery.isEmptyObject(currentFiles)) {
    	//forEach throws exception if currentFiles is empty
	    currentFiles.forEach(function (fileListElement) {
	        var ETrustExEdmaMdDocument = {
	            confidential: null,
	            documentComment: null,
	            file: null,
	            fileReferenceId: null,
	            path: null
	        };
	        ETrustExEdmaMdDocument.confidential = "" + fileListElement.confidential;
	        ETrustExEdmaMdDocument.documentComment = fileListElement.comment;
	        ETrustExEdmaMdDocument.file = fileListElement.fileName;
	        ETrustExEdmaMdDocument.path = fileListElement.path;
	        ETrustExEdmaMdDocument.fileReferenceId = fileListElement.fileReferenceId;
	        documents.push(ETrustExEdmaMdDocument)
	
	    });
    }
    ETrustExEdmaMdInbound.documents = documents;
    var ETrustExEdmaMdCompany_onBehalfOf = {
        company: null,
        street: null,
        city: null,
        phone: null,
        eMail: null,
        nationalRegNumber: null,
        country: null,
        position: null,
        zip: null,
        contactPerson: null
    };
    ETrustExEdmaMdCompany_onBehalfOf.city = document.getElementById("edmaMessage.onBehalfCity").value;
    ETrustExEdmaMdCompany_onBehalfOf.company = document.getElementById("edmaMessage.onBehalfCompanyName").value;
    ETrustExEdmaMdCompany_onBehalfOf.contactPerson = document.getElementById("edmaMessage.onBehalfContactPerson").value;
    ETrustExEdmaMdCompany_onBehalfOf.country = document.getElementById("edmaMessage.onBehalfCountry").value;
    ETrustExEdmaMdCompany_onBehalfOf.eMail = document.getElementById("edmaMessage.onBehalfEmail").value;
    ETrustExEdmaMdCompany_onBehalfOf.phone = document.getElementById("edmaMessage.onBehalfPhone").value;
    ETrustExEdmaMdCompany_onBehalfOf.position = document.getElementById("edmaMessage.onBehalfPosition").value;
    ETrustExEdmaMdCompany_onBehalfOf.street = document.getElementById("edmaMessage.onBehalfStreet").value;
    ETrustExEdmaMdCompany_onBehalfOf.zip = document.getElementById("edmaMessage.onBehalfZip").value;

    ETrustExEdmaMdInbound.onBehalfOf = ETrustExEdmaMdCompany_onBehalfOf;
    var ETrustExEdmaMdCompany_sender = {
        company: null,
        street: null,
        city: null,
        phone: null,
        eMail: null,
        nationalRegNumber: null,
        country: null,
        position: null,
        zip: null,
        contactPerson: null
    };
    ETrustExEdmaMdCompany_sender.city = document.getElementById("edmaMessage.senderCity").value;
    ETrustExEdmaMdCompany_sender.company = document.getElementById("senderOrganisationName").value;
    ETrustExEdmaMdCompany_sender.contactPerson = document.getElementById("senderContactPerson").value;
    ETrustExEdmaMdCompany_sender.country = document.getElementById("senderCountry").value;
    ETrustExEdmaMdCompany_sender.eMail = document.getElementById("senderEmail").value;
    ETrustExEdmaMdCompany_sender.phone = document.getElementById("edmaMessage.senderPhone").value;
    ETrustExEdmaMdCompany_sender.position = document.getElementById("edmaMessage.senderPosition").value;
    ETrustExEdmaMdCompany_sender.street = document.getElementById("edmaMessage.senderStreet").value;
    ETrustExEdmaMdCompany_sender.zip = document.getElementById("edmaMessage.senderZip").value;

    ETrustExEdmaMdInbound.sender = ETrustExEdmaMdCompany_sender;
    ETrustExEdmaMdInbound.from = document.getElementById("userId").value;
    ETrustExEdmaMdInbound.yourReference = "?????";
    ETrustExEdmaMdInbound.instrument = document.getElementById("msgInstrument").value;
    ETrustExEdmaMdInbound.distributionList = document.getElementById("msgDistributionList").value;


    ETrustExEdmaMd.inboundMetaData = ETrustExEdmaMdInbound;

    return JSON.stringify(ETrustExEdmaMd, function (k, v) {
        if (v != null && v != "") {
            return v;
        }
    });

}

function validateMultiRecipients(emails, errors) {
  var trimmedValue = emails.trim() //replace(/\s+/, "");
  var isValid = true;

  if (trimmedValue.length) {
    $.each(trimmedValue.split(/;|,/), function (index, email) {
      email = email.trim() // removing spaces around ',' and ';'
      if (!isEmail(email)) {
        errors.push('error.admin.wrongEmail');
        return isValid = false;
      } else if (!isValidCommissionEmail(email)) {
        errors.push('error.no.valid.ec.email');
        isValid = false;
      }
      return isValid; // false = break, true = continue
    });
  }

  return isValid;

}
