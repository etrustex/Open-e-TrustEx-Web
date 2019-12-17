<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<table>
    <tr>
        <td><img border="0" src="<c:url value="/images/error/404.gif" />"/></td>
        <td>
            <p>
                URL: <b><spring:message text="${badUrl}" htmlEscape="true"/></b>
            </p>

            <p>
                The web page you tried to access cannot be found.<br/>
                You may have misspelled the address in the browser or the application uses a broken
                hyperlink.<br/>
                If this problem persists, please contact your administrator with a detailed description of your
                actions that brought you to this page.
            </p>
        </td>
    </tr>
</table>
