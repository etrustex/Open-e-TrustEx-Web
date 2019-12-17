<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!--Sort-->
<div class="sort-header">
    <span class="${ps.unreadOnly == true ? 'sort-unselected' : 'sort-selected'}">
        <a href="<c:url value="/drafts.do?u=false${ps.listWoSortAndUnreadUrl}"/>">
            <spring:message code="label.all"/>
        </a>
    </span>
    <img src="<c:url value="/images/img/separator.png"/>" width="24" height="23" align="absmiddle" alt="Unread Messages"/>
    <span class="${ps.unreadOnly == true ? 'sort-selected' : 'sort-unselected'}">
        <a href="<spring:url value="/drafts.do?u=true${ps.listWoSortAndUnreadUrl}" htmlEscape="true"/>">
            <spring:message code="label.unread"/>
        </a>
    </span>
    <span class="right">
        <a href="<spring:url value="/drafts.do?sd=${ps.oppositeSortDirection}${ps.listWoSortUrl}" htmlEscape="true"/>">
            <spring:message code="label.saved"/>
        </a>
        <span class="delete-header"><spring:message code="label.deleteDraft"/></span>
    </span>
</div>