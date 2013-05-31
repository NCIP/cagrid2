package org.cagrid.core.common.security;

import java.io.Serializable;
import java.security.cert.X509Certificate;


public class X509CertificateChain implements Serializable {

    private X509Certificate[] certificateChain;


    public X509CertificateChain(X509Certificate[] chain) {
        this.certificateChain = chain;
    }


    public X509Certificate getIdentityCertificate() {
        if ((this.certificateChain != null) && (this.certificateChain.length > 0)) {
            return certificateChain[0];
        } else {
            return null;
        }
    }


    public X509Certificate[] getCertificateChain() {
        return certificateChain;
    }

}
