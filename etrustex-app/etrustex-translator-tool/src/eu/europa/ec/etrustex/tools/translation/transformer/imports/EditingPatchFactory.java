package eu.europa.ec.etrustex.tools.translation.transformer.imports;

import java.util.List;

/**
 * Factory for editing patches.
 *
 * @author keschma
 *
 */
public interface EditingPatchFactory {

	/**
	 * Returns the editing patches.
	 * @return the editing patches
	 */
	public abstract List<EditingPatch> getPatches();

}