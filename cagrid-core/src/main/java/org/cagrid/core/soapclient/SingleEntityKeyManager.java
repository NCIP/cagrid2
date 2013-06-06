package org.cagrid.core.soapclient;

import org.cagrid.core.common.security.X509Credential;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

public class SingleEntityKeyManager extends X509ExtendedKeyManager {

	private final String alias;
	private final X509Certificate[] certificateChain;
	private final PrivateKey privateKey;

    public SingleEntityKeyManager(String alias, X509Credential credential) {
        this(alias, credential.getCertificates(), credential.getKey());
    }

	public SingleEntityKeyManager(String alias,
			X509Certificate[] certificateChain, PrivateKey privateKey) {
		this.alias = alias;
		this.certificateChain = certificateChain;
		this.privateKey = privateKey;
	}

	@Override
	public String chooseEngineClientAlias(String[] keyType,
			Principal[] issuers, SSLEngine engine) {
		return alias;
	}

	@Override
	public String chooseEngineServerAlias(String keyType, Principal[] issuers,
			SSLEngine engine) {
		return null;
	}

	@Override
	public String chooseClientAlias(String[] arg0, Principal[] arg1, Socket arg2) {
		return alias;
	}

	@Override
	public String chooseServerAlias(String arg0, Principal[] arg1, Socket arg2) {
		return null;
	}

	@Override
	public X509Certificate[] getCertificateChain(String arg0) {
		return certificateChain;
	}

	@Override
	public String[] getClientAliases(String arg0, Principal[] arg1) {
		return new String[] { alias };
	}

	@Override
	public PrivateKey getPrivateKey(String arg0) {
		return privateKey;
	}

	@Override
	public String[] getServerAliases(String arg0, Principal[] arg1) {
		return new String[] {};
	}
}
