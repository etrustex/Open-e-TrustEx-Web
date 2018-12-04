(function (global, factory) {
	typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports, require('tslib'), require('xml-core'), require('xmldsigjs')) :
	typeof define === 'function' && define.amd ? define(['exports', 'tslib', 'xml-core', 'xmldsigjs'], factory) :
	(factory((global.XAdES = {}),global.tslib_1,global.XmlCore,global.XmlDSigJs));
}(this, (function (exports,tslib_1,XmlCore,XmlDSigJs) { 'use strict';

var XmlXades = {
    DefaultPrefix: "xades",

    NamespaceURI: "http://uri.etsi.org/01903/v1.3.2#",

    SignedPropertiesType: "http://uri.etsi.org/01903/v1.3.2#SignedProperties",
    ElementNames: {
        Any: "Any",
        ByName: "ByName",
        ByKey: "ByKey",
        AttrAuthoritiesCertValues: "AttrAuthoritiesCertValues",
        AttributeRevocationValues: "AttributeRevocationValues",
        AttributeCertificateRefs: "AttributeCertificateRefs",
        AttributeRevocationRefs: "AttributeRevocationRefs",
        QualifyingProperties: "QualifyingProperties",
        QualifyingPropertiesReference: "QualifyingPropertiesReference",
        SignedProperties: "SignedProperties",
        SignedSignatureProperties: "SignedSignatureProperties",
        SignedDataObjectProperties: "SignedDataObjectProperties",
        UnsignedProperties: "UnsignedProperties",
        UnsignedSignatureProperties: "UnsignedSignatureProperties",
        UnsignedDataObjectProperties: "UnsignedDataObjectProperties",
        UnsignedDataObjectProperty: "UnsignedDataObjectProperty",
        SigningTime: "SigningTime",
        SigningCertificate: "SigningCertificate",
        SignaturePolicyIdentifier: "SignaturePolicyIdentifier",
        SignatureProductionPlace: "SignatureProductionPlace",
        SignerRole: "SignerRole",
        Cert: "Cert",
        CertDigest: "CertDigest",
        IssuerSerial: "IssuerSerial",
        DataObjectFormat: "DataObjectFormat",
        CommitmentTypeIndication: "CommitmentTypeIndication",
        AllDataObjectsTimeStamp: "AllDataObjectsTimeStamp",
        IndividualDataObjectsTimeStamp: "IndividualDataObjectsTimeStamp",
        HashDataInfo: "HashDataInfo",
        EncapsulatedTimeStamp: "EncapsulatedTimeStamp",
        XMLTimeStamp: "XMLTimeStamp",
        XAdESTimeStamp: "XAdESTimeStamp",
        OtherTimeStamp: "OtherTimeStamp",
        Description: "Description",
        ObjectIdentifier: "ObjectIdentifier",
        MimeType: "MimeType",
        Encoding: "Encoding",
        Identifier: "Identifier",
        DocumentationReferences: "DocumentationReferences",
        DocumentationReference: "DocumentationReference",
        CommitmentTypeId: "CommitmentTypeId",
        ObjectReference: "ObjectReference",
        CommitmentTypeQualifiers: "CommitmentTypeQualifiers",
        AllSignedDataObjects: "AllSignedDataObjects",
        CommitmentTypeQualifier: "CommitmentTypeQualifier",
        SignaturePolicyId: "SignaturePolicyId",
        SignaturePolicyImplied: "SignaturePolicyImplied",
        SigPolicyId: "SigPolicyId",
        SigPolicyHash: "SigPolicyHash",
        SigPolicyQualifier: "SigPolicyQualifier",
        SigPolicyQualifiers: "SigPolicyQualifiers",
        SPURI: "SPURI",
        SPUserNotice: "SPUserNotice",
        NoticeRef: "NoticeRef",
        ExplicitText: "ExplicitText",
        ClaimedRoles: "ClaimedRoles",
        ClaimedRole: "ClaimedRole",
        CertifiedRoles: "CertifiedRoles",
        CertifiedRole: "CertifiedRole",
        Organization: "Organization",
        NoticeNumbers: "NoticeNumbers",
        Int: "int",
        City: "City",
        PostalCode: "PostalCode",
        StateOrProvince: "StateOrProvince",
        CountryName: "CountryName",
        CounterSignature: "CounterSignature",
        SignatureTimeStamp: "SignatureTimeStamp",
        CompleteCertificateRefs: "CompleteCertificateRefs",
        CompleteRevocationRefs: "CompleteRevocationRefs",
        SigAndRefsTimeStamp: "SigAndRefsTimeStamp",
        RefsOnlyTimeStamp: "RefsOnlyTimeStamp",
        CertificateValues: "CertificateValues",
        RevocationValues: "RevocationValues",
        ArchiveTimeStamp: "ArchiveTimeStamp",
        CertRefs: "CertRefs",
        CRLRefs: "CRLRefs",
        CRLRef: "CRLRef",
        OCSPRefs: "OCSPRefs",
        OtherRefs: "OtherRefs",
        OtherRef: "OtherRef",
        DigestAlgAndValue: "DigestAlgAndValue",
        CRLIdentifier: "CRLIdentifier",
        Issuer: "Issuer",
        IssueTime: "IssueTime",
        Number: "Number",
        OCSPRef: "OCSPRef",
        OCSPIdentifier: "OCSPIdentifier",
        ResponderID: "ResponderID",
        ProducedAt: "ProducedAt",
        EncapsulatedX509Certificate: "EncapsulatedX509Certificate",
        OtherCertificate: "OtherCertificate",
        CRLValues: "CRLValues",
        OCSPValues: "OCSPValues",
        OtherValues: "OtherValues",
        OtherValue: "OtherValue",
        EncapsulatedCRLValue: "EncapsulatedCRLValue",
        EncapsulatedOCSPValue: "EncapsulatedOCSPValue",
        ReferenceInfo: "ReferenceInfo",
        Include: "Include"
    },
    AttributeNames: {
        Id: "Id",
        Encoding: "Encoding",
        Target: "Target",
        ObjectReference: "ObjectReference",
        Qualifier: "Qualifier",
        Uri: "uri",
        URI: "URI",
        ReferencedData: "referencedData"
    }
};

var XadesObject = function (_super) {
    tslib_1.__extends(XadesObject, _super);
    function XadesObject() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    XadesObject = tslib_1.__decorate([XmlCore.XmlElement({
        localName: "xades",
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix
    })], XadesObject);
    return XadesObject;
}(XmlCore.XmlObject);
var XadesCollection = function (_super) {
    tslib_1.__extends(XadesCollection, _super);
    function XadesCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    XadesCollection = tslib_1.__decorate([XmlCore.XmlElement({
        localName: "xades_collection",
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix
    })], XadesCollection);
    return XadesCollection;
}(XmlCore.XmlCollection);

var Any = function (_super) {
    tslib_1.__extends(Any, _super);
    function Any() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlContent()], Any.prototype, "Value", void 0);
    Any = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.Any
    })], Any);
    return Any;
}(XadesObject);
var AnyCollection = function (_super) {
    tslib_1.__extends(AnyCollection, _super);
    function AnyCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    AnyCollection = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.Any
    })], AnyCollection);
    return AnyCollection;
}(XadesCollection);

var XmlEncodingConverter = {
    get: function get(value) {
        switch (value) {
            case "der":
            case "ber":
            case "cer":
            case "per":
            case "xer":
                return "http://uri.etsi.org/01903/v1.2.2#" + value.toUpperCase();
        }
        return void 0;
    },
    set: function set(value) {
        var regexp = /#(\w+)$/;
        var res = regexp.exec(value);
        if (res) {
            return res[1].toLowerCase();
        }
        return null;
    }
};
var EncapsulatedPKIData = function (_super) {
    tslib_1.__extends(EncapsulatedPKIData, _super);
    function EncapsulatedPKIData() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({
        localName: XmlXades.AttributeNames.Id,
        defaultValue: ""
    })], EncapsulatedPKIData.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlAttribute({
        localName: XmlXades.AttributeNames.Encoding,
        defaultValue: null,
        converter: XmlEncodingConverter
    })], EncapsulatedPKIData.prototype, "Encoding", void 0);
    tslib_1.__decorate([XmlCore.XmlContent({
        required: true,
        converter: XmlCore.XmlBase64Converter
    })], EncapsulatedPKIData.prototype, "Value", void 0);
    EncapsulatedPKIData = tslib_1.__decorate([XmlCore.XmlElement({
        localName: "EncapsulatedPKIData"
    })], EncapsulatedPKIData);
    return EncapsulatedPKIData;
}(XadesObject);

var OtherCertificate = function (_super) {
    tslib_1.__extends(OtherCertificate, _super);
    function OtherCertificate() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    OtherCertificate = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OtherCertificate })], OtherCertificate);
    return OtherCertificate;
}(Any);
var OtherCertificateCollection = function (_super) {
    tslib_1.__extends(OtherCertificateCollection, _super);
    function OtherCertificateCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    OtherCertificateCollection = tslib_1.__decorate([XmlCore.XmlElement({ localName: "OtherCertificateCollection", parser: OtherCertificate })], OtherCertificateCollection);
    return OtherCertificateCollection;
}(XadesCollection);
var EncapsulatedX509Certificate = function (_super) {
    tslib_1.__extends(EncapsulatedX509Certificate, _super);
    function EncapsulatedX509Certificate() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    EncapsulatedX509Certificate = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.EncapsulatedX509Certificate })], EncapsulatedX509Certificate);
    return EncapsulatedX509Certificate;
}(EncapsulatedPKIData);
var EncapsulatedX509CertificateCollection = function (_super) {
    tslib_1.__extends(EncapsulatedX509CertificateCollection, _super);
    function EncapsulatedX509CertificateCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    EncapsulatedX509CertificateCollection = tslib_1.__decorate([XmlCore.XmlElement({ localName: "EncapsulatedX509CertificateCollection", parser: EncapsulatedX509Certificate })], EncapsulatedX509CertificateCollection);
    return EncapsulatedX509CertificateCollection;
}(XadesCollection);
var CertificateValues = function (_super) {
    tslib_1.__extends(CertificateValues, _super);
    function CertificateValues() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], CertificateValues.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: EncapsulatedX509CertificateCollection, noRoot: true })], CertificateValues.prototype, "EncapsulatedX509Certificates", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: OtherCertificateCollection, noRoot: true })], CertificateValues.prototype, "OtherCertificates", void 0);
    CertificateValues = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CertificateValues })], CertificateValues);
    return CertificateValues;
}(XadesObject);

