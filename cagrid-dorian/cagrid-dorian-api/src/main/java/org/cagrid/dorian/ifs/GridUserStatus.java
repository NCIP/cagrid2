
package org.cagrid.dorian.ifs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GridUserStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GridUserStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Suspended"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Rejected"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GridUserStatus")
@XmlEnum
public enum GridUserStatus {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Suspended")
    SUSPENDED("Suspended"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Rejected")
    REJECTED("Rejected");
    private final String value;

    GridUserStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GridUserStatus fromValue(String v) {
        for (GridUserStatus c: GridUserStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
