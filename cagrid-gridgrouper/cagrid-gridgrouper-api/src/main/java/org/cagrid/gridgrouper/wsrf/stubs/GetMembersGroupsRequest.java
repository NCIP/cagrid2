
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.MembershipType;


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
 *         &lt;element name="member">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}SubjectIdentifier"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="type">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "member",
    "type"
})
@XmlRootElement(name = "GetMembersGroupsRequest")
public class GetMembersGroupsRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Member member;
    @XmlElement(required = true)
    protected Type type;

    /**
     * Gets the value of the member property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetMembersGroupsRequest.Member }
     *     
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets the value of the member property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetMembersGroupsRequest.Member }
     *     
     */
    public void setMember(Member value) {
        this.member = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetMembersGroupsRequest.Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetMembersGroupsRequest.Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }


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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}SubjectIdentifier"/>
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
        "subjectIdentifier"
    })
    public static class Member
        implements Serializable
    {

        @XmlElement(name = "SubjectIdentifier", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected String subjectIdentifier;

        /**
         * Gets the value of the subjectIdentifier property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSubjectIdentifier() {
            return subjectIdentifier;
        }

        /**
         * Sets the value of the subjectIdentifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSubjectIdentifier(String value) {
            this.subjectIdentifier = value;
        }

    }


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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipType"/>
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
        "membershipType"
    })
    public static class Type
        implements Serializable
    {

        @XmlElement(name = "MembershipType", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected MembershipType membershipType;

        /**
         * Gets the value of the membershipType property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gridgrouper.model.MembershipType }
         *     
         */
        public MembershipType getMembershipType() {
            return membershipType;
        }

        /**
         * Sets the value of the membershipType property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gridgrouper.model.MembershipType }
         *     
         */
        public void setMembershipType(MembershipType value) {
            this.membershipType = value;
        }

    }

}
