
package org.cagrid.dorian.model.federation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrustedIdPStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TrustedIdPStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Suspended"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TrustedIdPStatus")
@XmlEnum
public enum TrustedIdPStatus {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Suspended")
    SUSPENDED("Suspended");
    private final String value;

    TrustedIdPStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TrustedIdPStatus fromValue(String v) {
        for (TrustedIdPStatus c: TrustedIdPStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
