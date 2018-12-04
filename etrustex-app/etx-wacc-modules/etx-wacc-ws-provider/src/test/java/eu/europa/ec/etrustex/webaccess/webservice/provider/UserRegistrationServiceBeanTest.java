package eu.europa.ec.etrustex.webaccess.webservice.provider;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.utils.UserRegistrationWSScenarios;
import eu.europa.ec.etrustex.webaccess.webservice.provider.business.UserRegistrationServiceBO;
import eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1.*;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author apladap
 */
@SuppressWarnings("unchecked")
public class UserRegistrationServiceBeanTest extends AbstractTest {

    @InjectMocks
    private UserRegistrationServiceBean userRegistrationServiceBean = new UserRegistrationServiceBean();

    @Mock
    private UserRegistrationServiceBO userRegistrationServiceBO;

    @Test
    public void testListAvailableParties_populatedList() {
        ListAvailablePartiesReq parameters = new ListAvailablePartiesReq();
        parameters.setApplication("APPLICATION");

        List<String> availablePartiesList = new ArrayList<>();
        availablePartiesList.add("Party1");
        availablePartiesList.add("Party2");


        when(userRegistrationServiceBO.findAvailablePartiesPerBusiness(parameters.getApplication())).thenReturn(availablePartiesList);

        ListAvailablePartiesResp response = userRegistrationServiceBean.listAvailableParties(parameters);

        assertThat(response.getListAvailableParties(), notNullValue());
        assertThat(response.getListAvailableParties().size(), is(2));
        assertThat(response.getListAvailableParties(), (Matcher) hasItem((hasProperty("partyID", is("Party1")))));
        assertThat(response.getListAvailableParties(), (Matcher) hasItem((hasProperty("partyID", is("Party2")))));

        verify(userRegistrationServiceBO).findAvailablePartiesPerBusiness(parameters.getApplication());
        verifyNoMoreInteractions(userRegistrationServiceBO);
    }

    @Test
    public void testListAvailableParties_emptyList() {
        ListAvailablePartiesReq parameters = new ListAvailablePartiesReq();
        parameters.setApplication("APPLICATION");

        List<String> availablePartiesList = new ArrayList<>();


        when(userRegistrationServiceBO.findAvailablePartiesPerBusiness(parameters.getApplication())).thenReturn(availablePartiesList);

        ListAvailablePartiesResp resposne = userRegistrationServiceBean.listAvailableParties(parameters);

        assertThat(resposne.getListAvailableParties(), notNullValue());
        assertThat(resposne.getListAvailableParties().size(), is(0));

        verify(userRegistrationServiceBO).findAvailablePartiesPerBusiness(parameters.getApplication());
        verifyNoMoreInteractions(userRegistrationServiceBO);
    }

    @Test
    public void testListAvailableParties_exception() {
        thrown.expect(RuntimeException.class);

        ListAvailablePartiesReq parameters = new ListAvailablePartiesReq();
        parameters.setApplication("APPLICATION");

        when(userRegistrationServiceBO.findAvailablePartiesPerBusiness(parameters.getApplication())).thenThrow(RuntimeException.class);

        userRegistrationServiceBean.listAvailableParties(parameters);
    }

    @Test
    public void testAssignUserParty_success() throws Exception {
        AssignUserPartyReq parameters = new AssignUserPartyReq();
        parameters.setAssignUserParty(new AssignUserParty());
        AssignUserParty assignUserParty = parameters.getAssignUserParty();
        assignUserParty.setApplication("APPLICATION");
        assignUserParty.setPartyID("PARTY_REF_ID");
        assignUserParty.setNewPartyDisplayName("MY NEW NAME");
        assignUserParty.setUserEmail("EMAIL");
        assignUserParty.setUserFirstName("FIRST NAME");
        assignUserParty.setUserLastName("LAST NAME");
        assignUserParty.setUserLogin("LOGIN NAME");

        Mail userRegistrationMail = new Mail();
        String content = "This is test for the user registration service";
        String subject = "This is a test subject";

        userRegistrationMail.setContent(content);
        userRegistrationMail.setMailAddress("foo@foo.com");
        userRegistrationMail.setSentDate(Calendar.getInstance().getTime());
        userRegistrationMail.setSubject(subject);


        when(userRegistrationServiceBO.handleAssignUserParty(assignUserParty.getUserLogin(),
                assignUserParty.getUserFirstName(),
                assignUserParty.getUserLastName(),
                assignUserParty.getPartyID(),
                assignUserParty.getNewPartyDisplayName(),
                assignUserParty.getApplication(),
                assignUserParty.getUserEmail())).thenReturn(UserRegistrationWSScenarios.PARTY_MAPPED_SUCCESSFULLY);

        AssignUserPartyResp response = userRegistrationServiceBean.assignUserParty(parameters);

        assertThat(response.getStatus(), is(AssignUserPartyStatus.SUCCESS));

        verify(userRegistrationServiceBO).handleAssignUserParty(assignUserParty.getUserLogin(),
                assignUserParty.getUserFirstName(),
                assignUserParty.getUserLastName(),
                assignUserParty.getPartyID(),
                assignUserParty.getNewPartyDisplayName(),
                assignUserParty.getApplication(), assignUserParty.getUserEmail());
        verifyNoMoreInteractions(userRegistrationServiceBO);
    }

