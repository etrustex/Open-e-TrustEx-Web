package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe;

import eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata.TReferenceManifestation;


/**
 * @author apladap
 *
 */
public class EgreffeDocument extends TReferenceManifestation {
	
	private String attachmentReferenceID;
	private String attachmentFileName;
	private int partNumber;
	
	public String getAttachmentReferenceID() {
		return attachmentReferenceID;
	}

	public void setAttachmentReferenceID(String attachmentReferenceID) {
		this.attachmentReferenceID = attachmentReferenceID;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFilename) {
		this.attachmentFileName = attachmentFilename;
	}
	
}
