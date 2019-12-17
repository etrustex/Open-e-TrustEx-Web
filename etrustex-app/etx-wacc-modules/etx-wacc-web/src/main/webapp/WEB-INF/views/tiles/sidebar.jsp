<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="sidebar">
	<div class="sidebar-title-container">
		<h1><spring:message code="label.allFolders" /></h1>
	</div>
	<ul>
		<li>
            <img src="<c:url value="/images/img/icon_inbox.png"/>" alt="Inbox" width="40" height="35" align="absmiddle" border="0" />
            <a id="inboxUrl" href="<c:url value="/inbox.do?${ps.partyUrl}"/>">
            <c:choose>
                <c:when test="${unreadCountIncoming > '0'}">
                    <b><spring:message code="label.inbox" />(${unreadCountIncoming})</b>
                </c:when>
                <c:otherwise>
                    <spring:message code="label.inbox"/>
                </c:otherwise>
            </c:choose>
            </a>
		</li>
		<c:if test="${sendAbility == '1'}">
			<li>
				<img src="<c:url value="/images/img/icon_sent.png"/>" width="40" height="35" alt="Sent" align="absmiddle" border="0" />
				<a href="<c:url value="/outbox.do?${ps.partyUrl}"/>"><spring:message code="label.sent" /></a>
			</li>
			<li>
				<img src="<c:url value="/images/img/icon_draft.png"/>" width="40" height="35" alt="Draft" align="absmiddle" border="0" />
				<a href="<c:url value="/drafts.do?${ps.partyUrl}"/>">
                    <c:choose>
                        <c:when test="${unreadCountDraft > '0'}">
                            <b><spring:message code="label.draft" />(${unreadCountDraft})</b>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="label.draft" />
                        </c:otherwise>
                    </c:choose>
				</a>
			</li>
		</c:if>
	</ul>
</div>