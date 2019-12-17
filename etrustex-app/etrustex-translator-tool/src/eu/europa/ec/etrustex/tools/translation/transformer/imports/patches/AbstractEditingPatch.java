package eu.europa.ec.etrustex.tools.translation.transformer.imports.patches;

import eu.europa.ec.etrustex.tools.translation.transformer.imports.EditingPatch;

/**
 * Abstract patch for an editing operation.
 *
 * @author keschma
 *
 */
abstract class AbstractEditingPatch implements EditingPatch {
	
	/**
	 * Initialises this patch from the given arguments.
	 * @param arguments
	 * @throws IllegalArgumentException if the arguments are not as expected
	 * by the implementing classes
	 */
	protected abstract void initFrom(String... arguments) throws IllegalArgumentException;
	
}