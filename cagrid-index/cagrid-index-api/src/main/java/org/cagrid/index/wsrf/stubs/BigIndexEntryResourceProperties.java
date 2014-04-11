
package org.cagrid.index.wsrf.stubs;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter1;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;


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
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd}TerminationTime"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd}MemberEPR"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd}Content"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd}CurrentTime"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd}ServiceGroupEPR"/>
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
    "terminationTime",
    "memberEPR",
    "content",
    "currentTime",
    "serviceGroupEPR"
})
@XmlRootElement(name = "BigIndexEntryResourceProperties")
public class BigIndexEntryResourceProperties
    implements Serializable
{

    @XmlElement(name = "TerminationTime", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd", required = true, type = String.class, nillable = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar terminationTime;
    @XmlElement(name = "MemberEPR", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd", required = true)
    protected EndpointReferenceType memberEPR;
    @XmlElement(name = "Content", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd", required = true)
    protected Object content;
    @XmlElement(name = "CurrentTime", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar currentTime;
    @XmlElement(name = "ServiceGroupEPR", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd", required = true)
    protected EndpointReferenceType serviceGroupEPR;

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
     * Gets the value of the memberEPR property.
     * 
     * @return
     *     possible object is
     *     {@link EndpointReferenceType }
     *     
     */
    public EndpointReferenceType getMemberEPR() {
        return memberEPR;
    }

    /**
     * Sets the value of the memberEPR property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointReferenceType }
     *     
     */
    public void setMemberEPR(EndpointReferenceType value) {
        this.memberEPR = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setContent(Object value) {
        this.content = value;
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
     * Gets the value of the serviceGroupEPR property.
     * 
     * @return
     *     possible object is
     *     {@link EndpointReferenceType }
     *     
     */
    public EndpointReferenceType getServiceGroupEPR() {
        return serviceGroupEPR;
    }

    /**
     * Sets the value of the serviceGroupEPR property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointReferenceType }
     *     
     */
    public void setServiceGroupEPR(EndpointReferenceType value) {
        this.serviceGroupEPR = value;
    }

}
