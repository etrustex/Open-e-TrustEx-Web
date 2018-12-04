<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="cname" value="etrustex.${user.login}.cookie.info.shown"/>

<script>
    var cname = "${cname}";
</script>

<c:if test="${cookie[cname].value != 'true'}">
    <div id="cbanner">
        <spring:message code="message.cookie.banner" htmlEscape="true" javaScriptEscape="true"/>
        <button id="cbtn">OK</button>
    </div>
</c:if>