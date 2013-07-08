
package org.cagrid.dorian.model.federation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HostCertificateStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HostCertificateStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Suspended"/>
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Rejected"/>
 *     &lt;enumeration value="Compromised"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HostCertificateStatus")
@XmlEnum
public enum HostCertificateStatus {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Suspended")
    SUSPENDED("Suspended"),
    @XmlEnumValue("Pending")
    PENDING("Pending"),
    @XmlEnumValue("Rejected")
    REJECTED("Rejected"),
    @XmlEnumValue("Compromised")
    COMPROMISED("Compromised");
    private final String value;

    HostCertificateStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HostCertificateStatus fromValue(String v) {
        for (HostCertificateStatus c: HostCertificateStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
