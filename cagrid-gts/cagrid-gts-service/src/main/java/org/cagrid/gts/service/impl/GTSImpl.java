package org.cagrid.gts.service.impl;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.cagrid.core.resource.JAXBResourceProperties;
import org.cagrid.core.resource.JAXBResourcePropertySupport;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.gts.model.AuthorityGTS;
import org.cagrid.gts.model.AuthorityPriorityUpdate;
import org.cagrid.gts.model.Permission;
import org.cagrid.gts.model.PermissionFilter;
import org.cagrid.gts.model.TrustLevel;
import org.cagrid.gts.model.TrustedAuthority;
import org.cagrid.gts.model.TrustedAuthorityFilter;
import org.cagrid.gts.model.X509CRL;
import org.cagrid.gts.model.X509Certificate;
import org.cagrid.gts.service.exception.CertificateValidationException;
import org.cagrid.gts.service.exception.GTSInternalException;
import org.cagrid.gts.service.exception.IllegalAuthorityException;
import org.cagrid.gts.service.exception.IllegalPermissionException;
import org.cagrid.gts.service.exception.IllegalTrustLevelException;
import org.cagrid.gts.service.exception.IllegalTrustedAuthorityException;
import org.cagrid.gts.service.exception.InvalidAuthorityException;
import org.cagrid.gts.service.exception.InvalidPermissionException;
import org.cagrid.gts.service.exception.InvalidTrustLevelException;
import org.cagrid.gts.service.exception.InvalidTrustedAuthorityException;
import org.cagrid.gts.service.exception.PermissionDeniedException;
import org.cagrid.gts.wsrf.stubs.GTSResourceProperties;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;

public class GTSImpl implements org.cagrid.gts.service.GTS {

    private GTS gts;

    private ResourceProperty<ServiceMetadata> serviceMetadataResourceProperty;
    private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;
    private final Map<String, String> jaxbResourcePropertiesMap;
    private final ResourceImpl resource = new ResourceImpl(null);
    private final ResourceHome resourceHome = new SingletonResourceHomeImpl(resource);

    public GTSImpl(Map<String, String> jaxbResourcePropertiesMap, SimpleResourceManager srm, String gtsURI,
            CredentialManager credmanager) throws GTSInternalException {
        // EndpointReferenceType type = AddressingUtils.createEndpointReference(null);
        // String configFileEnd = (String) MessageContext.getCurrentContext().getProperty(GTS_CONFIG);
        // String configFile = ContainerConfig.getBaseDirectory() + File.separator + configFileEnd;
        Configuration conf = (Configuration) srm.getResource(Configuration.RESOURCE);
        this.gts = new GTS(conf, gtsURI, credmanager);
        this.jaxbResourcePropertiesMap = jaxbResourcePropertiesMap;
    }

    public void synchronizeWithAuthorities(){
        gts.synchronizeWithAuthorities();
    }

