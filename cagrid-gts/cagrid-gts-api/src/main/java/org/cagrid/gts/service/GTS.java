package org.cagrid.gts.service;

import java.rmi.RemoteException;

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

public interface GTS {

    public gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata getServiceSecurityMetadata();

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
    public org.cagrid.gts.model.TrustedAuthority addTrustedAuthority(org.cagrid.gts.model.TrustedAuthority ta) throws RemoteException, GTSInternalException,
            IllegalTrustedAuthorityException, PermissionDeniedException;

    /**
     * Discover a list of trusted authorities that meets a specified trust criteria.
     * 
     * @param filter
     */
    public org.cagrid.gts.model.TrustedAuthority[] findTrustedAuthorities(org.cagrid.gts.model.TrustedAuthorityFilter filter) throws RemoteException;

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
    public void removeTrustedAuthority(java.lang.String trustedAuthorityName) throws RemoteException, GTSInternalException, InvalidTrustedAuthorityException,
            PermissionDeniedException;

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
    public void addPermission(org.cagrid.gts.model.Permission permission) throws RemoteException, GTSInternalException, IllegalPermissionException,
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
    public org.cagrid.gts.model.Permission[] findPermissions(org.cagrid.gts.model.PermissionFilter filter) throws RemoteException, GTSInternalException,
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
    public void revokePermission(org.cagrid.gts.model.Permission permission) throws RemoteException, GTSInternalException, InvalidPermissionException,
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
    public void updateTrustedAuthority(org.cagrid.gts.model.TrustedAuthority ta) throws RemoteException, GTSInternalException,
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
    public void addTrustLevel(org.cagrid.gts.model.TrustLevel trustLevel) throws RemoteException, GTSInternalException, IllegalTrustLevelException,
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
    public void updateTrustLevel(org.cagrid.gts.model.TrustLevel trustLevel) throws RemoteException, GTSInternalException, InvalidTrustLevelException,
            IllegalTrustLevelException, PermissionDeniedException;

    /**
     * List the trust level define for the GTS.
     * 
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     */
    public org.cagrid.gts.model.TrustLevel[] getTrustLevels() throws RemoteException, GTSInternalException;

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
    public void removeTrustLevel(java.lang.String trustLevelName) throws RemoteException, GTSInternalException, InvalidTrustLevelException,
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
    public void addAuthority(org.cagrid.gts.model.AuthorityGTS authorityGTS) throws RemoteException, GTSInternalException, IllegalAuthorityException,
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
    public void updateAuthority(org.cagrid.gts.model.AuthorityGTS authorityGTS) throws RemoteException, GTSInternalException, IllegalAuthorityException,
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
    public void updateAuthorityPriorities(org.cagrid.gts.model.AuthorityPriorityUpdate authorityPriorityUpdate) throws RemoteException, GTSInternalException,
            IllegalAuthorityException, PermissionDeniedException;

    /**
     * List the GTS's authorities.
     * 
     * @throws GTSInternalFault
     *             An unexpected internal GTS error.
     */
    public org.cagrid.gts.model.AuthorityGTS[] getAuthorities() throws RemoteException, GTSInternalException;

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
    public void removeAuthority(java.lang.String serviceURI) throws RemoteException, GTSInternalException, InvalidAuthorityException, PermissionDeniedException;

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
    public void updateCRL(java.lang.String trustedAuthorityName, org.cagrid.gts.model.X509CRL crl) throws RemoteException, GTSInternalException,
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
    public boolean validate(org.cagrid.gts.model.X509Certificate[] chain, org.cagrid.gts.model.TrustedAuthorityFilter filter) throws RemoteException,
            GTSInternalException, CertificateValidationException;

    // public org.cagrid.wsrf.properties.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
    // org.cagrid.wsrf.properties.GetMultipleResourceProperties_Element params) throws RemoteException;
    //
    // public org.cagrid.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName params) throws RemoteException;
    //
    // public org.cagrid.wsrf.properties.QueryResourcePropertiesResponse queryResourceProperties(org.cagrid.wsrf.properties.QueryResourceProperties_Element
    // params)
    // throws RemoteException;

}
