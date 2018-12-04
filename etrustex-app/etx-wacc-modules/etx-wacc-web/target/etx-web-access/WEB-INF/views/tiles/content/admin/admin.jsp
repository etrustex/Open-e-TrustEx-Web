<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="isPartyAdmin" value="${isPartyAdmin}" />
<c:set var="businessAdministration" value="${fn:length(businesses) > 0}" />
<c:set var="systemAdministration" value="${isSystemAdmin}" />
<script type="text/javascript" language="javascript"
        src="<c:url value="/js/lib/jquery.tree.js"/>">//Source: https://jquery.com/</script>
<link href="<c:url value="/css/jquery.tree.css"/>" rel="stylesheet" type="text/css"/>
<script>
    var deleteImgSrc = url('/images/icons/delete.png');
    var notDeleteImgSrc = url('/images/icons/not-delete.png');
    var certificateIconImgSrc = url('/images/icons/certificate.png');
    var refreshIconImgSrc = url('/images/icons/icon_refresh.png');
    var loaderImgSrc = url('/images/loader.gif');

    var userRoleRowTemplate =
            '<span id="UR-{0}" class="userrole-grid-row">' +
                '<span class="userrole-grid-cell text-value"><a id="UR-NAME-{0}" href="#" onClick="editPartyUserRole(\'{0}\', {6}); return false;">{1}</a></span>' +
                '<span class="userrole-grid-cell text-value-login" id="UR-LOGIN-{0}">{2}</span>' +
                '<span class="userrole-grid-email-cell-value">' +
                '<span><input type="checkbox" id="UR-NOTIFICATION-{0}" {4} disabled/></span>' +
                '<span id="UR-EMAIL-{0}">{3}</span>' +
            '</span>' +
            '<span class="userrole-grid-email-cell-value">' +
                '<span><input type="checkbox" id="UR-SNOTIFICATION-{0}" {8} disabled/></span>' +
                '<span id="UR-SEMAIL-{0}">{9}</span>' +
            '</span>' +
            '<span class="userrole-grid-cell {7}" onClick="deletePartyUserRole(\'{0}\', \'{2}\', \'{5}\', {6}); return false;" id="ACT-{0}"><a><img src="'+deleteImgSrc+'"/></a></span>' +
                '<span style="display:none;" id="UR-PARTY-ROLE-TYPE-{0}">{5}</span>' +
        '</span>';

    var userRoleRowTemplateWithoutSNotification =
            '<span id="UR-{0}" class="userrole-grid-row">' +
                '<span class="userrole-grid-cell text-value nosnotification"><a id="UR-NAME-{0}" href="#" onClick="editPartyUserRole(\'{0}\', {6}); return false;">{1}</a></span>' +
                '<span class="userrole-grid-cell text-value-login nosnotification" id="UR-LOGIN-{0}">{2}</span>' +
                '<span class="userrole-grid-email-cell-value nosnotification">' +
                '<span><input type="checkbox" id="UR-NOTIFICATION-{0}" {4} disabled/></span>' +
                '<span id="UR-EMAIL-{0}">{3}</span>' +
            '</span>' +
            '<span class="userrole-grid-cell {7}" onClick="deletePartyUserRole(\'{0}\', \'{2}\', \'{5}\', {6}); return false;" id="ACT-{0}"><a><img src="'+deleteImgSrc+'"/></a></span>' +
                '<span style="display:none;" id="UR-PARTY-ROLE-TYPE-{0}">{5}</span>' +
        '</span>';

    var userRoleRowTemplateBusiness =
        '<span id="URB-{0}" class="userrole-grid-row">'+
            '<span class="userrole-grid-cell text-value-business-system"><a id="URB-NAME-{0}" href="#" onClick="editUserRole(\'{0}\', \'{3}\'); return false;">{1}</a></span>'+
            '<span class="userrole-grid-cell text-value-business-system" id="URB-LOGIN-{0}">{2}</span>'+
            '<span class="userrole-grid-cell control" onClick="deleteUserRole(\'{0}\', \'{2}\', \'{3}\'); return false;"><a><img src="'+deleteImgSrc+'"/></a></span>'+
            '<span style="display:none;" id="URB-PARTY-ROLE-TYPE-{0}">{3}</span>'+
        '</span>';

    var userRoleRowTemplateSystem =
        '<span id="URS-{0}" class="userrole-grid-row">'+
            '<span class="userrole-grid-cell text-value-business-system"><a id="URS-NAME-{0}" href="#" onClick="editUserRole(\'{0}\', \'{3}\'); return false;">{1}</a></span>'+
            '<span class="userrole-grid-cell text-value-business-system" id="URS-LOGIN-{0}">{2}</span>'+
            '<span class="userrole-grid-cell control" onClick="deleteUserRole(\'{0}\', \'{2}\', \'{3}\'); return false;"><a><img src="'+deleteImgSrc+'"/></a></span>'+
            '<span style="display:none;" id="URS-PARTY-ROLE-TYPE-{0}">{3}</span>'+
        '</span>';


    var icaDetailsTemplate =
            '<span id="ICA-DETAIL-{0}" class="userrole-grid-row" onmouseover="$(this).addClass(\'highlight-ica\');" onmouseout="$(this).removeClass(\'highlight-ica\');">' +
                '<span class="userrole-grid-cell text-value-party" id="ICA-PART1-{0}">{1} <span class="inactive-ica" style="visibility:{14}">' + messages['label.admin.ica.inactive'] + '</span></span>'+
                '<span class="userrole-grid-cell text-value-party" id="ICA-PART2-{0}">'+
            '<a class="control" id="ICA-PART2-CERTIFICATE-{0}" style="visibility:{13}" onClick="showPartyCertificate(\'{6}\', \'{7}\', \'{8}\', \'{9}\', \'{10}\', \'{11}\', \'{12}\'); return false;">' +
                        '<img class="certificate-icon" height="16" align="left" alt="Certificate" src="'+certificateIconImgSrc+'"/>'+
                    '</a>'+
                    '<span id="ICA-PART2-TEXT-{0}">{2}</span>'+
                '</span>'+
                '<span class="userrole-grid-cell text-value" id="ICA-IC-{0}">{3}</span>'+
                '<span class="userrole-grid-cell text-value" id="ICA-CC-{0}">{4} </span>'+
                '<span class="userrole-grid-cell text-value" id="ICA-RL-{0}">{5}</span>'+
            '<span title="' + messages['label.refresh'] + '" class="userrole-grid-cell control">' +
            '<a id="ICA-RELOAD-{0}" onClick="reloadIcaDetailsSenderReceiver(\'{1}\', \'{2}\'); return false;"><img style="height:20px;" src="' + refreshIconImgSrc + '"/></a>' +
            '<img style="height:20px; display:none;" id="LOADER-{0}" src="' + loaderImgSrc + '" />' +
                '</span>'+
            '</span>';


    var icaTreeBranchTemplate_with_ICA =
        '<li class="parent_li">' +
        '<span>' +
        '<i class="glyphicon glyphicon-plus-sign"></i>' +
        '</span>' +
        '<a id= \'{2}\' href="#"  onClick="editDisplayName(\'{0}\', \'{1}\', \'{2}\');return false;">{4}</a>' +
        '<ul>' +
        '{3}' +
        '</ul>' +
        '</li>';

    var icaTreeBranchTemplate_without_ICA =
        '<li class="parent_li">' +
        '<a id= \'{2}\' href="#" style="padding-left:32px" onClick="editDisplayName(\'{0}\', \'{1}\', \'{2}\');return false;">{3}</a>' +
        '</li>';

    var icaTreeLeafTemplate =
            '<li style="display: none;">' +
            '<a class="leaf" href="#" onClick="editDisplayName(\'{0}\', \'{1}\', \'{2}\');return false;">{5}</a>' +
            ' ({3}; {4})' +
        '</li>';


    $(function () {
        $("#admin-tabs").tabs();
        $("#admin-tabs a").click(function () {
            var href = $(this).attr('href');
            if (href == "#tab-party") {
                changeParty();
            } else if (href == "#tab-business") {
                loadBusiness();
            } else if (href == "#tab-system") {
                loadUserRoles(['SYSTEM_ADMIN'], 'getSystemScopeUserRoles.do');
            }
        });
    });
