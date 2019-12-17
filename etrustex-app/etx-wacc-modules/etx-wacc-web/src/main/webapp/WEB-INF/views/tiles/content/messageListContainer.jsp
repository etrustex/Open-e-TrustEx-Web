<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
  $(document).ready(function () {
    $("#status" ).combobox();
    $("#status").on('change', function(){
      if($("#status").val() != null){
        $("#messageStatusForm").submit();
      }
    })
  });
</script>

<div class="content">
    <form:form id="messageListForm" method="post" commandName="formBean" action="${action}">
        <div class="content-controls">
            <c:if test="${sendAbility == '1'}">
                <c:choose>
                    <c:when test="${hasLinkedIcas == '0'}">
                   <span style="color:grey;" title="<spring:message code="error.ica.not.loaded"/>">
                        <img src="<c:url value="/images/img/icon_new_grey.png"/>" width="25" height="25" align="absmiddle"
                             alt="grayed out create new message" border="0"/>
                        <spring:message code="label.new"/>
                   </span>
                    </c:when>
                    <c:otherwise>
                        <img src="<c:url value="/images/img/icon_new.png"/>" width="25" height="25" align="absmiddle"
                             alt="create new message" border="0"/>
                        <a href="<c:url value="/messageCreate.do?${ps.createMessageUrl}"/>">
                            <spring:message code="label.new"/>
                        </a>
                    </c:otherwise>
                </c:choose>
                <img src="<c:url value="/images/img/separator.png"/>" width="24" height="23" align="absmiddle"
                     alt=" "/>
            </c:if>
            <img src="<c:url value="/images/img/icon_refresh.png"/>" width="25" height="25" align="absmiddle"
                 alt="Refresh messages" border="0"/>
            <a href="#" onclick="document.forms['messageListForm'].submit(); return false;">
                <spring:message code="label.refresh"/>
            </a>
            <img src="<c:url value="/images/img/separator.png"/>" width="24" height="23" align="absmiddle"
                 alt=" "/>
            <span><spring:message code="label.subject"/></span>
            <form:input type="text" class="info-input" path="subject" size="30"
                        maxlength="30" htmlEscape="true"/>
            <input name="Search" type="image" src="<c:url value="/images/img/icon_search.png"/>" width="25"
                   height="25" align="absmiddle" alt="Search"/>
            <%@ include file="./common/pageStateInForm.jsp" %>
        </div>
    </form:form>


    <div>
        <c:if test="${ps.notifySuccessfulOperation}">
            <span class="notification-text">
                <spring:message code="label.message.send.succes"/>
            </span>
        </c:if>
    </div>

    <div class="content-title-container">
        <tilesx:useAttribute name="titleLabel"/>
        <h2><spring:message code="${titleLabel}"/></h2>
        <%@ include file="./common/viewSelector.jsp" %>
    </div>

    <tiles:insertAttribute name="messageListHeader" />
    <tiles:insertAttribute name="messageList" />

    <%@ include file="./common/list/messageListPager.jsp" %>
</div>
