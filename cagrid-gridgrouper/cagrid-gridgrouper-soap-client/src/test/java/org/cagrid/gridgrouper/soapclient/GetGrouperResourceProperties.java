package org.cagrid.gridgrouper.soapclient;

import org.cagrid.gridgrouper.wsrf.stubs.GridGrouperResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

public class GetGrouperResourceProperties extends GrouperClientBase {

	private final Collection<ResourcePropertyDescriptor> resourcePropertyDescriptors;
	private final JAXBContext jaxbCtxt;

	private GetGrouperResourceProperties(String url,
                                         Collection<ResourcePropertyDescriptor> resourcePropertyDescriptors)
			throws Exception {
		super(url);
		this.resourcePropertyDescriptors = resourcePropertyDescriptors;

		int n = resourcePropertyDescriptors.size();
		Class<?>[] klasses = new Class<?>[n];
		int i = 0;
		for (ResourcePropertyDescriptor resourcePropertyDescriptor : resourcePropertyDescriptors) {
			klasses[i++] = resourcePropertyDescriptor.resourcePropertyClass;
		}

		jaxbCtxt = JAXBContext.newInstance(klasses);
	}

	public static void main(String[] args) throws Exception {

		Collection<ResourcePropertyDescriptor> resourcePropertyDescriptors = getResourcePropertyDescriptors(GridGrouperResourceProperties.class);

		GetGrouperResourceProperties me = new GetGrouperResourceProperties(LOCAL_URL, resourcePropertyDescriptors);

		me.getProperties();
		
		
		QName badQName = new QName("bad", "name");
		try {
			me.getProperty(badQName);
		} catch (Exception e) {
			System.out.println(badQName + " incurred " + e);
		}
	}

	private static Collection<ResourcePropertyDescriptor> getResourcePropertyDescriptors(
			Class<?> klass) {
		Collection<ResourcePropertyDescriptor> resourcePropertyDescriptors = new LinkedHashSet<ResourcePropertyDescriptor>();
		for (; (klass != null) && (klass != Object.class); klass = klass
				.getSuperclass()) {
			getResourcePropertyDescriptors(klass, resourcePropertyDescriptors);
		}
		return resourcePropertyDescriptors;
	}

	private static void getResourcePropertyDescriptors(Class<?> klass,
			Collection<ResourcePropertyDescriptor> resourcePropertyDescriptors) {
		for (Field field : klass.getDeclaredFields()) {
			XmlElement xmlElement = field.getAnnotation(XmlElement.class);
			if (xmlElement == null)
				continue;
			Class<?> resourcePropertyClass = field.getType();
			String resourcePropertyNamespace = xmlElement.namespace();
			String resourcePropertyName = xmlElement.name();
			QName resourcePropertyQName = new QName(resourcePropertyNamespace,
					resourcePropertyName);
			ResourcePropertyDescriptor resourcePropertyDescriptor = new ResourcePropertyDescriptor(
					resourcePropertyClass, resourcePropertyQName);
			resourcePropertyDescriptors.add(resourcePropertyDescriptor);
		}
	}

	private void getProperties() throws Exception {
		for (ResourcePropertyDescriptor resourcePropertyDescriptor : resourcePropertyDescriptors) {
			getProperty(resourcePropertyDescriptor.resourcePropertyQName);
		}
	}

	private void getProperty(QName propertyQName) throws Exception {
		GetResourcePropertyResponse getResourcePropertyResponse = gridGrouper
				.getResourceProperty(propertyQName);
		List<Object> values = getResourcePropertyResponse.getAny();
		System.out.println();
		System.out.println(propertyQName);
		for (Object value : values) {
			if (value instanceof JAXBElement) {
				JAXBElement<?> jaxb = (JAXBElement<?>) value;
				Marshaller marshaller = jaxbCtxt.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw = new StringWriter();
				marshaller.marshal(jaxb, sw);
				String xml = sw.toString();
				System.out.println(xml);
			} else {
				System.out.println(value);
			}
		}
	}

	static class ResourcePropertyDescriptor {
		public final Class<?> resourcePropertyClass;
		public final QName resourcePropertyQName;

		public ResourcePropertyDescriptor(Class<?> resourcePropertyClass,
				QName resourcePropertyQName) {
			this.resourcePropertyClass = resourcePropertyClass;
			this.resourcePropertyQName = resourcePropertyQName;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof ResourcePropertyDescriptor))
				return false;
			ResourcePropertyDescriptor that = (ResourcePropertyDescriptor) obj;
			if (resourcePropertyClass != that.resourcePropertyClass)
				return false;
			return resourcePropertyQName.equals(that.resourcePropertyQName);
		}

		@Override
		public int hashCode() {
			return 31 * resourcePropertyClass.hashCode()
					+ resourcePropertyQName.hashCode();
		}

		@Override
		public String toString() {
			return resourcePropertyQName + " ["
					+ resourcePropertyClass.getName() + "]";
		}
	}
}
