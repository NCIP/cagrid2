package org.cagrid.dorian.service;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;
import org.cagrid.dorian.DorianPortType;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2013-06-10T14:11:33.550-04:00
 * Generated source version: 2.6.3
 * 
 */
@WebServiceClient(name = "DorianService", 
                  wsdlLocation = "/schema/org/cagrid/dorian/Dorian_service.wsdl",
                  targetNamespace = "http://cagrid.nci.nih.gov/Dorian/service") 
public class DorianService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://cagrid.nci.nih.gov/Dorian/service", "DorianService");
    public final static QName DorianPortTypePort = new QName("http://cagrid.nci.nih.gov/Dorian/service", "DorianPortTypePort");
    static {
        URL url = DorianService.class.getResource("/schema/org/cagrid/dorian/Dorian_service.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(DorianService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "/schema/org/cagrid/dorian/Dorian_service.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public DorianService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DorianService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DorianService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns DorianPortType
     */
    @WebEndpoint(name = "DorianPortTypePort")
    public DorianPortType getDorianPortTypePort() {
        return super.getPort(DorianPortTypePort, DorianPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DorianPortType
     */
    @WebEndpoint(name = "DorianPortTypePort")
    public DorianPortType getDorianPortTypePort(WebServiceFeature... features) {
        return super.getPort(DorianPortTypePort, DorianPortType.class, features);
    }

}
