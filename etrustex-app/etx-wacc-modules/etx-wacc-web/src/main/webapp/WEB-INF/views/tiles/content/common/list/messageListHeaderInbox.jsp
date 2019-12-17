<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="sort-header">
    <span class="${ps.unreadOnly == true ? 'sort-unselected' : 'sort-selected'}">
        <a href="<c:url value="${action}?u=false${ps.listWoSortAndUnreadUrl}"/>">
            <spring:message code="label.all"/>
        </a>
    </span>
    <img src="<c:url value="/images/img/separator.png"/>" width="24" height="23" align="absmiddle" alt="Unread Messages"/>
    <span class="${ps.unreadOnly == true ? 'sort-selected' : 'sort-unselected'}">
        <a href="<spring:url value="${action}?u=true&${ps.listWoUnreadUrl}" htmlEscape="true"/>">
            <spring:message code="label.unread"/>
        </a>
    </span>
    <span class="right">
        <a href="<spring:url value="${action}?sd=${ps.oppositeSortDirection}${ps.listWoSortUrl}" htmlEscape="true"/>">
            <spring:message code="label.received"/>
        </a>
    </span>
</div>
