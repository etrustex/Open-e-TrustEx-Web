package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.api.EtxUtilFacade;
import eu.europa.ec.etrustex.webaccess.model.LabelTranslation;
import eu.europa.ec.etrustex.webaccess.persistence.LabelTranslationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;


@Service
@Transactional
public class EtxUtilFacadeImpl implements EtxUtilFacade {

    @Autowired
    private LabelTranslationDAO labelTranslationDAO;


    public List<LabelTranslation> findLabelTranslationsByLocale(Locale locale) {
        return labelTranslationDAO.findLabelTranslationsByLocale(locale);
    }
}
