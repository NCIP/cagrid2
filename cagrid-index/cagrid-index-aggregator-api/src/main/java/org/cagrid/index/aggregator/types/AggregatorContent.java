
package org.cagrid.index.aggregator.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         This type encapsulates the Aggregator's ServiceGroup content element, 
 *         which is composed of three xsd:any arrays, the first storing the aggregator 
 *         configuration, the second storing the aggregated data, and the third, optional
 *         element (set by the server only) representing any errors that might have 
 *         occurred during aggregation.  
 *     
 * 
 * <p>Java class for AggregatorContent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggregatorContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AggregatorConfig" type="{http://mds.globus.org/aggregator/types}AggregatorConfig"/>
 *         &lt;element name="AggregatorData" type="{http://mds.globus.org/aggregator/types}AggregatorData"/>
 *         &lt;element name="AggregatorError" type="{http://mds.globus.org/aggregator/types}AggregatorError" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggregatorContent", propOrder = {
    "aggregatorConfig",
    "aggregatorData",
    "aggregatorError"
})
public class AggregatorContent
    implements Serializable
{

    @XmlElement(name = "AggregatorConfig", required = true)
    protected AggregatorConfig aggregatorConfig;
    @XmlElement(name = "AggregatorData", required = true)
    protected AggregatorData aggregatorData;
    @XmlElement(name = "AggregatorError")
    protected AggregatorError aggregatorError;

    /**
     * Gets the value of the aggregatorConfig property.
     * 
     * @return
     *     possible object is
     *     {@link AggregatorConfig }
     *     
     */
    public AggregatorConfig getAggregatorConfig() {
        return aggregatorConfig;
    }

    /**
     * Sets the value of the aggregatorConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregatorConfig }
     *     
     */
    public void setAggregatorConfig(AggregatorConfig value) {
        this.aggregatorConfig = value;
    }

    /**
     * Gets the value of the aggregatorData property.
     * 
     * @return
     *     possible object is
     *     {@link AggregatorData }
     *     
     */
    public AggregatorData getAggregatorData() {
        return aggregatorData;
    }

    /**
     * Sets the value of the aggregatorData property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregatorData }
     *     
     */
    public void setAggregatorData(AggregatorData value) {
        this.aggregatorData = value;
    }

    /**
     * Gets the value of the aggregatorError property.
     * 
     * @return
     *     possible object is
     *     {@link AggregatorError }
     *     
     */
    public AggregatorError getAggregatorError() {
        return aggregatorError;
    }

    /**
     * Sets the value of the aggregatorError property.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregatorError }
     *     
     */
    public void setAggregatorError(AggregatorError value) {
        this.aggregatorError = value;
    }

}