var Identifier = function (_super) {
    tslib_1.__extends(Identifier, _super);
    function Identifier() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({
        localName: XmlXades.AttributeNames.Qualifier
    })], Identifier.prototype, "Qualifier", void 0);
    tslib_1.__decorate([XmlCore.XmlContent({
        defaultValue: "",
        required: true
    })], Identifier.prototype, "Value", void 0);
    Identifier = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.Identifier
    })], Identifier);
    return Identifier;
}(XadesObject);
var DocumentationReference = function (_super) {
    tslib_1.__extends(DocumentationReference, _super);
    function DocumentationReference() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    DocumentationReference.prototype.OnLoadXml = function (e) {
        if (e.textContent) {
            this.Uri = e.textContent;
        }
    };
    DocumentationReference.prototype.OnGetXml = function (e) {
        if (this.Uri) {
            e.textContent = this.Uri;
        }
    };
    tslib_1.__decorate([XmlCore.XmlContent({
        defaultValue: "",
        required: true
    })], DocumentationReference.prototype, "Uri", void 0);
    DocumentationReference = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.DocumentationReference
    })], DocumentationReference);
    return DocumentationReference;
}(XadesObject);
var DocumentationReferences = function (_super) {
    tslib_1.__extends(DocumentationReferences, _super);
    function DocumentationReferences() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    DocumentationReferences = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.DocumentationReferences,
        parser: DocumentationReference
    })], DocumentationReferences);
    return DocumentationReferences;
}(XadesCollection);
var ObjectIdentifier = function (_super) {
    tslib_1.__extends(ObjectIdentifier, _super);
    function ObjectIdentifier() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({
        parser: Identifier,
        required: true
    })], ObjectIdentifier.prototype, "Identifier", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.Description,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        defaultValue: ""
    })], ObjectIdentifier.prototype, "Description", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        parser: DocumentationReferences
    })], ObjectIdentifier.prototype, "DocumentationReferences", void 0);
    ObjectIdentifier = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.ObjectIdentifier
    })], ObjectIdentifier);
    return ObjectIdentifier;
}(XadesObject);

var CommitmentTypeQualifier = function (_super) {
    tslib_1.__extends(CommitmentTypeQualifier, _super);
    function CommitmentTypeQualifier() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    CommitmentTypeQualifier = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CommitmentTypeQualifier })], CommitmentTypeQualifier);
    return CommitmentTypeQualifier;
}(Any);
var CommitmentTypeQualifiers = function (_super) {
    tslib_1.__extends(CommitmentTypeQualifiers, _super);
    function CommitmentTypeQualifiers() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    CommitmentTypeQualifiers = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CommitmentTypeQualifiers, parser: CommitmentTypeQualifier })], CommitmentTypeQualifiers);
    return CommitmentTypeQualifiers;
}(XadesCollection);
var ObjectReference = function (_super) {
    tslib_1.__extends(ObjectReference, _super);
    function ObjectReference() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlContent({ required: true })], ObjectReference.prototype, "Value", void 0);
    ObjectReference = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.ObjectReference })], ObjectReference);
    return ObjectReference;
}(XadesObject);
var ObjectReferenceCollection = function (_super) {
    tslib_1.__extends(ObjectReferenceCollection, _super);
    function ObjectReferenceCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    ObjectReferenceCollection = tslib_1.__decorate([XmlCore.XmlElement({ localName: "ObjectReferences", parser: ObjectReference })], ObjectReferenceCollection);
    return ObjectReferenceCollection;
}(XadesCollection);
var XmlAllSignedDataObjectsConverter = {
    set: function set(value) {
        return true;
    },
    get: function get(value) {
        return void 0;
    }
};
var CommitmentTypeIndication = function (_super) {
    tslib_1.__extends(CommitmentTypeIndication, _super);
    function CommitmentTypeIndication() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.CommitmentTypeId,
        required: true,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        parser: ObjectIdentifier
    })], CommitmentTypeIndication.prototype, "CommitmentTypeId", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: ObjectReferenceCollection, noRoot: true })], CommitmentTypeIndication.prototype, "ObjectReference", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.AllSignedDataObjects,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        converter: XmlAllSignedDataObjectsConverter,
        defaultValue: false
    })], CommitmentTypeIndication.prototype, "AllSignedDataObjects", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.CommitmentTypeQualifiers, parser: CommitmentTypeQualifier })], CommitmentTypeIndication.prototype, "CommitmentTypeQualifiers", void 0);
    CommitmentTypeIndication = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CommitmentTypeIndication })], CommitmentTypeIndication);
    return CommitmentTypeIndication;
}(XadesObject);

var DigestAlgAndValueType = function (_super) {
    tslib_1.__extends(DigestAlgAndValueType, _super);
    function DigestAlgAndValueType() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({
        parser: XmlDSigJs.DigestMethod,
        required: true
    })], DigestAlgAndValueType.prototype, "DigestMethod", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlDSigJs.XmlSignature.ElementNames.DigestValue,
        namespaceURI: XmlDSigJs.XmlSignature.NamespaceURI,
        prefix: XmlDSigJs.XmlSignature.DefaultPrefix,
        converter: XmlCore.XmlBase64Converter,
        required: true
    })], DigestAlgAndValueType.prototype, "DigestValue", void 0);
    DigestAlgAndValueType = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.DigestAlgAndValue })], DigestAlgAndValueType);
    return DigestAlgAndValueType;
}(XadesObject);
var IssuerSerial = function (_super) {
    tslib_1.__extends(IssuerSerial, _super);
    function IssuerSerial() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    IssuerSerial = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.IssuerSerial, namespaceURI: XmlXades.NamespaceURI, prefix: XmlXades.DefaultPrefix })], IssuerSerial);
    return IssuerSerial;
}(XmlDSigJs.X509IssuerSerial);
var Cert = function (_super) {
    tslib_1.__extends(Cert, _super);
    function Cert() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.CertDigest, parser: DigestAlgAndValueType, required: true })], Cert.prototype, "CertDigest", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: IssuerSerial, required: true })], Cert.prototype, "IssuerSerial", void 0);
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.URI })], Cert.prototype, "Uri", void 0);
    Cert = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.Cert })], Cert);
    return Cert;
}(XadesObject);
var CertIDList = function (_super) {
    tslib_1.__extends(CertIDList, _super);
    function CertIDList() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    CertIDList = tslib_1.__decorate([XmlCore.XmlElement({ localName: "CertIDList", parser: Cert })], CertIDList);
    return CertIDList;
}(XadesCollection);
var SigningCertificate = function (_super) {
    tslib_1.__extends(SigningCertificate, _super);
    function SigningCertificate() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    SigningCertificate = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SigningCertificate })], SigningCertificate);
    return SigningCertificate;
}(CertIDList);

var CompleteCertificateRefs = function (_super) {
    tslib_1.__extends(CompleteCertificateRefs, _super);
    function CompleteCertificateRefs() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], CompleteCertificateRefs.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.CertRefs, parser: CertIDList, required: true })], CompleteCertificateRefs.prototype, "CertRefs", void 0);
    CompleteCertificateRefs = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CompleteCertificateRefs })], CompleteCertificateRefs);
    return CompleteCertificateRefs;
}(XadesObject);

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) {
  return typeof obj;
} : function (obj) {
  return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
};

