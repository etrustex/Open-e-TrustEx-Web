<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="retentionExpiredMessage">
    <spring:message code="label.retention.expired" arguments="${retentionPolicyWeeks}" htmlEscape="true"/>
</c:set>

<tilesx:useAttribute name="business"/>
<c:if test="${attachmentCount > 0}">
    <h3><spring:message code="label.listOfFiles"/></h3>

    <div class="left">
        <img src="<c:url value="/images/img/icon_download.png"/>" alt="Download files" width="45" height="45"/>
    </div>

    <!--WebStart download -->
    <%@ include file="./download/attachmentListWS.jsp" %>
</c:if>
