
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserIdPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserIdPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="MinLength" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="MaxLength" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserIdPolicy")
public class UserIdPolicy
    implements Serializable
{

    @XmlAttribute(name = "MinLength", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int minLength;
    @XmlAttribute(name = "MaxLength", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int maxLength;

    /**
     * Gets the value of the minLength property.
     * 
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * Sets the value of the minLength property.
     * 
     */
    public void setMinLength(int value) {
        this.minLength = value;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     */
    public void setMaxLength(int value) {
        this.maxLength = value;
    }

}