var token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZWN]|"[^"]*"|'[^']*'/g;
var timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g;
var timezoneClip = /[^-+\dA-Z]/g;
function dateFormat(date, mask, utc, gmt) {
    if (arguments.length === 1 && kindOf(date) === "string" && !/\d/.test(date)) {
        mask = date;
        date = undefined;
    }
    date = date || new Date();
    if (!(date instanceof Date)) {
        date = new Date(date);
    }
    if (isNaN(date)) {
        throw TypeError("Invalid date");
    }
    mask = String(masks[mask] || mask || masks.default);

    var maskSlice = mask.slice(0, 4);
    if (maskSlice === "UTC:" || maskSlice === "GMT:") {
        mask = mask.slice(4);
        utc = true;
        if (maskSlice === "GMT:") {
            gmt = true;
        }
    }
    var _ = utc ? "getUTC" : "get";
    var d = date[_ + "Date"]();
    var D = date[_ + "Day"]();
    var m = date[_ + "Month"]();
    var y = date[_ + "FullYear"]();
    var H = date[_ + "Hours"]();
    var M = date[_ + "Minutes"]();
    var s = date[_ + "Seconds"]();
    var L = date[_ + "Milliseconds"]();
    var o = utc ? 0 : date.getTimezoneOffset();
    var W = getWeek(date);
    var N = getDayOfWeek(date);
    var flags = {
        d: d,
        dd: pad(d),
        ddd: i18n.dayNames[D],
        dddd: i18n.dayNames[D + 7],
        m: m + 1,
        mm: pad(m + 1),
        mmm: i18n.monthNames[m],
        mmmm: i18n.monthNames[m + 12],
        yy: String(y).slice(2),
        yyyy: y,
        h: H % 12 || 12,
        hh: pad(H % 12 || 12),
        H: H,
        HH: pad(H),
        M: M,
        MM: pad(M),
        s: s,
        ss: pad(s),
        l: pad(L, 3),
        L: pad(Math.round(L / 10)),
        t: H < 12 ? i18n.timeNames[0] : i18n.timeNames[1],
        tt: H < 12 ? i18n.timeNames[2] : i18n.timeNames[3],
        T: H < 12 ? i18n.timeNames[4] : i18n.timeNames[5],
        TT: H < 12 ? i18n.timeNames[6] : i18n.timeNames[7],
        Z: gmt ? "GMT" : utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
        o: (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),

        W: W,
        N: N
    };
    return mask.replace(token, function (match) {
        if (match in flags) {
            return flags[match];
        }
        return match.slice(1, match.length - 1);
    });
}
var masks = {
    default: "ddd mmm dd yyyy HH:MM:ss",
    shortDate: "m/d/yy",
    mediumDate: "mmm d, yyyy",
    longDate: "mmmm d, yyyy",
    fullDate: "dddd, mmmm d, yyyy",
    shortTime: "h:MM TT",
    mediumTime: "h:MM:ss TT",
    longTime: "h:MM:ss TT Z",
    isoDate: "yyyy-mm-dd",
    isoTime: "HH:MM:ss",
    isoDateTime: "yyyy-mm-dd'T'HH:MM:sso",
    isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'",
    expiresHeaderFormat: "ddd, dd mmm yyyy HH:MM:ss Z"
};

var i18n = {
    dayNames: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
    monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
    timeNames: ["a", "p", "am", "pm", "A", "P", "AM", "PM"]
};
function pad(val, len) {
    if (len === void 0) {
        len = 2;
    }
    val = String(val);
    while (val.length < len) {
        val = "0" + val;
    }
    return val;
}

function getWeek(date) {
    var targetThursday = new Date(date.getFullYear(), date.getMonth(), date.getDate());

    targetThursday.setDate(targetThursday.getDate() - (targetThursday.getDay() + 6) % 7 + 3);

    var firstThursday = new Date(targetThursday.getFullYear(), 0, 4);

    firstThursday.setDate(firstThursday.getDate() - (firstThursday.getDay() + 6) % 7 + 3);

    var ds = targetThursday.getTimezoneOffset() - firstThursday.getTimezoneOffset();
    targetThursday.setHours(targetThursday.getHours() - ds);

    var weekDiff = (targetThursday.getTime() - firstThursday.getTime()) / (86400000 * 7);
    return 1 + Math.floor(weekDiff);
}

function getDayOfWeek(date) {
    var dow = date.getDay();
    if (dow === 0) {
        dow = 7;
    }
    return dow;
}

function kindOf(val) {
    if (val === null) {
        return "null";
    }
    if (val === undefined) {
        return "undefined";
    }
    if ((typeof val === "undefined" ? "undefined" : _typeof(val)) !== "object") {
        return typeof val === "undefined" ? "undefined" : _typeof(val);
    }
    if (Array.isArray(val)) {
        return "array";
    }
    return {}.toString.call(val).slice(8, -1).toLowerCase();
}

var XadesDateTime = function (_super) {
    tslib_1.__extends(XadesDateTime, _super);
    function XadesDateTime() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    XadesDateTime.prototype.OnLoadXml = function (e) {
        if (e.textContent) {
            this.Value = new Date(e.textContent);
        }
    };
    XadesDateTime.prototype.OnGetXml = function (e) {
        if (this.Format) {
            e.textContent = dateFormat(this.Value, this.Format);
        } else {
            e.textContent = this.Value.toISOString();
        }
    };
    tslib_1.__decorate([XmlCore.XmlContent({
        defaultValue: new Date(),
        required: true
    })], XadesDateTime.prototype, "Value", void 0);
    XadesDateTime = tslib_1.__decorate([XmlCore.XmlElement({
        localName: "XadesDateTime",
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix
    })], XadesDateTime);
    return XadesDateTime;
}(XadesObject);

var OtherRef = function (_super) {
    tslib_1.__extends(OtherRef, _super);
    function OtherRef() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    OtherRef = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OtherRef })], OtherRef);
    return OtherRef;
}(Any);
var OtherRefs = function (_super) {
    tslib_1.__extends(OtherRefs, _super);
    function OtherRefs() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    OtherRefs = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OtherRefs })], OtherRefs);
    return OtherRefs;
}(XadesCollection);
var ResponderID = function (_super) {
    tslib_1.__extends(ResponderID, _super);
    function ResponderID() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.ByName,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        required: true
    })], ResponderID.prototype, "ByName", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.ByKey,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        converter: XmlCore.XmlBase64Converter,
        required: true
    })], ResponderID.prototype, "ByKey", void 0);
    ResponderID = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OCSPIdentifier })], ResponderID);
    return ResponderID;
}(XadesObject);
var OCSPIdentifier = function (_super) {
    tslib_1.__extends(OCSPIdentifier, _super);
    function OCSPIdentifier() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.URI, defaultValue: "" })], OCSPIdentifier.prototype, "URI", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: ResponderID, required: true })], OCSPIdentifier.prototype, "ResponderID", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.IssueTime,
        parser: XadesDateTime,
        required: true
    })], OCSPIdentifier.prototype, "ProducedAt", void 0);
    OCSPIdentifier = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OCSPIdentifier })], OCSPIdentifier);
    return OCSPIdentifier;
}(XadesObject);
var OCSPRef = function (_super) {
    tslib_1.__extends(OCSPRef, _super);
    function OCSPRef() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: OCSPIdentifier })], OCSPRef.prototype, "OCSPIdentifier", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: DigestAlgAndValueType, required: true })], OCSPRef.prototype, "DigestAlgAndValue", void 0);
    OCSPRef = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OCSPRef })], OCSPRef);
    return OCSPRef;
}(XadesObject);
var OCSPRefs = function (_super) {
    tslib_1.__extends(OCSPRefs, _super);
    function OCSPRefs() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    OCSPRefs = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OCSPRefs })], OCSPRefs);
    return OCSPRefs;
}(XadesCollection);
var CRLIdentifier = function (_super) {
    tslib_1.__extends(CRLIdentifier, _super);
    function CRLIdentifier() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.URI, defaultValue: "" })], CRLIdentifier.prototype, "URI", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.Issuer,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        required: true
    })], CRLIdentifier.prototype, "Issuer", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.IssueTime,
        parser: XadesDateTime,
        required: true
    })], CRLIdentifier.prototype, "IssueTime", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.Number,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        converter: XmlCore.XmlNumberConverter
    })], CRLIdentifier.prototype, "Number", void 0);
    CRLIdentifier = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CRLIdentifier })], CRLIdentifier);
    return CRLIdentifier;
}(XadesObject);
var CRLRef = function (_super) {
    tslib_1.__extends(CRLRef, _super);
    function CRLRef() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: DigestAlgAndValueType, required: true })], CRLRef.prototype, "DigestAlgAndValue", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: CRLIdentifier })], CRLRef.prototype, "CRLIdentifier", void 0);
    CRLRef = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CRLRef })], CRLRef);
    return CRLRef;
}(XadesObject);
var CRLRefs = function (_super) {
    tslib_1.__extends(CRLRefs, _super);
    function CRLRefs() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    CRLRefs = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CRLRefs })], CRLRefs);
    return CRLRefs;
}(XadesCollection);
var CompleteRevocationRefs = function (_super) {
    tslib_1.__extends(CompleteRevocationRefs, _super);
    function CompleteRevocationRefs() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], CompleteRevocationRefs.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: CRLRefs })], CompleteRevocationRefs.prototype, "CRLRefs", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: OCSPRefs })], CompleteRevocationRefs.prototype, "OCSPRefs", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: OtherRefs })], CompleteRevocationRefs.prototype, "OtherRefs", void 0);
    CompleteRevocationRefs = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CompleteRevocationRefs })], CompleteRevocationRefs);
    return CompleteRevocationRefs;
}(XadesObject);

var CounterSignature = function (_super) {
    tslib_1.__extends(CounterSignature, _super);
    function CounterSignature() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: XmlDSigJs.Signature, required: true })], CounterSignature.prototype, "Signature", void 0);
    CounterSignature = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CounterSignature })], CounterSignature);
    return CounterSignature;
}(XadesObject);

var DataObjectFormat = function (_super) {
    tslib_1.__extends(DataObjectFormat, _super);
    function DataObjectFormat() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.ObjectReference, required: true })], DataObjectFormat.prototype, "ObjectReference", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.Description, namespaceURI: XmlXades.NamespaceURI, prefix: XmlXades.DefaultPrefix })], DataObjectFormat.prototype, "Description", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: ObjectIdentifier })], DataObjectFormat.prototype, "ObjectIdentifier", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.MimeType, namespaceURI: XmlXades.NamespaceURI, prefix: XmlXades.DefaultPrefix })], DataObjectFormat.prototype, "MimeType", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.Encoding, namespaceURI: XmlXades.NamespaceURI, prefix: XmlXades.DefaultPrefix })], DataObjectFormat.prototype, "Encoding", void 0);
    DataObjectFormat = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.DataObjectFormat })], DataObjectFormat);
    return DataObjectFormat;
}(XadesObject);

