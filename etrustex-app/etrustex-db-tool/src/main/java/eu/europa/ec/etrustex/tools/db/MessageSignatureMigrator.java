package eu.europa.ec.etrustex.tools.db;

import eu.europa.ec.etrustex.signature.v1.DocumentType;
import eu.europa.ec.etrustex.signature.v1.SignedBundle;
import eu.europa.ec.etrustex.tools.db.common.BatchPreparedStatements;
import eu.europa.ec.etrustex.tools.db.common.DbProcessor;
import eu.europa.ec.etrustex.webaccess.model.MessageSignature;
import eu.europa.ec.etrustex.webaccess.security.MessageSignatureUtil;
import eu.europa.ec.etrustex.webaccess.utils.XMLHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * To be run ONLY for Web version 2.10.0
 * 
 */
public class MessageSignatureMigrator extends DbProcessor {

    private final static Logger LOGGER = Logger.getLogger(MessageSignatureMigrator.class);

    private final static String QUERY_SELECT_MESSAGES = "SELECT msg_id, mss_id, msg_signature, msg_signed_data FROM etx_web_message_sig";

    private final static String UPDATE_MESSAGE_SIGNATURE_STATEMENT = "UPDATE_SIGNATURE";
    private final static String QUERY_UPDATE_SIGNATURE = "UPDATE etx_web_message_sig set MSG_CERTIFICATE = ?, MSG_SIGNATURE_VALID = ? " +
                                                         "WHERE mss_id = ?";

    private static final String UPDATE_ATTACHMENT_CONTENT_CHECKSUM_STATEMENT = "UPDATE_ATTACHMENT_CNT_CHECKSUM";
    private static final String QUERY_UPDATE_ATTACHMENT_CNT_CHECKSUM = "UPDATE etx_web_attachment set ATT_CONTENT_CHK = ?, ATT_CONTENT_CHK_METHOD = ? " +
                                                                               "WHERE att_wrapper_id = ? AND msg_id = ?";

    public static void main(String[] args) {
        new MessageSignatureMigrator(args).process();
    }

    public MessageSignatureMigrator(String[] args) {
        super(args);
    }

    @Override
    protected BatchPreparedStatements prepareBatchStatements(Connection connection) throws SQLException {
        BatchPreparedStatements batchPreparedStatements = new BatchPreparedStatements();
        batchPreparedStatements.createStatement(UPDATE_MESSAGE_SIGNATURE_STATEMENT, QUERY_UPDATE_SIGNATURE, connection);
        batchPreparedStatements.createStatement(UPDATE_ATTACHMENT_CONTENT_CHECKSUM_STATEMENT, QUERY_UPDATE_ATTACHMENT_CNT_CHECKSUM, connection);

        return batchPreparedStatements;
    }

    @Override
    protected void performBatchOperation(ResultSet loopResultSet, BatchPreparedStatements batchPreparedStatements) throws Exception {
        int msgId = loopResultSet.getInt(1);
        int msgSignatureId = loopResultSet.getInt(2);
        String msgSignature = loopResultSet.getString(3);
        String msgSignedData = loopResultSet.getString(4);

        final PreparedStatement statement = batchPreparedStatements.getStatement(UPDATE_MESSAGE_SIGNATURE_STATEMENT);
        MessageSignature messageSignature = MessageSignatureUtil.extractSignatureData(msgSignature, msgSignedData, null);

        if (messageSignature != null) {
            statement.setString(1, messageSignature.getCertificate());
            statement.setInt(2, booleanToInt(messageSignature.isSignatureValid()));
        } else {
            LOGGER.error("failed extracting messageSignature for msg_id = " + msgId);
            statement.setString(1, null);
            statement.setInt(2, booleanToInt(false));
        }
        statement.setInt(3, msgSignatureId);

        statement.addBatch();

        updateAttachmentsContentChecksum(msgId, msgSignedData, batchPreparedStatements);

    }

    private void updateAttachmentsContentChecksum(int msgId, String msgSignedData, BatchPreparedStatements batchPreparedStatements) throws SQLException {
        SignedBundle signedBundle;
        try {
            signedBundle = (SignedBundle) XMLHelper.xmlToJaxbObject(msgSignedData, SignedBundle.class);
        } catch (Exception e) {
            LOGGER.warn("Invalid signedBundle", e);
            return;
        }

        final PreparedStatement statement = batchPreparedStatements.getStatement(UPDATE_ATTACHMENT_CONTENT_CHECKSUM_STATEMENT);
        for (DocumentType documentType : signedBundle.getDocument()) {
            statement.setString(1, documentType.getDigestValue());
            statement.setString(2, documentType.getDigestMethod());
            statement.setString(3, documentType.getId());
            statement.setInt(4, msgId);

            statement.addBatch();
        }
    }

    private int booleanToInt(boolean value) {
        return value ? 1 : 0;
    }

    @Override
    protected String getLoopQuery() {
        return QUERY_SELECT_MESSAGES;
    }
}
