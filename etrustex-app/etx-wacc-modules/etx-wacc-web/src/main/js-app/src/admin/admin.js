/*
TODO CHECK IF THIS FILE IS USED
 */

var DEFAULT_DIALOG_POSITION = { my: 'center', at: 'center', of: window };

var partiesCanSendValue = {};

var enforceNotifications;

var openedBranchesParties = [];

function jsEscape(str) {
    return str
		.replace(/\"/g, '\#34;')
		.replace(/\&/g, '\#38;')
		.replace(/\'/g, '\#39;')
		.replace(/\(/g, '\#40;')
		.replace(/\)/g, '\#41;');
}

function jsUnescape(str){
    return str
        .replace(/#34;/g, '"')
		.replace(/#38;/g, '&')
		.replace(/#39;/g, "'")
		.replace(/#40;/g, "(")
		.replace(/#41;/g, ")");
}

function defaultDialogOptions(dialogTitle) {
    var options = { position: DEFAULT_DIALOG_POSITION };
    if (dialogTitle != undefined && dialogTitle != null) {
        options['title'] = dialogTitle
    }
    return options;
}

function changeParty() {
    loadPartyDetails();
    loadUserRoles(['PARTY_ADMIN', 'OPERATOR'], 'getPartyScopeUserRoles.do');
}

function loadBusiness() {
    loadBusinessParties();
    loadUserRoles(['BUSINESS_ADMIN'], 'getBusinessScopeUserRoles.do');
}

String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined' ? args[number] : match;
    });
};

function loadPartyDetails() {
    if ($("#party").val().length > 0) {
        var partyId = $("#party").val();

        var json = {
            "partyId": partyId
        };
        $.ajax({
            url: url('loadPartyDetails.do'),
            type: 'POST',
            dataType: 'json',
            traditional: true,
            contentType: 'application/json',
            data: JSON.stringify(json),
            cache: false,
            success: function (response) {
                $('#pemail').removeClass("ui-state-error");
                $('#psemail').removeClass("ui-state-error");
                $("#party-details-tips").text("");
                enablePartyData();
                $('#pemail').val(response.email);
                $("#pnotifications").prop('checked', response.notificationsEnabled);
                $('#psemail').val(response.statusNotificationEmail);
                $("#psnotifications").prop('checked', response.statusNotificationEnabled);
                $('#businessLabel').html(response.businessLabel);
                if (partiesCanSendValue[$("#party").val()]) {
                    $("#span-psnotifications").show();
                    $(".userrole-grid-cell.text-header").removeClass("nosnotification");
                    $(".userrole-grid-cell.text-header-login").removeClass("nosnotification");
                    $(".userrole-grid-email-cell-header").removeClass("nosnotification");
                } else {
                    $("#span-psnotifications").hide();
                    $(".userrole-grid-cell.text-header").addClass("nosnotification");
                    $(".userrole-grid-cell.text-header-login").addClass("nosnotification");
                    $(".userrole-grid-email-cell-header").addClass("nosnotification");
                }
                enforceNotifications = response.enforceNotifications;
                validateFuncEmail(response);
                togglePartyNotificationCheckbox('#pnotifications', 'party-email-container');
                togglePartyNotificationCheckbox('#psnotifications', 'party-semail-container');
            },
            error: function (xhr, status, error) {
                etx_alert(messages['error.admin.generic'], error);
            }
        });
    }else{
        $("#span-psnotifications").hide();
    }
}

function savePartyDetails() {
    var notificationsEnabled = $("#pnotifications").prop('checked');
    var party = $("#party");
    var email = $("#pemail");
    var tips = $("#party-details-tips");
    tips.text("").removeClass("ui-state-highlight");
    email.removeClass("ui-state-error");
    var stsNotificationsEnabled = $("#psnotifications").prop('checked');
    var semail = $("#psemail");
    var bValid = true;

    if(enforceNotifications){
        bValid = checkIsMandatoryAndEmailMultiRecipients(email);
    }
    if (notificationsEnabled) {
        bValid = bValid & checkMandatory(email, tips);
    }
    bValid = bValid && checkIsEmailMultiRecipients(email, tips);

    if (stsNotificationsEnabled) {
        bValid = bValid && checkMandatory(semail, tips);
    }
    bValid = bValid && checkIsEmail(semail, tips);

    if (bValid) {
        var json = {
            "partyId": party.val(),
            "email": $.trim(email.val()),
            "notificationsEnabled": notificationsEnabled,
            "statusNotificationEmail": $.trim(semail.val()),
            "statusNotificationEnabled": stsNotificationsEnabled
        };
        $.ajax({
            url: url('savePartyDetails.do'),
            type: 'POST',
            dataType: 'json',
            traditional: true,
            contentType: 'application/json',
            data: JSON.stringify(json),
            cache: false,
            success: function (response) {
                changeParty();
            },
            error: function (xhr, status, error) {
                etx_alert(messages['error.admin.generic'], error);
            }
        });

        email.removeClass("ui-state-error");
        semail.removeClass("ui-state-error");
        tips.text("");
    }
}

function disableThanLoadPartiesToAdmin() {
    disablePartyData();
    $.ajax({
        url: url('getPartiesToAdmin.do'),
        type: 'POST',
        cache: false,
        success: function (response) {
            cleanAndRenderPartiesToAdmin(response);
        },
        error: function (xhr, status, error) {
            etx_alert(messages['error.admin.generic'], error);
        }
    });
}

function loadBusinessParties() {
    if ($("#business").val().length > 0) {
        var businessId = $("#business").val();

        $.ajax({
            url: url('getBusinessScopeParties.do'),
            type: 'POST',
            data: {businessId: businessId},
            cache: false,
            success: function (response) {
                enableLink('add-business-admin');
                cleanAndRenderBusinessPartiesTable(response);
                showOpenedBranchesParties();
            },
            error: function (xhr, status, error) {
                etx_alert(messages['error.admin.generic'], error);
            }
        });
     }
}

function loadUserRoles(roles, urlParam) {
    var idxParty = $.inArray('PARTY_ADMIN', roles);
    var idxOp = $.inArray('OPERATOR', roles);
    var idxBus = $.inArray('BUSINESS_ADMIN', roles);
    var idxSys = $.inArray('SYSTEM_ADMIN', roles);

	if (($("#party").val().length > 0 && idxParty > -1 && idxOp > -1) || ($("#business").val().length > 0 && idxBus > -1) || idxSys > -1) {
        var partyId = $("#party").val();
        var businessId = $("#business").val();
        var json = {
            "roleTypes": roles,
            "partyId": partyId,
            "businessId": businessId
        };

        $.ajax({
            url: url(urlParam),
            type: 'POST',
            dataType: 'json',
            traditional: true,
            contentType: 'application/json',
            data: JSON.stringify(json),
            cache: false,
            success: function (response) {
                cleanUserRoleTable(roles);
                renderUserRoleTable(roles, response);
            },
            error: function (xhr, status, error) {
                etx_alert(messages['error.admin.generic'], error);
            }
        });
    }
}

function showIcaDetails() {
    $.ajax({
        url: url('showIcaDetails.do'),
        type: 'GET',
        dataType: 'json',
        traditional: true,
        cache: false,
        success: function (response) {
            $('#loader').hide();
            renderIcaDetailsTable(response);
        },
        error: function (xhr, status, error) {
            etx_alert(messages['error.admin.generic'], error);
        }
    });
}

function renderIcaDetailsTable(icas) {
    for (var i = 0; i < icas.length; i++) {
        $("#ICA_TABLE").append(renderIca(icas[i]));
    }

    $("#ICA-INFO").show();
}

function renderIca(ica) {
    return $.parseHTML(icaDetailsTemplate.format(
        escapeHtml(ica.senderParty) + escapeHtml(ica.receiverParty),
        escapeHtml(ica.senderParty),
        escapeHtml(ica.receiverParty),
        escapeHtml(ica.integrityCode),
        escapeHtml(ica.confidentialityCode),
        escapeHtml(ica.lastDateReloaded),
        escapeHtml(ica.version),
        escapeHtml(ica.serialNumber),
        escapeHtml(ica.issuerDN),
        escapeHtml(ica.startDate),
        escapeHtml(ica.finalDate),
        escapeHtml(ica.subjectDN),
        escapeHtml(ica.signatureAlgorithm),
        ica.hasCertificate ? 'visible' : 'hidden',
        ica.activeState ? 'hidden' : 'visible'
    ));
}

function reloadIcaDetails() {
    $.ajax({
        url: url('reloadIcaDetails.do'),
        type: 'GET',
        dataType: 'json',
        traditional: true,
        cache: false,
        success: function (response) {
            $('#loader').hide();
            renderIcaDetailsTable(response);
        },
        error: function (xhr, status, error) {
            $('#loader').hide();
            etx_alert(messages['error.admin.generic'], error);
        }
    });
}

function cleanAndRenderPartiesToAdmin(result) {
    var parties = $("#party");
    var partyId = $("#party").val();
    parties.empty();
    if (result.length > 1) {
        parties.append("<option></option>");
        $.each(result, function () {
            parties.append($("<option />").val(this.partyId).text(this.displayName));
            partiesCanSendValue[this.partyId] = this.canSend;
        });
        $("#party").combobox();
        if (partyId.length > 0) {
            $("#party option[value=id]".replace('id', partyId)).attr("selected", "selected");
            $(".custom-combobox input").val($("#party option:selected").text());
        }
    } else if (result.length == 1) {
        var theParty = result[0];
        partiesCanSendValue[theParty.partyId] = theParty.canSend;
        $("#businessLabel").remove();
        $("#oneParty").remove();
        var html = '<span id="oneParty" class="header-value row-element">' + result[0].displayName + '</span>';
        html += '<input type="hidden" id="party" value="' + result[0].partyId + '"/>';
        $("#party").replaceWith(html);
        changeParty();
    } else {
        $("#businessLabel").remove();
        $("#noParty").remove();
        var html = '<span id="noParty" class="header-value row-element">' + messages['label.admin.parties.unavailable'] + '</span>';
        html += '<input type="hidden" id="party"/>';
        $("#party").replaceWith(html);
    }
}

function disableLink(id) {
    $("#" + id).removeAttr('href');
    $("#" + id).css('color', 'grey');
}

function enableLink(id) {
    $("#" + id).attr('href', '#');
    $("#" + id).css('color', '');
}

function enablePartyData() {
    $('#pdetails').find('*').attr('disabled', false);
    $("#pdetails img").each(function () {
        $(this).show();
    });
    enableLink('save-party-details');
    enableLink('add-admin');
}

function disablePartyData() {
    $("#pdetails input:text").each(function () {
        $(this).val('');
        $(this).attr('disabled', true);
        if ($(this).parent().hasClass("mandatory-value-container")) {
            $(this).parent().addClass("value-container");
            $(this).parent().removeClass("mandatory-value-container");
        }
    });
    $("#pdetails textarea").each(function () {
        $(this).val('');
        $(this).attr('disabled', true);
        if ($(this).parent().hasClass("mandatory-value-container")) {
            $(this).parent().addClass("value-container");
            $(this).parent().removeClass("mandatory-value-container");
        }
    });
    $("#pdetails input:checkbox").each(function () {
        $(this).removeAttr('checked');
        $(this).attr('disabled', true);
    });
    $("#pdetails img").each(function () {
        $(this).hide();
    });
    disableLink('save-party-details');
    disableLink('add-admin');
    cleanUserRoleTable(['PARTY_ADMIN', 'OPERATOR']);
    $('#businessLabel').html('');
}

function disableBusinessData() {
    disableLink('add-business-admin');
    cleanUserRoleTable(['BUSINESS_ADMIN', 'OPERATOR']);
    $("#BUSS_PARTY_TREE >.tree >.partyIcaList").empty();
}

function resetCache() {
    $.ajax({
        url: url('resetCache.do'),
        type: 'GET',
        dataType: 'json',
        traditional: true,
        cache: false,
        success: function (resp) {
            $('#resetCacheDiv').append('<div id="resetCacheResponse" class="row-element" style="padding-left: 20px; padding-top: 5px">Success: ' + resp + '</div>');
            setTimeout(function () {
                $('#resetCacheResponse').remove();
            }, 2000);
        },
        error: function (xhr, status, error) {
            etx_alert(messages['error.admin.generic'], error);
        }
    });
}

function renderUserRoleTable(roles, urs) {
    var idxParty = $.inArray('PARTY_ADMIN', roles);
    var idxOp = $.inArray('OPERATOR', roles);
    var idxBus = $.inArray('BUSINESS_ADMIN', roles);
    var idxSys = $.inArray('SYSTEM_ADMIN', roles);

    if (idxParty > -1 || idxOp > -1) {
        partyIds = [];
    } else if (idxBus > -1) {
        businessAdminIds = [];
    } else if (idxSys > -1) {
        systemAdminIds = [];
    }
    for (var i = 0; i < roles.length; i++) {
        $("#" + roles[i] + "_TABLE").html("");
    }
    for (var i = 0; i < urs.length; i++) {
        $("#" + urs[i].roleType + "_TABLE").append(renderUserRoleRow(urs[i]));
        if (idxParty > -1 || idxOp > -1) {
            partyIds.push(urs[i].login);
            if (!urs[i].deletable) {
                $("#ACT-" + urs[i].userRoleId).attr('title', messages['label.admin.notif.delete.mandatory']);
            }
        } else if (idxBus > -1) {
            businessAdminIds.push(urs[i].login);
        } else if (idxSys > -1) {
            systemAdminIds.push(urs[i].login);
        }
    }
}

var renderPartyList = function (party, index, arr) {
    var icasRelatedParties = party.partyIcas;
    var branch;
    if (icasRelatedParties != null && icasRelatedParties.length > 0) {
        var icaList = "";
        for (var i = 0; i < icasRelatedParties.length; i++) {
            var signature = messages["label.ica.signature.not.required"];
            if (icasRelatedParties[i].signatureMandatory) {
                signature = messages["label.ica.signature.required"];
            }
            var encryption = messages["label.ica.encryption.not.required"];
            if (icasRelatedParties[i].encryptionRequired) {
                encryption = messages["label.ica.encryption.required"];
            }
			icaList += icaTreeLeafTemplate.format(jsEscape(icasRelatedParties[i].remotePartyDisplayName), icasRelatedParties[i].remotePartyNodeName, icasRelatedParties[i].remotePartyId, signature, encryption, escapeHtml(icasRelatedParties[i].remotePartyDisplayName));
        }
        branch = icaTreeBranchTemplate_with_ICA.format(jsEscape(party.displayName), party.nodeName, party.partyId, icaList, escapeHtml(party.displayName));
    } else {
		branch = icaTreeBranchTemplate_without_ICA.format(jsEscape(party.displayName), party.nodeName, party.partyId, escapeHtml(party.displayName));
    }
    $('#treeList').append(branch);

};

function cleanAndRenderBusinessPartiesTable(parties) {

    $("#treeList").empty();
    parties.forEach(renderPartyList);
    $('.tree li:has(ul)').addClass('parent_li').find(' > span')
    $('.tree li.parent_li > span').each(function (index) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        children.hide('fast');
        $(this).find(' > i').removeClass('glyphicon glyphicon-minus-sign').addClass('glyphicon glyphicon-plus-sign');

    });
    $('.tree li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).find(' > i').removeClass('glyphicon glyphicon-minus-sign').addClass('glyphicon glyphicon-plus-sign');
        } else {
            children.show('fast');
            $(this).find(' > i').removeClass('glyphicon glyphicon-plus-sign').addClass('glyphicon glyphicon-minus-sign');
        }
        e.stopPropagation();
    });
}

function deleteRoleId(login, role) {
    if (role == "PARTY_ADMIN" || role == "OPERATOR") {
        var index = $.inArray(login, partyIds);
        if (index > -1) {
            partyIds.splice(index, 1);
        }
    } else if (role == "BUSINESS_ADMIN") {
        var index = $.inArray(login, businessAdminIds);
        if (index > -1) {
            businessAdminIds.splice(index, 1);
        }
    } else if (role == "SYSTEM_ADMIN") {
        var index = $.inArray(login, systemAdminIds);
        if (index > -1) {
            systemAdminIds.splice(index, 1);
        }
    }
}

function cleanUserRoleTable(roles) {
    for (var i = 0; i < roles.length; i++) {
        $("span").remove("#" + roles[i] + "_TABLE>.userrole-grid-row[id|='UR']");
        $("span").remove("#" + roles[i] + "_TABLE>.userrole-grid-row[id|='URB']");
        $("span").remove("#" + roles[i] + "_TABLE>.userrole-grid-row[id|='URS']");
    }
}

function cleanIcaTable() {
    $("span").remove("#ICA_TABLE>.userrole-grid-row");
}

function renderUserRoleRow(ur) {
    if (ur.roleType == "SYSTEM_ADMIN") {
        return $.parseHTML(userRoleRowTemplateSystem.format(
            ur.userRoleId,
            escapeHtml(ur.name),
            escapeHtml(ur.login),
            escapeHtml(ur.roleType)
        ));
    } else if (ur.roleType == "BUSINESS_ADMIN") {
        return $.parseHTML(userRoleRowTemplateBusiness.format(
            ur.userRoleId,
            escapeHtml(ur.name),
            escapeHtml(ur.login),
            escapeHtml(ur.roleType)
        ));
    } else if (ur.roleType == "PARTY_ADMIN" || ur.roleType == "OPERATOR") {
        if (partiesCanSendValue[$("#party").val()]) {
            var template = ur.deletable ? userRoleRowTemplate : userRoleRowTemplate.replace(deleteImgSrc, notDeleteImgSrc);
            $("#span-admin-sts-notif").show();
            $("#span-oper-sts-notif").show();
            return $.parseHTML(template.format(
                ur.userRoleId,
                escapeHtml(ur.name),
                escapeHtml(ur.login),
                escapeHtml(ur.email),
                (ur.notificationsEnabled ? "checked=\"checked\"" : ""),
                escapeHtml(ur.roleType),
                ur.deletable,
                (ur.deletable ? 'control' : ''),
                (ur.statusNotificationEnabled ? "checked=\"checked\"" : ""),
                escapeHtml(ur.statusNotificationEmail)));
        } else {
            var template = ur.deletable ? userRoleRowTemplateWithoutSNotification : userRoleRowTemplateWithoutSNotification.replace(deleteImgSrc, notDeleteImgSrc);
            $("#span-admin-sts-notif").hide();
            $("#span-oper-sts-notif").hide();
            return $.parseHTML(template.format(
                ur.userRoleId,
                escapeHtml(ur.name),
                escapeHtml(ur.login),
                escapeHtml(ur.email),
                (ur.notificationsEnabled ? "checked=\"checked\"" : ""),
                escapeHtml(ur.roleType),
                ur.deletable,
                (ur.deletable ? 'control' : '')));

        }
    }
}

function escapeHtml(content) {
    var div = $("<div />");
    if (content != null) {
        div.text(content);
    }
    return div.html();
}

function deletePartyUserRole(userRoleId, login, roleType, deletable) {
    if (deletable) {
        deleteUserRole(userRoleId, login, roleType);
    }
}

function deleteUserRole(userRoleId, login, roleType) {
    $("#dialog-confirm").dialog('option', defaultDialogOptions());
    $("#dialog-confirm").data("userRoleId", userRoleId)
        .data("login", login)
        .data("roleType", roleType)
        .dialog("open");
}

function togglePartyNotificationCheckbox(checkbox, emailContainerId) {
    if ($(checkbox).prop('checked')) {
        $("#" + emailContainerId).addClass("mandatory-value-container");
        $("#" + emailContainerId).removeClass("value-container");

        // apply specific status notif rules
        if ($(checkbox).attr('id') == 'psnotifications'
            && $.trim($('#pemail').val()).length > 0
            && $.trim($('#psemail').val()).length == 0) {
            $('#psemail').val($('#pemail').val());
        }
    } else {
        $("#" + emailContainerId).addClass("value-container");
        $("#" + emailContainerId).removeClass("mandatory-value-container");
    }
}

function toggleNotificationCheckbox(checkbox, emailContainerId) {
    if ($(checkbox).prop('checked')) {
        $("#" + emailContainerId).addClass("mandatory-value-container-sts");
        $("#" + emailContainerId).removeClass("value-container-sts");

        // apply specific status notif rules
        if ($(checkbox).attr('id') == 'usnotifications'
            && $.trim($('#uemail').val()).length > 0
            && $.trim($('#usemail').val()).length == 0) {
            $('#usemail').val($('#uemail').val());
        }
    } else {
        $("#" + emailContainerId).addClass("value-container-sts");
        $("#" + emailContainerId).removeClass("mandatory-value-container-sts");
    }
}

function updateTips(t, tips) {
    tips
        .text(t)
        .addClass("ui-state-highlight");
    setTimeout(function () {
        tips.removeClass("ui-state-highlight", 1500);
    }, 500);
}

function checkMandatory(o, tips) {
    if (o.val().length == 0) {
        o.addClass("ui-state-error");
        updateTips(messages["error.admin.mandatoryFields"], tips);
        return false;
    } else {
        return true;
    }
}

function checkMandatoryDropdown(o, tips) {
    if (o.val().length == 0 || o.val() == "EMPTY") {
        o.addClass("ui-state-error");
        updateTips(messages["error.admin.mandatoryFields"], tips);
        return false;
    } else {
        return true;
    }
}

function checkIsMandatoryAndEmailMultiRecipients(o) {
    if (o.val().length != 0) {
        var trimmedValue = $.trim(o.val());

        if (trimmedValue.length != 0) {
            var splittedValue = trimmedValue.split(/;|,/);

            for (i = 0; i < splittedValue.length; i++) {
                if (i > 9 || !isEmail(splittedValue[i])) {
                    return false;
                }
            }

            return true;
        }
    }

    return false;
}

function checkIsEmailMultiRecipients(o, tips) {
    var trimmedValue = $.trim(o.val());

    if (trimmedValue.length != 0) {
        var splittedValue = trimmedValue.split(/;|,/);

        for (i = 0; i < splittedValue.length; i++) {
            if (i > 9 || !isEmail(splittedValue[i])) {
                o.addClass("ui-state-error");
                updateTips(messages["error.admin.wrongEmail"], tips);
                return false;
            }
        }
    }

    return true;
}

function checkIsEmail(o, tips) {
    var trimmedValue = $.trim(o.val());
    if (trimmedValue.length != 0 && !isEmail(trimmedValue)) {
        o.addClass("ui-state-error");
        updateTips(messages["error.admin.wrongEmail"], tips);
        return false;
    } else {
        return true;
    }
}

function checkIsLogin(o) {
    if (o.val().length != 0 && !isAllowedLogin(o.val())) {
        o.addClass("ui-state-error");
        return false;
    } else {
        return true;
    }
}

var partyIds = [];
var businessAdminIds = [];
var systemAdminIds = [];


$(function () {
    var name = $("#uname"),
        login = $("#ulogin"),
        email = $("#uemail"),
        semail = $("#usemail"),
        roleType = $("#uroletype"),
        party = $("#party"),
        business = $("#business"),
        notifications = $("#unotifications"),
        snotifications = $("#usnotifications"),
        allFields = $([]).add(name).add(login).add(email).add(notifications).add(semail).add(snotifications),
        tips = $("#user-role-tips");

    $("#dialog-form").dialog({
        position: DEFAULT_DIALOG_POSITION,
        autoOpen: false,
        height: 'auto',
        minWidth: 417,
        modal: true,
        buttons: [
            {
                text: messages["label.save"],
                click: function () {

                    var bValid = true;
                    var nameVal = name.val();
                    var loginVal = login.val();
                    var emailVal = $.trim(email.val());
                    var notificationsEnabled = notifications.prop('checked');
                    var semailVal = $.trim(semail.val());
                    var snotificationsEnabled = snotifications.prop('checked');
                    var partyIdVal = $("#party").val();

                    allFields.removeClass("ui-state-error");
                    bValid = bValid && checkMandatory(name, tips);
                    bValid = bValid && checkIsLogin(login);
                    if (notificationsEnabled) {
                        bValid = bValid && checkMandatory(email, tips);
                    }
                    bValid = bValid && checkIsEmail(email, tips);
                    if (snotificationsEnabled) {
                        bValid = bValid && checkMandatory(semail, tips);
                    }
                    bValid = bValid && checkIsEmail(semail, tips);
                    bValid = bValid && checkMandatoryDropdown(roleType, tips);

                    if (bValid) {
                        var json = {
                            "userRoleId": $(this).data("userRoleId"),
                            "name": nameVal,
                            "login": loginVal,
                            "email": emailVal,
                            "notificationsEnabled": notificationsEnabled,
                            "statusNotificationEmail": semailVal,
                            "statusNotificationEnabled": snotificationsEnabled,
                            "partyId": partyIdVal,
                            "businessId": business.val(),
                            "roleType": roleType.val()
                        };
                        $.ajax({
                            url: $(this).data("url"),
                            type: 'POST',
                            dataType: 'json',
                            traditional: true,
                            contentType: 'application/json',
                            data: JSON.stringify(json),
                            cache: false,
                            success: $(this).data("successHandler"),
                            error: function (xhr, status, error) {
                                etx_alert(messages['error.admin.generic'], error);
                            }
                        });

                        $(this).dialog("close");
                    }
                }
            },
            {
                text: messages["label.admin.cancel"],
                click: function () {
                    $(this).dialog("close");
                }
            }
        ],
        close: function () {
            allFields.val("").removeClass("ui-state-error");
            notifications.prop('checked', false);
            snotifications.prop('checked', false);
            tips.text("");
            login.prop('disabled', false);
            $('#user-email-container').removeClass("mandatory-value-container-sts");
            $('#user-email-container').addClass("value-container-sts");
            $('#user-semail-container').removeClass("mandatory-value-container-sts");
            $('#user-semail-container').addClass("value-container-sts");
        }
    });

    $("#dialog-confirm").dialog({
        position: DEFAULT_DIALOG_POSITION,
        autoOpen: false,
        resizable: false,
        width: 400,
        height: 180,
        modal: true,
        buttons: [
            {
                text: messages["label.admin.delete"],
                click: function () {
                    var userRoleId = $(this).data("userRoleId");
                    var login = $(this).data("login");
                    var roleType = $(this).data("roleType");

                    var json = {
                        "userRoleId": userRoleId
                    };
                    $.ajax({
                        url: url('deleteUserRole.do'),
                        type: 'POST',
                        dataType: 'json',
                        traditional: true,
                        contentType: 'application/json',
                        data: JSON.stringify(json),
                        cache: false,
                        success: function (response) {
                            $("#UR-" + userRoleId).remove();
                            $("#URB-" + userRoleId).remove();
                            $("#URS-" + userRoleId).remove();
                            deleteRoleId(login, roleType);
                            if (roleType == "PARTY_ADMIN" || roleType == "OPERATOR") {
                                changeParty();
                            } else if (roleType == "BUSINESS_ADMIN") {
                                loadBusiness();
                            }
                        },
                        error: function (xhr, status, error) {
                            etx_alert(messages['error.admin.generic'], error);
                        }
                    });

                    $(this).dialog("close");
                }
            },
            {
                text: messages["label.admin.cancel"],
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });

    $("#add-admin")
        .click(function () {
            if ($("#party").val().length == 0) {
                return;
            }
            $('#uroletype').find('option').remove();
            $("#uroletype").append('<option value="EMPTY">&nbsp;</option>');
            $("#uroletype").append('<option value="PARTY_ADMIN">' + messages["label.admin.edit.user.role.administrator"] + '</option>');
            $("#uroletype").append('<option value="OPERATOR">' + messages["label.admin.edit.user.role.operator"] + '</option>');
            $("#uroletype").combobox();
            $("#uroletype").data('combobox').refresh();
            $("#partyOperatorAdmin").show();
            $("#dialogEmailNotif").show();
            if (partiesCanSendValue[$("#party").val()]) {
                $("#dialogStsEmailNotif").show();
            } else {
                $("#dialogStsEmailNotif").hide();
            }

            $("#unotifications").removeAttr("disabled");
            $("#unotifications").prop('checked', enforceNotifications);
            $("#unotifications").attr("disabled", enforceNotifications);
            if (enforceNotifications) {
                $("#unotifications").attr('title', messages['label.admin.notif.check.mandatory']);
            } else {
                $("#unotifications").removeAttr("title");
            }

            $("#usnotifications").removeAttr("disabled");
            $("#usnotifications").prop('checked', false);

            $("#dialog-form").dialog('option', defaultDialogOptions(messages["label.admin.addUserDialogTitle"]));
            $("#dialog-form")
                .data("url", url("addPartyScopeUserRole.do"))
                .data("successHandler", addUserRoleRow)
                .dialog("open");
            return false;

        });

    $("#add-business-admin")
        .click(function () {
            if ($("#business").val().length == 0) {
                return;
            }
            $('#uroletype').find('option').remove();
            $("#uroletype").append('<option value="BUSINESS_ADMIN">BUSINESS_ADMIN</option>');
            $("#uroletype").val("BUSINESS_ADMIN");
            $("#uroletype").combobox();
            $("#partyOperatorAdmin").hide();
            $("#dialogEmailNotif").hide();
            $("#dialogStsEmailNotif").hide();
            $("#dialog-form").dialog('option', defaultDialogOptions(messages["label.admin.addUserDialogTitle"]));
            $("#dialog-form")
                .data("url", url("addBusinessScopeUserRole.do"))
                .data("successHandler", addUserRoleRow)
                .dialog("open");
            return false;
        });

    $("#add-system-admin")
        .click(function () {
            $('#uroletype').find('option').remove();
            $("#uroletype").append('<option value="SYSTEM_ADMIN">SYSTEM_ADMIN</option>');
            $("#uroletype").val("SYSTEM_ADMIN");
            $("#uroletype").combobox();
            $("#partyOperatorAdmin").hide();
            $("#dialogEmailNotif").hide();
            $("#dialogStsEmailNotif").hide();
            $("#dialog-form").dialog('option', defaultDialogOptions(messages["label.admin.addUserDialogTitle"]));
            $("#dialog-form")
                .data("url", url("addSystemScopeUserRole.do"))
                .data("successHandler", addUserRoleRow)
                .dialog("open");
            return false;
        });

    $("#party-name-dialog").dialog({
        position: DEFAULT_DIALOG_POSITION,
        autoOpen: false,
        height: 'auto',
        minWidth: 417,
        modal: true,
        buttons: [
            {
                text: messages["label.save"],
                click: function () {
                    var tips = $("#party-name-tips");
                    if (checkMandatory($('#dname'))) {
                        var partyId = $(this).data("partyId");
                        var displayName = $('#dname').val();

                        $.ajax({
                            url: url("savePartyDisplayName.do"),
                            type: 'POST',
                            data: {partyId: partyId, displayName: displayName},
                            cache: false,
                            success: $(this).data("successHandler"),
                            error: function (xhr, status, error) {
                                etx_alert(messages['error.admin.generic'], error);
                            }
                        });

                        $('#dname').removeClass("ui-state-error");
                        tips.text("");

                        $(this).dialog("close");
                    }
                }
            },
            {
                text: messages["label.admin.cancel"],
                click: function () {
                    $(this).dialog("close");
                }
            }
        ],
        close: function () {
        }
    });

    $("#party-certificate-dialog").dialog({
        position: DEFAULT_DIALOG_POSITION,
        autoOpen: false,
        height: 'auto',
        width: 'auto',
        modal: true,
        buttons: [
            {
                text: messages["label.admin.close"],
                click: function () {
                    $(this).dialog("close");
                }
            }
        ],
        close: function () {
        }
    });

    $("#save-party-details")
        .click(function () {
            if ($("#party").val().length > 0) {
                savePartyDetails();
            }
        });

    disableThanLoadPartiesToAdmin();

    if ($("#isSystemAdmin").length > 0) {
        loadUserRoles(['SYSTEM_ADMIN'], 'getSystemScopeUserRoles.do');
    }

    $("#show-ica")
        .click(function () {
            $("#ICA-INFO").hide();
            $('#loader').show();
            cleanIcaTable();
            showIcaDetails();
        });

    $("#reload-ica")
        .click(function () {
            $("#ICA-INFO").hide();
            $('#loader').show();
            cleanIcaTable();
            reloadIcaDetails();
        });

    $("#reset-cache")
        .click(function () {
            resetCache();
        });

    $("#ICA-INFO").hide();

    //disable fields sts notifications when party not selected
    $("#span-psnotifications").hide();
    $("#span-admin-sts-notif").hide();
    $("#span-oper-sts-notif").hide();
});

function addUserRoleRow(ur) {
    var indexSys = $.inArray(ur.login, systemAdminIds);
    if (ur.roleType == "PARTY_ADMIN" || ur.roleType == "OPERATOR") {
        changeParty();
    } else if (ur.roleType == "BUSINESS_ADMIN") {
        loadBusiness();
    } else if (ur.roleType == "SYSTEM_ADMIN") {
        if (indexSys > -1) {
            loadUserRoles(['SYSTEM_ADMIN'], 'getSystemScopeUserRoles.do');
        } else {
            $("#" + ur.roleType + "_TABLE").append(renderUserRoleRow(ur));
            systemAdminIds.push(ur.login);
        }
    }
}

function updateUserRoleRow(ur) {
    var userRoleId = ur.userRoleId;
    $("#UR-NAME-" + userRoleId).text(ur.name);
    $("#UR-NOTIFICATION-" + userRoleId).prop('checked', ur.notificationsEnabled);
    $("#UR-EMAIL-" + userRoleId).text(ur.email);
    $("#UR-SNOTIFICATION-" + userRoleId).prop('checked', ur.statusNotificationEnabled);
    $("#UR-SEMAIL-" + userRoleId).text(ur.statusNotificationEmail);
    $("#URB-NAME-" + userRoleId).text(ur.name);
    $("#URS-NAME-" + userRoleId).text(ur.name);
    if (ur.roleType == "PARTY_ADMIN" || ur.roleType == "OPERATOR") {
        changeParty();
    } else if (ur.roleType == "BUSINESS_ADMIN") {
        loadBusiness();
    }
}

function editPartyUserRole(userRoleId, deletable) {
    var partyRoleType = $("#UR-PARTY-ROLE-TYPE-" + userRoleId).text();

    $("#dialogEmailNotif").show();

    $('#uroletype').find('option').remove();
    $("#uroletype").append('<option value=""></option>');
    $("#uroletype").append('<option value="PARTY_ADMIN">' + messages["label.admin.edit.user.role.administrator"] + '</option>');
    $("#uroletype").append('<option value="OPERATOR">' + messages["label.admin.edit.user.role.operator"] + '</option>');
	$("#uroletype").combobox();
	$("#uroletype").val(partyRoleType);
    $("#uroletype").data('combobox').refresh();

    $("#partyOperatorAdmin").show();

    $("#uname").val($("#UR-NAME-" + userRoleId).text());
    $("#ulogin").prop('disabled', true);
    $("#ulogin").val($("#UR-LOGIN-" + userRoleId).text());
    var notificationsEnabled = $("#UR-NOTIFICATION-" + userRoleId).prop('checked');
    $("#unotifications").removeAttr("disabled");
    $("#unotifications").prop('checked', notificationsEnabled);
    $("#unotifications").removeAttr("title");
    $("#uemail").val($("#UR-EMAIL-" + userRoleId).text());
    if (notificationsEnabled) {
        $("#user-email-container").addClass("mandatory-value-container-sts");
        $("#user-email-container").removeClass("value-container-sts");
        if (!deletable) {
            $("#unotifications").attr("disabled", true);
            $("#unotifications").attr('title', messages['label.admin.notif.check.mandatory']);
        }
    } else {
        $("#unotifications").prop('checked', enforceNotifications);
        $("#unotifications").attr("disabled", enforceNotifications);
        if (enforceNotifications) {
            $("#unotifications").attr('title', messages['label.admin.notif.check.mandatory']);
        }
    }

    var stsNotificationsEnabled = $("#UR-SNOTIFICATION-" + userRoleId).prop('checked');
    $("#usnotifications").removeAttr("disabled");
    $("#usnotifications").prop('checked', stsNotificationsEnabled);
    $("#usemail").val($("#UR-SEMAIL-" + userRoleId).text());
    if (stsNotificationsEnabled) {
        $("#user-semail-container").addClass("mandatory-value-container-sts");
        $("#user-semail-container").removeClass("value-container-sts");
    }
    if (partiesCanSendValue[$("#party").val()]) {
        $("#dialogStsEmailNotif").show();
    } else {
        $("#dialogStsEmailNotif").hide();
    }
    $("#dialog-form").dialog('option', defaultDialogOptions(messages["label.admin.editUserDialogTitle"]));
    $("#dialog-form")
        .data("userRoleId", userRoleId)
        .data("url", url("editUser.do"))
        .data("successHandler", updateUserRoleRow)
        .dialog("open");
}

function editUserRole(userRoleId, roleType) {
    if (roleType != 'BUSINESS_ADMIN' && roleType != 'SYSTEM_ADMIN') {
        return;
    }
    var partyRoleTypeId = (roleType == 'BUSINESS_ADMIN' ? '#URB-PARTY-ROLE-TYPE-' : '#URS-PARTY-ROLE-TYPE-') + userRoleId;
    var uroletypeVal = roleType == 'BUSINESS_ADMIN' ? '<option value="BUSINESS_ADMIN">BUSINESS_ADMIN</option>' : '<option value="SYSTEM_ADMIN">SYSTEM_ADMIN</option>';
    var unameId = (roleType == 'BUSINESS_ADMIN' ? '#URB-NAME-' : '#URS-NAME-') + userRoleId;
    var uloginId = (roleType == 'BUSINESS_ADMIN' ? '#URB-LOGIN-' : '#URS-LOGIN-') + userRoleId;

    var partyRoleType = $(partyRoleTypeId).text();

    $('#uroletype').find('option').remove();
    $("#uroletype").append(uroletypeVal);
	$("#uroletype").combobox();
	$("#uroletype").val(roleType);
    $("#uroletype").data('combobox').refresh();

    $("#partyOperatorAdmin").hide();
    $("#dialogEmailNotif").hide();
    $("#dialogStsEmailNotif").hide();

    $("#uname").val($(unameId).text());
    $("#ulogin").prop('disabled', true);
    $("#ulogin").val($(uloginId).text());

    $("#dialog-form").dialog('option', defaultDialogOptions(messages["label.admin.editUserDialogTitle"]));
    $("#dialog-form")
        .data("userRoleId", userRoleId)
        .data("url", "editUser.do")
        .data("successHandler", updateUserRoleRow)
        .dialog("open");
}

function editDisplayName(partyDisplayName, partyNodeName, partyId) {
    $('#dname').val(jsUnescape(partyDisplayName));
    $('#dname').removeClass("ui-state-error");
    $('#nname').val(partyNodeName);
    $("#nname").attr("disabled", true);

    $("#party-name-dialog").dialog('option', defaultDialogOptions(messages["label.admin.editPartyNameTitle"]));
    $("#party-name-dialog")
        .data("partyId", partyId)
        .data("successHandler", function () {
            updateOpenedBranchesParties();
            loadBusinessParties();
            disableThanLoadPartiesToAdmin();
        })
        .dialog("open");
}

function validateFuncEmail(response) {
    if (response.checkDisabled) {
        $("#pnotifications").attr("disabled", true);
        $("#pnotifications").attr('title', messages['label.admin.notif.check.mandatory']);
    } else {
        $("#pnotifications").removeAttr("disabled");
        $("#pnotifications").removeAttr("title");
    }
}

function isAllowedLogin(login) {
    var regex = /^[a-z0-9]+$/i;
    return regex.test(login);
}

function showPartyCertificate (version, serialNumber, issuerDN, startDate, finalDate, subjectDN, signatureAlgorithm) {
    $('#certificate-version').text(version);
    $('#certificate-serial-number').text(serialNumber);
    $('#certificate-issuer-dn').text(issuerDN);
    $('#certificate-start-date').text(startDate);
    $('#certificate-final-date').text(finalDate);
    $('#certificate-subject-dn').text(subjectDN);
    $('#certificate-signature-algorithm').text(signatureAlgorithm);

    $("#party-certificate-dialog").dialog('option', defaultDialogOptions(messages["label.admin.certificate.dialogTitle"]));
    $("#party-certificate-dialog").dialog("open");
}

function reloadIcaDetailsSenderReceiver(webParty, icaNodeParty) {
    var icaReloadId = "#ICA-RELOAD-" + escapeHtml(webParty) + escapeHtml(icaNodeParty);
    var icaLoaderId = '#LOADER-' + escapeHtml(webParty) + escapeHtml(icaNodeParty);
    var currentIcaId = "#ICA-DETAIL-" + escapeHtml(webParty) + escapeHtml(icaNodeParty);

    $(icaReloadId).hide();
    $(icaLoaderId).show();

    $.ajax({
        url: url('reloadIcaDetailsSenderReceiver.do'),
        type: 'GET',
        data: {webParty: webParty, icaNodeParty: icaNodeParty},
        dataType: 'json',
        cache: false,
        success: function (ica) {
            var reloadedICA = renderIca(ica);
            $(currentIcaId).replaceWith(reloadedICA);

            var isSuccess = ica.icaStatus == 'OK';
            highlightLoadedIca(isSuccess, currentIcaId);
        },
        error: function (xhr, status, error) {
            var msg = 'The reloading of ICA for Web party ' + webParty + ' and Second party ' + icaNodeParty + ' failed. </br>';
            etx_alert(msg + messages['error.admin.generic'], error);

            $(icaLoaderId).hide();
            $(icaReloadId).show();
            highlightLoadedIca(false, currentIcaId);
        }
    });
}

function highlightLoadedIca(isSuccess, currentIcaId) {
    $(currentIcaId).removeClass('highlight-ica');

    var classToAdd = isSuccess ? 'highlight-ica-loaded-ok' : 'highlight-ica-loded-failed';

    $(currentIcaId).addClass(classToAdd);
    setTimeout(function () {
        $(currentIcaId).removeClass(classToAdd);
    }, 1000);
}

function etx_alert(output_msg, title) {
    $("<div class='text-value' style='color: red'></div>").html(output_msg).dialog({
        title: title,
        resizable: false,
        modal: true,
        height: 'auto',
        width: 'auto',
        open: function (event, ui) {
            $(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
        },
        buttons: [{
            text: messages["label.admin.close"],
            click: function () {
                $(this).dialog("close");
            }
        }
        ],
        dialogClass: 'ui-state-error'
    });
}

function updateOpenedBranchesParties() {
    openedBranchesParties = [];
	$('.tree li.parent_li > a').each(function (index) {
		var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
			openedBranchesParties.push($(this).context.id);
        }
	});
}

function showOpenedBranchesParties(){
		var fLen = openedBranchesParties.length;
		for (i = 0; i < fLen; i++) {
			var parent =  $('#'+openedBranchesParties[i]).parent('li.parent_li');
			var children = parent.find(' > ul > li');
			children.show('fast');
			$('#'+openedBranchesParties[i]).prev().children().first().removeClass('glyphicon glyphicon-plus-sign').addClass('glyphicon glyphicon-minus-sign');
		}
		openedBranchesParties = [];
};

$(document).ready(function() {

    $("#party").on('blur',function(){
                    if($("#party").val()==""){
                        disablePartyData();
                    }
                });

    $("#party").on('change', function(){
        if($("#party").val() != null){
            changeParty();
        }else{
    	    disablePartyData();
    	}
    })

	$("#business").combobox();
    $("#business").on('change', function(){
            if($("#business").val() != null && $("#business").val() != ""){
                loadBusiness();
            }else{
        	    disableBusinessData();
        	}
        })

	$("#business").on('blur', function(){

		if($("#business").val()==""){
			disableBusinessData();
        }
    })


});
