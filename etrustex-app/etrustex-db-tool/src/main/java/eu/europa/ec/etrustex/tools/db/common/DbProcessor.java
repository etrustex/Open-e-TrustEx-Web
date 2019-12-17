package eu.europa.ec.etrustex.tools.db.common;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;

import java.sql.*;

abstract public class DbProcessor {

    protected static final int BATCH_SIZE = 500;

    private final static Logger LOGGER = Logger.getLogger(DbProcessor.class);

    protected Connection connection;

    public DbProcessor(String[] args) {
        MainUtils mainUtils = new MainUtils(this.getClass(), args,
                "user=<user> password=<password> host=<host> port=<port> service=<service> driverClass=<jdbc driver full class name; default is Oracle>");

        final String driverClass = mainUtils.getOptionalValue("driverClass");
        // use oracle as default driver
        registerDriver(driverClass != null ? driverClass : "oracle.jdbc.OracleDriver");

        String jdbcUrl = "jdbc:oracle:thin:@" + mainUtils.getRequiredValue("host") + ":" + mainUtils.getRequiredValue("port") + "/" + mainUtils.getRequiredValue("service");

        connection = openConnection(jdbcUrl, mainUtils.getRequiredValue("user"), mainUtils.getRequiredValue("password"));
    }

    /**
     * Create db connection
     * @param jdbcUrl
     * @param user
     * @param password
     * @return
     */
    protected Connection openConnection(String jdbcUrl, String user, String password) {
        LOGGER.info("Acquiring connection: " + jdbcUrl + " " + user + "/" + password);
        Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException e) {
            LOGGER.error("Unable to acquire connection with: " + jdbcUrl + " " + user + "/" + password);
            throw new IllegalStateException(e);
        }
    }

    /**
     * Register jdbc driver
     *
     * @param driverClass
     */
    private static void registerDriver(String driverClass) {
        LOGGER.info("Initialising driver: " + driverClass);
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Unable to load driver class: " + driverClass);
            throw new IllegalStateException(e);
        }
    }

    /**
     * Process batch updates
     */
    public void process() {
        try {
            StopWatch timer = new StopWatch();
            timer.start();

            performBatchOperation(connection);

            timer.stop();
            LOGGER.info("Execution time: " + timer.toString());
        } catch (Exception e) {
            LOGGER.error("Failed to update db");
            throw new IllegalStateException(e);
        }
    }

    /**
     *  Template method for performing batch operation.
     *  Either override the entire method or override following methods:
     *  performBatchOperation(ResultSet loopResultSet, PreparedStatement batchOperationStmt),
     *  String getLoopQuery(),
     *  String getBatchOperationQuery()
     *
     * @param connection
     * @throws Exception
     */
    protected void performBatchOperation(Connection connection) throws Exception {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setFetchSize(BATCH_SIZE);

        BatchPreparedStatements preparedStatements = prepareBatchStatements(connection);

        long totalRecords = 0;
        ResultSet resultSet = stmt.executeQuery(getLoopQuery());

        while(resultSet.next()) {
            totalRecords++;

            performBatchOperation(resultSet, preparedStatements);

            if (totalRecords % BATCH_SIZE == 0) {
                preparedStatements.executeBatch();
                connection.commit();
                LOGGER.info("Processed : " + totalRecords + " records");
            }
        }

        if (totalRecords % BATCH_SIZE > 0) {
            preparedStatements.executeBatch();
            connection.commit();
        }

        LOGGER.info("Total processed records: " + totalRecords);
    }

    protected abstract BatchPreparedStatements prepareBatchStatements(Connection connection) throws SQLException;

    protected void performBatchOperation(ResultSet loopResultSet, BatchPreparedStatements batchPreparedStatements) throws Exception {
        throw new NotImplementedException("Not implemented");
    }

    protected String getLoopQuery() {
        throw new NotImplementedException("Not implemented");
    }
}