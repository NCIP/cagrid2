package org.cagrid.gaards.dorian.test;

import org.cagrid.dorian.ca.impl.CertificateAuthority;
import org.cagrid.dorian.ca.impl.CertificateAuthorityProperties;
import org.cagrid.dorian.federation.impl.IdentityFederationProperties;
import org.cagrid.dorian.federation.impl.TrustedIdPManager;
import org.cagrid.dorian.idp.impl.AssertionCredentialsManager;
import org.cagrid.dorian.idp.impl.IdentityProvider;
import org.cagrid.dorian.idp.impl.IdentityProviderProperties;
import org.cagrid.dorian.idp.impl.UserManager;
import org.cagrid.dorian.service.impl.DorianProperties;
import org.cagrid.dorian.types.DorianInternalException;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.events.EventManager;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.AbstractResource;

public class BeanUtils {

	private XmlBeanFactory factory;

	public BeanUtils(AbstractResource dorianConf, AbstractResource dorianProperties) throws DorianInternalException {
		this.factory = new XmlBeanFactory(dorianConf);
		PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
		cfg.setLocation(dorianProperties);
		cfg.postProcessBeanFactory(factory);
	}

	public DorianProperties getDorianProperties() throws Exception {
		DorianProperties props = (DorianProperties) factory.getBean(DorianConstants.DORIAN_PROPERTIES_BEAN);
		return props;
	}
	
	

	public IdentityFederationProperties getIdentityFederationProperties() throws Exception {
		IdentityFederationProperties props = (IdentityFederationProperties) factory.getBean(DorianConstants.IDENTITY_FEDERATION_PROPERTIES_BEAN);
		return props;
	}

	public IdentityProvider getIdentityProvider() throws Exception {
		return new IdentityProvider(getIdentityProviderProperties(), getDatabase(), getCertificateAuthority(), getEventManager());
	}

	public EventManager getEventManager() throws Exception {
		return (EventManager) factory.getBean(DorianConstants.EVENT_MANAGER_BEAN);
	}

	public TrustedIdPManager getTrustedIdPManager() throws Exception {
		return (TrustedIdPManager) factory.getBean(DorianConstants.TRUSTED_IDP_MANAGER_BEAN);
	}

	public IdentityProviderProperties getIdentityProviderProperties() throws Exception {
		return (IdentityProviderProperties) factory.getBean(DorianConstants.IDP_PROPERTIES_BEAN);
	}

	public AssertionCredentialsManager getAssertionCredentialsManager() throws Exception {

		return new AssertionCredentialsManager(getIdentityProviderProperties(), getCertificateAuthority(), getDatabase());
	}

	public org.cagrid.dorian.idp.impl.UserManager getIdPUserManager() throws Exception {
		return new org.cagrid.dorian.idp.impl.UserManager(getDatabase(), getIdentityProviderProperties());
	}

	public Database getDatabase() throws Exception {
		return (Database) factory.getBean(DorianConstants.DATABASE_BEAN);
	}

	public CertificateAuthorityProperties getCertificateAuthorityProperties() throws Exception {
		return (CertificateAuthorityProperties) factory.getBean(DorianConstants.CA_PROPERTIES_BEAN);

	}

	public CertificateAuthority getCertificateAuthority() throws Exception {
		return (CertificateAuthority) factory.getBean(DorianConstants.CA_BEAN);
	}

}
