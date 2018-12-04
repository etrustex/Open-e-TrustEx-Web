/**
 *
 */
package eu.europa.ec.etrustex.webaccess.web.view.business.edma;

import eu.europa.ec.etrustex.webaccess.model.vo.AttachmentMetadata;

import java.util.List;


/**
 * @author apladap
 */
public class EdmaMessage{

    private String senderOrganisationName;
    private String senderRegNumber;
    private String senderContactPerson;
    private String senderPosition;
    private String senderPhone;
    private String senderEmail;
    private String senderStreet;
    private String senderZip;
    private String senderCity;
    private String senderCountry;

    private String onBehalfCompanyName;
    private String onBehalfRegNo;
    private String onBehalfContactPerson;
    private String onBehalfPosition;
    private String onBehalfPhone;
    private String onBehalfEmail;
    private String onBehalfStreet;
    private String onBehalfZip;
    private String onBehalfCity;
    private String onBehalfCountry;

    private String msgInfoNo;
    private String msgFileNo;
    private String msgInstrument;
    private String msgInAttentionOf;
    private String msgDistributionList;

    private List<AttachmentMetadata> attachmentMetadataList;

    public String getSenderOrganisationName() {
        return senderOrganisationName;
    }

    public void setSenderOrganisationName(String senderOrganisationName) {
        this.senderOrganisationName = senderOrganisationName;
    }

    public String getSenderRegNumber() {
        return senderRegNumber;
    }

    public void setSenderRegNumber(String senderRegNumber) {
        this.senderRegNumber = senderRegNumber;
    }

    public String getSenderContactPerson() {
        return senderContactPerson;
    }

    public void setSenderContactPerson(String senderContactPerson) {
        this.senderContactPerson = senderContactPerson;
    }

    public String getSenderPosition() {
        return senderPosition;
    }

    public void setSenderPosition(String senderPosition) {
        this.senderPosition = senderPosition;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderStreet() {
        return senderStreet;
    }

    public void setSenderStreet(String senderStreet) {
        this.senderStreet = senderStreet;
    }

    public String getSenderZip() {
        return senderZip;
    }

    public void setSenderZip(String senderZip) {
        this.senderZip = senderZip;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getOnBehalfCompanyName() {
        return onBehalfCompanyName;
    }

    public void setOnBehalfCompanyName(String onBehalfCompanyName) {
        this.onBehalfCompanyName = onBehalfCompanyName;
    }

    public String getOnBehalfRegNo() {
        return onBehalfRegNo;
    }

    public void setOnBehalfRegNo(String onBehalfRegNo) {
        this.onBehalfRegNo = onBehalfRegNo;
    }

    public String getOnBehalfContactPerson() {
        return onBehalfContactPerson;
    }

    public void setOnBehalfContactPerson(String onBehalfContactPerson) {
        this.onBehalfContactPerson = onBehalfContactPerson;
    }

    public String getOnBehalfPosition() {
        return onBehalfPosition;
    }

    public void setOnBehalfPosition(String onBehalfPosition) {
        this.onBehalfPosition = onBehalfPosition;
    }

    public String getOnBehalfPhone() {
        return onBehalfPhone;
    }

    public void setOnBehalfPhone(String onBehalfPhone) {
        this.onBehalfPhone = onBehalfPhone;
    }

    public String getOnBehalfEmail() {
        return onBehalfEmail;
    }

    public void setOnBehalfEmail(String onBehalfEmail) {
        this.onBehalfEmail = onBehalfEmail;
    }

    public String getOnBehalfStreet() {
        return onBehalfStreet;
    }

    public void setOnBehalfStreet(String onBehalfStreet) {
        this.onBehalfStreet = onBehalfStreet;
    }

    public String getOnBehalfZip() {
        return onBehalfZip;
    }

    public void setOnBehalfZip(String onBehalfZip) {
        this.onBehalfZip = onBehalfZip;
    }

    public String getOnBehalfCity() {
        return onBehalfCity;
    }

    public void setOnBehalfCity(String onBehalfCity) {
        this.onBehalfCity = onBehalfCity;
    }

    public String getOnBehalfCountry() {
        return onBehalfCountry;
    }

    public void setOnBehalfCountry(String onBehalfCountry) {
        this.onBehalfCountry = onBehalfCountry;
    }

    public String getMsgInfoNo() {
        return msgInfoNo;
    }

    public void setMsgInfoNo(String msgInfoNo) {
        this.msgInfoNo = msgInfoNo;
    }

    public String getMsgFileNo() {
        return msgFileNo;
    }

    public void setMsgFileNo(String msgFileNo) {
        this.msgFileNo = msgFileNo;
    }

    public String getMsgInstrument() {
        return msgInstrument;
    }

    public void setMsgInstrument(String msgInstrument) {
        this.msgInstrument = msgInstrument;
    }

    public String getMsgInAttentionOf() {
        return msgInAttentionOf;
    }

    public void setMsgInAttentionOf(String msgInAttentionOf) {
        this.msgInAttentionOf = msgInAttentionOf;
    }

    public String getMsgDistributionList() {
        return msgDistributionList;
    }

    public void setMsgDistributionList(String msgDistributionList) {
        this.msgDistributionList = msgDistributionList;
    }

    public List<AttachmentMetadata> getAttachmentMetadataList() {
        return attachmentMetadataList;
    }

    public void setAttachmentMetadataList(List<AttachmentMetadata> attachmentMetadataList) {
        this.attachmentMetadataList = attachmentMetadataList;
    }
}
