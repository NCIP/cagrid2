
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.UserCertificateUpdate;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


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
 *         &lt;element name="update">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateUpdate"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "update"
})
@XmlRootElement(name = "UpdateUserCertificateRequest")
public class UpdateUserCertificateRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected UpdateUserCertificateRequest.Update update;

    /**
     * Gets the value of the update property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateUserCertificateRequest.Update }
     *     
     */
    public UpdateUserCertificateRequest.Update getUpdate() {
        return update;
    }

    /**
     * Sets the value of the update property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateUserCertificateRequest.Update }
     *     
     */
    public void setUpdate(UpdateUserCertificateRequest.Update value) {
        this.update = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            UpdateUserCertificateRequest.Update theUpdate;
            theUpdate = this.getUpdate();
            strategy.appendField(locator, this, "update", buffer, theUpdate);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            UpdateUserCertificateRequest.Update theUpdate;
            theUpdate = this.getUpdate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "update", theUpdate), currentHashCode, theUpdate);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof UpdateUserCertificateRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final UpdateUserCertificateRequest that = ((UpdateUserCertificateRequest) object);
        {
            UpdateUserCertificateRequest.Update lhsUpdate;
            lhsUpdate = this.getUpdate();
            UpdateUserCertificateRequest.Update rhsUpdate;
            rhsUpdate = that.getUpdate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "update", lhsUpdate), LocatorUtils.property(thatLocator, "update", rhsUpdate), lhsUpdate, rhsUpdate)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateUpdate"/>
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
        "userCertificateUpdate"
    })
    public static class Update
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "UserCertificateUpdate", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected UserCertificateUpdate userCertificateUpdate;

        /**
         * Gets the value of the userCertificateUpdate property.
         * 
         * @return
         *     possible object is
         *     {@link UserCertificateUpdate }
         *     
         */
        public UserCertificateUpdate getUserCertificateUpdate() {
            return userCertificateUpdate;
        }

        /**
         * Sets the value of the userCertificateUpdate property.
         * 
         * @param value
         *     allowed object is
         *     {@link UserCertificateUpdate }
         *     
         */
        public void setUserCertificateUpdate(UserCertificateUpdate value) {
            this.userCertificateUpdate = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                UserCertificateUpdate theUserCertificateUpdate;
                theUserCertificateUpdate = this.getUserCertificateUpdate();
                strategy.appendField(locator, this, "userCertificateUpdate", buffer, theUserCertificateUpdate);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                UserCertificateUpdate theUserCertificateUpdate;
                theUserCertificateUpdate = this.getUserCertificateUpdate();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userCertificateUpdate", theUserCertificateUpdate), currentHashCode, theUserCertificateUpdate);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof UpdateUserCertificateRequest.Update)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final UpdateUserCertificateRequest.Update that = ((UpdateUserCertificateRequest.Update) object);
            {
                UserCertificateUpdate lhsUserCertificateUpdate;
                lhsUserCertificateUpdate = this.getUserCertificateUpdate();
                UserCertificateUpdate rhsUserCertificateUpdate;
                rhsUserCertificateUpdate = that.getUserCertificateUpdate();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "userCertificateUpdate", lhsUserCertificateUpdate), LocatorUtils.property(thatLocator, "userCertificateUpdate", rhsUserCertificateUpdate), lhsUserCertificateUpdate, rhsUserCertificateUpdate)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }

}
