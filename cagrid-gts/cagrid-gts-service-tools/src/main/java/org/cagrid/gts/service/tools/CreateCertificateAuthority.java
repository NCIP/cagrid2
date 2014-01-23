package org.cagrid.gts.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bouncycastle.asn1.x509.X509Name;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class CreateCertificateAuthority {

	public static void main(String[] args) {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

			String subject = org.cagrid.core.commandline.IOUtils.readLine("Please enter the subject for the certificate authority", true);
			String syears = org.cagrid.core.commandline.IOUtils.readLine("Please enter the number of years the certificate authority will be valid for.", true);
			int years = Integer.valueOf(syears);

			KeyPair pair = KeyUtil.generateRSAKeyPair2048("BC");

			GregorianCalendar date = new GregorianCalendar();
			Date start = new Date(date.getTimeInMillis());
			Date end = null;
			date.add(Calendar.YEAR, years);

			end = date.getTime();

			X509Certificate caCert = CertUtil.generateCACertificate("BC", new X509Name(subject), start, end, pair, CertUtil.SHA2_SIGNATURE_ALGORITHM);
			System.out.println("Successfully created the CA certificate:");
			System.out.println(caCert.getSubjectDN().toString());
			System.out.println("CA certificate valid till:");
			System.out.println(caCert.getNotAfter());
			String certOut = org.cagrid.core.commandline.IOUtils.readLine("Please enter a location/filename to write the certificate to", true);
			String keyOut = org.cagrid.core.commandline.IOUtils.readLine("Please enter a location/filename to write the private key to", true);
			KeyUtil.writePrivateKey(pair.getPrivate(), new File(keyOut));
			System.out.println("CA private key written to:");
			System.out.println(keyOut);
			CertUtil.writeCertificate(caCert, new File(certOut));
			System.out.println("CA certificate written to:");
			System.out.println(certOut);
			String truststorePath = org.cagrid.core.commandline.IOUtils.readLine("Please enter a location/filename to write the truststore to", true);
			String truststorePassword = org.cagrid.core.commandline.IOUtils.readLine("Please enter a password for the truststore", true);
			File f = new File(truststorePath);

			KeyStore keyStore = KeyStore.getInstance("jks");

			keyStore.load(null);
			keyStore.setEntry("trustedca", new KeyStore.TrustedCertificateEntry(caCert), null);

			FileOutputStream fos = new FileOutputStream(f);
			keyStore.store(fos, truststorePassword.toCharArray());
			fos.close();

			System.out.println("Truststore created for the CA " + caCert.getSubjectDN().getName() + " at " + f.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

}