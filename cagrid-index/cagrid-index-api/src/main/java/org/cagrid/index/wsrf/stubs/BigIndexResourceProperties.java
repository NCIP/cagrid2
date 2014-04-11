
package org.cagrid.index.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.cagrid.index.metrics.types.Accumulator;
import org.oasis_open.docs.wsn._2004._06.wsn_ws_basenotification_1_2_draft_01.TopicExpressionType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.MembershipContentRule;
import org.w3._2001.xmlschema.Adapter1;


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
 *         &lt;element ref="{http://docs.oasis-open.org/wsn/2004/06/wsn-WS-BaseNotification-1.2-draft-01.xsd}Topic" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsn/2004/06/wsn-WS-BaseNotification-1.2-draft-01.xsd}TopicExpressionDialects" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd}Entry" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd}TerminationTime"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd}MembershipContentRule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://mds.globus.org/aggregator/types}RegistrationCount"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd}CurrentTime"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsn/2004/06/wsn-WS-BaseNotification-1.2-draft-01.xsd}FixedTopicSet"/>
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
    "topic",
    "topicExpressionDialects",
    "entry",
    "terminationTime",
    "membershipContentRule",
    "registrationCount",
    "currentTime",
    "fixedTopicSet"
})
@XmlRootElement(name = "BigIndexResourceProperties")
public class BigIndexResourceProperties
    implements Serializable
{

    @XmlElement(name = "Topic", namespace = "http://docs.oasis-open.org/wsn/2004/06/wsn-WS-BaseNotification-1.2-draft-01.xsd", required = true)
    protected List<TopicExpressionType> topic;
    @XmlElement(name = "TopicExpressionDialects", namespace = "http://docs.oasis-open.org/wsn/2004/06/wsn-WS-BaseNotification-1.2-draft-01.xsd", required = true)
    @XmlSchemaType(name = "anyURI")
    protected List<String> topicExpressionDialects;
    @XmlElement(name = "Entry", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd")
    protected List<EntryType> entry;
    @XmlElement(name = "TerminationTime", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd", required = true, type = String.class, nillable = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar terminationTime;
    @XmlElement(name = "MembershipContentRule", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd")
    protected List<MembershipContentRule> membershipContentRule;
    @XmlElement(name = "RegistrationCount", namespace = "http://mds.globus.org/aggregator/types", required = true)
    protected Accumulator registrationCount;
    @XmlElement(name = "CurrentTime", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar currentTime;
    @XmlElement(name = "FixedTopicSet", namespace = "http://docs.oasis-open.org/wsn/2004/06/wsn-WS-BaseNotification-1.2-draft-01.xsd")
    protected boolean fixedTopicSet;

    /**
     * Gets the value of the topic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopicExpressionType }
     * 
     * 
     */
    public List<TopicExpressionType> getTopic() {
        if (topic == null) {
            topic = new ArrayList<TopicExpressionType>();
        }
        return this.topic;
    }

    /**
     * Gets the value of the topicExpressionDialects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topicExpressionDialects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopicExpressionDialects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTopicExpressionDialects() {
        if (topicExpressionDialects == null) {
            topicExpressionDialects = new ArrayList<String>();
        }
        return this.topicExpressionDialects;
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
     * Gets the value of the terminationTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getTerminationTime() {
        return terminationTime;
    }

    /**
     * Sets the value of the terminationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminationTime(Calendar value) {
        this.terminationTime = value;
    }

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

    /**
     * Gets the value of the currentTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getCurrentTime() {
        return currentTime;
    }

    /**
     * Sets the value of the currentTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentTime(Calendar value) {
        this.currentTime = value;
    }

    /**
     * Gets the value of the fixedTopicSet property.
     * 
     */
    public boolean isFixedTopicSet() {
        return fixedTopicSet;
    }

    /**
     * Sets the value of the fixedTopicSet property.
     * 
     */
    public void setFixedTopicSet(boolean value) {
        this.fixedTopicSet = value;
    }

}
