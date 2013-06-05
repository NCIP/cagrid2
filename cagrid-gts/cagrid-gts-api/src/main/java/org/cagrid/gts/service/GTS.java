package org.cagrid.gts.service;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;

import org.cagrid.gts.model.TrustLevel;
import org.cagrid.gts.service.exception.CertificateValidationException;
import org.cagrid.gts.service.exception.GTSInternalException;
import org.cagrid.gts.service.exception.IllegalAuthorityException;
import org.cagrid.gts.service.exception.IllegalPermissionException;
import org.cagrid.gts.service.exception.IllegalTrustLevelException;
import org.cagrid.gts.service.exception.IllegalTrustedAuthorityException;
import org.cagrid.gts.service.exception.InvalidAuthorityException;
import org.cagrid.gts.service.exception.InvalidPermissionException;
import org.cagrid.gts.service.exception.InvalidTrustLevelException;
import org.cagrid.gts.service.exception.InvalidTrustedAuthorityException;
import org.cagrid.gts.service.exception.PermissionDeniedException;
import org.cagrid.wsrf.properties.ResourceHome;

public interface GTS {

    public gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata getServiceSecurityMetadata();

    public ServiceMetadata getServiceMetadata();

    /**
     * Publish a Certificate Authority to the trust fabric.
     * 
     * @param ta
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws IllegalTrustedAuthorityFault
     *             Illegal certificate authority specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public org.cagrid.gts.model.TrustedAuthority addTrustedAuthority(String callerIdentity, org.cagrid.gts.model.TrustedAuthority ta)
            throws GTSInternalException, IllegalTrustedAuthorityException, PermissionDeniedException;

    /**
     * Discover a list of trusted authorities that meets a specified trust criteria.
     * 
     * @param filter
     * @throws GTSInternalException
     */
    public org.cagrid.gts.model.TrustedAuthority[] findTrustedAuthorities(String callerIdentity, org.cagrid.gts.model.TrustedAuthorityFilter filter)
            throws GTSInternalException;

    /**
     * Remove a certficate authority from the trust fabric.
     * 
     * @param trustedAuthorityName
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws InvalidTrustedAuthorityFault
     *             Invalid certificate authority specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void removeTrustedAuthority(String callerIdentity, java.lang.String trustedAuthorityName) throws GTSInternalException,
            InvalidTrustedAuthorityException, PermissionDeniedException;

    /**
     * Grant a permission to a user or service.
     * 
     * @param permission
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws IllegalPermissionFault
     *             Illegal permission specified by the client.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void addPermission(String callerIdentity, org.cagrid.gts.model.Permission permission) throws GTSInternalException, IllegalPermissionException,
            PermissionDeniedException;

    /**
     * List the permissions granted on the GTS.
     * 
     * @param filter
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public org.cagrid.gts.model.Permission[] findPermissions(String callerIdentity, org.cagrid.gts.model.PermissionFilter filter) throws GTSInternalException,
            PermissionDeniedException;

    /**
     * Revoke a permission that was granted to a user or service.
     * 
     * @param permission
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws InvalidPermissionFault
     *             Invalid permission specified by the client.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void revokePermission(String callerIdentity, org.cagrid.gts.model.Permission permission) throws GTSInternalException, InvalidPermissionException,
            PermissionDeniedException;

    /**
     * Update a certificate authority's information.
     * 
     * @param ta
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws IllegalTrustedAuthorityFault
     *             Illegal certificate authority specified.
     * @throws InvalidTrustedAuthorityFault
     *             Invalid certificate authority specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void updateTrustedAuthority(String callerIdentity, org.cagrid.gts.model.TrustedAuthority ta) throws GTSInternalException,
            IllegalTrustedAuthorityException, InvalidTrustedAuthorityException, PermissionDeniedException;

    /**
     * Create a trust level.
     * 
     * @param trustLevel
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws IllegalTrustLevelFault
     *             Illegal trust level specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void addTrustLevel(String callerIdentity, org.cagrid.gts.model.TrustLevel trustLevel) throws GTSInternalException, IllegalTrustLevelException,
            PermissionDeniedException;

    /**
     * Update a trust level's information.
     * 
     * @param trustLevel
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws InvalidTrustLevelFault
     *             Invalid trust level specified.
     * @throws IllegalTrustLevelFault
     *             Illegal trust level specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void updateTrustLevel(String callerIdentity, org.cagrid.gts.model.TrustLevel trustLevel) throws GTSInternalException, InvalidTrustLevelException,
            IllegalTrustLevelException, PermissionDeniedException;

    /**
     * List the trust level define for the GTS.
     * 
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     */
    public org.cagrid.gts.model.TrustLevel[] getTrustLevels(String callerIdentity) throws GTSInternalException;
    
    
    /**
     * List the trust level define for the GTS, limited to those from the identified source GTS
     * @param callerIdentity
     * @param gtsSourceURI  the source URI of the GTS
     * @return
     * @throws GTSInternalException
     */
    public TrustLevel[] getTrustLevels(String callerIdentity, String gtsSourceURI) throws GTSInternalException ;


