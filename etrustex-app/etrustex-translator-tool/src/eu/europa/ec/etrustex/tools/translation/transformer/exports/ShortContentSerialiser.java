package eu.europa.ec.etrustex.tools.translation.transformer.exports;

import eu.europa.ec.etrustex.tools.translation.jaxb.ShortContent;
import eu.europa.ec.etrustex.tools.translation.transformer.Transformer;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Serialises translations into an XML following the short_content.xsd
 * of DGT.
 *
 * @author keschma
 *
 */
public class ShortContentSerialiser implements Transformer<ShortContent, String> {
	
	private final static Logger LOGGER = Logger.getLogger(ShortContentSerialiser.class);
	
	private final static String STYLESHEET_REFERENCE =
		"\n<?xml-stylesheet type=\"text/xsl\" href=\"http://ec.europa.eu/dgs/translation/xsl/dgt_wcms.xslt\"?>";
	
	private final String outputDir;
	
	private final Marshaller marshaller;
	
	/**
	 * Constructor.
	 * @param outputDir the output directory
	 * @throws JAXBException on errors in the JAXB configuration
	 */
	public ShortContentSerialiser(String outputDir) throws JAXBException {
		if (!assertDir(outputDir)) {
			throw new IllegalArgumentException("Cannot use or create directory: " + outputDir);
		}
		this.outputDir = outputDir;
		
		final JAXBContext jc = JAXBContext.newInstance(ShortContent.class);
		marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	}
	
	/**
	 * Asserts that the directory with the given name exists
	 * (tries to create it, if necessary) and that it is writable.
	 * @param dirName the name of the directory
	 * @return true, if the directory exists and is usable
	 */
	private boolean assertDir(String dirName) {
		final Path dirFile = Paths.get(dirName);
		if (Files.notExists(dirFile)) {
			LOGGER.info("Creating directory: " + dirName);
			try {
				Files.createDirectory(dirFile);
			} catch (IOException e) {
				return false;
			}
			return true;
		}

		return Files.isDirectory(dirFile) && Files.isWritable(dirFile);
	}
	
	@Override
	public Collection<String> transform(Collection<? extends ShortContent> shortContents) throws IOException, JAXBException {
		final List<String> result = new ArrayList<>(shortContents.size());
		
		for (ShortContent shortContent : shortContents) {
			final String outputFileName = buildFileName(shortContent);
			final Path outputFile = Paths.get(outputDir, outputFileName);
			
			LOGGER.info("Serialising ShortContent object to: " + outputFile);			

			try (OutputStream outputStream = Files.newOutputStream(outputFile)) {
				marshaller.marshal(shortContent, new ContentInsertingOutputStream(outputStream, STYLESHEET_REFERENCE, "?>"));
			}
			result.add(outputFile.toAbsolutePath().toString());
		}
						
		return result;
	}

	/**
	 * Builds the output file name for the given ShortContent object.
	 * @param shortContent the ShortContent
	 * @return the file name for the serialisation
	 */
	private String buildFileName(ShortContent shortContent) {
		String language = null;
		if (shortContent.getLanguageDescriptor() != null) {
			language = shortContent.getLanguageDescriptor().getLanguageReference();
		}
		if (language == null || language.isEmpty()) {
			language = "??";
		}
		
		String description = null;
		if (shortContent.getHeading() != null) {
			description = shortContent.getHeading().getHdShortTitle();
		}
		if (description == null || description.isEmpty()) {
			description = String.valueOf(System.currentTimeMillis());
		}

		final StringBuilder builder = new StringBuilder();
		builder.append(description.toLowerCase().replace(' ', '-'));
		builder.append("-");
		builder.append(language);
		builder.append(".xml");
		return builder.toString();
	}

}
