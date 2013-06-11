package org.cagrid.gts.service.wsrf;

import javax.xml.ws.WebServiceContext;

import org.cagrid.gaards.authentication.WebServiceCallerId;
import org.cagrid.gts.service.GTS;
import org.cagrid.gts.wsrf.stubs.GTSPortTypeImpl;
import org.cagrid.wsrf.properties.ResourceHome;
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

    // @Override
    // public GetMembershipsResponse getMemberships(GetMembershipsRequest parameters) throws GroupNotFoundFaultFaultMessage, GridGrouperRuntimeFaultFaultMessage
    // {
    // String message = "getMemberships";
    // try {
    // List<MembershipDescriptor> memberships = gts.getMemberships(getCallerId(), parameters.getGroup().getGroupIdentifier(),
    // parameters.getFilter().getMemberFilter());
    // GetMembershipsResponse response = new GetMembershipsResponse();
    // response.getMembershipDescriptor().addAll(memberships);
    // return response;
    // } catch (GridGrouperRuntimeException e) {
    // throw new GridGrouperRuntimeFaultFaultMessage(message + ":" + e.getMessage(), e.getFault());
    // } catch (GroupNotFoundException e) {
    // throw new GroupNotFoundFaultFaultMessage(message + ":" + e.getMessage(), e.getFault());
    // }
    // }

    private String getCallerId() {
        String callerId = WebServiceCallerId.getCallerId(wsContext);
        if (callerId == null)
            callerId = "anonymous";
        logger.info("CallerId = " + callerId);
        return callerId;
    }

}
