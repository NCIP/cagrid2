
package org.cagrid.dorian.policy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchPolicyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SearchPolicyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Admin"/>
 *     &lt;enumeration value="Authenticated"/>
 *     &lt;enumeration value="Public"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SearchPolicyType")
@XmlEnum
public enum SearchPolicyType {

    @XmlEnumValue("Admin")
    ADMIN("Admin"),
    @XmlEnumValue("Authenticated")
    AUTHENTICATED("Authenticated"),
    @XmlEnumValue("Public")
    PUBLIC("Public");
    private final String value;

    SearchPolicyType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchPolicyType fromValue(String v) {
        for (SearchPolicyType c: SearchPolicyType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
