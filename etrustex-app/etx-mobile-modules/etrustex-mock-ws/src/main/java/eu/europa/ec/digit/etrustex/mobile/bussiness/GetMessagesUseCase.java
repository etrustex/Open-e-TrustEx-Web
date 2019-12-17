package eu.europa.ec.digit.etrustex.mobile.bussiness;

import eu.europa.ec.digit.etrustex.mobile.model.Message;
import eu.europa.ec.digit.etrustex.mobile.model.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;


@Component
@SessionScope
public class GetMessagesUseCase {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Random rand = new Random();

    private static final int MAX_MESSAGES = 51;
    private int servedMessages = 0;

    private int getServedMessages() {
        return servedMessages;
    }

    private void setServedMessages(int value) {
        this.servedMessages = value;
    }

    public Messages getInboxMessages(String partyId, String query, String startFrom, String resultSize) {
        Messages inbox = new Messages();
        inbox.messageCount(resultSize).hasMoreMessages(true);

        int getMessagesCount = getServedMessages();
        logger.debug("current messages count = " + getMessagesCount); //DEBUG

        int requestedSize = Integer.valueOf(resultSize);
        int count;
        if (getMessagesCount >= MAX_MESSAGES) count = 0;
        else count = Math.min(requestedSize, (MAX_MESSAGES - getMessagesCount));
        logger.debug("number of messages that will be sent = "  + count); //DEBUG

        for (int i = 0; i < count; i++) {
            inbox.addMessageListItem(createMessage(partyId, startFrom, i, true));
        }
        if (inbox.getMessageList().size() == 0) {
            inbox.messageCount("0").hasMoreMessages(false);
        } else {
            inbox.messageCount(Integer.toString(count)).hasMoreMessages(count == requestedSize);
        }
        setServedMessages(getMessagesCount + count);

        return inbox;
    }

    public Messages getSentMessages(String partyId, String query, String startFrom, String resultSize) {
        Messages inbox = new Messages();
        inbox.messageCount(resultSize).hasMoreMessages(true);

        int count = Integer.valueOf(resultSize);
        for (int i = 0; i < count; i++) {
            inbox.addMessageListItem(createMessage(partyId, startFrom, i, false));
        }

        return inbox;
    }

    private Message createMessage(String partyId, String startFrom, int i, boolean isInboxMessage) {
        Message message = new Message();

        String id = UUID.randomUUID().toString();
        boolean expired = Math.abs(rand.nextInt()) % 10 > 8;
        boolean signed = Math.abs(rand.nextInt()) % 10 > 5;
        boolean read = Math.abs(rand.nextInt()) % 10 > 2;

        if (isInboxMessage) {
            message
                    .sender("party ext TEAM 123_" + i)
                    .recipient(partyId);
        } else {
            message
                    .sender(partyId)
                    .recipient("party ext TEAM 123_" + i);
        }

        OffsetDateTime date = getRandomDateInThePast();

        message.id(id)
                .subject("Test card title " + id)
                .content("Sum dum de plurimis eadem dicit, tum certe de maximis. Ut in geometria, prima si dederis, danda sunt omnia. Quos nisi redarguimus, omnis virtus, omne decus, omnis vera laus deserenda est. Sed quanta sit alias, nunc tantum possitne esse tanta. Si id dicis, vicimus. Ita prorsus, inquam;")
                .receiptDate(date)
                .sentDate(date)
                .statusReceiptDate(date.plusDays(2))
                .contentEncrypted(String.valueOf(rand.nextBoolean()))
                .expired(String.valueOf(expired))
                .status(read ? "READ" : "DELIVERED")
                .attachmentCount(String.valueOf(Math.abs(rand.nextLong()) % 150))
                .totalAttachmentSize(String.valueOf(Math.abs(rand.nextLong()) % 300))
                .signed(String.valueOf(rand.nextBoolean()));

        if (!expired) {
            if (Math.abs(rand.nextInt()) % 10 > 8) {
                message.expirationWarning("WILL EXPIRE IN N DAYS");
            }
        }

        return message;
    }

    private OffsetDateTime getRandomDateInThePast() {
        return OffsetDateTime.now().minusDays(Math.abs(rand.nextInt() % 12));
    }

}
