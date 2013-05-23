
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.MembershipDescriptor;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipDescriptor" maxOccurs="unbounded"/>
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
    "membershipDescriptor"
})
@XmlRootElement(name = "GetMembershipsResponse")
public class GetMembershipsResponse
    implements Serializable
{

    @XmlElement(name = "MembershipDescriptor", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
    protected List<MembershipDescriptor> membershipDescriptor;

    /**
     * Gets the value of the membershipDescriptor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the membershipDescriptor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMembershipDescriptor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link org.cagrid.gridgrouper.model.MembershipDescriptor }
     * 
     * 
     */
    public List<MembershipDescriptor> getMembershipDescriptor() {
        if (membershipDescriptor == null) {
            membershipDescriptor = new ArrayList<MembershipDescriptor>();
        }
        return this.membershipDescriptor;
    }

}
