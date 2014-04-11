
package org.cagrid.index.aggregator.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.index.metrics.types.Accumulator;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.MembershipContentRule;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd}MembershipContentRule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd}Entry" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://mds.globus.org/aggregator/types}RegistrationCount"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "membershipContentRule",
    "entry",
    "registrationCount"
})
@XmlRootElement(name = "AggregatorServiceGroupRP")
public class AggregatorServiceGroupRP
    implements Serializable
{

    @XmlElement(name = "MembershipContentRule", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd")
    protected List<MembershipContentRule> membershipContentRule;
    @XmlElement(name = "Entry", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd")
    protected List<EntryType> entry;
    @XmlElement(name = "RegistrationCount", required = true)
    protected Accumulator registrationCount;

    /**
     * Gets the value of the membershipContentRule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the membershipContentRule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMembershipContentRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MembershipContentRule }
     * 
     * 
     */
    public List<MembershipContentRule> getMembershipContentRule() {
        if (membershipContentRule == null) {
            membershipContentRule = new ArrayList<MembershipContentRule>();
        }
        return this.membershipContentRule;
    }

    /**
     * Gets the value of the entry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntryType }
     * 
     * 
     */
    public List<EntryType> getEntry() {
        if (entry == null) {
            entry = new ArrayList<EntryType>();
        }
        return this.entry;
    }

    /**
     * Gets the value of the registrationCount property.
     * 
     * @return
     *     possible object is
     *     {@link Accumulator }
     *     
     */
    public Accumulator getRegistrationCount() {
        return registrationCount;
    }

    /**
     * Sets the value of the registrationCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Accumulator }
     *     
     */
    public void setRegistrationCount(Accumulator value) {
        this.registrationCount = value;
    }

}
