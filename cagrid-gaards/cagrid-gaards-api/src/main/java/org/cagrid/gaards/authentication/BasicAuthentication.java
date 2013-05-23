
package org.cagrid.gaards.authentication;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BasicAuthentication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BasicAuthentication">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gaards.cagrid.org/authentication}Credential">
 *       &lt;attribute name="userId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="password" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasicAuthentication", namespace = "http://gaards.cagrid.org/authentication")
public class BasicAuthentication
    extends Credential
    implements Serializable
{

    @XmlAttribute(name = "userId", namespace = "http://gaards.cagrid.org/authentication", required = true)
    protected String userId;
    @XmlAttribute(name = "password", namespace = "http://gaards.cagrid.org/authentication", required = true)
    protected String password;

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
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

}
