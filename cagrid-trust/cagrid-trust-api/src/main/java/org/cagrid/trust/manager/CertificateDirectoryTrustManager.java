package org.cagrid.trust.manager;

import java.io.File;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CRL;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.net.ssl.CertPathTrustManagerParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.cagrid.gaards.pki.CertUtil;
import org.eclipse.jetty.http.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificateDirectoryTrustManager implements X509TrustManager {
	private final String trustedCertificatesDirectory;
	private X509TrustManager trustManager;
	protected Logger log;

	public CertificateDirectoryTrustManager(String trustedCertificatesDirectory) {
		log = LoggerFactory.getLogger(this.getClass().getName());
		this.trustedCertificatesDirectory = trustedCertificatesDirectory;
		reloadTrustManager();
	}

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		if (log.isDebugEnabled()) {
			log.debug("checkClientTrusted() - Chain [" + chainToString(chain) + "] Auth Type: " + authType + ".");
		}
		reloadTrustManager();
		try {
			trustManager.checkClientTrusted(chain, authType);
			if (log.isDebugEnabled()) {
				log.debug("checkClientTrusted() - The chain [" + chainToString(chain) + " is trusted.");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("checkClientTrusted() - The chain [" + chainToString(chain) + " is NOT trusted: " + e.getMessage(), e);
			}

		}
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		if (log.isDebugEnabled()) {
			log.debug("checkServerTrusted() - Chain [" + chainToString(chain) + "] Auth Type: " + authType + ".");
		}
		reloadTrustManager();
		try {
			trustManager.checkServerTrusted(chain, authType);
			if (log.isDebugEnabled()) {
				log.debug("checkServerTrusted() - The chain [" + chainToString(chain) + " is trusted.");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("checkServerTrusted() - The chain [" + chainToString(chain) + " is NOT trusted: " + e.getMessage(), e);
			}
		}
	}

	public X509Certificate[] getAcceptedIssuers() {
		X509Certificate[] issuers = trustManager.getAcceptedIssuers();
		return issuers;
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

	private void reloadTrustManager() {
		File dir = new File(this.trustedCertificatesDirectory);
		log.info("Reloading Trust Manager, loading certificates from the directory " + dir.getAbsolutePath());
		if (!dir.isDirectory()) {
			log.error("Cannot reload the trust manager, the specified directory (" + dir.getAbsolutePath() + ") is not a directory.");
			return;
		}

		List<X509Certificate> certs = new ArrayList<X509Certificate>();
		Set<CRL> crls = new HashSet<CRL>();
		File[] files = dir.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isFile() && f.getAbsolutePath().endsWith(".cert")) {
					log.debug("Loading the certificate " + f.getAbsolutePath());
					try {
						X509Certificate cert = CertUtil.loadCertificate(f);
						log.debug("Successfully loaded the certificate " + cert.getSubjectDN().getName() + " from the file " + f.getAbsolutePath());
						certs.add(cert);
					} catch (Exception e) {
						log.error("Error loading the certificate " + f.getAbsolutePath() + ":", e);
					}
				} else if (f.isFile() && f.getAbsolutePath().endsWith(".crl")) {
					log.debug("Loading the crl " + f.getAbsolutePath());
					try {
						X509CRL crl = CertUtil.loadCRL(f);
						log.debug("Successfully the CRL for the CA " + crl.getIssuerDN().getName() + " from the file " + f.getAbsolutePath());
						crls.add(crl);
					} catch (Exception e) {
						log.error("Error loading the CRL " + f.getAbsolutePath() + ":", e);
					}
				} else {
					log.debug("Ignoring the file " + f.getAbsolutePath());
				}
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
			pbParams.setRevocationEnabled(true);

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

	public static void main(String[] args) {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			//X509Certificate cert = CertUtil.loadCertificate(new File("/Users/langella/Desktop/fuji.cert"));
			X509Certificate cert = CertUtil.loadCertificate(new File("/Users/langella/Documents/caGrid/environments/Training/training-grid-configuration/Services/dorian.training.cagrid.org/dorian.training.cagrid.org-cert.pem"));
			CertificateDirectoryTrustManager tm = new CertificateDirectoryTrustManager("/Users/langella/Desktop/certificates");
			X509Certificate[] chain = new X509Certificate[1];
			chain[0] = cert;
			tm.checkClientTrusted(chain, "RSA");
			tm.checkServerTrusted(chain, "RSA");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}