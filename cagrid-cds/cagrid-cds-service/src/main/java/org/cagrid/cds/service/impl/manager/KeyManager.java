package org.cagrid.cds.service.impl.manager;

import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

public interface KeyManager {

	public abstract KeyPair createAndStoreKeyPair(String alias, int keyLength)
			throws CDSInternalException;

	public abstract PublicKey getPublicKey(String alias)
			throws CDSInternalException;

	public abstract PrivateKey getPrivateKey(String alias)
			throws CDSInternalException;

	public abstract X509Certificate[] getCertificates(String alias)
			throws CDSInternalException;

	public abstract boolean exists(String alias) throws CDSInternalException;

	public abstract void storeCertificates(String alias, X509Certificate[] cert)
			throws CDSInternalException, DelegationException;

	public abstract void delete(String alias) throws CDSInternalException;

	public abstract void deleteAll() throws CDSInternalException;
}
