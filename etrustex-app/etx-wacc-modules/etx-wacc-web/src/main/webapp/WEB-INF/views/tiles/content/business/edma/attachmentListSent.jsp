<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<c:if test="${!fromSentIsEncryptionRequired}">

    <script>
      function isRetentionExpired() {
        return document.getElementById('retentionExpired').value == 'true'
      }
      $(document).ready(()=> {
        if(isRetentionExpired()) {
          let expiredDocsWarning = document.getElementById('downloadFromSentRetentionExpiredNotice')
          expiredDocsWarning.style.display = 'inline'
          expiredDocsWarning.textContent = '<spring:message code="label.retention.expired" arguments="${retentionPolicyWeeks}" htmlEscape="true"/>'

          document.getElementById('downloadSentFiles').disabled = true
        }
      })
    </script>

    <div style="display: none">
        <jsp:include page="../../common/message/download/attachmentList.jsp"/>
    </div>
    <input type="hidden" id="downloadingFromSent" value="true"/>
    <input type="hidden" id="retentionExpired" value="${retentionMetadata.retentionExpired}"/>
    <input type="hidden" id="retentionWarning" value="${retentionMetadata.retentionWarning}"/>
</c:if>

<c:if test="${fn:length(attachmentMetadataList) > 0}">
    <c:if test="${!fromSentIsEncryptionRequired}">
        <div>
            <button type="button" id="downloadSentFiles" class="btn btn-primary btn-outline btn-sm" onclick="document.getElementsByName('downloadSelectedFiles')[0].click()">
                <spring:message code="label.webstart.openDownloadWebstart"/></button>
            <span class="grid-cell header-value value-container" style='display: none' id="downloadFromSentRetentionExpiredNotice"></span>
        </div>
    </c:if>

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
    <%--<script>--%>
    <%--function startDownloadFromSent() {--%>
    <%--console.log("Staring download from sent!")--%>
    <%--document.getElementsByName('downloadSelectedFiles')[0].click()--%>
    <%--}--%>
    <%--</script>--%>
</c:if>

