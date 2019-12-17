<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<input type="hidden" id="isPartyAdmin" value="${isPartyAdmin}"/>

<p id="party-details-tips" class="validateTips"></p>

<div class="form-inline">
    <span class="header-label"><spring:message code="label.admin.manageParty"/></span>
    <span class="row-element">
        <select id="party">
            <option value=""></option>
            <%-- populated via JS --%>
        </select>
    </span>
    <span class="row-element-bus-lbl" id="businessLabel"></span>
</div>


<br/>
<br/>
<div>
    <p id="party-tips"></p>
    <span class="header-label"><spring:message code="label.admin.sendNotificationTo"/></span>
</div>

<div>
    <div class="grid" id="pdetails">
        <span class="grid-row">
            <span class="grid-cell">
                <span class="header-label-sts"><spring:message code="label.admin.bundle.notifications"/></span>
            </span>
            <span class="grid-cell">
                <input type="checkbox" id="pnotifications"
                       onclick="togglePartyNotificationCheckbox(this, 'party-email-container');"/>
            </span>
            <div class="grid-cell">
                <div id="party-email-container" class="value-container">
                    <textarea id="pemail" class="text-value-notification" rows="5" cols="50" maxlength="1000"></textarea>
                    <img src="<c:url value ="/images/icons/information.png"/>" class="info-icon" width="16" height="16" title='<spring:message code="admin.messageNotificationToolTip.text" htmlEscape="true"/>'/>
                </div>
            </div>
        </span>

        <span id="span-psnotifications" class="grid-row">
            <span class="grid-cell">
                <span class="header-label-sts"><spring:message code="label.admin.status.notifications"/></span>
            </span>
            <span class="grid-cell">
                <input type="checkbox" id="psnotifications"
                       onclick="togglePartyNotificationCheckbox(this, 'party-semail-container');"/>
            </span>
            <span class="grid-cell">
                <span id="party-semail-container" class="value-container">
                    <input type="text" id="psemail" class="text-value-notification" size="50" maxlength="64"/>
                </span>
            </span>
        </span>

        <span class="grid-row">
            <span class="grid-cell"></span>
            <span class="grid-cell"></span>
            <span class="grid-cell-l">
              <img src="<c:url value="/images/img/icon_save.png"/>" width="24" height="23" align="absmiddle"
                   alt="Save" class="grid-cell-img"/>
              <a href="#" id="save-party-details" class="text-value"><spring:message code="label.save"/></a>
            </span>
        </span>

        <span class="grid-row">
            <span class="grid-cell"></span>
            <span class="grid-cell"></span>
            <span class="grid-cell-l">
              <a href="#" id="add-admin" class="add-user"><spring:message code="label.admin.addUser"/></a>
            </span>
        </span>
    </div>
</div>

<br/>
<br/>
<div>
    <div>
        <span class="header-label"><spring:message code="label.admin.administrators"/></span>

        <div class="userrole-grid">
            <span class="userrole-grid-row">
                <span class="userrole-grid-cell text-header nosnotification"><spring:message code="label.admin.name"/></span>
                <span class="userrole-grid-cell text-header-login nosnotification"><spring:message code="label.admin.login"/></span>
                <span class="userrole-grid-email-cell-header nosnotification"><spring:message code="label.admin.bundle.notifications"/></span>
                <span id="span-admin-sts-notif" class="userrole-grid-email-cell-header"><spring:message code="label.admin.status.notifications"/></span>
                <span class="userrole-grid-cell control"></span>
            </span>
            <%-- populated via JS --%>
        </div>
        <div class="userrole-grid" id="PARTY_ADMIN_TABLE">
        </div>
    </div>

    <br/>
    <div>
        <span class="header-label"><spring:message code="label.admin.partyOperators"/></span>

        <div class="userrole-grid">
            <span class="userrole-grid-row">
                <span class="userrole-grid-cell text-header nosnotification"><spring:message code="label.admin.name"/></span>
                <span class="userrole-grid-cell text-header-login nosnotification"><spring:message code="label.admin.login"/></span>
                <span class="userrole-grid-email-cell-header nosnotification"><spring:message code="label.admin.bundle.notifications"/></span>
                <span id="span-oper-sts-notif" class="userrole-grid-email-cell-header"><spring:message code="label.admin.status.notifications"/></span>
                <span class="userrole-grid-cell control"></span>
            </span>
            <%-- populated via JS --%>
        </div>
        <div class="userrole-grid" id="OPERATOR_TABLE">
        </div>
    </div>
</div>
