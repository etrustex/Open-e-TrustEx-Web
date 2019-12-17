<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="sort-header">
    <form:form id="messageStatusForm" method="post" commandName="formBean" action="statusFilter.do">
        <div class="form-inline">
            <span class="status">
                <span>
                    <span style="padding-right:8px"><spring:message code="label.status" text="Status"/></span>
                    <form:select path="status">
                        <form:options items="${statuses}"/>
                    </form:select>
                    <%@ include file="../pageStateInFormChange.jsp" %>
                </span>
                <span class="style-sent">
                    <spring:message code="label.sent"/>
                </span>
            </span>
        </div>
    </form:form>
</div>
