<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="message-details">

    <div class="message-details-block">
        <h3><spring:message code="label.comments"/></h3>
        <div class="content-text-area"><form:textarea path="message.content"/></div>
    </div>

    <div class="message-details-block">
        <tiles:insertAttribute name="attachmentList" />
    </div>
</div>
