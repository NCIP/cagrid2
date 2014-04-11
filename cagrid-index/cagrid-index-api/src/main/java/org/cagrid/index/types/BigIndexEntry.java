
package org.cagrid.index.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;


/**
 * 
 *             This type defines a holder for persisting a service group entry
 *         
 * 
 * <p>Java class for BigIndexEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BigIndexEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd}Entry"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BigIndexEntry", propOrder = {
    "entry"
})
public class BigIndexEntry
    implements Serializable
{

    @XmlElement(name = "Entry", namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ServiceGroup-1.2-draft-01.xsd", required = true)
    protected EntryType entry;

    /**
     * Gets the value of the entry property.
     * 
     * @return
     *     possible object is
     *     {@link EntryType }
     *     
     */
    public EntryType getEntry() {
        return entry;
    }

    /**
     * Sets the value of the entry property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntryType }
     *     
     */
    public void setEntry(EntryType value) {
        this.entry = value;
    }

}
