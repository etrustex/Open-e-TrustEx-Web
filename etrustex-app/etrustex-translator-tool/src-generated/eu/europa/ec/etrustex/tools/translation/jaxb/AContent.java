//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.05.23 at 11:21:10 AM CEST 
//


package eu.europa.ec.etrustex.tools.translation.jaxb;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 *       a elements use "Inline" excluding a
 *       (simplified to use inline.basic)
 *       
 * 
 * <p>Java class for a.content complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="a.content">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;group ref="{}inline.basic"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "a.content", propOrder = {
    "content"
})
@XmlSeeAlso({
    A.class
})
public class AContent {

    @XmlElementRefs({
        @XmlElementRef(name = "strong", type = Strong.class),
        @XmlElementRef(name = "tt", type = Tt.class),
        @XmlElementRef(name = "strike", type = Strike.class),
        @XmlElementRef(name = "span", type = Span.class),
        @XmlElementRef(name = "b", type = B.class),
        @XmlElementRef(name = "i", type = I.class),
        @XmlElementRef(name = "em", type = Em.class),
        @XmlElementRef(name = "u", type = U.class),
        @XmlElementRef(name = "br", type = Br.class),
        @XmlElementRef(name = "sub", type = Sub.class),
        @XmlElementRef(name = "s", type = S.class),
        @XmlElementRef(name = "sup", type = Sup.class)
    })
    @XmlMixed
    protected List<Object> content;

    /**
     * 
     *       a elements use "Inline" excluding a
     *       (simplified to use inline.basic)
     *       Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tt }
     * {@link String }
     * {@link B }
     * {@link I }
     * {@link Em }
     * {@link U }
     * {@link Sup }
     * {@link Strong }
     * {@link Strike }
     * {@link Span }
     * {@link Br }
     * {@link Sub }
     * {@link S }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

}
