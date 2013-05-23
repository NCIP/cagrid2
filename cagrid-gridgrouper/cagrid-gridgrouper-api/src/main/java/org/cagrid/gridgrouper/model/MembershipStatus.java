
package org.cagrid.gridgrouper.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MembershipStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MembershipStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MEMBER_OF"/>
 *     &lt;enumeration value="NOT_MEMBER_OF"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MembershipStatus")
@XmlEnum
public enum MembershipStatus {

    MEMBER_OF,
    NOT_MEMBER_OF;

    public String value() {
        return name();
    }

    public static MembershipStatus fromValue(String v) {
        return valueOf(v);
    }

}
