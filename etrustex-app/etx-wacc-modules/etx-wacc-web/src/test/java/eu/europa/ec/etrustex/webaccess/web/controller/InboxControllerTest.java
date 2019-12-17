package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

import static eu.europa.ec.etrustex.webaccess.web.ModelHelper.buildRole;
import static org.mockito.Mockito.*;

public class InboxControllerTest extends AbstractControllerTest {

    @Mock
    private UserSessionContext userSessionContext;

    @InjectMocks
    private InboxController inboxController = new InboxController();

    @Test
    public void test_getAction_should_returnInboxAction() throws Exception {

        // DO THE ACTUAL CALL
        assertThat(inboxController.getAction(), is(equalTo(WebAction.INBOX)));
    }

    @Test
    public void test_getMessageViewAction_should_messageViewReceivedAction() throws Exception {

        // DO THE ACTUAL CALL
        assertThat(inboxController.getMessageViewAction(), is(equalTo(WebAction.MESSAGE_VIEW_RECEIVED)));
    }

    @Test
    public void test_changeOperatorParty_should_changeParty_when_partyOperator() throws Exception {

        Party otherParty = new Party();
        otherParty.setId(11L);

        UserRole userRole1 = new UserRole();
        userRole1.setParty(otherParty);
        userRole1.setId(21L);
        userRole1.setRole(buildRole("operator", Role.Type.OPERATOR, Collections.<Privilege>emptySet()));
        userRole1.setParty(otherParty);

        Party newParty = new Party();
        newParty.setId(12L);
        UserRole userRole2 = new UserRole();
        userRole2.setParty(otherParty);
        userRole2.setId(22L);
        userRole2.setRole(buildRole("operator", Role.Type.OPERATOR, Collections.<Privilege>emptySet()));
        userRole2.setParty(newParty);

        inboxController.pageState = new PageState();

        when(userSessionContext.getMessageParties()).thenReturn(Arrays.asList(otherParty, newParty));

        Long selectedPartyId = newParty.getId();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        ModelAndView modelAndView = new ModelAndView();

        InboxController spiedInbox = spy(inboxController);
        doReturn(modelAndView).when(spiedInbox).doGet(argThat(sameInstance(mockRequest)));

        // DO THE ACTUAL CALL
        ModelAndView actualModelAndView = spiedInbox.changeOperatorParty(selectedPartyId, mockRequest);

        assertThat(actualModelAndView, is(sameInstance(modelAndView)));

        InOrder inOrder = inOrder(spiedInbox, userSessionContext);
        inOrder.verify(spiedInbox).changeOperatorParty(selectedPartyId, mockRequest);
        inOrder.verify(userSessionContext).getMessageParties();
        inOrder.verify(spiedInbox).doGet(mockRequest);

        assertThat(inboxController.pageState.getPartyId(), is(equalTo(selectedPartyId.toString())));

        verifyNoMoreInteractions(spiedInbox, userSessionContext, userSessionContext);
    }

    @Test
    public void test_changeOperatorParty_should_changeParty_when_NotAllowedParty() throws Exception {

        Party otherParty = new Party();
        otherParty.setId(11L);

        UserRole userRole1 = new UserRole();
        userRole1.setParty(otherParty);
        userRole1.setId(21L);
        userRole1.setRole(buildRole("operator", Role.Type.OPERATOR, Collections.<Privilege>emptySet()));

        Party newParty = new Party();
        newParty.setId(12L);
        UserRole userRole2 = new UserRole();
        userRole2.setId(22L);
        userRole2.setRole(buildRole("sys admin", Role.Type.SYSTEM_ADMIN, Collections.<Privilege>emptySet()));
        userRole2.setParty(newParty);

        when(userSessionContext.getMessageParties()).thenReturn(Arrays.asList(otherParty));

        User user = new User();
        user.setId(2345L);
        user.setLogin("testUsr");
        when(userSessionContext.getUser()).thenReturn(user);

        Long selectedPartyId = newParty.getId();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        InboxController spiedInbox = spy(inboxController);

        when(mockRequest.getRequestURI()).thenReturn("/e-trustex/inbox.do");
        when(mockRequest.getQueryString()).thenReturn("test=10");

        // DO THE ACTUAL CALL
        ModelAndView actualModelAndView = spiedInbox.changeOperatorParty(selectedPartyId, mockRequest);

        assertThat(actualModelAndView.getView(), is(notNullValue()));
        assertThat(actualModelAndView.getView(), is(instanceOf(RedirectView.class)));
        assertThat(((RedirectViewWithLogging) actualModelAndView.getView()).getUrl(), is("error/error403.do"));

        InOrder inOrder = inOrder(spiedInbox, userSessionContext, userSessionContext);
        inOrder.verify(spiedInbox).changeOperatorParty(selectedPartyId, mockRequest);
        inOrder.verify(userSessionContext).getMessageParties();
        inOrder.verify(userSessionContext).getUser();

        verifyNoMoreInteractions(spiedInbox, userSessionContext, userSessionContext);
    }

}
