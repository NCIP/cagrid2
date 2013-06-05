
package org.cagrid.gts.wsrf.stubs;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-06-04T12:08:10.952-04:00
 * Generated source version: 2.6.3
 */

@WebFault(name = "InvalidPermissionFault", targetNamespace = "http://cagrid.nci.nih.gov/GTS/types")
public class InvalidPermissionFaultFaultMessage extends Exception {
    
    private org.cagrid.gts.types.InvalidPermissionFault invalidPermissionFault;

    public InvalidPermissionFaultFaultMessage() {
        super();
    }
    
    public InvalidPermissionFaultFaultMessage(String message) {
        super(message);
    }
    
    public InvalidPermissionFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPermissionFaultFaultMessage(String message, org.cagrid.gts.types.InvalidPermissionFault invalidPermissionFault) {
        super(message);
        this.invalidPermissionFault = invalidPermissionFault;
    }

    public InvalidPermissionFaultFaultMessage(String message, org.cagrid.gts.types.InvalidPermissionFault invalidPermissionFault, Throwable cause) {
        super(message, cause);
        this.invalidPermissionFault = invalidPermissionFault;
    }

    public org.cagrid.gts.types.InvalidPermissionFault getFaultInfo() {
        return this.invalidPermissionFault;
    }
}
