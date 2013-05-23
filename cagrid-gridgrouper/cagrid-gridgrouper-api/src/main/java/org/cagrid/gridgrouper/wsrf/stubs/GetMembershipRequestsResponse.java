
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipRequestDescriptor" maxOccurs="unbounded"/>
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
@XmlRootElement(name = "GetMembershipRequestsResponse")
public class GetMembershipRequestsResponse
    implements Serializable
{

    @XmlElement(name = "MembershipRequestDescriptor", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
    protected List<MembershipRequestDescriptor> membershipRequestDescriptor;

    /**
     * Gets the value of the membershipRequestDescriptor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the membershipRequestDescriptor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMembershipRequestDescriptor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link org.cagrid.gridgrouper.model.MembershipRequestDescriptor }
     * 
     * 
     */
    public List<MembershipRequestDescriptor> getMembershipRequestDescriptor() {
        if (membershipRequestDescriptor == null) {
            membershipRequestDescriptor = new ArrayList<MembershipRequestDescriptor>();
        }
        return this.membershipRequestDescriptor;
    }

}
