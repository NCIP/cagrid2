package org.cagrid.gts.service.tools;

import java.io.File;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;

import com.ibm.wsdl.util.IOUtils;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class CreateHostCertificate {

	public static void main(String[] args) {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

			String cert = org.cagrid.core.commandline.IOUtils.readLine("Please enter the location of the CA's certificate", true);
			String key = org.cagrid.core.commandline.IOUtils.readLine("Please enter the location of the CA's private key", true);
			String cn = org.cagrid.core.commandline.IOUtils.readLine("Please enter the hostname of the host you are requesting a certificate for", true);

			PrivateKey cakey = KeyUtil.loadPrivateKey(new File(key), null);
			X509Certificate cacert = CertUtil.loadCertificate("BC", new File(cert));

			KeyPair pair = KeyUtil.generateRSAKeyPair1024("BC");
			String rootSub = cacert.getSubjectDN().toString();
			int index = rootSub.lastIndexOf(",");
			String subject = rootSub.substring(0, index) + ",CN=" + cn;
			GregorianCalendar date = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
			/* Allow for a five minute clock skew here. */
			date.add(Calendar.MINUTE, -5);
			Date start = new Date(date.getTimeInMillis());
			Date end = null;
			date.add(Calendar.YEAR, 10);
			Date d = new Date(date.getTimeInMillis());
			if (cacert.getNotAfter().before(d)) {
				throw new GeneralSecurityException("Cannot create a certificate that expires after issuing certificate.");
			}
			end = d;

			X509Certificate userCert = CertUtil.generateCertificate("BC", new X509Name(subject), start, end, pair.getPublic(), cacert, cakey, CertUtil.SHA2_SIGNATURE_ALGORITHM, null);
			System.out.println("Successfully created the host certificate:");
			System.out.println(userCert.getSubjectDN().toString());
			System.out.println("Host certificate issued by:");
			System.out.println(cacert.getSubjectDN().toString());
			System.out.println("Host certificate valid till:");
			System.out.println(userCert.getNotAfter());
			String certOut = org.cagrid.core.commandline.IOUtils.readLine("Please enter a location/filename to write the certificate to", true);
			String keyOut = org.cagrid.core.commandline.IOUtils.readLine("Please enter a location/filename to write the private key to", true);
			KeyUtil.writePrivateKey(pair.getPrivate(), new File(keyOut));
			CertUtil.writeCertificate(userCert, new File(certOut));
			System.out.println("Host private key written to:");
			System.out.println(keyOut);
			System.out.println("Host certificate written to:");
			System.out.println(certOut);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

}