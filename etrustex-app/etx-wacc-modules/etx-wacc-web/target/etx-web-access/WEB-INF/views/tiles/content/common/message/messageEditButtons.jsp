<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
    $( document ).ready(function() {
        <c:if test="${retentionMetadata != null && retentionMetadata.retentionExpired}">
        disableSaveSendButtons();
        </c:if>
    });

</script>

<div class="content-controls">
    <img src="<c:url value="/images/img/icon_save.png"/>" width="24" height="23" align="absmiddle" alt="Save" />
    <a name="saveMessageAsDraftButton" class="hidden-btn" id="save-btn-${param.idx}">
      <spring:message code="label.save"/>
    </a>

    <img src="<c:url value="/images/img/separator.png"/>" width="24" height="23" align="absmiddle" alt=""/>

    <img src="<c:url value="/images/img/icon_send.png"/>" width="24" height="23" align="absmiddle" alt="Send"/>
    <a name="sendMessageButton" class="hidden-btn" id="send-btn-${param.idx}">
      <spring:message code="label.send"/>
    </a>

    <input type="file" id="certificateInput" style="display:none"/>

    <img src="<c:url value="/images/img/separator.png"/>" width="24" height="23" align="absmiddle" alt=""/>

    <img src="<c:url value="/images/img/icon-left-arrow.png"/>" width="24" height="23" align="absmiddle" alt="Cancel" />
    <a id="cancel-btn-${param.idx}" class="hidden-btn-enabled" name="cancelMessageButton">
      <spring:message code="label.cancel"/>
    </a>
</div>