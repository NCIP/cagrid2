
package org.cagrid.gridgrouper.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MembershipRequestStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MembershipRequestStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Approved"/>
 *     &lt;enumeration value="Rejected"/>
 *     &lt;enumeration value="Removed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MembershipRequestStatus")
@XmlEnum
public enum MembershipRequestStatus {

    @XmlEnumValue("All")
    All("All"),
    @XmlEnumValue("Pending")
    Pending("Pending"),
    @XmlEnumValue("Approved")
    Approved("Approved"),
    @XmlEnumValue("Rejected")
    Rejected("Rejected"),
    @XmlEnumValue("Removed")
    Removed("Removed");
    private final String value;

    MembershipRequestStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MembershipRequestStatus fromValue(String v) {
        for (MembershipRequestStatus c: MembershipRequestStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
