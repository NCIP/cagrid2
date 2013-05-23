
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.GridUserPolicy;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUserPolicy" maxOccurs="unbounded"/>
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
    "gridUserPolicy"
})
@XmlRootElement(name = "GetGridUserPoliciesResponse")
public class GetGridUserPoliciesResponse
    implements Serializable
{

    @XmlElement(name = "GridUserPolicy", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
    protected List<GridUserPolicy> gridUserPolicy;

    /**
     * Gets the value of the gridUserPolicy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gridUserPolicy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGridUserPolicy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GridUserPolicy }
     * 
     * 
     */
    public List<GridUserPolicy> getGridUserPolicy() {
        if (gridUserPolicy == null) {
            gridUserPolicy = new ArrayList<GridUserPolicy>();
        }
        return this.gridUserPolicy;
    }

}
