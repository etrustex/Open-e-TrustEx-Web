'use strict';

SignatureController.$inject = ['DataProvider', '$cookies', '$translate', '$timeout'];

function SignatureController(DataProvider, $cookies, $translate, $timeout) {
    var that = this;
    that.COOKIE_TTL = 90;
    that.signature = null;
    that.dialog = null;

    that.ICON_TYPE = {
        information: 1,
        warning: 2,
        error: 3,
        valid: 4,
        question: 5
    };

    that.init = init;
    that.showCertificate = showCertificate;
    that.trustCertificate = angular.noop;

    function init(isSignatureRequired) {
        if (DataProvider.data.signature.isProvided == 'true') {
            that.signature = {
                icon: that.ICON_TYPE.information,
                label: 'downloadView.signatureLoading.text'
            };

           if(DataProvider.data.signature.isValid == 'true') {
               that.isSignatureValid=true
           } else {
              verifySignature(DataProvider.data.signature.xmlDataToSign, DataProvider.data.signature.xmlExtractedSignature)
                .done (function(result) {
                that.isSignatureValid = result
              })
            }

            that.certificate = DataProvider.hasData(DataProvider.data.signature.certificate) ? JSON.parse(DataProvider.data.signature.certificate) : null;
            that.isCertificateExpired = false;

            if (that.certificate != null) {
                that.isCertificateExpired = checkCertificateExpired(that.certificate.expiryDate);
                initializeDialog();

                var cookieKey = that.certificate.commonName + '_' + that.certificate.serialNumber + '_' + that.certificate.thumbPrint;
                var isTrustedFromCookie = DataProvider.hasData($cookies.get(cookieKey, getExpirationSettings()));

                var populateIconAndLabel = function(isTrustedCertificate) {
                    var labelData = prepareIconAndLabel(isTrustedCertificate, that.isSignatureValid);

                    $timeout(function() {
                        that.signature.icon = labelData.icon;
                        that.signature.label = labelData.label;
                        DataProvider.local.isCertificateTrusted = isTrustedCertificate;
                    });
                };

                if (isTrustedFromCookie == false) {
                    that.trustCertificate = function() {
                        showTrustCertificateDialog(function() { populateIconAndLabel(true); },
                                                   function() { populateIconAndLabel(false); },
                                                   function() {
                                                       populateIconAndLabel(true);
                                                       $cookies.put(cookieKey, 'true', getExpirationSettings());
                                                   });
                    }
                } else {
                    populateIconAndLabel(true);
                }
            } else {
                that.signature.icon = that.ICON_TYPE.error;
                that.signature.label = 'downloadView.certNotLoadedLbl.text';
            }
        } else if (isSignatureRequired == true) {
            that.signature = {
                icon: that.ICON_TYPE.error,
                label: 'downloadView.loadSignatureInfo.error'
            };
            that.certificate = null;
        }

    }

    // expirationDateString: date in format - dd-MM-YYYY hh:mm:ss
    function checkCertificateExpired(expirationDateString) {
        var isExpired = false;
        if (DataProvider.hasData(expirationDateString)) {
            var tmp = expirationDateString.split(' ');
            var date = tmp[0].split('-');
            var hour = tmp[1].split(':');

            var expirationDate = new Date(parseInt(date[2]), parseInt(date[1]), parseInt(date[0]),
                                          parseInt(hour[0]), parseInt(hour[1]), parseInt(hour[2]), 0);
            isExpired = expirationDate < new Date();
        }

        return isExpired;
    }

    function getExpirationSettings() {
        var date = new Date();
        date.setDate(date.getDate() + that.COOKIE_TTL);

        return {
            expires: date
        };
    }

    function initializeDialog() {
        that.dialog = {
            icon: that.ICON_TYPE.question,
            title: 'downloadView.trustCertDlg.title',
            message: 'downloadView.signatureLbl.text',
            openQuestionDialog: null,
            openInformationDialog: null
        };

        var openDialog = function(buttons) {
            $('.certificate-dialog')
                .dialog({
                    dialogClass: 'no-close',
                    resizable: false,
                    autoOpen: false,
                    closeOnEscape: false,
                    modal: true,
                    position: { my: 'center', at: 'center', of: window },
                    title: decodeURIComponent(escapeHtml($translate.instant(that.dialog.title))),
                    height: 'auto',
                    width: 'auto',
                    buttons: buttons
                }).dialog('open');
        };

        that.dialog.openInformationDialog = function() {
            var buttons = [
                {
                    text: $translate.instant('commonView.optionPane.option.ok'),
                    click: function() {
                        $( this ).dialog('close');
                    }
                }
            ];

            openDialog(buttons);
        };

        that.dialog.openQuestionDialog = function(trustCertCallback, dontTrustCertCallback, alwaysTrustCertCallback) {
            var buttons = [
                {
                    text: $translate.instant('commonView.trustOption.yes'),
                    click: function() {
                        trustCertCallback();
                        $( this ).dialog('close');
                    }
                },
                {
                    text: $translate.instant('commonView.trustOption.no'),
                    click: function() {
                        dontTrustCertCallback();
                        $( this ).dialog('close');
                    }
                },
                {
                    text: $translate.instant('commonView.trustOption.always'),
                    click: function() {
                        alwaysTrustCertCallback();
                        $( this ).dialog('close');
                    }
                }
            ];

            openDialog(buttons);
        };
    }

    function showTrustCertificateDialog(trustCertCallback, dontTrustCertCallback, alwaysTrustCertCallback) {
        if (that.dialog != null) {
            that.dialog.openQuestionDialog(trustCertCallback, dontTrustCertCallback, alwaysTrustCertCallback);
        }
    }

    function prepareIconAndLabel(isTrustedCertificate, isSignatureValid) {
        var icon = that.ICON_TYPE.warning;
        var label;

        if (isTrustedCertificate == false) {
            label = 'downloadView.signatureNotTrustedLbl.text';
        } else if (isSignatureValid == false) {
            label = 'downloadView.signatureNotValidatedLbl.text';
        } else {
            icon = that.ICON_TYPE.valid;
            label = 'downloadView.signatureTrustedAndValidatedLbl.text';
        }

        return {
            icon: icon,
            label: label
        };
    }

    function showCertificate() {
        if (that.dialog != null) {
            that.dialog.icon = that.ICON_TYPE.information;
            that.dialog.title = 'downloadVies.dataCertDlg.title';
            that.dialog.message = 'downloadView.certLbl.text';
            that.dialog.openInformationDialog();
        }
    }


}

angular.module('etrustex.message.signature', [])
    .controller('SignatureController', SignatureController);