var Include = function (_super) {
    tslib_1.__extends(Include, _super);
    function Include() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({
        localName: XmlXades.AttributeNames.URI,
        defaultValue: "",
        required: true
    })], Include.prototype, "Uri", void 0);
    tslib_1.__decorate([XmlCore.XmlAttribute({
        localName: XmlXades.AttributeNames.ReferencedData,
        defaultValue: false
    })], Include.prototype, "ReferencedData", void 0);
    Include = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.Include
    })], Include);
    return Include;
}(XadesObject);
var ReferenceInfo = function (_super) {
    tslib_1.__extends(ReferenceInfo, _super);
    function ReferenceInfo() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.URI, defaultValue: "" })], ReferenceInfo.prototype, "Uri", void 0);
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], ReferenceInfo.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlDSigJs.XmlSignature.ElementNames.DigestMethod,
        namespaceURI: XmlDSigJs.XmlSignature.NamespaceURI,
        prefix: XmlDSigJs.XmlSignature.DefaultPrefix,
        required: true
    })], ReferenceInfo.prototype, "DigestMethod", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlDSigJs.XmlSignature.ElementNames.DigestMethod,
        namespaceURI: XmlDSigJs.XmlSignature.NamespaceURI,
        prefix: XmlDSigJs.XmlSignature.DefaultPrefix,
        converter: XmlCore.XmlBase64Converter,
        required: true
    })], ReferenceInfo.prototype, "DigestValue", void 0);
    ReferenceInfo = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.ReferenceInfo
    })], ReferenceInfo);
    return ReferenceInfo;
}(XadesObject);
var ReferenceInfos = function (_super) {
    tslib_1.__extends(ReferenceInfos, _super);
    function ReferenceInfos() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    ReferenceInfos = tslib_1.__decorate([XmlCore.XmlElement({ localName: "ReferenceInfos", parser: ReferenceInfo })], ReferenceInfos);
    return ReferenceInfos;
}(XadesCollection);
var EncapsulatedTimeStamp = function (_super) {
    tslib_1.__extends(EncapsulatedTimeStamp, _super);
    function EncapsulatedTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    EncapsulatedTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.EncapsulatedTimeStamp })], EncapsulatedTimeStamp);
    return EncapsulatedTimeStamp;
}(EncapsulatedPKIData);
var EncapsulatedTimeStampCollection = function (_super) {
    tslib_1.__extends(EncapsulatedTimeStampCollection, _super);
    function EncapsulatedTimeStampCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    EncapsulatedTimeStampCollection = tslib_1.__decorate([XmlCore.XmlElement({ localName: "EncapsulatedPKIDatas", parser: EncapsulatedTimeStamp })], EncapsulatedTimeStampCollection);
    return EncapsulatedTimeStampCollection;
}(XadesCollection);
var XMLTimeStamp = function (_super) {
    tslib_1.__extends(XMLTimeStamp, _super);
    function XMLTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    XMLTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.XMLTimeStamp })], XMLTimeStamp);
    return XMLTimeStamp;
}(Any);
var XMLTimeStampCollection = function (_super) {
    tslib_1.__extends(XMLTimeStampCollection, _super);
    function XMLTimeStampCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    XMLTimeStampCollection = tslib_1.__decorate([XmlCore.XmlElement({ localName: "XMLTimeStampCollection", parser: XMLTimeStamp })], XMLTimeStampCollection);
    return XMLTimeStampCollection;
}(XadesCollection);
var GenericTimeStamp = function (_super) {
    tslib_1.__extends(GenericTimeStamp, _super);
    function GenericTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], GenericTimeStamp.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: Include })], GenericTimeStamp.prototype, "Include", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: ReferenceInfos, noRoot: true })], GenericTimeStamp.prototype, "ReferenceInfo", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: XmlDSigJs.CanonicalizationMethod })], GenericTimeStamp.prototype, "CanonicalizationMethod", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: EncapsulatedTimeStampCollection, noRoot: true })], GenericTimeStamp.prototype, "EncapsulatedTimeStamp", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: XMLTimeStampCollection, noRoot: true })], GenericTimeStamp.prototype, "XMLTimeStamp", void 0);
    GenericTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({
        localName: "GenericTimeStamp"
    })], GenericTimeStamp);
    return GenericTimeStamp;
}(XadesObject);

var OtherTimeStamp = function (_super) {
    tslib_1.__extends(OtherTimeStamp, _super);
    function OtherTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], OtherTimeStamp.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: ReferenceInfos, noRoot: true })], OtherTimeStamp.prototype, "ReferenceInfo", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: XmlDSigJs.CanonicalizationMethod })], OtherTimeStamp.prototype, "CanonicalizationMethod", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: EncapsulatedTimeStampCollection, noRoot: true })], OtherTimeStamp.prototype, "EncapsulatedTimeStamp", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: XMLTimeStampCollection, noRoot: true })], OtherTimeStamp.prototype, "XMLTimeStamp", void 0);
    OtherTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.OtherTimeStamp
    })], OtherTimeStamp);
    return OtherTimeStamp;
}(XadesObject);

var XAdESTimeStamp = function (_super) {
    tslib_1.__extends(XAdESTimeStamp, _super);
    function XAdESTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], XAdESTimeStamp.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: Include })], XAdESTimeStamp.prototype, "Include", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: XmlDSigJs.CanonicalizationMethod })], XAdESTimeStamp.prototype, "CanonicalizationMethod", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: EncapsulatedTimeStampCollection, noRoot: true })], XAdESTimeStamp.prototype, "EncapsulatedTimeStamp", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: XMLTimeStampCollection, noRoot: true })], XAdESTimeStamp.prototype, "XMLTimeStamp", void 0);
    XAdESTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.XAdESTimeStamp
    })], XAdESTimeStamp);
    return XAdESTimeStamp;
}(XadesObject);

var IndividualDataObjectsTimeStamp = function (_super) {
    tslib_1.__extends(IndividualDataObjectsTimeStamp, _super);
    function IndividualDataObjectsTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    IndividualDataObjectsTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.IndividualDataObjectsTimeStamp })], IndividualDataObjectsTimeStamp);
    return IndividualDataObjectsTimeStamp;
}(XAdESTimeStamp);
var IndividualDataObjectsTimeStampCollection = function (_super) {
    tslib_1.__extends(IndividualDataObjectsTimeStampCollection, _super);
    function IndividualDataObjectsTimeStampCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    IndividualDataObjectsTimeStampCollection = tslib_1.__decorate([XmlCore.XmlElement({ localName: "IndividualDataObjectsTimeStampCollection", parser: IndividualDataObjectsTimeStamp })], IndividualDataObjectsTimeStampCollection);
    return IndividualDataObjectsTimeStampCollection;
}(XadesCollection);
var AllDataObjectsTimeStamp = function (_super) {
    tslib_1.__extends(AllDataObjectsTimeStamp, _super);
    function AllDataObjectsTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    AllDataObjectsTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.AllDataObjectsTimeStamp })], AllDataObjectsTimeStamp);
    return AllDataObjectsTimeStamp;
}(XAdESTimeStamp);
var DataObjectFormatCollection = function (_super) {
    tslib_1.__extends(DataObjectFormatCollection, _super);
    function DataObjectFormatCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    DataObjectFormatCollection = tslib_1.__decorate([XmlCore.XmlElement({ localName: "DataObjectFormatCollection", parser: DataObjectFormat })], DataObjectFormatCollection);
    return DataObjectFormatCollection;
}(XadesCollection);
var CommitmentTypeIndicationCollection = function (_super) {
    tslib_1.__extends(CommitmentTypeIndicationCollection, _super);
    function CommitmentTypeIndicationCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    CommitmentTypeIndicationCollection = tslib_1.__decorate([XmlCore.XmlElement({ localName: "CommitmentTypeIndicationCollection", parser: CommitmentTypeIndication })], CommitmentTypeIndicationCollection);
    return CommitmentTypeIndicationCollection;
}(XadesCollection);
var AllDataObjectsTimeStampCollection = function (_super) {
    tslib_1.__extends(AllDataObjectsTimeStampCollection, _super);
    function AllDataObjectsTimeStampCollection() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    AllDataObjectsTimeStampCollection = tslib_1.__decorate([XmlCore.XmlElement({ localName: "AllDataObjectsTimeStampCollection", parser: AllDataObjectsTimeStamp })], AllDataObjectsTimeStampCollection);
    return AllDataObjectsTimeStampCollection;
}(XadesCollection);
var SignedDataObjectProperties = function (_super) {
    tslib_1.__extends(SignedDataObjectProperties, _super);
    function SignedDataObjectProperties() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], SignedDataObjectProperties.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: DataObjectFormatCollection, noRoot: true })], SignedDataObjectProperties.prototype, "DataObjectFormats", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: CommitmentTypeIndicationCollection, noRoot: true })], SignedDataObjectProperties.prototype, "CommitmentTypeIndications", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: AllDataObjectsTimeStampCollection, noRoot: true })], SignedDataObjectProperties.prototype, "AllDataObjectsTimeStamps", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: IndividualDataObjectsTimeStampCollection, noRoot: true })], SignedDataObjectProperties.prototype, "IndividualDataObjectsTimeStamps", void 0);
    SignedDataObjectProperties = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.SignedDataObjectProperties
    })], SignedDataObjectProperties);
    return SignedDataObjectProperties;
}(XadesObject);

