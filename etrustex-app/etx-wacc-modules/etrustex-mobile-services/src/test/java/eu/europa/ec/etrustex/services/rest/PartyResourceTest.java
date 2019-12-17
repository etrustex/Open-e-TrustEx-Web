package eu.europa.ec.etrustex.services.rest;

import eu.europa.ec.etrustex.services.business.MailboxService;
import eu.europa.ec.etrustex.services.business.MessageService;
import eu.europa.ec.etrustex.services.business.PartyService;
import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.services.model.*;
import eu.europa.ec.etrustex.services.security.UserContext;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class PartyResourceTest extends AbstractTest {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @InjectMocks
    private PartyResource partyResource;

    @Mock
    private PartyService partyService;

    @Mock
    private UserService userService;

    @Mock
    private MailboxService mailboxService;

    @Mock
    private MessageService messageService;

    @Test
    public void test_getPartiesByUsername_nullParties_should_return_empty_list() {
        UserDetails currentUser = new UserContext("userId");

        when(partyService.getPartiesByUser("userId")).thenReturn(null);

        //ACTUAL CALL
        ResponseEntity<RestParties> result = partyResource.getPartiesByUsername(currentUser);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody().getPartyList().size(), is(0));

        verify(partyService).getPartiesByUser("userId");
        verifyNoMoreInteractions(partyService);
    }

    @Test
    public void test_getPartiesByUsername_emptyParties_should_return_empty_list() {
        UserDetails currentUser = new UserContext("userId");
        List<Party> parties = new ArrayList<>();

        when(partyService.getPartiesByUser("userId")).thenReturn(parties);

        //ACTUAL CALL
        ResponseEntity<RestParties> result = partyResource.getPartiesByUsername(currentUser);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody().getPartyList().size(), is(0));

        verify(partyService).getPartiesByUser("userId");
        verifyNoMoreInteractions(partyService);
    }

    @Test
    public void test_getPartiesByUsername_should_return_restParties() {
        UserDetails currentUser = new UserContext("userId");
        List<Party> parties = new ArrayList<>();

        Party party1 = new Party();
        party1.setId(1L);
        party1.setDisplayName("Party1");
        parties.add(party1);

        Party party2 = new Party();
        party2.setId(2L);
        party2.setDisplayName("Party2");
        parties.add(party2);

        RestParty restParty1 = new RestParty();
        restParty1.setId("1");
        restParty1.setName("Party1");

        RestParty restParty2 = new RestParty();
        restParty2.setId("2");
        restParty2.setName("Party2");

        when(partyService.getPartiesByUser("userId")).thenReturn(parties);

        //ACTUAL CALL
        ResponseEntity<RestParties> result = partyResource.getPartiesByUsername(currentUser);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody().getPartyList().size(), is(2));
        assertThat(result.getBody().getPartyList().get(0), is(restParty1));
        assertThat(result.getBody().getPartyList().get(1), is(restParty2));

        verify(partyService).getPartiesByUser("userId");
        verifyNoMoreInteractions(partyService);
    }

    @Test
    public void test_getInboxCountersByParty_invalid_party_should_return_400_code() {
        UserDetails currentUser = new UserContext("userId");

        //ACTUAL CALL
        ResponseEntity<RestInboxCounters> result = partyResource.getInboxCountersByParty(currentUser, "12d");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void test_getInboxCountersByParty_non_existing_party_should_return_404_code() {
        UserDetails currentUser = new UserContext("userId");
        Long partyId = 1L;

        when(partyService.getPartyById(partyId)).thenReturn(null);

        //ACTUAL CALL
        ResponseEntity<RestInboxCounters> result = partyResource.getInboxCountersByParty(currentUser, String.valueOf(partyId));

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

        verify(partyService).getPartyById(partyId);
        verifyNoMoreInteractions(partyService);
    }

    @Test
    public void test_getInboxCountersByParty_should_return_200_code() {
        UserDetails currentUser = new UserContext("userId");
        User user = new User();
        Party party = new Party();
        Long partyId = 1L;
        String userId = "userId";
        RestInboxCounters restInboxCounters = new RestInboxCounters();
        restInboxCounters.setAll("20");
        restInboxCounters.setRead("15");

        when(partyService.getPartyById(partyId)).thenReturn(party);
        when(userService.getUserDetails(userId)).thenReturn(user);
        when(mailboxService.getInboxCountersByParty(party, user)).thenReturn(restInboxCounters);

        //ACTUAL CALL
        ResponseEntity<RestInboxCounters> result = partyResource.getInboxCountersByParty(currentUser, String.valueOf(partyId));

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(restInboxCounters));

        verify(partyService).getPartyById(partyId);
        verifyNoMoreInteractions(partyService);

        verify(userService).getUserDetails(userId);
        verifyNoMoreInteractions(userService);

        verify(mailboxService).getInboxCountersByParty(party, user);
        verifyNoMoreInteractions(mailboxService);
    }

    @Test
    public void test_getSentCountersByParty_invalid_party_should_return_400_code() {

        //ACTUAL CALL
        ResponseEntity<RestSentCounters> result = partyResource.getSentCountersByParty("10L");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }


    @Test
    public void test_getSentCountersByParty_non_existing_party_should_return_404_code() {
        Long partyId = 1L;

        when(partyService.getPartyById(partyId)).thenReturn(null);

        //ACTUAL CALL
        ResponseEntity<RestSentCounters> result = partyResource.getSentCountersByParty(String.valueOf(partyId));

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

        verify(partyService).getPartyById(partyId);
        verifyNoMoreInteractions(partyService);
    }

    @Test
    public void test_getSentCountersByParty_should_return_200_code() {
        Party party = new Party();
        Long partyId = 1L;
        RestSentCounters restSentCounters = new RestSentCounters();
        restSentCounters.setAll("30");
        restSentCounters.setFailed("5");
        restSentCounters.setRead("10");
        restSentCounters.setNone("2");
        restSentCounters.setDelivered("10");

        when(partyService.getPartyById(partyId)).thenReturn(party);
        when(mailboxService.getSentCountersByParty(party)).thenReturn(restSentCounters);

        //ACTUAL CALL
        ResponseEntity<RestSentCounters> result = partyResource.getSentCountersByParty(String.valueOf(partyId));

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(restSentCounters));

        verify(partyService).getPartyById(partyId);
        verifyNoMoreInteractions(partyService);

        verify(mailboxService).getSentCountersByParty(party);
        verifyNoMoreInteractions(mailboxService);
    }

    @Test
    public void test_getSentMessages_invalid_party_should_return_400_code() {
        //ACTUAL CALL
        ResponseEntity<RestMessages> result = partyResource.getSentMessages("12d", "1", "10", "read", "Subject", "asc");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void test_getSentMessages_non_existing_party_should_return_404_code() {
        Long partyId = 1L;

        when(partyService.getPartyById(partyId)).thenReturn(null);

        //ACTUAL CALL
        ResponseEntity<RestMessages> result = partyResource.getSentMessages("1", "1", "10", "read", "Subject", "asc");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

        verify(partyService).getPartyById(partyId);
        verifyNoMoreInteractions(partyService);
    }

    @Test
    public void test_getSentMessages_should_return_200_code() {
        Party party = new Party();
        Long partyId = 1L;

        RestMessages restMessages = new RestMessages();
        RestMessage restMessage = new RestMessage();

        restMessage.setId("1");
        restMessage.setSender("Sender");
        restMessage.setRecipient("Receiver");
        restMessage.setReceiptDate(formatter.format(new Date()));
        restMessage.setSubject("Subject");
        restMessage.setContent("Content");
        restMessage.setAttachmentCount("10");
        restMessage.setTotalAttachmentSize("2019");
        restMessage.setSentDate(formatter.format(new Date()));
        restMessage.setStatus("Status");
        restMessage.setExpired("Expired");
        restMessage.setSigned("Signed");
        restMessage.setContentEncrypted("Content encrypted");
        restMessage.setSentDate(formatter.format(new Date()));
        restMessage.setStatusReceiptDate(formatter.format(new Date()));

        restMessages.addMessageListItem(restMessage);
        restMessages.setMessageCount("1");
        restMessages.setHasMoreMessages(true);

        when(partyService.getPartyById(partyId)).thenReturn(party);
        when(messageService.getMessages(Message.MessageState.SENT, party, "1", "10", "read", "Subject", "asc")).thenReturn(restMessages);

        //ACTUAL CALL
        ResponseEntity<RestMessages> result = partyResource.getSentMessages("1", "1", "10", "read", "Subject", "asc");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(restMessages));

        verify(partyService).getPartyById(partyId);
        verifyNoMoreInteractions(partyService);

        verify(messageService).getMessages(Message.MessageState.SENT, party, "1", "10", "read", "Subject", "asc");
        verifyNoMoreInteractions(messageService);
    }

    @Test
    public void test_getInboxMessages_invalid_party_should_return_400_code() {
        //ACTUAL CALL
        ResponseEntity<RestMessages> result = partyResource.getInboxMessages("15d", "1", "12", "available", "Subj", "asc");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void test_getInboxMessages_non_existing_party_should_return_404_code() {
        Long partyId = 1L;

        when(partyService.getPartyById(partyId)).thenReturn(null);

        //ACTUAL CALL
        ResponseEntity<RestMessages> result = partyResource.getInboxMessages("1", "1", "12", "available", "Subj", "asc");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

        verify(partyService).getPartyById(partyId);
        verifyNoMoreInteractions(partyService);
    }

    @Test
    public void test_getInboxMessages_should_return_200_code() {
        Party party = new Party();
        Long partyId = 1L;

        RestMessages restMessages = new RestMessages();
        RestMessage restMessage = new RestMessage();

        restMessage.setId("1");
        restMessage.setSender("Sender");
        restMessage.setRecipient("Receiver");
        restMessage.setReceiptDate(formatter.format(new Date()));
        restMessage.setSubject("Subj");
        restMessage.setContent("Content");
        restMessage.setAttachmentCount("12");
        restMessage.setTotalAttachmentSize("2019");
        restMessage.setStatus("Status");
        restMessage.setExpired("Expired");
        restMessage.setContentEncrypted("Content encrypted");
        restMessage.setSigned("signed");
        restMessage.setSentDate(formatter.format(new Date()));
        restMessage.setStatusReceiptDate(formatter.format(new Date()));
        restMessages.addMessageListItem(restMessage);
        restMessages.setMessageCount("1");
        restMessages.setHasMoreMessages(true);

        when(partyService.getPartyById(partyId)).thenReturn(party);
        when(messageService.getMessages(Message.MessageState.INCOMING, party, "1", "12", "available", "Subj", "asc")).thenReturn(restMessages);

        //ACTUAL CALL
        ResponseEntity<RestMessages> result = partyResource.getInboxMessages("1", "1", "12", "available", "Subj", "asc");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(restMessages));

        verify(partyService).getPartyById(partyId);
        verifyNoMoreInteractions(partyService);

        verify(messageService).getMessages(Message.MessageState.INCOMING, party, "1", "12", "available", "Subj", "asc");
        verifyNoMoreInteractions(messageService);
    }
}
