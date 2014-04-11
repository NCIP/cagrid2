
package org.cagrid.index.aggregator.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PairedKeyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PairedKeyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GroupKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EntryKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PairedKeyType", propOrder = {
    "groupKey",
    "entryKey"
})
public class PairedKeyType
    implements Serializable
{

    @XmlElement(name = "GroupKey", required = true)
    protected String groupKey;
    @XmlElement(name = "EntryKey", required = true)
    protected String entryKey;

    /**
     * Gets the value of the groupKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupKey() {
        return groupKey;
    }

    /**
     * Sets the value of the groupKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupKey(String value) {
        this.groupKey = value;
    }

    /**
     * Gets the value of the entryKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryKey() {
        return entryKey;
    }

    /**
     * Sets the value of the entryKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryKey(String value) {
        this.entryKey = value;
    }

}
