export const ProgressBar = Object.freeze({
  TRANSFER: Symbol.for("transfer"),
  PROCESSING: Symbol.for("processing"),
  ZIPPING: Symbol.for("zip"),
  ADDING: Symbol.for("add")
})

export const ProgressMode = Object.freeze({
  DOWNLOAD: Symbol(),
  UPLOAD: Symbol(),
  // ADD: Symbol(),
  CANCEL: Symbol()
})


export class Progress {
  constructor(mode = ProgressMode.UPLOAD, zipLabel = 'Zipping') {
    // this.timer

    switch (mode) {
      case ProgressMode.DOWNLOAD:
        $.blockUI({
          message:
          '<div class="flex-container">' +
            '<div class="flex-text-column">' +
              '<div class="progress-text">' + messages[GlobalAccess.i18.I18Label.LABEL_DOWNLOADING] + '</div>' +
              '<div class="progress-text">' + messages[GlobalAccess.i18.I18Label.LABEL_VERIFYING]+ '</div>' +
              `<div class="progress-text" id="zipProgressSpan">${zipLabel}</div>` +
            '</div>' +
            '<div class="flex-progress-column">' +
              '<div class="progress">' +
                '<div id="progress-transfer" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
              '</div>' +
              '<div class="progress">' +
                '<div id="progress-processing" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
              '</div>' +
              '<div class="progress">' +
                '<div id="progress-zip" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
              '</div>' +
            '</div>' +
          '</div>' +
          '<div class="row">' +
            '<button id="cancelDownloadBtn" type="button" class="btn btn-danger cancel" >' +
              '<i class="glyphicon glyphicon-ban-circle"></i><span>' + messages[GlobalAccess.i18.I18Label.LABEL_CANCEL] + '</span>' +
            '</button>' +
          '</div>'
        })
        break;

      case ProgressMode.UPLOAD:
        $.blockUI({
          message:
          '<div class="flex-container">' +
            '<div class="flex-text-column">' +
              '<div class="progress-text">' + messages[GlobalAccess.i18.I18Label.LABEL_PREPARING] + '</div>' +
              '<div class="progress-text">' + messages[GlobalAccess.i18.I18Label.LABEL_UPLOADING] + '</div>' +
            '</div>' +
            '<div class="flex-progress-column">' +
              '<div class="progress">' +
                '<div id="progress-processing" class="progress-bar progress-bar-striped progress-bar-animated bg-info" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
              '</div>' +
              '<div class="progress">' +
                '<div id="progress-transfer" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
              '</div>' +
            '</div>' +
          '</div>' +
          '<div class="row">' +
          '<button id="cancelUploadBtn" type="button" class="btn btn-danger cancel" >' +
          '<i class="glyphicon glyphicon-ban-circle"></i><span>' + messages[GlobalAccess.i18.I18Label.LABEL_CANCEL] + '</span>' +
          '</button>' +
          '</div>'
        })
        break;

      // case ProgressMode.ADD:
      //   $.blockUI({
      //     message:
      //     '<div class="flex-container">' +
      //       '<div class="flex-text-column">' +
      //         '<div class="progress-text">' + messages[GlobalAccess.i18.I18Label.LABEL_UPLOADING] + '</div>'+
      //       '</div>' +
      //       '<div class="lex-progress-column">' +
      //         '<div class="progress">' +
      //           '<div id="progress-add" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
      //         '</div>' +
      //       '</div>' +
      //     '</div>'
      //   })
      //   break;

      case ProgressMode.CANCEL:
        $.blockUI({
          message:
          '<div class="flex-container">' +
            '<div class="flex-text-column">' +
              '<div class="progress-text">' + messages[GlobalAccess.i18.I18Label.LABEL_CANCELLING] + '</div>'+
            '</div>' +
            '<div class="flex-progress-column">' +
              '<div class="progress">' +
                '<div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>' +
              '</div>' +
            '</div>' +
          '</div>'
          // '<span class="progress-text">' + messages[GlobalAccess.i18.I18Label.LABEL_CANCELLING] + '</span><div class="progress">' +
          // '<div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>' +
          // '</div>'
        })
        break;
    }

  }

  changeZipLabel(newLabel) {
    document.getElementById('zipProgressSpan').innerHTML = newLabel
  }

  update(bar, value) {
    if (value > 1) {
      value = value.toFixed(0)
      // clearTimeout(this.timer)
      // this.timer = setTimeout(() => $('#progress-' + Symbol.keyFor(bar)).attr('aria-valuenow', value).css('width', value+'%').text(value + '% '), 100)
      $('#progress-' + Symbol.keyFor(bar)).attr('aria-valuenow', value).css('width', value + '%').text(value + '% ')
    }
  }

  done() {
    $.unblockUI()
  }

}