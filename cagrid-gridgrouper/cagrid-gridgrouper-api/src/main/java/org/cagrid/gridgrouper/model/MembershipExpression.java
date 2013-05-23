
package org.cagrid.gridgrouper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * Binary joint
 * 
 * <p>Java class for MembershipExpression complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MembershipExpression">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipQuery" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipExpression" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attribute name="logicRelation" use="required" type="{http://cagrid.nci.nih.gov/1/GridGrouper}LogicalOperator" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MembershipExpression", propOrder = {
    "membershipQueryOrMembershipExpression"
})
public class MembershipExpression implements Serializable
{

    @XmlElements({
        @XmlElement(name = "MembershipExpression", type = MembershipExpression.class),
        @XmlElement(name = "MembershipQuery", type = MembershipQuery.class)
    })
    protected List<Serializable> membershipQueryOrMembershipExpression;
    @XmlAttribute(name = "logicRelation", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
    protected LogicalOperator logicRelation;

    /**
     * Gets the value of the membershipQueryOrMembershipExpression property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the membershipQueryOrMembershipExpression property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMembershipQueryOrMembershipExpression().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link org.cagrid.gridgrouper.model.MembershipExpression }
     * {@link MembershipQuery }
     * 
     * 
     */
    public List<Serializable> getMembershipQueryOrMembershipExpression() {
        if (membershipQueryOrMembershipExpression == null) {
            membershipQueryOrMembershipExpression = new ArrayList<Serializable>();
        }
        return this.membershipQueryOrMembershipExpression;
    }

    /**
     * Gets the value of the logicRelation property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.LogicalOperator }
     *     
     */
    public LogicalOperator getLogicRelation() {
        return logicRelation;
    }

    /**
     * Sets the value of the logicRelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.model.LogicalOperator }
     *     
     */
    public void setLogicRelation(LogicalOperator value) {
        this.logicRelation = value;
    }

}
