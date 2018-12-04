package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.LabelTranslation;
import eu.europa.ec.etrustex.webaccess.persistence.LabelTranslationDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Locale;

@Repository
public class LabelTranslationDAOImpl extends AbstractDAOImpl<LabelTranslation, Long> implements LabelTranslationDAO {
	
	private final static String QL_FIND_LABEL_TRANSLATIONS_BY_LOCALE = "SELECT lt FROM LabelTranslation lt WHERE lt.language.code = :code";

	@Override
	public List<LabelTranslation> findLabelTranslationsByLocale(Locale locale) {
			TypedQuery<LabelTranslation> query = entityManager.createQuery(QL_FIND_LABEL_TRANSLATIONS_BY_LOCALE, LabelTranslation.class);
			query.setParameter("code", locale.getLanguage());
            return query.getResultList();
	}
}

