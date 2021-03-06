
package org.cagrid.gaards.authentication;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-06-10T13:48:40.887-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "AuthenticationProviderFault", targetNamespace = "http://gaards.cagrid.org/authentication-faults")
public class AuthenticationProviderFaultFaultMessage extends Exception {
    
    private org.cagrid.gaards.authentication.faults.AuthenticationProviderFault authenticationProviderFault;

    public AuthenticationProviderFaultFaultMessage() {
        super();
    }
    
    public AuthenticationProviderFaultFaultMessage(String message) {
        super(message);
    }
    
    public AuthenticationProviderFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationProviderFaultFaultMessage(String message, org.cagrid.gaards.authentication.faults.AuthenticationProviderFault authenticationProviderFault) {
        super(message);
        this.authenticationProviderFault = authenticationProviderFault;
    }

    public AuthenticationProviderFaultFaultMessage(String message, org.cagrid.gaards.authentication.faults.AuthenticationProviderFault authenticationProviderFault, Throwable cause) {
        super(message, cause);
        this.authenticationProviderFault = authenticationProviderFault;
    }

    public org.cagrid.gaards.authentication.faults.AuthenticationProviderFault getFaultInfo() {
        return this.authenticationProviderFault;
    }
}
