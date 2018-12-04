<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="message-details">
    <div class="message-details-block">
        <h3><spring:message code="label.message"/></h3>
        <div class="content-text-area">
            <span><spring:message text="${msg.content}" htmlEscape="true"/></span>
        </div>
    </div>

    <c:if test="${fn:length(attachments) > 0}">
        <div class="message-details-block">
            <h3><spring:message code="label.officialVersion"/></h3>

            <div class="insert">
                <%@ include file="./eGreffe_metadata.jsp" %>
            </div>
        </div>

        <div class="message-details-block">
            <tiles:insertAttribute name="attachmentList" />
        </div>
    </c:if>
</div>
