/**
 *
 */
package eu.europa.ec.etrustex.webaccess.business.api;

import javax.xml.transform.Source;

/**
 * @author apladap
 */
public interface MetadataTransformer {

    public String transform(Source xmlSource, Source xsltSource);
}
