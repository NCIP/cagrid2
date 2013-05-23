
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GroupDelegationPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroupDelegationPolicy">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gaards.cagrid.org/cds}DelegationPolicy">
 *       &lt;sequence>
 *         &lt;element name="GridGrouperServiceURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupDelegationPolicy", propOrder = {
    "gridGrouperServiceURL",
    "groupName"
})
public class GroupDelegationPolicy
    extends DelegationPolicy
    implements Serializable
{

    @XmlElement(name = "GridGrouperServiceURL", required = true)
    protected String gridGrouperServiceURL;
    @XmlElement(required = true)
    protected String groupName;

    /**
     * Gets the value of the gridGrouperServiceURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridGrouperServiceURL() {
        return gridGrouperServiceURL;
    }

    /**
     * Sets the value of the gridGrouperServiceURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridGrouperServiceURL(String value) {
        this.gridGrouperServiceURL = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

}
