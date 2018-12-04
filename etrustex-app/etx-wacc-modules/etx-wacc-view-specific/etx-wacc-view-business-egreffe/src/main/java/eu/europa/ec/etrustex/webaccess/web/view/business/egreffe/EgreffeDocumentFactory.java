package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe;


/**
 * @author apladap
 *
 */
public class EgreffeDocumentFactory implements TReferenceManifestationFactory {

	/* (non-Javadoc)
	 * @see eu.europa.ec.etrustex.webaccess.web.model.TReferenceManifestationFactory#createNewObject()
	 */
	@Override
	public EgreffeDocument createNewObject() {
		
		return new EgreffeDocument();
	}

}
