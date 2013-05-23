
package org.cagrid.cds.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExpirationStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExpirationStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Valid"/>
 *     &lt;enumeration value="Expired"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ExpirationStatus")
@XmlEnum
public enum ExpirationStatus {

    @XmlEnumValue("Valid")
    VALID("Valid"),
    @XmlEnumValue("Expired")
    EXPIRED("Expired");
    private final String value;

    ExpirationStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExpirationStatus fromValue(String v) {
        for (ExpirationStatus c: ExpirationStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
