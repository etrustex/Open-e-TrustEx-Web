/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.webservice.utils;

import eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1.ListAvailableParties;








/**
 * @author apladap
 *
 */
public class UserRegistrationConverter {
	
	public static ListAvailableParties convertPartyToListAvailableParties(String party){
		ListAvailableParties listOfAvailableParties = new ListAvailableParties();
		listOfAvailableParties.setPartyID(party);
		
		return listOfAvailableParties;
	}

}
