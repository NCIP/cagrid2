package org.cagrid.cds.service.wsrf;

import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Message;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.delegatedcredential.service.DelegatedCredentialService;
import org.cagrid.delegatedcredential.wsrf.stubs.*;
import org.cagrid.gaards.authentication.WebServiceCallerId;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse;
import org.cagrid.wsrf.properties.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.List;

public class DCSWSRFImpl extends DelegatedCredentialPortTypeImpl {

    // TODO: should be able to get this from here, but its hidden in the service DelegatedCredentialResourceHome.RESOURCE_KEY
    public static final String SERVICE_NS = "http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential";
    public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "DelegatedCredentialKey");

    private final Logger logger;

    private final DelegatedCredentialService service;

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    public DCSWSRFImpl(DelegatedCredentialService service) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.service = service;
    }

    @Override
    public void destroy() throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault,
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceNotDestroyedFault {
        DelegationIdentifier did;
        try {
            did = extractDelegationIdentifierFromHeaders();
        } catch (CDSInternalFaultFaultMessage e) {
            logger.error("Problem locating delegation identifier:" + e.getMessage(), e);
            throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault(
                    "Problem locating delegation identifier:" + e.getMessage());
        }
        if (did == null) {
            logger.error("Unable to locate delegation identifier");
            throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceUnknownFault(
                    "Unable to locate delegation identifier");
        } else {
            try {
                this.service.suspendDelegatedCredential(getCallerId(), did);
            } catch (Exception e) {
                logger.error("Problem destroying resource:" + e.getMessage(), e);
                throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourcelifetime_1_2_draft_01_wsdl.ResourceNotDestroyedFault(
                        "Unable to locate delegation identifier" + e.getMessage());
            }
        }
    }

    @Override
    public org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(
            org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest parameters) {
        logger.debug("getServiceSecurityMetadata");
        ServiceSecurityMetadata serviceSecurityMetadata = service.getServiceSecurityMetadata();
        GetServiceSecurityMetadataResponse response = new GetServiceSecurityMetadataResponse();
        response.setServiceSecurityMetadata(serviceSecurityMetadata);
        return response;
    }

    private List<Header> getHeaders() {
        MessageContext messageContext = wsContext.getMessageContext();
        if (messageContext == null || !(messageContext instanceof WrappedMessageContext)) {
            return null;
        }

        Message message = ((WrappedMessageContext) messageContext).getWrappedMessage();
        List<Header> headers = CastUtils.cast((List<?>) message.get(Header.HEADER_LIST));
        return headers;
    }

    public GetDelegatedCredentialResponse getDelegatedCredential(GetDelegatedCredentialRequest parameters) throws DelegationFaultFaultMessage,
            PermissionDeniedFaultFaultMessage, CDSInternalFaultFaultMessage {
        String message = "getDelegatedCredential";
        GetDelegatedCredentialResponse boxedResult = new GetDelegatedCredentialResponse();
        DelegationIdentifier did = extractDelegationIdentifierFromHeaders();
        if (did == null) {
            logger.error("Unable to locate delegation identifier");
            throw new CDSInternalFaultFaultMessage("Unable to locate delegation identifier");
        }

        try {
            boxedResult.setCertificateChain(service.getDelegatedCredential(getCallerId(), did, parameters.getPublicKey().getPublicKey()));
        } catch (DelegationException e) {
            throw new DelegationFaultFaultMessage(message, e.getFault());
        } catch (PermissionDeniedException e) {
            throw new PermissionDeniedFaultFaultMessage(message, e.getFault());
        } catch (CDSInternalException e) {
            throw new CDSInternalFaultFaultMessage(message, e.getFault());
        } catch (ResourceException e) {
            throw new CDSInternalFaultFaultMessage("Problem locating resource:" + e.getMessage());
        }

        return boxedResult;
    }

    private DelegationIdentifier getDelegationId(Node parent) {
        if (parent == null) {
            return null;
        }

        if (parent.getLocalName() != null && parent.getLocalName().equals("delegationId")) {
            DelegationIdentifier did = new DelegationIdentifier();
            did.setDelegationId(Long.parseLong(parent.getTextContent()));
            logger.debug("Found delegation id: " + did.getDelegationId());
            return did;
        }

        if (parent.hasChildNodes()) {
            NodeList nl = parent.getChildNodes();
            for (int i=0; i < nl.getLength(); i++) {
                DelegationIdentifier did = getDelegationId(nl.item(i));
                if (did != null) {
                    return did;
                }
            }
        }

        return null;
    }

    private DelegationIdentifier extractDelegationIdentifierFromHeaders() throws CDSInternalFaultFaultMessage {
        DelegationIdentifier did = null;

        List<Header> headers = getHeaders();
        if (headers != null) {
            for (Header h : headers) {
                QName hName = h.getName();
                if (hName.equals(RESOURCE_KEY)) {
                    logger.debug("Found resource key.");
                    Object o = h.getObject();
                    try {
                        did = getDelegationId((Node) o);
                        if (did != null) {
                            break;
                        }
                        // JAXBContext jaxbContext = JAXBUtils.getJAXBContext(DelegationIdentifier.class);
                        // o = jaxbContext.createUnmarshaller().unmarshal((Node) o);
                        // if (o instanceof DelegationIdentifier) {
                        // did = (DelegationIdentifier) o;
                        // logger.debug("Set resource key to:" + did);
                        // break;
                        // } else {
                        // logger.error("Problem deserializing soap header; got unexpected type.");
                        // throw new CDSInternalFaultFaultMessage("Problem deserializing soap header; got unexpected type.");
                        // }
                        // } catch (JAXBException e) {
                    } catch (Exception e) {
                        logger.error("Problem deserializing soap header: " + e.toString(), e);
                        throw new CDSInternalFaultFaultMessage("Problem deserializing soap header: " + e.getMessage());
                    }
                } else {
                    logger.debug("Ignoring header:" + hName);
                }
            }
        } else {
            logger.error("Unable to locate SOAP headers.");
            throw new CDSInternalFaultFaultMessage("Unable to locate SOAP headers.");
        }
        return did;
    }

    private String getCallerId() {
        return WebServiceCallerId.getCallerId(wsContext);
    }

}
