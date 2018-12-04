package eu.europa.ec.etrustex.webaccess.webservice;

import ec.schema.xsd.commonaggregatecomponents_2.BusinessHeaderType;
import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import ec.schema.xsd.commonaggregatecomponents_2.TechnicalHeaderType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EndpointIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IssueDateType;
import oasis.names.specification.ubl.schema.xsd.signatureaggregatecomponents_2.SignatureInformationType;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.BusinessScope;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.Partner;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.PartnerIdentification;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.Scope;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author: micleva
 * @date: 10/30/13 8:18 AM
 * @project: ETX
 */
public class NodeObjectBuilder {

    private final static String STATUS_SCOPE_IDENTIFIER = "CREATE_STATUS_AVAILABLE";

    /**
     * Get the sender party
     *
     * @param partyName the name for which a party type needs to be created
     * @return the party type with the given partyName
     */
    public static PartyType buildParty(String partyName) {
        PartyType party = new PartyType();
        party.setEndpointID(new EndpointIDType());
        party.getEndpointID().setValue(partyName);
        return party;
    }

    public static IssueDateType buildIssueDate() {
        IssueDateType issueDateType;

        try {
            Date date = new Date();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(date.getTime());
            XMLGregorianCalendar xgcDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            xgcDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            xgcDate.setTime(DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);

            issueDateType = new IssueDateType();
            issueDateType.setValue(xgcDate);
        } catch (Exception e) {
            issueDateType = null;
        }

        return issueDateType;
    }

    public static Partner buildPartnerType(String partnerName) {
        PartnerIdentification partnerIdentification = new PartnerIdentification();
        partnerIdentification.setValue(partnerName);

        Partner partner = new Partner();
        partner.setIdentifier(partnerIdentification);
        return partner;
    }

    public static Holder<HeaderType> buildHeader(String senderParty, String receiverParty) {
        Holder<HeaderType> holder = new Holder<>();
        holder.value = new HeaderType();

        //create the business header
        BusinessHeaderType businessHeaderType = new BusinessHeaderType();
        businessHeaderType.getSender().add(buildPartnerType(senderParty));
        if (receiverParty != null) {
            businessHeaderType.getReceiver().add(buildPartnerType(receiverParty));
        }
        holder.value.setBusinessHeader(businessHeaderType);

        //create the technical header
        TechnicalHeaderType technicalHeaderType = new TechnicalHeaderType();
        technicalHeaderType.getSignatureInformation().add(new SignatureInformationType());
        holder.value.setTechnicalHeader(technicalHeaderType);

        return holder;
    }

    public static Holder<HeaderType> buildHeaderWithStatusScope(String senderParty, String receiverParty) {
        Holder<HeaderType> header = buildHeader(senderParty, receiverParty);

        BusinessScope businessScope = new BusinessScope();
        Scope scope = new Scope();
        scope.setType(STATUS_SCOPE_IDENTIFIER);
        businessScope.getScope().add(scope);
        header.value.getBusinessHeader().setBusinessScope(businessScope);

        return header;
    }
}
