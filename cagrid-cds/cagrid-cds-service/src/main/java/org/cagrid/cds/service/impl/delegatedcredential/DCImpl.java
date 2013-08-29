package org.cagrid.cds.service.impl.delegatedcredential;

import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.cagrid.cds.model.CertificateChain;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.PublicKey;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.cds.service.impl.manager.DelegationManager;
import org.cagrid.cds.service.impl.util.Errors;
import org.cagrid.core.resource.*;
import org.cagrid.delegatedcredential.service.DelegatedCredentialService;
import org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialResourceProperties;
import org.cagrid.tools.database.DatabaseException;
import org.cagrid.wsrf.properties.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

public class DCImpl implements DelegatedCredentialService {

    private final Logger log;
    private final Map<String, String> jaxbResourcePropertiesMap;
    private final ResourceHome delegatedCredentialResourceHome;

    private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;

    public DCImpl(DelegationManager cds, Map<String, String> jaxbResourcePropertiesMap) {
        this.log = LoggerFactory.getLogger(this.getClass().getName());
        this.jaxbResourcePropertiesMap = jaxbResourcePropertiesMap;
        this.delegatedCredentialResourceHome = new DelegatedCredentialResourceHome(cds);
    }

    @Override
    public ResourceHome getResourceHome() {
        return delegatedCredentialResourceHome;
    }

    @Override
    public ServiceSecurityMetadata getServiceSecurityMetadata() {
        return (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty
                .get(0) : null;
    }

    @Override
    public CertificateChain getDelegatedCredential(String callerGridIdentity, DelegationIdentifier did, PublicKey publicKey)
            throws ResourceException, DelegationException, PermissionDeniedException, CDSInternalException {
        Resource resource = getResourceHome().find(getResourceKey(did));
        return ((DelegatedCredentialResource)resource).getDelegatedCredential(callerGridIdentity, publicKey);
    }

    private ResourceKey getResourceKey(DelegationIdentifier id)  {
        ResourceKey key = new SimpleResourceKey(
                new QName("http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential",
                "DelegatedCredentialKey"), id);
        return key;
    }

    private void initialize() throws DatabaseException, JAXBException {

        // What resource properties should we know about?
        Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor
                .analyzeResourcePropertiesHolder(DelegatedCredentialResourceProperties.class);

        // Map them by field.
        Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor
                .mapByField(resourcePropertyDescriptors);

        // Load the static jaxb resource properties.
        JAXBResourceProperties jaxbResourceProperties = new JAXBResourceProperties(
                getClass().getClassLoader(), descriptorsByField,
                jaxbResourcePropertiesMap);

        /*
		 * ServiceSecurityMetadata isn't a resource property, but use that
		 * framework to handle it.
		 */
        String serviceSecurityMetadataURLString = jaxbResourcePropertiesMap.get("serviceSecurityMetadata");
        if (serviceSecurityMetadataURLString != null) {
            URL url = null;
            try {
                url = new URL(serviceSecurityMetadataURLString);
            } catch (MalformedURLException ignored) {
            }
            if (url == null) {
                url = getClass().getClassLoader().getResource(
                        serviceSecurityMetadataURLString);
            }
            if (url != null) {
                QName serviceSecurityMetadataQName = new QName(
                        getClass().getName(), "serviceSecurityMetadata");
                ResourcePropertyDescriptor<ServiceSecurityMetadata> serviceSecurityMetadataDescriptor = new ResourcePropertyDescriptor<ServiceSecurityMetadata>(
                        serviceSecurityMetadataQName,
                        ServiceSecurityMetadata.class,
                        "serviceSecurityMetadata");
                serviceSecurityMetadataResourceProperty = JAXBResourcePropertySupport
                        .createJAXBResourceProperty(
                                serviceSecurityMetadataDescriptor, url);
            }
        }
    }
}
