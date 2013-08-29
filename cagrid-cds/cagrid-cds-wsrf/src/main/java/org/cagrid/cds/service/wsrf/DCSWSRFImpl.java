package org.cagrid.cds.service.wsrf;

import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Message;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.core.common.JAXBUtils;
import org.cagrid.core.resource.SimpleResourceKey;
import org.cagrid.delegatedcredential.service.DelegatedCredentialService;
import org.cagrid.delegatedcredential.wsrf.stubs.CDSInternalFaultFaultMessage;
import org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialPortTypeImpl;
import org.cagrid.delegatedcredential.wsrf.stubs.DelegationFaultFaultMessage;
import org.cagrid.delegatedcredential.wsrf.stubs.GetDelegatedCredentialRequest;
import org.cagrid.delegatedcredential.wsrf.stubs.GetDelegatedCredentialResponse;
import org.cagrid.delegatedcredential.wsrf.stubs.PermissionDeniedFaultFaultMessage;
import org.cagrid.gaards.authentication.WebServiceCallerId;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceException;
import org.cagrid.wsrf.properties.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

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
        // TODO: look up the resource using the DelegationIdentifier on the resource home, and remove it
        super.destroy();
        // LOG.info("Executing operation destroy");
        // try {
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // throw new RuntimeException(ex);
        // }
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

        DelegationIdentifier did = null;

        List<Header> headers = getHeaders();
        if (headers != null) {
            for (Header h : headers) {
                QName hName = h.getName();
                if (hName.equals(RESOURCE_KEY)) {
                    logger.debug("Found resource key.");
                    Object o = h.getObject();
                    try {
                        Node node = (Node) o;
                        if (node.getFirstChild().getLocalName().equals("delegationId")) {
                            did = new DelegationIdentifier();
                            did.setDelegationId(Long.parseLong(node.getFirstChild().getTextContent()));
                            break;
                        }
//                        JAXBContext jaxbContext = JAXBUtils.getJAXBContext(DelegationIdentifier.class);
//                        o = jaxbContext.createUnmarshaller().unmarshal((Node) o);
//                        if (o instanceof DelegationIdentifier) {
//                            did = (DelegationIdentifier) o;
//                            logger.debug("Set resource key to:" + did);
//                            break;
//                        } else {
//                            logger.error("Problem deserializing soap header; got unexpected type.");
//                            throw new CDSInternalFaultFaultMessage("Problem deserializing soap header; got unexpected type.");
//                        }
//                    } catch (JAXBException e) {
                    } catch (Exception e) {
                        logger.error("Problem deserializing soap header: " + message, e);
                        throw new CDSInternalFaultFaultMessage("Problem deserializing soap header: " + message);
                    }
                } else {
                    logger.debug("Ignoring header:" + hName);
                }
            }
        } else {
            logger.error("Unable to locate SOAP headers.");
            throw new CDSInternalFaultFaultMessage("Unable to locate SOAP headers.");
        }

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

    private String getCallerId() {
        return WebServiceCallerId.getCallerId(wsContext);
    }

}