var SigPolicyId = function (_super) {
    tslib_1.__extends(SigPolicyId, _super);
    function SigPolicyId() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    SigPolicyId = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SigPolicyId })], SigPolicyId);
    return SigPolicyId;
}(ObjectIdentifier);
var SigPolicyHash = function (_super) {
    tslib_1.__extends(SigPolicyHash, _super);
    function SigPolicyHash() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    SigPolicyHash = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SigPolicyHash })], SigPolicyHash);
    return SigPolicyHash;
}(DigestAlgAndValueType);
var SigPolicyQualifier = function (_super) {
    tslib_1.__extends(SigPolicyQualifier, _super);
    function SigPolicyQualifier() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    SigPolicyQualifier = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SigPolicyQualifier })], SigPolicyQualifier);
    return SigPolicyQualifier;
}(AnyCollection);
var Integer = function (_super) {
    tslib_1.__extends(Integer, _super);
    function Integer() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlContent({ converter: XmlCore.XmlNumberConverter, required: true })], Integer.prototype, "Value", void 0);
    Integer = tslib_1.__decorate([XmlCore.XmlElement({ localName: "int" })], Integer);
    return Integer;
}(XadesObject);
var IntegerList = function (_super) {
    tslib_1.__extends(IntegerList, _super);
    function IntegerList() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    IntegerList = tslib_1.__decorate([XmlCore.XmlElement({ localName: "IntegerList", parser: Integer })], IntegerList);
    return IntegerList;
}(XadesCollection);
var NoticeReference = function (_super) {
    tslib_1.__extends(NoticeReference, _super);
    function NoticeReference() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.Organization,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        required: true
    })], NoticeReference.prototype, "Organization", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.NoticeNumbers, parser: IntegerList, required: true })], NoticeReference.prototype, "NoticeNumbers", void 0);
    NoticeReference = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.NoticeRef })], NoticeReference);
    return NoticeReference;
}(XadesObject);
var SPUserNotice = function (_super) {
    tslib_1.__extends(SPUserNotice, _super);
    function SPUserNotice() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.NoticeRef, parser: NoticeReference })], SPUserNotice.prototype, "NoticeRef", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.ExplicitText,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix
    })], SPUserNotice.prototype, "ExplicitText", void 0);
    SPUserNotice = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SPUserNotice })], SPUserNotice);
    return SPUserNotice;
}(XadesObject);
var SPURI = function (_super) {
    tslib_1.__extends(SPURI, _super);
    function SPURI() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlContent()], SPURI.prototype, "Value", void 0);
    SPURI = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SPURI })], SPURI);
    return SPURI;
}(XadesObject);
var SigPolicyQualifiers = function (_super) {
    tslib_1.__extends(SigPolicyQualifiers, _super);
    function SigPolicyQualifiers() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    SigPolicyQualifiers = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SigPolicyQualifiers, parser: SigPolicyQualifier })], SigPolicyQualifiers);
    return SigPolicyQualifiers;
}(XadesCollection);
var SignaturePolicyId = function (_super) {
    tslib_1.__extends(SignaturePolicyId, _super);
    function SignaturePolicyId() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.SigPolicyId, parser: SigPolicyId, required: true })], SignaturePolicyId.prototype, "SigPolicyId", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: XmlDSigJs.Transforms })], SignaturePolicyId.prototype, "Transforms", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.SigPolicyHash, parser: SigPolicyHash, required: true })], SignaturePolicyId.prototype, "SigPolicyHash", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ localName: XmlXades.ElementNames.SigPolicyQualifiers, parser: SigPolicyQualifiers })], SignaturePolicyId.prototype, "SigPolicyQualifiers", void 0);
    SignaturePolicyId = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SignaturePolicyId })], SignaturePolicyId);
    return SignaturePolicyId;
}(XadesObject);
var XmlSignaturePolicyImpliedConverter = {
    set: function set(value) {
        return true;
    },
    get: function get(value) {
        return "";
    }
};
var SignaturePolicyIdentifier = function (_super) {
    tslib_1.__extends(SignaturePolicyIdentifier, _super);
    function SignaturePolicyIdentifier() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.SignaturePolicyId,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        parser: SignaturePolicyId
    })], SignaturePolicyIdentifier.prototype, "SignaturePolicyId", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.SignaturePolicyImplied,
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix,
        converter: XmlSignaturePolicyImpliedConverter,
        defaultValue: false
    })], SignaturePolicyIdentifier.prototype, "SignaturePolicyImplied", void 0);
    SignaturePolicyIdentifier = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SignaturePolicyIdentifier })], SignaturePolicyIdentifier);
    return SignaturePolicyIdentifier;
}(XadesObject);

var SignatureProductionPlace = function (_super) {
    tslib_1.__extends(SignatureProductionPlace, _super);
    function SignatureProductionPlace() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.City,
        defaultValue: "",
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix
    })], SignatureProductionPlace.prototype, "City", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.StateOrProvince,
        defaultValue: "",
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix
    })], SignatureProductionPlace.prototype, "StateOrProvince", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.PostalCode,
        defaultValue: "",
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix
    })], SignatureProductionPlace.prototype, "PostalCode", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.CountryName,
        defaultValue: "",
        namespaceURI: XmlXades.NamespaceURI,
        prefix: XmlXades.DefaultPrefix
    })], SignatureProductionPlace.prototype, "CountryName", void 0);
    SignatureProductionPlace = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SignatureProductionPlace })], SignatureProductionPlace);
    return SignatureProductionPlace;
}(XadesObject);

var ClaimedRole = function (_super) {
    tslib_1.__extends(ClaimedRole, _super);
    function ClaimedRole() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    ClaimedRole = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.ClaimedRole })], ClaimedRole);
    return ClaimedRole;
}(Any);
var ClaimedRoles = function (_super) {
    tslib_1.__extends(ClaimedRoles, _super);
    function ClaimedRoles() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    ClaimedRoles = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.ClaimedRoles, parser: ClaimedRole })], ClaimedRoles);
    return ClaimedRoles;
}(XadesCollection);
var CertifiedRole = function (_super) {
    tslib_1.__extends(CertifiedRole, _super);
    function CertifiedRole() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    CertifiedRole = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CertifiedRole })], CertifiedRole);
    return CertifiedRole;
}(EncapsulatedPKIData);
var CertifiedRoles = function (_super) {
    tslib_1.__extends(CertifiedRoles, _super);
    function CertifiedRoles() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    CertifiedRoles = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CertifiedRoles, parser: CertifiedRole })], CertifiedRoles);
    return CertifiedRoles;
}(XadesCollection);
var SignerRole = function (_super) {
    tslib_1.__extends(SignerRole, _super);
    function SignerRole() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: ClaimedRoles })], SignerRole.prototype, "ClaimedRoles", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: CertifiedRoles })], SignerRole.prototype, "CertifiedRoles", void 0);
    SignerRole = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SignerRole })], SignerRole);
    return SignerRole;
}(XadesObject);

var SignedSignatureProperties = function (_super) {
    tslib_1.__extends(SignedSignatureProperties, _super);
    function SignedSignatureProperties() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], SignedSignatureProperties.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({
        localName: XmlXades.ElementNames.SigningTime,
        parser: XadesDateTime
    })], SignedSignatureProperties.prototype, "SigningTime", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: SigningCertificate })], SignedSignatureProperties.prototype, "SigningCertificate", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: SignaturePolicyIdentifier })], SignedSignatureProperties.prototype, "SignaturePolicyIdentifier", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: SignatureProductionPlace })], SignedSignatureProperties.prototype, "SignatureProductionPlace", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: SignerRole })], SignedSignatureProperties.prototype, "SignerRole", void 0);
    SignedSignatureProperties = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.SignedSignatureProperties
    })], SignedSignatureProperties);
    return SignedSignatureProperties;
}(XadesObject);

var SignedProperties = function (_super) {
    tslib_1.__extends(SignedProperties, _super);
    function SignedProperties() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], SignedProperties.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: SignedSignatureProperties })], SignedProperties.prototype, "SignedSignatureProperties", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: SignedDataObjectProperties })], SignedProperties.prototype, "SignedDataObjectProperties", void 0);
    SignedProperties = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.SignedProperties
    })], SignedProperties);
    return SignedProperties;
}(XadesObject);

var UnsignedDataObjectProperty = function (_super) {
    tslib_1.__extends(UnsignedDataObjectProperty, _super);
    function UnsignedDataObjectProperty() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    UnsignedDataObjectProperty = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.UnsignedDataObjectProperty })], UnsignedDataObjectProperty);
    return UnsignedDataObjectProperty;
}(Any);
var UnsignedDataObjectProperties = function (_super) {
    tslib_1.__extends(UnsignedDataObjectProperties, _super);
    function UnsignedDataObjectProperties() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], UnsignedDataObjectProperties.prototype, "Id", void 0);
    UnsignedDataObjectProperties = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.UnsignedSignatureProperties, parser: UnsignedDataObjectProperty })], UnsignedDataObjectProperties);
    return UnsignedDataObjectProperties;
}(XadesCollection);

var OtherValue = function (_super) {
    tslib_1.__extends(OtherValue, _super);
    function OtherValue() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    OtherValue = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OtherValue })], OtherValue);
    return OtherValue;
}(EncapsulatedPKIData);
var OtherValues = function (_super) {
    tslib_1.__extends(OtherValues, _super);
    function OtherValues() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    OtherValues = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OCSPValues, parser: OtherValue })], OtherValues);
    return OtherValues;
}(XadesCollection);
var EncapsulatedOCSPValue = function (_super) {
    tslib_1.__extends(EncapsulatedOCSPValue, _super);
    function EncapsulatedOCSPValue() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    EncapsulatedOCSPValue = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.EncapsulatedOCSPValue })], EncapsulatedOCSPValue);
    return EncapsulatedOCSPValue;
}(EncapsulatedPKIData);
var OCSPValues = function (_super) {
    tslib_1.__extends(OCSPValues, _super);
    function OCSPValues() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    OCSPValues = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.OCSPValues })], OCSPValues);
    return OCSPValues;
}(XadesCollection);
var EncapsulatedCRLValue = function (_super) {
    tslib_1.__extends(EncapsulatedCRLValue, _super);
    function EncapsulatedCRLValue() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    EncapsulatedCRLValue = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.EncapsulatedCRLValue })], EncapsulatedCRLValue);
    return EncapsulatedCRLValue;
}(EncapsulatedPKIData);
var CRLValues = function (_super) {
    tslib_1.__extends(CRLValues, _super);
    function CRLValues() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    CRLValues = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.CRLValues, parser: EncapsulatedCRLValue })], CRLValues);
    return CRLValues;
}(XadesCollection);
var RevocationValues = function (_super) {
    tslib_1.__extends(RevocationValues, _super);
    function RevocationValues() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], RevocationValues.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: CRLValues })], RevocationValues.prototype, "CRLValues", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: OCSPValues })], RevocationValues.prototype, "OCSPValues", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: OtherValues })], RevocationValues.prototype, "OtherValues", void 0);
    RevocationValues = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.RevocationValues })], RevocationValues);
    return RevocationValues;
}(XadesObject);

