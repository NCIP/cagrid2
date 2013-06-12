package org.cagrid.cds.service.impl.testutils;

import org.cagrid.cds.model.DelegatedCredentialAuditFilter;
import org.cagrid.cds.model.DelegatedCredentialEvent;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.service.impl.manager.DelegatedCredentialManager;
import org.cagrid.cds.service.impl.manager.DelegationManager;
import org.cagrid.cds.service.impl.manager.KeyManager;
import org.cagrid.cds.service.impl.manager.PropertyManager;
import org.cagrid.cds.service.impl.policy.IdentityPolicyHandler;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.events.EventManager;
import org.cagrid.tools.groups.GroupManager;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Utils {

	public static ClassPathXmlApplicationContext loadConfiguration() throws Exception {

        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[] { "classpath*:test-beans.xml", "classpath:META-INF/spring/cds-beans-configuration.xml" });

		PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource(Constants.CDS_PROPERTIES));
        appContext.addBeanFactoryPostProcessor(cfg);
		return appContext;
	}

	public static DelegationManager getCDS() throws Exception {
		return (DelegationManager) loadConfiguration()
				.getBean(ConfigurationConstants.CDS_BEAN);
	}

	public static GroupManager getGroupManager() throws Exception {
		return (GroupManager) loadConfiguration()
				.getBean(ConfigurationConstants.GROUP_MANAGER_BEAN);
	}

	public static DelegatedCredentialManager getDelegatedCredentialManager()
			throws Exception {
		return (DelegatedCredentialManager) loadConfiguration()
				.getBean(ConfigurationConstants.DELEGATED_CREDENTIAL_MANAGER_CONFIGURATION_BEAN);
	}

	public static KeyManager getKeyManager() throws Exception {
		return (KeyManager) loadConfiguration()
				.getBean(ConfigurationConstants.KEY_MANAGER_CONFIGURATION_BEAN);
	}

	public static Database getDatabase() throws Exception {

		return (Database) loadConfiguration()
				.getBean(ConfigurationConstants.DATABASE_CONFIGURATION_BEAN);
	}

	public static PropertyManager getPropertyManager() throws Exception {
		return (PropertyManager) loadConfiguration()
				.getBean(ConfigurationConstants.PROPERTY_MANAGER_CONFIGURATION_BEAN);
	}

	public static EventManager getEventManager() throws Exception {
		return (EventManager) loadConfiguration()
				.getBean(ConfigurationConstants.EVENT_MANAGER);
	}

	public static DelegatedCredentialAuditFilter getInitiatedAuditFilter() {
		return getInitiatedAuditFilter(null);
	}

	public static DelegatedCredentialAuditFilter getInitiatedAuditFilter(
			DelegationIdentifier id) {
		DelegatedCredentialAuditFilter f = new DelegatedCredentialAuditFilter();
		f.setEvent(DelegatedCredentialEvent.DELEGATION_INITIATED);
		f.setDelegationIdentifier(id);
		return f;
	}
	
	public static DelegatedCredentialAuditFilter getApprovedAuditFilter() {
		return getApprovedAuditFilter(null);
	}

	public static DelegatedCredentialAuditFilter getApprovedAuditFilter(
			DelegationIdentifier id) {
		DelegatedCredentialAuditFilter f = new DelegatedCredentialAuditFilter();
		f.setEvent(DelegatedCredentialEvent.DELEGATION_APPROVED);
		f.setDelegationIdentifier(id);
		return f;
	}
	
	public static DelegatedCredentialAuditFilter getIssuedAuditFilter() {
		return getIssuedAuditFilter(null);
	}

	public static DelegatedCredentialAuditFilter getIssuedAuditFilter(
			DelegationIdentifier id) {
		DelegatedCredentialAuditFilter f = new DelegatedCredentialAuditFilter();
		f.setEvent(DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ISSUED);
		f.setDelegationIdentifier(id);
		return f;
	}
	
	public static DelegatedCredentialAuditFilter getAccessDeniedAuditFilter() {
		return getAccessDeniedAuditFilter(null);
	}

	public static DelegatedCredentialAuditFilter getAccessDeniedAuditFilter(
			DelegationIdentifier id) {
		DelegatedCredentialAuditFilter f = new DelegatedCredentialAuditFilter();
		f.setEvent(DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED);
		f.setDelegationIdentifier(id);
		return f;
	}
	
	public static DelegatedCredentialAuditFilter getUpdateStatusdAuditFilter() {
		return getUpdateStatusAuditFilter(null);
	}

	public static DelegatedCredentialAuditFilter getUpdateStatusAuditFilter(
			DelegationIdentifier id) {
		DelegatedCredentialAuditFilter f = new DelegatedCredentialAuditFilter();
		f.setEvent(DelegatedCredentialEvent.DELEGATION_STATUS_UPDATED);
		f.setDelegationIdentifier(id);
		return f;
	}
}
