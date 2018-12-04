//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_resource-type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="t_resource-type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACKNOWLEDGE_RECP"/>
 *     &lt;enumeration value="AMEND_PROP"/>
 *     &lt;enumeration value="AMEND_PROP_DEC"/>
 *     &lt;enumeration value="AMEND_PROP_DIR"/>
 *     &lt;enumeration value="AMEND_PROP_REG"/>
 *     &lt;enumeration value="ANNEX"/>
 *     &lt;enumeration value="ANNEX_SUM"/>
 *     &lt;enumeration value="COMMUNIC"/>
 *     &lt;enumeration value="COMMUNIC_POSIT"/>
 *     &lt;enumeration value="CORRIGENDUM"/>
 *     &lt;enumeration value="DATPRO"/>
 *     &lt;enumeration value="DEC"/>
 *     &lt;enumeration value="DEC_DEL"/>
 *     &lt;enumeration value="DEC_DRAFT"/>
 *     &lt;enumeration value="DEC_IMPL"/>
 *     &lt;enumeration value="DIR"/>
 *     &lt;enumeration value="DIR_DEL"/>
 *     &lt;enumeration value="DIR_DRAFT"/>
 *     &lt;enumeration value="DIR_IMPL"/>
 *     &lt;enumeration value="IMPACT_ASSESS"/>
 *     &lt;enumeration value="IMPACT_ASSESS_SUM"/>
 *     &lt;enumeration value="JOINT_COMMUNIC"/>
 *     &lt;enumeration value="JOINT_IMPACT_ASSESS"/>
 *     &lt;enumeration value="JOINT_IMPACT_ASSESS_SUM"/>
 *     &lt;enumeration value="JOINT_PAPER_GREEN"/>
 *     &lt;enumeration value="JOINT_PAPER_WHITE"/>
 *     &lt;enumeration value="JOINT_PROP_DEC"/>
 *     &lt;enumeration value="JOINT_PROP_DIR"/>
 *     &lt;enumeration value="JOINT_PROP_REG"/>
 *     &lt;enumeration value="JOINT_REPORT"/>
 *     &lt;enumeration value="JOINT_SWD"/>
 *     &lt;enumeration value="NOTIF"/>
 *     &lt;enumeration value="OPIN"/>
 *     &lt;enumeration value="OPIN_AMEND_EP"/>
 *     &lt;enumeration value="PAPER_GREEN"/>
 *     &lt;enumeration value="PAPER_WHITE"/>
 *     &lt;enumeration value="PROP_DEC"/>
 *     &lt;enumeration value="PROP_DEC_IMPL"/>
 *     &lt;enumeration value="PROP_DIR"/>
 *     &lt;enumeration value="PROP_DIR_IMPL"/>
 *     &lt;enumeration value="PROP_RECO"/>
 *     &lt;enumeration value="PROP_REG"/>
 *     &lt;enumeration value="PROP_REG_IMPL"/>
 *     &lt;enumeration value="RECO"/>
 *     &lt;enumeration value="RECO_DEC"/>
 *     &lt;enumeration value="RECO_OPIN"/>
 *     &lt;enumeration value="RECO_RECO"/>
 *     &lt;enumeration value="REFERRAL_LET"/>
 *     &lt;enumeration value="REG"/>
 *     &lt;enumeration value="REG_DEL"/>
 *     &lt;enumeration value="REG_IMPL"/>
 *     &lt;enumeration value="REPLY_OPIN_NP"/>
 *     &lt;enumeration value="REPORT"/>
 *     &lt;enumeration value="REPORT_AAR"/>
 *     &lt;enumeration value="REPORT_ANNUAL_DAS"/>
 *     &lt;enumeration value="REPORT_ANNUAL_EDF"/>
 *     &lt;enumeration value="RES_LEGIS"/>
 *     &lt;enumeration value="SWD"/>
 *     &lt;enumeration value="VIEWPOINT"/>
 *     &lt;enumeration value="WORK_DOC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "t_resource-type", namespace = "http://publications.europa.eu/resource/resourcetypes/")
@XmlEnum
public enum TResourceType {

    ACKNOWLEDGE_RECP,
    AMEND_PROP,
    AMEND_PROP_DEC,
    AMEND_PROP_DIR,
    AMEND_PROP_REG,
    ANNEX,
    ANNEX_SUM,
    COMMUNIC,
    COMMUNIC_POSIT,
    CORRIGENDUM,
    DATPRO,
    DEC,
    DEC_DEL,
    DEC_DRAFT,
    DEC_IMPL,
    DIR,
    DIR_DEL,
    DIR_DRAFT,
    DIR_IMPL,
    IMPACT_ASSESS,
    IMPACT_ASSESS_SUM,
    JOINT_COMMUNIC,
    JOINT_IMPACT_ASSESS,
    JOINT_IMPACT_ASSESS_SUM,
    JOINT_PAPER_GREEN,
    JOINT_PAPER_WHITE,
    JOINT_PROP_DEC,
    JOINT_PROP_DIR,
    JOINT_PROP_REG,
    JOINT_REPORT,
    JOINT_SWD,
    NOTIF,
    OPIN,
    OPIN_AMEND_EP,
    PAPER_GREEN,
    PAPER_WHITE,
    PROP_DEC,
    PROP_DEC_IMPL,
    PROP_DIR,
    PROP_DIR_IMPL,
    PROP_RECO,
    PROP_REG,
    PROP_REG_IMPL,
    RECO,
    RECO_DEC,
    RECO_OPIN,
    RECO_RECO,
    REFERRAL_LET,
    REG,
    REG_DEL,
    REG_IMPL,
    REPLY_OPIN_NP,
    REPORT,
    REPORT_AAR,
    REPORT_ANNUAL_DAS,
    REPORT_ANNUAL_EDF,
    RES_LEGIS,
    SWD,
    VIEWPOINT,
    WORK_DOC;

    public String value() {
        return name();
    }

    public static TResourceType fromValue(String v) {
        return valueOf(v);
    }

}
