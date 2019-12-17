/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/drafts.do")
public class DraftsController extends AbstractMessageListController {

    @Override
    protected WebAction getMessageViewAction() {
        return WebAction.MESSAGE_EDIT;
    }

    @Override
    protected WebAction getAction() {
        return WebAction.DRAFTS;
    }
}
