import {plainDialogBody, PromptDialog, PromptDialogBtn} from "../utils/promptDialog";
import {Progress, ProgressBar, ProgressMode} from "../utils/progress";
import {DownloadThread} from "./downloadThread";
import {PromisePool} from "es6-promise-pool";
import {saveAs} from 'file-saver';
import {I18Label, I18Message} from "../common/i18";
import {FileStatus} from "../common/fileStatus";
import JSZip from "jszip"
import {formatFileSize} from "../utils/formatting";
import {displayError, cleanErrorMessages} from "../common/common"
import {isEdge, isIE} from "../utils/browserSupport";
import {arrangeIntoTree, treeFolderStructure} from "../utils/fsTreeUtils";

FileListController.$inject = ['$scope', 'DataProvider', '$translate'];

function FileListController($scope, DataProvider, $translate) {
  $scope.isAllSelected = true;
  $scope.isHideSuccessful = false;
  $scope.treeFolderStructure = treeFolderStructure


  $scope.selectAll = function () {
    angular.forEach($scope.files, function (file) {
      file.isSelected = file.isVisible && $scope.isAllSelected;
    });
  };

  $scope.unselectHidden = function () {
    if ($scope.isHideSuccessful) {
      angular.forEach($scope.files, function (file) {
        file.isVisible = FileStatus.SUCCESS != file.status;
        file.isSelected = file.isSelected && FileStatus.SUCCESS != file.status;
        if (!file.isVisible)  $('#'+file.getAnchorId()).css('display','none')
        if (!file.isVisible && $('#'+file.getAnchorId())[0] == undefined) $('#root'+file.getAnchorId()).css('display','none')
      });

      $scope.isAllSelected = !$scope.files.some((f) => !f.isSelected && f.isVisible)
    } else {
      $scope.isAllSelected = !$scope.files.some((f) => !f.isSelected)

      angular.forEach($scope.files, function (file) {
        file.isVisible = true;
        $('#'+file.getAnchorId()).css('display','')
        $('#root'+file.getAnchorId()).css('display','')
      });
    }
  }

  $scope.fileSelected = function () {
    $scope.isAllSelected = $scope.files.every(function (file) {
      return file.isSelected;
    });
  };

  let init = function () {
    $scope.files = DataProvider.extractData(DataProvider.data.files);

    DataProvider.data.files = null;

    if ($scope.files != null) {
      angular.forEach($scope.files, function (file) {
        file.isSelected = true;
        file.isVisible = true;
        file.isPayload = DataProvider.hasData(file.isPayload) && file.isPayload == 'true';
        file.comment = DataProvider.hasData(file.comment) ? file.comment : null;
        file.getAnchorId = function () {
          return 'F-' + file.id;
        };
        arrangeIntoTree(file)
      })

    }

    initDigestsAndEcryptedBitsFromSignedBundle()

    let icaDetails = JSON.parse(b64DecodeUnicode($('#icaDetailsEncoded').val()))

    if(typeof forceDownloadAsCleartext !== 'undefined' && forceDownloadAsCleartext) {
      icaDetails.isEncryptionRequired = false
    }

    if (icaDetails.isEncryptionRequired) {
      $('#p12-input-container').show()
      $('#p12-input').collapse('show')
    }
  }

  $scope.showComment = function (comment) {
    let dialog = angular.element("<textarea class='comment-box' readonly rows='4' cols='50'>" + comment + "</textarea>");

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

  $scope.countSuccessful = function () {
    return $scope.files.map((file) => { return file.status == FileStatus.SUCCESS ? 1 : 0} ).reduce( (prevVal, current) => { return prevVal + current})
  }

  $scope.totalSizeToDownload = function () {
    let totalSize =  $scope.files.map((file) => { return file.fileSize } ).reduce( (prevVal, current) => { return prevVal + current})
    return formatFileSize(totalSize)
  }

  $scope.startDownload = function () {
    $scope.successfullyDownloaded = 0;
    cleanErrorMessages()
    let selectedFiles = $scope.files.filter(file => file.isSelected)
    if (selectedFiles.length == 0) {
      PromptDialog({
        title: messages[I18Label.LABEL_NO_FILE_SELECTED],
        body: plainDialogBody(messages[I18Message.MESSAGE_SELECT_AT_LEAST_ONE_FILE]),
        buttons: [PromptDialogBtn.OK]
      })
      return
    }

    let icaDetails = JSON.parse(b64DecodeUnicode($('#icaDetailsEncoded').val()));

    if(typeof forceDownloadAsCleartext !== 'undefined' && forceDownloadAsCleartext) {
      icaDetails.isEncryptionRequired = false
    }

    if (icaDetails.isEncryptionRequired) {
      isReadyForDownload()
        .then(() => {
          generateZip({privateKey: GlobalAccess.crypto.getSelectedIdentity().privateKey, selectedFiles: selectedFiles});
        })
        .catch((validationErrors) => {
          validationErrors.forEach((msgErrorCode) => {
            displayError(msgErrorCode)
          })
        })
    } else {
      generateZip({selectedFiles: selectedFiles});
    }
  };

  /*
   * Working POCS Using StreamSaver.js
   * It uses a ServiceWorker and a mitm.html (Man in the middle)
   * If we want to use it in production we should provide our own implementation
   * of the SW and mitm.html
   */
  let saveFileStreaming = (options) => {
    options.selectedFiles.forEach((file) => {
      let
        request = new Request('downloadAttachment.do?attachmentId=' + file.id, {
          method: 'GET',
          credentials: 'include',
          cache: 'reload'
        }),
        fileStream = streamSaver.createWriteStream(file.fileName),
        writer = fileStream.getWriter()

      fetch(request).then(res => {
        // Later you will be able to just simply do
        // res.body.pipeTo(fileStream)
        let reader = res.body.getReader()
        let pump = () => reader.read()
          .then((res) => {
            if (res.done) {
              // close the stream so we stop writing
              console.log('done')
              writer.close()
            } else {
              // Write one chunk, then get the next one
              writer.write(res.value).then(pump)
            }
          })
        // Start the reader
        pump()
      })
    })
  }

  let generateZipStreaming = (options) => {
    DownloadThread.abortSignal = false

    let
      privateKey = options.privateKey,
      downloadedFileSize = 0,
      totalFilesSize = options.selectedFiles.map(file => file.fileSize).reduce((sum, fileSize) => sum + fileSize),
      readBytes = 0,
      progress = new Progress(ProgressMode.DOWNLOAD),
      writer = streamSaver.createWriteStream("testLargeFile.zip").getWriter()


    const abortBtn = document.querySelector('#cancelDownloadBtn');
    abortBtn.addEventListener('click', () => {
      // controller.abort();
      DownloadThread.abortSignal = true
      console.log('Download aborted');
      progress = new Progress(ProgressMode.CANCEL)
    });

    options.selectedFiles.forEach((currentFile) => {
      let zip = new JSZip()
      let downloadThread = new DownloadThread({
        file: currentFile,
        zip: zip,
        privateKey: privateKey,
        targetContentChecksums: contentChecksums
      })

      downloadThread.on('progress-processing', (data) => progress.update(ProgressBar.PROCESSING, (readBytes += data) / totalFilesSize * 100))
      downloadThread.on('progress-transfer', (data) => progress.update(ProgressBar.TRANSFER, (downloadedFileSize += data) / totalFilesSize * 100))
      downloadThread.on('transfer-failure', (message) => {
        if (!DownloadThread.abortSignal) { //all the downloads already started will fail, we cancel download and show the dialog only once.
          document.getElementById('cancelDownloadBtn').click()
          PromptDialog({
            title: messages[I18Label.LABEL_WRONG_CERTIFICATE],
            body: plainDialogBody(message),
            buttons: [PromptDialogBtn.OK]
          })
        }
      })
      downloadThread.on('status-update', (file) => {
        file.isVisible = !($scope.isHideSuccessful&&(file.status==FileStatus.SUCCESS))
        $scope.$apply()
        refreshDownloadedDisplay()
      })

      zip.generateInternalStream({type: "uint8array", streamFiles: true})
        .on('data', (data) => {
          writer.write(data)

          // console.log("progression: " + metadata.percent.toFixed(2) + " %");
          // progress.update(ProgressBar.ZIPPING, (sizeBefore + currentZipSize / 100 * metadata.percent) / totalFilesSize * 100)
        })
        .on('error', err => console.error(err))
        .on('end', () => {
          writer.close()
          progress.done()
        })
        .resume()
    })
  }
  /* End of Working POCS */


  /*
     * The final version of AbortController has been added to the DOM specification. See https://dom.spec.whatwg.org/#aborting-ongoing-activities
     * Check the Browser compatibility table https://developer.mozilla.org/en-US/docs/Web/API/AbortController/abort#Browser_compatibility
     */
  // const controller = new AbortController()
  // const signal = controller.signal

  let poppedFiles = []
  let generateZip = (options) => {
    DownloadThread.abortSignal = false

    let
      privateKey = options.privateKey,
      downloadedFileSize = 0,
      totalFilesSize = options.selectedFiles.map(file => file.fileSize).reduce((sum, fileSize) => sum + fileSize),
      readBytes = 0,
      currentZipSize = 0,
      zipSizeLimit = parseInt(document.getElementById('zipSplitSize').value),
      poolsToStart = [],
      currentPool = {zip: new JSZip(), downloadThreads: []},
      progress = new Progress(ProgressMode.DOWNLOAD)

    poolsToStart.push(currentPool)

    const abortBtn = document.querySelector('#cancelDownloadBtn');
    abortBtn.addEventListener('click', () => {
      // controller.abort();
      DownloadThread.abortSignal = true
      console.log('Download aborted');

      progress = new Progress(ProgressMode.CANCEL)

    });

    options.selectedFiles.forEach((currentFile) => {
      let fileSize = currentFile.fileSize

      if (currentZipSize == 0 || currentZipSize + fileSize <= zipSizeLimit) {
        currentZipSize += fileSize
      } else {
        currentPool = {zip: new JSZip(), downloadThreads: []}
        currentZipSize = fileSize
        poolsToStart.push(currentPool)
      }

      let downloadThread = new DownloadThread({
        file: currentFile,
        zip: currentPool.zip,
        privateKey: privateKey,
        digestsAndEncryptedBits: digestsAndEncryptedBits
      })

      downloadThread.on('progress-processing', (data) => progress.update(ProgressBar.PROCESSING, (readBytes += data) / totalFilesSize * 100))
      downloadThread.on('progress-transfer', (data) => progress.update(ProgressBar.TRANSFER, (downloadedFileSize += data) / totalFilesSize * 100))
      downloadThread.on('transfer-failure', (message) => {
        if (!DownloadThread.abortSignal) { //all the downloads already started will fail, we cancel download and show the dialog only once.
          document.getElementById('cancelDownloadBtn').click()
          PromptDialog({
            title: messages[I18Label.LABEL_WRONG_CERTIFICATE],
            body: plainDialogBody(message),
            buttons: [PromptDialogBtn.OK]
          })
        }
      })
      downloadThread.on('status-update', (file) => {
        file.isVisible = !($scope.isHideSuccessful&&(file.status==FileStatus.SUCCESS))
        $scope.$apply()
        refreshDownloadedDisplay()
      })
      currentPool.downloadThreads.push(downloadThread)
    })


    function getZipLabel(current, total) {
      return 'Zipping' + (total > 1 ? ` ${current}/${total}` : '')
    }

    function runPools(index) {
      let getNextAttachment = () => {
        if (DownloadThread.abortSignal || !poolsToStart[index].downloadThreads.length) return null
        let downloadThread = poolsToStart[index].downloadThreads.pop()
        poppedFiles.push(downloadThread.file)
        return downloadThread.fetchInputStream()
      }
      let pool = new PromisePool(getNextAttachment, 5)

      let poolPromise = pool.start()
      progress.changeZipLabel(getZipLabel(index + 1, poolsToStart.length))

      let sizeBefore = downloadedFileSize

      const runNextPoolOrFinish = () => {
        poppedFiles = []
        if (poolsToStart.length > index + 1) {
          runPools(index + 1)
        } else {
          progress.done()
        }
      }

      poolPromise.then(() => {
        if (DownloadThread.abortSignal) {
          for(let i=index; i < poolsToStart.length; i++) {
            poolsToStart[i].downloadThreads.forEach(downloadThread => downloadThread.file.status = FileStatus.CANCELLED)
          }
          poppedFiles.forEach(file => {
            file.status = FileStatus.CANCELLED
            file.isVisible = true
          })
          poppedFiles = []
          $scope.$apply()
          progress.done()
        } else {
          let currentZipSize = downloadedFileSize - sizeBefore
          if (Object.keys(poolsToStart[index].zip.files).length == 0) {
            if (poolsToStart.length > index + 1) {
              progress.update(ProgressBar.ZIPPING, (sizeBefore + currentZipSize) / totalFilesSize * 100)
              runPools(index + 1)
            } else {
              progress.done()
            }
          } else { // we have files in the current zip
            poolsToStart[index].zip.generateAsync({type: "blob", streamFiles: true}, function updateCallback(metadata) {
              console.log("progression: " + metadata.percent.toFixed(2) + " %");
              progress.update(ProgressBar.ZIPPING, (sizeBefore + currentZipSize / 100 * metadata.percent) / totalFilesSize * 100)
            }).then((content) => {
              let fileSaver = saveAs(content, getZipName({index: index + 1, overall: poolsToStart.length}))

              if(isEdge || isIE) {
                setTimeout(() => runNextPoolOrFinish(), 500);
              } else {
                fileSaver.onwriteend = () => runNextPoolOrFinish()
              }
            })
          }
        }
        poolsToStart[index] = null
      }).catch(() => {
        progress.done()
      })
    }

    if (poolsToStart.length > 0) {
      runPools(0)
    }
  }

  let digestsAndEncryptedBits = new Map()
  function initDigestsAndEcryptedBitsFromSignedBundle () {
    let signedBundleStr = atob(document.getElementById('xmlDataToSign').value)

    if(signedBundleStr.trim() != '' ) {
      let parser = new DOMParser();
      let signedBundleXml = parser.parseFromString(signedBundleStr,"text/xml");
      [...signedBundleXml.getElementsByTagName('document')].forEach((doc) => {
        let docId = doc.getElementsByTagName('id')[0].textContent
        let digestMethod = doc.getElementsByTagName('digestMethod')[0].textContent
        let digestValue = doc.getElementsByTagName('digestValue')[0].textContent
        let encryptedKey = doc.getElementsByTagName('encryptedKey')
          if(encryptedKey.length>0) { // non encrypted transmissions will have no encryptedKey filed in the signedBundle
            encryptedKey = encryptedKey[0].textContent
          }
        digestsAndEncryptedBits.set(docId, {digestValue: digestValue, digestMethod: digestMethod, encryptedKey: encryptedKey})
      })
    }
  }

  function getZipName(options) {
    let subject = $("#msgSubject").val().replace(/\s+/g, '_') /* replacing sequences of white spaces with one underscore */
      .slice(0, 200) /* we keep the first 200 characters of the subject for the zip name */;
    let createDate = $("#msgCreationDate").val()
      .replace(/\..*$/, '')/* removes trailing part after the seconds */
      .replace(' ', '_') /* removes the space between the date and the time part */
      .replace(new RegExp(':', 'g'), '-')
    /* colons in the time part would be replaced with underscores so we change them with dashes  */

    if (options.overall && options.overall > 1) {
      return `${createDate}_${subject}_${options.index}-of-${options.overall}.zip`
    } else {
      return `${createDate}_${subject}.zip`
    }
  }

  function isReadyForDownload() {
    let validationErrors = []
    return new Promise((resolve, reject) => {
      let passed = true

      let selectedIdentity = GlobalAccess.crypto.getSelectedIdentity()
      let identitySelectionValidationErrors = GlobalAccess.crypto.validateIdentitySelection(selectedIdentity, false)
      if(identitySelectionValidationErrors.length > 0) {
        passed = false
        validationErrors = validationErrors.concat(identitySelectionValidationErrors)
      }

      if (passed) {
        resolve()
      } else {
        reject(validationErrors)
      }
    })

  }


  let refreshDownloadedDisplay = () => {
    //document.getElementById('filesSuccessfullyDownloaded').show
    $('#filesSuccessfullyDownloaded').show()
  }

  init();


}

angular.module('etrustex.message.file-list', [])
  .controller('FileListController', FileListController);
