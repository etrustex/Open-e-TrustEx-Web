import { bundleHandler } from "./messageEdit";
import { I18Message} from "../common/i18";

export class EdmaMetadata {
  constructor() {
    getConfigMailDistribution().then((res) => {
      res.forEach((emailFormat) => {
        emailFormats.push(emailFormat)
      })
    })
  }


  getMessageMetadata() {

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
    }
    var ETrustExEdmaMdOutbound = {
      to: null,
      registrationNumber: null
    }
    var ETrustExEdmaMdInbound = {
      transferStatus: null,
      documents: [],
      onBehalfOf: null,
      sender: null,
      from: null,
      yourReference: null,
      instrument: null,
      distributionList: null
    }

    ETrustExEdmaMd.subject = bundleHandler.subject//document.getElementById("subject").value
    let e = document.getElementById("lang")
    let lang = e.options[e.selectedIndex].value
    ETrustExEdmaMd.language = lang
    //skip the inbound and outbound date
    ETrustExEdmaMd.fileNumber = document.getElementById("caseNumber").value

    //outbound metadata
    ETrustExEdmaMdOutbound.registrationNumber = document.getElementById("recipient").value

    ETrustExEdmaMd.outboundMetaData = ETrustExEdmaMdOutbound


    //inbound metadata
    ETrustExEdmaMdInbound.transferStatus = "SENT"
    var documents = []


    for (let fileInfo of bundleHandler.getFileInfos()) {
      var ETrustExEdmaMdDocument = {
        confidential: "" + fileInfo.confidential,
        documentComment:  fileInfo.comment,
        originalFilename: fileInfo.originalFilename,
        file: fileInfo.fileName,
        fileReferenceId: fileInfo.generatedFileId,
        path: fileInfo.filePath ? fileInfo.filePath.replace(/^[/]/,'') : ''
      }
      documents.push(ETrustExEdmaMdDocument)
    }

    ETrustExEdmaMdInbound.documents = documents
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
    }
    ETrustExEdmaMdCompany_onBehalfOf.city = document.getElementById("edmaMessage.onBehalfCity").value
    ETrustExEdmaMdCompany_onBehalfOf.company = document.getElementById("edmaMessage.onBehalfCompanyName").value
    ETrustExEdmaMdCompany_onBehalfOf.contactPerson = document.getElementById("edmaMessage.onBehalfContactPerson").value
    ETrustExEdmaMdCompany_onBehalfOf.country = document.getElementById("edmaMessage.onBehalfCountry").value
    ETrustExEdmaMdCompany_onBehalfOf.eMail = document.getElementById("edmaMessage.onBehalfEmail").value
    ETrustExEdmaMdCompany_onBehalfOf.phone = document.getElementById("edmaMessage.onBehalfPhone").value
    ETrustExEdmaMdCompany_onBehalfOf.position = document.getElementById("edmaMessage.onBehalfPosition").value
    ETrustExEdmaMdCompany_onBehalfOf.street = document.getElementById("edmaMessage.onBehalfStreet").value
    ETrustExEdmaMdCompany_onBehalfOf.zip = document.getElementById("edmaMessage.onBehalfZip").value

    ETrustExEdmaMdInbound.onBehalfOf = ETrustExEdmaMdCompany_onBehalfOf
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
    }
    ETrustExEdmaMdCompany_sender.city = document.getElementById("edmaMessage.senderCity").value
    ETrustExEdmaMdCompany_sender.company = document.getElementById("senderOrganisationName").value
    ETrustExEdmaMdCompany_sender.contactPerson = document.getElementById("senderContactPerson").value
    ETrustExEdmaMdCompany_sender.country = document.getElementById("senderCountry").value
    ETrustExEdmaMdCompany_sender.eMail = document.getElementById("senderEmail").value
    ETrustExEdmaMdCompany_sender.phone = document.getElementById("edmaMessage.senderPhone").value
    ETrustExEdmaMdCompany_sender.position = document.getElementById("edmaMessage.senderPosition").value
    ETrustExEdmaMdCompany_sender.street = document.getElementById("edmaMessage.senderStreet").value
    ETrustExEdmaMdCompany_sender.zip = document.getElementById("edmaMessage.senderZip").value

    ETrustExEdmaMdInbound.sender = ETrustExEdmaMdCompany_sender
    ETrustExEdmaMdInbound.from = document.getElementById("userId").value
    ETrustExEdmaMdInbound.yourReference = "?????"
    ETrustExEdmaMdInbound.instrument = document.getElementById("msgInstrument").value
    ETrustExEdmaMdInbound.distributionList = document.getElementById("msgDistributionList").value


    ETrustExEdmaMd.inboundMetaData = ETrustExEdmaMdInbound

    return JSON.stringify(ETrustExEdmaMd, function (k, v) {
      if (v != null && v != "") {
        return v
      }
    })
  }
}

