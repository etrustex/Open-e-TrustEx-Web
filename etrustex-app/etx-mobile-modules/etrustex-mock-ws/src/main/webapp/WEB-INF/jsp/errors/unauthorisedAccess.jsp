<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" session="false" %>

<% response.setStatus(HttpServletResponse.SC_FORBIDDEN); %>

<html>
<head>
    <title><%=HttpServletResponse.SC_FORBIDDEN%> - NOT AUTHORIZED</title>
</head>

<body>
<div>
    <h5>You are <span style="color: red;">NOT</span> authorized to access this resource!</h5>
</div>
</body>
</html>