package eu.europa.ec.etrustex.tools.translation.transformer.imports;

import eu.europa.ec.etrustex.tools.translation.jaxb.Div;
import eu.europa.ec.etrustex.tools.translation.jaxb.ShortContent;
import eu.europa.ec.etrustex.tools.translation.jaxb.XhtmlRType;
import eu.europa.ec.etrustex.tools.translation.model.Translation;
import eu.europa.ec.etrustex.tools.translation.transformer.Transformer;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Builds {@link Translation} objects from {@link ShortContent}
 * representations of translation data.
 *
 * @author keschma
 *
 */
public class TranslationBuilder implements Transformer<ShortContent, Translation> {
	
	private final static Logger LOGGER = Logger.getLogger(TranslationBuilder.class);
	
	private final Marshaller messageWrapperMarshaller;
	
	/**
	 * Constructor.
	 * @throws JAXBException on errors in the JAXB configuration
	 */
	public TranslationBuilder() throws JAXBException {
		final JAXBContext jc = JAXBContext.newInstance(Div.class);
		messageWrapperMarshaller = jc.createMarshaller();
		messageWrapperMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
	}
	
	@Override
	public Collection<Translation> transform(Collection<? extends ShortContent> shortContents) throws JAXBException {		
		final List<Translation> result = new ArrayList<>();
		
		for (ShortContent shortContent : shortContents) {
			LOGGER.info("Transforming ShortContent <" + getDescription(shortContent) + ">.");
			
			final List<Translation> translations = toTranslations(shortContent);
			LOGGER.info("Produced " + translations.size() + " translations.");
			result.addAll(translations);			
		}
		
		return result;
	}

	/**
	 * Translates a ShortContent object to the translations it encodes.
	 * @param shortContent a ShortContent
	 * @return the encoded translations
	 */
	private List<Translation> toTranslations(ShortContent shortContent) throws JAXBException {
		final List<Translation> result = new ArrayList<>();
		
		final String languageCode = getLanguageCode(shortContent);
		for (Div wrapper : getTranslationWrappers(shortContent)) {
			final Translation translation = toTranslation(wrapper, languageCode);
			result.add(translation);
		}
		
		return result;
	}
	
	/**
	 * Obtains the translation language code from the ShortContent object.
	 * @param shortContent the ShortContent object
	 * @return the language code
	 * @throws IllegalStateException if the language code cannot be resolved
	 */
	private String getLanguageCode(ShortContent shortContent) {
		assertNotEmpty(shortContent.getLanguageDescriptor(), "languageDescriptor");
		return shortContent.getLanguageDescriptor().getLanguageReference();
	}
	
	/**
	 * Obtains the description of this set of translations
	 * @param shortContent the ShortContent object
	 * @return the description, or "???", if it cannot be resolved
	 */
	private String getDescription(ShortContent shortContent) {
		String description = null;
		if (shortContent.getHeading() != null) {
			description = shortContent.getHeading().getHdShortTitle();
		}
		return (description == null) ? "???" : description;
	}
	
	/**
	 * Obtains the translation wrapper elements (currently: div) from
	 * the ShortContent object.
	 * @param shortContent the ShortContent object
	 * @return the wrapper elements for the actual translations
	 * @throws IllegalStateException if the wrapper elements cannot be resolved
	 */
	private List<Div> getTranslationWrappers(ShortContent shortContent) {
		final List<Div> result = new ArrayList<Div>();

		assertNotEmpty(shortContent.getText(), "text");
		
		int count = 0;
		for (XhtmlRType rType : shortContent.getText().getContentR()) {
			assertNotEmpty(rType.getXhtmlFragment(), "text.content_r[" + count + "].xhtml_fragment");
			result.addAll(rType.getXhtmlFragment().getDiv());
			count++;
		}
		
		return result;
	}
	
	/**
	 * Creates a translation object from a wrapper and the language
	 * code for the translation language.
	 * @param wrapper the translation wrapper
	 * @param languageCode the language code
	 * @return the corresponding translation object
	 * @throws JAXBException on underlying marshalling failures
	 */
	private Translation toTranslation(Div wrapper, String languageCode) throws JAXBException {
		final String tableName = getTableName(wrapper);
		final String key = getKey(wrapper);
		final String message = getMessage(wrapper); 
		return new Translation(key, message, languageCode, tableName);
	}
	
	
	/**
	 * Obtains the DB table name from a translation wrapper element.
	 * @param wrapper the translation wrapper
	 * @return the DB table name
	 * @throws IllegalStateException if the table name cannot be resolved
	 */
	private String getTableName(Div wrapper) {
		assertNotEmpty(wrapper.getClazz(), "class");
		return wrapper.getClazz().get(0);
	}
	
	/**
	 * Obtains the translation message key from a translation wrapper element.
	 * @param wrapper the translation wrapper
	 * @return the message key
 	 * @throws IllegalStateException if the key cannot be resolved
	 */
	private String getKey(Div wrapper) {
		assertNotEmpty(wrapper.getId(), "id");
		return wrapper.getId();
	}
	
	/**
	 * Obtains the translated message from a translation wrapper element.
	 * @param wrapper the translation wrapper
	 * @return the translated message
	 * @throws JAXBException on underlying marshalling failures
	 */
	private String getMessage(Div wrapper) throws JAXBException {
		// we have to marshall the wrapper content, since it's HTML
		// ==> this produces <div>MY HTML</div> 
		final StringWriter writer = new StringWriter();
		messageWrapperMarshaller.marshal(wrapper, writer);
		
		// remove the outer wrapper element, thus returning the actual HTML content
		// ==> this produces MY HTML
		final String content = writer.toString();		
		final int startIx = content.indexOf('>') + 1;
		final int endIx = content.lastIndexOf('<');		
		return content.substring(startIx, endIx);
	}
	
	/**
	 * Asserts that a given object value is not empty.
	 * @param <T> type of the value
	 * @param value the value to test
	 * @param description a description of the value (for an
	 * informative message in case of errors)
     * @throws IllegalStateException if the value is empty
	 */
	private static <T> void assertNotEmpty(T value, String description) {
		if (value == null) {
			throw new IllegalStateException("Empty object found: " + description);
		}
		if (value.equals("")) {
			throw new IllegalStateException("Empty string found: " + description);
		}
		if (value instanceof Collection && ((Collection<?>) value).isEmpty()) {
			throw new IllegalStateException("Empty collection found: " + description);
		}
	}

}
