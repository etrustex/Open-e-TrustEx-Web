package eu.europa.ec.etrustex.tools.translation.transformer.imports;

import eu.europa.ec.etrustex.tools.translation.model.Translation;
import eu.europa.ec.etrustex.tools.translation.transformer.Transformer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Patches translation objects with editing patches obtained from
 * a given factory.  
 *
 * @author keschma
 *
 */
public class EditingOperationTranslationPatcher implements Transformer<Translation, Translation> {
	
	private final static Logger LOGGER = Logger.getLogger(EditingOperationTranslationPatcher.class);
	
	private final List<EditingPatch> patches;	
	
	/**
	 * Constructor.
	 * @param editingPatchFactory the patch factory to use
	 */
	public EditingOperationTranslationPatcher(EditingPatchFactory editingPatchFactory) {
		this.patches = editingPatchFactory.getPatches();
	}

	@Override
	public Collection<Translation> transform(Collection<? extends Translation> objects) throws Exception {
		final List<Translation> result = new ArrayList<>(objects.size());
		
		outer:
		for (Translation translation : objects) {
			Translation patchedTranslation = translation;
			
			for (EditingPatch patch : patches) {
				patchedTranslation = patch.apply(patchedTranslation);
				if (patchedTranslation == null) {
					// translation has been deleted as a consequence of the patch
					continue outer;
				}
			}			
			result.add(patchedTranslation);
		}
				
		LOGGER.info("Finished applying editing patches; " + result.size() + " translations are left.");
		return result;
	}
	
}
