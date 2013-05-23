
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.HostCertificateRecord;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateRecord"/>
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
    "hostCertificateRecord"
})
@XmlRootElement(name = "RequestHostCertificateResponse")
public class RequestHostCertificateResponse
    implements Serializable
{

    @XmlElement(name = "HostCertificateRecord", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
    protected HostCertificateRecord hostCertificateRecord;

    /**
     * Gets the value of the hostCertificateRecord property.
     * 
     * @return
     *     possible object is
     *     {@link HostCertificateRecord }
     *     
     */
    public HostCertificateRecord getHostCertificateRecord() {
        return hostCertificateRecord;
    }

    /**
     * Sets the value of the hostCertificateRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostCertificateRecord }
     *     
     */
    public void setHostCertificateRecord(HostCertificateRecord value) {
        this.hostCertificateRecord = value;
    }

}
