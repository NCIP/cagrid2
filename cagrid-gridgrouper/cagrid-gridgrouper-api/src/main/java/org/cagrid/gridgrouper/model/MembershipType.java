
package org.cagrid.gridgrouper.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MembershipType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MembershipType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Any"/>
 *     &lt;enumeration value="ImmediateMembers"/>
 *     &lt;enumeration value="EffectiveMembers"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MembershipType")
@XmlEnum
public enum MembershipType {

    @XmlEnumValue("Any")
    ANY("Any"),
    @XmlEnumValue("ImmediateMembers")
    IMMEDIATE_MEMBERS("ImmediateMembers"),
    @XmlEnumValue("EffectiveMembers")
    EFFECTIVE_MEMBERS("EffectiveMembers");
    private final String value;

    MembershipType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MembershipType fromValue(String v) {
        for (MembershipType c: MembershipType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
