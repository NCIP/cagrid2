package org.cagrid.core.resource;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.namespace.QName;

import org.cagrid.core.common.JAXBUtils;
import static org.cagrid.core.common.JAXBUtils.DEFAULT_NAMESPACE_VALUE;

public class ResourcePropertyDescriptor<T> {

	/**
	 * Map the descriptors by field, if present.
	 */
	public static Map<String, ResourcePropertyDescriptor<?>> mapByField(
			Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors) {
		Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = new HashMap<String, ResourcePropertyDescriptor<?>>();
		for (ResourcePropertyDescriptor<?> resourcePropertyDescriptor : resourcePropertyDescriptors) {
			String fieldName = resourcePropertyDescriptor.getFieldName();
			if (fieldName != null) {
				descriptorsByField.put(fieldName, resourcePropertyDescriptor);
			}
		}
		return descriptorsByField;
	}

	/**
	 * Analyze a (usually generated) class hierarchy that holds a resource's
	 * properties.
	 */
	public static Collection<ResourcePropertyDescriptor<?>> analyzeResourcePropertiesHolder(
			Class<?> klass) {
		Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = new HashSet<ResourcePropertyDescriptor<?>>();
		for (; (klass != null) && (klass != Object.class); klass = klass
				.getSuperclass()) {
			analyzeResourcePropertiesHolder(klass, resourcePropertyDescriptors);
		}
		return resourcePropertyDescriptors;
	}

	/**
	 * Analyze a (usually generated) class that holds a resource's properties.
	 */
	public static Collection<ResourcePropertyDescriptor<?>> analyzeResourcePropertiesHolder(
			Class<?> klass,
			Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors) {

		// Look for a default namespace.
		String defaultNamespace = JAXBUtils.getNamespace(klass);

		for (Field field : klass.getDeclaredFields()) {
			XmlElement xmlElement = field.getAnnotation(XmlElement.class);
			if (xmlElement == null)
				continue;
			String resourcePropertyNamespace = xmlElement.namespace();
			if (DEFAULT_NAMESPACE_VALUE.equals(resourcePropertyNamespace)) {
				resourcePropertyNamespace = defaultNamespace;
			}
			String resourcePropertyName = xmlElement.name();
			QName resourcePropertyQName = new QName(resourcePropertyNamespace,
					resourcePropertyName);
			Class<?> resourcePropertyClass = field.getType();
			String fieldName = field.getName();
			@SuppressWarnings({ "rawtypes", "unchecked" })
			ResourcePropertyDescriptor<?> resourcePropertyDescriptor = new ResourcePropertyDescriptor(
					resourcePropertyQName, resourcePropertyClass, fieldName);
			resourcePropertyDescriptors.add(resourcePropertyDescriptor);
		}
		return resourcePropertyDescriptors;
	}

	private final QName resourcePropertyQName;
	private final Class<T> resourcePropertyClass;
	private final String fieldName;
	private final int hashCode;

	public ResourcePropertyDescriptor(QName resourcePropertyQName,
			Class<T> resourcePropertyClass, String fieldName) {
		this.resourcePropertyQName = resourcePropertyQName;
		this.resourcePropertyClass = resourcePropertyClass;
		this.fieldName = fieldName;
		hashCode = resourcePropertyQName.hashCode();
	}

	public QName getResourcePropertyQName() {
		return resourcePropertyQName;
	}

	public Class<T> getResourcePropertyClass() {
		return resourcePropertyClass;
	}

	public String getFieldName() {
		return fieldName;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ResourcePropertyDescriptor))
			return false;
		ResourcePropertyDescriptor<?> that = (ResourcePropertyDescriptor<?>) obj;
		return resourcePropertyQName.equals(that.resourcePropertyQName);
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public String toString() {
		String s = resourcePropertyQName.toString() + "["
				+ resourcePropertyClass.getName() + "]";
		if (fieldName != null) {
			s += " (" + fieldName + ")";
		}
		return s;
	}
}
