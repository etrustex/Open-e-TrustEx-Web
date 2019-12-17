<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>

<script>
    var pingUrl = url('/ping.do');
    var partyUrl = url('/inbox.do?${ps.partyUrl}');


</script>

<div class="content">
    <%@ include file="./common/message/backToList.jsp" %>

    <c:if test="${businessName == 'edma' && ps.backAction == 'outbox.do'}">
        <div class="content-controls">
            <img src="<c:url value="/images/img/icon_send.png"/>" width="24" height="23" align="absmiddle" alt="Send"/>
            <a id="openAsNeewBtn" href="<c:url value="/messageCreate.do?${ps.createMessageUrl}&mid=${msg.id}"/>">
                <spring:message code="label.openAsNew"/>
            </a>
        </div>
    </c:if>

    <div class="content-title-container">
        <tilesx:useAttribute name="titleLabel"/>
        <h2><spring:message code="${titleLabel}"/></h2>
        <%@ include file="./common/viewSelector.jsp" %>
    </div>

    <div>
        <c:if test="${!empty errorOccurred}">
            <span class="error-text">
                <spring:message code="${errorOccurred}" htmlEscape="true"/>
            </span>
        </c:if>
        <c:if test="${!empty notReady}">
            <span class="notification-text">
                <spring:message code="${notReady}" htmlEscape="true"/>
                <a href="javascript:location.reload(true);"><spring:message code="label.refresh"/></a>
            </span>
        </c:if>
    </div>

    <tiles:insertAttribute name="messageHeader" />
    <tiles:insertAttribute name="messageView" />

    <%@ include file="./common/message/backToList.jsp" %>
</div>