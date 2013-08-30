package org.cagrid.serviceregistration;

import javax.xml.ws.BindingProvider;

import org.cagrid.core.soapclient.SoapClientFactory;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ScheduledResourceTermination;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.service.WSResourceLifetimeService;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.GetResourceProperty;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.service.WSResourcePropertiesService;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.ServiceGroupRegistration;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.service.ServiceGroupService;

public class ServiceGroupClientFactory {

	public static ServiceGroupRegistration createServiceGroupRegistrationSoapClient(String url) {
		ServiceGroupService sgs = new ServiceGroupService();
		ServiceGroupRegistration sgr = sgs.getServiceGroupRegistrationPort();
		SoapClientFactory
				.configureSoapClient((BindingProvider) sgr, url);
		return sgr;
	}
	
	public static ScheduledResourceTermination createLifetimeSoapClient(String url) {
		WSResourceLifetimeService rls = new WSResourceLifetimeService();
		ScheduledResourceTermination srt = rls.getScheduledResourceTerminationPort();
		SoapClientFactory
				.configureSoapClient((BindingProvider) srt, url);
		return srt;
	}
	
	public static GetResourceProperty createGetResourcePropertySoapClient(String url) {
		WSResourcePropertiesService rps = new WSResourcePropertiesService();
		GetResourceProperty srt = rps.getGetResourcePropertyPort();
		SoapClientFactory
				.configureSoapClient((BindingProvider) srt, url);
		return srt;
	}
	
}
