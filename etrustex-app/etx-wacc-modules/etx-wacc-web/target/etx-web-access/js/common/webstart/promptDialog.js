PromptDialog = function (title, message) {
  var defer = $.Deferred();

  $('<div></div>').appendTo('body')
    .html('<div><h6>' + message + '</h6></div>')
    .dialog({
      modal: true, title: title, zIndex: 10000, autoOpen: true,
      width: 'auto', resizable: false,
      buttons: {
        "Ok": function () {
          defer.resolve(true);
          $(this).dialog("close");
        },
        "Cancel": function () {
          defer.resolve(false);
          $(this).dialog("close");
        }
      },
      close: function () {
        $(this).dialog('destroy').remove()
      }
    });

  return defer.promise();
};