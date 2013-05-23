
package org.cagrid.cds.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegatedCredentialEvent.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DelegatedCredentialEvent">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DelegationInitiated"/>
 *     &lt;enumeration value="DelegationApproved"/>
 *     &lt;enumeration value="DelegationStatusUpdated"/>
 *     &lt;enumeration value="DelegatedCredentialIssued"/>
 *     &lt;enumeration value="DelegatedCredentialAccessDenied"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DelegatedCredentialEvent")
@XmlEnum
public enum DelegatedCredentialEvent {

    @XmlEnumValue("DelegationInitiated")
    DELEGATION_INITIATED("DelegationInitiated"),
    @XmlEnumValue("DelegationApproved")
    DELEGATION_APPROVED("DelegationApproved"),
    @XmlEnumValue("DelegationStatusUpdated")
    DELEGATION_STATUS_UPDATED("DelegationStatusUpdated"),
    @XmlEnumValue("DelegatedCredentialIssued")
    DELEGATED_CREDENTIAL_ISSUED("DelegatedCredentialIssued"),
    @XmlEnumValue("DelegatedCredentialAccessDenied")
    DELEGATED_CREDENTIAL_ACCESS_DENIED("DelegatedCredentialAccessDenied");
    private final String value;

    DelegatedCredentialEvent(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DelegatedCredentialEvent fromValue(String v) {
        for (DelegatedCredentialEvent c: DelegatedCredentialEvent.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
