
package org.cagrid.gridgrouper.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MemberFilter.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MemberFilter">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="ImmediateMembers"/>
 *     &lt;enumeration value="EffectiveMembers"/>
 *     &lt;enumeration value="CompositeMembers"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MemberFilter")
@XmlEnum
public enum MemberFilter {

    @XmlEnumValue("All")
    ALL("All"),
    @XmlEnumValue("ImmediateMembers")
    IMMEDIATE_MEMBERS("ImmediateMembers"),
    @XmlEnumValue("EffectiveMembers")
    EFFECTIVE_MEMBERS("EffectiveMembers"),
    @XmlEnumValue("CompositeMembers")
    COMPOSITE_MEMBERS("CompositeMembers");
    private final String value;

    MemberFilter(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MemberFilter fromValue(String v) {
        for (MemberFilter c: MemberFilter.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