    /**
     * Remove a trust level from a GTS.
     * 
     * @param trustLevelName
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws InvalidTrustLevelFault
     *             Invalid trust level specified.
     * @throws IllegalTrustLevelFault
     *             Illegal trust level specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void removeTrustLevel(String callerIdentity, java.lang.String trustLevelName) throws GTSInternalException, InvalidTrustLevelException,
            IllegalTrustLevelException, PermissionDeniedException;

    /**
     * Add an Authority GTS to a GTS.
     * 
     * @param authorityGTS
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws IllegalAuthorityFault
     *             Illegal authority GTS specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void addAuthority(String callerIdentity, org.cagrid.gts.model.AuthorityGTS authorityGTS) throws GTSInternalException, IllegalAuthorityException,
            PermissionDeniedException;

    /**
     * Update the information for an Authority GTS.
     * 
     * @param authorityGTS
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws IllegalAuthorityFault
     *             Illegal authority GTS specified.
     * @throws InvalidAuthorityFault
     *             Invalid authority GTS specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void updateAuthority(String callerIdentity, org.cagrid.gts.model.AuthorityGTS authorityGTS) throws GTSInternalException, IllegalAuthorityException,
            InvalidAuthorityException, PermissionDeniedException;

    /**
     * Update the priorities of a GTS's authorities.
     * 
     * @param authorityPriorityUpdate
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws IllegalAuthorityFault
     *             Illegal authority GTS specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void updateAuthorityPriorities(String callerIdentity, org.cagrid.gts.model.AuthorityPriorityUpdate authorityPriorityUpdate)
            throws GTSInternalException, IllegalAuthorityException, PermissionDeniedException;

    /**
     * List the GTS's authorities.
     * 
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     */
    public org.cagrid.gts.model.AuthorityGTS[] getAuthorities(String callerIdentity) throws GTSInternalException;

    /**
     * Remove one of a GTS's authorities.
     * 
     * @param serviceURI
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws InvalidAuthorityFault
     *             Invalid authority GTS specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void removeAuthority(String callerIdentity, java.lang.String serviceURI) throws GTSInternalException, InvalidAuthorityException,
            PermissionDeniedException;

    /**
     * Publish an updated CRL for a certificate authority.
     * 
     * @param trustedAuthorityName
     * @param crl
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws IllegalTrustedAuthorityFault
     *             Illegal Certificate Authority Specified.
     * @throws InvalidTrustedAuthorityFault
     *             Invalid Certificate Authority Specified.
     * @throws PermissionDeniedFault
     *             Client does not have permission to perform the operation.
     */
    public void updateCRL(String callerIdentity, java.lang.String trustedAuthorityName, org.cagrid.gts.model.X509CRL crl) throws GTSInternalException,
            IllegalTrustedAuthorityException, InvalidTrustedAuthorityException, PermissionDeniedException;

    /**
     * Validate a certificate against the trust fabric.
     * 
     * @param chain
     * @param filter
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     * @throws CertificateValidationFault
     *             The certificate specified is invalid.
     */
    public boolean validate(String callerIdentity, org.cagrid.gts.model.X509Certificate[] chain, org.cagrid.gts.model.TrustedAuthorityFilter filter)
            throws GTSInternalException, CertificateValidationException;

    /**
     * Check whether the given trust level exists
     * 
     * @param name
     *            the name to check
     * @return true iff it exists
     * @throws GTSInternalException
     */
    public boolean doesTrustLevelExist(String callerIdentity, String name) throws GTSInternalException;

    /**
     * Returns the requested trust level
     * 
     * @param name
     * @return
     * @throws GTSInternalException
     * @throws InvalidTrustLevelException
     *             if the requested trustlevel does not exist
     */
    public TrustLevel getTrustLevel(String callerIdentity, String name) throws GTSInternalException, InvalidTrustLevelException;

    // public org.cagrid.wsrf.properties.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(String callerIdentity,
    // org.cagrid.wsrf.properties.GetMultipleResourceProperties_Element params) throws RemoteException;
    //
    // public org.cagrid.wsrf.properties.GetResourcePropertyResponse getResourceProperty(String callerIdentity,javax.xml.namespace.QName params) throws
    // RemoteException;
    //
    // public org.cagrid.wsrf.properties.QueryResourcePropertiesResponse queryResourceProperties(String
    // callerIdentity,org.cagrid.wsrf.properties.QueryResourceProperties_Element
    // params)
    // throws RemoteException;

    public ResourceHome getResourceHome();
}
