package eu.europa.ec.etrustex.tools.translation.transformer.exports;

import eu.europa.ec.etrustex.tools.translation.jaxb.*;
import eu.europa.ec.etrustex.tools.translation.jaxb.ContentQual.ContentLifeCycle;
import eu.europa.ec.etrustex.tools.translation.model.Translation;
import eu.europa.ec.etrustex.tools.translation.transformer.Transformer;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Builds a ShortContent object structure for a set of translations.
 *
 * @author keschma
 *
 */
public class ShortContentBuilder implements Transformer<Translation, ShortContent> {
	
	private final static Logger LOGGER = Logger.getLogger(ShortContentBuilder.class);
	
	private final static String SC_CONTENT_PRIORITY = "0";
	
	private final static String SC_DATE_FORMAT = "dd/MM/yyyy";
	
	private final static String SC_CONTENT_CREATOR = "DIGIT.B.2.001";
	
	private final String title;
	
	private final String languageCode;
	
	private final Unmarshaller messageWrapperUnmarshaller;

	/**
	 * Constructor.
	 * @param title the title to use in the ShortContent object
	 * @param languageCode the language code <em>common to all the translations</em> 
	 * @throws JAXBException on JAXB configuration errors
	 */
	public ShortContentBuilder(String title, String languageCode) throws JAXBException {
		this.title = title;
		this.languageCode = languageCode;		
		
		final JAXBContext jc = JAXBContext.newInstance(Div.class);
		messageWrapperUnmarshaller = jc.createUnmarshaller();
	}
	
	@Override
	public Collection<ShortContent> transform(Collection<? extends Translation> translations) throws JAXBException {
		LOGGER.info("Building ShortContent object structure.");	
		
		final ShortContent shortContent = new ShortContent();
		
		final LanguageDescriptor languageDescriptor = new LanguageDescriptor();		
		languageDescriptor.setLanguageReference(languageCode);
		shortContent.setLanguageDescriptor(languageDescriptor);
	
		final HdHeadingType hdHeadingType = new HdHeadingType();
		hdHeadingType.setHdShortTitle(title);
		hdHeadingType.setHdLongTitle(title);		
		shortContent.setHeading(hdHeadingType);
		
		final ContentQual contentQual = new ContentQual();
		contentQual.setContentPriority(SC_CONTENT_PRIORITY);		
		final ContentLifeCycle contentLifeCycle = new ContentLifeCycle();
		contentLifeCycle.setClcCreationDate(new SimpleDateFormat(SC_DATE_FORMAT).format(new Date()));
		contentQual.setContentLifeCycle(contentLifeCycle);
		final IpgMetaType ipgMetaType = new IpgMetaType();
		ipgMetaType.getIpgReferenceOrIpgTitleOrIpgCreator().add(new JAXBElement<>(new QName("ipg_reference"), String.class, "-"));
		ipgMetaType.getIpgReferenceOrIpgTitleOrIpgCreator().add(new JAXBElement<>(new QName("ipg_title"), String.class, title));
		ipgMetaType.getIpgReferenceOrIpgTitleOrIpgCreator().add(new JAXBElement<>(new QName("ipg_creator"), String.class, SC_CONTENT_CREATOR));
		ipgMetaType.getIpgReferenceOrIpgTitleOrIpgCreator().add(new JAXBElement<>(new QName("ipg_language"), String.class, languageCode));
		contentQual.setContentIpg(ipgMetaType);
		shortContent.setContentQual(contentQual);
		
		final TextType textType = new TextType();
		final XhtmlRType xhtmlRType = new XhtmlRType();
		final XhtmlFragmentType xhtmlFragmentType = new XhtmlFragmentType();
		final List<Div> divs = xhtmlFragmentType.getDiv();
		
		for (Translation translation : translations) {
			if (!translation.getLanguageCode().equals(languageCode)) {
				throw new IllegalStateException("Wrong language for translation " + translation + "; expected: " + languageCode);
			}
			LOGGER.debug("Wrapping translation: " + translation);
			divs.add(buildDiv(translation));
		}
		
		xhtmlRType.setXhtmlFragment(xhtmlFragmentType);
		textType.getContentR().add(xhtmlRType);
		shortContent.setText(textType);
				
		return Collections.singleton(shortContent);
	}

	/**
	 * Encodes an actual translation in a Div wrapper element.
	 * The current implementation encodes the translation key
	 * as the <code>id</code> attribute, and the table name as the
	 * <code>class</code> attribute of the wrapper element.
	 * @param translation a translation
	 * @return the generated Div element
	 * @throws JAXBException on JAXB errors
	 */
	private Div buildDiv(Translation translation) throws JAXBException {
		final String divString = "<div>" + translation.getMessage() + "</div>"; 
		final Div div = (Div) messageWrapperUnmarshaller.unmarshal(new StringReader(divString));
		div.getClazz().add(translation.getTableName());
		div.setId(translation.getKey());

		return div;
	}	

}
