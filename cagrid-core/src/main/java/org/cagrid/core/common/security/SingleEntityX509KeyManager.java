package org.cagrid.core.common.security;

import javax.net.ssl.X509KeyManager;
import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class SingleEntityX509KeyManager implements X509KeyManager {

	private static String ALIAS = "default";

	private X509Certificate[] certs;
	private PrivateKey key;

	public SingleEntityX509KeyManager(X509Certificate[] chain, PrivateKey key) {
		this.certs = chain;
		this.key = key;
	}

	@Override
	public String chooseClientAlias(String[] keyType, Principal[] issuers,
			Socket socket) {
		return ALIAS;
	}

	@Override
	public String chooseServerAlias(String keyType, Principal[] issuers,
			Socket socket) {
		return ALIAS;
	}

	@Override
	public X509Certificate[] getCertificateChain(String alias) {
		return certs;
	}

	@Override
	public String[] getClientAliases(String keyType, Principal[] issuers) {
		String[] aliases = new String[1];
		aliases[0] = ALIAS;
		return aliases;
	}

	@Override
	public PrivateKey getPrivateKey(String alias) {
		return key;
	}

	@Override
	public String[] getServerAliases(String keyType, Principal[] issuers) {
		String[] aliases = new String[1];
		aliases[0] = ALIAS;
		return aliases;
	}

}
