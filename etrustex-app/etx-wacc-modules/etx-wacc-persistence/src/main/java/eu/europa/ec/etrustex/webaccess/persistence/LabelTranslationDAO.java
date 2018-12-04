package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.LabelTranslation;

import java.util.List;
import java.util.Locale;



public interface LabelTranslationDAO extends AbstractDAO<LabelTranslation, Long>{

	public List<LabelTranslation> findLabelTranslationsByLocale(Locale locale);

}
