package eu.europa.ec.etrustex.tools.translation.db.dao;

import eu.europa.ec.etrustex.tools.translation.db.DbAccessor;
import eu.europa.ec.etrustex.tools.translation.model.ScreenTranslation;
import eu.europa.ec.etrustex.tools.translation.model.Translation;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * DAO for label translations.
 *
 * @author keschma
 *
 */
public class LabelTranslationDAO extends AbstractScreenTranslationDAO {
	
	private final static Logger LOGGER = Logger.getLogger(LabelTranslationDAO.class);
	
	// export
	
	
	private final static String SQL_FIND_ALL_SCREEN_IDS =
		"SELECT DISTINCT ltr_screen_id AS screen_id FROM ETX_WEB_LABEL_TRANSLATION";
		
	private final static String SQL_FIND_MODIFIED_LABEL_TRANSLATIONS_BY_SCREEN_AND_LANGUAGE_CODE =
		"SELECT ltr_key AS key, ltr_message AS message, lng_code AS language_code, ltr_screen_id AS screen_id, ltr_screen_position_x AS screen_position_x, ltr_screen_position_y AS screen_position_y "+
		"FROM ETX_WEB_LABEL_TRANSLATION t, ETX_WEB_LANG l " +
		"WHERE l.lng_id = t.lng_id AND ltr_screen_id = ? AND lng_code = ? " +
		"  AND ( ltr_export_checksum IS NULL OR ltr_export_checksum <> etx_util.md5(ltr_message)) " +
		"ORDER BY ltr_key";
	
	private final static String SQL_FIND_LABEL_TRANSLATIONS_BY_SCREEN_AND_LANGUAGE_CODE =
		"SELECT ltr_key AS key, ltr_message AS message, lng_code AS language_code, ltr_screen_id AS screen_id, ltr_screen_position_x AS screen_position_x, ltr_screen_position_y AS screen_position_y "+
		"FROM ETX_WEB_LABEL_TRANSLATION t, ETX_WEB_LANG l " +
		"WHERE l.lng_id = t.lng_id AND ltr_screen_id = ? AND lng_code = ? " +
		"ORDER BY ltr_key";
	
	private static final String SQL_UPDATE_LABEL_TRANSLATION_EXPORT_TIMESTAMP =
		"UPDATE ETX_WEB_LABEL_TRANSLATION SET ltr_export_date = ?, ltr_export_checksum = etx_util.md5(ltr_message) " +
		"WHERE ltr_key = ? AND lng_id = ( SELECT lng_id FROM ETX_WEB_LANG WHERE lng_code = ? )";
	
	// import
	
	private final static String SQL_FIND_LABEL_TRANSLATION_BY_KEY_AND_LANGUAGE_CODE =
		"SELECT ltr_key AS key, ltr_message AS message, lng_code AS language_code, ltr_screen_id AS screen_id, ltr_screen_position_x AS screen_position_x, ltr_screen_position_y AS screen_position_y " +
		"FROM ETX_WEB_LABEL_TRANSLATION t, ETX_WEB_LANG l " +
		"WHERE l.lng_id = t.lng_id AND ltr_key = ? AND lng_code = ? ";
	
	private final static String SQL_INSERT_LABEL_TRANSLATION =
		"INSERT INTO ETX_WEB_LABEL_TRANSLATION (ltr_id, lng_id, ltr_key, ltr_screen_id, ltr_screen_position_x, ltr_screen_position_y, ltr_import_date, ltr_import_checksum, ltr_message) "+
		"VALUES ((SELECT NVL(MAX(ltr_id),0) FROM ETX_WEB_LABEL_TRANSLATION)+?+1, (SELECT lng_id FROM ETX_WEB_LANG WHERE lng_code = ?), ?, ?, ?, ?, sysdate, etx_util.md5(?), ?)";

	private final static String SQL_DELETE_LABELS = "DELETE FROM ETX_WEB_LABEL_TRANSLATION " +
			"WHERE LTR_KEY = ? AND lng_id = ( SELECT lng_id FROM ETX_WEB_LANG WHERE lng_code = ? )";
	
	private final static String SQL_UPDATE_LABEL_TRANSLATION =
		"UPDATE ETX_WEB_LABEL_TRANSLATION SET ltr_import_date = sysdate, ltr_import_checksum = etx_util.md5(?), ltr_message = ? "+
		"WHERE ltr_key = ? AND lng_id = ( SELECT lng_id FROM ETX_WEB_LANG WHERE lng_code = ? )";
	
	/**
	 * Constructor.
	 * @param dbAccessor the DBAccessor to use for low-level operations
	 */
	public LabelTranslationDAO(DbAccessor dbAccessor) {
		super(dbAccessor);
	}
	
	@Override
	public String getTableName() {
		return "ETX_WEB_LABEL_TRANSLATION";
	}
	
	// export
	
	public List<Integer> findAllScreenIds() throws SQLException {
		final Connection connection = dbAccessor.getConnection();
		
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_FIND_ALL_SCREEN_IDS);
			
