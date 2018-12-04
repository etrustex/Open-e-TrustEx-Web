/**
 *
 */
package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.LabelTranslation;

import java.util.List;
import java.util.Locale;

/**
 * @author apladap
 */
public interface EtxUtilFacade {

    /**
     * Returns label translations for the required locale
     *
     * @param locale
     */
    public List<LabelTranslation> findLabelTranslationsByLocale(Locale locale);
}
