
package org.cagrid.dorian.ifs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FederationAudit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FederationAudit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="IdPAdded"/>
 *     &lt;enumeration value="IdPUpdated"/>
 *     &lt;enumeration value="IdPRemoved"/>
 *     &lt;enumeration value="AdminAdded"/>
 *     &lt;enumeration value="AdminRemoved"/>
 *     &lt;enumeration value="CRLPublished"/>
 *     &lt;enumeration value="AccountCreated"/>
 *     &lt;enumeration value="AccountUpdated"/>
 *     &lt;enumeration value="AccountRemoved"/>
 *     &lt;enumeration value="SuccessfulUserCertificateRequest"/>
 *     &lt;enumeration value="InvalidUserCertificateRequest"/>
 *     &lt;enumeration value="HostCertificateRequested"/>
 *     &lt;enumeration value="HostCertificateApproved"/>
 *     &lt;enumeration value="HostCertificateUpdated"/>
 *     &lt;enumeration value="HostCertificateRenewed"/>
 *     &lt;enumeration value="UserCertificateUpdated"/>
 *     &lt;enumeration value="UserCertificateRemoved"/>
 *     &lt;enumeration value="AccessDenied"/>
 *     &lt;enumeration value="SystemStartup"/>
 *     &lt;enumeration value="InternalError"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FederationAudit")
@XmlEnum
public enum FederationAudit {

    @XmlEnumValue("IdPAdded")
    ID_P_ADDED("IdPAdded"),
    @XmlEnumValue("IdPUpdated")
    ID_P_UPDATED("IdPUpdated"),
    @XmlEnumValue("IdPRemoved")
    ID_P_REMOVED("IdPRemoved"),
    @XmlEnumValue("AdminAdded")
    ADMIN_ADDED("AdminAdded"),
    @XmlEnumValue("AdminRemoved")
    ADMIN_REMOVED("AdminRemoved"),
    @XmlEnumValue("CRLPublished")
    CRL_PUBLISHED("CRLPublished"),
    @XmlEnumValue("AccountCreated")
    ACCOUNT_CREATED("AccountCreated"),
    @XmlEnumValue("AccountUpdated")
    ACCOUNT_UPDATED("AccountUpdated"),
    @XmlEnumValue("AccountRemoved")
    ACCOUNT_REMOVED("AccountRemoved"),
    @XmlEnumValue("SuccessfulUserCertificateRequest")
    SUCCESSFUL_USER_CERTIFICATE_REQUEST("SuccessfulUserCertificateRequest"),
    @XmlEnumValue("InvalidUserCertificateRequest")
    INVALID_USER_CERTIFICATE_REQUEST("InvalidUserCertificateRequest"),
    @XmlEnumValue("HostCertificateRequested")
    HOST_CERTIFICATE_REQUESTED("HostCertificateRequested"),
    @XmlEnumValue("HostCertificateApproved")
    HOST_CERTIFICATE_APPROVED("HostCertificateApproved"),
    @XmlEnumValue("HostCertificateUpdated")
    HOST_CERTIFICATE_UPDATED("HostCertificateUpdated"),
    @XmlEnumValue("HostCertificateRenewed")
    HOST_CERTIFICATE_RENEWED("HostCertificateRenewed"),
    @XmlEnumValue("UserCertificateUpdated")
    USER_CERTIFICATE_UPDATED("UserCertificateUpdated"),
    @XmlEnumValue("UserCertificateRemoved")
    USER_CERTIFICATE_REMOVED("UserCertificateRemoved"),
    @XmlEnumValue("AccessDenied")
    ACCESS_DENIED("AccessDenied"),
    @XmlEnumValue("SystemStartup")
    SYSTEM_STARTUP("SystemStartup"),
    @XmlEnumValue("InternalError")
    INTERNAL_ERROR("InternalError");
    private final String value;

    FederationAudit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FederationAudit fromValue(String v) {
        for (FederationAudit c: FederationAudit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
