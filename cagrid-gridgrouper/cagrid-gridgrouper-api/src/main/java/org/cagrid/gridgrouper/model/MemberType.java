
package org.cagrid.gridgrouper.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MemberType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MemberType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GrouperGroup"/>
 *     &lt;enumeration value="Grid"/>
 *     &lt;enumeration value="Other"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MemberType")
@XmlEnum
public enum MemberType {

    @XmlEnumValue("GrouperGroup")
    GROUPER_GROUP("GrouperGroup"),
    @XmlEnumValue("Grid")
    GRID("Grid"),
    @XmlEnumValue("Other")
    OTHER("Other");
    private final String value;

    MemberType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MemberType fromValue(String v) {
        for (MemberType c: MemberType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