</script>

<div class="admin-container">
    <h2><spring:message code="label.admin.administration"/></h2>

    <div id="admin-tabs">
        <ul>
            <li><a href="#tab-party"><spring:message code="label.admin.manageParty"/></a></li>
            <c:if test="${businessAdministration}">
                <li><a href="#tab-business"><spring:message code="label.admin.manageBusiness"/></a></li>
            </c:if>
            <c:if test="${systemAdministration}">
                <li><a href="#tab-system"><spring:message code="label.admin.manageSystemUsers"/></a></li>
            </c:if>
        </ul>
        <div id="tab-party">
            <jsp:include page="partyAdmin.jsp"/>
        </div>
        <c:if test="${businessAdministration}">
            <div id="tab-business">
                <jsp:include page="businessAdmin.jsp"/>
            </div>
        </c:if>
        <c:if test="${systemAdministration}">
            <div id="tab-system">
                <jsp:include page="systemAdmin.jsp"/>
            </div>
        </c:if>

        <br/>
        <br/>
        <div class="content-navigation">
            <span class="right">
                <a href="<spring:url value="/inbox.do"/>"><spring:message
                    code="label.back2Msgs"/></a>
            </span>
        </div>
    </div>
</div>

<div id="dialog-form" class="userrole-dialog">
    <div class="grid">
        <span class="grid-row">
            <span class="grid-cell header-label-sts"><spring:message code="label.admin.name"/></span>
            <span class="grid-cell mandatory-value-container-sts">
              <input type="text" name="uname" id="uname" class="edit-header-value-usr-mgmt" />
            </span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label-sts"><spring:message code="label.admin.login"/></span>
            <span class="grid-cell mandatory-value-container-sts">
              <input type="text" name="ulogin" id="ulogin" value="" class="edit-header-value-usr-mgmt" />
            </span>
        </span>
        <span class="grid-row" id="dialogEmailNotif">
            <span class="grid-cell header-label-sts"><spring:message code="label.admin.bundle.notifications"/></span>
            <span class="grid-cell">
                <span class="grid-row">
                    <span class="grid-cell-checkbox">
                        <input type="checkbox" name="unotifications" id="unotifications" value=""
                               onclick="toggleNotificationCheckbox(this, 'user-email-container')"/>
                    </span>
                    <span id="user-email-container" class="grid-cell value-container-sts">
                        <input type="text" name="uemail" id="uemail" value="" class="edit-header-value-near-cb"/>
                    </span>
                </span>
            </span>
        </span>
        <span class="grid-row" id="dialogStsEmailNotif">
            <span class="grid-cell header-label-sts"><spring:message code="label.admin.status.notifications"/></span>
            <span class="grid-cell">
                <span class="grid-row">
                    <span class="grid-cell-checkbox">
                        <input type="checkbox" name="usnotifications" id="usnotifications" value=""
                               onclick="toggleNotificationCheckbox(this, 'user-semail-container')"/>
                    </span>
                    <span id="user-semail-container" class="grid-cell value-container-sts">
                        <input type="text" name="usemail" id="usemail" value="" class="edit-header-value-near-cb"/>
                    </span>
                </span>
            </span>
        </span>
        <span class="grid-row" id="partyOperatorAdmin">
            <span class="grid-cell header-label-sts"><spring:message code="label.admin.user.role"/></span>
            <span class="grid-cell mandatory-value-container-sts">
              <div class="form-inline">
                  <select id="uroletype" class="form-control">
                      <%-- dynamically populated from JS depending on the type of available roles --%>
                  </select>
              </div>
            </span>
        </span>
    </div>
