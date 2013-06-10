
package org.oasis.names.tc.saml.assertion;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
import org.w3._2000._09.xmldsig.SignatureType;
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Java class for AssertionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssertionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Conditions" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Advice" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Statement"/>
 *           &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}SubjectStatement"/>
 *           &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}AuthenticationStatement"/>
 *           &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}AuthorizationDecisionStatement"/>
 *           &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}AttributeStatement"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MajorVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="MinorVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="AssertionID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="Issuer" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IssueInstant" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssertionType", propOrder = {
    "conditions",
    "advice",
    "statementOrSubjectStatementOrAuthenticationStatement",
    "signature"
})
public class AssertionType implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "Conditions")
    protected ConditionsType conditions;
    @XmlElement(name = "Advice")
    protected AdviceType advice;
    @XmlElements({
        @XmlElement(name = "AuthenticationStatement", type = AuthenticationStatementType.class),
        @XmlElement(name = "Statement"),
        @XmlElement(name = "AuthorizationDecisionStatement", type = AuthorizationDecisionStatementType.class),
        @XmlElement(name = "AttributeStatement", type = AttributeStatementType.class),
        @XmlElement(name = "SubjectStatement", type = SubjectStatementAbstractType.class)
    })
    protected List<StatementAbstractType> statementOrSubjectStatementOrAuthenticationStatement;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;
    @XmlAttribute(name = "MajorVersion", required = true)
    protected BigInteger majorVersion;
    @XmlAttribute(name = "MinorVersion", required = true)
    protected BigInteger minorVersion;
    @XmlAttribute(name = "AssertionID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String assertionID;
    @XmlAttribute(name = "Issuer", required = true)
    protected String issuer;
    @XmlAttribute(name = "IssueInstant", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar issueInstant;

    /**
     * Gets the value of the conditions property.
     * 
     * @return
     *     possible object is
     *     {@link ConditionsType }
     *     
     */
    public ConditionsType getConditions() {
        return conditions;
    }

    /**
     * Sets the value of the conditions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConditionsType }
     *     
     */
    public void setConditions(ConditionsType value) {
        this.conditions = value;
    }

    /**
     * Gets the value of the advice property.
     * 
     * @return
     *     possible object is
     *     {@link AdviceType }
     *     
     */
    public AdviceType getAdvice() {
        return advice;
    }

    /**
     * Sets the value of the advice property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdviceType }
     *     
     */
    public void setAdvice(AdviceType value) {
        this.advice = value;
    }

    /**
     * Gets the value of the statementOrSubjectStatementOrAuthenticationStatement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statementOrSubjectStatementOrAuthenticationStatement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatementOrSubjectStatementOrAuthenticationStatement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuthenticationStatementType }
     * {@link StatementAbstractType }
     * {@link AuthorizationDecisionStatementType }
     * {@link AttributeStatementType }
     * {@link SubjectStatementAbstractType }
     * 
     * 
     */
    public List<StatementAbstractType> getStatementOrSubjectStatementOrAuthenticationStatement() {
        if (statementOrSubjectStatementOrAuthenticationStatement == null) {
            statementOrSubjectStatementOrAuthenticationStatement = new ArrayList<StatementAbstractType>();
        }
        return this.statementOrSubjectStatementOrAuthenticationStatement;
    }

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Gets the value of the majorVersion property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMajorVersion() {
        return majorVersion;
    }

    /**
     * Sets the value of the majorVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMajorVersion(BigInteger value) {
        this.majorVersion = value;
    }

    /**
     * Gets the value of the minorVersion property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinorVersion() {
        return minorVersion;
    }

    /**
     * Sets the value of the minorVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinorVersion(BigInteger value) {
        this.minorVersion = value;
    }

    /**
     * Gets the value of the assertionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssertionID() {
        return assertionID;
    }

    /**
     * Sets the value of the assertionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssertionID(String value) {
        this.assertionID = value;
    }

    /**
     * Gets the value of the issuer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Sets the value of the issuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuer(String value) {
        this.issuer = value;
    }

    /**
     * Gets the value of the issueInstant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getIssueInstant() {
        return issueInstant;
    }

    /**
     * Sets the value of the issueInstant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueInstant(Calendar value) {
        this.issueInstant = value;
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
        {
            ConditionsType theConditions;
            theConditions = this.getConditions();
            strategy.appendField(locator, this, "conditions", buffer, theConditions);
        }
        {
            AdviceType theAdvice;
            theAdvice = this.getAdvice();
            strategy.appendField(locator, this, "advice", buffer, theAdvice);
        }
        {
            List<StatementAbstractType> theStatementOrSubjectStatementOrAuthenticationStatement;
            theStatementOrSubjectStatementOrAuthenticationStatement = (((this.statementOrSubjectStatementOrAuthenticationStatement!= null)&&(!this.statementOrSubjectStatementOrAuthenticationStatement.isEmpty()))?this.getStatementOrSubjectStatementOrAuthenticationStatement():null);
            strategy.appendField(locator, this, "statementOrSubjectStatementOrAuthenticationStatement", buffer, theStatementOrSubjectStatementOrAuthenticationStatement);
        }
        {
            SignatureType theSignature;
            theSignature = this.getSignature();
            strategy.appendField(locator, this, "signature", buffer, theSignature);
        }
        {
            BigInteger theMajorVersion;
            theMajorVersion = this.getMajorVersion();
            strategy.appendField(locator, this, "majorVersion", buffer, theMajorVersion);
        }
        {
            BigInteger theMinorVersion;
            theMinorVersion = this.getMinorVersion();
            strategy.appendField(locator, this, "minorVersion", buffer, theMinorVersion);
        }
        {
            String theAssertionID;
            theAssertionID = this.getAssertionID();
            strategy.appendField(locator, this, "assertionID", buffer, theAssertionID);
        }
        {
            String theIssuer;
            theIssuer = this.getIssuer();
            strategy.appendField(locator, this, "issuer", buffer, theIssuer);
        }
        {
            Calendar theIssueInstant;
            theIssueInstant = this.getIssueInstant();
            strategy.appendField(locator, this, "issueInstant", buffer, theIssueInstant);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            ConditionsType theConditions;
            theConditions = this.getConditions();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "conditions", theConditions), currentHashCode, theConditions);
        }
        {
            AdviceType theAdvice;
            theAdvice = this.getAdvice();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "advice", theAdvice), currentHashCode, theAdvice);
        }
        {
            List<StatementAbstractType> theStatementOrSubjectStatementOrAuthenticationStatement;
            theStatementOrSubjectStatementOrAuthenticationStatement = (((this.statementOrSubjectStatementOrAuthenticationStatement!= null)&&(!this.statementOrSubjectStatementOrAuthenticationStatement.isEmpty()))?this.getStatementOrSubjectStatementOrAuthenticationStatement():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "statementOrSubjectStatementOrAuthenticationStatement", theStatementOrSubjectStatementOrAuthenticationStatement), currentHashCode, theStatementOrSubjectStatementOrAuthenticationStatement);
        }
        {
            SignatureType theSignature;
            theSignature = this.getSignature();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "signature", theSignature), currentHashCode, theSignature);
        }
        {
            BigInteger theMajorVersion;
            theMajorVersion = this.getMajorVersion();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "majorVersion", theMajorVersion), currentHashCode, theMajorVersion);
        }
        {
            BigInteger theMinorVersion;
            theMinorVersion = this.getMinorVersion();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "minorVersion", theMinorVersion), currentHashCode, theMinorVersion);
        }
        {
            String theAssertionID;
            theAssertionID = this.getAssertionID();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "assertionID", theAssertionID), currentHashCode, theAssertionID);
        }
        {
            String theIssuer;
            theIssuer = this.getIssuer();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "issuer", theIssuer), currentHashCode, theIssuer);
        }
        {
            Calendar theIssueInstant;
            theIssueInstant = this.getIssueInstant();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "issueInstant", theIssueInstant), currentHashCode, theIssueInstant);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AssertionType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AssertionType that = ((AssertionType) object);
        {
            ConditionsType lhsConditions;
            lhsConditions = this.getConditions();
            ConditionsType rhsConditions;
            rhsConditions = that.getConditions();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "conditions", lhsConditions), LocatorUtils.property(thatLocator, "conditions", rhsConditions), lhsConditions, rhsConditions)) {
                return false;
            }
        }
        {
            AdviceType lhsAdvice;
            lhsAdvice = this.getAdvice();
            AdviceType rhsAdvice;
            rhsAdvice = that.getAdvice();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "advice", lhsAdvice), LocatorUtils.property(thatLocator, "advice", rhsAdvice), lhsAdvice, rhsAdvice)) {
                return false;
            }
        }
        {
            List<StatementAbstractType> lhsStatementOrSubjectStatementOrAuthenticationStatement;
            lhsStatementOrSubjectStatementOrAuthenticationStatement = (((this.statementOrSubjectStatementOrAuthenticationStatement!= null)&&(!this.statementOrSubjectStatementOrAuthenticationStatement.isEmpty()))?this.getStatementOrSubjectStatementOrAuthenticationStatement():null);
            List<StatementAbstractType> rhsStatementOrSubjectStatementOrAuthenticationStatement;
            rhsStatementOrSubjectStatementOrAuthenticationStatement = (((that.statementOrSubjectStatementOrAuthenticationStatement!= null)&&(!that.statementOrSubjectStatementOrAuthenticationStatement.isEmpty()))?that.getStatementOrSubjectStatementOrAuthenticationStatement():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "statementOrSubjectStatementOrAuthenticationStatement", lhsStatementOrSubjectStatementOrAuthenticationStatement), LocatorUtils.property(thatLocator, "statementOrSubjectStatementOrAuthenticationStatement", rhsStatementOrSubjectStatementOrAuthenticationStatement), lhsStatementOrSubjectStatementOrAuthenticationStatement, rhsStatementOrSubjectStatementOrAuthenticationStatement)) {
                return false;
            }
        }
        {
            SignatureType lhsSignature;
            lhsSignature = this.getSignature();
            SignatureType rhsSignature;
            rhsSignature = that.getSignature();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "signature", lhsSignature), LocatorUtils.property(thatLocator, "signature", rhsSignature), lhsSignature, rhsSignature)) {
                return false;
            }
        }
        {
            BigInteger lhsMajorVersion;
            lhsMajorVersion = this.getMajorVersion();
            BigInteger rhsMajorVersion;
            rhsMajorVersion = that.getMajorVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "majorVersion", lhsMajorVersion), LocatorUtils.property(thatLocator, "majorVersion", rhsMajorVersion), lhsMajorVersion, rhsMajorVersion)) {
                return false;
            }
        }
        {
            BigInteger lhsMinorVersion;
            lhsMinorVersion = this.getMinorVersion();
            BigInteger rhsMinorVersion;
            rhsMinorVersion = that.getMinorVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minorVersion", lhsMinorVersion), LocatorUtils.property(thatLocator, "minorVersion", rhsMinorVersion), lhsMinorVersion, rhsMinorVersion)) {
                return false;
            }
        }
        {
            String lhsAssertionID;
            lhsAssertionID = this.getAssertionID();
            String rhsAssertionID;
            rhsAssertionID = that.getAssertionID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "assertionID", lhsAssertionID), LocatorUtils.property(thatLocator, "assertionID", rhsAssertionID), lhsAssertionID, rhsAssertionID)) {
                return false;
            }
        }
        {
            String lhsIssuer;
            lhsIssuer = this.getIssuer();
            String rhsIssuer;
            rhsIssuer = that.getIssuer();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "issuer", lhsIssuer), LocatorUtils.property(thatLocator, "issuer", rhsIssuer), lhsIssuer, rhsIssuer)) {
                return false;
            }
        }
        {
            Calendar lhsIssueInstant;
            lhsIssueInstant = this.getIssueInstant();
            Calendar rhsIssueInstant;
            rhsIssueInstant = that.getIssueInstant();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "issueInstant", lhsIssueInstant), LocatorUtils.property(thatLocator, "issueInstant", rhsIssueInstant), lhsIssueInstant, rhsIssueInstant)) {
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
