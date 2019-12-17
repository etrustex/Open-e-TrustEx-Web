import {I18Label, I18Tooltip} from "./i18";

export class FileStatus {
  constructor(name, tooltip) {
    this.name = name
    this.tooltip = tooltip
  }

  toString() {
    return this.name;
  }
}

FileStatus.SUCCESS = new FileStatus(messages[I18Label.LABEL_SUCCESS], messages[I18Tooltip.TOOLTIP_DOWNLOAD_SUCCESS])
FileStatus.FAILED = new FileStatus(messages[I18Label.LABEL_FAILED], messages[I18Tooltip.TOOLTIP_DOWNLOAD_FAILED])
FileStatus.UNTRUSTED = new FileStatus(messages[I18Label.LABEL_UNTRUSTED], messages[I18Tooltip.TOOLTIP_DOWNLOAD_UNTRUSTED])
FileStatus.CANCELLED = new FileStatus(messages[I18Label.LABEL_CANCELLED], messages[I18Tooltip.TOOLTIP_DOWNLOAD_CANCELLED])
FileStatus.EXPIRED = new FileStatus(messages[I18Label.LABEL_ATTACHMENT_EXPIRED], messages[I18Tooltip.TOOLTIP_ATTACHMENT_EXPIRED])

FileStatus.UPLOAD_SUCCESS = new FileStatus(messages[I18Label.LABEL_SUCCESS], messages[I18Tooltip.TOOLTIP_UPLOAD_SUCCESS])
FileStatus.UPLOAD_FAILED_EXCEED_MAXIMUM_SIZE = new FileStatus(messages[I18Label.LABEL_FAILED], messages[I18Tooltip.TOOLTIP_UPLOAD_FAILED_EXCEED_MAXIMUM_SIZE])
FileStatus.UPLOAD_FAILED_EMPTY_FILE = new FileStatus(messages[I18Label.LABEL_FAILED], messages[I18Tooltip.TOOLTIP_UPLOAD_FAILED_EMPTY_FILE])
FileStatus.UPLOAD_FAILED_EXTENSION_NOT_ALLOWED = new FileStatus(messages[I18Label.LABEL_FAILED], messages[I18Tooltip.TOOLTIP_UPLOAD_FAILED_EXTENSION_NOT_ALLOWED])
FileStatus.UPLOAD_FAILED_CHECKSUM_MISMATCH = new FileStatus(messages[I18Label.LABEL_FAILED], messages[I18Tooltip.TOOLTIP_UPLOAD_FAILED_CHECKSUM_MISMATCH])
FileStatus.UPLOAD_FAILED = new FileStatus(messages[I18Label.LABEL_FAILED], messages[I18Tooltip.TOOLTIP_UPLOAD_FAILED])
FileStatus.UPLOAD_FAILED_EXCEED_MAXIMUM_FILE_NAME_LENGTH = new FileStatus(messages[I18Label.LABEL_FAILED], messages[I18Tooltip.TOOLTIP_UPLOAD_FAILED_EXCEED_MAXIMUM_FILE_NAME_LENGTH])
