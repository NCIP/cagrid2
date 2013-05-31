package org.cagrid.core.common.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;


public class KeyStoreUtil {

	public static KeyStore getKeyStore(String fileURL, char[] storePass) throws IOException, GeneralSecurityException {
		File f = new File(fileURL);
		return getKeyStore(f, storePass);
	}

	public static KeyStore getKeyStore(File file, char[] storePass) throws IOException, GeneralSecurityException {
		FileInputStream ksInputStream = new FileInputStream(file);
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(ksInputStream, storePass);
		return ks;
	}

	public static PublicKey getPublicKey(KeyStore keyStore, String alias, char[] password) throws GeneralSecurityException {
		PublicKey publicKey = null;

		// Get private key
		Key key = keyStore.getKey(alias, password);
		if (key instanceof PrivateKey) {
			// Get certificate of public key
			Certificate cert = keyStore.getCertificate(alias);

			// Get public key
			publicKey = cert.getPublicKey();
		}
		// if alias is a certificate alias, get the public key from the
		// certificate.
		if (publicKey == null) {
			Certificate cert = keyStore.getCertificate(alias);
			if (cert != null)
				publicKey = cert.getPublicKey();
		}
		return publicKey;
	}

	public static KeyPair generateKeyPair(String algo) throws GeneralSecurityException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(algo);
		return kpg.genKeyPair();
	}

}
