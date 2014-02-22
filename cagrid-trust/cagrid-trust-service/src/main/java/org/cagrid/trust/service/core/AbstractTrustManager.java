package org.cagrid.trust.service.core;

import org.eclipse.jetty.http.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.*;
import java.util.*;

/**
 * Created by langella on 2/16/14.
 */
public abstract class AbstractTrustManager implements X509TrustManager {

    protected Logger log;
    private X509TrustManager trustManager;

    public AbstractTrustManager() {
        log = LoggerFactory.getLogger(this.getClass().getName());
        reload(new ArrayList<TrustedCAEntry>());
    }

    public abstract boolean isRevocationEnabled();

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        if (trustManager == null) {
            throw new CertificateException("Trust manager does not have any trust roots.");
        }

        try {
            trustManager.checkClientTrusted(chain, authType);

        } catch (CertificateException e) {

            throw e;

        }
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        if (trustManager == null) {
            throw new CertificateException("Trust manager does not have any trust roots.");
        }

        try {
            trustManager.checkServerTrusted(chain, authType);
        } catch (CertificateException e) {
            throw e;
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        if (trustManager != null) {
            return trustManager.getAcceptedIssuers();
        } else {
            return new X509Certificate[0];
        }
    }


    public void reload(List<TrustedCAEntry> trustedCAList) {
        this.trustManager = null;
        if ((trustedCAList != null) && (trustedCAList.size() > 0)) {

            List<X509Certificate> certs = new ArrayList<X509Certificate>();
            Set<CRL> crls = new HashSet<CRL>();

            for (TrustedCAEntry ca : trustedCAList) {
                certs.add(ca.getCertificate());
                if (ca.getCRL() != null) {
                    crls.add(ca.getCRL());
                }
            }

            try {
                // load keystore from specified cert store (or default)
                KeyStore ts = KeyStore.getInstance("jks");
                ts.load(null);

                // add all temporary certs to KeyStore (ts)
                for (Certificate cert : certs) {
                    ts.setCertificateEntry(UUID.randomUUID().toString(), cert);
                }

                PKIXBuilderParameters pbParams = new PKIXBuilderParameters(ts, new X509CertSelector());
                pbParams.setSigProvider("BC");

                // Set maximum certification path length
                pbParams.setMaxPathLength(-1);

                // Make sure revocation checking is enabled
                pbParams.setRevocationEnabled(isRevocationEnabled());

                if (crls != null && !crls.isEmpty()) {
                    pbParams.addCertStore(CertStore.getInstance("Collection", new CollectionCertStoreParameters(crls)));
                }

                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(SslContextFactory.DEFAULT_TRUSTMANAGERFACTORY_ALGORITHM);
                trustManagerFactory.init(new CertPathTrustManagerParameters(pbParams));

                // acquire X509 trust manager from factory
                TrustManager tms[] = trustManagerFactory.getTrustManagers();
                for (int i = 0; i < tms.length; i++) {
                    if (tms[i] instanceof X509TrustManager) {
                        trustManager = (X509TrustManager) tms[i];
                        if (log.isDebugEnabled()) {
                            StringBuffer msg = new StringBuffer("Successfully loaded the trust manager with the following certificates:\n");
                            int count = 1;
                            for (X509Certificate cert : certs) {
                                msg.append("    (" + count + ") " + cert.getSubjectDN().getName() + "\n");
                                count = count + 1;
                            }
                            log.debug(msg.toString());
                        }
                        return;
                    }
                }

                throw new NoSuchAlgorithmException("No X509TrustManager in TrustManagerFactory");
            } catch (Exception e) {
                log.error("An unexpected error occurred reloading the trust manager:", e);
            }
        }
    }
}