    @Test
    public void testAssignUserParty_partyUnavailable() {
        AssignUserPartyReq parameters = new AssignUserPartyReq();
        parameters.setAssignUserParty(new AssignUserParty());
        parameters.getAssignUserParty().setApplication("APPLICATION");
        parameters.getAssignUserParty().setPartyID("PARTY_REF_ID");
        parameters.getAssignUserParty().setNewPartyDisplayName("MY NEW PARTY NAME");
        parameters.getAssignUserParty().setUserEmail("EMAIL");
        parameters.getAssignUserParty().setUserFirstName("FIRST NAME");
        parameters.getAssignUserParty().setUserLastName("LAST NAME");
        parameters.getAssignUserParty().setUserLogin("LOGIN NAME");

        Mail userRegistrationMail = new Mail();
        String content = "This is test for the user registration service";
        String subject = "This is a test subject";

        userRegistrationMail.setContent(content);
        userRegistrationMail.setMailAddress("foo@foo.com");
        userRegistrationMail.setSentDate(Calendar.getInstance().getTime());
        userRegistrationMail.setSubject(subject);

        when(userRegistrationServiceBO.handleAssignUserParty(parameters.getAssignUserParty().getUserLogin(),
                parameters.getAssignUserParty().getUserFirstName(),
                parameters.getAssignUserParty().getUserLastName(),
                parameters.getAssignUserParty().getPartyID(),
                parameters.getAssignUserParty().getNewPartyDisplayName(),
                parameters.getAssignUserParty().getApplication(), parameters.getAssignUserParty().getUserEmail())).thenReturn(UserRegistrationWSScenarios.PARTY_UNAVAILABLE);

        AssignUserPartyResp response = userRegistrationServiceBean.assignUserParty(parameters);

        assertThat(response.getStatus(), is(AssignUserPartyStatus.PARTY_NOT_AVAILABLE));

        verify(userRegistrationServiceBO).handleAssignUserParty(parameters.getAssignUserParty().getUserLogin(),
                parameters.getAssignUserParty().getUserFirstName(),
                parameters.getAssignUserParty().getUserLastName(),
                parameters.getAssignUserParty().getPartyID(),
                parameters.getAssignUserParty().getNewPartyDisplayName(),
                parameters.getAssignUserParty().getApplication(), parameters.getAssignUserParty().getUserEmail());
        verifyNoMoreInteractions(userRegistrationServiceBO);
    }

    @Test
    public void testAssignUserParty_partyUnknown() {
        AssignUserPartyReq parameters = new AssignUserPartyReq();
        parameters.setAssignUserParty(new AssignUserParty());
        parameters.getAssignUserParty().setApplication("APPLICATION");
        parameters.getAssignUserParty().setPartyID("PARTY_REF_ID");
        parameters.getAssignUserParty().setNewPartyDisplayName("MY NEW PARTY NAME");
        parameters.getAssignUserParty().setUserEmail("EMAIL");
        parameters.getAssignUserParty().setUserFirstName("FIRST NAME");
        parameters.getAssignUserParty().setUserLastName("LAST NAME");
        parameters.getAssignUserParty().setUserLogin("LOGIN NAME");

        Mail userRegistrationMail = new Mail();
        String content = "This is test for the user registration service";
        String subject = "This is a test subject";

        userRegistrationMail.setContent(content);
        userRegistrationMail.setMailAddress("foo@foo.com");
        userRegistrationMail.setSentDate(Calendar.getInstance().getTime());
        userRegistrationMail.setSubject(subject);


        when(userRegistrationServiceBO.handleAssignUserParty(parameters.getAssignUserParty().getUserLogin(),
                parameters.getAssignUserParty().getUserFirstName(),
                parameters.getAssignUserParty().getUserLastName(),
                parameters.getAssignUserParty().getPartyID(),
                parameters.getAssignUserParty().getNewPartyDisplayName(),
                parameters.getAssignUserParty().getApplication(), parameters.getAssignUserParty().getUserEmail())).thenReturn(UserRegistrationWSScenarios.PARTY_UNKNOWN);

        AssignUserPartyResp response = userRegistrationServiceBean.assignUserParty(parameters);

        assertThat(response.getStatus(), is(AssignUserPartyStatus.PARTY_DOES_NOT_EXIST));

        verify(userRegistrationServiceBO).handleAssignUserParty(parameters.getAssignUserParty().getUserLogin(),
                parameters.getAssignUserParty().getUserFirstName(),
                parameters.getAssignUserParty().getUserLastName(),
                parameters.getAssignUserParty().getPartyID(),
                parameters.getAssignUserParty().getNewPartyDisplayName(),
                parameters.getAssignUserParty().getApplication(), parameters.getAssignUserParty().getUserEmail());
        verifyNoMoreInteractions(userRegistrationServiceBO);
    }

