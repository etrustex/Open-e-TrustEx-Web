package eu.europa.ec.etrustex.tools.translation.transformer.imports;

import eu.europa.ec.etrustex.tools.translation.model.Translation;

/**
 * A patch for an editing operation.
 *
 * @author keschma
 *
 */
public interface EditingPatch {
	
	/**
	 * Applies this patch on the given translation.
	 * @param translation a translation
	 * @return the resulting translation, or null, if as a result of the patch,
	 * this translation should be <em>removed</em>
	 */
	public Translation apply(Translation translation);

}
