package org.cagrid.dorian.systest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;

public class ContextLoader {

	public final static String KARAF_BASE_KEY = "karaf.base";
	public final static String DEFAULT_KARAF_BASE = "paxruntime";

	public final static Logger logger = LoggerFactory
			.getLogger(ContextLoader.class);

	public static AbstractApplicationContext loadContext(String contextKey,
			String contextConfigurationPath) throws IOException {
		String karafBase = System.getProperty(KARAF_BASE_KEY);
		if (karafBase == null) {
			karafBase = DEFAULT_KARAF_BASE;
			logger.warn(KARAF_BASE_KEY + " not set, using default: "
					+ karafBase);
			System.setProperty(KARAF_BASE_KEY, karafBase);
		}

		StaticApplicationContext context = new StaticApplicationContext();
		AbstractResource contextConfigurationResource = (AbstractResource) getAndPruneResource(
				context, contextKey, contextConfigurationPath);
		context.close();

		AbstractApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				contextConfigurationResource.getURL().toString());
		return applicationContext;
	}

	public static Resource getAndPruneResource(ApplicationContext context,
			String resourceKey, String resourcePath) throws IOException {

		Resource[] resources = context.getResources(resourcePath);
		int nResources = resources.length;
		if (nResources == 0) {
			String msg = "No resources found for '" + resourceKey + "' at '"
					+ resourcePath + "'";
			logger.warn(msg);
			throw new IOException(msg);
		}

		Resource resource = resources[0];
		if (nResources > 1) {
			logger.warn(nResources + " resources found for '" + resourceKey
					+ "' at '" + resourcePath + "'");
		}
		return resource;
	}
}
