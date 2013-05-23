
package org.cagrid.dorian.idp;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocalUserRole.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LocalUserRole">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Administrator"/>
 *     &lt;enumeration value="Non_Administrator"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LocalUserRole")
@XmlEnum
public enum LocalUserRole {

    @XmlEnumValue("Administrator")
    ADMINISTRATOR("Administrator"),
    @XmlEnumValue("Non_Administrator")
    NON_ADMINISTRATOR("Non_Administrator");
    private final String value;

    LocalUserRole(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LocalUserRole fromValue(String v) {
        for (LocalUserRole c: LocalUserRole.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
