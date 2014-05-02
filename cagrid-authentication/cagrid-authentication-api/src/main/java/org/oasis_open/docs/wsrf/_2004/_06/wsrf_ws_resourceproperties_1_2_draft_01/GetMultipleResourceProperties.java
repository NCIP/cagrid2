
package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


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
 *         &lt;element name="ResourceProperty" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded"/>
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
    "resourceProperty"
})
@XmlRootElement(name = "GetMultipleResourceProperties")
public class GetMultipleResourceProperties
    implements Serializable
{

    @XmlElement(name = "ResourceProperty", required = true)
    protected List<QName> resourceProperty;

    /**
     * Gets the value of the resourceProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QName }
     * 
     * 
     */
    public List<QName> getResourceProperty() {
        if (resourceProperty == null) {
            resourceProperty = new ArrayList<QName>();
        }
        return this.resourceProperty;
    }

}
