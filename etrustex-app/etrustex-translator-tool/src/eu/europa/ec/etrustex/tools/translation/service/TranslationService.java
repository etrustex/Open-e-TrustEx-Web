package eu.europa.ec.etrustex.tools.translation.service;

import eu.europa.ec.etrustex.tools.translation.db.DbAccessor;
import eu.europa.ec.etrustex.tools.translation.db.dao.AbstractTranslationDAO;
import eu.europa.ec.etrustex.tools.translation.db.dao.LabelTranslationDAO;
import eu.europa.ec.etrustex.tools.translation.model.ScreenTranslation;
import eu.europa.ec.etrustex.tools.translation.model.Translation;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

/**
 * The translation service.
 *
 * @author keschma
 *
 */
public class TranslationService {
	
	private final static Logger LOGGER = Logger.getLogger(TranslationService.class);
	
	private DbAccessor dbAccessor;
	
	private LabelTranslationDAO labelTranslationDao;
	
	
	/**
	 * Starts the service with the given DB connection parameters.
	 * @param user
	 * @param password
	 * @param driverClass
	 * @param jdbcUrl
	 */
	public void start(String user, String password, String driverClass, String jdbcUrl) {
		dbAccessor = new DbAccessor(user, password, driverClass, jdbcUrl);
		dbAccessor.open();
		
		labelTranslationDao = new LabelTranslationDAO(dbAccessor);
	}
	
	/**
	 * Stops the service. This <b>must</b> be called, or else
	 * the connections won't be released!
	 * @param isSuccess whether all preceding business calls were successful
	 */
	public void stop(boolean isSuccess) {
		try {
			dbAccessor.close(isSuccess);			
		} catch (Exception e) {
			LOGGER.error("An error occurred!", e);
		} finally {
			dbAccessor = null;
			
			labelTranslationDao = null;
		}
	}
	
	// screen
	
	/**
	 * Finds all screen IDs.
	 * @return the screen IDs
	 * @throws SQLException on underlying DB errors
	 */
	public List<Integer> findAllScreenIds() throws SQLException {
		final Set<Integer> screenIds = new HashSet<>();
		
		screenIds.addAll(labelTranslationDao.findAllScreenIds());
		
		final List<Integer> result = new ArrayList<>(screenIds);
		Collections.sort(result);
		return result;
	}
	
	/**
	 * Finds all screen translations for the given screen and language.
	 * @param screenId the screen ID
	 * @param languageCode the code of the language to use
	 * @param isIncremental whether we should retrieve only those translations
	 * that have changed since the last export, or all of them
	 * @return the translations
	 * @throws SQLException on underlying DB errors
	 */
	public List<ScreenTranslation> findTranslationsByScreenAndLanguage(int screenId, String languageCode, boolean isIncremental) throws SQLException {
		final List<ScreenTranslation> translations = new ArrayList<>();
		
		if (isIncremental) {
			translations.addAll(labelTranslationDao.findModifiedByScreenAndLanguageCode(screenId, languageCode));
		} else {
			translations.addAll(labelTranslationDao.findByScreenAndLanguageCode(screenId, languageCode));
		}
		
		Collections.sort(translations, new ScreenTranslation.ScreenOrderComparator());		
		return translations;
	}
		
	
	// export timestamps
	
	/**
	 * Updates the export timestamps to the current date for the given translations.
	 * @param translations the translations
	 * @param exportDate the export date
	 * @throws SQLException on underlying DB errors
	 */
	public <T extends Translation> void updateExportTimestamps(Collection<T> translations, Date exportDate) throws SQLException {
		for (T translation : translations) {
			@SuppressWarnings({"unchecked", "rawtypes"})
			final AbstractTranslationDAO<T> dao = (AbstractTranslationDAO) resolveDao(translation.getTableName());
			dao.updateExportTimestamp(translation, exportDate);
		}
	}

	// loading translations
	
	/**
	 * Loads a number of translations into the DB. Since the given translations
	 * are compared and synchronized with existing data, it is not necessary
	 * for them to be any specialised subclass of Translation.
	 * @param translations list of translations to load
	 */
	public void loadTranslations(Collection<Translation> translations, String referenceLanguageCode) throws SQLException {
		final Map<String, Collection<Translation>> translationsByTableName = partitionByTableName(translations);
		
		for (Entry<String, Collection<Translation>> entry : translationsByTableName.entrySet()) {
			final AbstractTranslationDAO<? extends Translation> dao = resolveDao(entry.getKey());
			dao.loadTranslations(entry.getValue(), referenceLanguageCode);			
		}
	}

	public void deleteTranslations(Collection<Translation> translations) throws SQLException {
		final Map<String, Collection<Translation>> translationsByTableName = partitionByTableName(translations);

		for (Entry<String, Collection<Translation>> entry : translationsByTableName.entrySet()) {
			final AbstractTranslationDAO<? extends Translation> dao = resolveDao(entry.getKey());
			dao.deleteTranslations(entry.getValue());
		}
	}
	
	/**
	 * Partitions translations by table name.
	 * @param translations collection of translations
	 * @return a map (table name -&gt; translations of this table)
	 */
	private Map<String, Collection<Translation>> partitionByTableName(Collection<Translation> translations) {
		final Map<String, Collection<Translation>> result = new HashMap<>();
		
		for (Translation translation : translations) {
			Collection<Translation> partitionCollection = result.get(translation.getTableName());
			if (partitionCollection == null) {
				partitionCollection = new ArrayList<>();
				result.put(translation.getTableName(), partitionCollection);
			}
			partitionCollection.add(translation);
		}
		
		return result;
	}

	/**
	 * Resolves a DAO in charge of the given translation table.
	 * @param translationTableName the name of a translation table
	 * @return the DAO in charge
	 * @throws UnsupportedOperationException if the given translation table
	 * is not supported by any DAO implementation
	 */
	private AbstractTranslationDAO<? extends Translation> resolveDao(String translationTableName) {
		if (translationTableName.equalsIgnoreCase(labelTranslationDao.getTableName())) {
			return labelTranslationDao;
		}
		throw new UnsupportedOperationException("No DAO implementation available for table name: " + translationTableName);
	}

}