var SignatureTimeStamp = function (_super) {
    tslib_1.__extends(SignatureTimeStamp, _super);
    function SignatureTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    SignatureTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SignatureTimeStamp })], SignatureTimeStamp);
    return SignatureTimeStamp;
}(XAdESTimeStamp);
var SigAndRefsTimeStamp = function (_super) {
    tslib_1.__extends(SigAndRefsTimeStamp, _super);
    function SigAndRefsTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    SigAndRefsTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.SigAndRefsTimeStamp })], SigAndRefsTimeStamp);
    return SigAndRefsTimeStamp;
}(XAdESTimeStamp);
var RefsOnlyTimeStamp = function (_super) {
    tslib_1.__extends(RefsOnlyTimeStamp, _super);
    function RefsOnlyTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    RefsOnlyTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.RefsOnlyTimeStamp })], RefsOnlyTimeStamp);
    return RefsOnlyTimeStamp;
}(XAdESTimeStamp);
var ArchiveTimeStamp = function (_super) {
    tslib_1.__extends(ArchiveTimeStamp, _super);
    function ArchiveTimeStamp() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    ArchiveTimeStamp = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.ArchiveTimeStamp })], ArchiveTimeStamp);
    return ArchiveTimeStamp;
}(XAdESTimeStamp);
var AttributeCertificateRefs = function (_super) {
    tslib_1.__extends(AttributeCertificateRefs, _super);
    function AttributeCertificateRefs() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    AttributeCertificateRefs = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.AttributeCertificateRefs })], AttributeCertificateRefs);
    return AttributeCertificateRefs;
}(CompleteCertificateRefs);
var AttributeRevocationRefs = function (_super) {
    tslib_1.__extends(AttributeRevocationRefs, _super);
    function AttributeRevocationRefs() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    AttributeRevocationRefs = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.AttributeRevocationRefs })], AttributeRevocationRefs);
    return AttributeRevocationRefs;
}(CompleteRevocationRefs);
var AttrAuthoritiesCertValues = function (_super) {
    tslib_1.__extends(AttrAuthoritiesCertValues, _super);
    function AttrAuthoritiesCertValues() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    AttrAuthoritiesCertValues = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.AttrAuthoritiesCertValues })], AttrAuthoritiesCertValues);
    return AttrAuthoritiesCertValues;
}(CertificateValues);
var AttributeRevocationValues = function (_super) {
    tslib_1.__extends(AttributeRevocationValues, _super);
    function AttributeRevocationValues() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    AttributeRevocationValues = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.AttributeRevocationValues })], AttributeRevocationValues);
    return AttributeRevocationValues;
}(RevocationValues);

var UnsignedSignatureProperty = function (_super) {
    tslib_1.__extends(UnsignedSignatureProperty, _super);
    function UnsignedSignatureProperty() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    UnsignedSignatureProperty = tslib_1.__decorate([XmlCore.XmlElement({ localName: "UnsignedSignatureProperty" })], UnsignedSignatureProperty);
    return UnsignedSignatureProperty;
}(XadesObject);
var UnsignedSignatureProperties = function (_super) {
    tslib_1.__extends(UnsignedSignatureProperties, _super);
    function UnsignedSignatureProperties() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    UnsignedSignatureProperties.prototype.OnLoadXml = function (element) {
        for (var i = 0; i < element.childNodes.length; i++) {
            var node = element.childNodes.item(i);
            if (node.nodeType !== XmlCore.XmlNodeType.Element) {
                continue;
            }
            var XmlClass = void 0;
            switch (node.localName) {
                case XmlXades.ElementNames.CounterSignature:
                    XmlClass = CounterSignature;
                    break;
                case XmlXades.ElementNames.SignatureTimeStamp:
                    XmlClass = SignatureTimeStamp;
                    break;
                case XmlXades.ElementNames.CompleteCertificateRefs:
                    XmlClass = CompleteCertificateRefs;
                    break;
                case XmlXades.ElementNames.CompleteRevocationRefs:
                    XmlClass = CompleteRevocationRefs;
                    break;
                case XmlXades.ElementNames.AttributeCertificateRefs:
                    XmlClass = AttributeCertificateRefs;
                    break;
                case XmlXades.ElementNames.AttributeRevocationRefs:
                    XmlClass = AttributeRevocationRefs;
                    break;
                case XmlXades.ElementNames.SigAndRefsTimeStamp:
                    XmlClass = SigAndRefsTimeStamp;
                    break;
                case XmlXades.ElementNames.RefsOnlyTimeStamp:
                    XmlClass = RefsOnlyTimeStamp;
                    break;
                case XmlXades.ElementNames.CertificateValues:
                    XmlClass = CertificateValues;
                    break;
                case XmlXades.ElementNames.RevocationValues:
                    XmlClass = RevocationValues;
                    break;
                case XmlXades.ElementNames.AttrAuthoritiesCertValues:
                    XmlClass = AttrAuthoritiesCertValues;
                    break;
                case XmlXades.ElementNames.AttributeRevocationValues:
                    XmlClass = AttributeRevocationValues;
                    break;
                case XmlXades.ElementNames.ArchiveTimeStamp:
                    XmlClass = ArchiveTimeStamp;
                    break;
            }
            if (XmlClass) {
                var item = XmlClass.LoadXml(node);
                this.Add(item);
            }
        }
    };
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], UnsignedSignatureProperties.prototype, "Id", void 0);
    UnsignedSignatureProperties = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.UnsignedSignatureProperties, parser: UnsignedSignatureProperty })], UnsignedSignatureProperties);
    return UnsignedSignatureProperties;
}(XadesCollection);

var UnsignedProperties = function (_super) {
    tslib_1.__extends(UnsignedProperties, _super);
    function UnsignedProperties() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], UnsignedProperties.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: UnsignedSignatureProperties })], UnsignedProperties.prototype, "UnsignedSignatureProperties", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: UnsignedDataObjectProperties })], UnsignedProperties.prototype, "UnsignedDataObjectProperties", void 0);
    UnsignedProperties = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.UnsignedProperties
    })], UnsignedProperties);
    return UnsignedProperties;
}(XadesObject);

var QualifyingProperties = function (_super) {
    tslib_1.__extends(QualifyingProperties, _super);
    function QualifyingProperties() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Target, required: true })], QualifyingProperties.prototype, "Target", void 0);
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], QualifyingProperties.prototype, "Id", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: SignedProperties })], QualifyingProperties.prototype, "SignedProperties", void 0);
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: UnsignedProperties })], QualifyingProperties.prototype, "UnsignedProperties", void 0);
    QualifyingProperties = tslib_1.__decorate([XmlCore.XmlElement({
        localName: XmlXades.ElementNames.QualifyingProperties
    })], QualifyingProperties);
    return QualifyingProperties;
}(XadesObject);

var QualifyingPropertiesReference = function (_super) {
    tslib_1.__extends(QualifyingPropertiesReference, _super);
    function QualifyingPropertiesReference() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.URI, required: true })], QualifyingPropertiesReference.prototype, "Uri", void 0);
    tslib_1.__decorate([XmlCore.XmlAttribute({ localName: XmlXades.AttributeNames.Id, defaultValue: "" })], QualifyingPropertiesReference.prototype, "Id", void 0);
    QualifyingPropertiesReference = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlXades.ElementNames.QualifyingPropertiesReference })], QualifyingPropertiesReference);
    return QualifyingPropertiesReference;
}(XadesObject);

var DataObject$1 = function (_super) {
    tslib_1.__extends(DataObject$$1, _super);
    function DataObject$$1() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    tslib_1.__decorate([XmlCore.XmlChildElement({ parser: QualifyingProperties })], DataObject$$1.prototype, "QualifyingProperties", void 0);
    DataObject$$1 = tslib_1.__decorate([XmlCore.XmlElement({ localName: XmlDSigJs.XmlSignature.ElementNames.Object })], DataObject$$1);
    return DataObject$$1;
}(XmlDSigJs.DataObject);