    @Override
    public ServiceSecurityMetadata getServiceSecurityMetadata() {
        return (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty.get(0) : null;
    }

    @Override
    public ServiceMetadata getServiceMetadata() {
        return (serviceMetadataResourceProperty != null) ? serviceMetadataResourceProperty.get(0) : null;
    }

    @Override
    public ResourceHome getResourceHome() {
        return resourceHome;
    }

    private void initialize() throws JAXBException {
        // What resource properties should we know about?
        Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor
                .analyzeResourcePropertiesHolder(GTSResourceProperties.class);

        // Map them by field.
        Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor.mapByField(resourcePropertyDescriptors);

        // Load the static jaxb resource properties.
        JAXBResourceProperties jaxbResourceProperties = new JAXBResourceProperties(getClass().getClassLoader(), descriptorsByField, jaxbResourcePropertiesMap);

        // The serviceMetadata property is static.
        @SuppressWarnings("unchecked")
        ResourcePropertyDescriptor<ServiceMetadata> serviceMetadataDescriptor = (ResourcePropertyDescriptor<ServiceMetadata>) descriptorsByField
                .get("serviceMetadata");
        if (serviceMetadataDescriptor != null) {
            @SuppressWarnings("unchecked")
            ResourceProperty<ServiceMetadata> resourceProperty = (ResourceProperty<ServiceMetadata>) jaxbResourceProperties.getResourceProperties().get(
                    serviceMetadataDescriptor);
            serviceMetadataResourceProperty = resourceProperty;
            resource.add(serviceMetadataResourceProperty);
        }

        /*
         * ServiceSecurityMetadata isn't a resource property, but use that framework to handle it.
         */
        String serviceSecurityMetadataURLString = jaxbResourcePropertiesMap.get("serviceSecurityMetadata");
        if (serviceSecurityMetadataURLString != null) {
            URL url = null;
            try {
                url = new URL(serviceSecurityMetadataURLString);
            } catch (MalformedURLException ignored) {
            }
            if (url == null) {
                url = getClass().getClassLoader().getResource(serviceSecurityMetadataURLString);
            }
            if (url != null) {
                QName serviceSecurityMetadataQName = new QName(getClass().getName(), "serviceSecurityMetadata");
                ResourcePropertyDescriptor<ServiceSecurityMetadata> serviceSecurityMetadataDescriptor = new ResourcePropertyDescriptor<ServiceSecurityMetadata>(
                        serviceSecurityMetadataQName, ServiceSecurityMetadata.class, "serviceSecurityMetadata");
                serviceSecurityMetadataResourceProperty = JAXBResourcePropertySupport.createJAXBResourceProperty(serviceSecurityMetadataDescriptor, url);
            }
        }
    }

    @Override
    public TrustedAuthority addTrustedAuthority(String callerIdentity, TrustedAuthority ta) throws GTSInternalException, IllegalTrustedAuthorityException,
            PermissionDeniedException {
        return gts.addTrustedAuthority(ta, callerIdentity);
    }

    @Override
    public TrustedAuthority[] findTrustedAuthorities(String callerIdentity, TrustedAuthorityFilter filter) throws GTSInternalException {
        return gts.findTrustAuthorities(filter);
    }

    @Override
    public void removeTrustedAuthority(String callerIdentity, String trustedAuthorityName) throws GTSInternalException, InvalidTrustedAuthorityException,
            PermissionDeniedException {
        gts.removeTrustedAuthority(trustedAuthorityName, callerIdentity);

    }

    @Override
    public void addPermission(String callerIdentity, Permission permission) throws GTSInternalException, IllegalPermissionException, PermissionDeniedException {
        gts.addPermission(permission, callerIdentity);
    }

    @Override
    public Permission[] findPermissions(String callerIdentity, PermissionFilter filter) throws GTSInternalException, PermissionDeniedException {
        return gts.findPermissions(filter, callerIdentity);
    }

    @Override
    public void revokePermission(String callerIdentity, Permission permission) throws GTSInternalException, InvalidPermissionException,
            PermissionDeniedException {
        gts.revokePermission(permission, callerIdentity);
    }

    @Override
    public void updateTrustedAuthority(String callerIdentity, TrustedAuthority ta) throws GTSInternalException, IllegalTrustedAuthorityException,
            InvalidTrustedAuthorityException, PermissionDeniedException {
        gts.updateTrustedAuthority(ta, callerIdentity);

    }

    @Override
    public void addTrustLevel(String callerIdentity, TrustLevel trustLevel) throws GTSInternalException, IllegalTrustLevelException, PermissionDeniedException {
        gts.addTrustLevel(trustLevel, callerIdentity);

    }

    @Override
    public void updateTrustLevel(String callerIdentity, TrustLevel trustLevel) throws GTSInternalException, InvalidTrustLevelException,
            IllegalTrustLevelException, PermissionDeniedException {
        gts.updateTrustLevel(trustLevel, callerIdentity);

    }

    @Override
    public TrustLevel[] getTrustLevels(String callerIdentity) throws GTSInternalException {
        return gts.getTrustLevels(callerIdentity);
    }

    @Override
    public TrustLevel[] getTrustLevels(String callerIdentity, String gtsSourceURI) throws GTSInternalException {
        return gts.getTrustLevels(gtsSourceURI, callerIdentity);
    }

    @Override
    public void removeTrustLevel(String callerIdentity, String trustLevelName) throws GTSInternalException, InvalidTrustLevelException,
            IllegalTrustLevelException, PermissionDeniedException {
        gts.removeTrustLevel(trustLevelName, callerIdentity);

    }

    @Override
    public void addAuthority(String callerIdentity, AuthorityGTS authorityGTS) throws GTSInternalException, IllegalAuthorityException,
            PermissionDeniedException {
        gts.addAuthority(authorityGTS, callerIdentity);

    }

    @Override
    public void updateAuthority(String callerIdentity, AuthorityGTS authorityGTS) throws GTSInternalException, IllegalAuthorityException,
            InvalidAuthorityException, PermissionDeniedException {
        gts.updateAuthority(authorityGTS, callerIdentity);

    }

    @Override
    public void updateAuthorityPriorities(String callerIdentity, AuthorityPriorityUpdate authorityPriorityUpdate) throws GTSInternalException,
            IllegalAuthorityException, PermissionDeniedException {
        gts.updateAuthorityPriorities(authorityPriorityUpdate, callerIdentity);
    }

    @Override
    public AuthorityGTS[] getAuthorities(String callerIdentity) throws GTSInternalException {
        return gts.getAuthorities();
    }

    @Override
    public void removeAuthority(String callerIdentity, String serviceURI) throws GTSInternalException, InvalidAuthorityException, PermissionDeniedException {
        gts.removeAuthority(serviceURI, callerIdentity);

    }

    @Override
    public void updateCRL(String callerIdentity, String trustedAuthorityName, X509CRL crl) throws GTSInternalException, IllegalTrustedAuthorityException,
            InvalidTrustedAuthorityException, PermissionDeniedException {
        gts.updateCRL(trustedAuthorityName, crl, callerIdentity);

    }

    @Override
    public boolean validate(String callerIdentity, X509Certificate[] chain, TrustedAuthorityFilter filter) throws GTSInternalException,
            CertificateValidationException {
        return gts.validate(chain, filter);
    }

    protected GTS getGTS() {
        return this.gts;
    }

    @Override
    public boolean doesTrustLevelExist(String caller, String name) throws GTSInternalException {
        return gts.doesTrustLevelExist(name);
    }

    @Override
    public TrustLevel getTrustLevel(String caller, String name) throws GTSInternalException, InvalidTrustLevelException {
        return gts.getTrustLevel(name, caller);
    }

}
