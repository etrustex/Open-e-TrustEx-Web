package eu.europa.ec.etrustex.webaccess.model.vo;

import java.util.HashMap;
import java.util.List;

public class DossierResult {
	
	
	private HashMap<String, List<MessageListEntry>> dossierMap = new HashMap<>();
	
	private long totalResultSize;


	public HashMap<String, List<MessageListEntry>> getDossierMap() {
		return dossierMap;
	}

	public void setDossierMap(HashMap<String, List<MessageListEntry>> dossierMap) {
		this.dossierMap = dossierMap;
	}

	public long getTotalResultSize() {
		return totalResultSize;
	}

	public void setTotalResultSize(long totalResultSize) {
		this.totalResultSize = totalResultSize;
	}
	
	

}
