
package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-05-01T13:50:43.258-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "ResourceUnknownFault", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
public class ResourceUnknownFault extends Exception {
    
    private org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.ResourceUnknownFaultType resourceUnknownFault;

    public ResourceUnknownFault() {
        super();
    }
    
    public ResourceUnknownFault(String message) {
        super(message);
    }
    
    public ResourceUnknownFault(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceUnknownFault(String message, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.ResourceUnknownFaultType resourceUnknownFault) {
        super(message);
        this.resourceUnknownFault = resourceUnknownFault;
    }

    public ResourceUnknownFault(String message, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.ResourceUnknownFaultType resourceUnknownFault, Throwable cause) {
        super(message, cause);
        this.resourceUnknownFault = resourceUnknownFault;
    }

    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.ResourceUnknownFaultType getFaultInfo() {
        return this.resourceUnknownFault;
    }
}