var xadesXml = Object.freeze({
	Any: Any,
	AnyCollection: AnyCollection,
	OtherCertificate: OtherCertificate,
	OtherCertificateCollection: OtherCertificateCollection,
	EncapsulatedX509Certificate: EncapsulatedX509Certificate,
	EncapsulatedX509CertificateCollection: EncapsulatedX509CertificateCollection,
	CertificateValues: CertificateValues,
	CommitmentTypeQualifier: CommitmentTypeQualifier,
	CommitmentTypeQualifiers: CommitmentTypeQualifiers,
	ObjectReference: ObjectReference,
	ObjectReferenceCollection: ObjectReferenceCollection,
	CommitmentTypeIndication: CommitmentTypeIndication,
	CompleteCertificateRefs: CompleteCertificateRefs,
	OtherRef: OtherRef,
	OtherRefs: OtherRefs,
	ResponderID: ResponderID,
	OCSPIdentifier: OCSPIdentifier,
	OCSPRef: OCSPRef,
	OCSPRefs: OCSPRefs,
	CRLIdentifier: CRLIdentifier,
	CRLRef: CRLRef,
	CRLRefs: CRLRefs,
	CompleteRevocationRefs: CompleteRevocationRefs,
	CounterSignature: CounterSignature,
	DataObjectFormat: DataObjectFormat,
	XadesDateTime: XadesDateTime,
	EncapsulatedPKIData: EncapsulatedPKIData,
	Include: Include,
	ReferenceInfo: ReferenceInfo,
	ReferenceInfos: ReferenceInfos,
	EncapsulatedTimeStamp: EncapsulatedTimeStamp,
	EncapsulatedTimeStampCollection: EncapsulatedTimeStampCollection,
	XMLTimeStamp: XMLTimeStamp,
	XMLTimeStampCollection: XMLTimeStampCollection,
	GenericTimeStamp: GenericTimeStamp,
	Identifier: Identifier,
	DocumentationReference: DocumentationReference,
	DocumentationReferences: DocumentationReferences,
	ObjectIdentifier: ObjectIdentifier,
	OtherTimeStamp: OtherTimeStamp,
	QualifyingProperties: QualifyingProperties,
	QualifyingPropertiesReference: QualifyingPropertiesReference,
	OtherValue: OtherValue,
	OtherValues: OtherValues,
	EncapsulatedOCSPValue: EncapsulatedOCSPValue,
	OCSPValues: OCSPValues,
	EncapsulatedCRLValue: EncapsulatedCRLValue,
	CRLValues: CRLValues,
	RevocationValues: RevocationValues,
	SigPolicyId: SigPolicyId,
	SigPolicyHash: SigPolicyHash,
	SigPolicyQualifier: SigPolicyQualifier,
	Integer: Integer,
	IntegerList: IntegerList,
	NoticeReference: NoticeReference,
	SPUserNotice: SPUserNotice,
	SPURI: SPURI,
	SigPolicyQualifiers: SigPolicyQualifiers,
	SignaturePolicyId: SignaturePolicyId,
	SignaturePolicyIdentifier: SignaturePolicyIdentifier,
	SignatureProductionPlace: SignatureProductionPlace,
	IndividualDataObjectsTimeStamp: IndividualDataObjectsTimeStamp,
	IndividualDataObjectsTimeStampCollection: IndividualDataObjectsTimeStampCollection,
	AllDataObjectsTimeStamp: AllDataObjectsTimeStamp,
	DataObjectFormatCollection: DataObjectFormatCollection,
	CommitmentTypeIndicationCollection: CommitmentTypeIndicationCollection,
	AllDataObjectsTimeStampCollection: AllDataObjectsTimeStampCollection,
	SignedDataObjectProperties: SignedDataObjectProperties,
	SignedProperties: SignedProperties,
	SignedSignatureProperties: SignedSignatureProperties,
	ClaimedRole: ClaimedRole,
	ClaimedRoles: ClaimedRoles,
	CertifiedRole: CertifiedRole,
	CertifiedRoles: CertifiedRoles,
	SignerRole: SignerRole,
	DigestAlgAndValueType: DigestAlgAndValueType,
	IssuerSerial: IssuerSerial,
	Cert: Cert,
	CertIDList: CertIDList,
	SigningCertificate: SigningCertificate,
	UnsignedDataObjectProperty: UnsignedDataObjectProperty,
	UnsignedDataObjectProperties: UnsignedDataObjectProperties,
	UnsignedProperties: UnsignedProperties,
	SignatureTimeStamp: SignatureTimeStamp,
	SigAndRefsTimeStamp: SigAndRefsTimeStamp,
	RefsOnlyTimeStamp: RefsOnlyTimeStamp,
	ArchiveTimeStamp: ArchiveTimeStamp,
	AttributeCertificateRefs: AttributeCertificateRefs,
	AttributeRevocationRefs: AttributeRevocationRefs,
	AttrAuthoritiesCertValues: AttrAuthoritiesCertValues,
	AttributeRevocationValues: AttributeRevocationValues,
	UnsignedSignatureProperty: UnsignedSignatureProperty,
	UnsignedSignatureProperties: UnsignedSignatureProperties,
	XAdESTimeStamp: XAdESTimeStamp,
	DataObject: DataObject$1,
	XmlXades: XmlXades
});

