
package org.cagrid.dorian;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-06-10T14:11:33.355-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "InvalidUserFault", targetNamespace = "http://cagrid.nci.nih.gov/Dorian/types")
public class InvalidUserFaultFaultMessage extends Exception {
    
    private org.cagrid.dorian.types.InvalidUserFault invalidUserFault;

    public InvalidUserFaultFaultMessage() {
        super();
    }
    
    public InvalidUserFaultFaultMessage(String message) {
        super(message);
    }
    
    public InvalidUserFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserFaultFaultMessage(String message, org.cagrid.dorian.types.InvalidUserFault invalidUserFault) {
        super(message);
        this.invalidUserFault = invalidUserFault;
    }

    public InvalidUserFaultFaultMessage(String message, org.cagrid.dorian.types.InvalidUserFault invalidUserFault, Throwable cause) {
        super(message, cause);
        this.invalidUserFault = invalidUserFault;
    }

    public org.cagrid.dorian.types.InvalidUserFault getFaultInfo() {
        return this.invalidUserFault;
    }
}
