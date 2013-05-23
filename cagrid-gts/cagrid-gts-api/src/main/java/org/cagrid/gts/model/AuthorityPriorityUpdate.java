
package org.cagrid.gts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorityPriorityUpdate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorityPriorityUpdate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthorityPrioritySpecification" type="{http://cagrid.nci.nih.gov/8/gts}AuthorityPrioritySpecification" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorityPriorityUpdate", propOrder = {
    "authorityPrioritySpecification"
})
public class AuthorityPriorityUpdate
    implements Serializable
{

    @XmlElement(name = "AuthorityPrioritySpecification", required = true)
    protected List<AuthorityPrioritySpecification> authorityPrioritySpecification;

    /**
     * Gets the value of the authorityPrioritySpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authorityPrioritySpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthorityPrioritySpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuthorityPrioritySpecification }
     * 
     * 
     */
    public List<AuthorityPrioritySpecification> getAuthorityPrioritySpecification() {
        if (authorityPrioritySpecification == null) {
            authorityPrioritySpecification = new ArrayList<AuthorityPrioritySpecification>();
        }
        return this.authorityPrioritySpecification;
    }

}
