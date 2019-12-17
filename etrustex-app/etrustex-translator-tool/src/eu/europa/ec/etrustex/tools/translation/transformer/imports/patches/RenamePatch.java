package eu.europa.ec.etrustex.tools.translation.transformer.imports.patches;

import eu.europa.ec.etrustex.tools.translation.model.Translation;
import org.apache.log4j.Logger;

/**
 * Patch for renaming a translation key.
 *
 * @author keschma
 *
 */
class RenamePatch extends AbstractEditingPatch {
	
	private final static Logger LOGGER = Logger.getLogger(RenamePatch.class);
	
	final static String PATCH_ID = "ren";
	
	private String oldKey;
	
	private String newKey;
	
	public String getOldKey() {
		return oldKey;
	}
	
	public String getNewKey() {
		return newKey;
	}
	
	@Override
	protected void initFrom(String... arguments) {
		if (arguments.length < 2) {
			throw new IllegalArgumentException(PATCH_ID + " needs 2 arguments; got " + arguments.length);
		}		
		oldKey = arguments[0];
		newKey = arguments[1];	
	}
	
	@Override
	public Translation apply(Translation translation) {
		if (translation.getKey().equals(oldKey)) {
			LOGGER.info(toString() + " applied on translation: " + translation);
			return new Translation(newKey, translation.getMessage(),
					translation.getLanguageCode(), translation.getTableName());
		}
		return translation;
	}		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((newKey == null) ? 0 : newKey.hashCode());
		result = prime * result + ((oldKey == null) ? 0 : oldKey.hashCode());
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
		RenamePatch other = (RenamePatch) obj;
		if (newKey == null) {
			if (other.newKey != null)
				return false;
		} else if (!newKey.equals(other.newKey))
			return false;
		if (oldKey == null) {
			if (other.oldKey != null)
				return false;
		} else if (!oldKey.equals(other.oldKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ " + PATCH_ID + " " + oldKey + " " + newKey + " ]";
	}
	
}