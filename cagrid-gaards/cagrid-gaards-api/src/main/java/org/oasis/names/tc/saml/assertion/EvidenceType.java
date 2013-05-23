
package org.oasis.names.tc.saml.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EvidenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EvidenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}AssertionIDReference"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Assertion"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvidenceType", propOrder = {
    "assertionIDReferenceOrAssertion"
})
public class EvidenceType
    implements Serializable
{

    @XmlElementRefs({
        @XmlElementRef(name = "Assertion", namespace = "urn:oasis:names:tc:SAML:1.0:assertion", type = JAXBElement.class),
        @XmlElementRef(name = "AssertionIDReference", namespace = "urn:oasis:names:tc:SAML:1.0:assertion", type = JAXBElement.class)
    })
    protected List<JAXBElement<? extends Serializable>> assertionIDReferenceOrAssertion;

    /**
     * Gets the value of the assertionIDReferenceOrAssertion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assertionIDReferenceOrAssertion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssertionIDReferenceOrAssertion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AssertionType }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends Serializable>> getAssertionIDReferenceOrAssertion() {
        if (assertionIDReferenceOrAssertion == null) {
            assertionIDReferenceOrAssertion = new ArrayList<JAXBElement<? extends Serializable>>();
        }
        return this.assertionIDReferenceOrAssertion;
    }

}
