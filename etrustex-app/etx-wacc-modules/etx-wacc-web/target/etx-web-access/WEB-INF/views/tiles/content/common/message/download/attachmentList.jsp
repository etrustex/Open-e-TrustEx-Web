<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ver">trunk-19-04-2018 12:23</c:set>
<c:set var="localeCode" value="${pageContext.response.locale}" />
<link href="<c:url value="/css/downloadPanel.css?ver=${ver}"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript"
        src="<c:url value="/js/lib/angular.min-1.5.8.js?ver=${ver}"/>">//Source: https://angularjs.org/</script>
<script type="text/javascript" language="javascript"
        src="<c:url value="/js/lib/angular-sanitize.min-1.5.9.js?ver=${ver}"/>">//Source: https://github.com/angular/bower-angular-sanitize/</script>
<script type="text/javascript" language="javascript"
        src="<c:url value="/js/lib/angular-cookies.min-1.5.9.js?ver=${ver}"/>">//Source: https://github.com/angular/bower-angular-cookies/</script>
<script type="text/javascript" language="javascript"
        src="<c:url value="/js/lib/angular-translate.min-2.12.1.js?ver=${ver}"/>">//Source: https://github.com/angular-translate/angular-translate/</script>
<script type="text/javascript" language="javascript"
        src="<c:url value="/js/download/downloadModule.js?ver=${ver}"/>"></script>
<script type="text/javascript" language="javascript"
        src="<c:url value="/js/download/signatureModule.js?ver=${ver}"/>"></script>

<script type="text/javascript" language="javascript" src="<c:url value="/js/download/signatureVerification.js?ver=${ver}"/>"></script>

<%--
For ie11
<script type="text/javascript" language="javascript" src="<c:url value="/js/lib/polyfill.min.js?ver=${ver}"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/js/lib/promise.min.js?ver=${ver}"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/js/lib/webcrypto-shim.min.js?ver=${ver}"/>"></script>
--%>


<script type="text/javascript" language="javascript" src="<c:url value="/js/lib/xades.js"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/js/lib/jszip.min.js"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/js/dist/download.bundle.js?ver=${ver}"/>"></script>

