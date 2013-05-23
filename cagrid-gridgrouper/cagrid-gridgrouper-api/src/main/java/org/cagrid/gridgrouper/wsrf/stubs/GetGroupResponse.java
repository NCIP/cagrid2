
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.GroupDescriptor;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupDescriptor"/>
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
    "groupDescriptor"
})
@XmlRootElement(name = "GetGroupResponse")
public class GetGroupResponse
    implements Serializable
{

    @XmlElement(name = "GroupDescriptor", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
    protected GroupDescriptor groupDescriptor;

    /**
     * Gets the value of the groupDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.GroupDescriptor }
     *     
     */
    public GroupDescriptor getGroupDescriptor() {
        return groupDescriptor;
    }

    /**
     * Sets the value of the groupDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.model.GroupDescriptor }
     *     
     */
    public void setGroupDescriptor(GroupDescriptor value) {
        this.groupDescriptor = value;
    }

}
