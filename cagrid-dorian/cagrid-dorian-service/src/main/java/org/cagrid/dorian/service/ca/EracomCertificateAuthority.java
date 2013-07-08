package org.cagrid.dorian.service.ca;

import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EracomCertificateAuthority extends CertificateAuthority implements WrappingCertificateAuthority {

	private final static Logger logger = LoggerFactory.getLogger(EracomCertificateAuthority.class);

	public static final String CA_ALIAS = "dorianca";
	public static final String WRAPPER_KEY_ALIAS = "dorian-wrapper-key";
	public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
	public static final String SLOT_PROPERTY = "slot";
	private Provider provider;
	private KeyStore keyStore;
	private Key wrapper;
	private boolean isInit = false;

	public EracomCertificateAuthority(EracomCertificateAuthorityProperties properties) throws CertificateAuthorityException {
		super(properties);
		try {
			int slot = properties.getSlot();
			provider = (Provider) Class.forName("au.com.eracom.crypto.provider.slot" + slot + ".ERACOMProvider").newInstance();
			Security.addProvider(provider);
			keyStore = KeyStore.getInstance("CRYPTOKI", provider.getName());
			keyStore.load(null, properties.getCertificateAuthorityPassword().toCharArray());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Error initializing the Dorian Certificate Authority.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

	}

	public String getSignatureAlgorithm(CertificateSignatureAlgorithm alg) throws CertificateAuthorityException {
		if (alg.equals(CertificateSignatureAlgorithm.SHA1)) {
			return SIGNATURE_ALGORITHM;
		} else if (alg.equals(CertificateSignatureAlgorithm.SHA2)) {
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "The signature algorithm " + alg.value()
					+ " is not supported by the certificate authority " + getClass().getName() + ".");
			throw fault;
		} else {
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "The signature algorithm " + alg.value()
					+ " is not supported by the certificate authority " + getClass().getName() + ".");
			throw fault;
		}
	}

	public String getCACredentialsProvider() {
		return getProvider().getName();
	}

	protected Provider getProvider() {
		return provider;
	}

	protected KeyStore getKeyStore() {
		return keyStore;
	}

	protected X509Certificate convert(X509Certificate cert) throws Exception {
		String str = CertUtil.writeCertificate(cert);
		return CertUtil.loadCertificate(str);
	}

	public void deleteCACredentials() throws CertificateAuthorityException {
		try {
			getKeyStore().deleteEntry(CA_ALIAS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not delete the CA credentials.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

	}

	protected X509Certificate getCertificate() throws CertificateAuthorityException {
		try {
			if (!hasCACredentials()) {
				CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "The CA certificate does not exist.");
				throw fault;
			} else {
				return convert((X509Certificate) getKeyStore().getCertificate(CA_ALIAS));
			}
		} catch (CertificateAuthorityException f) {
			throw f;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not obtain the certificate.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

	}

	public PrivateKey getPrivateKey(String password) throws CertificateAuthorityException, NoCACredentialsException {
		try {
			if (!hasCACredentials()) {
				CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "The CA private key does not exist.");
				throw fault;
			} else {
				return (PrivateKey) getKeyStore().getKey(CA_ALIAS, null);
			}
		} catch (CertificateAuthorityException f) {
			throw f;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not obtain the private key.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public String getUserCredentialsProvider() {
		return getProvider().getName();
	}

	public boolean hasCACredentials() throws CertificateAuthorityException {
		try {
			return getKeyStore().containsAlias(CA_ALIAS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "An unexpected error occurred, could determin if credentials exist.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public void setCACredentials(X509Certificate cert, PrivateKey key, String password) throws CertificateAuthorityException {
		try {
			getKeyStore().setKeyEntry(CA_ALIAS, key, null, new X509Certificate[] { cert });
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not store CA credentials.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	protected void init() throws CertificateAuthorityException {
		try {
			if (!isInit) {
				if (keyStore.containsAlias(WRAPPER_KEY_ALIAS)) {
					wrapper = keyStore.getKey(WRAPPER_KEY_ALIAS, null);
				} else {
					KeyGenerator generator1 = KeyGenerator.getInstance("AES", provider);
					generator1.init(256, new SecureRandom());
					keyStore.setKeyEntry(WRAPPER_KEY_ALIAS, generator1.generateKey(), null, null);
					wrapper = keyStore.getKey(WRAPPER_KEY_ALIAS, null);
				}
				isInit = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "An unexpected error occurred, could not add certificate.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public PrivateKey unwrap(WrappedKey key) throws CertificateAuthorityException {
		try {
			init();
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", provider);
			IvParameterSpec dps = new IvParameterSpec(key.getIV());
			cipher.init(Cipher.DECRYPT_MODE, wrapper, dps);
			byte[] output = cipher.doFinal(key.getWrappedKeyData());
			return KeyUtil.loadPrivateKey(new ByteArrayInputStream(output), null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "An unexpected error occurred unwrapping a key.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public WrappedKey wrap(PrivateKey key) throws CertificateAuthorityException {
		try {
			init();
			byte[] input = KeyUtil.writePrivateKey(key, (String) null).getBytes();
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", provider);
			cipher.init(Cipher.ENCRYPT_MODE, wrapper);
			byte[] wrappedKey = cipher.doFinal(input);
			byte[] iv = cipher.getIV();
			return new WrappedKey(wrappedKey, iv);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "An unexpected error occurred wrapping a  key.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}
}
