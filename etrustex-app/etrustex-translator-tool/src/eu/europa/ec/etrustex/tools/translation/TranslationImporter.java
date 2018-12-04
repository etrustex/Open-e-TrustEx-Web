package eu.europa.ec.etrustex.tools.translation;

import eu.europa.ec.etrustex.tools.translation.jaxb.ShortContent;
import eu.europa.ec.etrustex.tools.translation.model.Translation;
import eu.europa.ec.etrustex.tools.translation.service.TranslationService;
import eu.europa.ec.etrustex.tools.translation.transformer.imports.EditingOperationTranslationPatcher;
import eu.europa.ec.etrustex.tools.translation.transformer.imports.EditingPatchFactory;
import eu.europa.ec.etrustex.tools.translation.transformer.imports.ShortContentDeserialiser;
import eu.europa.ec.etrustex.tools.translation.transformer.imports.TranslationBuilder;
import eu.europa.ec.etrustex.tools.translation.transformer.imports.patches.TextFilePatchFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.Map.Entry;

/**
 * Imports the ECI translations. If required, the translations can
 * be patched before the import, by providing a text file using the
 * following syntax: 
 * <ul>
 * <li><code>del foo.bar</code> - marks all translations with the
 * code foo.bar as deleted (will not be imported)</li>
 * <li><code>ren foo.bar foo.baz</code> - imports all translations with
 * the code foo.bar under the code foo.baz</li>
 * </ul> 
 *
 * @author keschma
 *
 */
public class TranslationImporter {

	private final static Logger LOGGER = Logger.getLogger(TranslationImporter.class);

	private static final PathMatcher XML_FILE_MATCHER = FileSystems.getDefault().getPathMatcher("glob:*.xml");
	
	private final static String REFERENCE_LANGUAGE_CODE = "en";
	
	public static void main(String[] args) throws Throwable {
		final MainUtils mainUtils = new MainUtils(TranslationImporter.class, args,
			"user=<user> password=<password> host=<host> port=<port> service=<service> in=<input_dir> recursive=<y|n> [ patchfile=<file_with_patches> ]");
		
		// parse import dir
		final String importDir = mainUtils.getRequiredValue("in");
		LOGGER.info("Importing from directory: " + importDir);
		
		// parse recursive option
		final String recursiveArg = mainUtils.getRequiredValue("recursive");
		final boolean isRecursive = recursiveArg.equalsIgnoreCase("y") || recursiveArg.equalsIgnoreCase("yes");
		LOGGER.info("Recursive traversal: " + isRecursive);
		
		// parse patch file option
		final String patchFile = mainUtils.getOptionalValue("patchfile");		
		LOGGER.info("Patch file: " + ((patchFile == null) ? "<none>" : patchFile));
		
		// the actual import
		final TranslationService service = new TranslationService();		
		service.start(mainUtils.getRequiredValue("user"), mainUtils.getRequiredValue("password"),
				"oracle.jdbc.OracleDriver",
				"jdbc:oracle:thin:@" + mainUtils.getRequiredValue("host") + ":" + mainUtils.getRequiredValue("port") + "/" + mainUtils.getRequiredValue("service"));
		
		boolean isSuccess = false;
		try {
			importAll(importDir, isRecursive, patchFile, service);
			isSuccess = true;
		} catch (Throwable e) {
			LOGGER.error("An error occurred", e);
			throw e;
		} finally {		
			service.stop(isSuccess);
		}		
	}

	/**
	 * Imports all translations in the given directory.
	 * @param importDir the import directory 
	 * @param isRecursive whether the import should use a full recursive descent
	 * @param patchFile (optional) a file defining patches that will be applied before importing
	 * @param service the translation service
	 * @throws IllegalArgumentException if importDir is not a
	 * readable directory
	 */
	private static void importAll(String importDir, boolean isRecursive, String patchFile, TranslationService service) throws Exception {
		LOGGER.info("### Importing translations ###");
		
		// load all custom patches, if necessary
		EditingPatchFactory patchFactory = null;
		if (patchFile != null) {
			patchFactory = new TextFilePatchFactory(patchFile);
		}
		
		final Path importDirFile = Paths.get(importDir);
		if (!Files.isDirectory(importDirFile) || !Files.isReadable(importDirFile)) {
			LOGGER.error(importDirFile.getFileName().toString() + " is not a readable directory.");
			throw new IllegalArgumentException("Unable to read from directory: " + importDir);
		}

		// import directory by directory => should be more comprehensible
		final Map<Path, List<Path>> dirToXmlFilesMap = buildDirToXmlFilesMap(importDirFile, isRecursive);
		int nrFiles = 0;
		for (List<Path> files : dirToXmlFilesMap.values()) {
			nrFiles += files.size();
		}
		LOGGER.info(nrFiles + " XML file(s) found in " + dirToXmlFilesMap.keySet().size() + " directories.");
		
		for (Entry<Path, List<Path>> entry : dirToXmlFilesMap.entrySet()) {
			LOGGER.info("------------------------------------");
			LOGGER.info("IMPORTING DIR: " + entry.getKey().toString());
			LOGGER.info("------------------------------------");
			
			final Collection<ShortContent> shortContents = new ShortContentDeserialiser().transform(entry.getValue());			
			final Collection<Translation> translations = new TranslationBuilder().transform(shortContents);
			final Collection<Translation> patchedTranslations1;
			if (patchFactory != null) {
				patchedTranslations1 = new EditingOperationTranslationPatcher(patchFactory).transform(translations);
			} else {
				patchedTranslations1 = translations;
			}
			service.loadTranslations(patchedTranslations1, REFERENCE_LANGUAGE_CODE);
			LOGGER.info(translations.size() + " translation(s) loaded.");
		}				
	}
	
	/**
	 * Builds a map File -&gt; List&lt;File&gt; containing translation XML files
	 * to import. The keys of this map are parent directories, and the
	 * values is a list of file objects of all XML files in the given parent
	 * directory. 
	 * @param rootDir the root directory of translation XML files
	 * @param isRecursive whether the import should be fully recursive,
	 * or just limited to the given directory
	 * @return the directory to list-of-files map
	 * @throws IOException on filesystem IO issues
	 */
	private static Map<Path, List<Path>> buildDirToXmlFilesMap(final Path rootDir, final boolean isRecursive) throws IOException {
		final Map<Path, List<Path>> dirToFilesMap = new LinkedHashMap<>();

		Files.walkFileTree(rootDir, EnumSet.noneOf(FileVisitOption.class), isRecursive ? Integer.MAX_VALUE : 0, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				// add this XML file to the collected files for this directory
				if (XML_FILE_MATCHER.matches(file.getFileName())) {
					final Path parent = file.getParent();
					List<Path> filesForDir = dirToFilesMap.get(parent);
					if (filesForDir == null) {
						filesForDir = new ArrayList<>();
						dirToFilesMap.put(parent, filesForDir);
					}
					filesForDir.add(file);
				}

				return FileVisitResult.CONTINUE;
			}
		});
		return dirToFilesMap;
	}
}
