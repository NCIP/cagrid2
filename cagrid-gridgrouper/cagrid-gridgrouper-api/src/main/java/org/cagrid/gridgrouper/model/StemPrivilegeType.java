
package org.cagrid.gridgrouper.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StemPrivilegeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StemPrivilegeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="create"/>
 *     &lt;enumeration value="stem"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StemPrivilegeType")
@XmlEnum
public enum StemPrivilegeType {

    @XmlEnumValue("create")
    CREATE("create"),
    @XmlEnumValue("stem")
    STEM("stem");
    private final String value;

    StemPrivilegeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StemPrivilegeType fromValue(String v) {
        for (StemPrivilegeType c: StemPrivilegeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
