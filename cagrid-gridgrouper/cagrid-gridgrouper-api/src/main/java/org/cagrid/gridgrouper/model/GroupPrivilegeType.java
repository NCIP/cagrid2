
package org.cagrid.gridgrouper.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GroupPrivilegeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GroupPrivilegeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="admin"/>
 *     &lt;enumeration value="optin"/>
 *     &lt;enumeration value="optout"/>
 *     &lt;enumeration value="read"/>
 *     &lt;enumeration value="update"/>
 *     &lt;enumeration value="view"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GroupPrivilegeType")
@XmlEnum
public enum GroupPrivilegeType {

    @XmlEnumValue("admin")
    ADMIN("admin"),
    @XmlEnumValue("optin")
    OPTIN("optin"),
    @XmlEnumValue("optout")
    OPTOUT("optout"),
    @XmlEnumValue("read")
    READ("read"),
    @XmlEnumValue("update")
    UPDATE("update"),
    @XmlEnumValue("view")
    VIEW("view");
    private final String value;

    GroupPrivilegeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GroupPrivilegeType fromValue(String v) {
        for (GroupPrivilegeType c: GroupPrivilegeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
