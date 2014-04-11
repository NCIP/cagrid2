
package org.cagrid.index.aggregator.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for GetMultipleResourcePropertiesPollType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetMultipleResourcePropertiesPollType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mds.globus.org/aggregator/types}AggregatorPollType">
 *       &lt;sequence>
 *         &lt;element name="ResourcePropertyNames" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetMultipleResourcePropertiesPollType", propOrder = {
    "resourcePropertyNames"
})
public class GetMultipleResourcePropertiesPollType
    extends AggregatorPollType
    implements Serializable
{

    @XmlElement(name = "ResourcePropertyNames", required = true)
    protected List<QName> resourcePropertyNames;

    /**
     * Gets the value of the resourcePropertyNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourcePropertyNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourcePropertyNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QName }
     * 
     * 
     */
    public List<QName> getResourcePropertyNames() {
        if (resourcePropertyNames == null) {
            resourcePropertyNames = new ArrayList<QName>();
        }
        return this.resourcePropertyNames;
    }

}
