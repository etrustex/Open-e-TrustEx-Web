package eu.europa.ec.etrustex.webaccess.model.vo;

import java.io.Serializable;

public class SignatureVO implements Serializable {

	private String signature;
	private String signedData;
	private Long signatureId;

	public SignatureVO(String signature, String signedData, Long signatureId) {
		this.signature = signature;
		this.signedData = signedData;
		this.signatureId = signatureId;
	}

	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSignedData() {
		return signedData;
	}
	public void setSignedData(String signedData) {
		this.signedData = signedData;
	}

	public Long getSignatureId() {
		return signatureId;
	}

	public void setSignatureId(Long signatureId) {
		this.signatureId = signatureId;
	}
}
