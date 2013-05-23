package org.cagrid.cds.service.wsrf;

import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.delegatedcredential.service.DelegatedCredentialService;
import org.cagrid.delegatedcredential.wsrf.stubs.CDSInternalFaultFaultMessage;
import org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialPortTypeImpl;
import org.cagrid.delegatedcredential.wsrf.stubs.DelegationFaultFaultMessage;
import org.cagrid.delegatedcredential.wsrf.stubs.GetDelegatedCredentialRequest;
import org.cagrid.delegatedcredential.wsrf.stubs.GetDelegatedCredentialResponse;
import org.cagrid.delegatedcredential.wsrf.stubs.PermissionDeniedFaultFaultMessage;
import org.cagrid.gaards.authentication.WebServiceCallerId;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.WebServiceContext;

public class DCSWSRFImpl extends DelegatedCredentialPortTypeImpl {
    private final Logger logger;

    private final DelegatedCredentialService service;

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    public DCSWSRFImpl(DelegatedCredentialService service) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.service = service;
    }

    @Override
    public void destroy() throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceNotDestroyedFault    {
        //TODO
        super.destroy();
//        LOG.info("Executing operation destroy");
//        try {
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new RuntimeException(ex);
//        }
    }

    @Override
    public org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest parameters) {
        logger.debug("getServiceSecurityMetadata");
        ServiceSecurityMetadata serviceSecurityMetadata = service.getServiceSecurityMetadata();
        GetServiceSecurityMetadataResponse response = new GetServiceSecurityMetadataResponse();
        response.setServiceSecurityMetadata(serviceSecurityMetadata);
        return response;
    }

    public GetDelegatedCredentialResponse getDelegatedCredential(GetDelegatedCredentialRequest parameters) throws DelegationFaultFaultMessage, PermissionDeniedFaultFaultMessage, CDSInternalFaultFaultMessage {
        String message = "getDelegatedCredential";
        GetDelegatedCredentialResponse boxedResult = new GetDelegatedCredentialResponse();
        try {
            boxedResult.setCertificateChain(service.getDelegatedCredential(getCallerId(), parameters.getPublicKey().getPublicKey()));
        } catch (DelegationException e) {
            throw new DelegationFaultFaultMessage(message, e.getFault());
        } catch (PermissionDeniedException e) {
            throw new PermissionDeniedFaultFaultMessage(message, e.getFault());
        } catch (CDSInternalException e) {
            throw new CDSInternalFaultFaultMessage(message, e.getFault());
        }
        return boxedResult;
    }

    private String getCallerId() {
        return WebServiceCallerId.getCallerId(wsContext);
    }

}
