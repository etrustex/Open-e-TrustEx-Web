
package ec.schema.xsd.commonaggregatecomponents_2;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ItemClassificationCodeType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.schema.xsd.commonaggregatecomponents_2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InterchangeAgreement_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "InterchangeAgreement");
    private final static QName _LargeAttachment_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "LargeAttachment");
    private final static QName _ResourceInformationReference_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ResourceInformationReference");
    private final static QName _StreamBase64Binary_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "StreamBase64Binary");
    private final static QName _DocumentSize_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentSize");
    private final static QName _Format_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "Format");
    private final static QName _Creator_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "Creator");
    private final static QName _Title_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "Title");
    private final static QName _UserID_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "UserID");
    private final static QName _BusinessDocumentType_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "BusinessDocumentType");
    private final static QName _IncludeMessagesInErrorIndicator_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "IncludeMessagesInErrorIndicator");
    private final static QName _PaginationRange_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "PaginationRange");
    private final static QName _DocumentHashMethod_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentHashMethod");
    private final static QName _DocumentCanonicalizationMethod_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentCanonicalizationMethod");
    private final static QName _PayloadDigestMethod_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "PayloadDigestMethod");
    private final static QName _PayloadDigestValue_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "PayloadDigestValue");
    private final static QName _PayloadCanonicalizationMethod_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "PayloadCanonicalizationMethod");
    private final static QName _DigestValue_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DigestValue");
    private final static QName _EncryptionInformation_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "EncryptionInformation");
    private final static QName _SecurityInformation_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "SecurityInformation");
    private final static QName _SenderPartyCertificate_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "SenderPartyCertificate");
    private final static QName _ReceiverPartyCertificate_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ReceiverPartyCertificate");
    private final static QName _Timestamp_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "Timestamp");
    private final static QName _DocumentReferenceRequest_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentReferenceRequest");
    private final static QName _DocumentReferenceResponse_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentReferenceResponse");
    private final static QName _ExtendedDocumentReferenceResponse_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ExtendedDocumentReferenceResponse");
    private final static QName _DocumentReferenceResponseRelatedDocs_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentReferenceResponseRelatedDocs");
    private final static QName _DocumentReferenceResponseParentDocs_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentReferenceResponseParentDocs");
    private final static QName _ExtendedDocumentReferenceResponseParentDocs_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ExtendedDocumentReferenceResponseParentDocs");
    private final static QName _DocumentContent_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentContent");
    private final static QName _DocumentSenderParty_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentSenderParty");
    private final static QName _DocumentReceiverParty_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentReceiverParty");
    private final static QName _DocumentWrapperReference_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentWrapperReference");
    private final static QName _RequestDocumentParameter_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "RequestDocumentParameter");
    private final static QName _ExtendedRequestDocumentParameter_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ExtendedRequestDocumentParameter");
    private final static QName _AcknowledgedDocumentReference_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "AcknowledgedDocumentReference");
    private final static QName _RelatedDocument_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "RelatedDocument");
    private final static QName _ParentDocument_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ParentDocument");
    private final static QName _ECDocumentReceivedData_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ECDocumentReceivedData");
    private final static QName _ProcessingWarning_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ProcessingWarning");
    private final static QName _AdditionaHeaderlProperty_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "AdditionaHeaderlProperty");
    private final static QName _AuthorisationHeader_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "AuthorisationHeader");
    private final static QName _Header_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "Header");
    private final static QName _BusinessHeader_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "BusinessHeader");
    private final static QName _TechnicalHeader_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "TechnicalHeader");
    private final static QName _CatalogueLineSynopsis_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "CatalogueLineSynopsis");
    private final static QName _ItemSynopsis_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ItemSynopsis");
    private final static QName _CatalogueSynopsis_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "CatalogueSynopsis");
    private final static QName _ItemClassification_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ItemClassification");
    private final static QName _ParentItemClassificationCode_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ParentItemClassificationCode");
    private final static QName _NumberOfItems_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "NumberOfItems");
    private final static QName _ItemClassificationOrderSequence_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ItemClassificationOrderSequence");
    private final static QName _CatalogueItemClassification_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "CatalogueItemClassification");
    private final static QName _ArchiveDocumentReference_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ArchiveDocumentReference");
    private final static QName _DocumentSummary_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentSummary");
    private final static QName _DocumentTimeline_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "DocumentTimeline");
    private final static QName _ECLot_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ECLot");
    private final static QName _ECRequestForQuotationDocumentReference_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ECRequestForQuotationDocumentReference");
    private final static QName _NextDocument_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "NextDocument");
    private final static QName _OriginatorDocumentOpenerParty_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "OriginatorDocumentOpenerParty");
    private final static QName _ProcessTimeline_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ProcessTimeline");
    private final static QName _BusinessScope_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "BusinessScope");
    private final static QName _SpecificContent_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "SpecificContent");
    private final static QName _FromInterchangeAgreement_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "FromInterchangeAgreement");
    private final static QName _ToInterchangeAgreement_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ToInterchangeAgreement");
    private final static QName _FromParty_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "FromParty");
    private final static QName _ToParty_QNAME = new QName("ec:schema:xsd:CommonAggregateComponents-2", "ToParty");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.schema.xsd.commonaggregatecomponents_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BusinessHeaderType }
     * 
     */
    public BusinessHeaderType createBusinessHeaderType() {
        return new BusinessHeaderType();
    }

    /**
     * Create an instance of {@link InterchangeAgreementType }
     * 
     */
    public InterchangeAgreementType createInterchangeAgreementType() {
        return new InterchangeAgreementType();
    }

    /**
     * Create an instance of {@link LargeAttachmentType }
     * 
     */
    public LargeAttachmentType createLargeAttachmentType() {
        return new LargeAttachmentType();
    }

    /**
     * Create an instance of {@link ResourceInformationReferenceType }
     * 
     */
    public ResourceInformationReferenceType createResourceInformationReferenceType() {
        return new ResourceInformationReferenceType();
    }

    /**
     * Create an instance of {@link Base64BinaryType }
     * 
     */
    public Base64BinaryType createBase64BinaryType() {
        return new Base64BinaryType();
    }

    /**
     * Create an instance of {@link DocumentSizeType }
     * 
     */
    public DocumentSizeType createDocumentSizeType() {
        return new DocumentSizeType();
    }

    /**
     * Create an instance of {@link FormatType }
     * 
     */
    public FormatType createFormatType() {
        return new FormatType();
    }

    /**
     * Create an instance of {@link CreatorType }
     * 
     */
    public CreatorType createCreatorType() {
        return new CreatorType();
    }

    /**
     * Create an instance of {@link TitleType }
     * 
     */
    public TitleType createTitleType() {
        return new TitleType();
    }

    /**
     * Create an instance of {@link UserIDType }
     * 
     */
    public UserIDType createUserIDType() {
        return new UserIDType();
    }

    /**
     * Create an instance of {@link BusinessDocumentTypeType }
     * 
     */
    public BusinessDocumentTypeType createBusinessDocumentTypeType() {
        return new BusinessDocumentTypeType();
    }

    /**
     * Create an instance of {@link IncludeMessagesInErrorIndicatorType }
     * 
     */
    public IncludeMessagesInErrorIndicatorType createIncludeMessagesInErrorIndicatorType() {
        return new IncludeMessagesInErrorIndicatorType();
    }

    /**
     * Create an instance of {@link PaginationRangeType }
     * 
     */
    public PaginationRangeType createPaginationRangeType() {
        return new PaginationRangeType();
    }

    /**
     * Create an instance of {@link DigestMethodType }
     * 
     */
    public DigestMethodType createDigestMethodType() {
        return new DigestMethodType();
    }

    /**
     * Create an instance of {@link CanonicalizationMethodType }
     * 
     */
    public CanonicalizationMethodType createCanonicalizationMethodType() {
        return new CanonicalizationMethodType();
    }

    /**
     * Create an instance of {@link DigestValueType }
     * 
     */
    public DigestValueType createDigestValueType() {
        return new DigestValueType();
    }

    /**
     * Create an instance of {@link EncryptionInformationType }
     * 
     */
    public EncryptionInformationType createEncryptionInformationType() {
        return new EncryptionInformationType();
    }

    /**
     * Create an instance of {@link SecurityInformationType }
     * 
     */
    public SecurityInformationType createSecurityInformationType() {
        return new SecurityInformationType();
    }

    /**
     * Create an instance of {@link CertificateType }
     * 
     */
    public CertificateType createCertificateType() {
        return new CertificateType();
    }

    /**
     * Create an instance of {@link TimestampType }
     * 
     */
    public TimestampType createTimestampType() {
        return new TimestampType();
    }

    /**
     * Create an instance of {@link DocumentReferenceRequestType }
     * 
     */
    public DocumentReferenceRequestType createDocumentReferenceRequestType() {
        return new DocumentReferenceRequestType();
    }

    /**
     * Create an instance of {@link DocumentReferenceResponseType }
     * 
     */
    public DocumentReferenceResponseType createDocumentReferenceResponseType() {
        return new DocumentReferenceResponseType();
    }

    /**
     * Create an instance of {@link ExtendedDocumentReferenceResponseType }
     * 
     */
    public ExtendedDocumentReferenceResponseType createExtendedDocumentReferenceResponseType() {
        return new ExtendedDocumentReferenceResponseType();
    }

    /**
     * Create an instance of {@link DocumentReferenceResponseRelatedDocsType }
     * 
     */
    public DocumentReferenceResponseRelatedDocsType createDocumentReferenceResponseRelatedDocsType() {
        return new DocumentReferenceResponseRelatedDocsType();
    }

    /**
     * Create an instance of {@link DocumentReferenceResponseParentDocsType }
     * 
     */
    public DocumentReferenceResponseParentDocsType createDocumentReferenceResponseParentDocsType() {
        return new DocumentReferenceResponseParentDocsType();
    }

    /**
     * Create an instance of {@link ExtendedDocumentReferenceResponseParentDocsType }
     * 
     */
    public ExtendedDocumentReferenceResponseParentDocsType createExtendedDocumentReferenceResponseParentDocsType() {
        return new ExtendedDocumentReferenceResponseParentDocsType();
    }

    /**
     * Create an instance of {@link DocumentContentType }
     * 
     */
    public DocumentContentType createDocumentContentType() {
        return new DocumentContentType();
    }

    /**
     * Create an instance of {@link DocumentSenderParty }
     * 
     */
    public DocumentSenderParty createDocumentSenderParty() {
        return new DocumentSenderParty();
    }

    /**
     * Create an instance of {@link DocumentReceiverParty }
     * 
     */
    public DocumentReceiverParty createDocumentReceiverParty() {
        return new DocumentReceiverParty();
    }

    /**
     * Create an instance of {@link DocumentWrapperReferenceType }
     * 
     */
    public DocumentWrapperReferenceType createDocumentWrapperReferenceType() {
        return new DocumentWrapperReferenceType();
    }

    /**
     * Create an instance of {@link RequestDocumentParameterType }
     * 
     */
    public RequestDocumentParameterType createRequestDocumentParameterType() {
        return new RequestDocumentParameterType();
    }

    /**
     * Create an instance of {@link ExtendedRequestDocumentParameterType }
     * 
     */
    public ExtendedRequestDocumentParameterType createExtendedRequestDocumentParameterType() {
        return new ExtendedRequestDocumentParameterType();
    }

    /**
     * Create an instance of {@link AcknowledgedDocumentReferenceType }
     * 
     */
    public AcknowledgedDocumentReferenceType createAcknowledgedDocumentReferenceType() {
        return new AcknowledgedDocumentReferenceType();
    }

    /**
     * Create an instance of {@link ECDocumentReceivedDataType }
     * 
     */
    public ECDocumentReceivedDataType createECDocumentReceivedDataType() {
        return new ECDocumentReceivedDataType();
    }

    /**
     * Create an instance of {@link ProcessingWarningType }
     * 
     */
    public ProcessingWarningType createProcessingWarningType() {
        return new ProcessingWarningType();
    }

    /**
     * Create an instance of {@link AdditionalProperty }
     * 
     */
    public AdditionalProperty createAdditionalProperty() {
        return new AdditionalProperty();
    }

    /**
     * Create an instance of {@link AuthorisationHeaderType }
     * 
     */
    public AuthorisationHeaderType createAuthorisationHeaderType() {
        return new AuthorisationHeaderType();
    }

    /**
     * Create an instance of {@link HeaderType }
     * 
     */
    public HeaderType createHeaderType() {
        return new HeaderType();
    }

    /**
     * Create an instance of {@link TechnicalHeaderType }
     * 
     */
    public TechnicalHeaderType createTechnicalHeaderType() {
        return new TechnicalHeaderType();
    }

    /**
     * Create an instance of {@link CatalogueLineSynopsisType }
     * 
     */
    public CatalogueLineSynopsisType createCatalogueLineSynopsisType() {
        return new CatalogueLineSynopsisType();
    }

    /**
     * Create an instance of {@link ItemSynopsisType }
     * 
     */
    public ItemSynopsisType createItemSynopsisType() {
        return new ItemSynopsisType();
    }

    /**
     * Create an instance of {@link CatalogueSynopsisType }
     * 
     */
    public CatalogueSynopsisType createCatalogueSynopsisType() {
        return new CatalogueSynopsisType();
    }

    /**
     * Create an instance of {@link ItemClassificationType }
     * 
     */
    public ItemClassificationType createItemClassificationType() {
        return new ItemClassificationType();
    }

    /**
     * Create an instance of {@link CatalogueItemClassificationType }
     * 
     */
    public CatalogueItemClassificationType createCatalogueItemClassificationType() {
        return new CatalogueItemClassificationType();
    }

    /**
     * Create an instance of {@link BrandNameCriterion }
     * 
     */
    public BrandNameCriterion createBrandNameCriterion() {
        return new BrandNameCriterion();
    }

    /**
     * Create an instance of {@link ModelNameCriterion }
     * 
     */
    public ModelNameCriterion createModelNameCriterion() {
        return new ModelNameCriterion();
    }

    /**
     * Create an instance of {@link NameCriterion }
     * 
     */
    public NameCriterion createNameCriterion() {
        return new NameCriterion();
    }

    /**
     * Create an instance of {@link ItemClassificationCodeCriterion }
     * 
     */
    public ItemClassificationCodeCriterion createItemClassificationCodeCriterion() {
        return new ItemClassificationCodeCriterion();
    }

    /**
     * Create an instance of {@link RelatedItemCriterion }
     * 
     */
    public RelatedItemCriterion createRelatedItemCriterion() {
        return new RelatedItemCriterion();
    }

    /**
     * Create an instance of {@link DocumentSummaryType }
     * 
     */
    public DocumentSummaryType createDocumentSummaryType() {
        return new DocumentSummaryType();
    }

    /**
     * Create an instance of {@link DocumentTimelineType }
     * 
     */
    public DocumentTimelineType createDocumentTimelineType() {
        return new DocumentTimelineType();
    }

    /**
     * Create an instance of {@link ECLotType }
     * 
     */
    public ECLotType createECLotType() {
        return new ECLotType();
    }

    /**
     * Create an instance of {@link ECRequestForQuotationDocumentReferenceType }
     * 
     */
    public ECRequestForQuotationDocumentReferenceType createECRequestForQuotationDocumentReferenceType() {
        return new ECRequestForQuotationDocumentReferenceType();
    }

    /**
     * Create an instance of {@link NextDocumentType }
     * 
     */
    public NextDocumentType createNextDocumentType() {
        return new NextDocumentType();
    }

    /**
     * Create an instance of {@link OriginatorDocumentOpenerPartyType }
     * 
     */
    public OriginatorDocumentOpenerPartyType createOriginatorDocumentOpenerPartyType() {
        return new OriginatorDocumentOpenerPartyType();
    }

    /**
     * Create an instance of {@link ProcessTimelineType }
     * 
     */
    public ProcessTimelineType createProcessTimelineType() {
        return new ProcessTimelineType();
    }

    /**
     * Create an instance of {@link BusinessScopeType }
     * 
     */
    public BusinessScopeType createBusinessScopeType() {
        return new BusinessScopeType();
    }

    /**
     * Create an instance of {@link SpecificContentType }
     * 
     */
    public SpecificContentType createSpecificContentType() {
        return new SpecificContentType();
    }

    /**
     * Create an instance of {@link FromPartyType }
     * 
     */
    public FromPartyType createFromPartyType() {
        return new FromPartyType();
    }

    /**
     * Create an instance of {@link ToPartyType }
     * 
     */
    public ToPartyType createToPartyType() {
        return new ToPartyType();
    }

    /**
     * Create an instance of {@link PayloadHashType }
     * 
     */
    public PayloadHashType createPayloadHashType() {
        return new PayloadHashType();
    }

    /**
     * Create an instance of {@link DocumentReferenceRestrictionType }
     * 
     */
    public DocumentReferenceRestrictionType createDocumentReferenceRestrictionType() {
        return new DocumentReferenceRestrictionType();
    }

    /**
     * Create an instance of {@link BusinessHeaderType.ValidationResult }
     * 
     */
    public BusinessHeaderType.ValidationResult createBusinessHeaderTypeValidationResult() {
        return new BusinessHeaderType.ValidationResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterchangeAgreementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "InterchangeAgreement")
    public JAXBElement<InterchangeAgreementType> createInterchangeAgreement(InterchangeAgreementType value) {
        return new JAXBElement<InterchangeAgreementType>(_InterchangeAgreement_QNAME, InterchangeAgreementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LargeAttachmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "LargeAttachment")
    public JAXBElement<LargeAttachmentType> createLargeAttachment(LargeAttachmentType value) {
        return new JAXBElement<LargeAttachmentType>(_LargeAttachment_QNAME, LargeAttachmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceInformationReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ResourceInformationReference")
    public JAXBElement<ResourceInformationReferenceType> createResourceInformationReference(ResourceInformationReferenceType value) {
        return new JAXBElement<ResourceInformationReferenceType>(_ResourceInformationReference_QNAME, ResourceInformationReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Base64BinaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "StreamBase64Binary")
    public JAXBElement<Base64BinaryType> createStreamBase64Binary(Base64BinaryType value) {
        return new JAXBElement<Base64BinaryType>(_StreamBase64Binary_QNAME, Base64BinaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentSizeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentSize")
    public JAXBElement<DocumentSizeType> createDocumentSize(DocumentSizeType value) {
        return new JAXBElement<DocumentSizeType>(_DocumentSize_QNAME, DocumentSizeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FormatType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "Format")
    public JAXBElement<FormatType> createFormat(FormatType value) {
        return new JAXBElement<FormatType>(_Format_QNAME, FormatType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "Creator")
    public JAXBElement<CreatorType> createCreator(CreatorType value) {
        return new JAXBElement<CreatorType>(_Creator_QNAME, CreatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TitleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "Title")
    public JAXBElement<TitleType> createTitle(TitleType value) {
        return new JAXBElement<TitleType>(_Title_QNAME, TitleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserIDType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "UserID")
    public JAXBElement<UserIDType> createUserID(UserIDType value) {
        return new JAXBElement<UserIDType>(_UserID_QNAME, UserIDType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessDocumentTypeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "BusinessDocumentType")
    public JAXBElement<BusinessDocumentTypeType> createBusinessDocumentType(BusinessDocumentTypeType value) {
        return new JAXBElement<BusinessDocumentTypeType>(_BusinessDocumentType_QNAME, BusinessDocumentTypeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncludeMessagesInErrorIndicatorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "IncludeMessagesInErrorIndicator")
    public JAXBElement<IncludeMessagesInErrorIndicatorType> createIncludeMessagesInErrorIndicator(IncludeMessagesInErrorIndicatorType value) {
        return new JAXBElement<IncludeMessagesInErrorIndicatorType>(_IncludeMessagesInErrorIndicator_QNAME, IncludeMessagesInErrorIndicatorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaginationRangeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "PaginationRange")
    public JAXBElement<PaginationRangeType> createPaginationRange(PaginationRangeType value) {
        return new JAXBElement<PaginationRangeType>(_PaginationRange_QNAME, PaginationRangeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DigestMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentHashMethod")
    public JAXBElement<DigestMethodType> createDocumentHashMethod(DigestMethodType value) {
        return new JAXBElement<DigestMethodType>(_DocumentHashMethod_QNAME, DigestMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanonicalizationMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentCanonicalizationMethod")
    public JAXBElement<CanonicalizationMethodType> createDocumentCanonicalizationMethod(CanonicalizationMethodType value) {
        return new JAXBElement<CanonicalizationMethodType>(_DocumentCanonicalizationMethod_QNAME, CanonicalizationMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DigestMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "PayloadDigestMethod")
    public JAXBElement<DigestMethodType> createPayloadDigestMethod(DigestMethodType value) {
        return new JAXBElement<DigestMethodType>(_PayloadDigestMethod_QNAME, DigestMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DigestValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "PayloadDigestValue")
    public JAXBElement<DigestValueType> createPayloadDigestValue(DigestValueType value) {
        return new JAXBElement<DigestValueType>(_PayloadDigestValue_QNAME, DigestValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanonicalizationMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "PayloadCanonicalizationMethod")
    public JAXBElement<CanonicalizationMethodType> createPayloadCanonicalizationMethod(CanonicalizationMethodType value) {
        return new JAXBElement<CanonicalizationMethodType>(_PayloadCanonicalizationMethod_QNAME, CanonicalizationMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DigestValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DigestValue")
    public JAXBElement<DigestValueType> createDigestValue(DigestValueType value) {
        return new JAXBElement<DigestValueType>(_DigestValue_QNAME, DigestValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptionInformationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "EncryptionInformation")
    public JAXBElement<EncryptionInformationType> createEncryptionInformation(EncryptionInformationType value) {
        return new JAXBElement<EncryptionInformationType>(_EncryptionInformation_QNAME, EncryptionInformationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityInformationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "SecurityInformation")
    public JAXBElement<SecurityInformationType> createSecurityInformation(SecurityInformationType value) {
        return new JAXBElement<SecurityInformationType>(_SecurityInformation_QNAME, SecurityInformationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "SenderPartyCertificate")
    public JAXBElement<CertificateType> createSenderPartyCertificate(CertificateType value) {
        return new JAXBElement<CertificateType>(_SenderPartyCertificate_QNAME, CertificateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ReceiverPartyCertificate")
    public JAXBElement<CertificateType> createReceiverPartyCertificate(CertificateType value) {
        return new JAXBElement<CertificateType>(_ReceiverPartyCertificate_QNAME, CertificateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimestampType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "Timestamp")
    public JAXBElement<TimestampType> createTimestamp(TimestampType value) {
        return new JAXBElement<TimestampType>(_Timestamp_QNAME, TimestampType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentReferenceRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentReferenceRequest")
    public JAXBElement<DocumentReferenceRequestType> createDocumentReferenceRequest(DocumentReferenceRequestType value) {
        return new JAXBElement<DocumentReferenceRequestType>(_DocumentReferenceRequest_QNAME, DocumentReferenceRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentReferenceResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentReferenceResponse")
    public JAXBElement<DocumentReferenceResponseType> createDocumentReferenceResponse(DocumentReferenceResponseType value) {
        return new JAXBElement<DocumentReferenceResponseType>(_DocumentReferenceResponse_QNAME, DocumentReferenceResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtendedDocumentReferenceResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ExtendedDocumentReferenceResponse")
    public JAXBElement<ExtendedDocumentReferenceResponseType> createExtendedDocumentReferenceResponse(ExtendedDocumentReferenceResponseType value) {
        return new JAXBElement<ExtendedDocumentReferenceResponseType>(_ExtendedDocumentReferenceResponse_QNAME, ExtendedDocumentReferenceResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentReferenceResponseRelatedDocsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentReferenceResponseRelatedDocs")
    public JAXBElement<DocumentReferenceResponseRelatedDocsType> createDocumentReferenceResponseRelatedDocs(DocumentReferenceResponseRelatedDocsType value) {
        return new JAXBElement<DocumentReferenceResponseRelatedDocsType>(_DocumentReferenceResponseRelatedDocs_QNAME, DocumentReferenceResponseRelatedDocsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentReferenceResponseParentDocsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentReferenceResponseParentDocs")
    public JAXBElement<DocumentReferenceResponseParentDocsType> createDocumentReferenceResponseParentDocs(DocumentReferenceResponseParentDocsType value) {
        return new JAXBElement<DocumentReferenceResponseParentDocsType>(_DocumentReferenceResponseParentDocs_QNAME, DocumentReferenceResponseParentDocsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtendedDocumentReferenceResponseParentDocsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ExtendedDocumentReferenceResponseParentDocs")
    public JAXBElement<ExtendedDocumentReferenceResponseParentDocsType> createExtendedDocumentReferenceResponseParentDocs(ExtendedDocumentReferenceResponseParentDocsType value) {
        return new JAXBElement<ExtendedDocumentReferenceResponseParentDocsType>(_ExtendedDocumentReferenceResponseParentDocs_QNAME, ExtendedDocumentReferenceResponseParentDocsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentContentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentContent")
    public JAXBElement<DocumentContentType> createDocumentContent(DocumentContentType value) {
        return new JAXBElement<DocumentContentType>(_DocumentContent_QNAME, DocumentContentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentSenderParty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentSenderParty")
    public JAXBElement<DocumentSenderParty> createDocumentSenderParty(DocumentSenderParty value) {
        return new JAXBElement<DocumentSenderParty>(_DocumentSenderParty_QNAME, DocumentSenderParty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentReceiverParty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentReceiverParty")
    public JAXBElement<DocumentReceiverParty> createDocumentReceiverParty(DocumentReceiverParty value) {
        return new JAXBElement<DocumentReceiverParty>(_DocumentReceiverParty_QNAME, DocumentReceiverParty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentWrapperReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentWrapperReference")
    public JAXBElement<DocumentWrapperReferenceType> createDocumentWrapperReference(DocumentWrapperReferenceType value) {
        return new JAXBElement<DocumentWrapperReferenceType>(_DocumentWrapperReference_QNAME, DocumentWrapperReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestDocumentParameterType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "RequestDocumentParameter")
    public JAXBElement<RequestDocumentParameterType> createRequestDocumentParameter(RequestDocumentParameterType value) {
        return new JAXBElement<RequestDocumentParameterType>(_RequestDocumentParameter_QNAME, RequestDocumentParameterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtendedRequestDocumentParameterType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ExtendedRequestDocumentParameter")
    public JAXBElement<ExtendedRequestDocumentParameterType> createExtendedRequestDocumentParameter(ExtendedRequestDocumentParameterType value) {
        return new JAXBElement<ExtendedRequestDocumentParameterType>(_ExtendedRequestDocumentParameter_QNAME, ExtendedRequestDocumentParameterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcknowledgedDocumentReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "AcknowledgedDocumentReference")
    public JAXBElement<AcknowledgedDocumentReferenceType> createAcknowledgedDocumentReference(AcknowledgedDocumentReferenceType value) {
        return new JAXBElement<AcknowledgedDocumentReferenceType>(_AcknowledgedDocumentReference_QNAME, AcknowledgedDocumentReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentReferenceResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "RelatedDocument")
    public JAXBElement<DocumentReferenceResponseType> createRelatedDocument(DocumentReferenceResponseType value) {
        return new JAXBElement<DocumentReferenceResponseType>(_RelatedDocument_QNAME, DocumentReferenceResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentReferenceResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ParentDocument")
    public JAXBElement<DocumentReferenceResponseType> createParentDocument(DocumentReferenceResponseType value) {
        return new JAXBElement<DocumentReferenceResponseType>(_ParentDocument_QNAME, DocumentReferenceResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ECDocumentReceivedDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ECDocumentReceivedData")
    public JAXBElement<ECDocumentReceivedDataType> createECDocumentReceivedData(ECDocumentReceivedDataType value) {
        return new JAXBElement<ECDocumentReceivedDataType>(_ECDocumentReceivedData_QNAME, ECDocumentReceivedDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessingWarningType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ProcessingWarning")
    public JAXBElement<ProcessingWarningType> createProcessingWarning(ProcessingWarningType value) {
        return new JAXBElement<ProcessingWarningType>(_ProcessingWarning_QNAME, ProcessingWarningType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdditionalProperty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "AdditionaHeaderlProperty")
    public JAXBElement<AdditionalProperty> createAdditionaHeaderlProperty(AdditionalProperty value) {
        return new JAXBElement<AdditionalProperty>(_AdditionaHeaderlProperty_QNAME, AdditionalProperty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorisationHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "AuthorisationHeader")
    public JAXBElement<AuthorisationHeaderType> createAuthorisationHeader(AuthorisationHeaderType value) {
        return new JAXBElement<AuthorisationHeaderType>(_AuthorisationHeader_QNAME, AuthorisationHeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "Header")
    public JAXBElement<HeaderType> createHeader(HeaderType value) {
        return new JAXBElement<HeaderType>(_Header_QNAME, HeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "BusinessHeader")
    public JAXBElement<BusinessHeaderType> createBusinessHeader(BusinessHeaderType value) {
        return new JAXBElement<BusinessHeaderType>(_BusinessHeader_QNAME, BusinessHeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TechnicalHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "TechnicalHeader")
    public JAXBElement<TechnicalHeaderType> createTechnicalHeader(TechnicalHeaderType value) {
        return new JAXBElement<TechnicalHeaderType>(_TechnicalHeader_QNAME, TechnicalHeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CatalogueLineSynopsisType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "CatalogueLineSynopsis")
    public JAXBElement<CatalogueLineSynopsisType> createCatalogueLineSynopsis(CatalogueLineSynopsisType value) {
        return new JAXBElement<CatalogueLineSynopsisType>(_CatalogueLineSynopsis_QNAME, CatalogueLineSynopsisType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ItemSynopsisType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ItemSynopsis")
    public JAXBElement<ItemSynopsisType> createItemSynopsis(ItemSynopsisType value) {
        return new JAXBElement<ItemSynopsisType>(_ItemSynopsis_QNAME, ItemSynopsisType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CatalogueSynopsisType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "CatalogueSynopsis")
    public JAXBElement<CatalogueSynopsisType> createCatalogueSynopsis(CatalogueSynopsisType value) {
        return new JAXBElement<CatalogueSynopsisType>(_CatalogueSynopsis_QNAME, CatalogueSynopsisType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ItemClassificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ItemClassification")
    public JAXBElement<ItemClassificationType> createItemClassification(ItemClassificationType value) {
        return new JAXBElement<ItemClassificationType>(_ItemClassification_QNAME, ItemClassificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ItemClassificationCodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ParentItemClassificationCode")
    public JAXBElement<ItemClassificationCodeType> createParentItemClassificationCode(ItemClassificationCodeType value) {
        return new JAXBElement<ItemClassificationCodeType>(_ParentItemClassificationCode_QNAME, ItemClassificationCodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "NumberOfItems")
    public JAXBElement<BigDecimal> createNumberOfItems(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_NumberOfItems_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ItemClassificationOrderSequence")
    public JAXBElement<BigDecimal> createItemClassificationOrderSequence(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ItemClassificationOrderSequence_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CatalogueItemClassificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "CatalogueItemClassification")
    public JAXBElement<CatalogueItemClassificationType> createCatalogueItemClassification(CatalogueItemClassificationType value) {
        return new JAXBElement<CatalogueItemClassificationType>(_CatalogueItemClassification_QNAME, CatalogueItemClassificationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ArchiveDocumentReference")
    public JAXBElement<DocumentReferenceType> createArchiveDocumentReference(DocumentReferenceType value) {
        return new JAXBElement<DocumentReferenceType>(_ArchiveDocumentReference_QNAME, DocumentReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentSummaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentSummary")
    public JAXBElement<DocumentSummaryType> createDocumentSummary(DocumentSummaryType value) {
        return new JAXBElement<DocumentSummaryType>(_DocumentSummary_QNAME, DocumentSummaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentTimelineType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "DocumentTimeline")
    public JAXBElement<DocumentTimelineType> createDocumentTimeline(DocumentTimelineType value) {
        return new JAXBElement<DocumentTimelineType>(_DocumentTimeline_QNAME, DocumentTimelineType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ECLotType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ECLot")
    public JAXBElement<ECLotType> createECLot(ECLotType value) {
        return new JAXBElement<ECLotType>(_ECLot_QNAME, ECLotType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ECRequestForQuotationDocumentReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ECRequestForQuotationDocumentReference")
    public JAXBElement<ECRequestForQuotationDocumentReferenceType> createECRequestForQuotationDocumentReference(ECRequestForQuotationDocumentReferenceType value) {
        return new JAXBElement<ECRequestForQuotationDocumentReferenceType>(_ECRequestForQuotationDocumentReference_QNAME, ECRequestForQuotationDocumentReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NextDocumentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "NextDocument")
    public JAXBElement<NextDocumentType> createNextDocument(NextDocumentType value) {
        return new JAXBElement<NextDocumentType>(_NextDocument_QNAME, NextDocumentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OriginatorDocumentOpenerPartyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "OriginatorDocumentOpenerParty")
    public JAXBElement<OriginatorDocumentOpenerPartyType> createOriginatorDocumentOpenerParty(OriginatorDocumentOpenerPartyType value) {
        return new JAXBElement<OriginatorDocumentOpenerPartyType>(_OriginatorDocumentOpenerParty_QNAME, OriginatorDocumentOpenerPartyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessTimelineType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ProcessTimeline")
    public JAXBElement<ProcessTimelineType> createProcessTimeline(ProcessTimelineType value) {
        return new JAXBElement<ProcessTimelineType>(_ProcessTimeline_QNAME, ProcessTimelineType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessScopeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "BusinessScope")
    public JAXBElement<BusinessScopeType> createBusinessScope(BusinessScopeType value) {
        return new JAXBElement<BusinessScopeType>(_BusinessScope_QNAME, BusinessScopeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SpecificContentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "SpecificContent")
    public JAXBElement<SpecificContentType> createSpecificContent(SpecificContentType value) {
        return new JAXBElement<SpecificContentType>(_SpecificContent_QNAME, SpecificContentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterchangeAgreementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "FromInterchangeAgreement")
    public JAXBElement<InterchangeAgreementType> createFromInterchangeAgreement(InterchangeAgreementType value) {
        return new JAXBElement<InterchangeAgreementType>(_FromInterchangeAgreement_QNAME, InterchangeAgreementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InterchangeAgreementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ToInterchangeAgreement")
    public JAXBElement<InterchangeAgreementType> createToInterchangeAgreement(InterchangeAgreementType value) {
        return new JAXBElement<InterchangeAgreementType>(_ToInterchangeAgreement_QNAME, InterchangeAgreementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FromPartyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "FromParty")
    public JAXBElement<FromPartyType> createFromParty(FromPartyType value) {
        return new JAXBElement<FromPartyType>(_FromParty_QNAME, FromPartyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ToPartyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:CommonAggregateComponents-2", name = "ToParty")
    public JAXBElement<ToPartyType> createToParty(ToPartyType value) {
        return new JAXBElement<ToPartyType>(_ToParty_QNAME, ToPartyType.class, null, value);
    }

}
