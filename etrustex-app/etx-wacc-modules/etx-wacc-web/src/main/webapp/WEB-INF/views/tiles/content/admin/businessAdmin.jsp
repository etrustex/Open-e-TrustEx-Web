<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<p id="party-name-tips" class="validateTips"></p>

<div class="form-inline">

<span class="header-label"><spring:message code="label.admin.manageBusiness"/></span>
<c:choose>
    <c:when test="${fn:length(businesses) == 1}">
        <c:forEach items="${businesses}" var="business">
            <span class="header-value row-element">${business.name}</span>
            <input type="hidden" id="business" value="${business.id}"/>
        </c:forEach>
    </c:when>
    <c:otherwise>
            <span class="row-element">
                <select id="business" class="info-input">
                    <option value=""></option>
                    <c:forEach items="${businesses}" var="entry">
                        <option value="${entry.id}">${entry.name}</option>
                    </c:forEach>
                </select>
            </span>
    </c:otherwise>
</c:choose>
    <span class="row-element">
        <button type="button" id="exportPartyUsers" class="btn btn-info btn-outline btn-xs">
            <i class="glyphicon glyphicon-export"></i>
            <spring:message code="label.admin.export.ParyUsers"/>
        </button>
    </span>
    <%--<a id="exportPartyUsers"> Export Party Users</a>--%>
</div>

<br/>

<div class="row-element-business-system">
    <a href="#" id="add-business-admin" class="add-user"><spring:message code="label.admin.addUser"/></a>
</div>

<br/>
<br/>
<div>
    <div>
        <span class="header-label"><spring:message code="label.admin.administrators"/></span>

        <div class="userrole-grid">
            <span class="userrole-grid-row">
                <span class="userrole-grid-cell text-header-business-system"><spring:message code="label.admin.name"/></span>
                <span class="userrole-grid-cell text-header-business-system"><spring:message code="label.admin.login"/></span>
                <span class="userrole-grid-cell control"></span>
            </span>
            <%-- populated via JS --%>
        </div>
        <div class="userrole-grid" id="BUSINESS_ADMIN_TABLE">
        </div>
    </div>

    <br/>
    <div>
        <span class="header-label"><spring:message code="label.admin.parties"/></span>

        <div id="BUSS_PARTY_TREE">
            <div class="tree">
                <ul id="treeList" class="partyIcaList">
                    <%-- populated via JS --%>
                </ul>
            </div>
        </div>
    </div>
</div>