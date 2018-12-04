<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>

<script>
    var pingUrl = url('/ping.do');
    var partyUrl = url('/inbox.do?${ps.partyUrl}');

    $(document).ready(function () {
      //Code for the radio buttons form submission - ETX-840
      $('#generic').click(function(){
        $('#changeViewForm').submit();
      });


      $('#custom').click(function(){
        $('#changeViewForm').submit();
      });
    });
</script>

<div class="content">
    <%@ include file="./common/message/backToList.jsp" %>

    <div class="content-title-container">
        <tilesx:useAttribute name="titleLabel"/>
        <h2><spring:message code="${titleLabel}"/></h2>
        <%@ include file="./common/viewSelector.jsp" %>
    </div>

    <div>
        <c:if test="${!empty errorOccurred}">
            <span class="error-text">
                <spring:message code="${errorOccurred}" htmlEscape="true"/>
            </span>
        </c:if>
        <c:if test="${!empty notReady}">
            <span class="notification-text">
                <spring:message code="${notReady}" htmlEscape="true"/>
                <a href="javascript:location.reload(true);"><spring:message code="label.refresh"/></a>
            </span>
        </c:if>
    </div>

    <tiles:insertAttribute name="messageHeader" />
    <tiles:insertAttribute name="messageView" />

    <%@ include file="./common/message/backToList.jsp" %>
</div>