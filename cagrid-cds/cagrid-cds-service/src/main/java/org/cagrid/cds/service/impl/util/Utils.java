package org.cagrid.cds.service.impl.util;

import org.cagrid.cds.model.AllowedParties;
import org.cagrid.cds.model.CertificateChain;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.IdentityDelegationPolicy;
import org.cagrid.delegatedcredential.types.DelegatedCredentialReference;
import org.cagrid.gaards.pki.CertUtil;
//import org.apache.axis.message.MessageElement;

import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

public class Utils {

	public static Date getEarliestExpiration(X509Certificate[] certs) {
		Date earliestTime = null;
		for (int i = 0; i < certs.length; i++) {
			Date time = certs[i].getNotAfter();
			if (earliestTime == null || time.before(earliestTime)) {
				earliestTime = time;
			}
		}
		return earliestTime;
	}

//	public static DelegationIdentifier getDelegationIdentifier(
//			DelegatedCredentialReference ref) {
//		MessageElement e = (MessageElement) ref.getEndpointReference()
//				.getProperties().get(0);
//		MessageElement c = (MessageElement) e.getChildElements().next();
//		String s = c.getValue();
//		DelegationIdentifier id = new DelegationIdentifier();
//		id.setDelegationId(Long.valueOf(s).longValue());
//		return id;
//	}

	public static org.cagrid.cds.model.X509Certificate convertCertificate(
			X509Certificate cert) throws Exception {
		String str = CertUtil.writeCertificate(cert);
		org.cagrid.cds.model.X509Certificate x509 = new org.cagrid.cds.model.X509Certificate();
		x509.setCertificateAsString(str);
		return x509;
	}

	public static X509Certificate convertCertificate(
			org.cagrid.cds.model.X509Certificate cert) throws Exception {
		return CertUtil.loadCertificate(cert.getCertificateAsString());
	}

	public static CertificateChain toCertificateChain(X509Certificate[] certs)
			throws Exception {
		CertificateChain chain = new CertificateChain();
		if (certs != null) {
			for (int i = 0; i < certs.length; i++) {
                chain.getX509Certificate().add(convertCertificate(certs[i]));
			}
		}
		return chain;
	}

	public static X509Certificate[] toCertificateArray(CertificateChain chain)
			throws Exception {
        if (chain == null || chain.getX509Certificate().size() == 0) {
            return new X509Certificate[0];
        }

        X509Certificate[] x509 = new X509Certificate[chain.getX509Certificate().size()];
        for (int i = 0; i < x509.length; i++) {
            x509[i] = convertCertificate(chain.getX509Certificate().get(i));
        }
        return x509;
	}

	public static IdentityDelegationPolicy createIdentityDelegationPolicy(
			List<String> parties) {
		IdentityDelegationPolicy policy = new IdentityDelegationPolicy();
		AllowedParties ap = new AllowedParties();
		if (parties != null) {
			ap.getGridIdentity().addAll(parties);
		}
		policy.setAllowedParties(ap);
		return policy;
	}
}
