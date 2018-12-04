
package un.unece.uncefact.data.specification.unqualifieddatatypesschemamodule._2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ec.schema.xsd.commonbasiccomponents_1.AvailabilityLevelCodeType;
import ec.schema.xsd.commonbasiccomponents_1.ConfidentialityLevelCodeType;
import ec.schema.xsd.commonbasiccomponents_1.DocumentIdentificationTypeType;
import ec.schema.xsd.commonbasiccomponents_1.IntegrityLevelCodeType;
import ec.schema.xsd.commonbasiccomponents_1.KeyUsageCodeType;
import ec.schema.xsd.commonbasiccomponents_1.MimeCodeType;
import ec.schema.xsd.commonbasiccomponents_1.ModeCodeType;
import ec.schema.xsd.commonbasiccomponents_1.RequestForQuotationPropertyCodeType;
import ec.schema.xsd.commonbasiccomponents_1.RequestForQuotationTypeCodeType;
import ec.schema.xsd.commonbasiccomponents_1.ScopeTypeType;
import ec.schema.xsd.commonbasiccomponents_1.SortFieldTypeCodeType;
import ec.schema.xsd.commonbasiccomponents_1.SummaryDescriptionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AccountTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AccountingCostCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ActionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AddressFormatCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AddressTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ApplicationStatusCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CardTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CargoTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CommodityCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ContractTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CoordinateSystemCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CorporateRegistrationTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CountrySubentityCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.CustomsStatusCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DespatchAdviceTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DirectionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DispositionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EmergencyProceduresCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EventCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ExemptionReasonCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.FreightRateClassCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.FullnessIndicationCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.HandlingCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.HazardousCategoryCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.HazardousRegulationCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.InhalationToxicityZoneCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.InspectionMethodCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.InvoiceTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ItemClassificationCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.LifeCycleStatusCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.LocaleCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.LossRiskResponsibilityCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MedicalFirstAidGuideCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.NatureCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.OwnerTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PackLevelCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PackageLevelCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PackingCriteriaCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ParentDocumentTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PaymentChannelCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PositionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PreferenceCriterionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.PriceTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ProviderTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ReferenceEventCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.RejectActionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.RejectReasonCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ReminderTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ResponseCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.RoleCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.SealIssuerTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.SealStatusCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ShippingPriorityLevelCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ShortageActionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.SizeTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.StatusCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.StatusReasonCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TariffClassCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TariffCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TaxExemptionReasonCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TaxLevelCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TaxTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TimingComplaintCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TransitDirectionCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TransportAuthorizationCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TransportEmergencyCardCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TransportEventTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TransportHandlingUnitTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TransportMeansTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TransportServiceCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.UNDGCodeType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.ExtensionReasonCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.AllowanceChargeReasonCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.ChannelCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.ChipCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.ContainerSizeTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.CountryIdentificationCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.CurrencyCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.DocumentStatusCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.LatitudeDirectionCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.LineStatusCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.LongitudeDirectionCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.OperatorCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.PackagingTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.PaymentMeansCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.PortCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.SubstitutionStatusCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.TransportEquipmentTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.TransportModeCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.TransportationStatusCodeType;
import oasis.names.specification.ubl.schema.xsd.qualifieddatatypes_2.UnitOfMeasureCodeType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:UniqueID xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;UDT000007&lt;/ccts:UniqueID&gt;
 * </pre>
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:CategoryCode xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;UDT&lt;/ccts:CategoryCode&gt;
 * </pre>
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:DictionaryEntryName xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code. Type&lt;/ccts:DictionaryEntryName&gt;
 * </pre>
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:VersionID xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;1.0&lt;/ccts:VersionID&gt;
 * </pre>
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Definition xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;A character string (letters, figures, or symbols) that for brevity and/or languange independence may be used to represent or replace a definitive value or text of an attribute together with relevant supplementary information.&lt;/ccts:Definition&gt;
 * </pre>
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:RepresentationTermName xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Code&lt;/ccts:RepresentationTermName&gt;
 * </pre>
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:PrimitiveType xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;string&lt;/ccts:PrimitiveType&gt;
 * </pre>
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;xsd:BuiltinType xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2"&gt;normalizedString&lt;/xsd:BuiltinType&gt;
 * </pre>
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:UsageRule xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns:clm5639="urn:un:unece:uncefact:codelist:specification:5639:1988" xmlns:udt="urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Other supplementary components in the CCT are captured as part of the token and name for the schema module containing the code list and thus, are not declared as attributes. &lt;/ccts:UsageRule&gt;
 * </pre>
 * 
 * 
 * <p>Java class for CodeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CodeType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;normalizedString"&gt;
 *       &lt;attribute name="listID" type="{http://www.w3.org/2001/XMLSchema}normalizedString" /&gt;
 *       &lt;attribute name="listAgencyID" type="{http://www.w3.org/2001/XMLSchema}normalizedString" /&gt;
 *       &lt;attribute name="listAgencyName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="listName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="listVersionID" type="{http://www.w3.org/2001/XMLSchema}normalizedString" /&gt;
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="languageID" type="{http://www.w3.org/2001/XMLSchema}language" /&gt;
 *       &lt;attribute name="listURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="listSchemeURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodeType", propOrder = {
    "value"
})
@XmlSeeAlso({
    SortFieldTypeCodeType.class,
    MimeCodeType.class,
    ConfidentialityLevelCodeType.class,
    IntegrityLevelCodeType.class,
    AvailabilityLevelCodeType.class,
    KeyUsageCodeType.class,
    ScopeTypeType.class,
    DocumentIdentificationTypeType.class,
    ModeCodeType.class,
    RequestForQuotationPropertyCodeType.class,
    RequestForQuotationTypeCodeType.class,
    SummaryDescriptionCodeType.class,
    ContainerSizeTypeCodeType.class,
    PortCodeType.class,
    UnitOfMeasureCodeType.class,
    AccountingCostCodeType.class,
    AccountTypeCodeType.class,
    ActionCodeType.class,
    AddressFormatCodeType.class,
    AddressTypeCodeType.class,
    AllowanceChargeReasonCodeType.class,
    ApplicationStatusCodeType.class,
    ChipCodeType.class,
    CardTypeCodeType.class,
    CargoTypeCodeType.class,
    ChannelCodeType.class,
    CommodityCodeType.class,
    TransportationStatusCodeType.class,
    ContractTypeCodeType.class,
    CoordinateSystemCodeType.class,
    CorporateRegistrationTypeCodeType.class,
    CountrySubentityCodeType.class,
    CustomsStatusCodeType.class,
    DescriptionCodeType.class,
    DespatchAdviceTypeCodeType.class,
    DirectionCodeType.class,
    DispositionCodeType.class,
    DocumentStatusCodeType.class,
    DocumentTypeCodeType.class,
    EmergencyProceduresCodeType.class,
    EventCodeType.class,
    ExemptionReasonCodeType.class,
    FreightRateClassCodeType.class,
    FullnessIndicationCodeType.class,
    HandlingCodeType.class,
    HazardousCategoryCodeType.class,
    HazardousRegulationCodeType.class,
    CountryIdentificationCodeType.class,
    InhalationToxicityZoneCodeType.class,
    InspectionMethodCodeType.class,
    InvoiceTypeCodeType.class,
    LatitudeDirectionCodeType.class,
    LifeCycleStatusCodeType.class,
    LineStatusCodeType.class,
    LocaleCodeType.class,
    LongitudeDirectionCodeType.class,
    LossRiskResponsibilityCodeType.class,
    OperatorCodeType.class,
    MedicalFirstAidGuideCodeType.class,
    NatureCodeType.class,
    OwnerTypeCodeType.class,
    PackageLevelCodeType.class,
    PackagingTypeCodeType.class,
    PackingCriteriaCodeType.class,
    PackLevelCodeType.class,
    ParentDocumentTypeCodeType.class,
    PaymentChannelCodeType.class,
    PaymentMeansCodeType.class,
    PositionCodeType.class,
    PreferenceCriterionCodeType.class,
    PriceTypeCodeType.class,
    ProviderTypeCodeType.class,
    ReferenceEventCodeType.class,
    RejectActionCodeType.class,
    RejectReasonCodeType.class,
    ReminderTypeCodeType.class,
    ResponseCodeType.class,
    RoleCodeType.class,
    SealIssuerTypeCodeType.class,
    SealStatusCodeType.class,
    ShippingPriorityLevelCodeType.class,
    ShortageActionCodeType.class,
    SizeTypeCodeType.class,
    StatusCodeType.class,
    StatusReasonCodeType.class,
    SubstitutionStatusCodeType.class,
    TariffClassCodeType.class,
    TariffCodeType.class,
    CurrencyCodeType.class,
    TaxExemptionReasonCodeType.class,
    TaxLevelCodeType.class,
    TaxTypeCodeType.class,
    TimingComplaintCodeType.class,
    TransitDirectionCodeType.class,
    TransportAuthorizationCodeType.class,
    TransportEmergencyCardCodeType.class,
    TransportEquipmentTypeCodeType.class,
    TransportEventTypeCodeType.class,
    TransportHandlingUnitTypeCodeType.class,
    TransportMeansTypeCodeType.class,
    TransportModeCodeType.class,
    TransportServiceCodeType.class,
    UNDGCodeType.class,
    ExtensionReasonCodeType.class,
    ItemClassificationCodeType.class
})
public class CodeType {

