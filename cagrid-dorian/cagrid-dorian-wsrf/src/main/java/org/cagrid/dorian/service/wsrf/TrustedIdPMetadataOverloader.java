package org.cagrid.dorian.service.wsrf;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.cagrid.dorian.model.federation.TrustedIdentityProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrustedIdPMetadataOverloader {

	private final static Logger logger = LoggerFactory.getLogger(TrustedIdPMetadataOverloader.class);

	private final static String SET_DELIM = ";";
	private final static String ITEM_DELIM = ",";

	private Map<String, TrustedIdentityProvider> idps;

	public TrustedIdPMetadataOverloader(String properties) {
		idps = new HashMap<String, TrustedIdentityProvider>();
		parseProperties(properties);
	}

	public TrustedIdentityProvider overload(TrustedIdentityProvider idp) {
		if (idps.containsKey(idp.getName())) {
			TrustedIdentityProvider obj = idps.get(idp.getName());
			idp.setAuthenticationServiceURL(obj.getAuthenticationServiceURL());
			idp.setAuthenticationServiceIdentity(obj.getAuthenticationServiceIdentity());
		}
		return idp;
	}

	private void parseProperties(String properties) {
		logger.debug("Properties " + properties);
		StringTokenizer st = new StringTokenizer(properties, SET_DELIM);
		while (st.hasMoreTokens()) {
			String set = st.nextToken();
			StringTokenizer st2 = new StringTokenizer(set, ITEM_DELIM);
			String name = null;
			String url = null;
			String identity = null;
			if (st2.hasMoreTokens()) {
				name = st2.nextToken();
				logger.debug("Name " + name);
				if (st2.hasMoreTokens()) {
					url = st2.nextToken();
					if (st2.hasMoreTokens()) {
						identity = st2.nextToken();
					}

				} else {
					continue;
				}

			} else {
				continue;
			}
			logger.debug("Overloading the Trusted IdP " + name + " with url " + url + " and the identity " + identity + ".");
			TrustedIdentityProvider idp = new TrustedIdentityProvider();
			idp.setName(name);
			idp.setAuthenticationServiceURL(url);
			idp.setAuthenticationServiceIdentity(identity);
			idps.put(name, idp);
		}

	}

}
