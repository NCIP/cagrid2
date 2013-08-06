package org.cagrid.dorian.service.wsrf;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.cagrid.opensaml.SAMLException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceContext;

import org.cagrid.core.common.JAXBUtils;
import org.cagrid.core.resource.ExternalSingletonResourceProperty;
import org.cagrid.core.resource.ExternalSingletonResourcePropertyValue;
import org.cagrid.core.resource.JAXBResourceProperties;
import org.cagrid.core.resource.JAXBResourcePropertySupport;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.dorian.AddAdminRequest;
import org.cagrid.dorian.AddAdminResponse;
import org.cagrid.dorian.AddTrustedIdPRequest;
import org.cagrid.dorian.AddTrustedIdPResponse;
import org.cagrid.dorian.ApproveHostCertificateRequest;
import org.cagrid.dorian.ApproveHostCertificateResponse;
import org.cagrid.dorian.ChangeIdPUserPasswordRequest;
import org.cagrid.dorian.ChangeIdPUserPasswordResponse;
import org.cagrid.dorian.ChangeLocalUserPasswordRequest;
import org.cagrid.dorian.ChangeLocalUserPasswordResponse;
import org.cagrid.dorian.CreateProxyRequest;
import org.cagrid.dorian.CreateProxyResponse;
import org.cagrid.dorian.DoesLocalUserExistRequest;
import org.cagrid.dorian.DoesLocalUserExistResponse;
import org.cagrid.dorian.DorianInternalFaultFaultMessage;
import org.cagrid.dorian.DorianPortTypeImpl;
import org.cagrid.dorian.DorianResourceProperties;
import org.cagrid.dorian.FindGridUsersRequest;
import org.cagrid.dorian.FindGridUsersResponse;
import org.cagrid.dorian.FindHostCertificatesRequest;
import org.cagrid.dorian.FindHostCertificatesResponse;
import org.cagrid.dorian.FindLocalUsersRequest;
import org.cagrid.dorian.FindLocalUsersResponse;
import org.cagrid.dorian.FindUserCertificatesRequest;
import org.cagrid.dorian.FindUserCertificatesResponse;
import org.cagrid.dorian.GetAccountProfileRequest;
import org.cagrid.dorian.GetAccountProfileResponse;
import org.cagrid.dorian.GetAdminsRequest;
import org.cagrid.dorian.GetAdminsResponse;
import org.cagrid.dorian.GetCACertificateRequest;
import org.cagrid.dorian.GetCACertificateResponse;
import org.cagrid.dorian.GetGridUserPoliciesRequest;
import org.cagrid.dorian.GetGridUserPoliciesResponse;
import org.cagrid.dorian.GetOwnedHostCertificatesRequest;
import org.cagrid.dorian.GetOwnedHostCertificatesResponse;
import org.cagrid.dorian.GetPublishRequest;
import org.cagrid.dorian.GetPublishResponse;
import org.cagrid.dorian.GetTrustedIdPsRequest;
import org.cagrid.dorian.GetTrustedIdPsResponse;
import org.cagrid.dorian.HostSearchRequest;
import org.cagrid.dorian.HostSearchResponse;
import org.cagrid.dorian.InvalidAssertionFaultFaultMessage;
import org.cagrid.dorian.InvalidHostCertificateFaultFaultMessage;
import org.cagrid.dorian.InvalidHostCertificateRequestFaultFaultMessage;
import org.cagrid.dorian.InvalidProxyFaultFaultMessage;
import org.cagrid.dorian.InvalidTrustedIdPFaultFaultMessage;
import org.cagrid.dorian.InvalidUserCertificateFaultFaultMessage;
import org.cagrid.dorian.InvalidUserFaultFaultMessage;
import org.cagrid.dorian.InvalidUserPropertyFaultFaultMessage;
import org.cagrid.dorian.NoSuchUserFaultFaultMessage;
import org.cagrid.dorian.PerformFederationAuditRequest;
import org.cagrid.dorian.PerformFederationAuditResponse;
import org.cagrid.dorian.PerformIdentityProviderAuditRequest;
import org.cagrid.dorian.PerformIdentityProviderAuditResponse;
import org.cagrid.dorian.PermissionDeniedFaultFaultMessage;
import org.cagrid.dorian.RegisterLocalUserRequest;
import org.cagrid.dorian.RegisterLocalUserResponse;
import org.cagrid.dorian.RegisterWithIdPRequest;
import org.cagrid.dorian.RegisterWithIdPResponse;
import org.cagrid.dorian.RemoveAdminRequest;
import org.cagrid.dorian.RemoveAdminResponse;
import org.cagrid.dorian.RemoveGridUserRequest;
import org.cagrid.dorian.RemoveGridUserResponse;
import org.cagrid.dorian.RemoveLocalUserRequest;
import org.cagrid.dorian.RemoveLocalUserResponse;
import org.cagrid.dorian.RemoveTrustedIdPRequest;
import org.cagrid.dorian.RemoveTrustedIdPResponse;
import org.cagrid.dorian.RemoveUserCertificateRequest;
import org.cagrid.dorian.RemoveUserCertificateResponse;
import org.cagrid.dorian.RenewHostCertificateRequest;
import org.cagrid.dorian.RenewHostCertificateResponse;
import org.cagrid.dorian.RequestHostCertificateRequest;
import org.cagrid.dorian.RequestHostCertificateResponse;
import org.cagrid.dorian.RequestUserCertificateRequest;
import org.cagrid.dorian.RequestUserCertificateResponse;
import org.cagrid.dorian.SetPublishRequest;
import org.cagrid.dorian.SetPublishResponse;
import org.cagrid.dorian.UpdateAccountProfileRequest;
import org.cagrid.dorian.UpdateAccountProfileResponse;
import org.cagrid.dorian.UpdateGridUserRequest;
import org.cagrid.dorian.UpdateGridUserResponse;
import org.cagrid.dorian.UpdateHostCertificateRecordRequest;
import org.cagrid.dorian.UpdateHostCertificateRecordResponse;
import org.cagrid.dorian.UpdateLocalUserRequest;
import org.cagrid.dorian.UpdateLocalUserResponse;
import org.cagrid.dorian.UpdateTrustedIdPRequest;
import org.cagrid.dorian.UpdateTrustedIdPResponse;
import org.cagrid.dorian.UpdateUserCertificateRequest;
import org.cagrid.dorian.UpdateUserCertificateResponse;
import org.cagrid.dorian.UserPolicyFaultFaultMessage;
import org.cagrid.dorian.UserSearchRequest;
import org.cagrid.dorian.UserSearchResponse;
import org.cagrid.dorian.common.X509Certificate;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.model.exceptions.InvalidAssertionException;
import org.cagrid.dorian.model.exceptions.InvalidHostCertificateException;
import org.cagrid.dorian.model.exceptions.InvalidHostCertificateRequestException;
import org.cagrid.dorian.model.exceptions.InvalidTrustedIdPException;
import org.cagrid.dorian.model.exceptions.InvalidUserCertificateException;
import org.cagrid.dorian.model.exceptions.InvalidUserException;
import org.cagrid.dorian.model.exceptions.InvalidUserPropertyException;
import org.cagrid.dorian.model.exceptions.NoSuchUserException;
import org.cagrid.dorian.model.exceptions.PermissionDeniedException;
import org.cagrid.dorian.model.exceptions.UserPolicyException;
import org.cagrid.dorian.model.federation.CertificateLifetime;
import org.cagrid.dorian.model.federation.FederationAuditFilter;
import org.cagrid.dorian.model.federation.FederationAuditRecord;
import org.cagrid.dorian.model.federation.GridUser;
import org.cagrid.dorian.model.federation.GridUserFilter;
import org.cagrid.dorian.model.federation.GridUserPolicy;
import org.cagrid.dorian.model.federation.GridUserRecord;
import org.cagrid.dorian.model.federation.GridUserSearchCriteria;
import org.cagrid.dorian.model.federation.HostCertificateFilter;
import org.cagrid.dorian.model.federation.HostCertificateRecord;
import org.cagrid.dorian.model.federation.HostCertificateRequest;
import org.cagrid.dorian.model.federation.HostCertificateUpdate;
import org.cagrid.dorian.model.federation.HostRecord;
import org.cagrid.dorian.model.federation.HostSearchCriteria;
import org.cagrid.dorian.model.federation.ProxyLifetime;
import org.cagrid.dorian.model.federation.TrustedIdP;
import org.cagrid.dorian.model.federation.TrustedIdentityProvider;
import org.cagrid.dorian.model.federation.TrustedIdentityProviders;
import org.cagrid.dorian.model.federation.UserCertificateFilter;
import org.cagrid.dorian.model.federation.UserCertificateRecord;
import org.cagrid.dorian.model.federation.UserCertificateUpdate;
import org.cagrid.dorian.model.idp.AccountProfile;
import org.cagrid.dorian.model.idp.Application;
import org.cagrid.dorian.model.idp.BasicAuthCredential;
import org.cagrid.dorian.model.idp.IdentityProviderAuditFilter;
import org.cagrid.dorian.model.idp.IdentityProviderAuditRecord;
import org.cagrid.dorian.model.idp.LocalUser;
import org.cagrid.dorian.model.idp.LocalUserFilter;
import org.cagrid.dorian.policy.DorianPolicy;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.gaards.authentication.AuthenticateUserRequest;
import org.cagrid.gaards.authentication.AuthenticateUserResponse;
import org.cagrid.gaards.authentication.AuthenticationProfiles;
import org.cagrid.gaards.authentication.AuthenticationProviderFaultFaultMessage;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.authentication.Credential;
import org.cagrid.gaards.authentication.CredentialNotSupportedFaultFaultMessage;
import org.cagrid.gaards.authentication.InsufficientAttributeFaultFaultMessage;
import org.cagrid.gaards.authentication.InvalidCredentialFaultFaultMessage;
import org.cagrid.gaards.authentication.WebServiceCallerId;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.CredentialNotSupportedException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.gaards.saml.encoding.SAMLUtils;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse;
import org.cagrid.wsrf.properties.InvalidResourceKeyException;
import org.cagrid.wsrf.properties.NoSuchResourceException;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceException;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;
import org.cagrid.wsrf.properties.ResourcePropertySet;
import org.oasis.names.tc.saml.assertion.AssertionType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DorianServiceWSRFImpl extends DorianPortTypeImpl {

	private final static String ANONYMOUS_ID = "anonymous";

	private final static String AUTHENTICATION_PROFILES_PREFIX = "gauth";

	private final static Logger logger = LoggerFactory
			.getLogger(DorianServiceWSRFImpl.class);

	private final Dorian dorian;
	private final ResourceHome resourceHome;
	private ResourceProperty<ServiceMetadata> serviceMetadataResourceProperty;
	private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;

	private CertificateSignatureAlgorithm signingAlgorithm = CertificateSignatureAlgorithm.SHA2;
	@javax.annotation.Resource
	private WebServiceContext wsContext;

	public DorianServiceWSRFImpl(Dorian dorian, String signatureAlgorithm,
			Map<String, String> jaxbResourcePropertiesMap,
			TrustedIdPMetadataOverloader overloader) {
		this.dorian = dorian;
		// resourceHome = dorian.getResourceHome();
		resourceHome = getResourceHome(jaxbResourcePropertiesMap, overloader);
		if (signatureAlgorithm != null) {
			try {
				this.signingAlgorithm = CertificateSignatureAlgorithm
						.fromValue(signatureAlgorithm);
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	private ResourceHome getResourceHome(
			Map<String, String> jaxbResourcePropertiesMap,
			final TrustedIdPMetadataOverloader overloader) {
		ResourceImpl resource = new ResourceImpl(null);
		ResourceHome resourceHome = new SingletonResourceHomeImpl(resource);
		try {
			// What resource properties should we know about?
			Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor
					.analyzeResourcePropertiesHolder(DorianResourceProperties.class);

			// Map them by field.
			Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor
					.mapByField(resourcePropertyDescriptors);

			// Load the static jaxb resource properties.
			if (jaxbResourcePropertiesMap != null) {
				JAXBResourceProperties jaxbResourceProperties = new JAXBResourceProperties(
						getClass().getClassLoader(), descriptorsByField,
						jaxbResourcePropertiesMap);

				// The serviceMetadata property is static.
				@SuppressWarnings("unchecked")
				ResourcePropertyDescriptor<ServiceMetadata> serviceMetadataDescriptor = (ResourcePropertyDescriptor<ServiceMetadata>) descriptorsByField
						.get("serviceMetadata");
				if (serviceMetadataDescriptor != null) {
					@SuppressWarnings("unchecked")
					ResourceProperty<ServiceMetadata> resourceProperty = (ResourceProperty<ServiceMetadata>) jaxbResourceProperties
							.getResourceProperties().get(
									serviceMetadataDescriptor);
					serviceMetadataResourceProperty = resourceProperty;
					resource.add(serviceMetadataResourceProperty);
				}

				// The rest of the properties are callbacks.
				@SuppressWarnings("unchecked")
				ResourcePropertyDescriptor<AuthenticationProfiles> authenticationProfilesDescriptor = (ResourcePropertyDescriptor<AuthenticationProfiles>) descriptorsByField
						.get("authenticationProfiles");
				if (authenticationProfilesDescriptor != null) {
					// Must treat auth profiles as Element!
					ResourcePropertyDescriptor<Element> authenticationProfilesElementDescriptor = new ResourcePropertyDescriptor<Element>(
							authenticationProfilesDescriptor
									.getResourcePropertyQName(),
							Element.class, authenticationProfilesDescriptor
									.getFieldName());

					ExternalSingletonResourcePropertyValue<Element> propertyValue = new ExternalSingletonResourcePropertyValue<Element>() {
						@Override
						public Element getPropertyValue() {
							return getAuthenticationProfilesElement();
						}
					};
					ResourceProperty<Element> resourceProperty = new ExternalSingletonResourceProperty<Element>(
							authenticationProfilesElementDescriptor,
							propertyValue);
					resource.add(resourceProperty);
				}

				@SuppressWarnings("unchecked")
				ResourcePropertyDescriptor<TrustedIdentityProviders> trustedIdentityProvidersDescriptor = (ResourcePropertyDescriptor<TrustedIdentityProviders>) descriptorsByField
						.get("trustedIdentityProviders");
				if (trustedIdentityProvidersDescriptor != null) {
					ExternalSingletonResourcePropertyValue<TrustedIdentityProviders> propertyValue = new ExternalSingletonResourcePropertyValue<TrustedIdentityProviders>() {
						@Override
						public TrustedIdentityProviders getPropertyValue() {
							TrustedIdentityProviders trustedIdentityProviders = null;
							try {
								trustedIdentityProviders = dorian
										.getTrustedIdentityProviders();
								if (overloader != null) {
									List<TrustedIdentityProvider> list = trustedIdentityProviders
											.getTrustedIdentityProvider();
									for (TrustedIdentityProvider idp : list) {
										idp = overloader.overload(idp);
									}
								}
							} catch (DorianInternalException ignored) {
							}
							return trustedIdentityProviders;
						}
					};
					ResourceProperty<TrustedIdentityProviders> resourceProperty = new ExternalSingletonResourceProperty<TrustedIdentityProviders>(
							trustedIdentityProvidersDescriptor, propertyValue);
					resource.add(resourceProperty);
				}

				@SuppressWarnings("unchecked")
				ResourcePropertyDescriptor<DorianPolicy> dorianPolicyDescriptor = (ResourcePropertyDescriptor<DorianPolicy>) descriptorsByField
						.get("dorianPolicy");
				if (dorianPolicyDescriptor != null) {
					ExternalSingletonResourcePropertyValue<DorianPolicy> propertyValue = new ExternalSingletonResourcePropertyValue<DorianPolicy>() {
						@Override
						public DorianPolicy getPropertyValue() {
							return dorian.getDorianPolicy();
						}
					};
					ResourceProperty<DorianPolicy> resourceProperty = new ExternalSingletonResourceProperty<DorianPolicy>(
							dorianPolicyDescriptor, propertyValue);
					resource.add(resourceProperty);
				}

				/*
				 * ServiceSecurityMetadata isn't a resource property, but use
				 * that framework to handle it.
				 */
				String serviceSecurityMetadataURLString = jaxbResourcePropertiesMap
						.get("serviceSecurityMetadata");
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
								DorianServiceWSRFImpl.class.getName(),
								"serviceSecurityMetadata");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourceHome;
	}

	@Override
	public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
			GetMultipleResourceProperties getMultipleResourcePropertiesRequest)
			throws ResourceUnknownFault, InvalidResourcePropertyQNameFault {
		logger.info("getMultipleResourceProperty "
				+ getMultipleResourcePropertiesRequest);
		System.out.println(getMultipleResourcePropertiesRequest);
		GetMultipleResourcePropertiesResponse response = new GetMultipleResourcePropertiesResponse();
		for (Iterator iterator = getMultipleResourcePropertiesRequest
				.getResourceProperty().iterator(); iterator.hasNext();) {
			QName qname = (QName) iterator.next();
			Exception e;
			try {
				Resource resource = resourceHome.find(null);
				if (resource instanceof ResourcePropertySet) {
					ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resource;
					ResourceProperty<?> resourceProperty = resourcePropertySet
							.get(qname);
					if (resourceProperty != null) {
						Object resourcePropertyValue = resourceProperty.get(0);
						logger.info("getResourceProperty " + qname
								+ " returning " + resourcePropertyValue);
						if (!(resourcePropertyValue instanceof Node)
								&& !(resourcePropertyValue instanceof JAXBElement<?>)) {
							resourcePropertyValue = JAXBUtils
									.wrap(resourcePropertyValue);
						}
						response.getAny().add(resourcePropertyValue);
					}
				}
			} catch (NoSuchResourceException nsre) {
				e = nsre;
			} catch (InvalidResourceKeyException irke) {
				e = irke;
			} catch (ResourceException re) {
				e = re;
			}
		}

		return response;
	}

	@Override
	public GetResourcePropertyResponse getResourceProperty(
			QName resourcePropertyQName) throws ResourceUnknownFault,
			InvalidResourcePropertyQNameFault {
		logger.info("getResourceProperty " + resourcePropertyQName);
		Exception e = null;
		GetResourcePropertyResponse response = null;
		try {
			Resource resource = resourceHome.find(null);
			if (resource instanceof ResourcePropertySet) {
				ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resource;
				ResourceProperty<?> resourceProperty = resourcePropertySet
						.get(resourcePropertyQName);
				if (resourceProperty != null) {
					Object resourcePropertyValue = resourceProperty.get(0);
					logger.info("getResourceProperty " + resourcePropertyQName
							+ " returning " + resourcePropertyValue);
					if (!(resourcePropertyValue instanceof Node)
							&& !(resourcePropertyValue instanceof JAXBElement<?>)) {
						resourcePropertyValue = JAXBUtils
								.wrap(resourcePropertyValue);
					}
					response = new GetResourcePropertyResponse();
					response.getAny().add(resourcePropertyValue);
				}
			}
		} catch (NoSuchResourceException nsre) {
			e = nsre;
		} catch (InvalidResourceKeyException irke) {
			e = irke;
		} catch (ResourceException re) {
			e = re;
		}
		if ((response == null) || (e != null)) {
			throw new ResourceUnknownFault("No resource for '"
					+ resourcePropertyQName + "'", e);
		}
		return response;
	}

	@Override
	public QueryResourcePropertiesResponse queryResourceProperties(
			org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties queryResourcePropertiesRequest)
			throws QueryEvaluationErrorFault, InvalidQueryExpressionFault,
			ResourceUnknownFault, InvalidResourcePropertyQNameFault,
			UnknownQueryExpressionDialectFault {
		logger.info("queryResourceProperties");
		QueryResourcePropertiesResponse response = null;
		response = new QueryResourcePropertiesResponse();
		return response;
	}

	@Override
	public GetServiceSecurityMetadataResponse getServiceSecurityMetadata(
			GetServiceSecurityMetadataRequest getServiceSecurityMetadataRequest) {
		logger.info("getServiceSecurityMetadata");
		// ServiceSecurityMetadata serviceSecurityMetadata =
		// dorian.getServiceSecurityMetadata();
		ServiceSecurityMetadata serviceSecurityMetadata = (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty
				.get(0) : null;
		GetServiceSecurityMetadataResponse response = new GetServiceSecurityMetadataResponse();
		response.setServiceSecurityMetadata(serviceSecurityMetadata);
		return response;
	}

	@Override
	public GetPublishResponse getPublish(GetPublishRequest getPublishRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage {
		String message = "getPublish";
		logger.info(message);

		GetPublishResponse response = null;
		TrustedIdP idp = getPublishRequest.getTrustedIdP().getTrustedIdP();

		try {
			String gridId = getCallerId();
			boolean publish = dorian.getPublish(gridId, idp);
			response = new GetPublishResponse();
			response.setResponse(publish);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidTrustedIdPException itidpe) {
			throw new InvalidTrustedIdPFaultFaultMessage(message,
					itidpe.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public SetPublishResponse setPublish(SetPublishRequest setPublishRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage {
		String message = "setPublish";
		boolean publish = setPublishRequest.isPublish();
		logger.info(message + ": " + publish);

		TrustedIdP idp = setPublishRequest.getTrustedIdP().getTrustedIdP();
		String gridId = getCallerId();
		SetPublishResponse response = null;
		try {
			dorian.setPublish(gridId, idp, publish);
			response = new SetPublishResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidTrustedIdPException itidpe) {
			throw new InvalidTrustedIdPFaultFaultMessage(message,
					itidpe.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public DoesLocalUserExistResponse doesLocalUserExist(
			DoesLocalUserExistRequest doesLocalUserExistRequest)
			throws DorianInternalFaultFaultMessage {

		String message = "doesLocalUserExist";
		String userId = doesLocalUserExistRequest.getUserId();
		logger.info(message + ": " + userId);

		DoesLocalUserExistResponse response = null;
		try {
			boolean localUserExists = dorian.doesLocalUserExist(userId);
			logger.info(message + ": " + userId + " returning "
					+ localUserExists);
			response = new DoesLocalUserExistResponse();
			response.setResponse(localUserExists);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		}

		return response;
	}

	@Override
	public FindLocalUsersResponse findLocalUsers(
			FindLocalUsersRequest findLocalUsersRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {

		String message = "findLocalUsers";
		LocalUserFilter filter = findLocalUsersRequest.getF()
				.getLocalUserFilter();
		logger.info(message + ": " + filter);

		String gridId = getCallerId();
		FindLocalUsersResponse response = null;
		try {
			LocalUser[] localUsers = dorian.findLocalUsers(gridId, filter);
			response = new FindLocalUsersResponse();
			response.getLocalUser().addAll(Arrays.asList(localUsers));
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}

		return response;
	}

	@Override
	public UserSearchResponse userSearch(UserSearchRequest userSearchRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "userSearch";
		logger.info(message);

		GridUserSearchCriteria gridUserSearchCriteria = userSearchRequest
				.getGridUserSearchCriteria().getGridUserSearchCriteria();
		String gridId = getCallerId();
		UserSearchResponse response = new UserSearchResponse();
		try {
			List<GridUserRecord> gridUserRecords = dorian.userSearch(gridId,
					gridUserSearchCriteria);
			response.getGridUserRecord().addAll(gridUserRecords);
		} catch (RemoteException re) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ re.getMessage());
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public RegisterLocalUserResponse registerLocalUser(
			RegisterLocalUserRequest registerLocalUserRequest)
			throws DorianInternalFaultFaultMessage,
			InvalidUserPropertyFaultFaultMessage {
		String message = "registerLocalUser";
		logger.info(message);

		Application application = registerLocalUserRequest.getA()
				.getApplication();
		RegisterLocalUserResponse response = null;
		String userId = registerLocalUserInternal(message, application);
		response = new RegisterLocalUserResponse();
		response.setResponse(userId);
		return response;
	}

	private String registerLocalUserInternal(String message,
			Application application) throws DorianInternalFaultFaultMessage,
			InvalidUserPropertyFaultFaultMessage {
		String userId = null;
		try {
			userId = dorian.registerLocalUser(application);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidUserPropertyException iupe) {
			throw new InvalidUserPropertyFaultFaultMessage(message,
					iupe.getFault());
		}
		return userId;
	}

	@Override
	public UpdateLocalUserResponse updateLocalUser(
			UpdateLocalUserRequest updateLocalUserRequest)
			throws DorianInternalFaultFaultMessage,
			PermissionDeniedFaultFaultMessage, NoSuchUserFaultFaultMessage {
		String message = "updateLocalUser";
		logger.info(message);

		LocalUser localUser = updateLocalUserRequest.getUser().getLocalUser();
		String gridId = getCallerId();
		UpdateLocalUserResponse response = null;
		try {
			dorian.updateLocalUser(gridId, localUser);
			response = new UpdateLocalUserResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		} catch (NoSuchUserException nsue) {
			throw new NoSuchUserFaultFaultMessage(message, nsue.getFault());
		} catch (InvalidUserPropertyException iupe) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ iupe.getMessage());
		}
		return response;
	}

	@Override
	public ChangeLocalUserPasswordResponse changeLocalUserPassword(
			ChangeLocalUserPasswordRequest changeLocalUserPasswordRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage,
			InvalidUserPropertyFaultFaultMessage {
		String message = "changeLocalUserPassword";
		logger.info(message);

		BasicAuthentication basicAuthentication = changeLocalUserPasswordRequest
				.getCredential().getBasicAuthentication();
		String newPassword = changeLocalUserPasswordRequest.getNewPassword();
		ChangeLocalUserPasswordResponse response = null;
		changeLocalUserPasswordInternal(message, basicAuthentication,
				newPassword);
		response = new ChangeLocalUserPasswordResponse();
		return response;
	}

	private void changeLocalUserPasswordInternal(String message,
			BasicAuthentication basicAuthentication, String newPassword)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage,
			InvalidUserPropertyFaultFaultMessage {
		try {
			dorian.changeLocalUserPassword(basicAuthentication, newPassword);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		} catch (InvalidUserPropertyException iupe) {
			throw new InvalidUserPropertyFaultFaultMessage(message,
					iupe.getFault());
		}
	}

	public RemoveLocalUserResponse removeLocalUser(
			RemoveLocalUserRequest removeLocalUserRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "removeLocalUser";
		logger.info(message);

		String userId = removeLocalUserRequest.getUserId();
		String gridId = getCallerId();
		RemoveLocalUserResponse response = null;
		try {
			dorian.removeLocalUser(gridId, userId);
			response = new RemoveLocalUserResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public HostSearchResponse hostSearch(HostSearchRequest hostSearchRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "hostSearch";
		logger.info(message);

		HostSearchCriteria criteria = hostSearchRequest.getHostSearchCriteria()
				.getHostSearchCriteria();
		String gridId = getCallerId();
		HostSearchResponse response = null;
		try {
			List<HostRecord> hostRecords = dorian.hostSearch(gridId, criteria);
			response = new HostSearchResponse();
			response.getHostRecord().addAll(hostRecords);
		} catch (RemoteException re) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ re.getMessage());
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public GetTrustedIdPsResponse getTrustedIdPs(
			GetTrustedIdPsRequest getTrustedIdPsRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "getTrustedIdPs";
		logger.info(message);

		String gridId = getCallerId();
		GetTrustedIdPsResponse response = null;
		try {
			TrustedIdP[] idps = dorian.getTrustedIdPs(gridId);
			response = new GetTrustedIdPsResponse();
			response.getTrustedIdP().addAll(Arrays.asList(idps));
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	// Duplicates registerLocalUser
	public RegisterWithIdPResponse registerWithIdP(
			RegisterWithIdPRequest registerWithIdPRequest)
			throws DorianInternalFaultFaultMessage,
			InvalidUserPropertyFaultFaultMessage {
		String message = "registerWithIdP";
		logger.info(message);

		Application application = registerWithIdPRequest.getApplication()
				.getApplication();
		RegisterWithIdPResponse response = null;
		String userId = registerLocalUserInternal(message, application);
		response = new RegisterWithIdPResponse();
		response.setResponse(userId);
		return response;
	}

	@Override
	// Duplicates changeLocalUserPassword
	public ChangeIdPUserPasswordResponse changeIdPUserPassword(
			ChangeIdPUserPasswordRequest changeIdPUserPasswordRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage,
			InvalidUserPropertyFaultFaultMessage {
		String message = "changeIdPUserPassword";
		logger.info(message);

		BasicAuthCredential basicAuthCredential = changeIdPUserPasswordRequest
				.getCredential().getBasicAuthCredential();
		BasicAuthentication basicAuthentication = new BasicAuthentication();
		basicAuthentication.setUserId(basicAuthCredential.getUserId());
		basicAuthentication.setPassword(basicAuthCredential.getPassword());
		String newPassword = changeIdPUserPasswordRequest.getNewPassword();
		ChangeIdPUserPasswordResponse response = null;
		changeLocalUserPasswordInternal(message, basicAuthentication,
				newPassword);
		response = new ChangeIdPUserPasswordResponse();
		return response;
	}

	@Override
	public AddTrustedIdPResponse addTrustedIdP(
			AddTrustedIdPRequest addTrustedIdPRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage {
		String message = "addTrustedIdP";
		logger.info(message);

		TrustedIdP tip = addTrustedIdPRequest.getIdp().getTrustedIdP();
		String gridId = getCallerId();
		AddTrustedIdPResponse response = null;
		try {
			tip = dorian.addTrustedIdP(gridId, tip);
			response = new AddTrustedIdPResponse();
			response.setTrustedIdP(tip);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidTrustedIdPException itidpe) {
			throw new InvalidTrustedIdPFaultFaultMessage(message,
					itidpe.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public UpdateTrustedIdPResponse updateTrustedIdP(
			UpdateTrustedIdPRequest updateTrustedIdPRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage {
		String message = "updateTrustedIdP";
		logger.info(message);

		TrustedIdP idp = updateTrustedIdPRequest.getTrustedIdP()
				.getTrustedIdP();
		String gridId = getCallerId();
		UpdateTrustedIdPResponse response = null;
		try {
			dorian.updateTrustedIdP(gridId, idp);
			response = new UpdateTrustedIdPResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidTrustedIdPException itidpe) {
			throw new InvalidTrustedIdPFaultFaultMessage(message,
					itidpe.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public RemoveTrustedIdPResponse removeTrustedIdP(
			RemoveTrustedIdPRequest removeTrustedIdPRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage, InvalidTrustedIdPFaultFaultMessage {
		String message = "removeTrustedIdP";
		logger.info(message);

		TrustedIdP tip = removeTrustedIdPRequest.getTrustedIdP()
				.getTrustedIdP();
		String gridId = getCallerId();
		RemoveTrustedIdPResponse response = null;
		try {
			dorian.removeTrustedIdP(gridId, tip);
			response = new RemoveTrustedIdPResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidTrustedIdPException itidpe) {
			throw new InvalidTrustedIdPFaultFaultMessage(message,
					itidpe.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public PerformFederationAuditResponse performFederationAudit(
			PerformFederationAuditRequest performFederationAuditRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "performFederationAudit";
		logger.info(message);

		FederationAuditFilter filter = performFederationAuditRequest.getF()
				.getFederationAuditFilter();
		String gridId = getCallerId();
		PerformFederationAuditResponse response = null;
		try {
			List<FederationAuditRecord> federationAuditRecords = dorian
					.performFederationAudit(gridId, filter);
			response = new PerformFederationAuditResponse();
			response.getFederationAuditRecord().addAll(federationAuditRecords);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public PerformIdentityProviderAuditResponse performIdentityProviderAudit(
			PerformIdentityProviderAuditRequest performIdentityProviderAuditRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "performIdentityProviderAudit";
		logger.info(message);

		IdentityProviderAuditFilter filter = performIdentityProviderAuditRequest
				.getF().getIdentityProviderAuditFilter();
		String gridId = getCallerId();
		PerformIdentityProviderAuditResponse response = null;
		try {
			List<IdentityProviderAuditRecord> identityProviderAuditRecords = dorian
					.performIdentityProviderAudit(gridId, filter);
			response = new PerformIdentityProviderAuditResponse();
			response.getIdentityProviderAuditRecord().addAll(
					identityProviderAuditRecords);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public RequestUserCertificateResponse requestUserCertificate(
			RequestUserCertificateRequest requestUserCertificateRequest)
			throws DorianInternalFaultFaultMessage,
			PermissionDeniedFaultFaultMessage,
			InvalidAssertionFaultFaultMessage, UserPolicyFaultFaultMessage {
		String message = "requestUserCertificate";
		logger.info(message);

		AssertionType assertion = requestUserCertificateRequest.getSaml()
				.getAssertion();
		CertificateLifetime lifetime = requestUserCertificateRequest
				.getLifetime().getCertificateLifetime();
		RequestUserCertificateResponse response = null;
		try {
			PublicKey publicKey = KeyUtil
					.loadPublicKey(requestUserCertificateRequest.getKey()
							.getPublicKey().getKeyAsString());
			Element assertionElement = JAXBUtils.marshalToElement(assertion,
					SAMLUtils.ASSERTION_QNAME);
			SAMLUtils.canonicalizeAssertion(assertionElement);
			SAMLAssertion samlAssertion = new SAMLAssertion(assertionElement);
			// Must regenerate internal DOM!
			samlAssertion.toString();
			X509Certificate cert = new X509Certificate();
			cert.setCertificateAsString(CertUtil.writeCertificate(dorian
					.requestUserCertificate(samlAssertion, publicKey, lifetime,
							signingAlgorithm)));
			response = new RequestUserCertificateResponse();
			response.setX509Certificate(cert);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidAssertionException iae) {
			throw new InvalidAssertionFaultFaultMessage(message, iae.getFault());
		} catch (UserPolicyException upe) {
			throw new UserPolicyFaultFaultMessage(message, upe.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		} catch (IOException ioe) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ ioe.getMessage());
		} catch (GeneralSecurityException gse) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ gse.getMessage());
		} catch (JAXBException jaxbe) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ jaxbe.getMessage());
		} catch (SAMLException samle) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ samle.getMessage());
		} catch (ParserConfigurationException pce) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ pce.getMessage());
		}
		return response;
	}

	@Override
	public FindUserCertificatesResponse findUserCertificates(
			FindUserCertificatesRequest findUserCertificatesRequest)
			throws DorianInternalFaultFaultMessage,
			InvalidUserCertificateFaultFaultMessage,
			PermissionDeniedFaultFaultMessage {
		String message = "findUserCertificates";
		logger.info(message);

		UserCertificateFilter filter = findUserCertificatesRequest
				.getUserCertificateFilter().getUserCertificateFilter();
		String gridId = getCallerId();
		FindUserCertificatesResponse response = null;
		try {
			List<UserCertificateRecord> userCertificateRecords = dorian
					.findUserCertificateRecords(gridId, filter);
			response = new FindUserCertificatesResponse();
			response.getUserCertificateRecord().addAll(userCertificateRecords);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidUserCertificateException iuce) {
			throw new InvalidUserCertificateFaultFaultMessage(message,
					iuce.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public UpdateUserCertificateResponse updateUserCertificate(
			UpdateUserCertificateRequest updateUserCertificateRequest)
			throws DorianInternalFaultFaultMessage,
			InvalidUserCertificateFaultFaultMessage,
			PermissionDeniedFaultFaultMessage {
		String message = "updateUserCertificate";
		logger.info(message);

		UserCertificateUpdate update = updateUserCertificateRequest.getUpdate()
				.getUserCertificateUpdate();
		String gridId = getCallerId();
		UpdateUserCertificateResponse response = null;
		try {
			dorian.updateUserCertificateRecord(gridId, update);
			response = new UpdateUserCertificateResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidUserCertificateException iuce) {
			throw new InvalidUserCertificateFaultFaultMessage(message,
					iuce.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public RemoveUserCertificateResponse removeUserCertificate(
			RemoveUserCertificateRequest removeUserCertificateRequest)
			throws DorianInternalFaultFaultMessage,
			InvalidUserCertificateFaultFaultMessage,
			PermissionDeniedFaultFaultMessage {
		String message = "removeUserCertificate";
		logger.info(message);

		long serialNumber = Long.parseLong(removeUserCertificateRequest
				.getSerialNumber());
		String gridId = getCallerId();
		RemoveUserCertificateResponse response = null;
		try {
			dorian.removeUserCertificate(gridId, serialNumber);
			response = new RemoveUserCertificateResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidUserCertificateException iuce) {
			throw new InvalidUserCertificateFaultFaultMessage(message,
					iuce.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public FindGridUsersResponse findGridUsers(
			FindGridUsersRequest findGridUserRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "findGridUsers";
		logger.info(message);

		GridUserFilter filter = findGridUserRequest.getFilter()
				.getGridUserFilter();
		String gridId = getCallerId();
		FindGridUsersResponse response = null;
		try {
			GridUser[] gridUsers = dorian.findGridUsers(gridId, filter);
			response = new FindGridUsersResponse();
			response.getGridUser().addAll(Arrays.asList(gridUsers));
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public UpdateGridUserResponse updateGridUser(
			UpdateGridUserRequest updateGridUserRequest)
			throws DorianInternalFaultFaultMessage,
			PermissionDeniedFaultFaultMessage, InvalidUserFaultFaultMessage {
		String message = "updateGridUser";
		logger.info(message);

		GridUser gridUser = updateGridUserRequest.getUser().getGridUser();
		String gridId = getCallerId();
		UpdateGridUserResponse response = null;
		try {
			dorian.updateGridUser(gridId, gridUser);
			response = new UpdateGridUserResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidUserException iue) {
			throw new InvalidUserFaultFaultMessage(message, iue.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public RemoveGridUserResponse removeGridUser(
			RemoveGridUserRequest removeGridUserRequest)
			throws DorianInternalFaultFaultMessage,
			PermissionDeniedFaultFaultMessage, InvalidUserFaultFaultMessage {
		String message = "removeGridUser";
		logger.info(message);

		GridUser gridUser = removeGridUserRequest.getUser().getGridUser();
		String gridId = getCallerId();
		RemoveGridUserResponse response = null;
		try {
			dorian.removeGridUser(gridId, gridUser);
			response = new RemoveGridUserResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidUserException iue) {
			throw new InvalidUserFaultFaultMessage(message, iue.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public RequestHostCertificateResponse requestHostCertificate(
			RequestHostCertificateRequest requestHostCertificateRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage,
			InvalidHostCertificateRequestFaultFaultMessage,
			InvalidHostCertificateFaultFaultMessage {
		String message = "requestHostCertificate";
		logger.info(message);

		HostCertificateRequest hostCertificateRequest = requestHostCertificateRequest
				.getReq().getHostCertificateRequest();
		String gridId = getCallerId();
		RequestHostCertificateResponse response = null;
		try {
			HostCertificateRecord hostCertificateRecord = dorian
					.requestHostCertificate(gridId, hostCertificateRequest,
							CertificateSignatureAlgorithm.SHA2);
			response = new RequestHostCertificateResponse();
			response.setHostCertificateRecord(hostCertificateRecord);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidHostCertificateRequestException ihcre) {
			throw new InvalidHostCertificateRequestFaultFaultMessage(message,
					ihcre.getFault());
		} catch (InvalidHostCertificateException ihce) {
			throw new InvalidHostCertificateFaultFaultMessage(message,
					ihce.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public GetOwnedHostCertificatesResponse getOwnedHostCertificates(
			GetOwnedHostCertificatesRequest getOwnedHostCertificatesRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "getOwnedHostCertificates";
		logger.info(message);

		String gridId = getCallerId();
		GetOwnedHostCertificatesResponse response = null;
		try {
			HostCertificateRecord[] hostCertificateRecords = dorian
					.getOwnedHostCertificates(gridId);
			response = new GetOwnedHostCertificatesResponse();
			response.getHostCertificateRecord().addAll(
					Arrays.asList(hostCertificateRecords));
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public FindHostCertificatesResponse findHostCertificates(
			FindHostCertificatesRequest findHostCertificatesRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "findHostCertificates";
		logger.info(message);

		HostCertificateFilter filter = findHostCertificatesRequest
				.getHostCertificateFilter().getHostCertificateFilter();
		String gridId = getCallerId();
		FindHostCertificatesResponse response = null;
		try {
			HostCertificateRecord[] hostCertificateRecords = dorian
					.findHostCertificates(gridId, filter);
			response = new FindHostCertificatesResponse();
			response.getHostCertificateRecord().addAll(
					Arrays.asList(hostCertificateRecords));
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public ApproveHostCertificateResponse approveHostCertificate(
			ApproveHostCertificateRequest approveHostCertificateRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage,
			InvalidHostCertificateFaultFaultMessage {
		String message = "approveHostCertificate";
		logger.info(message);

		long recordId = approveHostCertificateRequest.getRecordId().longValue();
		String gridId = getCallerId();
		ApproveHostCertificateResponse response = null;
		try {
			HostCertificateRecord hostCertificateRecord = dorian
					.approveHostCertificate(gridId, recordId,
							CertificateSignatureAlgorithm.SHA2);
			response = new ApproveHostCertificateResponse();
			response.setHostCertificateRecord(hostCertificateRecord);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidHostCertificateException ihce) {
			throw new InvalidHostCertificateFaultFaultMessage(message,
					ihce.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public RenewHostCertificateResponse renewHostCertificate(
			RenewHostCertificateRequest renewHostCertificateRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage,
			InvalidHostCertificateFaultFaultMessage {
		String message = "renewHostCertificate";
		logger.info(message);

		long recordId = renewHostCertificateRequest.getRecordId().longValue();
		String gridId = getCallerId();
		RenewHostCertificateResponse response = null;
		try {
			HostCertificateRecord hostCertificateRecord = dorian
					.renewHostCertificate(gridId, recordId);
			response = new RenewHostCertificateResponse();
			response.setHostCertificateRecord(hostCertificateRecord);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidHostCertificateException ihce) {
			throw new InvalidHostCertificateFaultFaultMessage(message,
					ihce.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public UpdateHostCertificateRecordResponse updateHostCertificateRecord(
			UpdateHostCertificateRecordRequest updateHostCertificateRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage,
			InvalidHostCertificateFaultFaultMessage {
		String message = "updateHostCertificate";
		logger.info(message);

		HostCertificateUpdate update = updateHostCertificateRequest
				.getHostCertificateUpdate().getHostCertificateUpdate();
		String gridId = getCallerId();
		UpdateHostCertificateRecordResponse response = null;
		try {
			dorian.updateHostCertificateRecord(gridId, update);
			response = new UpdateHostCertificateRecordResponse();
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidHostCertificateException ihce) {
			throw new InvalidHostCertificateFaultFaultMessage(message,
					ihce.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public GetAccountProfileResponse getAccountProfile(
			GetAccountProfileRequest getAccountProfileRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "getAccountProfile";
		logger.info(message);

		String gridId = getCallerId();
		GetAccountProfileResponse response = null;
		try {
			dorian.getAccountProfile(gridId);
		} catch (RemoteException re) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ re.getMessage());
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public UpdateAccountProfileResponse updateAccountProfile(
			UpdateAccountProfileRequest updateAccountProfileRequest)
			throws DorianInternalFaultFaultMessage,
			PermissionDeniedFaultFaultMessage, NoSuchUserFaultFaultMessage,
			InvalidUserPropertyFaultFaultMessage {
		String message = "updateAccountProfile";
		logger.info(message);

		AccountProfile profile = updateAccountProfileRequest.getProfile()
				.getAccountProfile();
		String gridId = getCallerId();
		UpdateAccountProfileResponse response = null;
		try {
			dorian.updateAccountProfile(gridId, profile);
			response = new UpdateAccountProfileResponse();
		} catch (RemoteException re) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ re.getMessage());
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidUserPropertyException iupe) {
			throw new InvalidUserPropertyFaultFaultMessage(message,
					iupe.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		} catch (NoSuchUserException nsue) {
			throw new NoSuchUserFaultFaultMessage(message, nsue.getFault());
		}
		return response;
	}

	@Override
	public GetGridUserPoliciesResponse getGridUserPolicies(
			GetGridUserPoliciesRequest getGridUserPoliciesRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "getGridUserPolicies";
		logger.info(message);

		String gridId = getCallerId();
		GetGridUserPoliciesResponse response = null;
		try {
			GridUserPolicy[] gridUserPolicies = dorian
					.getGridUserPolicies(gridId);
			response = new GetGridUserPoliciesResponse();
			response.getGridUserPolicy()
					.addAll(Arrays.asList(gridUserPolicies));
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public AuthenticateUserResponse authenticateUser(
			AuthenticateUserRequest authenticateUserRequest)
			throws InsufficientAttributeFaultFaultMessage,
			AuthenticationProviderFaultFaultMessage,
			InvalidCredentialFaultFaultMessage,
			CredentialNotSupportedFaultFaultMessage {
		String message = "authenticateUser";
		logger.info(message);

		Credential credential = authenticateUserRequest.getCredential()
				.getCredential();

		AuthenticateUserResponse response = null;

		try {
			SAMLAssertion samlAssertion = dorian.authenticate(credential);
			String samlXML = samlAssertion.toString();
			AssertionType assertion = JAXBUtils.unmarshal(AssertionType.class,
					samlXML);
			response = new AuthenticateUserResponse();
			response.setAssertion(assertion);
		} catch (AuthenticationProviderException ape) {
			throw new AuthenticationProviderFaultFaultMessage(message,
					ape.getFault());
		} catch (InvalidCredentialException ice) {
			throw new InvalidCredentialFaultFaultMessage(message,
					ice.getFault());
		} catch (CredentialNotSupportedException cnse) {
			throw new CredentialNotSupportedFaultFaultMessage(message,
					cnse.getFault());
		} catch (JAXBException jaxbe) {
			throw new AuthenticationProviderFaultFaultMessage(message + ": "
					+ jaxbe.getMessage());
		}
		return response;
	}

	public GetCACertificateResponse getCACertificate(
			GetCACertificateRequest getCACertificateRequest)
			throws DorianInternalFaultFaultMessage {
		String message = "getCACertificate";
		logger.info(message);

		GetCACertificateResponse response = null;
		try {
			X509Certificate cert = new X509Certificate();
			cert.setCertificateAsString(CertUtil.writeCertificate(dorian
					.getCACertificate()));
			response = new GetCACertificateResponse();
			response.setX509Certificate(cert);
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (IOException ioe) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ ioe.getMessage());
		}
		return response;
	}

	@Override
	public GetAdminsResponse getAdmins(GetAdminsRequest getAdminsRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "getAdmins";
		logger.info(message);

		String gridId = getCallerId();
		GetAdminsResponse response = null;
		try {
			String[] adminIds = dorian.getAdmins(gridId);
			response = new GetAdminsResponse();
			response.getResponse().addAll(Arrays.asList(adminIds));
		} catch (RemoteException re) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ re.getMessage());
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public AddAdminResponse addAdmin(AddAdminRequest addAdminRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "addAdmin";
		logger.info(message);

		String adminIdentity = addAdminRequest.getGridIdentity();
		String gridId = getCallerId();
		AddAdminResponse response = null;
		try {
			dorian.addAdmin(gridId, adminIdentity);
			response = new AddAdminResponse();
		} catch (RemoteException re) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ re.getMessage());
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public RemoveAdminResponse removeAdmin(RemoveAdminRequest removeAdminRequest)
			throws PermissionDeniedFaultFaultMessage,
			DorianInternalFaultFaultMessage {
		String message = "removeAdmin";
		logger.info(message);

		String adminId = removeAdminRequest.getGridIdentity();
		String gridId = getCallerId();
		RemoveAdminResponse response = null;
		try {
			dorian.removeAdmin(gridId, adminId);
			response = new RemoveAdminResponse();
		} catch (RemoteException re) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ re.getMessage());
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		}
		return response;
	}

	@Override
	public CreateProxyResponse createProxy(CreateProxyRequest createProxyRequest)
			throws DorianInternalFaultFaultMessage,
			PermissionDeniedFaultFaultMessage,
			InvalidAssertionFaultFaultMessage, UserPolicyFaultFaultMessage,
			InvalidProxyFaultFaultMessage {
		String message = "createProxy";
		logger.info(message);

		String samlXML = createProxyRequest.getSaml().getSAMLAssertion()
				.getXml();
		ProxyLifetime proxyLifetime = createProxyRequest.getLifetime()
				.getProxyLifetime();
		CertificateLifetime lifetime = new CertificateLifetime();
		lifetime.setHours(proxyLifetime.getHours());
		lifetime.setMinutes(proxyLifetime.getMinutes());
		lifetime.setSeconds(proxyLifetime.getSeconds());
		CreateProxyResponse response = null;
		try {
			SAMLAssertion samlAssertion = new SAMLAssertion(
					new ByteArrayInputStream(samlXML.getBytes("UTF-8")));
			PublicKey publicKey = KeyUtil.loadPublicKey(createProxyRequest
					.getPublicKey().getPublicKey().getKeyAsString());
			X509Certificate cert = new X509Certificate();
			cert.setCertificateAsString(CertUtil.writeCertificate(dorian
					.requestUserCertificate(samlAssertion, publicKey, lifetime,
							signingAlgorithm)));
			response = new CreateProxyResponse();
			response.getX509Certificate().add(cert);
		} catch (IOException ioe) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ ioe.getMessage());
		} catch (DorianInternalException die) {
			throw new DorianInternalFaultFaultMessage(message, die.getFault());
		} catch (InvalidAssertionException iae) {
			throw new InvalidAssertionFaultFaultMessage(message, iae.getFault());
		} catch (UserPolicyException upe) {
			throw new UserPolicyFaultFaultMessage(message, upe);
		} catch (PermissionDeniedException pde) {
			throw new PermissionDeniedFaultFaultMessage(message, pde.getFault());
		} catch (GeneralSecurityException gse) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ gse.getMessage());
		} catch (SAMLException samle) {
			throw new DorianInternalFaultFaultMessage(message + ": "
					+ samle.getMessage());
		}
		return response;
	}

	private String getCallerId() {
		String callerId = WebServiceCallerId.getCallerId(wsContext);
		if (callerId == null)
			callerId = ANONYMOUS_ID;
		logger.info("CallerId = " + callerId);
		return callerId;
	}

	private AuthenticationProfiles getAuthenticationProfiles() {
		AuthenticationProfiles authProfiles = new AuthenticationProfiles();
		QName basicAuthenticationQName = JAXBUtils
				.getQName(BasicAuthentication.class);
		authProfiles.getProfile().add(basicAuthenticationQName);
		return authProfiles;
	}

	/*
	 * The client-side reconstruction of QNames from the getResourceProperty
	 * response is broken. It depends on the namespace prefix in the 'profile'
	 * element content being the same as in the element tag. To try to work
	 * around this, regenerate the appropriate QNames with a specific prefix and
	 * marshal the container with that prefix. The final response probably won't
	 * have the prefix used here, but the necessary prefixes should agree.
	 */
	private Element getAuthenticationProfilesElement() {
		AuthenticationProfiles authProfiles = getAuthenticationProfiles();
		QName authProfilesQName = JAXBUtils
				.getQName(AuthenticationProfiles.class);
		String authProfilesNamespace = authProfilesQName.getNamespaceURI();

		// New QName for marshalling
		authProfilesQName = new QName(authProfilesNamespace,
				authProfilesQName.getLocalPart(),
				AUTHENTICATION_PROFILES_PREFIX);

		// New QName elements
		List<QName> oldQNames = authProfiles.getProfile();
		List<QName> newQNames = new ArrayList<QName>(oldQNames.size());
		for (QName oldQName : oldQNames) {
			QName newQName = oldQName;
			if (authProfilesNamespace.equals(oldQName.getNamespaceURI())) {
				newQName = new QName(authProfilesNamespace,
						oldQName.getLocalPart(), AUTHENTICATION_PROFILES_PREFIX);
			}
			newQNames.add(newQName);
		}
		oldQNames.clear();
		oldQNames.addAll(newQNames);

		// Marshal to element with, hopefully, consistent prefixes.
		Element authProfilesElement = null;
		try {
			authProfilesElement = JAXBUtils.marshalToElement(authProfiles,
					authProfilesQName);
		} catch (Exception e) {
			logger.error("Exception marshalling AuthenticationProfiles", e);
		}
		return authProfilesElement;
	}

}
