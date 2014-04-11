
package org.cagrid.index.aggregator.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AggregatorPollType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggregatorPollType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PollIntervalMillis" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggregatorPollType", propOrder = {
    "pollIntervalMillis"
})
@XmlSeeAlso({
    ExecutionPollType.class,
    QueryResourcePropertiesPollType.class,
    GetResourcePropertyPollType.class,
    GetMultipleResourcePropertiesPollType.class
})
public class AggregatorPollType
    implements Serializable
{

    @XmlElement(name = "PollIntervalMillis")
    protected int pollIntervalMillis;

    /**
     * Gets the value of the pollIntervalMillis property.
     * 
     */
    public int getPollIntervalMillis() {
        return pollIntervalMillis;
    }

    /**
     * Sets the value of the pollIntervalMillis property.
     * 
     */
    public void setPollIntervalMillis(int value) {
        this.pollIntervalMillis = value;
    }

}
