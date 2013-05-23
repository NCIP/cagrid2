
package org.cagrid.dorian.idp;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdentityProviderAudit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IdentityProviderAudit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Registration"/>
 *     &lt;enumeration value="LocalAccountUpdated"/>
 *     &lt;enumeration value="LocalAccountRemoved"/>
 *     &lt;enumeration value="LocalAccountLocked"/>
 *     &lt;enumeration value="PasswordChanged"/>
 *     &lt;enumeration value="SuccessfulLogin"/>
 *     &lt;enumeration value="InvalidLogin"/>
 *     &lt;enumeration value="LocalAccessDenied"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IdentityProviderAudit")
@XmlEnum
public enum IdentityProviderAudit {

    @XmlEnumValue("Registration")
    REGISTRATION("Registration"),
    @XmlEnumValue("LocalAccountUpdated")
    LOCAL_ACCOUNT_UPDATED("LocalAccountUpdated"),
    @XmlEnumValue("LocalAccountRemoved")
    LOCAL_ACCOUNT_REMOVED("LocalAccountRemoved"),
    @XmlEnumValue("LocalAccountLocked")
    LOCAL_ACCOUNT_LOCKED("LocalAccountLocked"),
    @XmlEnumValue("PasswordChanged")
    PASSWORD_CHANGED("PasswordChanged"),
    @XmlEnumValue("SuccessfulLogin")
    SUCCESSFUL_LOGIN("SuccessfulLogin"),
    @XmlEnumValue("InvalidLogin")
    INVALID_LOGIN("InvalidLogin"),
    @XmlEnumValue("LocalAccessDenied")
    LOCAL_ACCESS_DENIED("LocalAccessDenied");
    private final String value;

    IdentityProviderAudit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdentityProviderAudit fromValue(String v) {
        for (IdentityProviderAudit c: IdentityProviderAudit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