var XADES_REFERENCE_TYPE = "http://uri.etsi.org/01903#SignedProperties";
var SignedXml$1 = function (_super) {
    tslib_1.__extends(SignedXml$$1, _super);
    function SignedXml$$1(node) {
        var _this = _super.call(this, node) || this;
        _this.properties = null;
        _this.CreateQualifyingProperties();
        return _this;
    }
    Object.defineProperty(SignedXml$$1.prototype, "Properties", {
        get: function get$$1() {
            return this.properties;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(SignedXml$$1.prototype, "SignedProperties", {
        get: function get$$1() {
            if (!this.Properties) {
                throw new XmlCore.XmlError(XmlCore.XE.XML_EXCEPTION, "Properties is empty");
            }
            return this.Properties.SignedProperties;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(SignedXml$$1.prototype, "UnsignedProperties", {
        get: function get$$1() {
            if (!this.Properties) {
                throw new XmlCore.XmlError(XmlCore.XE.XML_EXCEPTION, "Properties is empty");
            }
            return this.Properties.UnsignedProperties;
        },
        enumerable: true,
        configurable: true
    });

    SignedXml$$1.prototype.LoadXml = function (value, useContainer) {
        _super.prototype.LoadXml.call(this, value);
        var properties = null;
        this.XmlSignature.ObjectList.Some(function (item) {
            if (item.Element) {
                for (var i = 0; i < item.Element.childNodes.length; i++) {
                    var node = item.Element.childNodes.item(i);
                    if (node.nodeType === XmlCore.XmlNodeType.Element && node.localName === XmlXades.ElementNames.QualifyingProperties) {
                        properties = QualifyingProperties.LoadXml(node);
                        return true;
                    }
                }
            }
            return false;
        });
        this.properties = properties;
    };
    SignedXml$$1.prototype.Sign = function (algorithm, key, data, options) {
        return (_a = _super.prototype.Sign).apply.call(_a, this, arguments);
        var _a;
    };

    SignedXml$$1.prototype.CreateQualifyingProperties = function () {
        if (this.Properties) {
            throw new XmlCore.XmlError(XmlCore.XE.XML_EXCEPTION, "Cannot create QualifyingProperties cause current signature has got one. You must create CounterSignature");
        }
        var rnd = XmlDSigJs.Application.crypto.getRandomValues(new Uint8Array(6));
        var id = XmlCore.Convert.ToHex(rnd);
        this.XmlSignature.Id = "id-" + id;
        var dataObject = new DataObject$1();
        dataObject.QualifyingProperties.Target = "#" + this.XmlSignature.Id;
        dataObject.QualifyingProperties.SignedProperties.Id = "xades-" + this.XmlSignature.Id;
        this.properties = dataObject.QualifyingProperties;
        this.XmlSignature.ObjectList.Add(dataObject);
    };
    SignedXml$$1.prototype.ApplySignOptions = function (signature, algorithm, key, options) {
        return tslib_1.__awaiter(this, void 0, void 0, function () {
            var sigProps, signingAlg, xadesRefHash, xadesRef;
            return tslib_1.__generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        return [4, _super.prototype.ApplySignOptions.call(this, signature, algorithm, key, options)];
                    case 1:
                        _a.sent();
                        if (!this.Properties) return [3, 4];
                        sigProps = this.Properties.SignedProperties.SignedSignatureProperties;
                        sigProps.SigningTime.Value = new Date();
                        signingAlg = XmlCore.assign({}, algorithm, key.algorithm);
                        xadesRefHash = signingAlg.hash;
                        xadesRef = new XmlDSigJs.Reference();
                        xadesRef.Type = XADES_REFERENCE_TYPE;
                        xadesRef.Uri = "#" + this.Properties.SignedProperties.Id;
                        xadesRef.DigestMethod.Algorithm = XmlDSigJs.CryptoConfig.GetHashAlgorithm(xadesRefHash).namespaceURI;
                        signature.SignedInfo.References.Add(xadesRef);
                        return [4, this.ApplySigningCertificate(options.signingCertificate)];
                    case 2:
                        _a.sent();
                        return [4, this.ApplySignaturePolicyIdentifier(options.policy)];
                    case 3:
                        _a.sent();
                        this.ApplySignatureProductionPlace(options.productionPlace);
                        this.ApplySignerRoles(options.signerRole);
                        _a.label = 4;
                    case 4:
                        return [2];
                }
            });
        });
    };
    SignedXml$$1.prototype.ApplySigningCertificate = function (base64string) {
        return tslib_1.__awaiter(this, void 0, void 0, function () {
            var raw, cert, ssp, signingCertificate, alg, _a, _b;
            return tslib_1.__generator(this, function (_c) {
                switch (_c.label) {
                    case 0:
                        if (!(this.Properties && base64string)) return [3, 2];
                        raw = XmlCore.Convert.FromBase64(base64string);
                        cert = new XmlDSigJs.X509Certificate(raw);
                        ssp = this.Properties.SignedProperties.SignedSignatureProperties;
                        if (ssp.SigningCertificate.Count) {
                            throw new XmlCore.XmlError(XmlCore.XE.XML_EXCEPTION, "Signature can contain only one SigningCertificate");
                        }
                        signingCertificate = new Cert();
                        signingCertificate.IssuerSerial.X509IssuerName = cert.Issuer;
                        signingCertificate.IssuerSerial.X509SerialNumber = cert.SerialNumber;
                        alg = XmlDSigJs.CryptoConfig.GetHashAlgorithm("SHA-256");
                        signingCertificate.CertDigest.DigestMethod.Algorithm = alg.namespaceURI;
                        _a = signingCertificate.CertDigest;
                        _b = Uint8Array.bind;
                        return [4, cert.Thumbprint(alg.algorithm.name)];
                    case 1:
                        _a.DigestValue = new (_b.apply(Uint8Array, [void 0, _c.sent()]))();
                        this.Properties.SignedProperties.SignedSignatureProperties.SigningCertificate.Add(signingCertificate);
                        _c.label = 2;
                    case 2:
                        return [2];
                }
            });
        });
    };
    SignedXml$$1.prototype.ApplySignaturePolicyIdentifier = function (options) {
        return tslib_1.__awaiter(this, void 0, void 0, function () {
            var _this = this;
            var ssp, policyId_1, digestAlgorithm, identifierDoc, identifierContent, c14n, _a;
            return tslib_1.__generator(this, function (_b) {
                switch (_b.label) {
                    case 0:
                        if (!this.Properties) return [3, 3];
                        ssp = this.Properties.SignedProperties.SignedSignatureProperties;
                        if (!(options && (typeof options === "undefined" ? "undefined" : _typeof(options)) === "object")) return [3, 2];
                        policyId_1 = new SignaturePolicyId();
                        policyId_1.SigPolicyId = new SigPolicyId();
                        policyId_1.SigPolicyId.Identifier = new Identifier();
                        policyId_1.SigPolicyId.Identifier.Qualifier = options.identifier.qualifier;
                        policyId_1.SigPolicyId.Identifier.Value = options.identifier.value;
                        if (options.identifier.description) {
                            policyId_1.SigPolicyId.Description = options.identifier.description;
                        }
                        if (options.identifier.references) {
                            policyId_1.SigPolicyId.DocumentationReferences = new DocumentationReferences();
                            options.identifier.references.forEach(function (referenceValue) {
                                var reference = new DocumentationReference();
                                reference.Uri = referenceValue;
                                policyId_1.SigPolicyId.DocumentationReferences.Add(reference);
                            });
                        }
                        if (options.transforms && options.transforms.length) {
                            policyId_1.Transforms = new XmlDSigJs.Transforms();
                            options.transforms.forEach(function (transform) {
                                policyId_1.Transforms.Add(_this.ResolveTransform(transform));
                            });
                        }
                        policyId_1.SigPolicyHash = new SigPolicyHash();
                        policyId_1.SigPolicyHash.DigestMethod = new XmlDSigJs.DigestMethod();
                        digestAlgorithm = XmlDSigJs.CryptoConfig.GetHashAlgorithm(options.hash);
                        policyId_1.SigPolicyHash.DigestMethod.Algorithm = digestAlgorithm.namespaceURI;
                        identifierDoc = policyId_1.SigPolicyId.Identifier.GetXml().cloneNode(true);
                        this.CopyNamespaces(identifierDoc, identifierDoc, true);
                        this.InjectNamespaces(this.GetSignatureNamespaces(), identifierDoc, true);
                        identifierContent = null;
                        if (policyId_1.Transforms && policyId_1.Transforms.Count) {
                            identifierContent = this.ApplyTransforms(policyId_1.Transforms, identifierDoc);
                        } else {
                            c14n = new XmlDSigJs.XmlDsigC14NTransform();
                            c14n.LoadInnerXml(identifierDoc);
                            identifierContent = c14n.GetOutput();
                        }
                        _a = policyId_1.SigPolicyHash;
                        return [4, digestAlgorithm.Digest(identifierContent)];
                    case 1:
                        _a.DigestValue = _b.sent();
                        if (options.qualifiers) {
                            policyId_1.SigPolicyQualifiers = new SigPolicyQualifiers();
                            options.qualifiers.forEach(function (qualifierValue) {
                                var container = new SigPolicyQualifier();
                                if (typeof qualifierValue === "string") {
                                    var qualifier = new SPURI();
                                    qualifier.Value = qualifierValue;
                                    container.Add(qualifier);
                                } else {
                                    var qualifier_1 = new SPUserNotice();
                                    if (qualifierValue.explicitText) {
                                        qualifier_1.ExplicitText = qualifierValue.explicitText;
                                    }
                                    if (qualifierValue.noticeRef) {
                                        qualifier_1.NoticeRef = new NoticeReference();
                                        qualifier_1.NoticeRef.Organization = qualifierValue.noticeRef.organization;
                                        qualifier_1.NoticeRef.NoticeNumbers = new IntegerList();
                                        if (qualifierValue.noticeRef.noticeNumbers) {
                                            qualifierValue.noticeRef.noticeNumbers.forEach(function (numberValue) {
                                                var noticeNumber = new Integer();
                                                noticeNumber.Value = numberValue;
                                                qualifier_1.NoticeRef.NoticeNumbers.Add(noticeNumber);
                                            });
                                        }
                                    }
                                    container.Add(qualifier_1);
                                }
                                policyId_1.SigPolicyQualifiers.Add(container);
                            });
                        }
                        ssp.SignaturePolicyIdentifier.SignaturePolicyId = policyId_1;
                        ssp.SignaturePolicyIdentifier.SignaturePolicyImplied = false;
                        return [3, 3];
                    case 2:
                        ssp.SignaturePolicyIdentifier.SignaturePolicyImplied = true;
                        _b.label = 3;
                    case 3:
                        return [2];
                }
            });
        });
    };
    SignedXml$$1.prototype.ApplySignatureProductionPlace = function (options) {
        if (this.Properties && options) {
            var ssp = this.Properties.SignedProperties.SignedSignatureProperties;
            if (options.city) {
                ssp.SignatureProductionPlace.City = options.city;
            }
            if (options.code) {
                ssp.SignatureProductionPlace.PostalCode = options.code;
            }
            if (options.country) {
                ssp.SignatureProductionPlace.CountryName = options.country;
            }
            if (options.state) {
                ssp.SignatureProductionPlace.StateOrProvince = options.state;
            }
        }
    };
    SignedXml$$1.prototype.ApplySignerRoles = function (options) {
        if (this.Properties && options) {
            var ssp_1 = this.Properties.SignedProperties.SignedSignatureProperties;
            if (options.claimed) {
                options.claimed.forEach(function (role) {
                    var claimedRole = new ClaimedRole();
                    claimedRole.Value = role;
                    ssp_1.SignerRole.ClaimedRoles.Add(claimedRole);
                });
            }
            if (options.certified) {
                options.certified.forEach(function (role) {
                    var certifiedRole = new CertifiedRole();
                    certifiedRole.Encoding = "der";
                    certifiedRole.Value = XmlCore.Convert.FromBase64(role);
                    ssp_1.SignerRole.CertifiedRoles.Add(certifiedRole);
                });
            }
        }
    };
    SignedXml$$1.prototype.VerifySigningCertificate = function () {
        return tslib_1.__awaiter(this, void 0, void 0, function () {
            var x509, ssp, alg, signingCertificate, b64CertDigest, keyInfos, i, item, certs, j, cert, hash, _a, b64Hash;
            return tslib_1.__generator(this, function (_b) {
                switch (_b.label) {
                    case 0:
                        x509 = null;
                        if (!(this.XmlSignature && this.Properties)) return [3, 7];
                        ssp = this.Properties.SignedProperties.SignedSignatureProperties;
                        if (ssp.SigningCertificate.Count !== 1) {
                            throw new XmlCore.XmlError(XmlCore.XE.XML_EXCEPTION, "Signature has got wrong amount of SigningCertificate, MUST be one");
                        }
                        alg = XmlDSigJs.CryptoConfig.GetHashAlgorithm("SHA-256");
                        signingCertificate = ssp.SigningCertificate.Item(0);
                        b64CertDigest = XmlCore.Convert.ToBase64(signingCertificate.CertDigest.DigestValue);
                        keyInfos = this.XmlSignature.KeyInfo;
                        i = 0;
                        _b.label = 1;
                    case 1:
                        if (!(i < keyInfos.Count, !x509)) return [3, 6];
                        item = keyInfos.Item(i);
                        if (!(item instanceof XmlDSigJs.KeyInfoX509Data)) return [3, 5];
                        certs = item.Certificates;
                        j = 0;
                        _b.label = 2;
                    case 2:
                        if (!(j < certs.length, !x509)) return [3, 5];
                        cert = certs[j];
                        if (!cert) {
                            return [3, 4];
                        }
                        _a = Uint8Array.bind;
                        return [4, cert.Thumbprint(alg.algorithm)];
                    case 3:
                        hash = new (_a.apply(Uint8Array, [void 0, _b.sent()]))();
                        b64Hash = XmlCore.Convert.ToBase64(hash);
                        if (b64Hash === b64CertDigest) {
                            x509 = cert;
                        }
                        _b.label = 4;
                    case 4:
                        j++;
                        return [3, 2];
                    case 5:
                        i++;
                        return [3, 1];
                    case 6:
                        if (!(x509 && x509.Issuer === signingCertificate.IssuerSerial.X509IssuerName && x509.SerialNumber === signingCertificate.IssuerSerial.X509SerialNumber)) {
                            throw new XmlCore.XmlError(XmlCore.XE.XML_EXCEPTION, "SigningCertificate not found");
                        }
                        _b.label = 7;
                    case 7:
                        return [2, x509];
                }
            });
        });
    };
    return SignedXml$$1;
}(XmlDSigJs.SignedXml);

var xml = xadesXml;

exports.xml = xml;
exports.Application = XmlDSigJs.Application;
exports.Select = XmlCore.Select;
exports.Parse = XmlCore.Parse;
exports.Convert = XmlCore.Convert;
exports.SignedXml = SignedXml$1;

Object.defineProperty(exports, '__esModule', { value: true });

})));
