
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.cds.model.DelegatedCredentialAuditRecord;


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
 *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegatedCredentialAuditRecord" maxOccurs="unbounded"/>
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
    "delegatedCredentialAuditRecord"
})
@XmlRootElement(name = "SearchDelegatedCredentialAuditLogResponse")
public class SearchDelegatedCredentialAuditLogResponse
    implements Serializable
{

    @XmlElement(name = "DelegatedCredentialAuditRecord", namespace = "http://gaards.cagrid.org/cds", required = true)
    protected List<DelegatedCredentialAuditRecord> delegatedCredentialAuditRecord;

    /**
     * Gets the value of the delegatedCredentialAuditRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the delegatedCredentialAuditRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDelegatedCredentialAuditRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link org.cagrid.cds.model.DelegatedCredentialAuditRecord }
     * 
     * 
     */
    public List<DelegatedCredentialAuditRecord> getDelegatedCredentialAuditRecord() {
        if (delegatedCredentialAuditRecord == null) {
            delegatedCredentialAuditRecord = new ArrayList<DelegatedCredentialAuditRecord>();
        }
        return this.delegatedCredentialAuditRecord;
    }

}
