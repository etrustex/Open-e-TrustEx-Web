/*** jQuery $( document ).ready() function ***/
$(function() {
  $('.content').css('display', 'block');
  $('.sidebar').css('display', 'block');

  // Cookies banner
  $('#cbtn').click(function () {
    var date = new Date();
    date.setTime(date.getTime() + 365 * 24 * 60 * 60 * 1000);
    document.cookie = cname + " = true; expires = " + date.toGMTString();
    $("#cbanner").slideUp("slow");
  });

  // Change party selectjavascript scroll to top
  $("#selectedPartyId").on('change', function () {
    if ($("#selectedPartyId").val() != null) {
      document.forms['changeCurrentParty'].submit();
    }
  })
});

/**** Errors ****/
export function displayError(err) {
  var table = $('#errors-table');

  table
    .append($('<tr>')
      .append($('<td>')
        .attr('class', 'error-text')
        .text(messages[err])
      )
    );

  window.scrollTo(0, 0);
}

export function cleanErrorMessages() {
  $('#errors-table').empty()
}

export function disableMessageInputFields() {
  $("#compMsg :input").prop("disabled", true);
}
export function disableSaveSendButtons() {
  $('[id^=save-btn-]').each(function() {
    $(this).addClass("hidden-btn-disabled").removeClass("hidden-btn").off();

  });
  $('[id^=send-btn-]').each(function() {
    $(this).addClass("hidden-btn-disabled").removeClass("hidden-btn").off();


  });
}

// export function enableSaveSendButtons() {
//   $('[id^=save-btn-]').each(function() {
//     $(this).removeClass("hidden-btn-disabled");
//     $(this).addClass("hidden-btn");
//     $(this).unbind('click');
//     $(this).click(saveMessage);
//   });
//
//   $('[id^=send-btn-]').each(function() {
//     $(this).removeClass("hidden-btn-disabled");
//     $(this).addClass("hidden-btn");
//     $(this).unbind('click');
//     $(this).click(validateAndSend);
//   });
// }


/*** TODO change to ES6 the non-ES6 functions below IF USED. Otherwise remove ***/
function goToLink(url) {
  window.location.href = url;
}

function url(url) {
  var value = url;
  var _contextRoot = $("#ctxRootId").attr("href");

  if (typeof(_contextRoot) == 'string' && url.indexOf('/') == 0) {
    value = _contextRoot.replace(/\/$/, '') + url;
  }
  return value;
}


function b64DecodeUnicode(str) {
  // Going backwards: from bytestream, to percent-encoding, to original string.
  return decodeURIComponent(atob(str).split('').map(function(c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));
}


function isEmail(email) {
  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return regex.test(email);
}

function endsWith_etrustex(str, suffix) {
  return str.indexOf(suffix, str.length - suffix.length) !== -1;
}

function isValidCommissionEmail(email) {
    if (emailsFormat.length == 0)    {
        getConfigMailDistribution().then(checkValidCommissionEmail(email))
    } else {
        checkValidCommissionEmail(email)
    }
}

function checkValidCommissionEmail(email) {
    var isValid = false;
    for (i = 0; i < emailsFormat.length; i++) {
        if (endsWith_etrustex(email, emailsFormat[i])) {
            isValid = true;
        }
    }
    return isValid;
}


function deleteDraft(messageId) {
  var deleteDraftMsg = messages["message.deleteDraftMessage"];
  var r = confirm(deleteDraftMsg);
  if (r == true) {
    document.forms['deleteDraftMessage-' + messageId].submit();
  }
}

function getConfigMailDistribution() {
    var res = new Promise((resolve, reject) => {
        $.ajax({
        url: url('getConfigMailDistribution.do'),
        type: 'GET',
        dataType: 'json',
        traditional: true,
        cache: false,
        success: function (response) {
            for (i = 0; i < response.length; i++) {
                emailsFormat.push(response[i]);
            }
            resolve()
        },
        error: function (xhr, status, error) {
            etx_alert(messages['error.admin.generic'], error);
            reject()
        }
    })})
    return res
}
