
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateRecord" maxOccurs="unbounded"/>
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
@XmlRootElement(name = "FindHostCertificatesResponse")
public class FindHostCertificatesResponse
    implements Serializable
{

    @XmlElement(name = "HostCertificateRecord", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
    protected List<HostCertificateRecord> hostCertificateRecord;

    /**
     * Gets the value of the hostCertificateRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hostCertificateRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHostCertificateRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HostCertificateRecord }
     * 
     * 
     */
    public List<HostCertificateRecord> getHostCertificateRecord() {
        if (hostCertificateRecord == null) {
            hostCertificateRecord = new ArrayList<HostCertificateRecord>();
        }
        return this.hostCertificateRecord;
    }

}
