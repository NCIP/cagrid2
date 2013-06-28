package org.cagrid.cds.service.impl;

import org.cagrid.cds.model.AllowedParties;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationRequest;
import org.cagrid.cds.model.DelegationSigningRequest;
import org.cagrid.cds.model.IdentityDelegationPolicy;
import org.cagrid.cds.model.ProxyLifetime;
import org.cagrid.cds.model.PublicKey;
import org.junit.Assert;
import org.junit.Test;

public class CDSTest extends BaseTest {

	@Test
	public void testGetServiceMetadata() {
		Assert.assertNotNull(cds.getServiceMetadata());
	}

	@Test
	public void testGetServiceSecurityMetadata() {
		Assert.assertNotNull(cds.getServiceSecurityMetadata());
	}

	@Test
	public void testDelegateCredential() throws Exception {
		final String initiatorGridIdentity = "/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=initiator";
		final String delegateGridIdentity = "/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=delegate";
		
		AllowedParties allowedParties = new AllowedParties();
		allowedParties.getGridIdentity().add(delegateGridIdentity);
		
		IdentityDelegationPolicy delegationPolicy = new IdentityDelegationPolicy();
		delegationPolicy.setAllowedParties(allowedParties);
		
		ProxyLifetime lifetime = new ProxyLifetime();
		lifetime.setHours(24);
		
		DelegationRequest delegationRequest = new DelegationRequest();
		delegationRequest.setDelegationPolicy(delegationPolicy);
		delegationRequest.setIssuedCredentialLifetime(lifetime);
		delegationRequest.setIssuedCredentialPathLength(0);
		delegationRequest.setKeyLength(2048);
		
		DelegationSigningRequest delegationSigningRequest = cds.initiateDelegation(initiatorGridIdentity, delegationRequest);
		
		DelegationIdentifier delegationIdentifier = delegationSigningRequest.getDelegationIdentifier();
		PublicKey publicKey = delegationSigningRequest.getPublicKey();
		System.out.println(delegationIdentifier.getDelegationId());
		System.out.println(publicKey.getKeyAsString());
	}
}
