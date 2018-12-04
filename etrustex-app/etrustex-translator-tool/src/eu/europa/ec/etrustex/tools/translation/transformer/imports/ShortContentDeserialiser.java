package eu.europa.ec.etrustex.tools.translation.transformer.imports;

import eu.europa.ec.etrustex.tools.translation.jaxb.ShortContent;
import eu.europa.ec.etrustex.tools.translation.transformer.Transformer;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Deserialises translation XML files into ShortContent objects.
 *
 * @author keschma
 *
 */
public class ShortContentDeserialiser implements Transformer<Path, ShortContent> {
	
	private final static Logger LOGGER = Logger.getLogger(ShortContentDeserialiser.class);
	
	private final Unmarshaller unmarshaller;
	
	/**
	 * Constructor.
	 * @throws JAXBException on errors in configuring JAXB
	 */
	public ShortContentDeserialiser() throws JAXBException {
		final JAXBContext jc = JAXBContext.newInstance(ShortContent.class);
		unmarshaller = jc.createUnmarshaller();
	}

	@Override
	public Collection<ShortContent> transform(Collection<? extends Path> files) throws IOException, JAXBException {
		final List<ShortContent> result = new ArrayList<ShortContent>(files.size());
		
		for (Path file : files) {
			LOGGER.info("Deserialising file: " + file.getFileName().toString());
			try(InputStream inputStream = Files.newInputStream(file)) {
				final ShortContent shortContent = (ShortContent) unmarshaller.unmarshal(inputStream);
				result.add(shortContent);
			}
		}
		
		return result;
	}

}
