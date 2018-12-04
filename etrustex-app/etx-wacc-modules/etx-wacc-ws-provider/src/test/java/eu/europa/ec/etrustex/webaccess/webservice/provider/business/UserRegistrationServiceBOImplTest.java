package eu.europa.ec.etrustex.webaccess.webservice.provider.business;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailHandlerService;
import eu.europa.ec.etrustex.webaccess.business.api.MailTriggerService;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.utils.UserRegistrationWSScenarios;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author: micleva
 * @date: 1/15/13 12:14 PM
 * @project: ETX
 */
public class UserRegistrationServiceBOImplTest extends AbstractTest {

    @InjectMocks
    private UserRegistrationServiceBOImpl userRegistrationServiceBO = new UserRegistrationServiceBOImpl();

    @Mock
    private PartyManager partyManager;

    @Mock
    private MailHandlerService mailHandlerService;

    @Mock
    private ConnectionFactory connectionFactory;

    @Mock
    private Queue queue;

    @Mock
    private MailTriggerService mailTriggerService;

    @Test
    public void testListAvailableParties_populatedList() {

        String applicationName = "APPLICATION";

        List<String> availablePartiesList = Collections.emptyList();

        when(partyManager.findAvailablePartiesPerBusiness(applicationName)).thenReturn(availablePartiesList);

        List<String> actualPartiesList = userRegistrationServiceBO.findAvailablePartiesPerBusiness(applicationName);

        assertThat(actualPartiesList, is(sameInstance(availablePartiesList)));
        verify(partyManager).findAvailablePartiesPerBusiness(applicationName);
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void testAssignUserParty_success() throws Exception {
        String application = "APPLICATION";
        String partyId = "PARTY_REF_ID";
        String newPartyDisplayName = "MY NEW NAME";
        String email = "EMAIL";
        String firstName = "FIRST NAME";
        String lastName = "LAST NAME";
        String userLoginName = "LOGIN NAME";

        Mail userRegistrationMail = new Mail();
        String content = "This is test for the user registration service";
        String subject = "This is a test subject";

        userRegistrationMail.setContent(content);
        userRegistrationMail.setMailAddress("foo@foo.com");
        userRegistrationMail.setSentDate(Calendar.getInstance().getTime());
        userRegistrationMail.setSubject(subject);

        when(partyManager.assignUserToParty(userLoginName,
                firstName,
                lastName,
                partyId,
                newPartyDisplayName,
                application, email)).thenReturn(UserRegistrationWSScenarios.PARTY_MAPPED_SUCCESSFULLY);

        UserRegistrationWSScenarios response = userRegistrationServiceBO.handleAssignUserParty(userLoginName, firstName, lastName, partyId, newPartyDisplayName, application, email);

        assertThat(response, is(UserRegistrationWSScenarios.PARTY_MAPPED_SUCCESSFULLY));
        verify(partyManager).assignUserToParty(userLoginName,
                firstName,
                lastName,
                partyId,
                newPartyDisplayName,
                application, email);
        verifyNoMoreInteractions(partyManager);

        verifyNoMoreInteractions(mailHandlerService);

        verifyNoMoreInteractions(mailTriggerService);
    }

    @Test
    public void testAssignUserParty_NotSuccess() {
        String application = "APPLICATION";
        String partyId = "PARTY_REF_ID";
        String email = "EMAIL";
        String firstName = "FIRST NAME";
        String lastName = "LAST NAME";
        String userLoginName = "LOGIN NAME";
        String newPartyDisplayName = null;

        Mail userRegistrationMail = new Mail();
        String content = "This is test for the user registration service";
        String subject = "This is a test subject";

        userRegistrationMail.setContent(content);
        userRegistrationMail.setMailAddress("foo@foo.com");
        userRegistrationMail.setSentDate(Calendar.getInstance().getTime());
        userRegistrationMail.setSubject(subject);

        when(partyManager.assignUserToParty(userLoginName,
                firstName,
                lastName,
                partyId,
                newPartyDisplayName,
                application, email)).thenReturn(UserRegistrationWSScenarios.PARTY_UNAVAILABLE);

        UserRegistrationWSScenarios response = userRegistrationServiceBO.handleAssignUserParty(userLoginName, firstName, lastName, partyId, null, application, email);

        assertThat(response, is(UserRegistrationWSScenarios.PARTY_UNAVAILABLE));
        verify(partyManager).assignUserToParty(userLoginName,
                firstName,
                lastName,
                partyId,
                newPartyDisplayName,
                application, email);
        verifyNoMoreInteractions(partyManager);

        verifyZeroInteractions(mailHandlerService);
    }
}
