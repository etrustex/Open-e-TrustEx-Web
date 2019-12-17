<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script>
  var retentionExpired;
  <c:choose>
  <c:when test="${retentionMetadata != null && retentionMetadata.retentionExpired}">
  retentionExpired = true;
  </c:when>
  <c:otherwise>
  retentionExpired = false;
  </c:otherwise>
  </c:choose>

  var localPartyNodeName = "${formBean.message.localParty.nodeName}";
  var icaDetailsVO = "${formBean.icaDetailsVO}";

</script>

<form:form id="compMsg" method="POST" commandName="formBean" action="">

    <div class="content">

        <jsp:include page="./common/message/messageEditButtons.jsp">
            <jsp:param name="idx" value="0"/>
        </jsp:include>

        <div>
            <table id="errors-table">
                <tbody>

                <c:if test="${ps.notifySuccessfulOperation}">
                    <tr>
                        <td class="notification-text">
                            <spring:message code="label.message.save.success"/>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${ps.status == 'sendingError'}">
                    <tr>
                        <td class="error-text">
                            <spring:message code="label.message.send.error"/>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>

        </div>

        <div class="content-title-container">
            <h2><spring:message code="label.new"/></h2>
        </div>

        <div class="message-header">
            <div class="grid">
                <c:if test="${retentionMetadata != null && (retentionMetadata.retentionExpired || retentionMetadata.retentionWarning)}">
                    <c:if test="${retentionMetadata.retentionExpired}">
                        <span class="grid-row">
                            <span class="grid-cell header-label"><img src='<c:url value ="/images/img/blocking_icon.png" />' style="vertical-align: middle" width="32" height="32" /></span>
                            <span class="grid-cell header-value value-container"><spring:message code="label.retention.expired" arguments="${retentionPolicyWeeks}" htmlEscape="true"/></span>
                        </span>
                    </c:if>
                    <c:if test="${retentionMetadata.retentionWarning}">
                        <span class="grid-row">
                            <span class="grid-cell header-label"><img src='<c:url value ="/images/img/warning_icon.png" />' style="vertical-align: middle" width="32" height="32" /></span>
                            <fmt:formatDate value="${retentionMetadata.expiredOn.time}" pattern="dd/MM/yyyy" var="expiredOn"/>
                            <span class="grid-cell header-value value-container"><spring:message code="label.retention.warning" arguments="${expiredOn}" htmlEscape="true"/></span>
                        </span>
                    </c:if>
                </c:if>
                <span class="grid-row">
                    <span class="grid-cell header-label"><spring:message code="label.to"/>:</span>
                    <span id="recipient-displayName" class="grid-cell header-value value-container">
                        <spring:message text="${formBean.message.remoteParty != null ? formBean.message.remoteParty.displayName : remoteParties[0].displayName}" htmlEscape="true"/>
                    </span>
                    <form:hidden id="recipient" path="message.remoteParty.nodeName" value="${remoteParties[0].nodeName}"/>
                    <c:if test="${remoteParties.size() > 1}">
                        <div id="recipient-select-container" class="hidden">
                            <span class="grid-cell mandatory-value-container">
                                <form:select id="recipient-select" path="message.remoteParty.nodeName">
                                    <%--<form:option value="" label=""/>--%>
                                    <form:options items="${remoteParties}" itemValue="nodeName" itemLabel="displayName" />
                                </form:select>
                            </span>
                        </div>
                    </c:if>
                </span>
                <span class="grid-row">
                    <span class="grid-cell header-label"><spring:message code="label.subject"/>:</span>
                    <span class="grid-cell mandatory-value-container">
                        <form:input type="text" id="subject" class="message-subject" path="message.subject" maxlength="250"/>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell header-label"></span>
                    <span class="grid-cell value-container">
                        <div style="display: inline; float: right; font-size: small">250 max</div>
                    </span>
                </span>
                <span class="grid-row">
                    <span class="grid-cell header-label"><spring:message code="label.signature"/>:</span>
                    <span class="grid-cell value-container">
                        <input id="sing-message-chk" type="checkbox"  />
                        <label for="sing-message-chk"><spring:message code="label.sign.message"/></label>
                    </span>
                </span>
            </div>

            <jsp:include page="./common/p12input.jsp"/>
        </div>

        <tiles:insertAttribute name="messageEdit"/>

        <jsp:include page="./common/message/messageEditButtons.jsp">
            <jsp:param name="idx" value="1"/>
        </jsp:include>

        <form:hidden path="message.localParty"/>
        <form:hidden path="message.id"/>


        <form:hidden path="xmlSignedBundle"/>
        <form:hidden path="xmlExtractedSignature"/>
        <form:hidden path="xmlDataToSign"/>

        <form:hidden path="encodedIdSelectedPayloadFile" value="${encodedIdSelectedPayloadFile}"/>
        <form:hidden path="encodedIcaDetails"/>
        <form:hidden path="messageMetadata"/>
        <form:hidden path="fileIdsListJSON" value="${fileIdsListJSON}"/>
        <form:hidden path="isEncryptionRequired" value="${isEncryptionRequired}"/>

        <input type="hidden" id="userId" value="${user.login}"/>
        <input type="hidden" id="encodedFileElements" value="${encodedFileElements}"/>

    </div>
    <!-- end #mainContent -->
</form:form>
