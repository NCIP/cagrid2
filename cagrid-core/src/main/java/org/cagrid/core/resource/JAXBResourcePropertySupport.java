package org.cagrid.core.resource;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.cagrid.core.common.JAXBUtils;
import org.cagrid.wsrf.properties.ResourceProperty;

public class JAXBResourcePropertySupport {

	public static Map<ResourcePropertyDescriptor<?>, ResourceProperty<?>> buildJAXBResourceProperties(
			Map<String, ResourcePropertyDescriptor<?>> descriptorsByField,
			Map<String, URL> fieldURLs) throws JAXBException {

		Map<ResourcePropertyDescriptor<?>, ResourceProperty<?>> resourceProperties = new HashMap<ResourcePropertyDescriptor<?>, ResourceProperty<?>>();
		for (Map.Entry<String, URL> entry : fieldURLs.entrySet()) {
			String fieldName = entry.getKey();
			ResourcePropertyDescriptor<?> descriptor = descriptorsByField
					.get(fieldName);
			if (descriptor == null)
				continue;
			URL url = entry.getValue();
			ResourceProperty<?> resourceProperty = createJAXBResourceProperty(
					descriptor, url);
			resourceProperties.put(descriptor, resourceProperty);
		}
		return resourceProperties;
	}

	public static <T> ResourceProperty<T> createJAXBResourceProperty(
			ResourcePropertyDescriptor<T> resourcePropertyDescriptor, URL url)
			throws JAXBException {
		T property = JAXBUtils.unmarshal(
				resourcePropertyDescriptor.getResourcePropertyClass(), url);
		ResourceProperty<T> resourceProperty = new ImmutableSingletonResourceProperty<T>(
				resourcePropertyDescriptor, property);
		return resourceProperty;
	}
}
