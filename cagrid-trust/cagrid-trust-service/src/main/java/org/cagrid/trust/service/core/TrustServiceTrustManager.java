package org.cagrid.trust.service.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by langella on 2/16/14.
 */
public class TrustServiceTrustManager implements X509TrustManager {

    protected Logger log;
    private RevocationEnabledTrustManager revocationEnabledTrustManager;
    private RevocationDisabledTrustManager revocationDisabledTrustManager;


    public TrustServiceTrustManager() {
        log = LoggerFactory.getLogger(this.getClass().getName());
        this.revocationEnabledTrustManager = new RevocationEnabledTrustManager();
        this.revocationDisabledTrustManager = new RevocationDisabledTrustManager();
        reload(new ArrayList<TrustedCAEntry>());
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (log.isDebugEnabled()) {
            log.debug("checkClientTrusted() - Chain [" + chainToString(chain) + "] Auth Type: " + authType + ".");
        }

        StringBuffer reasons = new StringBuffer();

        try {
            this.revocationEnabledTrustManager.checkClientTrusted(chain, authType);
            if (log.isDebugEnabled()) {
                log.debug("checkClientTrusted() - The chain [" + chainToString(chain) + " is trusted (" + this.revocationEnabledTrustManager.getClass().getName() + ").");
            }
            return;
        } catch (CertificateException e) {

            if (log.isDebugEnabled()) {
                log.debug("checkClientTrusted() - The chain [" + chainToString(chain) + " is NOT trusted (" + this.revocationEnabledTrustManager.getClass().getName() + ": " + e.getMessage(), e);
            }
            reasons.append("(1) " + e.getMessage() + "\n");
        }


        try {
            this.revocationDisabledTrustManager.checkClientTrusted(chain, authType);
            if (log.isDebugEnabled()) {
                log.debug("checkClientTrusted() - The chain [" + chainToString(chain) + " is trusted (" + this.revocationDisabledTrustManager.getClass().getName() + ").");
            }
            return;
        } catch (CertificateException e) {
            if (log.isDebugEnabled()) {
                log.debug("checkClientTrusted() - The chain [" + chainToString(chain) + " is NOT trusted (" + this.revocationDisabledTrustManager.getClass().getName() + ": " + e.getMessage(), e);
            }
            reasons.append("(2) " + e.getMessage() + "\n");
        }
        throw new CertificateException("The certificate chain  [" + chainToString(chain) + " could not be validated: \n" + reasons.toString());
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (log.isDebugEnabled()) {
            log.debug("checkServerTrusted() - Chain [" + chainToString(chain) + "] Auth Type: " + authType + ".");
        }

        StringBuffer reasons = new StringBuffer();

        try {
            this.revocationEnabledTrustManager.checkServerTrusted(chain, authType);
            if (log.isDebugEnabled()) {
                log.debug("checkServerTrusted() - The chain [" + chainToString(chain) + " is trusted (" + this.revocationEnabledTrustManager.getClass().getName() + ").");
            }
            return;
        } catch (CertificateException e) {

            if (log.isDebugEnabled()) {
                log.debug("checkServerTrusted() - The chain [" + chainToString(chain) + " is NOT trusted (" + this.revocationEnabledTrustManager.getClass().getName() + ": " + e.getMessage(), e);
            }
            reasons.append("(1) " + e.getMessage() + "\n");
        }


        try {
            this.revocationDisabledTrustManager.checkClientTrusted(chain, authType);
            if (log.isDebugEnabled()) {
                log.debug("checkServerTrusted() - The chain [" + chainToString(chain) + " is trusted (" + this.revocationDisabledTrustManager.getClass().getName() + ").");
            }
            return;
        } catch (CertificateException e) {
            if (log.isDebugEnabled()) {
                log.debug("checkServerTrusted() - The chain [" + chainToString(chain) + " is NOT trusted (" + this.revocationDisabledTrustManager.getClass().getName() + ": " + e.getMessage(), e);
            }
            reasons.append("(2) " + e.getMessage() + "\n");
        }
        throw new CertificateException("The certificate chain  [" + chainToString(chain) + " could not be validated: \n" + reasons.toString());
    }

    public X509Certificate[] getAcceptedIssuers() {
        List<X509Certificate> list = new ArrayList<X509Certificate>();

        for (X509Certificate cert : this.revocationEnabledTrustManager.getAcceptedIssuers()) {
            list.add(cert);
        }

        for (X509Certificate cert : this.revocationDisabledTrustManager.getAcceptedIssuers()) {
            list.add(cert);
        }

        return list.toArray(new X509Certificate[]{});
    }

    private String chainToString(X509Certificate[] chain) {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (X509Certificate cert : chain) {
            if (count > 0) {
                sb.append(", ");
            }
            sb.append(cert.getSubjectDN().getName());
            count = count + 1;
        }
        return sb.toString();
    }

    public void reload(List<TrustedCAEntry> trustedCAList) {

        List<TrustedCAEntry> revocationEnabled = new ArrayList<TrustedCAEntry>();
        List<TrustedCAEntry> revocationDisabled = new ArrayList<TrustedCAEntry>();
        for (TrustedCAEntry ca : trustedCAList) {
            if (ca.getCRL() == null) {
                revocationDisabled.add(ca);
            } else {
                revocationEnabled.add(ca);
            }
        }
        this.revocationEnabledTrustManager.reload(revocationEnabled);
        this.revocationDisabledTrustManager.reload(revocationDisabled);

        if (log.isInfoEnabled()) {
            StringBuffer msg = new StringBuffer("Successfully loaded the trust manager with the following certificates:\n");
            int count = 1;
            for (X509Certificate cert : this.revocationEnabledTrustManager.getAcceptedIssuers()) {
                msg.append("    (" + count + ") " + cert.getSubjectDN().getName() + " [CRL: YES]\n");
                count = count + 1;
            }

            for (X509Certificate cert : this.revocationDisabledTrustManager.getAcceptedIssuers()) {
                msg.append("    (" + count + ") " + cert.getSubjectDN().getName() + " [CRL: NO]\n");
                count = count + 1;
            }
            log.info(msg.toString());
        }

    }
}