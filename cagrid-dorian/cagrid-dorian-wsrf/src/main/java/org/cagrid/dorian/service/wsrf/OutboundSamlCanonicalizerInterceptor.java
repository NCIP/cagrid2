package org.cagrid.dorian.service.wsrf;

import org.apache.cxf.binding.soap.interceptor.SoapPreProtocolOutInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OutboundSamlCanonicalizerInterceptor extends AbstractSamlCanonicalizerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(OutboundSamlCanonicalizerInterceptor.class);

    public OutboundSamlCanonicalizerInterceptor() {
        super(Phase.PRE_STREAM);
        addBefore(SoapPreProtocolOutInterceptor.class.getName());
    }

    @Override
    protected boolean doesOutboundMessageApply(Message message) {
        //TODO: change this to look for the messages to apply it to
        
        return true;
    }

    @Override
    protected boolean doesInboundMessageApply(Message message) {
        return false;
    }

}
