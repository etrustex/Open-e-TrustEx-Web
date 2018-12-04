<%@ page isErrorPage="false" import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%--
	isErrorPage="true" won't work for some reason, event though this
 	is an exception handling JSP page
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<table>
    <tr>
        <td><img border="0" src="<c:url value="/images/error/500.gif" />"/></td>
        <td>
            <p>
                URL: <b><spring:message text="${badUrl}" htmlEscape="true"/></b>
            </p>

            <p>
                The web page you tried to access cannot be shown.<br/>
                There may be errors in the code or external conditions that prevent the page from being
                displayed.<br/>
                If this problem persists, please contact your administrator with a detailed description of your
                actions that brought you to this page.
                <%
                    Throwable throwable = (Throwable) pageContext.findAttribute("exception");
                    if (throwable != null) {
                        out.println("<!--");
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        throwable.printStackTrace(pw);
                        out.print(sw);
                        sw.close();
                        pw.close();
                        out.println("-->");
                    }
                %>
            </p>
        </td>
    </tr>
</table>
