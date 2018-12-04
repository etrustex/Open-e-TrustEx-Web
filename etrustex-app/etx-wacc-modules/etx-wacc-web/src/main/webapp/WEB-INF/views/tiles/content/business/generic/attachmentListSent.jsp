<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(attachments) > 0}">
    <h3><spring:message code="label.listOfFiles"/></h3>

    <div class="left">
        <img src="<c:url value="/images/img/icon_upload.png"/>" alt="Download files" width="45" height="45"/>
    </div>
    <div class="file-list-header">
            <h6><spring:message code="label.uploaded"/> (${fn:length(attachments)})</h6>
    </div>
    <div class="file-list">
            <c:forEach items="${attachments}" var="item" varStatus="iStatus">
                <ul>
                    <li>
                    	<spring:message text="${item.filePath}${item.fileName}" htmlEscape="true"/>
                    </li>
                </ul>
            </c:forEach>
    </div>
</c:if>