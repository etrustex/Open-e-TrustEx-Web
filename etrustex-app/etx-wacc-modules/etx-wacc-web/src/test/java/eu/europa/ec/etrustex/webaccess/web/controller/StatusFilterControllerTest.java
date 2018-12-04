package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import eu.europa.ec.etrustex.webaccess.web.model.FilterFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;

public class StatusFilterControllerTest extends AbstractControllerTest {
    @Test
    public void test_getAction_should_returnOutboxAction() throws Exception {
        OutboxController controller = new OutboxController();

        // DO THE ACTUAL CALL
        assertThat(controller.getAction(), is(equalTo(WebAction.OUTBOX)));
    }

    @Test
    public void test_getMessageViewAction_should_messageViewSentAction() throws Exception {
        OutboxController controller = new OutboxController();

        // DO THE ACTUAL CALL
        assertThat(controller.getMessageViewAction(), is(equalTo(WebAction.MESSAGE_VIEW_SENT)));
    }


    @Test
    public void test_doStatusFilter_should_addStatusFilterToModelAndView_when_filterIsNotEmpty() throws Exception {
        StatusFilterController controller = new StatusFilterController();
        String status = MessageListQueryParams.MessageStatus.READ.name();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        FilterFormBean formBean = new FilterFormBean();
        formBean.setStatus(status);

        controller.pageState = new PageState();

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = controller.doStatusFilter(formBean, mockRequest);

        assertThat((String) modelAndView.getModel().get(PageState.Param.STATUS.getName()), is(equalTo(status)));
    }

}
