<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="retentionExpiredMessage">
    <spring:message code="label.retention.expired" arguments="${retentionPolicyWeeks}" htmlEscape="true"/>
</c:set>
<link href="<c:url value="/css/fileupload/jquery.fileupload.css?ver=${ver}"/>" rel="stylesheet" type="text/css"/>

<tilesx:useAttribute name="business"/>
<c:if test="${attachmentCount > 0}">
    <h3><spring:message code="label.listOfFiles"/></h3>
    <div class="certificate-panel">
        <div>
            <table id="errors-table">
                <tbody>
                    <tr>
                        <td class="error-text">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div id="p12-input-container" class="message-header" style="display: none">
            <h4><spring:message code="label.decryption.information"/></h4>
            <jsp:include page="../../common/p12input.jsp"/>
        </div>
    </div>

    <div class="left">
        <img src="<c:url value="/images/img/icon_download.png"/>" alt="Download files" width="45" height="45"/>
    </div>

    <%@ include file="./download/attachmentList.jsp" %>
</c:if>
