package eu.europa.ec.etrustex.tools.translation.db.dao;

import eu.europa.ec.etrustex.tools.translation.db.DbAccessor;
import eu.europa.ec.etrustex.tools.translation.model.Translation;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Abstract translation DAO.
 *
 * @author keschma
 *
 */
public abstract class AbstractTranslationDAO<T extends Translation> {
	
	private final static Logger LOGGER = Logger.getLogger(AbstractTranslationDAO.class);

	protected final DbAccessor dbAccessor;
	
	/**
	 * Constructor. 
	 * @param dbAccessor the DbAccessor to use for low-level DB operations
	 */
	public AbstractTranslationDAO(DbAccessor dbAccessor) {
		this.dbAccessor = dbAccessor;
	}
	
	/**
	 * Returns the DB table handled by this DAO.
	 * @return the DB table
	 */
	public abstract String getTableName();
	
	/**
	 * Loads the given translations into the DB.
	 * @param translations the translations to load
	 * @param referenceLanguageCode the language code of the initial
	 * language (the one from which everything got translated), used
	 * @throws SQLException on DB issues
	 */
	public abstract void loadTranslations(Collection<Translation> translations, String referenceLanguageCode)
	throws SQLException;

	public abstract void deleteTranslations(Collection<Translation> translations)
	throws SQLException;
	
	/**
	 * Sets the export timestamp for the given translation to the current date.
	 * @param translation the translation for which to set the timestamp
	 * @param exportDate the date of the export to record
	 * @throws SQLException on DB issues
	 */
	public abstract void updateExportTimestamp(T translation, Date exportDate)
	throws SQLException;
	
	/**
	 * Null-safely closes a prepared statement. No effect if the 
	 * prepared statement is null.
	 * @param ps a prepared statement, can be null
	 * @throws SQLException on underlying errors
	 */
	protected void nullSafeClose(PreparedStatement ps) throws SQLException {
		if (ps != null) {
			ps.close();
		}
	}
	
	/**
	 * Finds a translation by message key and language code.
	 * @param key the message key
	 * @param languageCode the language code
	 * @return the translation, or null, if not found
	 * @throws SQLException on DB issues
	 */
	protected abstract T findByKeyAndLanguageCode(String key, String languageCode)
	throws SQLException;
	
	/**
	 * Transforms a result set with numbers into Integers.
	 * @param rs the result set
	 * @param columnName name of the numeric result set column
	 * @return list of integers extracted from the result set
	 * @throws SQLException 
	 */
	protected List<Integer> transformIntegerResultSet(ResultSet rs, String columnName) throws SQLException {
		final List<Integer> result = new ArrayList<>();
		
		while (rs.next()) {		
			result.add(rs.getInt(columnName));
		}
		
		return result;
	}
	
	/**
	 * Reusable piece of code for loading a collection of translations.
	 * Note that the calling code should take care of creating and
	 * closing all prepared statements! 
	 * @param translations the translations to load
	 * @param referenceLanguageCode the reference language code
	 * @param insertStatementSetter a prepared statement setter for the insert statement
	 * @param updateStatementSetter a prepared statement setter for the update statement
	 * @throws SQLException forwarded from JDBC calls
	 */
	protected void loadTranslations(Collection<Translation> translations, String referenceLanguageCode,
			PreparedStatementSetter<T> insertStatementSetter,
			PreparedStatementSetter<T> updateStatementSetter) throws SQLException {
		final int[] encodedIndexes = new int[translations.size()];
				
		int i = 0;
		int insertIx = 0;
		int updateIx = 0;
		
		for (Translation translation : translations) {		
			// look up the reference record in the "original" language for this key
			final T referenceTranslation = findByKeyAndLanguageCode(translation.getKey(),
					referenceLanguageCode);
			
			if (referenceTranslation == null) {
				LOGGER.error("No reference translation found: key=" + translation.getKey() + " [" + referenceLanguageCode + "]");
				throw new IllegalArgumentException("Cannot load translation: " + translation + " - no reference translation found");
			}

			// insert or update the new translation
			final T existingTranslation = findByKeyAndLanguageCode(translation.getKey(),
					translation.getLanguageCode());
			final PreparedStatementSetter<T> currentStatementSetter;
			if (existingTranslation == null) {
				LOGGER.debug("Inserting: " + translation.getKey() + " [" + translation.getLanguageCode() + "]");
				
				currentStatementSetter = insertStatementSetter;
				encodedIndexes[i] = encodeBatchLoadIndex(insertIx, true);
				translation.setBatchIndex(insertIx);
				insertIx++;					
			} else {
				LOGGER.debug("Updating: " + translation.getKey() + " [" + translation.getLanguageCode() + "]");
				
				currentStatementSetter = updateStatementSetter;
				encodedIndexes[i] = encodeBatchLoadIndex(updateIx, false);
				translation.setBatchIndex(updateIx);
				updateIx++;
			}
									
			currentStatementSetter.setArguments(translation, referenceTranslation);
			LOGGER.debug("\r\n[SQL] " + currentStatementSetter.getCurrentSql());				
			currentStatementSetter.statement.addBatch();
			i++;
		}
	
		final int[] insertResults = insertStatementSetter.statement.executeBatch();
		final int[] updateResults = updateStatementSetter.statement.executeBatch();
		assertBatchLoadSuccess(translations, encodedIndexes, insertResults, updateResults);			
	}
	
