package org.cagrid.dorian.service.core;

import org.cagrid.dorian.service.ca.CertificateAuthorityManager;
import org.cagrid.dorian.service.federation.IdentityFederationProperties;
import org.cagrid.dorian.service.idp.IdentityProviderProperties;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.events.EventManager;

public class DorianProperties {
	private IdentityProviderProperties identityProviderProperties;
	private IdentityFederationProperties identityFederationProperties;
	private CertificateAuthorityManager certificateAuthorityManager;
	private Database database;
	private EventManager eventManager;

	public DorianProperties(Database db, IdentityProviderProperties identityProviderProperties, IdentityFederationProperties identityFederationProperties,
			CertificateAuthorityManager certificateAuthorityManager, EventManager eventManager) {
		this.database = db;
		this.identityFederationProperties = identityFederationProperties;
		this.identityProviderProperties = identityProviderProperties;
		this.certificateAuthorityManager = certificateAuthorityManager;
		this.eventManager = eventManager;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public IdentityProviderProperties getIdentityProviderProperties() {
		return identityProviderProperties;
	}

	public IdentityFederationProperties getIdentityFederationProperties() {
		return identityFederationProperties;
	}

	public CertificateAuthorityManager getCertificateAuthorityManager() {
		return certificateAuthorityManager;
	}

	public Database getDatabase() {
		return database;
	}

}
