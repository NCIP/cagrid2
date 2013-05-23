package org.cagrid.dorian.service.tools;

import java.util.Properties;

import org.cagrid.dorian.ca.impl.CertificateAuthorityProperties;
import org.cagrid.dorian.federation.impl.IdentityFederationProperties;
import org.cagrid.dorian.idp.impl.IdentityProviderProperties;
import org.cagrid.dorian.service.Dorian;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class SpringUtils {

	private static final ClassPathResource CONFIGURATION = new ClassPathResource(
			"META-INF/spring/dorian-configuration.xml");
	private static final FileSystemResource PROPERTIES = new FileSystemResource(
			"src/main/resources/dorian.properties");
	private static String DORIAN = "dorian";
	private static String CA_PROPERTIES = "caProperties";
	private static String IDP_PROPERTIES = "identityProviderProperties";
	private static String IFS_PROPERTIES = "identityFederationProperties";
	
	private XmlBeanFactory context;

	public SpringUtils() {
		this(CONFIGURATION, PROPERTIES);
	}

	public SpringUtils(Properties properties) {
		this(CONFIGURATION, properties);
	}

	public SpringUtils(AbstractResource conf, Properties properties) {
		this.context = new XmlBeanFactory(conf);
		if (properties != null) {
			PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
			cfg.setProperties(properties);
			cfg.postProcessBeanFactory(context);
		}
	}

	public SpringUtils(AbstractResource conf, AbstractResource properties) {
		this.context = new XmlBeanFactory(conf);
		if (properties != null) {
			PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
			cfg.setLocation(properties);
			cfg.postProcessBeanFactory(context);
		}
	}

	public Dorian getDorian() {
		return (Dorian) getBean(DORIAN);
	}
	public IdentityFederationProperties getIdentityFederationProperties() {
		return (IdentityFederationProperties) getBean(IFS_PROPERTIES);
	}
	public IdentityProviderProperties getIdentityProviderProperties() {
		return (IdentityProviderProperties) getBean(IDP_PROPERTIES);
	}
	
	public CertificateAuthorityProperties getCertificateAuthorityProperties() {
		return (CertificateAuthorityProperties) getBean(CA_PROPERTIES);
	}

	private Object getBean(String bean) {
		if (context != null) {
			return context.getBean(bean);
		} else {
			return null;
		}
	}

}