	// support methods
	
	/**
	 * Interprets the batch load result of translations, and asserts that
	 * all operations succeeded.
	 * @param translations the translations that have been batch-loaded
	 * @param encodedIndexes array with encoded indexes of the insert/update
	 * operations, as produced by the {@link #encodeBatchLoadIndex(int, boolean)}
	 * call 
	 * @param insertResults the result array of the batch insert call
	 * @param updateResults the result array of the batch update call
	 * @throws IllegalArgumentException if the array sizes don't add up
	 * @throws IllegalStateException if the batch load failed
	 * for at least one translation
	 */
	protected void assertBatchLoadSuccess(Collection<Translation> translations, int[] encodedIndexes,
			int[] insertResults, int[] updateResults) {
		// check all array sizes for sanity
		if (encodedIndexes.length != translations.size()) {
			throw new IllegalArgumentException("Wrong length of encoded index array. Expected: " +
					translations.size() + "; got: " + encodedIndexes.length);
		}
		if (insertResults.length + updateResults.length != translations.size()) {
			throw new IllegalArgumentException("Batch inserts and updates don't add up. Expected: " +
					translations.size() + "; got: " + (insertResults.length + updateResults.length));
		}
		
		// check the batch processing result for each translation		
		int errorCount = 0;
		
		int i = 0;
		for (Translation translation : translations) {
			final boolean opIsInsert = isBatchInsert(encodedIndexes[i]);
			final int opIndex = decodeBatchLoadIndex(encodedIndexes[i]);
			final int opResult = opIsInsert ? insertResults[opIndex] : updateResults[opIndex];
			if (opResult != Statement.SUCCESS_NO_INFO && opResult != 1) {
				LOGGER.error((opIsInsert ? "INSERT" : "UPDATE") + " <" + opIndex +
						"> failed with code " + opResult + " for: " + translation);
				errorCount++;
			}
			i++;
		}
		
		if (errorCount > 0) {
			throw new IllegalStateException(errorCount + " inserts/updates failed. See log for details.");
		}
	}
	
	/**
	 * Encodes a batch load index in an int value.
	 * @param index the index to encode
	 * @param isInsert whether this is the index of an insert or update
	 * @return the encoded index
	 */
	protected int encodeBatchLoadIndex(int index, boolean isInsert) {
		return isInsert ? (index + 1) : -(index + 1); 
	}
	
	/**
	 * Decodes an encoded batch load index to the original value.
	 * @param encodedIndex the encoded index
	 * @return the original index
	 */
	private int decodeBatchLoadIndex(int encodedIndex) {
		return Math.abs(encodedIndex) - 1;
	}
	
	/**
	 * Decodes the insert or update information from an encoded index.
	 * @param encodedIndex the encoded index
	 * @return true, if this represents an insert, false for an update
	 */
	private boolean isBatchInsert(int encodedIndex) {
		return encodedIndex > 0;
	}
	
}