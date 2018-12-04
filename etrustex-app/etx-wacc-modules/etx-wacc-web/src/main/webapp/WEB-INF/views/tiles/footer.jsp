<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="environment">@etrustex.environment@</c:set>
<c:choose>
    <c:when test="${environment eq 'prod'}">
        <c:set var="environment"></c:set>
    </c:when>
    <c:otherwise>
        <c:set var="environment">-${fn:toUpperCase(environment)}</c:set>
    </c:otherwise>
</c:choose>
<div class="footer">
    <p><b>eTrustEx v@eTrustEx.version@${environment} - @eTrustEx.timestamp@</b></p>
</div>