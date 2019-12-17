package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.services.business.IcaService;
import eu.europa.ec.etrustex.services.business.MessageService;
import eu.europa.ec.etrustex.services.business.config.Configuration;
import eu.europa.ec.etrustex.services.business.config.ConfigurationService;
import eu.europa.ec.etrustex.services.converter.RestMessageConverter;
import eu.europa.ec.etrustex.services.model.RestMessages;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.MessageStatus;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.jpa.MessageColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service layer implementation for messages.
 */

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private IcaService icaService;

    @Override
    public RestMessages getMessages(Message.MessageState messageState, Party party, String startfrom, String resultsize, String status, String subject, String sortby) {

        Configuration configuration = configurationService.getConfiguration();

        boolean isIncoming = true;

        //default sorting asc
        boolean isAscending = true;


        if (sortby != null) {
            isAscending = (sortby.equals("asc")) ? true : false;
        }

        MessageColumn sortColumn = MessageColumn.RECEIVED;

        if (messageState.equals(Message.MessageState.SENT)) {
            sortColumn = MessageColumn.SENT;
            isIncoming = false;
        }

        MessageStatus.Status messageStatus = null;

        if (status != null) {
            switch (status) {
                case "FAILED":
                    messageStatus = MessageStatus.Status.FAILED;
                    break;
                case "AVAILABLE":
                    messageStatus = MessageStatus.Status.AVAILABLE;
                    break;
                case "READ":
                    messageStatus = MessageStatus.Status.READ;
                    break;
                case "UNKNOWN":
                    messageStatus = MessageStatus.Status.UNKNOWN;
                    break;
            }
        }


        int startIndex = (startfrom != null) ? Integer.valueOf(startfrom).intValue() : 1;

        //set partyIcas as needed afterwards
        party.setPartyICAs(icaService.getIcasByParty(party));

        int maxResults = (resultsize != null) ? Integer.valueOf(resultsize).intValue() : 0;

        List<Message> messages;

        if (status == null) {
            messages = messageDAO.getMessages(subject, messageState, party, sortColumn, isAscending, startIndex, maxResults);
        } else {
            messages = messageDAO.getMessages(subject, messageState, messageStatus, party, sortColumn, isAscending, startIndex, maxResults);
        }

        RestMessages restMessages = RestMessageConverter.getInstance().convertToRestMessages(messages, party, configuration, isIncoming);

        return restMessages;
    }

}
