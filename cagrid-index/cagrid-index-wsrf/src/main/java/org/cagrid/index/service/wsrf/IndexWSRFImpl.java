package org.cagrid.index.service.wsrf;

import java.util.Calendar;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;

import org.cagrid.index.service.IndexService;
import org.cagrid.index.types.BigIndexContentIDList;
import org.cagrid.index.wsrf.stubs.BigIndexPortTypeImpl;
import org.cagrid.index.wsrf.stubs.GetContentResponse;
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
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.AddRefusedFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.ContentCreationFailedFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_servicegroup_1_2_draft_01_wsdl.UnsupportedMemberInterfaceFault;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;

public class IndexWSRFImpl extends BigIndexPortTypeImpl {
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
        // TODO Auto-generated method stub
        EndpointReferenceType memberEPR = addRequest.getMemberEPR();
        Calendar termTime = addRequest.getInitialTerminationTime();
        Object content = addRequest.getContent();

        System.out.println("Request to add:" + memberEPR + " with terminiation time of:" + termTime + " using content:"
                + content);

        return super.add(addRequest);
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

}
