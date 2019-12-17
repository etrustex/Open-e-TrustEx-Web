<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" session="false" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Login Error</title>
</head>

<body>
<div>
    <h2>An error occurred during the authentication process.</h2>
    <%
        LoggerFactory.getLogger("eu.europa.ec.digit.cotg.aggregator.ECAS")
                .error("ECAS authentication error", exception);
    %>
</div>
</body>
</html>
