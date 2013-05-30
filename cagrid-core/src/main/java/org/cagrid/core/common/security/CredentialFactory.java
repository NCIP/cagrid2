package org.cagrid.core.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * This class provides a convenient method for retrieving credentials that may
 * be used in a Spring Configuration file.
 *
 */
public class CredentialFactory {
	public static Logger log = LoggerFactory.getLogger(CredentialFactory.class);
	
	public static X509Credential getCredential(String keyStoreFile, String keyStorePassword, String keyAlias, String keyPassword) {
		X509Credential cred = null;
		try {
			KeyStore keystore = KeyStoreUtil.getKeyStore(keyStoreFile, keyStorePassword.toCharArray());
			
			if (keyAlias == null) {
				Enumeration<String> aliases = keystore.aliases();
				while (aliases.hasMoreElements()) {
					String alias = aliases.nextElement();
					if (keystore.entryInstanceOf(alias,
							KeyStore.PrivateKeyEntry.class)) {
						keyAlias = alias;
						break;
					}
				}
			}

			Key key = keystore.getKey(keyAlias, keyPassword.toCharArray());
			Certificate[] certAry = keystore.getCertificateChain(keyAlias);
			if(certAry == null){
				throw new GeneralSecurityException("A credential with the alias "+keyAlias+" could not be found in the keystore "+keyStoreFile+".");
			}
			X509Certificate[] chain = new X509Certificate[certAry.length];
			for(int i=0; i<certAry.length; i++) {
				chain[i] = (X509Certificate)certAry[i];
			}
			cred = new X509Credential(chain, (PrivateKey) key);
		} catch (IOException e) {
			log.error("IOException while getting credential", e);
		} catch (GeneralSecurityException e) {
			log.error("GeneralSecurityException while getting credential", e);
		}
		return cred;
	}
	

}
