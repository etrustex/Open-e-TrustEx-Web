package eu.europa.ec.etrustex.services.converter;

import eu.europa.ec.etrustex.services.model.RestAttachment;
import eu.europa.ec.etrustex.services.model.RestAttachments;
import eu.europa.ec.etrustex.webaccess.model.Attachment;

import java.util.List;

/**
 * Rest attachment converter.
 */

public class RestAttachmentConverter {

    private static final RestAttachmentConverter INSTANCE = new RestAttachmentConverter();

    /**
     * Gets singleton instance.
     *
     * @return Singleton instance.
     */

    public static RestAttachmentConverter getInstance() {
        return INSTANCE;
    }

    private RestAttachmentConverter() {
    }

    /**
     * Converts a list of Attachment entities to a REST Attachments entity.
     *
     * @param attachments List of Attachment entities.
     * @return REST Attachments entity.
     */

    public RestAttachments convertToRestAttachments(List<Attachment> attachments) {
        RestAttachments restAttachments = new RestAttachments();

        if (attachments != null) {
            for (Attachment attachment : attachments) {
                RestAttachment restAttachment = new RestAttachment();
                restAttachment.setId(attachment.getId() != null ? String.valueOf(attachment.getId()) : null);
                restAttachment.setMessageId((attachment.getMessage() != null && attachment.getMessage().getId() != null) ? String.valueOf(attachment.getMessage().getId()) : null);
                restAttachment.setMimeType(attachment.getMimeType());
                restAttachment.setName(attachment.getFileName());

                restAttachments.addAttachmentListItem(restAttachment);
            }
        }

        return restAttachments;
    }

}
