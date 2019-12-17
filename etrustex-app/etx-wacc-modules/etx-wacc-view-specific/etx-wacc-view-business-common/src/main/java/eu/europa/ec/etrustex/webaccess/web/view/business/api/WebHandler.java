package eu.europa.ec.etrustex.webaccess.web.view.business.api;

import eu.europa.ec.etrustex.webaccess.model.vo.QueryParams;

import java.util.Map;

public interface WebHandler {
    static final String MESSAGE_ATTR = "msg";
    static final String REMOTE_PARTIES_ATTR = "remoteParties";
    static final String MESSAGE_ICA = "messageIca";
    static final String EDMA_MESSAGE_ATTR = "edmaMessage";
    static final String MESSAGE_LIST_ATTR = "messages";
    static final String RETENTION_METADATA_ATTR = "retentionMetadata";
    static final String ATTACHMENTS_LIST = "attachments";

    Map<String, Object> buildBusinessModel(QueryParams params) throws Exception;
}