			return transformIntegerResultSet(ps.executeQuery(), "screen_id");
		} catch (SQLException e) {
			LOGGER.error("Could not execute query: " + SQL_FIND_ALL_SCREEN_IDS, e);
			throw e;
		} finally {
			nullSafeClose(ps);
		}
	}
		
	public List<ScreenTranslation> findModifiedByScreenAndLanguageCode(int screenId, String languageCode) throws SQLException {
		return findByScreenAndLanguageCode(SQL_FIND_MODIFIED_LABEL_TRANSLATIONS_BY_SCREEN_AND_LANGUAGE_CODE, screenId, languageCode);		
	}
	
	public List<ScreenTranslation> findByScreenAndLanguageCode(int screenId, String languageCode) throws SQLException {
		return findByScreenAndLanguageCode(SQL_FIND_LABEL_TRANSLATIONS_BY_SCREEN_AND_LANGUAGE_CODE, screenId, languageCode);		
	}
	
	@Override
	public void updateExportTimestamp(ScreenTranslation translation, Date exportDate) throws SQLException {
		final Connection connection = dbAccessor.getConnection();
		
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_UPDATE_LABEL_TRANSLATION_EXPORT_TIMESTAMP);
			ps.setTimestamp(1, new java.sql.Timestamp(exportDate.getTime()));
			ps.setString(2, translation.getKey());
			ps.setString(3, translation.getLanguageCode());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Could not execute query: " + SQL_UPDATE_LABEL_TRANSLATION_EXPORT_TIMESTAMP, e);
			throw e;
		} finally {
			nullSafeClose(ps);
		}
	}
	
	// import
	
	@Override
	protected ScreenTranslation findByKeyAndLanguageCode(String key, String languageCode) throws SQLException {
		final Connection connection = dbAccessor.getConnection();
		
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_FIND_LABEL_TRANSLATION_BY_KEY_AND_LANGUAGE_CODE);
			ps.setString(1, key);
			ps.setString(2, languageCode);
			
			final List<ScreenTranslation> translations = transformScreenContentResultSet(ps.executeQuery());
			return translations.isEmpty() ? null : translations.get(0);
		} catch (SQLException e) {
			LOGGER.error("Could not execute query: " + SQL_FIND_LABEL_TRANSLATION_BY_KEY_AND_LANGUAGE_CODE +
					" [" + key + "," + languageCode + "]", e);
			LOGGER.error(e);
			throw e;
		} finally {
			nullSafeClose(ps);
		}
	}
	
	@Override
	public void loadTranslations(Collection<Translation> translations, String referenceLanguageCode) throws SQLException {
		final Connection connection = dbAccessor.getConnection();

		PreparedStatement insertPs = null;
		PreparedStatement updatePs = null;
		try {
			insertPs = connection.prepareStatement(SQL_INSERT_LABEL_TRANSLATION);
			final PreparedStatementSetter<ScreenTranslation> insertStatementSetter =
				new PreparedStatementSetter<ScreenTranslation>(insertPs, SQL_INSERT_LABEL_TRANSLATION) {
				@Override
				protected void setArguments(Translation translation, ScreenTranslation referenceTranslation) throws SQLException {
					statement.setInt(1, translation.getBatchIndex());
					statement.setString(2, translation.getLanguageCode());
					statement.setString(3, translation.getKey());
					statement.setInt(4, referenceTranslation.getScreenId());
					statement.setInt(5, referenceTranslation.getScreenPositionX());
					statement.setInt(6, referenceTranslation.getScreenPositionY());
					statement.setString(7, translation.getMessage());
					statement.setString(8, translation.getMessage());
				}
			};

			updatePs = connection.prepareStatement(SQL_UPDATE_LABEL_TRANSLATION);
			final PreparedStatementSetter<ScreenTranslation> updateStatementSetter =
				new PreparedStatementSetter<ScreenTranslation>(updatePs, SQL_UPDATE_LABEL_TRANSLATION) {
				@Override
				protected void setArguments(Translation translation, ScreenTranslation referenceTranslation) throws SQLException {
					statement.setString(1, translation.getMessage());
					statement.setString(2, translation.getMessage());
					statement.setString(3, translation.getKey());
					statement.setString(4, translation.getLanguageCode());
				}
			};

			loadTranslations(translations, referenceLanguageCode, insertStatementSetter, updateStatementSetter);
		} finally {
			nullSafeClose(insertPs);
			nullSafeClose(updatePs);
		}
	}

	@Override
	public void deleteTranslations(Collection<Translation> translations) throws SQLException {
		final Connection connection = dbAccessor.getConnection();

		final int[] encodedIndexes = new int[translations.size()];
		PreparedStatement deletePs = null;
		try {
			deletePs = connection.prepareStatement(SQL_DELETE_LABELS);
			final PreparedStatementSetter<ScreenTranslation> deleteStatement =
					new PreparedStatementSetter<ScreenTranslation>(deletePs, SQL_DELETE_LABELS) {
						@Override
						protected void setArguments(Translation translation, ScreenTranslation referenceTranslation) throws SQLException {
							statement.setString(1, translation.getKey());
							statement.setString(2, translation.getLanguageCode());
						}
					};
			int index = 0;
			for (Translation translation : translations) {
				// look up the reference record in the "original" language for this key

				encodedIndexes[index] = encodeBatchLoadIndex(index, true);
				translation.setBatchIndex(index);
				index++;

				deleteStatement.setArguments(translation, null);
				LOGGER.debug("\r\n[SQL] " + deleteStatement.getCurrentSql());
				deleteStatement.statement.addBatch();
			}

			final int[] deleteResults = deleteStatement.statement.executeBatch();
			assertBatchLoadSuccess(translations, encodedIndexes, deleteResults, new int[]{});
		} finally {
			nullSafeClose(deletePs);
		}
	}

}
