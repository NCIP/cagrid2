package org.cagrid.core.metadata;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;

import org.cagrid.wsrf.properties.InvalidResourceKeyException;
import org.cagrid.wsrf.properties.NoSuchResourceException;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceException;
import org.cagrid.wsrf.properties.ResourceHome;

public class ResourcePropertyManager {

	private final ResourceHome resourceHome;

	public ResourcePropertyManager(ResourceHome resourceHome) {
		this.resourceHome = resourceHome;
	}

	public Resource getResource(MessageContext msgCtx)
			throws NoSuchResourceException, InvalidResourceKeyException,
			ResourceException {
		Resource resource = resourceHome.find(null);
		return resource;
	}

	public JAXBElement<?> getResourceProperty(QName rpName) {
		return null;
	}
}
