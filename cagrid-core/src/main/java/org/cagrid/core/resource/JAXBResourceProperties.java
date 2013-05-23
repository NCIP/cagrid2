package org.cagrid.core.resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.cagrid.wsrf.properties.ResourceProperty;

public class JAXBResourceProperties {

	private final Map<ResourcePropertyDescriptor<?>, ResourceProperty<?>> _resourceProperties = new HashMap<ResourcePropertyDescriptor<?>, ResourceProperty<?>>();
	private final Map<ResourcePropertyDescriptor<?>, ResourceProperty<?>> resourceProperties = Collections
			.unmodifiableMap(_resourceProperties);

	public Map<ResourcePropertyDescriptor<?>, ResourceProperty<?>> getResourceProperties() {
		return resourceProperties;
	}

	public JAXBResourceProperties(
			ClassLoader cl,
			Map<String, ResourcePropertyDescriptor<?>> descriptorsByField,
			Map<String, String> fieldURLStrings) throws JAXBException {

		Map<String, URL> fieldURLs = new HashMap<String, URL>();
		for (Map.Entry<String, String> entry : fieldURLStrings.entrySet()) {
			String fieldName = entry.getKey();
			String urlString = entry.getValue();
			URL url = null;

			// Try as String as URL
			try {
				url = new URL(urlString);
			} catch (MalformedURLException ignored) {
			}

			// Try as a classpath resource.
			if (url == null) {
				url = cl.getResource(urlString);
			}

			if (url == null) {
				throw new IllegalArgumentException("No resource property for '"
						+ urlString + "'");
			}

			fieldURLs.put(fieldName, url);
		}

		_resourceProperties.putAll(JAXBResourcePropertySupport
				.buildJAXBResourceProperties(descriptorsByField,
						fieldURLs));
	}
}
