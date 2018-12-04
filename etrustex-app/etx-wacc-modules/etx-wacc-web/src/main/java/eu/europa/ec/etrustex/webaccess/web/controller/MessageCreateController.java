package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.QueryParams;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messageCreate.do")
public class MessageCreateController extends AbstractController{

    @Autowired
    private PartyPropertyEditor partyPropertyEditor;

    @Override
    protected WebAction getAction() {
        return WebAction.MESSAGE_CREATE;
    }

    @Override
    protected QueryParams buildQueryParams(PageState pageState, Party party, int messageListPageSize) {

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setParty(party);
        return queryParams;
    }

    @InitBinder
    public void initBinderAll(WebDataBinder binder) {
        binder.registerCustomEditor(Party.class, partyPropertyEditor);
    }
}