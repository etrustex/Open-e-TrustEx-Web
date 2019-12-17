<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="numOfMessages" value="${messages}" />
<c:if  test="${fn:length(messages) > 0}">
<div class="content-bottom">
    <span class="pager right">
        <c:choose>
            <c:when test="${currentPage == 1}">
                <img src="<c:url value="/images/img/icon_first-unselect.png"/>" border="0" width="8" height="22" align="absmiddle" alt="first page"/>
            </c:when>
            <c:otherwise>
                <a href="<spring:url value="/${action}?p=${firstPage}${ps.listWoPageUrl}" htmlEscape="true"/>">
                    <img src="<c:url value="/images/img/icon_first-select.png"/>" border="0" width="8" height="22" align="absmiddle" alt="first page"/>
                </a>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${currentPage == 1}">
                <img src="<c:url value="/images/img/icon_previous-unselect.png"/>" border="0" width="8" height="22" align="absmiddle" alt="previous page"/>
            </c:when>
            <c:otherwise>
                <a href="<spring:url value="/${action}?p=${prevPage}${ps.listWoPageUrl}" htmlEscape="true"/>">
                    <img src="<c:url value="/images/img/icon_previous-select.png"/>" border="0" width="8"	height="22" align="absmiddle" alt="previous page"/>
                </a>
            </c:otherwise>
        </c:choose>

        ${currentPage}&nbsp;<spring:message code="label.of"/>&nbsp;${lastPage}


        <c:choose>
            <c:when test="${currentPage == lastPage}">
                <img src="<c:url value="/images/img/icon_next-unselect.png"/>" border="0" width="8" height="22" align="absmiddle" alt="next page" class="no_padding"  />
            </c:when>
            <c:otherwise>
                <a href="<spring:url value="/${action}?p=${nextPage}${ps.listWoPageUrl}" htmlEscape="true"/>">
                    <img src="<c:url value="/images/img/icon_next-select.png"/>" border="0" width="8" height="22" align="absmiddle" alt="next page"  class="no_padding"/>
                </a>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${currentPage == lastPage}">
                <img src="<c:url value="/images/img/icon_last-unselect.png"/>" border="0" width="8" height="22" align="absmiddle" alt="last page"  class="no_padding"/>
            </c:when>
            <c:otherwise>
                <a href="<spring:url value="/${action}?p=${lastPage}${ps.listWoPageUrl}" htmlEscape="true"/>">
                    <img src="<c:url value="/images/img/icon_last-select.png"/>" border="0" width="8" height="22" align="absmiddle" alt="last page"  class="no_padding"/>
                </a>
            </c:otherwise>
        </c:choose>
    </span>
</div>
</c:if>

