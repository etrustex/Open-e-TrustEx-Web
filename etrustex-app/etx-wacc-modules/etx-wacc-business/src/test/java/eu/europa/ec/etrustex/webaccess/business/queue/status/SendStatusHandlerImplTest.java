package eu.europa.ec.etrustex.webaccess.business.queue.status;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.MessageStatus;
import eu.europa.ec.etrustex.webaccess.persistence.MessageStatusDAO;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_2.ApplicationResponseType;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.xml.datatype.DatatypeConstants;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SendStatusHandlerImplTest extends AbstractTest {

    @InjectMocks
    private SendStatusHandlerImpl sendStatusHandler = new SendStatusHandlerImpl();

    @Mock
    private MessageStatusDAO messageStatusDAO;

    @Test
    public void test_convertMessageStatus_should_NotHaveTimezoneInfo() throws Exception {
        Long messageId = 1L;
        String bundleId = "bundleId";
        int year = 2014;
        int month = 10;
        int day = 9;

        Calendar cal = new GregorianCalendar();
        cal.set(year, month, day);

        Message message = new Message();
        message.setId(messageId);
        message.setIssueDate(cal.getTime());
        message.setBundleId(bundleId);

        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setMessage(message);

        // DO THE ACTUAL CALL
        ApplicationResponseType applicationResponseType = sendStatusHandler.convertMessageStatus(messageStatus);

        // time fields check
        assertThat(applicationResponseType, not(nullValue()));
        assertThat(applicationResponseType.getIssueDate(), not(nullValue()));
        assertThat(applicationResponseType.getIssueDate().getValue(), not(nullValue()));
        assertThat(applicationResponseType.getIssueDate().getValue().getTimezone(), is(DatatypeConstants.FIELD_UNDEFINED));
        assertThat(applicationResponseType.getIssueDate().getValue().getHour(), is(DatatypeConstants.FIELD_UNDEFINED));
        assertThat(applicationResponseType.getIssueDate().getValue().getMinute(), is(DatatypeConstants.FIELD_UNDEFINED));
        assertThat(applicationResponseType.getIssueDate().getValue().getSecond(), is(DatatypeConstants.FIELD_UNDEFINED));
        assertThat(applicationResponseType.getIssueDate().getValue().getMillisecond(), is(DatatypeConstants.FIELD_UNDEFINED));

        // date fields check
        Calendar response = applicationResponseType.getIssueDate().getValue().toGregorianCalendar();
        assertThat(response.get(Calendar.YEAR), is(year));
        assertThat(response.get(Calendar.MONTH), is(month));
        assertThat(response.get(Calendar.DAY_OF_MONTH), is(day));
    }
}