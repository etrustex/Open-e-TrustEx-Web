package eu.europa.ec.etrustex.webaccess.web.view.business.edma;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.AttachmentMetadata;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

public class EDMAUtilsTest extends AbstractTest{
    private String payload = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
            "<ETrustExEdmaMd xmlns=\"urn:eu:europa:ec:comp:etrustex\">"+
            "<subject>Test 3</subject>"+
            "<fileNumber>21</fileNumber>"+
            "<inAttentionOf>23</inAttentionOf>"+
            "<inboundDate>2011-10-27T14:17:34.138+02:00</inboundDate>"+
            "<language>EN</language>"+
            "<outboundMetaData>"+
            "<to>EDMA-APP-PARTY</to>"+
            "<registrationNumber>20</registrationNumber>"+
            "</outboundMetaData>"+
            "<inboundMetaData>"+
            "<transferStatus>SENT</transferStatus>"+
            "<documents>"+
            "<ETrustExEdmaMdDocument>"+
            "<confidential>26</confidential>"+
            "<documentComment>27</documentComment>"+
            "<file>to-be-defined-by-COMP</file>"+
            "<fileReferenceId>20180222164238525--6962602402794975070</fileReferenceId>" +
            "<path>path of the file</path>" +
            "</ETrustExEdmaMdDocument>"+
            "</documents>"+
            "<onBehalfOf>"+
            "<company>10</company>"+
            "<street>16</street>"+
            "<city>18</city>"+
            "<phone>14</phone>"+
            "<eMail>15</eMail>"+
            "<nationalRegNumber>11</nationalRegNumber>"+
            "<country>19</country>"+
            "<position>13</position>"+
            "<zip>17</zip>"+
            "<contactPerson>12</contactPerson>"+
            "</onBehalfOf>"+
            "<sender>"+
            "<company>EU</company>"+
            "<street>6</street>"+
            "<city>8</city>"+
            "<phone>4</phone>"+
            "<eMail>5</eMail>"+
            "<nationalRegNumber>1</nationalRegNumber>"+
            "<country>9</country>"+
            "<position>3</position>"+
            "<zip>7</zip>"+
            "<contactPerson>2</contactPerson>"+
            "</sender>"+
            "<eMail>15</eMail>"+
            "<from>find the current user name's email</from>"+
            "<yourReference>to-be-defined-by-COMP</yourReference>"+
            "<instrument>22</instrument>"+
            "<distributionList>John.Doe@ec.europa.eu</distributionList>" +
            "</inboundMetaData>"+
            "<outboundDate>2011-10-27T14:17:34.138+02:00</outboundDate>"+
            "<comment>25</comment>"+
            "</ETrustExEdmaMd>";

    @InjectMocks
    EDMAUtils edmaUtils = new EDMAUtils();


    @Test
    public void testFetchEdmaMessage() throws Exception {
        Party remoteParty = new Party();
        remoteParty.setNodeName("NODE APP PARTY");

        Message message = new Message();
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.DRAFT);
        message.setId(100L);

        List<Attachment> attachments = new ArrayList<>();
        Attachment attach1 = new Attachment();
        attach1.setType(AttachmentType.BINARY);
        attach1.setWrapperId("Reference1");
        attach1.setFileName("Name1");
        attachments.add(attach1);

        message.setAttachments(attachments);

        Metadata metadata = new Metadata();
        metadata.setContent(payload);

        EdmaMessage result = edmaUtils.fetchEdmaMessage(message, attachments, metadata);

        assertThat(result.getSenderOrganisationName(), is("EU"));
        assertThat(result.getSenderRegNumber(), is("1"));
        assertThat(result.getSenderContactPerson(), is("2"));
        assertThat(result.getSenderPosition(), is("3"));
        assertThat(result.getSenderPhone(), is("4"));
        assertThat(result.getSenderEmail(), is("5"));
        assertThat(result.getSenderStreet(), is("6"));
        assertThat(result.getSenderZip(), is("7"));
        assertThat(result.getSenderCity(), is("8"));
        assertThat(result.getSenderCountry(), is("9"));
        assertThat(result.getOnBehalfCompanyName(), is("10"));
        assertThat(result.getOnBehalfRegNo(), is("11"));
        assertThat(result.getOnBehalfContactPerson(), is("12"));
        assertThat(result.getOnBehalfPosition(), is("13"));
        assertThat(result.getOnBehalfPhone(), is("14"));
        assertThat(result.getOnBehalfEmail(), is("15"));
        assertThat(result.getOnBehalfStreet(), is("16"));
        assertThat(result.getOnBehalfZip(), is("17"));
        assertThat(result.getOnBehalfCity(), is("18"));
        assertThat(result.getOnBehalfCountry(), is("19"));
        assertThat(result.getMsgInfoNo(), isEmptyString());
        assertThat(result.getMsgFileNo(), is("21"));
        assertThat(result.getMsgInstrument(), is("22"));
        assertThat(result.getMsgInAttentionOf(), is("23"));
        assertThat(result.getMsgDistributionList(), is("John.Doe@ec.europa.eu"));
        assertThat(result.getAttachmentMetadataList(), hasSize(1));

        final AttachmentMetadata attachmentMetadata = result.getAttachmentMetadataList().iterator().next();
        assertThat(attachmentMetadata.getComment(), is("27"));
        assertThat(attachmentMetadata.getConfidential(), is(false));
        assertThat(attachmentMetadata.getFilename(), is("to-be-defined-by-COMP"));
        assertThat(attachmentMetadata.getPath(), is("path of the file"));
        assertThat(attachmentMetadata.getFileReferenceId(), is("20180222164238525--6962602402794975070"));
    }
}
