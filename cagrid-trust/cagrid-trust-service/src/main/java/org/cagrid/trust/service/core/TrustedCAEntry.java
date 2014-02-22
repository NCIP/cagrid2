package org.cagrid.trust.service.core;

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;

/**
 * Created by langella on 2/16/14.
 */
public class TrustedCAEntry{
   private  String dn = null;
   private X509Certificate certificate;
    private X509CRL crl;

    public TrustedCAEntry(String dn){
        this.dn = dn;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(X509Certificate certificate) {
        this.certificate = certificate;
    }

    public X509CRL getCRL() {
        return crl;
    }

    public void setCRL(X509CRL crl) {
        this.crl = crl;
    }
}
