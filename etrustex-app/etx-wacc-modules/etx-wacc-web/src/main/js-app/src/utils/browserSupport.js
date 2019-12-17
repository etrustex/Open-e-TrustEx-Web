// Edge 20+
export const isEdge = !isIE && !!window.StyleMedia

// Internet Explorer 6-11. Nooooooooooo !!!
export const isIE = /*@cc_on!@*/false || !!document.documentMode

// Opera 8.0+
export const isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0

// Firefox 1.0+
export const isFirefox = typeof InstallTrigger !== 'undefined'

// Safari 3.0+ "[object HTMLElementConstructor]"
export const isSafari = /constructor/i.test(window.HTMLElement) || ((p) => {
  return p.toString() === "[object SafariRemoteNotification]"
})(!window['safari'] || (typeof safari !== 'undefined' && safari.pushNotification))

// Chrome 1+
export const isChrome = !!window.chrome && !!window.chrome.webstore

export const isWritableStreamsSupported = () => {
  let supported = false

  if (isChrome) {
    supported = true
  } else {
    supported = false
  }

  return supported
}