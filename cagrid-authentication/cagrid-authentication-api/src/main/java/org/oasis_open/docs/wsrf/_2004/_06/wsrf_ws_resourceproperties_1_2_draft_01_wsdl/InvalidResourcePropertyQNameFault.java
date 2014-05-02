
package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-05-01T13:50:43.245-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "InvalidResourcePropertyQNameFault", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
public class InvalidResourcePropertyQNameFault extends Exception {
    
    private org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.InvalidResourcePropertyQNameFaultType invalidResourcePropertyQNameFault;

    public InvalidResourcePropertyQNameFault() {
        super();
    }
    
    public InvalidResourcePropertyQNameFault(String message) {
        super(message);
    }
    
    public InvalidResourcePropertyQNameFault(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidResourcePropertyQNameFault(String message, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.InvalidResourcePropertyQNameFaultType invalidResourcePropertyQNameFault) {
        super(message);
        this.invalidResourcePropertyQNameFault = invalidResourcePropertyQNameFault;
    }

    public InvalidResourcePropertyQNameFault(String message, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.InvalidResourcePropertyQNameFaultType invalidResourcePropertyQNameFault, Throwable cause) {
        super(message, cause);
        this.invalidResourcePropertyQNameFault = invalidResourcePropertyQNameFault;
    }

    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.InvalidResourcePropertyQNameFaultType getFaultInfo() {
        return this.invalidResourcePropertyQNameFault;
    }
}
