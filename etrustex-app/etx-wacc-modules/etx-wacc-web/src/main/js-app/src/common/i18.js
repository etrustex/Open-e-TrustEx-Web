export const I18Message = Object.freeze({
    MESSAGE_SUBJECT_ERROR_MESSAGE: 'message.subjectErrorMessage',
    MESSAGE_RECIPIENT_ERROR_MESSAGE: 'message.recipientErrorMessage',
    MESSAGE_COUNTRY_ERROR_MESSAGE: 'message.countryErrorMessage',
    MESSAGE_COMPANY_ERROR_MESSAGE: 'message.companyErrorMessage',
    MESSAGE_CONTACT_PERSON_ERROR_MESSAGE: 'message.contactPersonErrorMessage',
    MESSAGE_INSTRUMENT_ERROR_MESSAGE: 'message.instrumentErrorMessage',
    MESSAGE_CANCEL_MESSAGE: 'message.cancelMessage',
    MESSAGE_ATTACHMENT_LIST_EMPTY: 'message.attachmentListEmpty',

    MESSAGE_DELETE_DRAFT_MESSAGE: 'message.deleteDraftMessage',
    ERROR_ADMIN_WRONG_EMAIL: 'error.admin.wrongEmail',
    ERROR_NO_VALID_EC_EMAIL: 'error.no.valid.ec.email',
    ERROR_ADMIN_MANDATORY_FIELD: 'error.admin.mandatoryFields',
    ERROR_ADMIN_GENERIC: 'error.admin.generic',
    MESSAGE_SHOULD_BE_SIGNED_ERROR_MESSAGE: 'message.shouldBeSignedErrorMessage',
    MESSAGE_ATTACHMENT_LIST_TOO_LONG: 'message.attachmentListTooLong',

    // MESSAGE_INSERT_PASSWORD_AND_PROVIDE_CERTIFICATE_BEFORE_DECODING: 'message.insert.password.and.provide.certificate.before.decoding',
    MESSAGE_SELECT_AT_LEAST_ONE_FILE: 'message.select.et.least.one.file',
    MESSAGE_CANCEL_EDIT_MESSAGE: 'message.cancel.edit.message',
    MESSAGE_UPLOAD_FILES_BEFORE_SENDING_MESSAGE:  'message.upload.files.before.sending.message',
    MESSAGE_UPLOAD_FILES_BEFORE_SAVING_MESSAGE:  'message.upload.files.before.saving.message',
    MESSAGE_INSERT_PASSWORD_AND_PROVIDE_CERTIFICATE_BEFORE_SIGNING: 'message.insert.password.and.provide.certificate.before.signing',
    MESSAGE_WRONG_PASSWORD :'message.wrong.password',
    MESSAGE_UPLOAD_AT_LEAST_ONE_FILE: 'message.upload.at.least.one.file',
    // MESSAGE_PKCS12_GENERIC: 'message.pkcs12.generic',
    // MESSAGE_CERTIFICATE_PASSWORD: 'message.certificatePassword',
    // MESSAGE_CERTIFICATE_INPUT: 'message.certificateInput',
    // MESSAGE_CERTIFICATE_EXPIRED: 'message.certificate.expired',
    MESSAGE_CERTIFICATE_FILE_MAX_SIZE: 'message.certificate.file.max.size',
    MESSAGE_CERTIFICATE_FILE_NOT_VALID: 'message.certificate.file.not.valid',
    MESSAGE_CERTIFICATE_SELECT_IDENTITY: 'message.certificate.select.identity',
    MESSAGE_CERTIFICATE_SELECT_IDENTITY_VALID: 'message.certificate.select.identity.valid',
    MESSAGE_CERTIFICATE_SELECT: 'message.certificate.select',
    MESSAGE_CERTIFICATE_INSERT_PASSWORD: 'message.certificate.insert.password',
    MESSAGE_CERTIFICATE_LOAD_IDENTITIES: 'message.certificate.load.identities',
    MESSAGE_DECRYPTION_FAILED_WRONG_KEY: 'message.decryption.failed.wrong.key',
    MESSAGE_PLEASE_ENTER_CASE_NUMBER: 'message.please.enter.case.number'
})

