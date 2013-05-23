
package org.cagrid.gts.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gts.model.TrustedAuthority;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustedAuthority"/>
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
    "trustedAuthority"
})
@XmlRootElement(name = "AddTrustedAuthorityResponse")
public class AddTrustedAuthorityResponse
    implements Serializable
{

    @XmlElement(name = "TrustedAuthority", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
    protected TrustedAuthority trustedAuthority;

    /**
     * Gets the value of the trustedAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link TrustedAuthority }
     *     
     */
    public TrustedAuthority getTrustedAuthority() {
        return trustedAuthority;
    }

    /**
     * Sets the value of the trustedAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustedAuthority }
     *     
     */
    public void setTrustedAuthority(TrustedAuthority value) {
        this.trustedAuthority = value;
    }

}
