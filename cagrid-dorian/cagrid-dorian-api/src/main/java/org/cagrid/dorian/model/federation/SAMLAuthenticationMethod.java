
package org.cagrid.dorian.model.federation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SAMLAuthenticationMethod.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SAMLAuthenticationMethod">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="urn:oasis:names:tc:SAML:1.0:am:password"/>
 *     &lt;enumeration value="urn:ietf:rfc:1510"/>
 *     &lt;enumeration value="urn:ietf:rfc:2945"/>
 *     &lt;enumeration value="urn:oasis:names:tc:SAML:1.0:am:HardwareToken"/>
 *     &lt;enumeration value="urn:ietf:rfc:2246"/>
 *     &lt;enumeration value="urn:oasis:names:tc:SAML:1.0:am:X509-PKI"/>
 *     &lt;enumeration value="urn:oasis:names:tc:SAML:1.0:am:PGP"/>
 *     &lt;enumeration value="urn:oasis:names:tc:SAML:1.0:am:SPKI"/>
 *     &lt;enumeration value="urn:oasis:names:tc:SAML:1.0:am:XKMS"/>
 *     &lt;enumeration value="urn:ietf:rfc:3075"/>
 *     &lt;enumeration value="urn:oasis:names:tc:SAML:1.0:am:unspecified"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SAMLAuthenticationMethod")
@XmlEnum
public enum SAMLAuthenticationMethod {

    @XmlEnumValue("urn:oasis:names:tc:SAML:1.0:am:password")
    URN_OASIS_NAMES_TC_SAML_1_0_AM_PASSWORD("urn:oasis:names:tc:SAML:1.0:am:password"),
    @XmlEnumValue("urn:ietf:rfc:1510")
    URN_IETF_RFC_1510("urn:ietf:rfc:1510"),
    @XmlEnumValue("urn:ietf:rfc:2945")
    URN_IETF_RFC_2945("urn:ietf:rfc:2945"),
    @XmlEnumValue("urn:oasis:names:tc:SAML:1.0:am:HardwareToken")
    URN_OASIS_NAMES_TC_SAML_1_0_AM_HARDWARE_TOKEN("urn:oasis:names:tc:SAML:1.0:am:HardwareToken"),
    @XmlEnumValue("urn:ietf:rfc:2246")
    URN_IETF_RFC_2246("urn:ietf:rfc:2246"),
    @XmlEnumValue("urn:oasis:names:tc:SAML:1.0:am:X509-PKI")
    URN_OASIS_NAMES_TC_SAML_1_0_AM_X_509_PKI("urn:oasis:names:tc:SAML:1.0:am:X509-PKI"),
    @XmlEnumValue("urn:oasis:names:tc:SAML:1.0:am:PGP")
    URN_OASIS_NAMES_TC_SAML_1_0_AM_PGP("urn:oasis:names:tc:SAML:1.0:am:PGP"),
    @XmlEnumValue("urn:oasis:names:tc:SAML:1.0:am:SPKI")
    URN_OASIS_NAMES_TC_SAML_1_0_AM_SPKI("urn:oasis:names:tc:SAML:1.0:am:SPKI"),
    @XmlEnumValue("urn:oasis:names:tc:SAML:1.0:am:XKMS")
    URN_OASIS_NAMES_TC_SAML_1_0_AM_XKMS("urn:oasis:names:tc:SAML:1.0:am:XKMS"),
    @XmlEnumValue("urn:ietf:rfc:3075")
    URN_IETF_RFC_3075("urn:ietf:rfc:3075"),
    @XmlEnumValue("urn:oasis:names:tc:SAML:1.0:am:unspecified")
    URN_OASIS_NAMES_TC_SAML_1_0_AM_UNSPECIFIED("urn:oasis:names:tc:SAML:1.0:am:unspecified");
    private final String value;

    SAMLAuthenticationMethod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SAMLAuthenticationMethod fromValue(String v) {
        for (SAMLAuthenticationMethod c: SAMLAuthenticationMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
