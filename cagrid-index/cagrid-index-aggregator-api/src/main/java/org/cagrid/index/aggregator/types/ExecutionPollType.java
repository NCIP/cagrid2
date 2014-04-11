
package org.cagrid.index.aggregator.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for ExecutionPollType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExecutionPollType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mds.globus.org/aggregator/types}AggregatorPollType">
 *       &lt;sequence>
 *         &lt;element name="ProbeName" type="{http://www.w3.org/2001/XMLSchema}QName"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExecutionPollType", propOrder = {
    "probeName"
})
public class ExecutionPollType
    extends AggregatorPollType
    implements Serializable
{

    @XmlElement(name = "ProbeName", required = true)
    protected QName probeName;

    /**
     * Gets the value of the probeName property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getProbeName() {
        return probeName;
    }

    /**
     * Sets the value of the probeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setProbeName(QName value) {
        this.probeName = value;
    }

}
