package org.cagrid.dorian.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Properties;

import org.cagrid.core.commandline.BaseCommandLine;
import org.cagrid.dorian.service.Dorian;
import org.globus.gsi.CertUtil;

public class CreateTrustStore extends BaseCommandLine {

	private final static String PROPERTIES_FILE = "src/main/resources/host-certificate-request.properties";
	private final static String ALIAS = "dorianca";
	private final static char[] STORE_PASSWORD = "changeit".toCharArray();
	private final static String TRUSTSTORE_PATH = "dorian-truststore.jks";

	public CreateTrustStore(File propertiesFile) {
		super(propertiesFile);
	}

	public CreateTrustStore(Properties properties) {
		super(properties);
	}

	@Override
	public void execute() throws Exception {

		SpringUtils utils = new SpringUtils();
		Dorian dorian = utils.getDorian();

		X509Certificate certificate = dorian.getCACertificate();

		KeyStore trustStore = KeyStore.getInstance("JKS");
		trustStore.load(null, null);
		trustStore.setCertificateEntry(ALIAS, certificate);

		FileOutputStream truststoreStream = new FileOutputStream(
				TRUSTSTORE_PATH);
		trustStore.store(truststoreStream, STORE_PASSWORD);
		truststoreStream.close();
		System.out.println("Trust store written to " + TRUSTSTORE_PATH);

		String certFile = certificate.getSerialNumber().toString() + ".pem";
		FileOutputStream certStream = new FileOutputStream(certFile);
		CertUtil.writeCertificate(certStream, certificate);
		certStream.close();
		System.out.println("Certificate written to " + certFile);
	}

	public static void main(String[] args) throws Exception {
		CreateTrustStore main = new CreateTrustStore(new File(PROPERTIES_FILE));
		main.execute();
	}
}
