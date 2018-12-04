package eu.europa.ec.etrustex.tools.translation.db.dao;

import eu.europa.ec.etrustex.tools.translation.db.DbAccessor;
import eu.europa.ec.etrustex.tools.translation.model.ScreenTranslation;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract DAO for any kind of screen translations.
 *
 * @author keschma
 *
 */
public abstract class AbstractScreenTranslationDAO extends AbstractTranslationDAO<ScreenTranslation> {
	
	private final static Logger LOGGER = Logger.getLogger(AbstractTranslationDAO.class);

	/**
	 * Constructor. 
	 * @param dbAccessor the DbAccessor to use for low-level DB operations
	 */
	public AbstractScreenTranslationDAO(DbAccessor dbAccessor) {
		super(dbAccessor);
	}
	
	/**
	 * Finds screen translations by screen ID and language code using the
	 * given SQL string for a query.
	 * @param sql the query SQL
	 * @param screenId the screen ID
	 * @param languageCode the language code
	 * @return the found translations
	 * @throws SQLException
	 */
	protected List<ScreenTranslation> findByScreenAndLanguageCode(String sql, int screenId, String languageCode) throws SQLException {
		final Connection connection = dbAccessor.getConnection();
		
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, screenId);
			ps.setString(2, languageCode);
			
			return transformScreenContentResultSet(ps.executeQuery());
		} catch (SQLException e) {
			LOGGER.error("Could not execute query: " + sql, e);
			throw e;
		} finally {
			nullSafeClose(ps);
		}
	}
	
	/**
	 * Transforms a result set with screen position information into
	 * ScreenTranslation objects.
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	protected List<ScreenTranslation> transformScreenContentResultSet(ResultSet rs) throws SQLException {
		final List<ScreenTranslation> translations = new ArrayList<>();
		
		while (rs.next()) {
			final String key = rs.getString("key");
			final String message = rs.getString("message");
			final String languageCode = rs.getString("language_code");
			final int screenId = rs.getInt("screen_id");
			final int screenPositionX = rs.getInt("screen_position_x");
			final int screenPositionY = rs.getInt("screen_position_y");
			translations.add(new ScreenTranslation(key, message, languageCode, getTableName(), screenId, screenPositionX, screenPositionY));
		}
		
		return translations;
	}

}
