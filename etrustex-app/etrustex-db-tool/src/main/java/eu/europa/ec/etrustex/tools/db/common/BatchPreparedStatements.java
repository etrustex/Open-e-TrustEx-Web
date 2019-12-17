package eu.europa.ec.etrustex.tools.db.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BatchPreparedStatements {

    private Map<String, PreparedStatement> statements = new HashMap<>();

    public void createStatement(String name, String query, Connection connection) throws SQLException {
        statements.put(name, connection.prepareStatement(query));
    }

    public PreparedStatement getStatement(String name) {
        return statements.get(name);
    }

    public void executeBatch() throws SQLException {
        for(PreparedStatement statement : statements.values()) {
            statement.executeBatch();
        }
    }
}
