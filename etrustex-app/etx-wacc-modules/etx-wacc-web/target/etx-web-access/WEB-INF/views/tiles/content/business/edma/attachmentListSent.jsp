<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${fn:length(attachmentMetadataList) > 0}">
    <h3><spring:message code="label.listOfFiles"/></h3>

    <div class="left">
        <img src="<c:url value="/images/img/icon_upload.png"/>" alt="Download files" width="45" height="45"/>
    </div>
    <div class="file-list-header">
        <h6><spring:message code="label.uploaded"/> (${fn:length(attachmentMetadataList)})</h6>
    </div>
    <div class="file-list">
        <div class="grid">
        <c:forEach items="${attachmentMetadataList}" var="item" varStatus="iStatus">
            <span class="grid-row">
                <span class="grid-cell"><spring:message text="${item.path}${item.filename}" htmlEscape="true"/></span>
                <c:choose>
                    <c:when test="${item.confidential}">
                        <span class="grid-cell" title="Confidential"><img src="<c:url value="/images/icons/confidential.png"/>" alt="Download files" width="13" height="13"/></span>
                    </c:when>
                    <c:otherwise>
                        <span class="grid-cell" title="Non confidential"><img src="<c:url value="/images/icons/non-confidential.png"/>" alt="Download files" width="13" height="13"/></span>
                    </c:otherwise>
                </c:choose>
                <c:if test="${!empty item.comment}">
                    <span class="grid-cell" title="${item.comment}">
                        <img src="<c:url value="/images/icons/comment.png"/>" alt="Download files" width="13" height="13"/>
                    </span>
                </c:if>
            </span>
        </c:forEach>
        </div>
    </div>
</c:if>

