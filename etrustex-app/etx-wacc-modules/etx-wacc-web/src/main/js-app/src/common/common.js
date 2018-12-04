/***********************************************/
/****** Webstart & Websockets stuff ************/
/***********************************************/
//This is the id of the process that initialized the interval timeout of the running window
var pingIntervalId;
var emailsFormat = new Array();

function ping(url, sessionIsAlive, sessionIsTimedOut) {
  var request = $.ajax({
    url: url,
    cache: false,
    context: document
  });
  request.done(function(result) {
    if (result == "isAlive") {
      if(typeof(sessionIsAlive) == 'function') {
        sessionIsAlive();
      }
    } else if (typeof(sessionIsTimedOut) == 'function') {
      sessionIsTimedOut();
    }
  });
  request.fail(function(jqXHR, result){
    if(typeof(sessionIsTimedOut) == 'function' && (result == null || result != "isAlive")){
      sessionIsTimedOut();
    }
  });
};

function stopPinging(){
  window.clearInterval(pingIntervalId);
};

function blockPage(){
  computeBlockingDivHeight();
  $("#disabling-div").show();
};

function unblockPage(){
  $("#disabling-div").hide();
};
/****** End Webstart & Websockets stuff ************/

var _contextRoot = $("#ctxRootId").attr("href");

function computeBlockingDivHeight(){
  var docHeight = $(document).height();
  $("#disabling-div").height(docHeight);
};

function goToLink(url) {
  window.location.href = url;
}

function url(url) {
  var value = url;
  if (typeof(_contextRoot) == 'string' && url.indexOf('/') == 0) {
    value = _contextRoot.replace(/\/$/, '') + url;
  }
  return value;
}

$(function() {
  $('.content').css('display', 'block');
  $('.sidebar').css('display', 'block');
  $('#languages-script').css('display', 'block');
});

function b64EncodeUnicode(str) {
  // first we use encodeURIComponent to get percent-encoded UTF-8,
  // then we convert the percent encodings into raw bytes which
  // can be fed into btoa.
  return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g,
    function toSolidBytes(match, p1) {
      return String.fromCharCode('0x' + p1);
    }));
}

function b64DecodeUnicode(str) {
  // Going backwards: from bytestream, to percent-encoding, to original string.
  return decodeURIComponent(atob(str).split('').map(function(c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));
}

function escapeHtml(content) {
  var div = document.createElement('div');

  if (content != null) {
    div.innerHTML = content;
  }

  return div.firstChild.nodeValue;
}

// see http://stackoverflow.com/questions/2507030/email-validation-using-jquery
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
    let res = new Promise((resolve, reject) => {
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

$(document).ready(function () {
  // Cookies banner
  $('#cbtn').click(function () {
    var date = new Date();
    date.setTime(date.getTime() + 365 * 24 * 60 * 60 * 1000);
    document.cookie = cname + " = true; expires = " + date.toGMTString();
    $("#cbanner").slideUp("slow");
  });

  // Change party select
  $("#selectedPartyId").on('change', function(){
    if($("#selectedPartyId").val() != null){
      document.forms['changeCurrentParty'].submit();
    }
  })

  // Switch version
  $('#switchVersion').click( function () {
    var href= location.href;
    if(href.indexOf("?") == -1) {
      href = href + "?pid=" + $('#selectedPartyId').val();
    }
    $.post('switchVersion', function () {
      location.replace(href);
    })
  })
});