package org.cagrid.serviceregistration.test;

import org.cagrid.serviceregistration.ServiceGroupRegistrator;
import org.cagrid.serviceregistration.model.ServiceGroupRegistrationParameters;
import org.junit.Test;
import org.xmlsoap.schemas.ws._2004._03.addressing.AttributedURI;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;

public class TestServiceRegistration {

	@Test
	public void registrationTest() {
		ServiceGroupRegistrator client = new ServiceGroupRegistrator();
		try {
			client.register("./src/test/resources/Dorian_registration.xml",
					"http://testing123");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
