<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="environment">wildfly</c:set>
<c:choose>
    <c:when test="${environment eq 'prod'}">
        <c:set var="environment"></c:set>
    </c:when>
    <c:otherwise>
        <c:set var="environment">-${fn:toUpperCase(environment)}</c:set>
    </c:otherwise>
</c:choose>
<div class="footer">
    <p><b>eTrustEx vtrunk${environment} - 19-04-2018 12:23</b></p>
</div>