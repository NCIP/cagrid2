package org.cagrid.trust.service.core;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class SpringUtils {

	private static final ClassPathResource CONFIGURATION = new ClassPathResource("META-INF/spring/trust-service-configuration.xml");
	private static final FileSystemResource PROPERTIES = new FileSystemResource("src/test/resources/trust.properties");
	private static final String SYNCHRONIZER = "synchronizer";
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

	public Synchronizer getSynchronizer() {
		return (Synchronizer) getBean(SYNCHRONIZER);
	}

	private Object getBean(String bean) {
		if (context != null) {
			return context.getBean(bean);
		} else {
			return null;
		}
	}

}
