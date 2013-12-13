package org.cagrid.dorian.service.wsrf;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.interceptor.SoapPreProtocolOutInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OutboundSamlCanonicalizerInterceptor extends AbstractSamlCanonicalizerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(OutboundSamlCanonicalizerInterceptor.class);
    // It's stupid JAXB doesn't generate constants for these...
    // TODO: could probably take these in as parameters
    public static final QName AUTHENTICATE_USER_QNAME = new QName("http://cagrid.nci.nih.gov/Dorian",
            "authenticateUser");

    public OutboundSamlCanonicalizerInterceptor() {
        super(Phase.PRE_STREAM);
        addBefore(SoapPreProtocolOutInterceptor.class.getName());
    }

    @Override
    protected boolean doesOutboundMessageApply(Message message) {
        BindingOperationInfo operationInfo = message.getExchange().getBindingOperationInfo();
        if (operationInfo != null && operationInfo.getName() != null) {
            if (operationInfo.getName().equals(AUTHENTICATE_USER_QNAME)) {
                LOG.debug("Matching operation:" + operationInfo.getName());
                return true;
            } else {
                LOG.debug("Skipped operation:" + operationInfo.getName());
                return false;
            }

        } else {
            LOG.warn("Unable to determine whether to apply canonicalization as operation info was null. Ensure the Phase is POST_PROTOCOL, and this is running on the inbound path. Returning false.");
            return false;
        }
    }

    @Override
    protected boolean doesInboundMessageApply(Message message) {
        return false;
    }

}
