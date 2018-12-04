package eu.europa.ec.etrustex.tools.db;

import eu.europa.ec.etrustex.tools.db.common.BatchPreparedStatements;
import eu.europa.ec.etrustex.tools.db.common.DbProcessor;
import eu.europa.ec.etrustex.webaccess.persistence.util.MessageUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Generate etx_web_message.msg_searchable_content.
 * See: ETX-1869
 * Iterate all messages in etx_web_message and update msg_searchable_content based on msg_subject.
 */
public class StringNormalizer extends DbProcessor {
    private static final String QUERY_UPDATE_SEARCHABLE_CONTENT = "UPDATE etx_web_message SET msg_searchable_content = ? WHERE msg_id = ?";
    private static final String QUERY_SELECT_MESSAGES = "SELECT msg_id, msg_subject FROM etx_web_message";
    private static final String UPDATE_SEARCHABLE_CONTENT_STATEMENT = "UPDATE_SEARCHABLE_CONTENT";

    public static void main(String[] args) {
        new StringNormalizer(args).process();
    }

    public StringNormalizer(String[] args) {
        super(args);
    }

    @Override
    protected BatchPreparedStatements prepareBatchStatements(Connection connection) throws SQLException {
        BatchPreparedStatements batchPreparedStatements = new BatchPreparedStatements();
        batchPreparedStatements.createStatement(UPDATE_SEARCHABLE_CONTENT_STATEMENT, QUERY_UPDATE_SEARCHABLE_CONTENT, connection);

        return batchPreparedStatements;
    }


    protected void performBatchOperation(ResultSet loopResultSet, BatchPreparedStatements batchPreparedStatements) throws SQLException {
        int messageId = loopResultSet.getInt(1);
        String subject = loopResultSet.getString(2);

        final PreparedStatement statement = batchPreparedStatements.getStatement(UPDATE_SEARCHABLE_CONTENT_STATEMENT);
        statement.setString(1, MessageUtil.normalizeString(subject));
        statement.setInt(2, messageId);

        statement.addBatch();
    }

    @Override
    protected String getLoopQuery() {
        return QUERY_SELECT_MESSAGES;
    }

}
