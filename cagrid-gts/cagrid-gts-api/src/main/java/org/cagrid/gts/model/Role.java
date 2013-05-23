
package org.cagrid.gts.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Role.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Role">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TrustServiceAdmin"/>
 *     &lt;enumeration value="TrustAuthorityManager"/>
 *     &lt;enumeration value="User"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Role")
@XmlEnum
public enum Role {

    @XmlEnumValue("TrustServiceAdmin")
    TRUST_SERVICE_ADMIN("TrustServiceAdmin"),
    @XmlEnumValue("TrustAuthorityManager")
    TRUST_AUTHORITY_MANAGER("TrustAuthorityManager"),
    @XmlEnumValue("User")
    USER("User");
    private final String value;

    Role(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Role fromValue(String v) {
        for (Role c: Role.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
