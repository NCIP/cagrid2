
package org.oasis.names.tc.saml.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for AuthorizationDecisionStatementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorizationDecisionStatementType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SAML:1.0:assertion}SubjectStatementAbstractType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Action" maxOccurs="unbounded"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Evidence" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Resource" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="Decision" use="required" type="{urn:oasis:names:tc:SAML:1.0:assertion}DecisionType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizationDecisionStatementType", propOrder = {
    "action",
    "evidence"
})
public class AuthorizationDecisionStatementType
    extends SubjectStatementAbstractType
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "Action", required = true)
    protected List<ActionType> action;
    @XmlElement(name = "Evidence")
    protected EvidenceType evidence;
    @XmlAttribute(name = "Resource", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String resource;
    @XmlAttribute(name = "Decision", required = true)
    protected DecisionType decision;

    /**
     * Gets the value of the action property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the action property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActionType }
     * 
     * 
     */
    public List<ActionType> getAction() {
        if (action == null) {
            action = new ArrayList<ActionType>();
        }
        return this.action;
    }

    /**
     * Gets the value of the evidence property.
     * 
     * @return
     *     possible object is
     *     {@link EvidenceType }
     *     
     */
    public EvidenceType getEvidence() {
        return evidence;
    }

    /**
     * Sets the value of the evidence property.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidenceType }
     *     
     */
    public void setEvidence(EvidenceType value) {
        this.evidence = value;
    }

    /**
     * Gets the value of the resource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResource() {
        return resource;
    }

    /**
     * Sets the value of the resource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResource(String value) {
        this.resource = value;
    }

    /**
     * Gets the value of the decision property.
     * 
     * @return
     *     possible object is
     *     {@link DecisionType }
     *     
     */
    public DecisionType getDecision() {
        return decision;
    }

    /**
     * Sets the value of the decision property.
     * 
     * @param value
     *     allowed object is
     *     {@link DecisionType }
     *     
     */
    public void setDecision(DecisionType value) {
        this.decision = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            List<ActionType> theAction;
            theAction = (((this.action!= null)&&(!this.action.isEmpty()))?this.getAction():null);
            strategy.appendField(locator, this, "action", buffer, theAction);
        }
        {
            EvidenceType theEvidence;
            theEvidence = this.getEvidence();
            strategy.appendField(locator, this, "evidence", buffer, theEvidence);
        }
        {
            String theResource;
            theResource = this.getResource();
            strategy.appendField(locator, this, "resource", buffer, theResource);
        }
        {
            DecisionType theDecision;
            theDecision = this.getDecision();
            strategy.appendField(locator, this, "decision", buffer, theDecision);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            List<ActionType> theAction;
            theAction = (((this.action!= null)&&(!this.action.isEmpty()))?this.getAction():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "action", theAction), currentHashCode, theAction);
        }
        {
            EvidenceType theEvidence;
            theEvidence = this.getEvidence();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "evidence", theEvidence), currentHashCode, theEvidence);
        }
        {
            String theResource;
            theResource = this.getResource();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "resource", theResource), currentHashCode, theResource);
        }
        {
            DecisionType theDecision;
            theDecision = this.getDecision();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "decision", theDecision), currentHashCode, theDecision);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AuthorizationDecisionStatementType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AuthorizationDecisionStatementType that = ((AuthorizationDecisionStatementType) object);
        {
            List<ActionType> lhsAction;
            lhsAction = (((this.action!= null)&&(!this.action.isEmpty()))?this.getAction():null);
            List<ActionType> rhsAction;
            rhsAction = (((that.action!= null)&&(!that.action.isEmpty()))?that.getAction():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "action", lhsAction), LocatorUtils.property(thatLocator, "action", rhsAction), lhsAction, rhsAction)) {
                return false;
            }
        }
        {
            EvidenceType lhsEvidence;
            lhsEvidence = this.getEvidence();
            EvidenceType rhsEvidence;
            rhsEvidence = that.getEvidence();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "evidence", lhsEvidence), LocatorUtils.property(thatLocator, "evidence", rhsEvidence), lhsEvidence, rhsEvidence)) {
                return false;
            }
        }
        {
            String lhsResource;
            lhsResource = this.getResource();
            String rhsResource;
            rhsResource = that.getResource();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "resource", lhsResource), LocatorUtils.property(thatLocator, "resource", rhsResource), lhsResource, rhsResource)) {
                return false;
            }
        }
        {
            DecisionType lhsDecision;
            lhsDecision = this.getDecision();
            DecisionType rhsDecision;
            rhsDecision = that.getDecision();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "decision", lhsDecision), LocatorUtils.property(thatLocator, "decision", rhsDecision), lhsDecision, rhsDecision)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
