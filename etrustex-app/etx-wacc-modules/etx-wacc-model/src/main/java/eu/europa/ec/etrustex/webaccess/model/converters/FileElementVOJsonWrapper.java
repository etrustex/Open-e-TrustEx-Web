package eu.europa.ec.etrustex.webaccess.model.converters;

import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import eu.europa.ec.etrustex.webaccess.model.converters.util.FileSizeFormatter;
import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonObjectBuilderDecorator;
import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonSerializable;
import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonUtil;
import eu.europa.ec.etrustex.webaccess.model.vo.FileElementVO;

import javax.json.JsonObjectBuilder;

public class FileElementVOJsonWrapper implements JsonSerializable {

    private FileElementVO fileElement;

    public FileElementVOJsonWrapper(FileElementVO fileElement) {
        this.fileElement = fileElement;
    }

    @Override
    public JsonObjectBuilder createJsonBuilder() {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilderDecorator();

        if (fileElement != null) {
            jsonObjectBuilder
                    .add("id", fileElement.getId())
                    .add("fileSize", fileElement.getFileSize())
                    .add("formattedSize", FileSizeFormatter.formatFileSize(fileElement.getFileSize()))
                    .add("fileName", fileElement.getFileName())
                    .add("path", fileElement.getPath())
                    .add("transmissionChecksum", JsonUtil.toBase64(fileElement.getTransmissionChecksum()))
                    .add("transmissionChecksumMethod", fileElement.getTransmissionChecksumMethod())
                    .add("contentChecksum", JsonUtil.toBase64(fileElement.getContentChecksum()))
                    .add("contentChecksumMethod", fileElement.getContentChecksumMethod())
                    .add("mimeType", fileElement.getMimeType())
                    .add("fileReferenceId", fileElement.getFileReferenceId())
                    .add("isPayload", AttachmentType.METADATA == fileElement.getType())
                    .add("comment", fileElement.getComment());

            if (fileElement.getConfidential() != null) {
                jsonObjectBuilder.add("confidential", fileElement.getConfidential());
            }
        }
        return jsonObjectBuilder;
    }
}
