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
 * <p>Java class for t_interinstitutionalprocedure.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="t_interinstitutionalprocedure">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACC"/>
 *     &lt;enumeration value="ACI"/>
 *     &lt;enumeration value="APP"/>
 *     &lt;enumeration value="AVC"/>
 *     &lt;enumeration value="BUD"/>
 *     &lt;enumeration value="CNB"/>
 *     &lt;enumeration value="CNC"/>
 *     &lt;enumeration value="CNS"/>
 *     &lt;enumeration value="COD"/>
 *     &lt;enumeration value="COS"/>
 *     &lt;enumeration value="DCE"/>
 *     &lt;enumeration value="DEC"/>
 *     &lt;enumeration value="IMM"/>
 *     &lt;enumeration value="INI"/>
 *     &lt;enumeration value="INS"/>
 *     &lt;enumeration value="NLE"/>
 *     &lt;enumeration value="OLP"/>
 *     &lt;enumeration value="ORP"/>
 *     &lt;enumeration value="PRT"/>
 *     &lt;enumeration value="REG"/>
 *     &lt;enumeration value="RPS"/>
 *     &lt;enumeration value="RSO"/>
 *     &lt;enumeration value="RSP"/>
 *     &lt;enumeration value="SLP"/>
 *     &lt;enumeration value="SRP"/>
 *     &lt;enumeration value="SYN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "t_interinstitutionalprocedure", namespace = "http://publications.europa.eu/resource/interinstitutionalprocedures/")
@XmlEnum
public enum TInterinstitutionalprocedure {

    ACC,
    ACI,
    APP,
    AVC,
    BUD,
    CNB,
    CNC,
    CNS,
    COD,
    COS,
    DCE,
    DEC,
    IMM,
    INI,
    INS,
    NLE,
    OLP,
    ORP,
    PRT,
    REG,
    RPS,
    RSO,
    RSP,
    SLP,
    SRP,
    SYN;

    public String value() {
        return name();
    }

    public static TInterinstitutionalprocedure fromValue(String v) {
        return valueOf(v);
    }

}
