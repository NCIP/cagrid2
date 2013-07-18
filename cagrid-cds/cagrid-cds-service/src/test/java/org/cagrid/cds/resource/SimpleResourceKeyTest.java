package org.cagrid.cds.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.service.impl.delegatedcredential.DelegatedCredentialResourceHome;
import org.cagrid.wsrf.properties.ResourceKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleResourceKeyTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testToSOAPElement() throws SOAPException, IOException {
        DelegatedCredentialResourceHome home = new DelegatedCredentialResourceHome();
        DelegationIdentifier id = new DelegationIdentifier();
        id.setDelegationId(5);
        ResourceKey resourceKey = null;
        try {
            resourceKey = home.getResourceKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        SOAPElement soapElement = resourceKey.toSOAPElement();
        Assert.assertEquals(DelegatedCredentialResourceHome.RESOURCE_KEY.getNamespaceURI(),soapElement.getNamespaceURI());
        Assert.assertEquals(DelegatedCredentialResourceHome.RESOURCE_KEY.getLocalPart(),soapElement.getNodeName());

        soapElement.getChildElements();
        
//        MessageFactory factory = MessageFactory.newInstance();  
//        SOAPMessage message = factory.createMessage();
//        SOAPBody body = message.getSOAPBody();
//        body.addChildElement(soapElement);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        message.writeTo(out);
//        System.out.println(out.toString());        
//        Assert.assertNotNull(soapElement);
        /**
         * <DelegatedCredentialKey xmlns="http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential">
         *      <delegationIdentifier xmlns="http://gaards.cagrid.org/cds"
         *                            xmlns:ns2="http://schemas.xmlsoap.org/ws/2004/03/addressing"
         *                            xmlns:ns3="http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types">
         *          <delegationId>5</delegationId>
         *      </delegationIdentifier>
         * </DelegatedCredentialKey>
         */

    }

}
