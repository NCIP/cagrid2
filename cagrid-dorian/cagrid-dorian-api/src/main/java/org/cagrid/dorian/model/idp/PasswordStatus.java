
package org.cagrid.dorian.model.idp;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PasswordStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PasswordStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Valid"/>
 *     &lt;enumeration value="Locked"/>
 *     &lt;enumeration value="LockedUntilChanged"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PasswordStatus")
@XmlEnum
public enum PasswordStatus {

    @XmlEnumValue("Valid")
    VALID("Valid"),
    @XmlEnumValue("Locked")
    LOCKED("Locked"),
    @XmlEnumValue("LockedUntilChanged")
    LOCKED_UNTIL_CHANGED("LockedUntilChanged");
    private final String value;

    PasswordStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PasswordStatus fromValue(String v) {
        for (PasswordStatus c: PasswordStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
