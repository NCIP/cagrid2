package org.cagrid.trust.service.core;

import org.cagrid.gaards.pki.CertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by langella on 2/16/14.
 */
public class FileSystemTrustedCAManager implements TrustedCAManager {

    private final String trustedCertificatesDirectory;
    protected Logger log;

    public FileSystemTrustedCAManager(String trustedCertificatesDirectory) {
        log = LoggerFactory.getLogger(this.getClass().getName());
        this.trustedCertificatesDirectory = trustedCertificatesDirectory;
    }


    @Override
    public List<TrustedCAEntry> getTrustedCertificateAuthorities() {

        List<TrustedCAEntry> entries = new ArrayList<TrustedCAEntry>();

        File dir = new File(this.trustedCertificatesDirectory);
        log.info("Reloading Trust Manager, loading certificates from the directory " + dir.getAbsolutePath());
        if (!dir.isDirectory()) {
            log.error("Cannot reload the trust manager, the specified directory (" + dir.getAbsolutePath() + ") is not a directory.");
            return entries;
        }

        Map<String, TrustedCAEntry> map = new HashMap<String, TrustedCAEntry>();

        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile() && f.getAbsolutePath().endsWith(".cert")) {
                    log.debug("Loading the certificate " + f.getAbsolutePath());
                    try {
                        X509Certificate cert = CertUtil.loadCertificate(f);
                        log.debug("Successfully loaded the certificate " + cert.getSubjectDN().getName() + " from the file " + f.getAbsolutePath());
                        getEntry(map, cert.getSubjectDN().getName()).setCertificate(cert);

                    } catch (Exception e) {
                        log.error("Error loading the certificate " + f.getAbsolutePath() + ":", e);
                    }
                } else if (f.isFile() && f.getAbsolutePath().endsWith(".crl")) {
                    log.debug("Loading the crl " + f.getAbsolutePath());
                    try {
                        X509CRL crl = CertUtil.loadCRL(f);

                        log.debug("Successfully the CRL for the CA " + crl.getIssuerDN().getName() + " from the file " + f.getAbsolutePath());
                        getEntry(map, crl.getIssuerDN().getName()).setCRL(crl);
                    } catch (Exception e) {
                        log.error("Error loading the CRL " + f.getAbsolutePath() + ":", e);
                    }
                } else {
                    log.debug("Ignoring the file " + f.getAbsolutePath());
                }
            }
        }
        for (TrustedCAEntry entry : map.values()) {
            entries.add(entry);
        }
        return entries;
    }

    private TrustedCAEntry getEntry(Map<String, TrustedCAEntry> map, String dn) {
        TrustedCAEntry ca = map.get(dn);
        if (ca == null) {
            ca = new TrustedCAEntry(dn);
            map.put(dn, ca);
        }
        return ca;
    }
}