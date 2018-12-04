package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.webaccess.model.Business;

import java.util.Comparator;

public class BusinessComparator implements Comparator<Business> {
    @Override
    public int compare(Business business1, Business business2) {
        //business name shouldn't be null (db constraints), but returns 0 not to break unit tests (backwards compatibility)
        if (business1.getName() == null || business2.getName() == null) return 0;
        return business1.getName().compareTo(business2.getName());
    }
}
