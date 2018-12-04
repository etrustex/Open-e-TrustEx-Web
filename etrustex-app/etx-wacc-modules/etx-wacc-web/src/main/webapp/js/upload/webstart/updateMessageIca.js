var lastIca = null;
var currentIca = null;



function loadMessageIca(ica) {
    if (ica != "") {
        ica = JSON.parse(ica);
        lastIca = ica;
        currentIca = ica;
        enableUploadButton();
    }
}

function enableUploadButton() {
    $('#openUploadJNLP').removeClass("btn btn-primary.disabled");
    $('#openUploadJNLP').addClass("btn btn-primary");
    $('#openUploadJNLP').unbind('click');
    $('#openUploadJNLP').click(donwloadJNLP);
    $('#uploadFiles').click();
}

function disableUploadButton() {
    $('#openUploadJNLP').removeClass("btn btn-primary");
    $('#openUploadJNLP').addClass("btn btn-primary.disabled");
    $('#openUploadJNLP').unbind('click');
}

function checkLoadedIca(ica) {
    //TODO: Check current and last ICA
    lastIca = currentIca;
    currentIca = ica;
}