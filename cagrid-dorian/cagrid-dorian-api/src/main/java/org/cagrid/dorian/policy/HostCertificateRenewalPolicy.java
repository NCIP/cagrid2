
package org.cagrid.dorian.policy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HostCertificateRenewalPolicy.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HostCertificateRenewalPolicy">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Admin"/>
 *     &lt;enumeration value="Owner"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HostCertificateRenewalPolicy")
@XmlEnum
public enum HostCertificateRenewalPolicy {

    @XmlEnumValue("Admin")
    ADMIN("Admin"),
    @XmlEnumValue("Owner")
    OWNER("Owner");
    private final String value;

    HostCertificateRenewalPolicy(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HostCertificateRenewalPolicy fromValue(String v) {
        for (HostCertificateRenewalPolicy c: HostCertificateRenewalPolicy.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
