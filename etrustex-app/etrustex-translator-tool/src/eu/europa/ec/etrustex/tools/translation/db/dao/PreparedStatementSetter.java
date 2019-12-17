package eu.europa.ec.etrustex.tools.translation.db.dao;

import eu.europa.ec.etrustex.tools.translation.model.Translation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Abstraction for setting the actual parameters on a prepared
 * statement.
 *
 * @author keschma
 *
 * @param <T> the precise type of the reference translations used
 * inside the {@link #setArguments(Translation, Translation)} call
 */
abstract class PreparedStatementSetter<T extends Translation> {
	
	protected final PreparedStatement statement;
	
	private final String sql;
	
	protected Object[] args;
	
	/**
	 * Constructor.
	 * @param statement the prepared statement being manipulated
	 * @param sql the SQL behind the statement (for debugging)
	 */
	public PreparedStatementSetter(PreparedStatement statement, String sql) {
		this.statement = statement;
		this.sql = sql;
	}

	/**
	 * Applies actual parameters from a current translation and a reference
	 * translation onto the backing prepared statement.
	 * @param translation the current translation
	 * @param referenceTranslation a reference translation
	 * @throws SQLException forwarded from lower layers
	 */
	protected abstract void setArguments(Translation translation, T referenceTranslation) throws SQLException;
	
	/**
	 * Returns the current SQL, as generated with the "last" set
	 * of actual parameters.
	 * @return the current SQL
	 */
	public String getCurrentSql() {
		String fullSql = sql;
		
		if (args != null) {
			for (Object arg : args) {
				final String formattedArg =	(arg instanceof String) ?
						("'" + ((String) arg).replaceAll("'", "''") + "'") :
						String.valueOf(arg);
				fullSql = fullSql.replaceFirst("?", formattedArg);
			}
		}
		
		return fullSql;
	}

}
