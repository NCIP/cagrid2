
package org.cagrid.gridgrouper.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GroupCompositeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GroupCompositeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Union"/>
 *     &lt;enumeration value="Intersection"/>
 *     &lt;enumeration value="Complement"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GroupCompositeType")
@XmlEnum
public enum GroupCompositeType {

    @XmlEnumValue("Union")
    UNION("Union"),
    @XmlEnumValue("Intersection")
    INTERSECTION("Intersection"),
    @XmlEnumValue("Complement")
    COMPLEMENT("Complement");
    private final String value;

    GroupCompositeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GroupCompositeType fromValue(String v) {
        for (GroupCompositeType c: GroupCompositeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
