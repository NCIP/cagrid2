
package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-05-01T13:50:43.272-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "InvalidQueryExpressionFault", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
public class InvalidQueryExpressionFault extends Exception {
    
    private org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.InvalidQueryExpressionFaultType invalidQueryExpressionFault;

    public InvalidQueryExpressionFault() {
        super();
    }
    
    public InvalidQueryExpressionFault(String message) {
        super(message);
    }
    
    public InvalidQueryExpressionFault(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidQueryExpressionFault(String message, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.InvalidQueryExpressionFaultType invalidQueryExpressionFault) {
        super(message);
        this.invalidQueryExpressionFault = invalidQueryExpressionFault;
    }

    public InvalidQueryExpressionFault(String message, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.InvalidQueryExpressionFaultType invalidQueryExpressionFault, Throwable cause) {
        super(message, cause);
        this.invalidQueryExpressionFault = invalidQueryExpressionFault;
    }

    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.InvalidQueryExpressionFaultType getFaultInfo() {
        return this.invalidQueryExpressionFault;
    }
}
