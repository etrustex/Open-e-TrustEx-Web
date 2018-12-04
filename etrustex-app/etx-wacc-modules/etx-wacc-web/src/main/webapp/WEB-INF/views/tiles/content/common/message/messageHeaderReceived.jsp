<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="message-header">
    <div class="grid">
        <c:if test="${retentionMetadata.retentionExpired || retentionMetadata.retentionWarning}">
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
            <span class="grid-cell header-label"><spring:message code="label.subject"/>:</span>
            <span class="grid-cell header-value value-container"><spring:message text=" ${msg.subject}" htmlEscape="true"/></span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.from"/>:</span>
            <span class="grid-cell header-value value-container"><spring:message text="${msg.remoteParty.displayName}" htmlEscape="true"/></span>
        </span>
    </div>
    <div class="date-info">
        <span>
            <spring:message code="label.receivedOn"/>:
            <fmt:formatDate value="${msg.createdOn}" pattern="EEEE, MMMM d yyyy HH:mm"/>
        </span>
    </div>
</div>