
package org.cagrid.gaards.authentication;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OneTimePassword complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OneTimePassword">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gaards.cagrid.org/authentication}Credential">
 *       &lt;attribute name="userId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="oneTimePassword" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OneTimePassword", namespace = "http://gaards.cagrid.org/authentication")
public class OneTimePassword
    extends Credential
    implements Serializable
{

    @XmlAttribute(name = "userId", namespace = "http://gaards.cagrid.org/authentication", required = true)
    protected String userId;
    @XmlAttribute(name = "oneTimePassword", namespace = "http://gaards.cagrid.org/authentication", required = true)
    protected String oneTimePassword;

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the oneTimePassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOneTimePassword() {
        return oneTimePassword;
    }

    /**
     * Sets the value of the oneTimePassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOneTimePassword(String value) {
        this.oneTimePassword = value;
    }

}
