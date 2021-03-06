
package org.cagrid.gaards.authentication.authenticationservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-05-01T13:50:43.290-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "InvalidCredentialFault", targetNamespace = "http://gaards.cagrid.org/authentication-faults")
public class InvalidCredentialFaultFaultMessage extends Exception {
    
    private org.cagrid.gaards.authentication.faults.InvalidCredentialFault invalidCredentialFault;

    public InvalidCredentialFaultFaultMessage() {
        super();
    }
    
    public InvalidCredentialFaultFaultMessage(String message) {
        super(message);
    }
    
    public InvalidCredentialFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCredentialFaultFaultMessage(String message, org.cagrid.gaards.authentication.faults.InvalidCredentialFault invalidCredentialFault) {
        super(message);
        this.invalidCredentialFault = invalidCredentialFault;
    }

    public InvalidCredentialFaultFaultMessage(String message, org.cagrid.gaards.authentication.faults.InvalidCredentialFault invalidCredentialFault, Throwable cause) {
        super(message, cause);
        this.invalidCredentialFault = invalidCredentialFault;
    }

    public org.cagrid.gaards.authentication.faults.InvalidCredentialFault getFaultInfo() {
        return this.invalidCredentialFault;
    }
}
