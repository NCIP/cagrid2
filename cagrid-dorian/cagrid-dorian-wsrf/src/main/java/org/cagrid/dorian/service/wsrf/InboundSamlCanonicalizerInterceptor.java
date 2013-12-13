package org.cagrid.dorian.service.wsrf;

import javax.xml.namespace.QName;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
//import org.apache.cxf.service.model.BindingOperationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InboundSamlCanonicalizerInterceptor extends AbstractSamlCanonicalizerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(InboundSamlCanonicalizerInterceptor.class);
    // It's stupid JAXB doesn't generate constants for these...
    // TODO: could probably take these in as parameters
    public static final QName REQUEST_USER_CERTIFICATE_QNAME = new QName("http://cagrid.nci.nih.gov/Dorian",
            "requestUserCertificate");

    public InboundSamlCanonicalizerInterceptor() {
        super(Phase.POST_PROTOCOL);
    }

    @Override
    protected boolean doesOutboundMessageApply(Message message) {
        return false;
    }

    @Override
    protected boolean doesInboundMessageApply(Message message) {
        return false;
        // TODO: This doesn't work doing this here, as JAXB discards the whitespace stuff we clean up anyway, so it still needs to be
        // canonicalized again.
        // BindingOperationInfo operationInfo = message.getExchange().getBindingOperationInfo();
        // if (operationInfo != null && operationInfo.getName() != null) {
        // if (operationInfo.getName().equals(REQUEST_USER_CERTIFICATE_QNAME)) {
        // LOG.debug("Matching operation:" + operationInfo.getName());
        // return true;
        // } else {
        // LOG.debug("Skipped operation:" + operationInfo.getName());
        // return false;
        // }
        //
        // } else {
        // LOG.warn("Unable to determine whether to apply canonicalization as operation info was null. Ensure the Phase is POST_PROTOCOL, and this is running on the inbound path. Returning false.");
        // return false;
        // }

    }

}
