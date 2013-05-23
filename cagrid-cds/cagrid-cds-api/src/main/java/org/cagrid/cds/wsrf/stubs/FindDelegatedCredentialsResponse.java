
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.cds.model.DelegationRecord;


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
 *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegationRecord" maxOccurs="unbounded"/>
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
    "delegationRecord"
})
@XmlRootElement(name = "FindDelegatedCredentialsResponse")
public class FindDelegatedCredentialsResponse
    implements Serializable
{

    @XmlElement(name = "DelegationRecord", namespace = "http://gaards.cagrid.org/cds", required = true)
    protected List<DelegationRecord> delegationRecord;

    /**
     * Gets the value of the delegationRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the delegationRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDelegationRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link org.cagrid.cds.model.DelegationRecord }
     * 
     * 
     */
    public List<DelegationRecord> getDelegationRecord() {
        if (delegationRecord == null) {
            delegationRecord = new ArrayList<DelegationRecord>();
        }
        return this.delegationRecord;
    }

}