    @Test
    public void testAssignUserParty_userAlreadyAssigned() {
        AssignUserPartyReq parameters = new AssignUserPartyReq();
        parameters.setAssignUserParty(new AssignUserParty());
        parameters.getAssignUserParty().setApplication("APPLICATION");
        parameters.getAssignUserParty().setPartyID("PARTY_REF_ID");
        parameters.getAssignUserParty().setNewPartyDisplayName("MY NEW PARTY NAME");
        parameters.getAssignUserParty().setUserEmail("EMAIL");
        parameters.getAssignUserParty().setUserFirstName("FIRST NAME");
        parameters.getAssignUserParty().setUserLastName("LAST NAME");
        parameters.getAssignUserParty().setUserLogin("LOGIN NAME");

        Mail userRegistrationMail = new Mail();
        String content = "This is test for the user registration service";
        String subject = "This is a test subject";

        userRegistrationMail.setContent(content);
        userRegistrationMail.setMailAddress("foo@foo.com");
        userRegistrationMail.setSentDate(Calendar.getInstance().getTime());
        userRegistrationMail.setSubject(subject);

        when(userRegistrationServiceBO.handleAssignUserParty(parameters.getAssignUserParty().getUserLogin(),
                parameters.getAssignUserParty().getUserFirstName(),
                parameters.getAssignUserParty().getUserLastName(),
                parameters.getAssignUserParty().getPartyID(),
                parameters.getAssignUserParty().getNewPartyDisplayName(),
                parameters.getAssignUserParty().getApplication(), parameters.getAssignUserParty().getUserEmail())).thenReturn(UserRegistrationWSScenarios.USER_ALREADY_ASSIGNED);

        AssignUserPartyResp response = userRegistrationServiceBean.assignUserParty(parameters);

        assertThat(response.getStatus(), is(AssignUserPartyStatus.USER_ALREADY_ASSIGNED));

        verify(userRegistrationServiceBO).handleAssignUserParty(parameters.getAssignUserParty().getUserLogin(),
                parameters.getAssignUserParty().getUserFirstName(),
                parameters.getAssignUserParty().getUserLastName(),
                parameters.getAssignUserParty().getPartyID(),
                parameters.getAssignUserParty().getNewPartyDisplayName(),
                parameters.getAssignUserParty().getApplication(), parameters.getAssignUserParty().getUserEmail());
        verifyNoMoreInteractions(userRegistrationServiceBO);
    }

    @Test(expected = RuntimeException.class)
    public void testAssignUserParty_emailRequired() throws Exception {
        AssignUserPartyReq parameters = new AssignUserPartyReq();
        parameters.setAssignUserParty(new AssignUserParty());
        parameters.getAssignUserParty().setApplication("APPLICATION");
        parameters.getAssignUserParty().setPartyID("PARTY_REF_ID");
        parameters.getAssignUserParty().setNewPartyDisplayName("MY NEW PARTY NAME");
        parameters.getAssignUserParty().setUserEmail(null);
        parameters.getAssignUserParty().setUserFirstName("FIRST NAME");
        parameters.getAssignUserParty().setUserLastName("LAST NAME");
        parameters.getAssignUserParty().setUserLogin("LOGIN NAME");

        Mail userRegistrationMail = new Mail();
        String content = "This is test for the user registration service";
        String subject = "This is a test subject";

        userRegistrationMail.setContent(content);
        userRegistrationMail.setMailAddress("foo@foo.com");
        userRegistrationMail.setSentDate(Calendar.getInstance().getTime());
        userRegistrationMail.setSubject(subject);


        when(userRegistrationServiceBO.handleAssignUserParty(parameters.getAssignUserParty().getUserLogin(),
                parameters.getAssignUserParty().getUserFirstName(),
                parameters.getAssignUserParty().getUserLastName(),
                parameters.getAssignUserParty().getPartyID(),
                parameters.getAssignUserParty().getNewPartyDisplayName(),
                parameters.getAssignUserParty().getApplication(), parameters.getAssignUserParty().getUserEmail())).thenReturn(UserRegistrationWSScenarios.USER_EMAIL_REQUIRED);

        userRegistrationServiceBean.assignUserParty(parameters);
    }

    @Test
    public void testAssignUserParty_validateUserRegistrationWSScenarios() {
        //validate that only elements that we can parse in the assignUserParty are contained by the UserRegistrationWSScenarios
        //if this test fails, go and check  the method UserRegistrationServiceBean.assignUserParty that works as expected
        UserRegistrationWSScenarios[] userRegistrationWSScenariosValues = UserRegistrationWSScenarios.values();

        assertThat(userRegistrationWSScenariosValues.length, is(5));
        assertThat(userRegistrationWSScenariosValues, arrayContainingInAnyOrder(
                UserRegistrationWSScenarios.PARTY_MAPPED_SUCCESSFULLY,
                UserRegistrationWSScenarios.PARTY_UNAVAILABLE,
                UserRegistrationWSScenarios.PARTY_UNKNOWN,
                UserRegistrationWSScenarios.USER_ALREADY_ASSIGNED,
                UserRegistrationWSScenarios.USER_EMAIL_REQUIRED));
    }
}
