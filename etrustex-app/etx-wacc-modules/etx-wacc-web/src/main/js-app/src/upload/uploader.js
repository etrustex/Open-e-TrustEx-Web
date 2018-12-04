export class Uploader {

  constructor({fileStream, localPartyId, encryptionCertificateX509SubjectName, fileInfo }) {
    this.fileStream = fileStream
    this.localPartyId = localPartyId
    this.fileInfo = fileInfo
    this.encryptionCertificateX509SubjectName = encryptionCertificateX509SubjectName
  }

  upload() {
    return new Promise((resolve, reject) => {
      let formData = new FormData()

      let generatedFileId = this.generateFileId()
      this.fileInfo.generatedFileId = generatedFileId

      formData.append('referenceId', this.fileInfo.generatedFileId)
      formData.append('localPartyId', this.localPartyId)
      formData.append('name', this.fileInfo.fileName)
      formData.append('path', this.fileInfo.filePath)
      formData.append('size', '' + this.fileInfo.fileSize)
      formData.append('mimeType', 'application/octet-stream' /*TODO: check the right mimeType of the attachment?*/)
      formData.append('attachmentType', 'BINARY')

      formData.append('transmissionChecksumMethod', this.fileInfo.transmissionChecksumMethod)// this.fileStream.getChecksumMethod())
      formData.append('transmissionChecksum', this.fileInfo.transmissionChecksum)//this.fileStream.getMD())

      if (this.fileInfo.isEncrypted) {
        formData.append('contentChecksumMethod', this.fileInfo.contentChecksumMethod)
        formData.append('contentChecksum', this.fileInfo.contentChecksum)
        formData.append('encryptedKey', this.fileInfo.encryptedKey)
        formData.append('encryptionCertificateX509SubjectName', this.encryptionCertificateX509SubjectName)

      } else { // still sending a content checksum (which is equal to the transmission checksum)
        formData.append('contentChecksumMethod', this.fileInfo.contentChecksumMethod)
        formData.append('contentChecksum', this.fileInfo.contentChecksum)
      }

      const blob = this.fileStream.getBlob("application/octet-stream")
      formData.append('file', blob, this.fileInfo.fileName)

      let theFileInfo = this.fileInfo

      let xhr = new XMLHttpRequest(),
        method = "POST",
        url = "uploadFile.do"

      xhr.open(method, url, true)

      xhr.onload = function () {
        if (this.status >= 200 && this.status < 300) {
          console.log('Upload completed for file ' + theFileInfo.fileName)
          theFileInfo.fileIdFromNode=xhr.response
          //bundleHandler.addFile(fileIdForUpload, theFileInfo)
          theFileInfo.uploadStatus='ok'
          resolve(xhr.response);
        } else {
          theFileInfo.uploadStatus='ko'
          console.log('Failure!' + ' - Error: ' + xhr.statusText)
          reject({ status: this.status, statusText: xhr.statusText })
        }
      }

      xhr.onerror = function () {
        theFileInfo.uploadStatus='ko'
        console.log('Failure!' + ' - Error: ' + xhr.statusText)
        reject({ status: this.status, statusText: xhr.statusText })
      }

      xhr.send(formData)
    })
  }

  generateFileId() {
    let now  = new Date()
    let res = `${now.getFullYear()}${this.toTwoDigits(now.getMonth()+1)}${this.toTwoDigits(now.getDate())}${this.toTwoDigits(now.getHours())}${this.toTwoDigits(now.getMinutes())}${this.toTwoDigits(now.getSeconds())}${this.toKDigits(now.getMilliseconds(),3)}`
    return `${res}-${this.toKDigits(Math.floor((Math.random() * Math.pow(2, 32)) + 1),10)}${this.toKDigits(Math.floor((Math.random() * Math.pow(2, 32)) + 1),10)}`
  }
  toTwoDigits(number) {
    return ('0'+number).slice(-2)
  }
  toKDigits(number, k) {
    let prefix = '0000000000'
    while (prefix.length <k) {
      prefix = prefix.concat(prefix)
    }
    return (prefix + number).slice(-1*k)
  }
}

