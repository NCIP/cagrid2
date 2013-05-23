
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.MembershipExpression;


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
 *         &lt;element name="member" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expression">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipExpression"/>
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
    "expression"
})
@XmlRootElement(name = "IsMemberRequest")
public class IsMemberRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected String member;
    @XmlElement(required = true)
    protected Expression expression;

    /**
     * Gets the value of the member property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMember() {
        return member;
    }

    /**
     * Sets the value of the member property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMember(String value) {
        this.member = value;
    }

    /**
     * Gets the value of the expression property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.IsMemberRequest.Expression }
     *     
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * Sets the value of the expression property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.IsMemberRequest.Expression }
     *     
     */
    public void setExpression(Expression value) {
        this.expression = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipExpression"/>
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
        "membershipExpression"
    })
    public static class Expression
        implements Serializable
    {

        @XmlElement(name = "MembershipExpression", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected MembershipExpression membershipExpression;

        /**
         * Gets the value of the membershipExpression property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gridgrouper.model.MembershipExpression }
         *     
         */
        public MembershipExpression getMembershipExpression() {
            return membershipExpression;
        }

        /**
         * Sets the value of the membershipExpression property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gridgrouper.model.MembershipExpression }
         *     
         */
        public void setMembershipExpression(MembershipExpression value) {
            this.membershipExpression = value;
        }

    }

}
