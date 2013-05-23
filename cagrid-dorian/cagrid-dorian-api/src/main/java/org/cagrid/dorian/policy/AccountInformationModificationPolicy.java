
package org.cagrid.dorian.policy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountInformationModificationPolicy.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccountInformationModificationPolicy">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Admin"/>
 *     &lt;enumeration value="User"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccountInformationModificationPolicy")
@XmlEnum
public enum AccountInformationModificationPolicy {

    @XmlEnumValue("Admin")
    ADMIN("Admin"),
    @XmlEnumValue("User")
    USER("User");
    private final String value;

    AccountInformationModificationPolicy(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccountInformationModificationPolicy fromValue(String v) {
        for (AccountInformationModificationPolicy c: AccountInformationModificationPolicy.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
