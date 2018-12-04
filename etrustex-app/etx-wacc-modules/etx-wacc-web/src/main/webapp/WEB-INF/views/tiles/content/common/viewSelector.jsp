<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${viewCount > 1}">
    <form:form id="changeViewForm" method="post" commandName="changeViewFormBean" action="${action}" cssStyle="display:inline">
        <span class="view-selector">
            <form:radiobutton id='generic' path="view" value="generic" align="absmiddle"/>
            <spring:message code="label.generic"/>

            <form:radiobutton id='custom' path="view" value="custom" align="absmiddle"/>
            <c:choose>
                <c:when test="${not empty customViewLabel}">
                    <spring:message code="${customViewLabel}"/>
                </c:when>
                <c:otherwise>
                    <spring:message code="label.custom"/>
                </c:otherwise>
            </c:choose>
        </span>
        <%@ include file="./pageStateInFormChange.jsp" %>
    </form:form>
</c:if>
