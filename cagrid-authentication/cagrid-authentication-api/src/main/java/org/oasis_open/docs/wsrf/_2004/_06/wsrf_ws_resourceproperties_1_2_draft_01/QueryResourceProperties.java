
package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element ref="{http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd}QueryExpression"/>
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
    "queryExpression"
})
@XmlRootElement(name = "QueryResourceProperties")
public class QueryResourceProperties
    implements Serializable
{

    @XmlElement(name = "QueryExpression", required = true)
    protected QueryExpressionType queryExpression;

    /**
     * Gets the value of the queryExpression property.
     * 
     * @return
     *     possible object is
     *     {@link QueryExpressionType }
     *     
     */
    public QueryExpressionType getQueryExpression() {
        return queryExpression;
    }

    /**
     * Sets the value of the queryExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryExpressionType }
     *     
     */
    public void setQueryExpression(QueryExpressionType value) {
        this.queryExpression = value;
    }

}
