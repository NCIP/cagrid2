
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegationIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelegationIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="delegationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelegationIdentifier", propOrder = {
    "delegationId"
})
public class DelegationIdentifier
    implements Serializable
{

    protected long delegationId;

    /**
     * Gets the value of the delegationId property.
     * 
     */
    public long getDelegationId() {
        return delegationId;
    }

    /**
     * Sets the value of the delegationId property.
     * 
     */
    public void setDelegationId(long value) {
        this.delegationId = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DelegationIdentifier that = (DelegationIdentifier) o;

        if (delegationId != that.delegationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (delegationId ^ (delegationId >>> 32));
    }
}
