package eu.europa.ec.etrustex.tools.translation;

import eu.europa.ec.etrustex.tools.translation.jaxb.ShortContent;
import eu.europa.ec.etrustex.tools.translation.model.ScreenTranslation;
import eu.europa.ec.etrustex.tools.translation.service.TranslationService;
import eu.europa.ec.etrustex.tools.translation.transformer.exports.ShortContentBuilder;
import eu.europa.ec.etrustex.tools.translation.transformer.exports.ShortContentSerialiser;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Exports the ECI translations.
 *
 * @author keschma
 *
 */
public class TranslationExporter {
	
	private final static Logger LOGGER = Logger.getLogger(TranslationExporter.class);
	
	private final static String EXPORT_LANGUAGE_CODE = "en";
	
	private static MainUtils mainUtils = null; 
	
	public static void main(String[] args) throws Throwable {
		mainUtils = new MainUtils(TranslationExporter.class, args,
				"user=<user> password=<password> host=<host> port=<port> service=<service> out=<output_dir> incremental=<y|n> update-checksums=<y|n> [ screens=ID1,...,IDn ] [ refdata=TYPE1,...,TYPEn ] [ reports=ID1,...,IDn ] [ mails=ID1,...IDn]");
		
		// parse export dir
		final String exportDir = mainUtils.getRequiredValue("out");
		LOGGER.info("Exporting to directory: " + exportDir);
		
		// parse incremental option
		final String incrementalArg = mainUtils.getRequiredValue("incremental");
		final boolean isIncremental = incrementalArg.equalsIgnoreCase("y") || incrementalArg.equalsIgnoreCase("yes");
		LOGGER.info("Using incremental exports: " + isIncremental);
		
		// parse update checksums option
		final String updateChecksumsArg = mainUtils.getRequiredValue("update-checksums");
		final boolean isUpdateChecksums = updateChecksumsArg.equalsIgnoreCase("y") || updateChecksumsArg.equalsIgnoreCase("yes");
		LOGGER.info("Updating export checksums: " + isUpdateChecksums);

		// parse IDs of all items to export (if specified)
		final String screenIdsArg = mainUtils.getOptionalValue("screens");
		
		// the actual export		
		final TranslationService service = new TranslationService();		
		service.start(mainUtils.getRequiredValue("user"), mainUtils.getRequiredValue("password"),
				"oracle.jdbc.OracleDriver",
				"jdbc:oracle:thin:@" + mainUtils.getRequiredValue("host") + ":" + mainUtils.getRequiredValue("port") + "/" + mainUtils.getRequiredValue("service"));
		
		final Date exportDate = new Date();
		boolean isSuccess = false;
		try {
			// screens
			final List<Integer> screenIds = screenIdsArg != null ?
					mainUtils.parseIntegerList(screenIdsArg) :
					service.findAllScreenIds();
			exportScreens(screenIds, exportDir, isIncremental, isUpdateChecksums, exportDate, service);

			isSuccess = true;
		} catch (Throwable e) {
			LOGGER.error("An error occurred", e);
			throw e;
		} finally {
			service.stop(isSuccess);
		}	
	}

	/**
	 * Export the screen translations for all screens.
	 * @param screenIds list of the IDs of the screens to export
	 * @param exportDir
	 * @param isIncremental
	 * @param isUpdateChecksums
	 * @param exportDate
	 * @param service
	 * @throws JAXBException
	 */
	private static void exportScreens(List<Integer> screenIds, String exportDir, boolean isIncremental, boolean isUpdateChecksums, Date exportDate, TranslationService service) throws Exception {
		LOGGER.info("### Exporting " + screenIds.size() + " screens: " + screenIds + " ###");
		
		for (Integer screenId : screenIds) {
			LOGGER.info("Exporting screen: " + screenId);
			
			final List<ScreenTranslation> translations = service.findTranslationsByScreenAndLanguage(screenId, EXPORT_LANGUAGE_CODE, isIncremental);
			if (!translations.isEmpty()) {
				final Collection<ShortContent> shortContents = new ShortContentBuilder("eTrustEx Screen " + screenId, EXPORT_LANGUAGE_CODE).transform(translations);
				final Collection<String> exportFiles = new ShortContentSerialiser(exportDir).transform(shortContents);
				LOGGER.info(exportFiles.size() + " file(s) written.");
				
				if (isUpdateChecksums) {
					service.updateExportTimestamps(translations, exportDate);
					LOGGER.info("Export checksums updated.");
				}
			} else {
				LOGGER.info("No screen translations affected.");
			}
		}
	}
}
