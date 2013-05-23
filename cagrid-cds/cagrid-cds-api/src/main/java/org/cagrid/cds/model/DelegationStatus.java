
package org.cagrid.cds.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegationStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DelegationStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Approved"/>
 *     &lt;enumeration value="Suspended"/>
 *     &lt;enumeration value="Pending"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DelegationStatus")
@XmlEnum
public enum DelegationStatus {

    @XmlEnumValue("Approved")
    APPROVED("Approved"),
    @XmlEnumValue("Suspended")
    SUSPENDED("Suspended"),
    @XmlEnumValue("Pending")
    PENDING("Pending");
    private final String value;

    DelegationStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DelegationStatus fromValue(String v) {
        for (DelegationStatus c: DelegationStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
