package org.cagrid.trust.manager;

import java.io.File;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.cagrid.gaards.pki.CertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificateDirectoryTrustManager implements X509TrustManager {
	private final String trustedCertificatesDirectory;
	private X509TrustManager trustManager;
	protected Logger log;

	public CertificateDirectoryTrustManager(String trustedCertificatesDirectory) throws Exception {
		log = LoggerFactory.getLogger(this.getClass().getName());
		this.trustedCertificatesDirectory = trustedCertificatesDirectory;
		reloadTrustManager();
	}

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		trustManager.checkClientTrusted(chain, authType);
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		trustManager.checkServerTrusted(chain, authType);

	}

	public X509Certificate[] getAcceptedIssuers() {
		X509Certificate[] issuers = trustManager.getAcceptedIssuers();
		return issuers;
	}

	private void reloadTrustManager() {
		File dir = new File(this.trustedCertificatesDirectory);
		log.info("Reloading Trust Manager, loading certificates from the directory " + dir.getAbsolutePath());
		if (!dir.isDirectory()) {
			log.error("Cannot reload the trust manager, the specified directory (" + dir.getAbsolutePath() + ") is not a directory.");
		}

		List<X509Certificate> certs = new ArrayList<X509Certificate>();
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.isFile() && f.getAbsolutePath().endsWith(".pem")) {
				log.debug("Loading the certificate " + f.getAbsolutePath());
				try {
					X509Certificate cert = CertUtil.loadCertificate(f);
					log.debug("Successfully loaded the certificate " + cert.getSubjectDN().getName() + " from the certificate " + f.getAbsolutePath());
					certs.add(cert);
				} catch (Exception e) {
					log.error("Error loading the certificate " + f.getAbsolutePath() + ":", e);
				}
			} else {
				log.debug("Ignoring the file " + f.getAbsolutePath());
			}
		}

		try {
			// load keystore from specified cert store (or default)
			KeyStore ts = KeyStore.getInstance(KeyStore.getDefaultType());

			// add all temporary certs to KeyStore (ts)
			for (Certificate cert : certs) {
				ts.setCertificateEntry(UUID.randomUUID().toString(), cert);
			}

			// initialize a new TMF with the ts we just loaded
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ts);

			// acquire X509 trust manager from factory
			TrustManager tms[] = tmf.getTrustManagers();
			for (int i = 0; i < tms.length; i++) {
				if (tms[i] instanceof X509TrustManager) {
					trustManager = (X509TrustManager) tms[i];
					if (log.isInfoEnabled()) {
						StringBuffer msg = new StringBuffer("Successfully reloaded the trust manager with the following certificates:\n");
						int count = 1;
						for (X509Certificate cert : certs) {
							msg.append("    (" + count + ") " + cert.getSubjectDN().getName() + "\n");
							count = count + 1;
						}
						log.info(msg.toString());
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