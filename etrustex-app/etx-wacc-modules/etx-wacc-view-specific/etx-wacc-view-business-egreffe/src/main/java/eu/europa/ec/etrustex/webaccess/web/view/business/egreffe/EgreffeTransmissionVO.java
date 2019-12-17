package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author apladap
 *
 */

public class EgreffeTransmissionVO {
	private List<EgreffeWork> listOfWorks;
	private Date adoptionDate;
	private String comments;
	
	public EgreffeTransmissionVO(){
		if(listOfWorks == null){
			listOfWorks = new ArrayList<>();
		}
	}
	
	public List<EgreffeWork> getListOfWorks() {
		return listOfWorks;
	}

	public void setListOfWorks(List<EgreffeWork> listOfWorks) {
		this.listOfWorks = listOfWorks;
	}
	
	public void addWork(EgreffeWork work){
		listOfWorks.add(work);
	}

	public Date getAdoptionDate() {
		return adoptionDate;
	}

	public void setAdoptionDate(Date adoptionDate) {
		this.adoptionDate = adoptionDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
