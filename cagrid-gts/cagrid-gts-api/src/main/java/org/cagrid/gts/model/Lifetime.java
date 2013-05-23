
package org.cagrid.gts.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Lifetime.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Lifetime">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Valid"/>
 *     &lt;enumeration value="Expired"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Lifetime")
@XmlEnum
public enum Lifetime {

    @XmlEnumValue("Valid")
    VALID("Valid"),
    @XmlEnumValue("Expired")
    EXPIRED("Expired");
    private final String value;

    Lifetime(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Lifetime fromValue(String v) {
        for (Lifetime c: Lifetime.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
