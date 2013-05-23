package org.cagrid.gaards.credentials;

import java.io.File;
import java.io.StringReader;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.io.FileUtils;
import org.cagrid.core.common.JAXBUtils;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.globus.gsi.GlobusCredential;

public class EncodingUtil {

	public static final String CLIENT_WSDD = "client-config.wsdd";
	public static final QName X509_CREDENTIAL = new QName(
			"http://gaards.cagrid.org/credentials", "X509CredentialDescriptor");
	public static final QName DORIAN_USER_CREDENTIAL = new QName(
			"http://gaards.cagrid.org/credentials",
			"DorianUserCredentialDescriptor");

	public static void serialize(File f, X509CredentialDescriptor des)
			throws Exception {
		String str = serialize(des);
		FileUtils.writeStringToFile(f, str);
	}

	public static String serialize(X509CredentialDescriptor des)
			throws Exception {
		if (des instanceof DorianUserCredentialDescriptor) {
			return serialize(DORIAN_USER_CREDENTIAL, des);
		} else {
			return serialize(X509_CREDENTIAL, des);
		}
	}

	public static X509CredentialDescriptor deserialize(File f) throws Exception {
		String str = FileUtils.readFileToString(f);
		return deserialize(str);
	}

	public static X509CredentialDescriptor encode(GlobusCredential cred)
			throws Exception {
		return encode(cred, new X509CredentialDescriptor());
	}

	public static X509CredentialDescriptor encode(GlobusCredential cred,
			X509CredentialDescriptor des) throws Exception {
		des.setIdentity(cred.getIdentity());
		EncodedCertificates list = new EncodedCertificates();
		X509Certificate[] chain = cred.getCertificateChain();
		if (chain != null) {
			List<String> certs = new ArrayList<String>(chain.length);
			for (int i = 0; i < chain.length; i++) {
				certs.add(CertUtil.writeCertificate(chain[i]));
			}
			list.getEncodedCertificate().addAll(certs);
		}
		des.setEncodedCertificates(list);
		des.setEncodedKey(KeyUtil.writePrivateKey(cred.getPrivateKey(),
				(String) null));
		return des;
	}

	public static X509CredentialDescriptor deserialize(String s)
			throws Exception {
		if (s.indexOf(DORIAN_USER_CREDENTIAL.getLocalPart()) != -1) {
			return JAXBUtils.unmarshal(DorianUserCredentialDescriptor.class,
					new StringReader(s));
		} else {
			return JAXBUtils.unmarshal(X509CredentialDescriptor.class,
					new StringReader(s));
		}
	}

	public static String serialize(QName ns, Object o) throws Exception {
		return JAXBUtils.marshal(o);
	}

	public static <T> T deserialize(String s, Class<T> c) throws Exception {
		return JAXBUtils.unmarshal(c, new StringReader(s));
	}
}
