/**
 * append polyfill to fix Edge compatibility issues (ETRUSTEX-5517)
 * @type {BundleHandler}
 */
// Source: https://github.com/jserz/js_piece/blob/master/DOM/ParentNode/append()/append().md
(function (arr) {
  arr.forEach(function (item) {
    if (item.hasOwnProperty('append')) {
      return;
    }
    Object.defineProperty(item, 'append', {
      configurable: true,
      enumerable: true,
      writable: true,
      value: function append() {
        var argArr = Array.prototype.slice.call(arguments),
          docFrag = document.createDocumentFragment();

        argArr.forEach(function (argItem) {
          var isNode = argItem instanceof Node;
          docFrag.appendChild(isNode ? argItem : document.createTextNode(String(argItem)));
        });

        this.appendChild(docFrag);
      }
    });
  });
})([Element.prototype, Document.prototype, DocumentFragment.prototype]);
/** end of append polyfill **/
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
});

String.prototype.format = function () {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function (match, number) {
    return typeof args[number] != 'undefined' ? args[number] : match;
  });
};


function getConfigMailDistribution() {
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
        },
        error: function (xhr, status, error) {
            etx_alert(messages['error.admin.generic'], error);
        }
    });
}

function getBrowserInfo(){
  var ua=navigator.userAgent,tem,M=ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];

  if(ua.indexOf("Edge") > -1){
    tem=ua.match(/\bEdge\/(\d+)/);
    return {name:'Edge', version:tem[1]};
  }
  if(/trident/i.test(M[1])){
    tem=/\brv[ :]+(\d+)/g.exec(ua) || [];
    return {name:'IE',version:(tem[1]||'')};
  }
  if(M[1]==='Chrome'){
    tem=ua.match(/\bOPR\/(\d+)/)
    if(tem!=null)   {return {name:'Opera', version:tem[1]};}
  }
  M=M[2]? [M[1], M[2]]: [navigator.appName, navigator.appVersion, '-?'];
  if((tem=ua.match(/version\/(\d+)/i))!=null) {M.splice(1,1,tem[1]);}
  return {
    name: M[0],
    version: M[1]
  };
}


function displayBrowsersCompatibilityTableIfNotCompatible(listOfCompatibleBrowsers) {
  var browser = getBrowserInfo();
  console.log('Your browser :'+browser.name+' : '+browser.version);
  var notCompatible = false;

  for (var i = 0; i < listOfCompatibleBrowsers.length; i++ ){
    if (browser.name == listOfCompatibleBrowsers[i].name && (!listOfCompatibleBrowsers[i].compatible || browser.version < listOfCompatibleBrowsers[i].version)) {
      notCompatible = true;
      break;
    }
  }

  if ( notCompatible ) {
    displayBrowsersCompatibilityTable(listOfCompatibleBrowsers);
  }
}

function displayBrowsersCompatibilityTable(listOfCompatibleBrowsers) {
  var table = document.getElementById('compatible-browsers-table');
  var body = table.getElementsByTagName('tbody')[0];

  for (var i = 0; i < listOfCompatibleBrowsers.length; i++ ) {
    if ( listOfCompatibleBrowsers[i].compatible ) {
      var tr = document.createElement('tr')
      var tdVersion = document.createElement('td')
      var tdComment = document.createElement('td');
      var tdImg = document.createElement('td')
      var img = document.createElement('img');
      var browserName = document.createElement('span');
      img.className = 'image-browser-displayed';
      img.src = listOfCompatibleBrowsers[i].path;
      img.id = listOfCompatibleBrowsers[i].name;
      tdImg.appendChild(img)
      browserName.className = 'image-browser-displayed';
      browserName.innerText = listOfCompatibleBrowsers[i].name
      tdImg.appendChild(browserName)
      tdVersion.style.textAlign = 'center';
      tdVersion.innerHTML = listOfCompatibleBrowsers[i].version
      tdComment.innerHTML = listOfCompatibleBrowsers[i].comment.replace(/\\r\\n/g,'<br>')
      tr.appendChild(tdImg)
      tr.appendChild(tdVersion)
      tr.appendChild(tdComment)
      body.appendChild(tr)
    }
  }

  $('#body-content').hide();
  $('#compatible-browser').show();
}
