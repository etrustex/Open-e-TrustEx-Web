export const PromptDialogBtn = Object.freeze({
  OK: 'Ok',
  CANCEL: 'Cancel',
  YES: 'Yes',
  NO: 'No',
})

export function plainDialogBody(message) {
  return '<div><h6>' + message + '</h6></div>'
}

export function textAreaBody(text, textId) {
  return '<textarea rows="4" cols="50" id=' + textId + '>' + text + '</textarea>'
}

export function PromptDialog(options) {
  let dialogDiv = $('<div></div>').appendTo('body').html(options.body.replace('\\r\\n','<br>'))

  return new Promise( (resolve) => {

    let actions = []

    options.buttons.forEach( (button) => {
      actions.push({
        text: button,
        click: () => {
          if(button == PromptDialogBtn.OK && options.beforeOkFn) {
            options.beforeOkFn()
          } else if (button == PromptDialogBtn.CANCEL && options.beforeCancelFn) {
            options.beforeCancelFn()
          }
          resolve(button)
          console.log(button + ' pressed!')
          dialogDiv.dialog("close")
        }
      })
    })

    dialogDiv.dialog({
        dialogClass: "no-close",
        modal: true, title: options.title, zIndex: 10000, autoOpen: true,
        width: options.width ? options.width : 'auto', resizable: true,
        buttons: actions,
        close: function () {
          console.log('closing dialog')
          dialogDiv.dialog('destroy').remove()
        }
      })
  } )
}