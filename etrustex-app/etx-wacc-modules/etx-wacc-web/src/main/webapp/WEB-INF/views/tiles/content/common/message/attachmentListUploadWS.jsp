<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://etrustex.ec.europa.eu/functions" %>

<c:set var="ver">@eTrustEx.version@-@eTrustEx.timestamp@</c:set>
<h3><spring:message code="label.listOfFiles"/></h3>

<div class="left">
    <img src="<c:url value="/images/img/icon_upload.png"/>" alt="Upload files" width="45" height="45"/>
</div>

<link href="<c:url value="/css/uploadPanel.css?ver=${ver}"/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript" language="javascript" src="<c:url value="/js/websocket/WSClient.js"/>"></script>
<div class="main-panel">
    <div class="panel panel-default upload-panel">
        <c:if test="${businessName == 'edma'}">
            <%@ include file="upload/attachmentEdmaList.jsp" %>
        </c:if>
        <c:if test="${businessName != 'edma'}">
            <%@ include file="upload/attachmentGenericList.jsp" %>
        </c:if>
        <tilesx:useAttribute name="business"/>
    </div>

    <button type="button" id="openUploadJNLP" class="btn btn-primary.disabled"><spring:message
            code="label.webstart.openUploadWebstart"/></button>
</div>

<script type="text/javascript">
  var connectionId = '<c:out value="${pageContext.session.id}"/>';
  var endpoint = '<c:out value="${pageContext.request.contextPath}"/>';
  var websocketClient = new WSClient(endpoint, connectionId);
  var currentAttachments = null;

  $(document).ready(function () {
    var attachmentsEncoded = $('#encodedFileElements').val();
    var isEncryptionRequired = $('#isEncryptionRequired').val();
      if (attachmentsEncoded != undefined && attachmentsEncoded != null && attachmentsEncoded != '') {
      currentFileList = b64DecodeUnicode(attachmentsEncoded);
      currentAttachments = JSON.parse(currentFileList);
      updateList(currentAttachments);
    }

    /* Send and save as draft buttons */
    $( "[name='sendMessageButton']" ).click(validateAndSend);
    $( "[name='saveMessageAsDraftButton']" ).click(saveMessage);
      $( "[name='cancelMessageButton']" ).click(cancelMessage)
  });

  donwloadJNLP = function () {
    if ($('#uploadJNLPForm').length) {
      $('#uploadJNLPForm').remove();
    }
    var subject = document.getElementById("subject").value;
    var action = "applet/uploadApplet.jnlp";
    var paramString = "sessionId=${pageContext.session.id}&msgId=${ps.messageId}&msgSubject=" + subject + "&partyId=${ps.partyId}&token=${token}&currentAttachments=" + currentAttachments + "&lang=${pageContext.response.locale.language}&timeLastUpdate=${msg.updatedOn.time}&remotePartyNodeName=" + currentIca.receiverParty;
    var params = paramString.split('&');
    var form = $('<form/>', {action: action, method: 'post', id: 'uploadJNLPForm'}).appendTo('body');
    for (var i in params) {
      var tmp = params[i].split('=');
      var key = tmp[0], value = tmp[1];
      $('<input/>', {type: 'hidden', name: key, value: value}).appendTo(form);
    }
    $(form).submit();
    websocketClient.startPolling();
  }
</script>

