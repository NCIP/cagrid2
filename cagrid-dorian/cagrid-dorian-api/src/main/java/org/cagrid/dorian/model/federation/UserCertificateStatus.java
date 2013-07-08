
package org.cagrid.dorian.model.federation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserCertificateStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UserCertificateStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="Compromised"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UserCertificateStatus")
@XmlEnum
public enum UserCertificateStatus {

    OK("OK"),
    @XmlEnumValue("Compromised")
    COMPROMISED("Compromised");
    private final String value;

    UserCertificateStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UserCertificateStatus fromValue(String v) {
        for (UserCertificateStatus c: UserCertificateStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
