
package org.cagrid.cds.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CertificateChain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificateChain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X509Certificate" type="{http://gaards.cagrid.org/cds}X509Certificate" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateChain", propOrder = {
    "x509Certificate"
})
public class CertificateChain
    implements Serializable
{

    @XmlElement(name = "X509Certificate", required = true)
    protected List<X509Certificate> x509Certificate;

    /**
     * Gets the value of the x509Certificate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the x509Certificate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getX509Certificate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link org.cagrid.cds.model.X509Certificate }
     * 
     * 
     */
    public List<X509Certificate> getX509Certificate() {
        if (x509Certificate == null) {
            x509Certificate = new ArrayList<X509Certificate>();
        }
        return this.x509Certificate;
    }

}
