export let hexToUtf8 = (hex) => {
  if(!hex) return ''
  return decodeURIComponent(hex.replace(/\s+/g, '').replace(/[0-9A-Fa-f]{2}/g, '%$&'))
}
