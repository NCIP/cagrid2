
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package gov.nih.nci.cagrid.introduce.security.servicesecurity;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-05-01T13:50:43.431-04:00
 * Generated source version: 2.6.3
 * 
 */

@javax.jws.WebService(
                      serviceName = "$service.ServiceName",
                      targetNamespace = "$service.Namespace",
                      wsdlLocation = "/schema/org/cagrid/AuthenticationService/AuthenticationService.wsdl",
                      endpointInterface = "gov.nih.nci.cagrid.introduce.security.servicesecurity.ServiceSecurityPortType")
                      
public class ServiceSecurityPortTypeImpl implements ServiceSecurityPortType {

    private static final Logger LOG = Logger.getLogger(ServiceSecurityPortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see gov.nih.nci.cagrid.introduce.security.servicesecurity.ServiceSecurityPortType#getResourceProperty(javax.xml.namespace.QName  getResourcePropertyRequest )*
     */
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault    { 
        LOG.info("Executing operation getResourceProperty");
        System.out.println(getResourcePropertyRequest);
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault("InvalidResourcePropertyQNameFault...");
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cagrid.introduce.security.servicesecurity.ServiceSecurityPortType#getMultipleResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties  getMultipleResourcePropertiesRequest )*
     */
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties getMultipleResourcePropertiesRequest) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault    { 
        LOG.info("Executing operation getMultipleResourceProperties");
        System.out.println(getMultipleResourcePropertiesRequest);
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault("InvalidResourcePropertyQNameFault...");
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cagrid.introduce.security.servicesecurity.ServiceSecurityPortType#queryResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties  queryResourcePropertiesRequest )*
     */
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse queryResourceProperties(org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties queryResourcePropertiesRequest) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault , org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault    { 
        LOG.info("Executing operation queryResourceProperties");
        System.out.println(queryResourcePropertiesRequest);
        try {
            org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault("ResourceUnknownFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault("UnknownQueryExpressionDialectFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault("InvalidQueryExpressionFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault("QueryEvaluationErrorFault...");
        //throw new org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault("InvalidResourcePropertyQNameFault...");
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cagrid.introduce.security.servicesecurity.ServiceSecurityPortType#getServiceSecurityMetadata(gov.nih.nci.cagrid.introduce.security.servicesecurity.GetServiceSecurityMetadataRequest  parameters )*
     */
    public gov.nih.nci.cagrid.introduce.security.servicesecurity.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(GetServiceSecurityMetadataRequest parameters) { 
        LOG.info("Executing operation getServiceSecurityMetadata");
        System.out.println(parameters);
        try {
            gov.nih.nci.cagrid.introduce.security.servicesecurity.GetServiceSecurityMetadataResponse _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}