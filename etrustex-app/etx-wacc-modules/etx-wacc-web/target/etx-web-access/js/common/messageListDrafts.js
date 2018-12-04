function deleteDraft(messageId) {
    var deleteDraftMsg = messages["message.deleteDraftMessage"];
    var r = confirm(deleteDraftMsg);
    if (r == true) {
        document.forms['deleteDraftMessage-' + messageId].submit();
    }
}
