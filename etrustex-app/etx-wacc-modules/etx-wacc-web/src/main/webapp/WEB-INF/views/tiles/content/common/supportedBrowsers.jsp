<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: brighab
  Date: 07/05/2018
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>

<div class="compatible-browsers-content">
    <h3><spring:message code="message.compatible.browsers.title1"/> </h3>
    <br>
    <h3>
        <p><spring:message code="message.compatible.browsers.title2"/> </p>
    </h3>
    <br>
    <br>
    <table id="compatible-browsers-table" class="compatible-version-table">
            <tr>
               <th><spring:message code="message.compatible.browsers.name"/> </th>
               <th><spring:message code="message.compatible.browsers.version"/> </th>
               <th><spring:message code="message.compatible.browsers.comment"/> </th>
            </tr>
        <tbody>

        </tbody>
    </table>
</div>