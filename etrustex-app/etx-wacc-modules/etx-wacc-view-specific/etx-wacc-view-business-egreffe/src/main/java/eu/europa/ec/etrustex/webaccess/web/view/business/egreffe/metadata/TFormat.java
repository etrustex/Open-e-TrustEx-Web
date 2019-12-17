//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_format.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="t_format">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DOC"/>
 *     &lt;enumeration value="DOCX"/>
 *     &lt;enumeration value="PDF"/>
 *     &lt;enumeration value="PDFA1A"/>
 *     &lt;enumeration value="PDFA1B"/>
 *     &lt;enumeration value="PDFX"/>
 *     &lt;enumeration value="PPSX"/>
 *     &lt;enumeration value="PPT"/>
 *     &lt;enumeration value="PPTX"/>
 *     &lt;enumeration value="XLS"/>
 *     &lt;enumeration value="XLSX"/>
 *     &lt;enumeration value="XML"/>
 *     &lt;enumeration value="ZIP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "t_format", namespace = "http://publications.europa.eu/resource/formats/")
@XmlEnum
public enum TFormat {

    DOC("DOC"),
    DOCX("DOCX"),
    PDF("PDF"),
    @XmlEnumValue("PDFA1A")
    PDFA_1_A("PDFA1A"),
    @XmlEnumValue("PDFA1B")
    PDFA_1_B("PDFA1B"),
    PDFX("PDFX"),
    PPSX("PPSX"),
    PPT("PPT"),
    PPTX("PPTX"),
    XLS("XLS"),
    XLSX("XLSX"),
    XML("XML"),
    ZIP("ZIP");
    private final String value;

    TFormat(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TFormat fromValue(String v) {
        for (TFormat c: TFormat.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
