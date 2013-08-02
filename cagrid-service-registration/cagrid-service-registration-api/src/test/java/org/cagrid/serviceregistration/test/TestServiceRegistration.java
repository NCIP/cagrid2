package org.cagrid.serviceregistration.test;


import org.cagrid.serviceregistration.ServiceGroupRegistrationClient;
import org.cagrid.serviceregistration.model.ServiceGroupRegistrationParameters;
import org.junit.Test;
import org.xmlsoap.schemas.ws._2004._03.addressing.AttributedURI;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;

public class TestServiceRegistration {
	
	 @Test 
	public void registrationTest(){
		ServiceGroupRegistrationClient client = new ServiceGroupRegistrationClient();
		try {
			EndpointReferenceType epr = new EndpointReferenceType();
			AttributedURI uri = new AttributedURI();
			uri.setValue("http://testing123");
			epr.setAddress(uri);
			ServiceGroupRegistrationParameters params = ServiceGroupRegistrationClient.readParams("./src/test/resources/Dorian_registration.xml");
			params.setRegistrantEPR(epr);
			client.register(params);
			Thread.sleep(30000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
