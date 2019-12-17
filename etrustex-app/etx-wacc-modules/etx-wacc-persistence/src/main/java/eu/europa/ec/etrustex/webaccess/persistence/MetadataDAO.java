/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.Metadata;

/**
 * @author apladap
 *
 */
public interface MetadataDAO extends AbstractDAO<Metadata, Long>{

	Metadata retrievePayload(long messageId);

}
