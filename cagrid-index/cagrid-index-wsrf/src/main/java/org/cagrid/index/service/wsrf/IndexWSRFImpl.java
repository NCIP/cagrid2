package org.cagrid.index.service.wsrf;

import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.cagrid.core.resource.SimpleResourceKey;
import org.cagrid.index.aggregator.types.AggregatorConfig;
import org.cagrid.index.aggregator.types.AggregatorContent;
import org.cagrid.index.aggregator.types.PairedKeyType;
import org.cagrid.index.aggregator.utils.AggregatorUtils;
import org.cagrid.index.service.IndexService;
import org.cagrid.index.types.BigIndexContentIDList;
import org.cagrid.index.wsrf.stubs.BigIndexPortTypeImpl;
import org.cagrid.index.wsrf.stubs.GetContentResponse;
import org.cagrid.wsrf.properties.ResourceKey;
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
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.Add;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01.EntryType;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.AddRefusedFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.ContentCreationFailedFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.UnsupportedMemberInterfaceFault;
import org.xmlsoap.schemas.ws._2004._03.addressing.AttributedURI;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;
import org.xmlsoap.schemas.ws._2004._03.addressing.ReferencePropertiesType;

public class IndexWSRFImpl extends BigIndexPortTypeImpl {
    static public final QName KEY = new QName("http://mds.globus.org/inmemoryservicegroup", "ServiceGroupKey");
    private ResourceKey key = new SimpleResourceKey(KEY, UUID.randomUUID().toString());

    private static final Logger LOG = Logger.getLogger(IndexWSRFImpl.class.getName());

    private IndexService indexService;

    @javax.annotation.Resource
    private WebServiceContext wsContext;

    public IndexWSRFImpl(IndexService indexService) {
        this.indexService = indexService;
    }

    @Override
    public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
            GetMultipleResourceProperties getMultipleResourcePropertiesRequest) throws ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        // TODO Auto-generated method stub
        return super.getMultipleResourceProperties(getMultipleResourcePropertiesRequest);
    }

    @Override
    public GetContentResponse getContent(BigIndexContentIDList getContentRequest) {
        // TODO Auto-generated method stub
        return super.getContent(getContentRequest);
    }

    @Override
    public EndpointReferenceType add(Add addRequest) throws ContentCreationFailedFault,
            UnsupportedMemberInterfaceFault, AddRefusedFault {
        EndpointReferenceType memberEPR = addRequest.getMemberEPR();
        Calendar termTime = addRequest.getInitialTerminationTime();
        Object content = addRequest.getContent();

        LOG.info("Request to add:" + memberEPR.getAddress().getValue() + " with terminiation time of:"
                + termTime.getTime() + " using content:" + content);

        if (content instanceof AggregatorContent) {
            AggregatorContent aggCon = (AggregatorContent) content;
            AggregatorConfig config = aggCon.getAggregatorConfig();
            // TODO: handle config
            LOG.info("Add using config:" + config);
        } else {
            LOG.warning("Got request to add using unsupported content type:" + content.getClass() + ", refusing add.");
            throw new AddRefusedFault("Unsupported content type:" + content);
        }

        MessageContext msgContext = wsContext.getMessageContext();
        HttpServletRequest request = (HttpServletRequest) msgContext.get("HTTP.REQUEST");
        String transportURL = request.getRequestURL().toString();

        // EndpointReferenceType entryEPR = null;
        EndpointReferenceType serviceGroupEPR = null;
        serviceGroupEPR = createEndpointReference(transportURL, null);

        if (AggregatorUtils.detectLoopback(memberEPR, serviceGroupEPR)) {
            LOG.warning("Loopback or duplicate registrant address submitted");
            throw new AddRefusedFault("Loopback or duplicate registrant address submitted");
        }

        EntryType entry = new EntryType();
        entry.setContent(content);
        entry.setMemberServiceEPR(memberEPR);
        entry.setServiceGroupEntryEPR(serviceGroupEPR);

        // TODO: change what we pass into the service?
        String entryId = this.indexService.add(entry);
        ResourceKey entryKey = getResourceKey(entryId);

        // construct an EPR to entry through the entry service.
        // TODO: is there a better way to get the URL to use?
        transportURL = transportURL + "Entry";
        EndpointReferenceType entryEPR = createEndpointReference(transportURL, entryKey);
        // TODO: need an "entry" resource to set this stuff on
        // entry.setEntryEPR(entryEPR);
        // // set initial termination time
        // entry.setTerminationTime(termTime);

        return entryEPR;
    }

    @Override
    public GetResourcePropertyResponse getResourceProperty(QName getResourcePropertyRequest)
            throws ResourceUnknownFault, InvalidResourcePropertyQNameFault {
        // TODO Auto-generated method stub
        return super.getResourceProperty(getResourcePropertyRequest);
    }

    @Override
    public QueryResourcePropertiesResponse queryResourceProperties(
            QueryResourceProperties queryResourcePropertiesRequest) throws UnknownQueryExpressionDialectFault,
            InvalidQueryExpressionFault, QueryEvaluationErrorFault, ResourceUnknownFault,
            InvalidResourcePropertyQNameFault {
        // TODO Auto-generated method stub
        return super.queryResourceProperties(queryResourcePropertiesRequest);
    }

    // private DelegatedCredentialReference getDelegatedCredentialRefernce(DelegationIdentifier id) throws
    // CDSInternalFaultFaultMessage {
    //
    // try {
    // MessageContext msgContext = wsContext.getMessageContext();
    // HttpServletRequest request = (HttpServletRequest) msgContext.get("HTTP.REQUEST");
    // String transportURL = request.getRequestURL().toString();
    // // TODO: fix this to use the property... but deal with handling which endpoint they came in on
    // // this currently assumes the cds and dcs URLs are the same up to the last / (the old code did too)
    // transportURL = transportURL.substring(0, transportURL.lastIndexOf('/') + 1);
    // transportURL += "DelegatedCredential";
    //
    // EndpointReferenceType epr = createEndpointReference(transportURL, getResourceKey(id));
    // DelegatedCredentialReference response = new DelegatedCredentialReference();
    // response.setEndpointReference(epr);
    // return response;
    // } catch (Exception e) {
    // logger.error(e.getMessage(), e);
    // throw new CDSInternalFaultFaultMessage("Unexpected error creating EPR.", e);
    // }
    // }

    private EndpointReferenceType createEndpointReference(String address, ResourceKey key) {
        EndpointReferenceType reference = new EndpointReferenceType();
        if (key != null) {
            ReferencePropertiesType referenceProperties = new ReferencePropertiesType();

            SOAPElement elem = key.toSOAPElement();

            setAny(referenceProperties, elem);

            reference.setReferenceProperties(referenceProperties);
        }

        AttributedURI uri = new AttributedURI();
        uri.setValue(address);
        reference.setAddress(uri);

        return reference;
    }

    private ResourceKey getResourceKey(String entryId) {
        PairedKeyType pk = new PairedKeyType();
        pk.setGroupKey((String) this.getKey().getValue());
        pk.setEntryKey(entryId);
        return new SimpleResourceKey(IndexEntryWSRFImpl.ENTRY_KEY, pk);

    }

    public ResourceKey getKey() {
        return key;
    }


    private void setAny(ReferencePropertiesType object, SOAPElement value) {
        if (value == null || object == null) {
            return;
        }
        if (!(value instanceof SOAPBodyElement)) {
            throw new IllegalArgumentException();
        }
        object.getAny().add(value);
    }

}
