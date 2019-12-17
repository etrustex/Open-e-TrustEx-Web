<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${fn:length(messages) > 0}">
    <div class="business-egreffe-message-list">
        <c:forEach items="${messages}" var="dossier">
            <table>
                <tr class="dossier">
                    <td class="style-dossier">
                        <spring:message code="label.dossierName"/> <spring:message text="${dossier.key}" htmlEscape="true"/>
                    </td>
                </tr>
            </table>
            <table>
                <c:forEach items="${dossier.value}" var="msg" varStatus="idx">
                    <c:set var="style" scope="page" value="notRead"/>
                    <c:if test="${msg.readByCurrentUser}">
                        <c:set var="style" scope="page" value="read"/>
                    </c:if>
                    <tr class="${style}">
                        <c:choose>
                            <c:when test="${msg.hasIca}">
                                <c:choose>
                                    <c:when test="${msg.hasAttachments}">
                                        <td class="style-attachment">
                                            <img src="<c:url value="/images/img/icon_attachment.png"/>" width="25"
                                                 height="25" alt="Attachment"/>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="style-attachment">
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <td class="style-attachment">
                                    <img src="<c:url value ="/images/img/icon-broken-contract.png"/>" width="21" height="21" title='<spring:message code="error.ica.not.loaded" htmlEscape="true"/>'/>
                                </td>
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${showExpiredIcon}">
                            <c:if test="${msg.retentionMetadata.retentionExpired || msg.retentionMetadata.retentionWarning}">
                                <c:if test="${msg.retentionMetadata.retentionExpired}">
                                    <td class="style-attachment">
                                        <img src="<c:url value ="/images/img/blocking_icon.png"/>" width="20"
                                             height="20" title='<spring:message code="label.retention.expired" arguments="${retentionPolicyWeeks}" htmlEscape="true"/>'/>
                                    </td>
                                </c:if>
                                <c:if test="${msg.retentionMetadata.retentionWarning}">
                                    <td class="style-attachment">
                                        <fmt:formatDate value="${msg.retentionMetadata.expiredOn.time}" pattern="dd/MM/yyyy" var="expiredOn"/>
                                        <img src="<c:url value ="/images/img/warning_icon.png"/>" width="20"
                                             height="20" title='<spring:message code="label.retention.warning" arguments="${expiredOn}" htmlEscape="true"/>'/>
                                    </td>
                                </c:if>
                            </c:if>
                            <c:if test="${!(msg.retentionMetadata.retentionExpired || msg.retentionMetadata.retentionWarning)}">
                                <td class="style-attachment">
                                </td>
                            </c:if>
                        </c:if>
                        <c:if test="${!showExpiredIcon}">
                            <c:choose>
                                <c:when test="${msg.messageState == 'SENT' && (msg.statusCode == 'label.status.read' || msg.statusCode == 'label.status.delivered')}">
                                    <td class="style-attachment messageSubject"
                                        onclick='goToLink(url("/exportMessage.do?messageId=${msg.messageId}"))'>
                                        <img src="<c:url value ="/images/img/icon-pdf.png"/>" width="23" height="25"
                                             title='<spring:message code="message.export.pdf" htmlEscape="true"/>'/>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td class="style-attachment">
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:if>

                        <c:choose>
                            <c:when test="${msg.hasIca}">
                                <td class="style-subject messageSubject"
                                    onclick='goToLink(url("/${messageViewAction}?mid=${msg.messageId}${ps.toMessageUrl}"))'>
                                    <spring:message text="${msg.subject}" htmlEscape="true"/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td class="style-subject notIca">
                                    <spring:message text="${msg.subject}" htmlEscape="true"/>
                                </td>
                            </c:otherwise>
                        </c:choose>

                        <td class="style-date">
                            <fmt:formatDate value="${msg.createDate}" type="date"/>
                        </td>
                    </tr>
                    <tr class="${style}">
                        <td class="style-count">
                        </td>
                        <td class="style-count">
                        </td>
                        <c:set var="senderStyle" scope="page" value="style-sender messageSender"/>
                        <c:if test="${!msg.hasIca}">
                            <c:set var="senderStyle" scope="page" value="style-sender notIca"/>
                        </c:if>
                        <td class="${senderStyle}">
                                <spring:message text="${msg.senderName}" htmlEscape="true"/>
                        </td>

                        <td class="style-hour">
                            <fmt:formatDate value="${msg.createDate}" pattern="HH:mm"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
    </div>
</c:if>
