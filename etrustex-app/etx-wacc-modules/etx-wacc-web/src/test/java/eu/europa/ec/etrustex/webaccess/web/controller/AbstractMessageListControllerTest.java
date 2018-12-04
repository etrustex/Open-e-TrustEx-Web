package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.QueryParams;
import eu.europa.ec.etrustex.webaccess.web.model.FilterFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class AbstractMessageListControllerTest extends AbstractControllerTest {

    private static final WebAction ACTION = WebAction.INBOX;
    private static final WebAction VIEW_ACTION = WebAction.MESSAGE_VIEW_RECEIVED_WS;

    @InjectMocks
    private AbstractMessageListController controller = new AbstractMessageListController() {
        @Override
        protected WebAction getMessageViewAction() {
            return VIEW_ACTION;
        }

        @Override
        protected WebAction getAction() {
            return ACTION;
        }
    };

    @Test
    public void test_doGetExtra_should_addMandatoryObjectsToModel() throws Exception {
        String subject = "subject";
        String status = "status";
        ModelAndView modelAndView = new ModelAndView();
        PageState pageState = new PageState();
        pageState.setSubject(subject);
        pageState.setStatus(status);
        controller.pageState = pageState;

        // DO THE ACTUAL CALL
        controller.postProcessGet(modelAndView, null);

        FilterFormBean formBean = (FilterFormBean) modelAndView.getModelMap().get("formBean");
        assertThat(formBean, is(notNullValue()));
        assertThat(formBean.getSubject(), is(equalTo(subject)));
        assertThat(formBean.getStatus(), is(equalTo(status)));
        assertThat(modelAndView.getModelMap().get("messageViewAction"), is(notNullValue()));
        assertThat(modelAndView.getModel().size(), is(2));
    }

    @Test
    public void test_doGetExtra_should_setBackPropertiesForPageState() throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        controller.pageState = new PageState();

        // DO THE ACTUAL CALL
        controller.postProcessGet(modelAndView, null);

        assertThat(controller.pageState.getBackAction(), is(notNullValue()));
        assertThat(controller.pageState.getBackView(), is(notNullValue()));
    }

    @Test
    public void test_buildQueryParams_should_createMessageListQueryParams() throws Exception {
        Map<String, String[]> requestMap = new HashMap<>();
        String subject = "subject13";
        String status = MessageListQueryParams.MessageStatus.READ.name();
        requestMap.put(PageState.Param.SUBJECT.getName(), new String[]{subject});
        requestMap.put(PageState.Param.SORT_DIRECTION.getName(), new String[]{"DESC"});
        requestMap.put(PageState.Param.UNREAD_ONLY.getName(), new String[]{"true"});
        requestMap.put(PageState.Param.PAGE.getName(), new String[]{"43"});
        requestMap.put(PageState.Param.STATUS.getName(), new String[]{status});

        PageState pageState = new PageState();
        pageState.populate(requestMap);

        Party party = new Party();

        // DO THE ACTUAL CALL
        QueryParams queryParams = controller.buildQueryParams(pageState, party, 12);

        assertThat(queryParams, is(instanceOf(MessageListQueryParams.class)));
        MessageListQueryParams messageListQueryParams = (MessageListQueryParams) queryParams;
        assertThat(messageListQueryParams.getPage(), is(equalTo(43)));
        assertThat(messageListQueryParams.getFilters().get(MessageListQueryParams.MessageColumn.SUBJECT), is(equalTo(subject)));
        assertThat(messageListQueryParams.getSortDirection(), is(equalTo(MessageListQueryParams.SortDirection.DESC)));
        assertThat(messageListQueryParams.isUnreadOnly(), is(equalTo(true)));
        assertThat(messageListQueryParams.getParty(), is(equalTo(party)));
        assertThat(messageListQueryParams.getMessageStatus(), is(equalTo(MessageListQueryParams.MessageStatus.READ)));
        assertThat(messageListQueryParams.getOffset(), is(equalTo(12)));
    }

    @Test
    public void test_doPost_should_addSubjectFilterToModelAndView_when_filterIsNotEmpty() throws Exception {
        String subject = "subject13";
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        FilterFormBean formBean = new FilterFormBean();
        formBean.setSubject(subject);

        controller.pageState = new PageState();

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = controller.doPost(formBean, mockRequest);

        assertThat((String) modelAndView.getModel().get(PageState.Param.SUBJECT.getName()), is(equalTo(subject)));
    }

    @Test
    public void test_doPost_should_notAddSubjectFilterToModelAndView_when_filterIsEmpty() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        FilterFormBean formBean = new FilterFormBean();
        formBean.setSubject(null);

        controller.pageState = new PageState();

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = controller.doPost(formBean, mockRequest);

        assertThat(modelAndView.getModel().get(PageState.Param.SUBJECT.getName()), is(nullValue()));
    }
}
