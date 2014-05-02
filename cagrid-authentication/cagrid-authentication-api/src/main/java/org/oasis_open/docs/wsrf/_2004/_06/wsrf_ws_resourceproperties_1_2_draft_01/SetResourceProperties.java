
package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd}Insert"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd}Update"/>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd}Delete"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "insertOrUpdateOrDelete"
})
@XmlRootElement(name = "SetResourceProperties")
public class SetResourceProperties
    implements Serializable
{

    @XmlElements({
        @XmlElement(name = "Insert", type = InsertType.class),
        @XmlElement(name = "Update", type = UpdateType.class),
        @XmlElement(name = "Delete", type = DeleteType.class)
    })
    protected List<Serializable> insertOrUpdateOrDelete;

    /**
     * Gets the value of the insertOrUpdateOrDelete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the insertOrUpdateOrDelete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInsertOrUpdateOrDelete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InsertType }
     * {@link UpdateType }
     * {@link DeleteType }
     * 
     * 
     */
    public List<Serializable> getInsertOrUpdateOrDelete() {
        if (insertOrUpdateOrDelete == null) {
            insertOrUpdateOrDelete = new ArrayList<Serializable>();
        }
        return this.insertOrUpdateOrDelete;
    }

}
