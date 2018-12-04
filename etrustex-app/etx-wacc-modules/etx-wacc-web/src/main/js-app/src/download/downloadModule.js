import { plainDialogBody, PromptDialogBtn, PromptDialog, inputPasswordBody } from "../utils/promptDialog";
import { P12 } from "../utils/p12";
import { ProgressMode, Progress, ProgressBar } from "../utils/progress";
import { DownloadThread } from "./downloadThread";
import { PromisePool } from "es6-promise-pool";
import { saveAs } from 'file-saver';
/*import 'promise-polyfill/src/polyfill';*/

FileListController.$inject = ['$scope', 'DataProvider', '$translate'];

function FileListController($scope, DataProvider, $translate) {
  $scope.isAllSelected = true;

  $scope.selectAll = function () {
    angular.forEach($scope.files, function (file) {
      file.isSelected = $scope.isAllSelected;
    });
  };

  $scope.fileSelected = function () {
    $scope.isAllSelected = $scope.files.every(function (file) {
      return file.isSelected;
    });
  };


  var init = function () {
    $scope.files = DataProvider.extractData(DataProvider.data.files);

    DataProvider.data.files = null;

    if ($scope.files != null) {
      angular.forEach($scope.files, function (file) {
        file.isSelected = true;
        file.isPayload = DataProvider.hasData(file.isPayload) && file.isPayload == 'true';
        file.comment = DataProvider.hasData(file.comment) ? file.comment : null;
        file.getAnchorId = function () {
          return 'F-' + file.id;
        };
      })
    }

    initContentChecksumsFromSignedBundle()
  }

  $scope.showComment = function (comment) {
    var dialog = angular.element("<textarea class='comment-box' readonly rows='4' cols='50'>" + comment + "</textarea>");

    $(dialog)
      .dialog({
        resizable: false,
        autoOpen: false,
        modal: true,
        position: {my: 'center', at: 'center', of: window},
        title: $translate.instant('downloadView.messageDlg.title'),
        height: 'auto',
        width: 'auto',
        buttons: [
          {
            text: $translate.instant('commonView.optionPane.option.ok'),
            click: function () {
              $(this).dialog('close');
              $(this).remove();
            }
          }
        ]
      })
      .dialog('open');
  }

  $scope.startDownload = function () {

    let selectedFiles = $scope.files.filter(file => file.isSelected)
    if(selectedFiles.length == 0) {
      PromptDialog({
        title: 'No file selected',
        body: plainDialogBody('Please select at least one file to download!'),
        buttons: [ PromptDialogBtn.OK ]
      })
      return
    }

    let zip = new JSZip();
    let icaDetails = JSON.parse(b64DecodeUnicode($('#icaDetailsEncoded').val()));

    let openP12AndDownload = (alert) => {

      let body = inputPasswordBody({alert: alert, message: 'Please insert password and provide the p12 file for decoding', passwordId: 'certificatePassword'})
      PromptDialog(
        {
          title: 'Decryption needed!',
          body: body,
          buttons: [ PromptDialogBtn.OK, PromptDialogBtn.CANCEL ],
          beforeOkFn: () => {
            let password = $('#certificatePassword').val()
            new P12({password: password}).then(function (keysAndCert) {
              downloadSelectedAttachments({zip: zip, privateKey: keysAndCert.keys.privateKey}, selectedFiles);
            }).catch(()=> {
              openP12AndDownload("Wrong password!")
            })
          }
        })
    }

    if (icaDetails.isEncryptionRequired) {
      openP12AndDownload(false)
    } else {
      downloadSelectedAttachments({zip: zip}, selectedFiles);
    }
  };

  /*
     * The final version of AbortController has been added to the DOM specification. See https://dom.spec.whatwg.org/#aborting-ongoing-activities
     * Check the Browser compatibility table https://developer.mozilla.org/en-US/docs/Web/API/AbortController/abort#Browser_compatibility
     */
  // const controller = new AbortController()
  // const signal = controller.signal

  let downloadSelectedAttachments = (options, selectedFiles) => {
    DownloadThread.abortSignal = false

    let progress = new Progress(ProgressMode.DOWNLOAD),
      zip = options.zip,
      privateKey = options.privateKey,
      downloadedFileSize = 0,
      //selectedFiles = $scope.files.filter(file => file.isSelected),
      totalFilesSize = selectedFiles.map(file => file.fileSize).reduce((sum, fileSize) => sum + fileSize),
      readBytes = 0

    const abortBtn = document.querySelector('#cancelDownloadBtn');
    abortBtn.addEventListener('click', () => {
      // controller.abort();
      DownloadThread.abortSignal = true
      console.log('Download aborted');
      $('#cancelDownloadBtn').prop('disabled', true);
    });

    let getNextAttachment = () => {
      if (DownloadThread.abortSignal || !selectedFiles.length) {
        return null
      } else {
        let downloadThread = new DownloadThread({
          fileId: selectedFiles.pop().id,
          zip: zip,
          privateKey: privateKey,
          targetContentChecksums: contentChecksums
        })

        downloadThread.on('progress-processing', (data) => progress.update(ProgressBar.PROCESSING, (readBytes += data) / totalFilesSize * 100))
        downloadThread.on('progress-transfer', (data) => progress.update(ProgressBar.TRANSFER, (downloadedFileSize += data) / totalFilesSize * 100))
        downloadThread.on('transfer-failure', (message) => {
          if(!DownloadThread.abortSignal) { //all the downloads already started will fail, we cancel download and show the dialog only once.
            document.getElementById('cancelDownloadBtn').click()
            PromptDialog({title: 'Wrong p12!', body: plainDialogBody(message), buttons: [ PromptDialogBtn.OK ]})
          }
        })

        return downloadThread.fetchInputStream()
      }

    }

  // Create a pool.
    let pool = new PromisePool(getNextAttachment, 5)

    let poolPromise = pool.start()

    poolPromise.then(() => {
      if (!DownloadThread.abortSignal) {
        zip.generateAsync({type: "blob", streamFiles: true}, function updateCallback(metadata) {
          console.log("progression: " + metadata.percent.toFixed(2) + " %");
          progress.update(ProgressBar.ZIPPING, metadata.percent);
          if (metadata.currentFile) {
            console.log(metadata.currentFile);
          }
        }).then(content => {
          saveAs(content, getZipName());
          progress.done()
        })
      } else {
        progress.done()
      }
    }, () => {
      console.log('Some promise rejected')
      progress.done()
    })
  }

  let contentChecksums = new Map()

  function initContentChecksumsFromSignedBundle () {
    let signedBundleStr = document.getElementById('xmlDataToSign').value
    if(signedBundleStr.trim() != '' ) {
      let parser = new DOMParser();
      let signedBundleXml = parser.parseFromString(signedBundleStr,"text/xml");
      [...signedBundleXml.getElementsByTagName('document')].forEach((doc) => {
        let docId = doc.getElementsByTagName('id')[0].textContent
        let digestValue = doc.getElementsByTagName('digestValue')[0].textContent
        contentChecksums.set(docId, digestValue)
      })
    }
  }

  function getZipName() {
    let subject = $("#msgSubject").val().replace(/\s+/g, '_') /* replacing sequences of white spaces with one underscore */
      .slice(0,200) /* we keep the first 200 characters of the subject for the zip name */ ;
    let createDate = $("#msgCreationDate").val()
      .replace(/\..*$/, '')/* removes trailing part after the seconds */
      .replace(' ', '_') /* removes the space between the date and the time part */
      .replace(new RegExp(':', 'g'), '-') /* colons in the time part would be replaced with underscores so we change them with dashes  */

    return createDate + '_'  + subject  + '.zip';
  }

  init();
}

angular.module('etrustex.message.file-list', [])
  .controller('FileListController', FileListController);
