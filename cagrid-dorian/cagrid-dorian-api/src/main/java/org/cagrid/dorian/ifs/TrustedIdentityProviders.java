
package org.cagrid.dorian.ifs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrustedIdentityProviders complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustedIdentityProviders">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrustedIdentityProvider" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdentityProvider" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustedIdentityProviders", propOrder = {
    "trustedIdentityProvider"
})
public class TrustedIdentityProviders
    implements Serializable
{

    @XmlElement(name = "TrustedIdentityProvider")
    protected List<TrustedIdentityProvider> trustedIdentityProvider;

    /**
     * Gets the value of the trustedIdentityProvider property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trustedIdentityProvider property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrustedIdentityProvider().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrustedIdentityProvider }
     * 
     * 
     */
    public List<TrustedIdentityProvider> getTrustedIdentityProvider() {
        if (trustedIdentityProvider == null) {
            trustedIdentityProvider = new ArrayList<TrustedIdentityProvider>();
        }
        return this.trustedIdentityProvider;
    }

}
