package eu.europa.ec.etrustex.tools.translation.transformer.imports.patches;

import eu.europa.ec.etrustex.tools.translation.model.Translation;
import org.apache.log4j.Logger;

/**
 * Patch for deleting a translation.
 *
 * @author keschma
 *
 */
class DeletePatch extends AbstractEditingPatch {
	
	private final static Logger LOGGER = Logger.getLogger(DeletePatch.class);
	
	final static String PATCH_ID = "del";
	
	private String key;
	
	public String getKey() {
		return key;
	}
	
	@Override
	protected void initFrom(String... arguments) {
		if (arguments.length < 1) {
			throw new IllegalArgumentException(PATCH_ID + " needs 1 argument; got " + arguments.length);
		}		
		key = arguments[0];
	}
	
	@Override
	public Translation apply(Translation translation) {
		if (translation.getKey().equals(key)) {
			LOGGER.info(toString() + " applied on translation: " + translation);
			return null;
		}
		return translation;
	}
			
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeletePatch other = (DeletePatch) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ "+ PATCH_ID + " " + key + " ]";
	}
	
}