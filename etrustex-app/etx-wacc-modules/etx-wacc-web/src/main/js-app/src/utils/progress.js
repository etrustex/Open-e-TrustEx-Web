export const ProgressBar = Object.freeze({
    TRANSFER: Symbol.for("transfer"),
    PROCESSING: Symbol.for("processing"),
    ZIPPING: Symbol.for("zip"),
    ADDING: Symbol.for("add")
})

export const ProgressMode = Object.freeze({
    DOWNLOAD: Symbol(),
    UPLOAD: Symbol(),
    ADD: Symbol()
})


export class Progress {
    constructor(mode = ProgressMode.UPLOAD) {
        // this.timer

        switch (mode) {
            case ProgressMode.DOWNLOAD:
                $.blockUI({
                    message:
                    '<div class="row">' +
                      '<span class="progress-text">Downloading</span><div class="progress">' +
                      '<div id="progress-transfer" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
                      '</div>' +
                      '<span class="progress-text">Verifying</span><div class="progress">' +
                      '<div id="progress-processing" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
                      '</div>' +
                      '<span class="progress-text">Zipping</span><div class="progress">' +
                      '<div id="progress-zip" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
                      '</div>' +
                    '</div>' +
                    '<div class="row">' +
                      '<button id="cancelDownloadBtn" type="button" class="btn btn-danger cancel" >' +
                        '<i class="glyphicon glyphicon-ban-circle"></i><span>Cancel</span>' +
                      '</button>' +
                    '</div>'
                })
                break;

            case ProgressMode.UPLOAD:
                $.blockUI({
                    message:
                    '<div class="row">' +
                      '<span class="progress-text">Preparing</span><div class="progress">' +
                      '<div id="progress-processing" class="progress-bar progress-bar-striped progress-bar-animated bg-info" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
                      '</div>' +
                      '<span class="progress-text">Uploading</span><div class="progress">' +
                      '<div id="progress-transfer" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
                      '</div>' +
                    '</div>' +
                    '<div class="row">' +
                      '<button id="cancelUploadBtn" type="button" class="btn btn-danger cancel" >'+
                      '<i class="glyphicon glyphicon-ban-circle"></i><span>Cancel</span>' +
                      '</button>' +
                    '</div>'
                })
                break;

          case ProgressMode.ADD:
            $.blockUI({
              message:
              '<span class="progress-text">Uploading</span><div class="progress">' +
              '<div id="progress-add" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 1%"></div>' +
              '</div>'
            })
            break;
        }

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