</div>

<div id="dialog-confirm" title="<spring:message code="label.admin.deleteUserRoleAssignment"/>">
    <p>
    <span class="header-value"><spring:message code="label.admin.confirmDeleteText"/></span>
    </p>
</div>

<div id="party-name-dialog" class="userrole-dialog">
    <div class="grid">
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.party.name.display"/></span>
            <span class="grid-cell mandatory-value-container">
              <input type="text" name="dname" id="dname" maxlength="75" class="edit-header-value-usr-mgmt" />
            </span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.party.name.node"/></span>
            <span class="grid-cell mandatory-value-container">
              <input type="text" name="nname" id="nname" value="" class="edit-header-value-usr-mgmt" />
            </span>
        </span>
    </div>
</div>

<div id="party-certificate-dialog" class="party-certificate-dialog">
    <div class="grid">
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.admin.certificate.version" /></span>
            <span class="grid-cell text-value" id="certificate-version"></span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.admin.certificate.serialNumber" /></span>
            <span class="grid-cell text-value" id="certificate-serial-number"></span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.admin.certificate.issuerDN" /></span>
            <span class="grid-cell text-value" id="certificate-issuer-dn"></span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.admin.certificate.startDate" /></span>
            <span class="grid-cell text-value" id="certificate-start-date"></span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.admin.certificate.finalDate" /></span>
            <span class="grid-cell text-value" id="certificate-final-date"></span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.admin.certificate.subjectDN" /></span>
            <span class="grid-cell text-value" id="certificate-subject-dn"></span>
        </span>
        <span class="grid-row">
            <span class="grid-cell header-label"><spring:message code="label.admin.certificate.signatureAlgorithm" /></span>
            <span class="grid-cell text-value" id="certificate-signature-algorithm"></span>
        </span>
    </div>
</div>