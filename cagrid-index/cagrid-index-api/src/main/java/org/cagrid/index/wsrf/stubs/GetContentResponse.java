
package org.cagrid.index.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.index.types.BigIndexContent;


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
 *         &lt;element name="BigIndexContent" type="{http://mds.globus.org/bigindex/2008/11/24/types}BigIndexContent" maxOccurs="unbounded" minOccurs="0"/>
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
    "bigIndexContent"
})
@XmlRootElement(name = "GetContentResponse")
public class GetContentResponse
    implements Serializable
{

    @XmlElement(name = "BigIndexContent", namespace = "")
    protected List<BigIndexContent> bigIndexContent;

    /**
     * Gets the value of the bigIndexContent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bigIndexContent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBigIndexContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigIndexContent }
     * 
     * 
     */
    public List<BigIndexContent> getBigIndexContent() {
        if (bigIndexContent == null) {
            bigIndexContent = new ArrayList<BigIndexContent>();
        }
        return this.bigIndexContent;
    }

}
