package org.cagrid.dorian.service.ca;

import org.cagrid.dorian.common.Lifetime;
import org.cagrid.dorian.model.exceptions.DorianInternalException;


public class EracomCertificateAuthorityProperties extends CertificateAuthorityProperties {

    private int slot;


    public EracomCertificateAuthorityProperties(String certificateAuthorityPassword, int issuedCertificateKeySize,
        int slot) throws DorianInternalException {
        this(certificateAuthorityPassword, null, issuedCertificateKeySize, false, null, false, null, slot);
    }


    public EracomCertificateAuthorityProperties(String certificateAuthorityPassword, String policyOID,
        int issuedCertificateKeySize, int slot) throws DorianInternalException {
        this(certificateAuthorityPassword, policyOID, issuedCertificateKeySize, false, null, false, null, slot);
    }


    public EracomCertificateAuthorityProperties(String certificateAuthorityPassword, String policyOID,
        int issuedCertificateKeySize, boolean autoCreate, CertificateAuthorityCreationPolicy creationPolicy,
        boolean autoRenew, Lifetime renewalLifetime, int slot) throws DorianInternalException {
        super(certificateAuthorityPassword, policyOID, issuedCertificateKeySize, autoCreate, creationPolicy, autoRenew,
            renewalLifetime);
        this.slot = slot;
    }


    public int getSlot() {
        return slot;
    }

}
