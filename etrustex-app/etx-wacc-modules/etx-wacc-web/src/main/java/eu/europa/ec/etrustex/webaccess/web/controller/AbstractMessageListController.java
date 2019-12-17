package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.vo.BusinessCustomViewName;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.QueryParams;
import eu.europa.ec.etrustex.webaccess.web.model.FilterFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
abstract public class AbstractMessageListController extends AbstractController {

    @Override
    protected void postProcessGet(ModelAndView modelAndView, BusinessCustomViewName businessName) {

        pageState.setBackAction(getAction().getDo());
        pageState.setBackView(pageState.getView());

        modelAndView.addObject("messageViewAction", getMessageViewAction().getDo());

        FilterFormBean formBean = new FilterFormBean();
        formBean.setSubject(pageState.getSubject());
        formBean.setStatus(pageState.getStatus());
        modelAndView.addObject("formBean", formBean);
    }

    @Override
    protected QueryParams buildQueryParams(PageState pageState, Party party, int messageListPageSize) {
        MessageListQueryParams queryParams = new MessageListQueryParams();

        queryParams.setParty(party);

        if (!StringUtils.isEmpty(pageState.getSubject())) {
            Map<MessageListQueryParams.MessageColumn, String> filtersMap = new HashMap<>();
            filtersMap.put(MessageListQueryParams.MessageColumn.SUBJECT, pageState.getSubject());
            queryParams.setFilters(filtersMap);
        }

        if (!StringUtils.isEmpty(pageState.getStatus())) {
            queryParams.setMessageStatus(MessageListQueryParams.MessageStatus.valueOf(pageState.getStatus()));
        }

        MessageListQueryParams.SortDirection sortDirection = MessageListQueryParams.SortDirection.valueOf(pageState.getSortDirection());
        queryParams.setSortDirection(sortDirection);

        //start is used as an index to fetch messages so here we convert from page size to message index
        queryParams.setPage(Integer.valueOf(pageState.getPage()));
        queryParams.setUnreadOnly(Boolean.valueOf(pageState.getUnreadOnly()));
        queryParams.setOffset(messageListPageSize);

        return queryParams;
    }


    @RequestMapping(method = RequestMethod.POST, params = "subject")
    public ModelAndView doPost(@ModelAttribute("formBean") FilterFormBean formBean,
                               HttpServletRequest request) throws Exception {
        pageState.populate(request.getParameterMap());

        pageState.setSubject(formBean.getSubject());

        ModelAndView modelAndView = new ModelAndView(new RedirectView(getAction().getDo()));
        modelAndView.addAllObjects(pageState.getParams());
        return modelAndView;
    }

    protected abstract WebAction getMessageViewAction();
}