    @XmlValue
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String value;
    @XmlAttribute(name = "listID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String listID;
    @XmlAttribute(name = "listAgencyID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String listAgencyID;
    @XmlAttribute(name = "listAgencyName")
    protected String listAgencyName;
    @XmlAttribute(name = "listName")
    protected String listName;
    @XmlAttribute(name = "listVersionID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String listVersionID;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "languageID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String languageID;
    @XmlAttribute(name = "listURI")
    @XmlSchemaType(name = "anyURI")
    protected String listURI;
    @XmlAttribute(name = "listSchemeURI")
    @XmlSchemaType(name = "anyURI")
    protected String listSchemeURI;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the listID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListID() {
        return listID;
    }

    /**
     * Sets the value of the listID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListID(String value) {
        this.listID = value;
    }

    /**
     * Gets the value of the listAgencyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListAgencyID() {
        return listAgencyID;
    }

    /**
     * Sets the value of the listAgencyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListAgencyID(String value) {
        this.listAgencyID = value;
    }

    /**
     * Gets the value of the listAgencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListAgencyName() {
        return listAgencyName;
    }

    /**
     * Sets the value of the listAgencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListAgencyName(String value) {
        this.listAgencyName = value;
    }

    /**
     * Gets the value of the listName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListName() {
        return listName;
    }

    /**
     * Sets the value of the listName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListName(String value) {
        this.listName = value;
    }

    /**
     * Gets the value of the listVersionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListVersionID() {
        return listVersionID;
    }

    /**
     * Sets the value of the listVersionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListVersionID(String value) {
        this.listVersionID = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the languageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageID() {
        return languageID;
    }

    /**
     * Sets the value of the languageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageID(String value) {
        this.languageID = value;
    }

    /**
     * Gets the value of the listURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListURI() {
        return listURI;
    }

    /**
     * Sets the value of the listURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListURI(String value) {
        this.listURI = value;
    }

    /**
     * Gets the value of the listSchemeURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListSchemeURI() {
        return listSchemeURI;
    }

    /**
     * Sets the value of the listSchemeURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListSchemeURI(String value) {
        this.listSchemeURI = value;
    }

}