export let validateDistribution = (emails, errors) => {
  let trimmedValue = emails.trim() //replace(/\s+/, "");
  let isValid = true

  if (trimmedValue.length) {
    $.each(trimmedValue.split(/;|,/), function (index, email) {
      email = email.trim() // removing spaces around ',' and ';'
      if (!isEmail(email)) {
        errors.push(I18Message.ERROR_ADMIN_WRONG_EMAIL)
        return isValid = false
      } else if (!isValidCommissionEmail(email)) {
        errors.push('error.no.valid.ec.email')
        isValid = false
      }
      return isValid // false = break, true = continue
    })
  }
  return isValid
}
export let endsWith_etrustex = (str, suffix)  =>{
  return str.indexOf(suffix, str.length - suffix.length) !== -1;
}

// see http://stackoverflow.com/questions/2507030/email-validation-using-jquery
export let isEmail = (email) => {
  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return regex.test(email);
}

export let isValidCommissionEmail = (email) => {
  var isValid = false
  emailFormats.forEach((format) => {
    if (endsWith_etrustex(email, format)) {
      isValid = true
    }
  })
  return isValid
}

export let emailFormats = []

export let getConfigMailDistribution = () => {
  let res = new Promise((resolve, reject) => {
    $.ajax({
      url: url('getConfigMailDistribution.do'),
      type: 'GET',
      dataType: 'json',
      traditional: true,
      cache: false,
      success: function (response) {
        resolve(response)
      },
      error: function (xhr, status, error) {
        etx_alert(messages[I18Message.ERROR_ADMIN_GENERIC], error);
        reject()
      }
    })})
  return res
}

export let validateEdmaMetadata = () => {
  var errors = []

  if ($('#senderOrganisationName').val().length > 0) {
    if ($('#senderCountry').val().length == 0) {
      errors.push(I18Message.MESSAGE_COUNTRY_ERROR_MESSAGE)
    }
  }

  if ($('#senderContactPerson').val().length == 0) {
    errors.push(I18Message.MESSAGE_CONTACT_PERSON_ERROR_MESSAGE)
  }

  if ($('#senderEmail').val().length == 0) {
    errors.push('message.emailErrorMessage');
  } else {
    if (!isEmail($('#senderEmail').val())) {
      errors.push('error.admin.wrongEmail');
    }
  }

  if ($('#caseNumber').val().length == 0) {
    errors.push(I18Message.MESSAGE_PLEASE_ENTER_CASE_NUMBER)
  }

  if ($('#msgInstrument').val().length == 0) {
    errors.push(I18Message.MESSAGE_INSTRUMENT_ERROR_MESSAGE)
  }

  if ($('#msgDistributionList').val().length) {
    let emails = $('#msgDistributionList').val()
    if (!validateDistribution(emails, errors)) {
      $('#msgDistributionList').addClass("ui-state-error");
    } else {
      $('#msgDistributionList').removeClass("ui-state-error")
    }
  }
  return errors
}