<div class="main-panel">
    <div class="download-panel"
         ng-cloak
         ng-strict-di
         ng-app="attachmentDownloadModule"
         ng-controller="DownloadController as downloadCnt"
         ng-init="downloadCnt.init()">

        <div class="message" ng-if="downloadCnt.glassPane.message != null">{{downloadCnt.glassPane.message}}</div>

        <div class="signature-panel" ng-controller="SignatureController as signatureCnt" ng-if="!downloadCnt.retentionExpired" ng-init="signatureCnt.init(downloadCnt.isSignatureRequired())">
            <div ng-if="signatureCnt.signature != null" class="grid">
                <span class="grid-row">
                    <span class="grid-cell icon">
                        <img ng-if="signatureCnt.signature.icon == signatureCnt.ICON_TYPE.information" src="<c:url value="/images/icons/information.png"/>" width="13" height="13"/>
                        <img ng-if="signatureCnt.signature.icon == signatureCnt.ICON_TYPE.warning" src="<c:url value="/images/img/warning_icon.png"/>" width="13" height="13"/>
                        <img ng-if="signatureCnt.signature.icon == signatureCnt.ICON_TYPE.error" src="<c:url value="/images/img/blocking_icon.png"/>" width="13" height="13"/>
                        <img ng-if="signatureCnt.signature.icon == signatureCnt.ICON_TYPE.valid" src="<c:url value="/images/img/valid.png"/>" width="13" height="13"/>
                    </span>
                    <span class="grid-cell label" translate="{{signatureCnt.signature.label}}"></span>
                    <span class="grid-cell link" ng-if="signatureCnt.certificate != null">
                        <a class="show-certificate-link" ng-click="signatureCnt.showCertificate()" translate="downloadView.displayDataLbl.text"></a>

                        <div class="certificate-dialog" style="display: none">
                            <div>
                                <img ng-if="signatureCnt.dialog.icon == signatureCnt.ICON_TYPE.information" src="<c:url value="/images/icons/information.png"/>" width="16" height="16"/>
                                <img ng-if="signatureCnt.dialog.icon == signatureCnt.ICON_TYPE.question" src="<c:url value="/images/img/help-circle.png"/>" width="16" height="16"/>
                                <div class="caption" translate="{{signatureCnt.dialog.message}}"></div>
                                <div class="grid auto-height">
                                    <span class="grid-row">
                                        <span class="grid-cell">Common Name(CN): </span>
                                        <span class="grid-cell">{{signatureCnt.certificate.commonName}}</span>
                                    </span>
                                    <span class="grid-row">
                                        <span class="grid-cell">Organization Unit(OU): </span>
                                        <span class="grid-cell">{{signatureCnt.certificate.organizationUnit}}</span>
                                    </span>
                                    <span class="grid-row">
                                        <span class="grid-cell">Organization Name(O): </span>
                                        <span class="grid-cell">{{signatureCnt.certificate.organizationName}}</span>
                                    </span>
                                    <span class="grid-row">
                                        <span class="grid-cell">Locality Name(L): </span>
                                        <span class="grid-cell">{{signatureCnt.certificate.locality}}</span>
                                    </span>
                                    <span class="grid-row">
                                        <span class="grid-cell">State Name(ST): </span>
                                        <span class="grid-cell">{{signatureCnt.certificate.state}}</span>
                                    </span>
                                    <span class="grid-row">
                                        <span class="grid-cell">Country(C): </span>
                                        <span class="grid-cell">{{signatureCnt.certificate.country}}</span>
                                    </span>
                                    <span class="grid-row">
                                        <span class="grid-cell">Email(E): </span>
                                        <span class="grid-cell">{{signatureCnt.certificate.email}}</span>
                                    </span>
                                    <span class="grid-row">
                                        <span class="grid-cell">Certificate Expiry Date: </span>
                                        <span class="grid-cell">{{signatureCnt.certificate.expiryDate | date:'dd-M-yyy hh:mm:ss'}}<span class="expired" ng-if="signatureCnt.isCertificateExpired"> (EXPIRED)</span></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <span style="display: none" ng-init="signatureCnt.trustCertificate()"></span>
                    </span>
                </span>
            </div>
        </div>


        <div ng-controller="FileListController" id="fileListCtrl">

            <div class="download-header-container">
                <span class="download-header-select">
                    <label for="select-all">Select all</label>
                    <input id="select-all" type="checkbox" ng-change="selectAll()" ng-model="isAllSelected"/>
                </span>
            </div>

            <div class="attachment-list upload-panel">
                <div class="grid auto-height" ng-if="files != null">
                    <span ng-repeat="file in files track by file.id">
                        <span id="{{file.getAnchorId()}}" class="grid-row auto-height">
                            <span class="grid-cell">
                                <img ng-if="file.isPayload"
                                     ng-class="{inactive: downloadCnt.isDisabled()}"
                                     src="<c:url value="/images/icons/information.png"/>"
                                     title="{{'downloadView.transmissionToolTip.text' | translate}}"
                                     width="13" height="13"/>
                            </span>
                            <span class="grid-cell long-text" title="{{file.fileName}}"
                                  ng-class="{inactive: downloadCnt.isDisabled()}">
                                <%--<span ng-if="downloadCnt.retentionExpired || downloadCnt.isSignatureProvided() || downloadCnt.isEncryptionRequired()">{{file.fileName}}</span>
                                <a ng-if="!downloadCnt.retentionExpired && !downloadCnt.isSignatureProvided() && !downloadCnt.isEncryptionRequired()"
                                   ng-href="<c:url value="/downloadAttachment.do?attachmentId="/>{{file.id}}">{{file.fileName}}</a>--%>
                                <span>{{file.path + file.fileName}}</span>
                            </span>
                            <span class="grid-cell nowrap" ng-class="{inactive: downloadCnt.isDisabled()}">{{file.formattedSize}}</span>
                            <span class="grid-cell">
                                <img ng-if="file.confidential != null && file.confidential == true"
                                     ng-class="{inactive: downloadCnt.isDisabled()}"
                                     src="<c:url value="/images/icons/confidential.png"/>"
                                     title="{{'downloadView.confidentialToolTip.text' | translate}}"
                                     width="13" height="13"/>
                                <img ng-if="file.confidential != null && file.confidential == false"
                                     ng-class="{inactive: downloadCnt.isDisabled()}"
                                     src="<c:url value="/images/icons/non-confidential.png"/>"
                                     title="{{'downloadView.nonConfidentToolTip.text' | translate}}"
                                     width="13" height="13"/>
                            </span>
                            <span class="grid-cell">
                                <img ng-if="file.comment != null"
                                     ng-class="{inactive: downloadCnt.isDisabled()}"
                                     src="<c:url value="/images/icons/comment.png"/>"
                                     ng-click="!downloadCnt.isDisabled() && showComment(file.comment)"
                                     title="{{file.comment}}"
                                     width="13" height="13"/>
                            </span>
                            <span class="grid-cell">
                                <input type="checkbox" ng-model="file.isSelected" ng-change="fileSelected()"/>
                            </span>
                        </span>
                    </span>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-10" ng-if="files != null">
                    <button type="button" class="btn btn-primary btn-outline btn-sm" ng-click="startDownload()" ng-disabled="${retentionMetadata != null && retentionMetadata.retentionExpired}">
                        Download selected files</button>
                </div>
            </div>
        </div>
    </div>

    <input type="hidden" id="icaDetailsEncoded" value="${icaDetails}"/>
    <input type="hidden" id="xmlSignedBundle" value='${xmlSignedBundle}' />
    <input type="hidden" id="xmlExtractedSignature" value='${xmlExtractedSignature}'/>
    <input type="hidden" id="xmlDataToSign" value='${xmlDataToSign}'/>
    <input type="hidden" id="msgSubject" value='${msg.subject}'/>
    <input type="hidden" id="msgCreationDate" value='${msg.createdOn}'/>

