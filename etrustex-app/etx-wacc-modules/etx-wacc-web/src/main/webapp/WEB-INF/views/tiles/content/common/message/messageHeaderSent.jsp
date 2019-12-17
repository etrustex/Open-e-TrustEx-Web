<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="message-header">
    <div class="grid">
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.to"/>:</span>
            <span class="grid-cell">
                <span class="grid-row">
                    <span class="grid-cell header-value value-container">
                        <spring:message text="${msg.remoteParty.displayName}" htmlEscape="true"/>
                    </span>
                </span>
            </span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.subject"/>:</span>
            <span class="grid-cell">
                <span class="grid-row">
                    <span class="grid-cell header-value value-container">
                        <spring:message text=" ${msg.subject}" htmlEscape="true"/>
                    </span>
                </span>
            </span>
        </span>
        <c:if test="${not empty statusCode}">
            <span class="grid-row">
                <span class="grid-cell header-label"><spring:message code="label.status"/>:</span>
                <span class="grid-cell">
                    <span class="grid-row">
                        <span class="grid-cell header-value value-container">
                            <spring:message code="${statusCode}" htmlEscape="true"/>
                        </span>
                        <span class="grid-cell header-label value-container"><spring:message code="label.status.receivedOn"/>:</span>
                        <span class="grid-cell header-value value-container">
                            <fmt:formatDate value="${statusDate}" pattern="EEEE, MMMM d yyyy HH:mm"/>
                        </span>
                    </span>
                </span>
            </span>
        </c:if>
    </div>
    <div class="date-info">
        <span>
            <spring:message code="label.message.sentOn"/>:
            <fmt:formatDate value="${msg.issueDate}" pattern="EEEE, MMMM d yyyy HH:mm"/>
        </span>
    </div>
</div>