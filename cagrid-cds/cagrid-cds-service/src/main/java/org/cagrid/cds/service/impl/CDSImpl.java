package org.cagrid.cds.service.impl;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.cagrid.cds.model.ClientDelegationFilter;
import org.cagrid.cds.model.DelegatedCredentialAuditFilter;
import org.cagrid.cds.model.DelegatedCredentialAuditRecord;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationRecord;
import org.cagrid.cds.model.DelegationRecordFilter;
import org.cagrid.cds.model.DelegationRequest;
import org.cagrid.cds.model.DelegationSigningRequest;
import org.cagrid.cds.model.DelegationSigningResponse;
import org.cagrid.cds.model.DelegationStatus;
import org.cagrid.cds.service.CredentialDelegationService;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.InvalidPolicyException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.cds.service.impl.delegatedcredential.DelegatedCredentialResourceHome;
import org.cagrid.cds.service.impl.manager.DelegationManager;
import org.cagrid.cds.wsrf.stubs.CredentialDelegationServiceResourceProperties;
import org.cagrid.core.resource.JAXBResourceProperties;
import org.cagrid.core.resource.JAXBResourcePropertySupport;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.database.DatabaseException;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CDSImpl implements CredentialDelegationService {

    private final Logger log;
    private DelegationManager cds;
    private CDSProperties cdsProperties;
    private final Map<String, String> jaxbResourcePropertiesMap;
    private Database db;
    private final ResourceImpl resource = new ResourceImpl(null);
    private final ResourceHome resourceHome = new SingletonResourceHomeImpl(resource);
    private final ResourceHome delegatedCredentialResourceHome = new DelegatedCredentialResourceHome();

    private ResourceProperty<ServiceMetadata> serviceMetadataResourceProperty;
    private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;

    public CDSImpl(CDSProperties cdsProperties, Map<String, String> jaxbResourcePropertiesMap) throws DatabaseException, JAXBException {
        this.log = LoggerFactory.getLogger(this.getClass().getName());
        this.cdsProperties = cdsProperties;
        this.jaxbResourcePropertiesMap = jaxbResourcePropertiesMap;
        initialize();
    }

    private void initialize() throws DatabaseException, JAXBException {
            /*
    	public CredentialDelegationServiceImpl() throws RemoteException {
		super();
		try {
			this.log = LogFactory.getLog(this.getClass().getName());
			String conf = this.getConfiguration().getCdsConfiguration();
			String properties = this.getConfiguration().getCdsProperties();
			FileSystemResource fsr = new FileSystemResource(conf);
			XmlBeanFactory factory = new XmlBeanFactory(fsr);
			PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
			cfg.setLocation(new FileSystemResource(properties));
			cfg.postProcessBeanFactory(factory);
			Database db = (Database) factory
					.getBean(ConfigurationConstants.DATABASE_CONFIGURATION_BEAN);
			db.createDatabaseIfNeeded();
			cds = (DelegationManager) factory
					.getBean(ConfigurationConstants.CDS_BEAN);

			home = (DelegatedCredentialResourceHome) getDelegatedCredentialResourceHome();
			home.setCDS(cds);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw Errors.getInternalFault(
					"Error initializing the Credential Delegation Service.", e);
		}
	}
     */

        db = cdsProperties.getDatabase();
        db.createDatabaseIfNeeded();
        cds = cdsProperties.getDelegationManager();

        //TODO: do we need to add "cds" to resource?

        // What resource properties should we know about?
        Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor
                .analyzeResourcePropertiesHolder(CredentialDelegationServiceResourceProperties.class);

        // Map them by field.
        Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor
                .mapByField(resourcePropertyDescriptors);

        // Load the static jaxb resource properties.
        JAXBResourceProperties jaxbResourceProperties = new JAXBResourceProperties(
                getClass().getClassLoader(), descriptorsByField,
                jaxbResourcePropertiesMap);

        @SuppressWarnings("unchecked")
		ResourcePropertyDescriptor<ServiceMetadata> serviceMetadataDescriptor = (ResourcePropertyDescriptor<ServiceMetadata>) descriptorsByField
                .get("serviceMetadata");
        if (serviceMetadataDescriptor != null) {
            @SuppressWarnings("unchecked")
			ResourceProperty<ServiceMetadata> resourceProperty = (ResourceProperty<ServiceMetadata>) jaxbResourceProperties
                    .getResourceProperties().get(serviceMetadataDescriptor);
            serviceMetadataResourceProperty = resourceProperty;
            resource.add(serviceMetadataResourceProperty);
        }

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

    @Override
    public DelegationSigningRequest initiateDelegation(String callerGridIdentiry, DelegationRequest req) throws DelegationException, PermissionDeniedException, CDSInternalException, InvalidPolicyException {
        return cds.initiateDelegation(callerGridIdentiry, req);
    }

    @Override
    public DelegationIdentifier approveDelegation(String callerGridIdentity, DelegationSigningResponse delegationSigningResponse) throws DelegationException, PermissionDeniedException, CDSInternalException {
        return cds.approveDelegation(callerGridIdentity, delegationSigningResponse);
    }

    @Override
    public List<DelegationRecord> findDelegatedCredentials(String callerGridIdentity, DelegationRecordFilter filter) throws PermissionDeniedException, CDSInternalException {
        return this.cds.findDelegatedCredentials(callerGridIdentity, filter);
    }

    @Override
    public void updateDelegatedCredentialStatus(String callerGridIndentity, DelegationIdentifier id, DelegationStatus status) throws DelegationException, PermissionDeniedException, CDSInternalException {
        this.cds.updateDelegatedCredentialStatus(callerGridIndentity, id, status);
    }

    @Override
    public List<DelegationRecord> findCredentialsDelegatedToClient(String callerGridIdentity, ClientDelegationFilter filter) throws PermissionDeniedException, CDSInternalException {
        return this.cds.findCredentialsDelegatedToClient(callerGridIdentity, filter);
    }

    @Override
    public List<DelegatedCredentialAuditRecord> searchDelegatedCredentialAuditLog(String callerGridIdentity, DelegatedCredentialAuditFilter f) throws DelegationException, PermissionDeniedException, CDSInternalException {
        return this.cds.searchDelegatedCredentialAuditLog(callerGridIdentity, f);
    }

    @Override
    public void deleteDelegatedCredential(String callerGridIdentity, DelegationIdentifier id) throws PermissionDeniedException, CDSInternalException {
        this.cds.deleteDelegatedCredential(callerGridIdentity, id);
    }

    @Override
    public void addAdmin(String callerGridIdentity, String gridIdentity) throws PermissionDeniedException, CDSInternalException {
        this.cds.addAdmin(callerGridIdentity, gridIdentity);
    }

    @Override
    public ResourceHome getResourceHome() {
        return resourceHome;
    }

    @Override
    public ServiceMetadata getServiceMetadata() {
        return (serviceMetadataResourceProperty != null) ? serviceMetadataResourceProperty
                .get(0) : null;
    }

    @Override
    public ServiceSecurityMetadata getServiceSecurityMetadata() {
        return (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty
                .get(0) : null;
    }

    @Override
    public void removeAdmin(String callerGridIdentity, String gridIdentity) throws PermissionDeniedException, CDSInternalException {
        this.cds.removeAdmin(callerGridIdentity, gridIdentity);
    }

    @Override
    public List<String> getAdmins(String callerGridIdentity) throws PermissionDeniedException, CDSInternalException {
        return this.cds.getAdmins(callerGridIdentity);
    }
}
