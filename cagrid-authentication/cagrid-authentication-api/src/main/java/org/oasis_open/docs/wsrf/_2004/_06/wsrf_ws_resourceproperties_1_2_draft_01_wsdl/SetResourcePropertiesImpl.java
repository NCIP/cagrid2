
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-05-01T13:50:43.460-04:00
 * Generated source version: 2.6.3
 * 
 */

@javax.jws.WebService(
                      serviceName = "$service.ServiceName",
                      targetNamespace = "$service.Namespace",
                      wsdlLocation = "/schema/org/cagrid/AuthenticationService/AuthenticationService.wsdl",
                      endpointInterface = "org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.SetResourceProperties")
                      
public class SetResourcePropertiesImpl implements SetResourceProperties {

    private static final Logger LOG = Logger.getLogger(SetResourcePropertiesImpl.class.getName());

    /* (non-Javadoc)
     * @see org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.SetResourceProperties#setResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.SetResourceProperties  setResourcePropertiesRequest )*
     */
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.SetResourcePropertiesResponse setResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.SetResourceProperties setResourcePropertiesRequest) throws InvalidResourcePropertyQNameFault , ResourceUnknownFault , UnableToModifyResourcePropertyFault , SetResourcePropertyRequestFailedFault , InvalidSetResourcePropertiesRequestContentFault    { 
        LOG.info("Executing operation setResourceProperties");
        System.out.println(setResourcePropertiesRequest);
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.SetResourcePropertiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new InvalidResourcePropertyQNameFault("InvalidResourcePropertyQNameFault...");
        //throw new ResourceUnknownFault("ResourceUnknownFault...");
        //throw new UnableToModifyResourcePropertyFault("UnableToModifyResourcePropertyFault...");
        //throw new SetResourcePropertyRequestFailedFault("SetResourcePropertyRequestFailedFault...");
        //throw new InvalidSetResourcePropertiesRequestContentFault("InvalidSetResourcePropertiesRequestContentFault...");
    }

}