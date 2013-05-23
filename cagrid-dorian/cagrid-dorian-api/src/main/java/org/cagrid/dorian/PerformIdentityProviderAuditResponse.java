
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.IdentityProviderAuditRecord;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}IdentityProviderAuditRecord" maxOccurs="unbounded"/>
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
    "identityProviderAuditRecord"
})
@XmlRootElement(name = "PerformIdentityProviderAuditResponse")
public class PerformIdentityProviderAuditResponse
    implements Serializable
{

    @XmlElement(name = "IdentityProviderAuditRecord", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
    protected List<IdentityProviderAuditRecord> identityProviderAuditRecord;

    /**
     * Gets the value of the identityProviderAuditRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identityProviderAuditRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentityProviderAuditRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentityProviderAuditRecord }
     * 
     * 
     */
    public List<IdentityProviderAuditRecord> getIdentityProviderAuditRecord() {
        if (identityProviderAuditRecord == null) {
            identityProviderAuditRecord = new ArrayList<IdentityProviderAuditRecord>();
        }
        return this.identityProviderAuditRecord;
    }

}