export const I18Label = Object.freeze({
    LABEL_SAVE: 'label.save',
    LABEL_ADMIN_CANCEL: 'label.admin.cancel',
    LABEL_ADMIN_DELETE: 'label.admin.delete',
    LABEL_ADMIN_EDIT_USER_ROLE_ADMINISTRATOR: 'label.admin.edit.user.role.administrator',
    LABEL_ADMIN_EDIT_USER_ROLE_OPERATOR: 'label.admin.edit.user.role.operator',
    LABEL_ADMIN_ADD_USER_DIALOG_TITLE: 'label.admin.addUserDialogTitle',
    LABEL_ADMIN_EDIT_USER_DIALOG_TITLE: 'label.admin.editUserDialogTitle',
    LABEL_ADMIN_NOTIF_DELETE_MANDATORY: 'label.admin.notif.delete.mandatory',
    LABEL_ADMIN_NOTIF_CHECK_MANDATORY: 'label.admin.notif.check.mandatory',
    LABEL_ADMIN_EDIT_PARTY_NAME_TITLE: 'label.admin.editPartyNameTitle',
    LABEL_ADMIN_PARTIES_UNAVAILABLE: 'label.admin.parties.unavailable',
    LABEL_ADMIN_CLOSE: 'label.admin.close',
    LABEL_ADMIN_CERTIFICATE_DIALOG_TITLE: 'label.admin.certificate.dialogTitle',
    LABEL_REFRESH: 'label.refresh',
    LABEL_ADMIN_ICA_INACTIVE: 'label.admin.ica.inactive',
    LABEL_ICA_ENCRYPTION_REQUIRED: 'label.ica.encryption.required',
    LABEL_ICA_ENCRYPTION_NOT_REQUIRED: 'label.ica.encryption.not.required',
    LABEL_ICA_SIGNATURE_REQUIRED: 'label.ica.signature.required',
    LABEL_ICA_SIGNATURE_NOT_REQUIRED: 'label.ica.signature.not.required',
    LABEL_CANCEL_MESSAGE: 'label.cancel.message',
    LABEL_NO_FILE_SELECTED: 'label.no.file.selected',
    LABEL_DECRYPTION_NEEDED: 'label.decryption.needed',
    LABEL_UPLOAD_NOT_FINISHED: 'label.upload.not.finished',
    LABEL_WRONG_CERTIFICATE: 'v3.2.0_label.wrong.certificate',
    LABEL_SIGNATURE_REQUIRED: 'label.signature.required',
    LABEL_DECRYPTION_INFORMATION: 'label.decryption.information',
    LABEL_SELECT_CERTIFICATE: 'label.select.certificate',
    LABEL_CERTIFICATE_IDENTITIES: 'label.certificate.identities',
    LABEL_LOAD_IDENTITIES: 'label.load.identities',
    LABEL_REMOVE_FILE: 'label.remove.file',
    LABEL_CANCELLING: 'label.cancelling',
    LABEL_UPLOADING: 'label.uploading',
    LABEL_PREPARING: 'label.preparing',
    LABEL_VERIFYING: 'label.verifying',
    LABEL_DOWNLOADING: 'label.downloading',
    LABEL_CANCEL: 'label.cancel',
    LABEL_SUCCESS: 'label.success',
    LABEL_FAILED: 'label.failed',
    LABEL_CANCELLED:  'label.cancelled',
    LABEL_UNTRUSTED: 'label.untrusted',
    LABEL_ATTACHMENT_EXPIRED: 'label.attachment.expired',
    LABEL_SELECT_REMOTE_DIALOG: 'label.select.remote.dialog'
    })

export const I18Tooltip = Object.freeze({
  TOOLTIP_DOWNLOAD_SUCCESS: 'tooltip.download.success',
  TOOLTIP_DOWNLOAD_FAILED: 'tooltip.download.failed',
  TOOLTIP_DOWNLOAD_UNTRUSTED: 'tooltip.download.untrusted',
  TOOLTIP_DOWNLOAD_CANCELLED: 'tooltip.download.cancelled',
  TOOLTIP_UPLOAD_SUCCESS: 'tooltip.upload.success',
  TOOLTIP_UPLOAD_FAILED: 'tooltip.upload.failed',
  TOOLTIP_UPLOAD_FAILED_CHECKSUM_MISMATCH: 'tooltip.upload.failed.checksum.mismatch',
  TOOLTIP_UPLOAD_FAILED_EXCEED_MAXIMUM_SIZE: 'tooltip.upload.failed.exceed.maximum.size',
  TOOLTIP_UPLOAD_FAILED_EMPTY_FILE: 'tooltip.upload.failed.empty.file',
  TOOLTIP_IDENTITY_NOT_YET_VALID: 'tooltip.identity.not.yet.valid',
  TOOLTIP_IDENTITY_EXPIRED: 'tooltip.identity.expired',
  TOOLTIP_ATTACHMENT_EXPIRED: 'tooltip.attachment.expired',
  TOOLTIP_DRAFT_CANNOT_SAVE: 'tooltip.draft.cannotSave',
  TOOLTIP_UPLOAD_FAILED_EXCEED_MAXIMUM_FILE_NAME_LENGTH: 'tooltip.upload.failed.exceed.maximum.file.name.length',
  TOOLTIP_UPLOAD_FAILED_EXTENSION_NOT_ALLOWED: 'tooltip.upload.failed.extension.not.allowed'
})
