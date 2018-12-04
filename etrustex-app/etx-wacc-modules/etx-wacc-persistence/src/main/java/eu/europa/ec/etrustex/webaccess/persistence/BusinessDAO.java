package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.Business;

import java.util.List;

public interface BusinessDAO extends AbstractDAO<Business, Long> {

    List<Business> getAllBusinesses();
}