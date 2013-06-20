package org.cagrid.gts.service.wsrf;

import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.util.Arrays;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.cagrid.gaards.authentication.WebServiceCallerId;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse;
import org.cagrid.gts.model.AuthorityGTS;
import org.cagrid.gts.model.Permission;
import org.cagrid.gts.model.TrustLevel;
import org.cagrid.gts.model.TrustedAuthority;
import org.cagrid.gts.model.X509Certificate;
import org.cagrid.gts.service.GTS;
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
import org.cagrid.gts.wsrf.stubs.AddAuthorityRequest;
import org.cagrid.gts.wsrf.stubs.AddAuthorityResponse;
import org.cagrid.gts.wsrf.stubs.AddPermissionRequest;
import org.cagrid.gts.wsrf.stubs.AddPermissionResponse;
import org.cagrid.gts.wsrf.stubs.AddTrustLevelRequest;
import org.cagrid.gts.wsrf.stubs.AddTrustLevelResponse;
import org.cagrid.gts.wsrf.stubs.AddTrustedAuthorityRequest;
import org.cagrid.gts.wsrf.stubs.AddTrustedAuthorityResponse;
import org.cagrid.gts.wsrf.stubs.CertificateValidationFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.FindPermissionsRequest;
import org.cagrid.gts.wsrf.stubs.FindPermissionsRequest.Filter;
import org.cagrid.gts.wsrf.stubs.FindPermissionsResponse;
import org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesRequest;
import org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesResponse;
import org.cagrid.gts.wsrf.stubs.GTSInternalFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.GTSPortTypeImpl;
import org.cagrid.gts.wsrf.stubs.GetAuthoritiesRequest;
import org.cagrid.gts.wsrf.stubs.GetAuthoritiesResponse;
import org.cagrid.gts.wsrf.stubs.GetTrustLevelsRequest;
import org.cagrid.gts.wsrf.stubs.GetTrustLevelsResponse;
import org.cagrid.gts.wsrf.stubs.IllegalAuthorityFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.IllegalPermissionFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.IllegalTrustLevelFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.IllegalTrustedAuthorityFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.InvalidAuthorityFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.InvalidPermissionFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.InvalidTrustLevelFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.InvalidTrustedAuthorityFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.PermissionDeniedFaultFaultMessage;
import org.cagrid.gts.wsrf.stubs.RemoveAuthorityRequest;
import org.cagrid.gts.wsrf.stubs.RemoveAuthorityResponse;
import org.cagrid.gts.wsrf.stubs.RemoveTrustLevelRequest;
import org.cagrid.gts.wsrf.stubs.RemoveTrustLevelResponse;
import org.cagrid.gts.wsrf.stubs.RemoveTrustedAuthorityRequest;
import org.cagrid.gts.wsrf.stubs.RemoveTrustedAuthorityResponse;
import org.cagrid.gts.wsrf.stubs.RevokePermissionRequest;
import org.cagrid.gts.wsrf.stubs.RevokePermissionResponse;
import org.cagrid.gts.wsrf.stubs.UpdateAuthorityPrioritiesRequest;
import org.cagrid.gts.wsrf.stubs.UpdateAuthorityPrioritiesResponse;
import org.cagrid.gts.wsrf.stubs.UpdateAuthorityRequest;
import org.cagrid.gts.wsrf.stubs.UpdateAuthorityResponse;
import org.cagrid.gts.wsrf.stubs.UpdateCRLRequest;
import org.cagrid.gts.wsrf.stubs.UpdateCRLResponse;
import org.cagrid.gts.wsrf.stubs.UpdateTrustLevelRequest;
import org.cagrid.gts.wsrf.stubs.UpdateTrustLevelResponse;
import org.cagrid.gts.wsrf.stubs.UpdateTrustedAuthorityRequest;
import org.cagrid.gts.wsrf.stubs.UpdateTrustedAuthorityResponse;
import org.cagrid.gts.wsrf.stubs.ValidateRequest;
import org.cagrid.gts.wsrf.stubs.ValidateResponse;
import org.cagrid.wsrf.properties.ResourceHome;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GTSWSRFImpl extends GTSPortTypeImpl {

    private GTS gts;
    private final Logger logger;

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    private final ResourceHome resourceHome;

    public GTSWSRFImpl(GTS service) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.gts = service;
        this.resourceHome = service.getResourceHome();
    }

    private String getCallerId() {
        String callerId = WebServiceCallerId.getCallerId(wsContext);
        if (callerId == null)
            callerId = "anonymous";
        logger.info("CallerId = " + callerId);
        return callerId;
    }

    @Override
    public GetAuthoritiesResponse getAuthorities(GetAuthoritiesRequest parameters) throws GTSInternalFaultFaultMessage {
        try {
            AuthorityGTS[] authorities = this.gts.getAuthorities(getCallerId());
            GetAuthoritiesResponse resp = new GetAuthoritiesResponse();
            if (authorities != null && authorities.length > 0) {
                resp.getAuthorityGTS().addAll(Arrays.asList(authorities));
            }
            return resp;
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public GetServiceSecurityMetadataResponse getServiceSecurityMetadata(GetServiceSecurityMetadataRequest parameters) {
        ServiceSecurityMetadata serviceSecurityMetadata = gts.getServiceSecurityMetadata();
        GetServiceSecurityMetadataResponse response = new GetServiceSecurityMetadataResponse();
        response.setServiceSecurityMetadata(serviceSecurityMetadata);
        return response;
    }

    @Override
    public AddPermissionResponse addPermission(AddPermissionRequest parameters) throws GTSInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage,
            IllegalPermissionFaultFaultMessage {
        try {
            this.gts.addPermission(getCallerId(), parameters.getPermission().getPermission());
            return new AddPermissionResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalPermissionException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalPermissionFaultFaultMessage fault = new IllegalPermissionFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(GetMultipleResourceProperties getMultipleResourcePropertiesRequest)
            throws ResourceUnknownFault, InvalidResourcePropertyQNameFault {
        logger.debug("Executing operation getMultipleResourceProperties");
        // TODO
        return null;
    }

    @Override
    public UpdateCRLResponse updateCRL(UpdateCRLRequest parameters) throws GTSInternalFaultFaultMessage, IllegalTrustedAuthorityFaultFaultMessage,
            InvalidTrustedAuthorityFaultFaultMessage, PermissionDeniedFaultFaultMessage {
        try {
            this.gts.updateCRL(getCallerId(), parameters.getTrustedAuthorityName(), parameters.getCrl().getX509CRL());
            return new UpdateCRLResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalTrustedAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalTrustedAuthorityFaultFaultMessage fault = new IllegalTrustedAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (InvalidTrustedAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            InvalidTrustedAuthorityFaultFaultMessage fault = new InvalidTrustedAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public QueryResourcePropertiesResponse queryResourceProperties(QueryResourceProperties queryResourcePropertiesRequest) throws ResourceUnknownFault,
            QueryEvaluationErrorFault, UnknownQueryExpressionDialectFault, InvalidResourcePropertyQNameFault, InvalidQueryExpressionFault {
        // TODO Auto-generated method stub
        return super.queryResourceProperties(queryResourcePropertiesRequest);
    }

    @Override
    public UpdateTrustLevelResponse updateTrustLevel(UpdateTrustLevelRequest parameters) throws GTSInternalFaultFaultMessage,
            PermissionDeniedFaultFaultMessage, IllegalTrustLevelFaultFaultMessage, InvalidTrustLevelFaultFaultMessage {
        try {
            this.gts.updateTrustLevel(getCallerId(), parameters.getTrustLevel().getTrustLevel());
            return new UpdateTrustLevelResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (InvalidTrustLevelException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            InvalidTrustLevelFaultFaultMessage fault = new InvalidTrustLevelFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalTrustLevelException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalTrustLevelFaultFaultMessage fault = new IllegalTrustLevelFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public UpdateAuthorityResponse updateAuthority(UpdateAuthorityRequest parameters) throws GTSInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage,
            InvalidAuthorityFaultFaultMessage, IllegalAuthorityFaultFaultMessage {
        try {
            this.gts.updateAuthority(getCallerId(), parameters.getAuthorityGTS().getAuthorityGTS());
            return new UpdateAuthorityResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalAuthorityFaultFaultMessage fault = new IllegalAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (InvalidAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            InvalidAuthorityFaultFaultMessage fault = new InvalidAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public AddAuthorityResponse addAuthority(AddAuthorityRequest parameters) throws GTSInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage,
            IllegalAuthorityFaultFaultMessage {
        try {
            this.gts.addAuthority(getCallerId(), parameters.getAuthorityGTS().getAuthorityGTS());
            return new AddAuthorityResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalAuthorityFaultFaultMessage fault = new IllegalAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public UpdateTrustedAuthorityResponse updateTrustedAuthority(UpdateTrustedAuthorityRequest parameters) throws GTSInternalFaultFaultMessage,
            IllegalTrustedAuthorityFaultFaultMessage, InvalidTrustedAuthorityFaultFaultMessage, PermissionDeniedFaultFaultMessage {
        try {
            this.gts.updateTrustedAuthority(getCallerId(), parameters.getTa().getTrustedAuthority());
            return new UpdateTrustedAuthorityResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalTrustedAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalTrustedAuthorityFaultFaultMessage fault = new IllegalTrustedAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (InvalidTrustedAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            InvalidTrustedAuthorityFaultFaultMessage fault = new InvalidTrustedAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public RemoveTrustedAuthorityResponse removeTrustedAuthority(RemoveTrustedAuthorityRequest parameters) throws GTSInternalFaultFaultMessage,
            InvalidTrustedAuthorityFaultFaultMessage, PermissionDeniedFaultFaultMessage {
        try {
            this.gts.removeTrustedAuthority(getCallerId(), parameters.getTrustedAuthorityName());
            return new RemoveTrustedAuthorityResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (InvalidTrustedAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            InvalidTrustedAuthorityFaultFaultMessage fault = new InvalidTrustedAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public RemoveAuthorityResponse removeAuthority(RemoveAuthorityRequest parameters) throws GTSInternalFaultFaultMessage, InvalidAuthorityFaultFaultMessage,
            PermissionDeniedFaultFaultMessage {
        try {
            this.gts.removeAuthority(getCallerId(), parameters.getServiceURI());
            return new RemoveAuthorityResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (InvalidAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            InvalidAuthorityFaultFaultMessage fault = new InvalidAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }

    }

    @Override
    public FindPermissionsResponse findPermissions(FindPermissionsRequest parameters) throws GTSInternalFaultFaultMessage, PermissionDeniedFaultFaultMessage {
        try {
            Permission[] permissions;
            if (parameters.getFilter() != null && parameters.getFilter().getPermissionFilter() != null) {
                permissions = this.gts.findPermissions(getCallerId(), parameters.getFilter().getPermissionFilter());
            } else {
                GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage("Cannot pass a null filter.");
                throw fault;
            }

            FindPermissionsResponse resp = new FindPermissionsResponse();
            if (permissions != null && permissions.length > 0) {
                resp.getPermission().addAll(Arrays.asList(permissions));
            }
            return resp;
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public AddTrustLevelResponse addTrustLevel(AddTrustLevelRequest parameters) throws GTSInternalFaultFaultMessage, IllegalTrustLevelFaultFaultMessage,
            PermissionDeniedFaultFaultMessage {
        try {
            this.gts.addTrustLevel(getCallerId(), parameters.getTrustLevel().getTrustLevel());
            return new AddTrustLevelResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalTrustLevelException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalTrustLevelFaultFaultMessage fault = new IllegalTrustLevelFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public AddTrustedAuthorityResponse addTrustedAuthority(AddTrustedAuthorityRequest parameters) throws GTSInternalFaultFaultMessage,
            IllegalTrustedAuthorityFaultFaultMessage, PermissionDeniedFaultFaultMessage {
        try {
            this.gts.addTrustedAuthority(getCallerId(), parameters.getTa().getTrustedAuthority());
            return new AddTrustedAuthorityResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalTrustedAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalTrustedAuthorityFaultFaultMessage fault = new IllegalTrustedAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public GetResourcePropertyResponse getResourceProperty(QName getResourcePropertyRequest) throws ResourceUnknownFault, InvalidResourcePropertyQNameFault {
        // TODO Auto-generated method stub
        return super.getResourceProperty(getResourcePropertyRequest);
    }

    @Override
    public UpdateAuthorityPrioritiesResponse updateAuthorityPriorities(UpdateAuthorityPrioritiesRequest parameters) throws GTSInternalFaultFaultMessage,
            PermissionDeniedFaultFaultMessage, IllegalAuthorityFaultFaultMessage {
        try {
            this.gts.updateAuthorityPriorities(getCallerId(), parameters.getAuthorityPriorityUpdate().getAuthorityPriorityUpdate());
            return new UpdateAuthorityPrioritiesResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalAuthorityException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalAuthorityFaultFaultMessage fault = new IllegalAuthorityFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public RemoveTrustLevelResponse removeTrustLevel(RemoveTrustLevelRequest parameters) throws GTSInternalFaultFaultMessage,
            PermissionDeniedFaultFaultMessage, IllegalTrustLevelFaultFaultMessage, InvalidTrustLevelFaultFaultMessage {
        try {
            this.gts.removeTrustLevel(getCallerId(), parameters.getTrustLevelName());
            return new RemoveTrustLevelResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (InvalidTrustLevelException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            InvalidTrustLevelFaultFaultMessage fault = new InvalidTrustLevelFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (IllegalTrustLevelException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            IllegalTrustLevelFaultFaultMessage fault = new IllegalTrustLevelFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public FindTrustedAuthoritiesResponse findTrustedAuthorities(FindTrustedAuthoritiesRequest parameters) {
        try {
            TrustedAuthority[] authorities = this.gts.findTrustedAuthorities(getCallerId(), parameters.getFilter().getTrustedAuthorityFilter());
            FindTrustedAuthoritiesResponse resp = new FindTrustedAuthoritiesResponse();
            if (authorities != null && authorities.length > 0) {
                resp.getTrustedAuthority().addAll(Arrays.asList(authorities));
            }
            return resp;
        } catch (GTSInternalException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
            // NOTE: this looks like an omission in the origina WSDL... it should throw this fault; I have to just trap the error for now.
            // GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            // throw fault;
            return new FindTrustedAuthoritiesResponse();
        }

    }

    @Override
    public GetTrustLevelsResponse getTrustLevels(GetTrustLevelsRequest parameters) throws GTSInternalFaultFaultMessage {
        try {
            TrustLevel[] levels = this.gts.getTrustLevels(getCallerId());
            GetTrustLevelsResponse resp = new GetTrustLevelsResponse();
            if (levels != null && levels.length > 0) {
                resp.getTrustLevel().addAll(Arrays.asList(levels));
            }
            return resp;
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public ValidateResponse validate(ValidateRequest parameters) throws GTSInternalFaultFaultMessage, CertificateValidationFaultFaultMessage {
        try {
            boolean validated = this.gts.validate(getCallerId(),
                    parameters.getChain().getX509Certificate().toArray(new X509Certificate[parameters.getChain().getX509Certificate().size()]), parameters
                            .getFilter().getTrustedAuthorityFilter());
            ValidateResponse resp = new ValidateResponse();
            resp.setResponse(validated);
            return resp;
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (CertificateValidationException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            CertificateValidationFaultFaultMessage fault = new CertificateValidationFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

    @Override
    public RevokePermissionResponse revokePermission(RevokePermissionRequest parameters) throws GTSInternalFaultFaultMessage,
            InvalidPermissionFaultFaultMessage, PermissionDeniedFaultFaultMessage {
        try {
            this.gts.revokePermission(getCallerId(), parameters.getPermission().getPermission());
            return new RevokePermissionResponse();
        } catch (GTSInternalException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            GTSInternalFaultFaultMessage fault = new GTSInternalFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (InvalidPermissionException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            InvalidPermissionFaultFaultMessage fault = new InvalidPermissionFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        } catch (PermissionDeniedException e) {
            logger.debug(ExceptionUtils.getFullStackTrace(e));
            PermissionDeniedFaultFaultMessage fault = new PermissionDeniedFaultFaultMessage(e.getMessage(), e.getFault());
            throw fault;
        }
    }

}
