'use strict';

DownloadController.$inject = ['DataProvider', '$translate'];

function DownloadController(DataProvider, $translate) {
    var that = this;
    that.init = init;
    that.disable = disable;
    that.enable = enable;
    that.isDisabled = isDisabled;
    that.showBlockingError = showBlockingError;
    that.isLocalCertificateTrusted = isLocalCertificateTrusted;
    that.isSignatureRequired = isSignatureRequired;
    that.isSignatureProvided = isSignatureProvided;
    that.getExpirationSettings = getExpirationSettings;
    that.isRetentionExpired = isRetentionExpired;
    that.isEncryptionRequired = isEncryptionRequired;

    function init() {
        that.retentionExpired = DataProvider.data.retentionExpired == 'true' || false;
        that.icaDetails = DataProvider.extractData(DataProvider.data.icaDetails);
        that.glassPane = {
            isVisible: false,
            message: null
        };

        DataProvider.data.icaDetails = null;

        if (that.retentionExpired) {
            that.showBlockingError(DataProvider.data.retentionExpiredMessage);
        } else if (that.icaDetails == null) {
            that.showBlockingError($translate.instant('downloadView.init.blockingError.text'));
        }
    }

    function isSignatureRequired() {
        return that.icaDetails.isSignatureRequired == 'true';
    }

    function isEncryptionRequired() {
        return that.icaDetails.isEncryptionRequired == 'true';
    }

    function isSignatureProvided() {
        return DataProvider.data.signature.isProvided == 'true';
    }

    function showBlockingError(message) {
        that.glassPane.isVisible = true;
        that.glassPane.message = message || null;
    }

    function isDisabled() {
        return that.glassPane.isVisible;
    }

    function disable() {
        that.glassPane.isVisible = true;
    }

    function enable() {
        that.glassPane.isVisible = false;
    }

    function getExpirationSettings() {
        var date = new Date();
        date.setDate(date.getDate() + 90);

        return {
            expires: date
        };
    }

    function isLocalCertificateTrusted(){
        return DataProvider.local.isCertificateTrusted;
    }

    function isRetentionExpired() {
        return DataProvider.data.retentionExpired;
    }

}

angular.module('etrustex.message.download', [])
    .controller('DownloadController', DownloadController);

