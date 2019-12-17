<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<input type="hidden" id="isSystemAdmin" value="${isSystemAdmin}"/>

<div class="row-element-business-system">
    <a href="#" id="add-system-admin" class="add-user"><spring:message code="label.admin.addUser"/></a>
</div>
<br/>
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
    <div class="userrole-grid" id="SYSTEM_ADMIN_TABLE"></div>
</div>


<br/><br/>

<div>
    <div class="row-element">
        <a href="#" id="show-ica" class="add-user"><spring:message code="label.admin.showIca" /></a>
    </div>
    <div class="row-element" style="padding-top:5px">
        <a href="#" id="reload-ica" class="add-user"><spring:message code="label.admin.reloadIca" /></a>
        <div id='loader' style='display:none'>
            <img src="<c:url value="/images/loader.gif"/>" />
        </div>
    </div>
    <div id="resetCacheDiv" class="row-element" style="padding-top:5px">
        <a href="#" id="reset-cache" class="add-user"><spring:message code="label.admin.resetCache" /></a>
    </div>

    <div id="ICA-INFO">
        <div id="ICA_TABLE" class="userrole-grid">
                <span class="userrole-grid-row-header">
                    <span class="userrole-grid-cell text-header-party"><spring:message
                            code="label.admin.webParty"/></span>
                    <span class="userrole-grid-cell text-header-party"><spring:message
                            code="label.admin.icaParty"/></span>
                    <span class="userrole-grid-cell text-header"><spring:message code="label.admin.integrityCode" /></span>
                    <span class="userrole-grid-cell text-header"><spring:message code="label.admin.confidentialityCode" /></span>
                    <span class="userrole-grid-cell text-header"><spring:message code="label.admin.lastDateReloaded" /></span>
                    <span class="userrole-grid-cell text-header"><%--placeholder for the reload action--%></span>
                </span>
            <%-- populated via JS --%>
        </div>
    </div>
</div>
