package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe;

import java.util.HashMap;
import java.util.List;

/**
 * @author apladap
 *
 */
public class EgreffeWork {
	
	private String comments;
	private String summaryTittle;
	private String act;
	private String version;
	private List<String> languages;
	private List<Integer> partNumbers;
	private HashMap<String, HashMap<Integer,List<EgreffeDocument>>> documentsList;
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSummaryTittle() {
		return summaryTittle;
	}
	public void setSummaryTittle(String summaryTittle) {
		this.summaryTittle = summaryTittle;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	public List<Integer> getPartNumbers() {
		return partNumbers;
	}
	public void setPartNumbers(List<Integer> partNumbers) {
		this.partNumbers = partNumbers;
	}
	public HashMap<String, HashMap<Integer, List<EgreffeDocument>>> getDocumentsList() {
		return documentsList;
	}
	public void setDocumentsList(
			HashMap<String, HashMap<Integer, List<EgreffeDocument>>> documentsList) {
		this.documentsList = documentsList;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	

}