</div>

<script type="text/javascript" language="javascript">
  $( document ).ready(function() {
    var isRetentionExpired=angular.element(document.getElementsByClassName("download-panel")).controller().isRetentionExpired();

  });

  var translations =  {
    'downloadView.selectUnselectBtn.text': '<spring:message code="downloadView.selectUnselectBtn.text" text="Select/Unselect all" javaScriptEscape="true"/>',
    'downloadView.messageDlg.title': '<spring:message code="downloadView.messageDlg.title" text="Comment" javaScriptEscape="true"/>',
    'commonView.optionPane.option.ok': '<spring:message code="commonView.optionPane.option.ok" />',
    'downloadView.transmissionToolTip.text': '<spring:message code="downloadView.transmissionToolTip.text" text="Transmission payload file" javaScriptEscape="true"/>',
    'downloadView.confidentialToolTip.text': '<spring:message code="downloadView.confidentialToolTip.text" text="Confidential" javaScriptEscape="true"/>',
    'downloadView.nonConfidentToolTip.text': '<spring:message code="downloadView.nonConfidentToolTip.text" text="Non confidential" javaScriptEscape="true"/>',
    'downloadView.totalProgressDwlLbl.text': '<spring:message code="downloadView.totalProgressDwlLbl.text" text="Downloaded files:" javaScriptEscape="true"/>',
    'downloadView.downloadBtn.text': '<spring:message code="downloadView.downloadBtn.text" text="Open download" javaScriptEscape="true"/>',
    'downloadView.abortBtn.text': '<spring:message code="downloadView.abortBtn.text" text="Abort" javaScriptEscape="true"/>',
    'commonView.file.state.waiting': '<spring:message code="commonView.file.state.waiting" text="Waiting..." javaScriptEscape="true"/>',
    'commonView.file.state.preparing': '<spring:message code="commonView.file.state.preparing" text="Preparing..." javaScriptEscape="true"/>',
    'commonView.file.state.downloading': '<spring:message code="commonView.file.state.downloading" text="Downloading..." javaScriptEscape="true"/>',
    'commonView.file.state.saving': '<spring:message code="commonView.file.state.saving" text="Saving..." javaScriptEscape="true"/>',
    'commonView.file.state.failed': '<spring:message code="commonView.file.state.failed" text="Failed" javaScriptEscape="true"/>',
    'commonView.file.state.done': '<spring:message code="commonView.file.state.done" text="Done" javaScriptEscape="true"/>',
    'commonView.file.state.aborted': '<spring:message code="commonView.file.state.aborted" text="Aborted" javaScriptEscape="true"/>',
    'downloadView.status.abort.text': '<spring:message code="downloadView.status.abort.text" text="Download aborted !" javaScriptEscape="true"/>',
    'downloadView.status.failed.text': '<spring:message code="downloadView.status.failed.text" text="Download failed !" javaScriptEscape="true"/>',
    'downloadView.init.blockingError.text': '<spring:message code="downloadView.init.blockingError.text" text="Interchange Agreement was not successfully loaded. Please contact the System Administrator." javaScriptEscape="true"/>',
    'downloadView.signatureLoading.text': '<spring:message code="downloadView.signatureLoading.text" text="Loading..." javaScriptEscape="true"/>',
    'downloadView.loadSignatureInfo.error': '<spring:message code="downloadView.loadSignatureInfo.error" text="This message did not contain a mandatory signature." javaScriptEscape="true"/>',
    'downloadView.certNotLoadedLbl.text': '<spring:message code="downloadView.certNotLoadedLbl.text" text="Message signed but the certificate could not be loaded" javaScriptEscape="true"/>',
    'downloadView.signatureNotTrustedLbl.text': '<spring:message code="downloadView.signatureNotTrustedLbl.text"/>',
    'downloadView.signatureNotValidatedLbl.text': '<spring:message code="downloadView.signatureNotValidatedLbl.text" text="The message's digital signature was not validated" javaScriptEscape="true"/>',
    'downloadView.signatureTrustedAndValidatedLbl.text': '<spring:message code="downloadView.signatureTrustedAndValidatedLbl.text"/>',
    'downloadView.certificateExpiredLbl.text': '<spring:message code="downloadView.certificateExpiredLbl.text" text="Message signed but the signer certificate is expired" javaScriptEscape="true"/>',
    'downloadView.displayDataLbl.text': '<spring:message code="downloadView.displayDataLbl.text" />',
    'downloadView.trustCertDlg.title': '<spring:message code="downloadView.trustCertDlg.title"/>',
    'downloadVies.dataCertDlg.title': '<spring:message code="downloadVies.dataCertDlg.title"/>',
    'downloadView.certLbl.text': '<spring:message code="downloadView.certLbl.text"/>',
    'downloadView.signatureLbl.text': '<spring:message code="downloadView.signatureLbl.text"/>',
    'commonView.trustOption.yes': '<spring:message code="commonView.trustOption.yes" />',
    'commonView.trustOption.no': '<spring:message code="commonView.trustOption.no" />',
    'commonView.trustOption.always': '<spring:message code="commonView.trustOption.always" />'
  };

  angular.module('attachmentDownloadModule', ['ngCookies', 'ngSanitize', 'pascalprecht.translate', 'etrustex.message.download', 'etrustex.message.signature', 'etrustex.message.file-list'])
    .config(['$compileProvider', function ($compileProvider) {
      $compileProvider.debugInfoEnabled(false);
    }])
    .config(['$translateProvider', function ($translateProvider) {
      $translateProvider
        .translations('${localeCode}', translations)
        .preferredLanguage('${localeCode}')
        .useSanitizeValueStrategy(null);
    }])
    .factory('DataProvider', function() {
      var hasData = function(data) {
        return data != undefined && data != null && data != '';
      };

      return {
        data: {
          retentionExpired: '${retentionMetadata.retentionExpired}',
          retentionExpiredMessage: '${retentionExpiredMessage}',
          icaDetails: '${icaDetails}',
          files: '${encodedFileElements}',
          signature: { //TODO: add signedData and signature and whole signed bundle
            isProvided: '${isSignatureProvided}',
            isValid: '${isSignatureValid}',
            certificate: '${certificate}',
            xmlSignedBundle: '${xmlSignedBundle}',
            xmlDataToSign: '${xmlDataToSign}',
            xmlExtractedSignature: '${xmlExtractedSignature}',
          }
        },
        local: {
          isCertificateTrusted: false
        },
        hasData: hasData,
        extractData: function(encodedData) {
          var result = null;
          if (hasData(encodedData)) {
            result = JSON.parse(b64DecodeUnicode(encodedData));
            if ($.isEmptyObject(result)) {
              result = null;
            }
          }

          return result;
        }
      };
    })
    .decorator("$xhrFactory", [
      "$delegate", "$injector",
      function($delegate, $injector) {
        return function(method, url) {
          var xhr = $delegate(method, url);
          var $http = $injector.get("$http");
          var callConfig = $http.pendingRequests[$http.pendingRequests.length - 1];
          if (angular.isFunction(callConfig.onProgress))
            xhr.addEventListener("progress", callConfig.onProgress);
          return xhr;
        };
      }
    ]);
</script>
