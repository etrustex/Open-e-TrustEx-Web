
package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.web.model.FilterFormBean;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/statusFilter.do")
public class StatusFilterController extends AbstractMessageListController {

    protected WebAction getAction() {
        return WebAction.OUTBOX;
    }

    @Override
    protected WebAction getMessageViewAction() {
        return WebAction.MESSAGE_VIEW_SENT;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doStatusFilter(@ModelAttribute("formBean") FilterFormBean formBean, HttpServletRequest request) throws Exception {
        pageState.populate(request.getParameterMap());
        pageState.setStatus(formBean.getStatus());
        ModelAndView modelAndView = new ModelAndView(new RedirectView(getAction().getDo()));
        modelAndView.addAllObjects(pageState.getParams());
        return modelAndView;
    }
}
