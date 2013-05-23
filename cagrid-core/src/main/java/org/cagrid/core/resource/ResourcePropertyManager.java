package org.cagrid.core.resource;

import javax.xml.namespace.QName;

import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;
import org.cagrid.wsrf.properties.ResourcePropertySet;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;

public class ResourcePropertyManager {

	private final ResourceHome resourceHome;

	public ResourcePropertyManager(ResourceHome resourceHome) {
		this.resourceHome = resourceHome;
	}

	public GetResourcePropertyResponse getResourceProperty(QName qName)
			throws InvalidResourcePropertyQNameFault, ResourceUnknownFault {
		ResourceProperty<?> resourceProperty = null;

		Resource resource = null;
		try {
			resource = resourceHome.find(null);
		} catch (Exception e) {
			throw new ResourceUnknownFault("No resource for '" + qName + "'", e);
		}

		if (resource instanceof ResourcePropertySet) {
			ResourcePropertySet resourceProperties = (ResourcePropertySet) resource;
			resourceProperty = resourceProperties.get(qName);
		}

		if (resourceProperty == null) {
			throw new ResourceUnknownFault("No resource property for '" + qName
					+ "'");
		}

		GetResourcePropertyResponse response = new GetResourcePropertyResponse();
		response.getAny().add(resourceProperty.get(0));
		return response;
	}
}
