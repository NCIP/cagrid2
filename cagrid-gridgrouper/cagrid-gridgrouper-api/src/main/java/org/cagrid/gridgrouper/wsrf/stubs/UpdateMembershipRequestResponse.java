
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.MembershipRequestDescriptor;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipRequestDescriptor"/>
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
    "membershipRequestDescriptor"
})
@XmlRootElement(name = "UpdateMembershipRequestResponse")
public class UpdateMembershipRequestResponse
    implements Serializable
{

    @XmlElement(name = "MembershipRequestDescriptor", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
    protected MembershipRequestDescriptor membershipRequestDescriptor;

    /**
     * Gets the value of the membershipRequestDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.MembershipRequestDescriptor }
     *     
     */
    public MembershipRequestDescriptor getMembershipRequestDescriptor() {
        return membershipRequestDescriptor;
    }

    /**
     * Sets the value of the membershipRequestDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.model.MembershipRequestDescriptor }
     *     
     */
    public void setMembershipRequestDescriptor(MembershipRequestDescriptor value) {
        this.membershipRequestDescriptor = value;
    }

}
