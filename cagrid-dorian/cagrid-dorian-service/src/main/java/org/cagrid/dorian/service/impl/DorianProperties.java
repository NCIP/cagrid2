package org.cagrid.dorian.service.impl;

import org.cagrid.dorian.ca.impl.CertificateAuthority;
import org.cagrid.dorian.federation.impl.IdentityFederationProperties;
import org.cagrid.dorian.idp.impl.IdentityProviderProperties;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.events.EventManager;


public class DorianProperties {
    private IdentityProviderProperties identityProviderProperties;
    private IdentityFederationProperties identityFederationProperties;
    private CertificateAuthority certificateAuthority;
    private Database database;
    private EventManager eventManager;


    public DorianProperties(Database db, IdentityProviderProperties identityProviderProperties,
        IdentityFederationProperties identityFederationProperties, CertificateAuthority certificateAuthority,
        EventManager eventManager) {
        this.database = db;
        this.identityFederationProperties = identityFederationProperties;
        this.identityProviderProperties = identityProviderProperties;
        this.certificateAuthority = certificateAuthority;
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


    public CertificateAuthority getCertificateAuthority() {
        return certificateAuthority;
    }


    public Database getDatabase() {
        return database;
    }

}
