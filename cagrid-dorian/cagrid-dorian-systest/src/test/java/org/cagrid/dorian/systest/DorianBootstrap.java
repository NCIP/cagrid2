package org.cagrid.dorian.systest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.cagrid.dorian.ca.impl.CertificateAuthorityProperties;
import org.cagrid.dorian.ifs.HostCertificateRecord;
import org.cagrid.dorian.ifs.HostCertificateRequest;
import org.cagrid.dorian.ifs.PublicKey;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.systest.ContextLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;

public class DorianBootstrap {

	public final static String DORIAN_CONFIGURATION_PATH = "classpath*:META-INF/spring/dorian-configuration.xml";
	public final static String TRUST_ALIAS = "dorianCA";
	public final static String KEY_ALIAS = "host";
	public final static char[] STORE_PASSWORD = "changeit".toCharArray();

	private final static Logger logger = LoggerFactory
			.getLogger(DorianBootstrap.class);

	private final AbstractApplicationContext dorianContext;
	private final Dorian dorian;
	private final String gridId = "/C=US/O=abc/OU=xyz/OU=caGrid/OU=Dorian/CN=dorian";

	public DorianBootstrap() throws IOException {
		dorianContext = ContextLoader.loadContext("Dorian",
				DORIAN_CONFIGURATION_PATH);
		dorian = dorianContext.getBean(Dorian.class);
	}

	public void createKeyAndTrustStores() throws Exception {
		File karafBase = ContextLoader.getKarafBase();
		File karafEtc = new File(karafBase, "etc");
		if (!karafEtc.exists() || !karafEtc.isDirectory()) {
			logger.warn("No " + ContextLoader.KARAF_BASE_KEY
					+ " etc directory " + karafEtc.getAbsolutePath());
		}

		X509Certificate certificate = dorian.getCACertificate();
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStore.load(null, null);
		trustStore.setCertificateEntry(TRUST_ALIAS, certificate);

		LdapName caDN = new LdapName(certificate.getSubjectX500Principal()
				.getName());
		List<Rdn> baseRDNs = new LinkedList<Rdn>(caDN.getRdns());
		for (Iterator<Rdn> rdnIter = baseRDNs.iterator(); rdnIter.hasNext();) {
			Rdn rdn = rdnIter.next();
			if ("CN".equals(rdn.getType())) {
				rdnIter.remove();
			}
		}
		String baseDN = new LdapName(baseRDNs).toString();

		String hostName = InetAddress.getLocalHost().getHostName();

		CertificateAuthorityProperties caProperties = dorianContext.getBean(
				"caProperties", CertificateAuthorityProperties.class);
		int keySize = caProperties.getIssuedCertificateKeySize();

		for (File dir : karafEtc.listFiles()) {
			if (!dir.isDirectory())
				continue;
			String cn = dir.getName() + '@' + hostName;
			String dn = baseDN + ",CN=" + cn;

			KeyPair pair = KeyUtil.generateRSAKeyPair(keySize);
			HostCertificateRequest req = new HostCertificateRequest();
			req.setHostname(dn);
			PublicKey publicKey = new PublicKey();
			publicKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
			req.setPublicKey(publicKey);
			HostCertificateRecord record = dorian.requestHostCertificate(
					gridId, req);
			org.cagrid.dorian.common.X509Certificate cert = record
					.getCertificate();
			X509Certificate hostCertificate = CertUtil.loadCertificate(cert
					.getCertificateAsString());

			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(null, null);
			keyStore.setKeyEntry(KEY_ALIAS, pair.getPrivate(), STORE_PASSWORD,
					new Certificate[] { hostCertificate });

			File trustStoreFile = new File(dir, "truststore.jks");
			OutputStream trustStoreStream = new FileOutputStream(trustStoreFile);
			trustStore.store(trustStoreStream, STORE_PASSWORD);
			trustStoreStream.close();

			File keyStoreFile = new File(dir, "keystore.jks");
			OutputStream keyStoreStream = new FileOutputStream(keyStoreFile);
			keyStore.store(keyStoreStream, STORE_PASSWORD);
			keyStoreStream.close();
		}
	}

	public void close() {
		dorianContext.close();
	}

	public static void main(String[] args) throws Exception {
		DorianBootstrap dorianBootstrap = new DorianBootstrap();

		System.out.println(dorianBootstrap.gridId);
		dorianBootstrap.createKeyAndTrustStores();

		dorianBootstrap.close();
	}
}
