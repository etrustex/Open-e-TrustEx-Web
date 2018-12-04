<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<table>
    <tr>
        <td><img border="0" src="<c:url value="/images/error/403.gif" />"/></td>
        <td>
            <c:if test="${badUrl != null}">
                <p>
                    URL: <b><spring:message text="${badUrl}" htmlEscape="true"/></b>
                </p>
            </c:if>
            <p>
                You are not authorized to view this page.<br/>
                This error occurs if you attempt to access a page for which you do not have sufficient
                privileges.
            </p>
        </td>
    </tr>
</table>
