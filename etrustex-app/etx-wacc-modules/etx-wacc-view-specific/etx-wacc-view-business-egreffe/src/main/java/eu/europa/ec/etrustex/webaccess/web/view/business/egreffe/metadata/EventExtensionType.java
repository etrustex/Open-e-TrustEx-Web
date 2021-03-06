//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 				Extension for an event.
 * 			
 * 
 * <p>Java class for EventExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventExtensionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://publications.europa.eu/resource/core-metadata}t_event_extension_base">
 *       &lt;sequence>
 *         &lt;element name="agent_person" type="{urn:eu:ec:cm:common:extensions:v11}AgentPersonNameType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventExtensionType", namespace = "urn:eu:ec:cm:common:extensions:v11", propOrder = {
    "agentPerson"
})
public class EventExtensionType
    extends TEventExtensionBase
{

    @XmlElement(name = "agent_person")
    protected List<AgentPersonNameType> agentPerson;

    /**
     * Gets the value of the agentPerson property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agentPerson property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgentPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AgentPersonNameType }
     * 
     * 
     */
    public List<AgentPersonNameType> getAgentPerson() {
        if (agentPerson == null) {
            agentPerson = new ArrayList<>();
        }
        return this.agentPerson;
    }

}
