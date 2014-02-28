package org.cagrid.dorian.service.impl;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.io.ByteArrayInputStream;
import java.security.KeyPair;
import java.security.cert.X509Certificate;

import org.cagrid.core.common.JAXBUtils;
import org.cagrid.dorian.model.federation.CertificateLifetime;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.gaards.authentication.AuthenticateUserRequest;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.gaards.saml.encoding.SAMLUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oasis.names.tc.saml.assertion.AssertionType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Element;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/dorian-configuration.xml" })
public class DorianImplTest {

	@javax.annotation.Resource
	private Dorian dorian;

	@Test
	public void testDorian() {
		Assert.assertNotNull(dorian);
	}


	@Test
	public void testAuthenticate() throws Exception {
		BasicAuthentication credential = new BasicAuthentication();
		credential.setUserId("dorian");
		credential.setPassword("DorianAdmin$1");
		AuthenticateUserRequest request = new AuthenticateUserRequest();
		AuthenticateUserRequest.Credential credential2 = new AuthenticateUserRequest.Credential();
		credential2.setCredential(credential);
		request.setCredential(credential2);
		SAMLAssertion samlAssertion = dorian.authenticate(credential);

		String samlXML = samlAssertion.toString();
		System.out.println("samlXML");
		System.out.println(samlXML);
		System.out.println();

		KeyPair keyPair = KeyUtil.generateRSAKeyPair1024();
		CertificateLifetime lifetime = new CertificateLifetime();
		lifetime.setHours(6);

		// Test that SAMLAssertion to String and back is OK.
		SAMLAssertion _samlAssertionX = new SAMLAssertion(new ByteArrayInputStream(samlXML.getBytes("UTF-8")));
		SAMLAssertion samlAssertionX = SAMLUtils.canonicalizeSAMLAssertion(_samlAssertionX);
		Assert.assertNotSame(_samlAssertionX, samlAssertionX);
		System.out.println("samlXML canonicalized");
		System.out.println(samlAssertionX);
		System.out.println();
		X509Certificate certificate = dorian.requestUserCertificate(samlAssertionX, keyPair.getPublic(), lifetime, CertificateSignatureAlgorithm.SHA2);
		Assert.assertNotNull(certificate);
	}
}
