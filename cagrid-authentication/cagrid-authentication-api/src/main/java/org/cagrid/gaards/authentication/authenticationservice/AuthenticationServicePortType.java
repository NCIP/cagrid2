package org.cagrid.gaards.authentication.authenticationservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.3
 * 2014-05-01T13:50:43.532-04:00
 * Generated source version: 2.6.3
 * 
 */
@WebService(targetNamespace = "http://authentication.gaards.cagrid.org/AuthenticationService", name = "AuthenticationServicePortType")
@XmlSeeAlso({gov.nih.nci.cagrid.metadata.ObjectFactory.class, org.cagrid.gaards.authentication.types.ObjectFactory.class, gov.nih.nci.cagrid.introduce.security.servicesecurity.ObjectFactory.class, org.xmlsoap.schemas.ws._2004._03.addressing.ObjectFactory.class, org.w3._2000._09.xmldsig.ObjectFactory.class, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.ObjectFactory.class, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01.ObjectFactory.class, ObjectFactory.class, org.cagrid.gaards.authentication.service.ObjectFactory.class, org.oasis.names.tc.saml.assertion.ObjectFactory.class, org.cagrid.gaards.credentials.ObjectFactory.class, gov.nih.nci.cagrid.metadata.security.ObjectFactory.class, gov.nih.nci.cagrid.metadata.common.ObjectFactory.class, org.cagrid.gaards.authentication.ObjectFactory.class, org.cagrid.gaards.authentication.lockout.ObjectFactory.class, org.cagrid.gaards.authentication.faults.ObjectFactory.class, gov.nih.nci.cagrid.metadata.service.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface AuthenticationServicePortType {

    @WebResult(name = "GetLockedOutUsersResponse", targetNamespace = "http://authentication.gaards.cagrid.org/AuthenticationService", partName = "parameters")
    @WebMethod
    public GetLockedOutUsersResponse getLockedOutUsers(
        @WebParam(partName = "parameters", name = "GetLockedOutUsersRequest", targetNamespace = "http://authentication.gaards.cagrid.org/AuthenticationService")
        GetLockedOutUsersRequest parameters
    );

    @WebResult(name = "QueryResourcePropertiesResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "QueryResourcePropertiesResponse")
    @WebMethod(operationName = "QueryResourceProperties")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse queryResourceProperties(
        @WebParam(partName = "QueryResourcePropertiesRequest", name = "QueryResourceProperties", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties queryResourcePropertiesRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault;

    @WebResult(name = "GetResourcePropertyResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "GetResourcePropertyResponse")
    @WebMethod(operationName = "GetResourceProperty")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse getResourceProperty(
        @WebParam(partName = "GetResourcePropertyRequest", name = "GetResourceProperty", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
        javax.xml.namespace.QName getResourcePropertyRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;

    @WebResult(name = "GetMultipleResourcePropertiesResponse", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd", partName = "GetMultipleResourcePropertiesResponse")
    @WebMethod(operationName = "GetMultipleResourceProperties")
    public org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
        @WebParam(partName = "GetMultipleResourcePropertiesRequest", name = "GetMultipleResourceProperties", targetNamespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd")
        org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties getMultipleResourcePropertiesRequest
    ) throws org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault, org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;

    @WebResult(name = "GetServiceSecurityMetadataResponse", targetNamespace = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity", partName = "parameters")
    @WebMethod
    public gov.nih.nci.cagrid.introduce.security.servicesecurity.GetServiceSecurityMetadataResponse getServiceSecurityMetadata(
        @WebParam(partName = "parameters", name = "GetServiceSecurityMetadataRequest", targetNamespace = "http://security.introduce.cagrid.nci.nih.gov/ServiceSecurity")
        gov.nih.nci.cagrid.introduce.security.servicesecurity.GetServiceSecurityMetadataRequest parameters
    );

    @WebResult(name = "AuthenticateUserResponse", targetNamespace = "http://authentication.gaards.cagrid.org/AuthenticationService", partName = "parameters")
    @WebMethod
    public AuthenticateUserResponse authenticateUser(
        @WebParam(partName = "parameters", name = "AuthenticateUserRequest", targetNamespace = "http://authentication.gaards.cagrid.org/AuthenticationService")
        AuthenticateUserRequest parameters
    ) throws InvalidCredentialFaultFaultMessage, CredentialNotSupportedFaultFaultMessage, InsufficientAttributeFaultFaultMessage, AuthenticationProviderFaultFaultMessage;
}
