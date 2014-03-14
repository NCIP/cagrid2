package org.cagrid.gts.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.gts.model.*;
import org.cagrid.gts.service.exception.*;
import org.cagrid.gts.service.impl.db.DBManager;
import org.cagrid.gts.service.impl.db.mysql.MySQLManager;
import org.cagrid.gts.soapclient.GTSSoapClientFactory;
import org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesRequest;
import org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesResponse;
import org.cagrid.gts.wsrf.stubs.GTSPortType;
import org.cagrid.gts.wsrf.stubs.GetTrustLevelsRequest;

import java.util.*;

/**
 * @author <A HREF="MAILTO:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A HREF="MAILTO:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A HREF="MAILTO:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: TrustedAuthorityManager.java,v 1.1 2006/03/08 19:48:46 langella Exp $
 */
public class GTS implements TrustedAuthorityLevelRemover, TrustLevelLookup {

    private Configuration conf;

    private String gtsURI;

    private TrustedAuthorityManager trust;

    private PermissionManager permissions;

    private TrustLevelManager trustLevelManager;

    private GTSAuthorityManager authority;

    private Map<String, GTSPortType> gtsClientCache;

    private Object gtsClientCacheMutex = new Object();

    private Log log;

    private Database db;

    private CredentialManager credmanager = null;

    public GTS(Configuration conf, String gtsURI, CredentialManager credmanager) {
        this.conf = conf;
        this.gtsURI = gtsURI;
        this.credmanager = credmanager;
        this.gtsClientCache = new HashMap<String, GTSPortType>();
        log = LogFactory.getLog(this.getClass().getName());

        DBManager dbManager = new MySQLManager(new MySQLDatabase(this.conf.getConnectionManager(), this.conf.getGTSInternalId()));
        this.db = dbManager.getDatabase();
        trust = new TrustedAuthorityManager(this.gtsURI, this, dbManager);
        trustLevelManager = new TrustLevelManager(this.gtsURI, this, dbManager);
        permissions = new PermissionManager(dbManager);
        authority = new GTSAuthorityManager(gtsURI, conf.getAuthoritySyncTime(), dbManager);
    }

    protected Database getDatabase() {
        return db;
    }

    public TrustedAuthority addTrustedAuthority(TrustedAuthority ta, String callerGridIdentity) throws GTSInternalException, IllegalTrustedAuthorityException,
            PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        return trust.addTrustedAuthority(ta);
    }

    public TrustedAuthority[] findTrustAuthorities(TrustedAuthorityFilter filter) throws GTSInternalException {
        return trust.findTrustAuthorities(filter);
    }

    public boolean validate(X509Certificate cert, TrustedAuthorityFilter filter) throws GTSInternalException, CertificateValidationException {
        X509Certificate[] chain = new X509Certificate[1];
        chain[0] = cert;
        return this.validate(chain, filter);
    }


    public boolean validate(X509Certificate[] chain, TrustedAuthorityFilter filter) throws GTSInternalException, CertificateValidationException {
        GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class, "Operation no longer supported!");
        throw fault;
        // TODO: MIGRATION: implement
        // boolean isValidated = false;
        // TrustedAuthority[] list = trust.findTrustAuthorities(filter);
        // ProxyPathValidator validator = new ProxyPathValidator();
        // if ((list == null) || (list.length == 0)) {
        // CertificateValidationFault fault = new CertificateValidationFault();
        // fault.setFaultString("Could not validate chain, no trusted roots found!!!");
        // throw fault;
        // } else {
        // java.security.cert.X509Certificate[] trustedCerts = new java.security.cert.X509Certificate[list.length];
        // List<java.security.cert.X509CRL> crlList = new ArrayList<java.security.cert.X509CRL>();
        // for (int i = 0; i < list.length; i++) {
        // try {
        // trustedCerts[i] = CertUtil.loadCertificate(list[i].getCertificate().getCertificateEncodedString());
        // } catch (Exception e) {
        // log.error(e.getMessage(), e);
        // GTSInternalFault fault = new GTSInternalFault();
        // fault.setFaultString("Unexpected Error loading the certificate for the trusted authority "
        // + list[i].getName() + "!!!");
        // throw fault;
        // }
        //
        // try {
        // if (list[i].getCRL() != null) {
        // crlList.add(CertUtil.loadCRL(list[i].getCRL().getCrlEncodedString()));
        // }
        // } catch (Exception e) {
        // log.error(e.getMessage(), e);
        // GTSInternalFault fault = new GTSInternalFault();
        // fault.setFaultString("Unexpected Error loading the CRL for the trusted authority "
        // + list[i].getName() + "!!!");
        // throw fault;
        // }
        //
        // }
        // java.security.cert.X509CRL[] crls = new java.security.cert.X509CRL[crlList.size()];
        // for (int i = 0; i < crlList.size(); i++) {
        // crls[i] = (java.security.cert.X509CRL) crlList.get(i);
        // }
        //
        // java.security.cert.X509Certificate[] certChain = new java.security.cert.X509Certificate[chain.length];
        // for (int i = 0; i < crlList.size(); i++) {
        // try {
        // certChain[i] = CertUtil.loadCertificate(chain[i].getCertificateEncodedString());
        // } catch (Exception e) {
        // log.error(e.getMessage(), e);
        // GTSInternalFault fault = new GTSInternalFault();
        // fault.setFaultString("Unexpected Error formatting the certificate chain.!!!");
        // throw fault;
        // }
        // }
        // try {
        // validator.validate(certChain, trustedCerts, CertificateRevocationLists
        // .getCertificateRevocationLists(crls));
        // isValidated = true;
        // } catch (Exception e) {
        // CertificateValidationFault fault = new CertificateValidationFault();
        // fault.setFaultString("Could not validate chain " + e.getMessage());
        // throw fault;
        // }
        // }
        // return isValidated;
    }

    public void updateTrustedAuthority(TrustedAuthority ta, String callerGridIdentity) throws GTSInternalException, IllegalTrustedAuthorityException,
            InvalidTrustedAuthorityException, PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        trust.updateTrustedAuthority(ta);
    }

    public void updateCRL(String trustedAuthorityName, X509CRL crl, String callerGridIdentity) throws GTSInternalException, IllegalTrustedAuthorityException,
            InvalidTrustedAuthorityException, PermissionDeniedException {
        this.checkAdministratorOfTrustedAuthority(trustedAuthorityName, callerGridIdentity);
        TrustedAuthority ta = trust.getTrustedAuthority(trustedAuthorityName);
        ta.setCRL(crl);
        trust.updateTrustedAuthority(ta);
    }

    public void removeTrustedAuthority(String name, String callerGridIdentity) throws GTSInternalException, InvalidTrustedAuthorityException,
            PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        trust.removeTrustedAuthority(name);
        permissions.revokePermissions(name);
    }

    public void addTrustLevel(TrustLevel level, String callerGridIdentity) throws GTSInternalException, IllegalTrustLevelException, PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        trustLevelManager.addTrustLevel(level);
    }

    public void removeTrustLevel(String name, String callerGridIdentity) throws GTSInternalException, InvalidTrustLevelException, IllegalTrustLevelException,
            PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        trustLevelManager.removeTrustLevel(name);
    }

    public void updateTrustLevel(TrustLevel level, String callerGridIdentity) throws GTSInternalException, InvalidTrustLevelException,
            IllegalTrustLevelException, PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        trustLevelManager.updateTrustLevel(level);
    }

    public TrustLevel[] getTrustLevels(String callerGridIdentity) throws GTSInternalException {
        return trustLevelManager.getTrustLevels();
    }

    public TrustLevel[] getTrustLevels(String gtsSourceURI, String callerGridIdentity) throws GTSInternalException {
        return trustLevelManager.getTrustLevels(gtsSourceURI);
    }

    public TrustLevel getTrustLevel(String name, String callerGridIdentity) throws GTSInternalException, InvalidTrustLevelException {
        return trustLevelManager.getTrustLevel(name);
    }

    public void addPermission(Permission p, String callerGridIdentity) throws GTSInternalException, IllegalPermissionException, PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        if ((p.getTrustedAuthorityName() != null) && (!p.getTrustedAuthorityName().equals(org.cagrid.gts.service.impl.Constants.ALL_TRUST_AUTHORITIES))) {
            if (!trust.doesTrustedAuthorityExist(p.getTrustedAuthorityName())) {
                IllegalPermissionException fault = FaultHelper.createFaultException(IllegalPermissionException.class,
                        "Cannot add permission, the Trusted Authority (" + p.getTrustedAuthorityName() + ") specified does not exist.");
                throw fault;
            }
        }
        permissions.addPermission(p);
    }

    public Permission[] findPermissions(PermissionFilter filter, String callerGridIdentity) throws GTSInternalException, PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        return permissions.findPermissions(filter);
    }

    public void revokePermission(Permission p, String callerGridIdentity) throws GTSInternalException, InvalidPermissionException, PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        permissions.revokePermission(p);
    }

    public void addAuthority(AuthorityGTS gts, String callerGridIdentity) throws GTSInternalException, IllegalAuthorityException, PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        authority.addAuthority(gts);
    }

    public void updateAuthorityPriorities(AuthorityPriorityUpdate update, String callerGridIdentity) throws GTSInternalException, IllegalAuthorityException,
            PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        authority.updateAuthorityPriorities(update);
    }

    public void updateAuthority(AuthorityGTS gts, String callerGridIdentity) throws GTSInternalException, IllegalAuthorityException, InvalidAuthorityException,
            PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        authority.updateAuthority(gts);
    }

    public AuthorityGTS[] getAuthorities() throws GTSInternalException {
        return authority.getAuthorities();
    }

    public void removeAuthority(String serviceURI, String callerGridIdentity) throws GTSInternalException, InvalidAuthorityException, PermissionDeniedException {
        checkServiceAdministrator(callerGridIdentity);
        try {
            authority.removeAuthority(serviceURI);
            TrustedAuthorityFilter f = new TrustedAuthorityFilter();
            f.setSourceGTS(serviceURI);
            TrustedAuthority[] ta = this.trust.findTrustAuthorities(f);
            boolean error = false;
            StringBuffer elist = null;
            for (int i = 0; i < ta.length; i++) {
                try {
                    trust.removeTrustedAuthority(ta[i].getName());
                } catch (Exception ex) {
                    log.error(ex);
                    if (elist == null) {
                        error = true;
                        elist = new StringBuffer("Unable to remove the trusted authorities:\n");

                    }
                    elist.append(ta[i].getName() + "\n");

                }
            }
            if (error) {
                throw new Exception(elist.toString());
            }
        } catch (Exception e) {
            log.error(e);
            GTSInternalException fault = FaultHelper.createFaultException(GTSInternalException.class,
                    "An following unexpected error occurred removing the authority " + serviceURI + ": " + e.getMessage());
            FaultHelper.addMessage(fault, e.getMessage());
            throw fault;
        }
    }

    private void checkServiceAdministrator(String gridIdentity) throws GTSInternalException, PermissionDeniedException {
        if (!permissions.isUserTrustServiceAdmin(gridIdentity)) {
            PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "You are not a trust service administrator!!!");
            throw fault;
        }
    }

    private void checkAdministratorOfTrustedAuthority(String ta, String gridIdentity) throws GTSInternalException, PermissionDeniedException {
        if (permissions.isUserTrustServiceAdmin(gridIdentity)) {
            return;
        }

        if (permissions.isUserTrustedAuthorityAdmin(ta, gridIdentity)) {
            return;
        }
        PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class,
                "You are not an administrator for the trusted authority " + ta + "!!!");
        throw fault;
    }

    public void clearDatabase() throws GTSInternalException {
        trust.clearDatabase();
        permissions.clearDatabase();
        trustLevelManager.clearDatabase();
        authority.clearDatabase();
    }

    public boolean isTrustLevelUsed(String name) throws GTSInternalException {
        TrustedAuthorityFilter f = new TrustedAuthorityFilter();
        TrustLevels levels = new TrustLevels();
        levels.getTrustLevel().add(name);
        f.setTrustLevels(levels);

        TrustedAuthority[] ta = this.findTrustAuthorities(f);
        if ((ta == null) || (ta.length == 0)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean doesTrustLevelExist(String name) throws GTSInternalException {
        if (trustLevelManager.doesTrustLevelExist(name)) {
            return true;
        } else {
            return false;
        }
    }

    public void removeAssociatedTrustedAuthorities(String trustLevel) throws GTSInternalException {
        this.trust.removeLevelFromTrustedAuthorities(trustLevel);
    }

    protected void synchronizeTrustLevels(String authorityServiceURI, List<TrustLevel> levels) {
        // Synchronize the Trust Level

        // We need to get a list of all the Trusted Authorities provided
        // by the source,
        // such that we can remove the ones that are not provided in the
        // new list

        Map<String, Boolean> toBeDeleted = new HashMap<String, Boolean>();
        try {
            TrustLevel[] existing = this.trustLevelManager.getTrustLevels(authorityServiceURI);
            for (int i = 0; i < existing.length; i++) {
                toBeDeleted.put(existing[i].getName(), Boolean.TRUE);
            }
        } catch (Exception e) {
            this.log.error("Error synchronizing with the authority " + authorityServiceURI
                    + "the following error occurred obtaining the existing trust level: " + e.getMessage(), e);
            return;
        }
        if (levels != null) {
            for (TrustLevel level : levels) {
                toBeDeleted.remove(level.getName());
                try {

                    if (this.trustLevelManager.doesTrustLevelExist(level.getName())) {
                        // Perform Update
                        TrustLevel l = this.trustLevelManager.getTrustLevel(level.getName());
                        AuthorityGTS updateAuthority = authority.getAuthority(authorityServiceURI);
                        // Determine if we should peform update
                        boolean performUpdate = false;
                        // Check to see if this service is the authority
                        if (!l.getAuthorityGTS().equals(gtsURI)) {
                            AuthorityGTS currAuthority = authority.getAuthority(l.getSourceGTS());

                            // Check to see if the authority GTS is the same
                            if (currAuthority.getServiceURI().equals(updateAuthority.getServiceURI())) {
                                performUpdate = true;
                                this.log.debug("The trust level (" + level.getName() + ") will be updated!!!");
                            } else if (currAuthority.getPriority() > updateAuthority.getPriority()) {
                                performUpdate = true;
                                this.log.debug("The trust level (" + level.getName() + ") will be updated, the authority (" + updateAuthority.getServiceURI()
                                        + ") has a greater priority then the current source authority (" + currAuthority.getServiceURI() + ")!!!");

                            } else {
                                this.log.debug("The trust level(" + level.getName() + ") will NOT be updated, the current source authority ("
                                        + currAuthority.getServiceURI() + ") has a greater priority then the source authority ("
                                        + updateAuthority.getServiceURI() + ")!!!");
                                performUpdate = false;
                            }
                        } else {
                            this.log.debug("The trust level (" + level.getName() + ") will NOT be updated, this GTS is its authority !!!");
                            performUpdate = false;
                        }
                        if (performUpdate) {
                            level.setIsAuthority(Boolean.FALSE);
                            level.setSourceGTS(authorityServiceURI);

                            try {
                                this.trustLevelManager.updateTrustLevel(level, false);
                            } catch (Exception e) {
                                this.log.error("Error synchronizing with the authority " + authorityServiceURI
                                        + ", the following error occcurred when trying to update the authority, " + level.getName() + ": " + e.getMessage(), e);
                                continue;
                            }
                        }
                    } else {
                        this.log.debug("The trusted authority (" + level.getName() + ") will be added with the authority (" + authorityServiceURI
                                + ") as the source!!!");
                        level.setIsAuthority(Boolean.FALSE);
                        level.setSourceGTS(authorityServiceURI);

                        try {
                            this.trustLevelManager.addTrustLevel(level, false);
                        } catch (Exception e) {
                            this.log.error("Error synchronizing with the authority " + authorityServiceURI
                                    + ", the following error occcurred when trying to add the trust level, " + level.getName() + ": " + e.getMessage(), e);
                            continue;
                        }

                    }
                } catch (Exception ex) {
                    this.log.error("Error synchronizing with the authority " + authorityServiceURI + ": " + ex.getMessage(), ex);
                    continue;
                }
            }
        }
        Iterator<String> itr = toBeDeleted.keySet().iterator();
        while (itr.hasNext()) {
            String name = itr.next();
            try {
                this.trustLevelManager.removeTrustLevel(name);
                this.log.debug("The trust level (" + name + ") was removed because it has been removed from the authority " + authorityServiceURI + "!!!");
            } catch (Exception e) {
                this.log.error("The trust level (" + name + ") should have been removed because it has been removed from the authority " + authorityServiceURI
                        + ", however the following error occurred:" + e.getMessage(), e);
            }
        }
    }

    protected void synchronizeTrustedAuthorities(String authorityServiceURI, TrustedAuthority[] trusted) {
        // Synchronize the Trusted Authorities
        if (trusted != null) {
            // We need to get a list of all the trust levels provided
            // by the source,
            // such that we can remove the ones that are not provided in the
            // new list
            Map<String, Boolean> toBeDeleted = new HashMap<String, Boolean>();
            try {
                TrustedAuthorityFilter f = new TrustedAuthorityFilter();
                f.setSourceGTS(authorityServiceURI);
                TrustedAuthority[] existing = this.trust.findTrustAuthorities(f);
                for (int i = 0; i < existing.length; i++) {
                    toBeDeleted.put(existing[i].getName(), Boolean.TRUE);
                }
            } catch (Exception e) {
                this.log.error("Error synchronizing with the authority " + authorityServiceURI
                        + "the following error occurred obtaining the existing Trusted Authorities: " + e.getMessage(), e);
                return;
            }

            for (int j = 0; j < trusted.length; j++) {
                try {
                    toBeDeleted.remove(trusted[j].getName());
                    if (this.trust.doesTrustedAuthorityExist(trusted[j].getName())) {
                        // Perform Update
                        TrustedAuthority ta = this.trust.getTrustedAuthority(trusted[j].getName());
                        AuthorityGTS updateAuthority = authority.getAuthority(authorityServiceURI);
                        // Determine if we should peform update
                        boolean performUpdate = false;
                        // Check to see if this service is the authority
                        if (!ta.getAuthorityGTS().equals(gtsURI)) {
                            AuthorityGTS currAuthority = authority.getAuthority(ta.getSourceGTS());

                            // Check to see if the authority GTS is the same
                            if (currAuthority.getServiceURI().equals(updateAuthority.getServiceURI())) {
                                performUpdate = true;
                                this.log.debug("The trusted authority (" + ta.getName() + ") will be updated!!!");
                            } else if (currAuthority.getPriority() > updateAuthority.getPriority()) {
                                performUpdate = true;
                                this.log.debug("The trusted authority (" + ta.getName() + ") will be updated, the authority ("
                                        + updateAuthority.getServiceURI() + ") has a greater priority then the current source authority ("
                                        + currAuthority.getServiceURI() + ")!!!");

                            } else {
                                this.log.debug("The trusted authority (" + ta.getName() + ") will NOT be updated, the current source authority ("
                                        + currAuthority.getServiceURI() + ") has a greater priority then the source authority ("
                                        + updateAuthority.getServiceURI() + ")!!!");
                                performUpdate = false;
                            }
                        } else {
                            this.log.debug("The trusted authority (" + ta.getName() + ") will NOT be updated, this GTS is its authority !!!");
                            performUpdate = false;
                        }
                        if (performUpdate) {
                            trusted[j].setIsAuthority(Boolean.FALSE);
                            trusted[j].setSourceGTS(authorityServiceURI);
                            Calendar c = new GregorianCalendar();
                            c.add(Calendar.HOUR, updateAuthority.getTimeToLive().getHours());
                            c.add(Calendar.MINUTE, updateAuthority.getTimeToLive().getMinutes());
                            c.add(Calendar.SECOND, updateAuthority.getTimeToLive().getSeconds());
                            trusted[j].setExpires(c.getTimeInMillis());
                            try {
                                trust.updateTrustedAuthority(trusted[j], false);
                            } catch (Exception e) {
                                this.log.error(
                                        "Error synchronizing with the authority " + authorityServiceURI
                                                + ", the following error occcurred when trying to update the authority, " + trusted[j].getName() + ": "
                                                + e.getMessage(), e);
                                continue;
                            }
                        }
                    } else {
                        AuthorityGTS updateAuthority = authority.getAuthority(authorityServiceURI);
                        this.log.debug("The trusted authority (" + trusted[j].getName() + ") will be added with the authority (" + authorityServiceURI
                                + ") as the source!!!");
                        trusted[j].setIsAuthority(Boolean.FALSE);
                        trusted[j].setSourceGTS(authorityServiceURI);
                        Calendar c = new GregorianCalendar();
                        c.add(Calendar.HOUR, updateAuthority.getTimeToLive().getHours());
                        c.add(Calendar.MINUTE, updateAuthority.getTimeToLive().getMinutes());
                        c.add(Calendar.SECOND, updateAuthority.getTimeToLive().getSeconds());
                        trusted[j].setExpires(c.getTimeInMillis());
                        try {
                            trust.addTrustedAuthority(trusted[j], false);
                        } catch (Exception e) {
                            this.log.error("Error synchronizing with the authority " + authorityServiceURI
                                    + ", the following error occcurred when trying to add the authority, " + trusted[j].getName() + ": " + e.getMessage(), e);
                            continue;
                        }

                    }
                } catch (Exception ex) {
                    this.log.error("Error synchronizing with the authority " + authorityServiceURI + ": " + ex.getMessage(), ex);
                    continue;
                }
            }
            Iterator<String> itr = toBeDeleted.keySet().iterator();
            while (itr.hasNext()) {
                String name = itr.next();
                try {
                    trust.removeTrustedAuthority(name);
                    this.log.debug("The trusted authority (" + name + ") was removed because it has been removed from the authority " + authorityServiceURI
                            + "!!!");
                } catch (Exception e) {
                    this.log.error("The trusted authority (" + name + ") should have been removed because it has been removed from the authority "
                            + authorityServiceURI + ", however the following error occurred:" + e.getMessage(), e);
                }
            }

        }
    }

    private GTSPortType getGTSClient(String url) throws Exception {
        synchronized (this.gtsClientCacheMutex) {
            if (this.gtsClientCache.containsKey(url)) {
                return this.gtsClientCache.get(url);
            } else {
                X509Credential credential = this.credmanager.getCredential();
                KeyStoreType truststore = this.credmanager.getTruststore();
                GTSPortType client = GTSSoapClientFactory.createSoapClient(url, truststore, credential);
                this.gtsClientCache.put(url, client);
                return client;
            }
        }
    }

    public void synchronizeWithAuthorities() {

        log.info("Syncing with authorities.");
        AuthorityGTS[] auths = null;
        try {
            auths = this.getAuthorities();
        } catch (Exception ex) {
            this.log.error("Error synchronizing with the authorities, could not obtain a list of authorities!!!", ex);
        }

        if (auths != null) {
            TrustedAuthorityFilter filter = new TrustedAuthorityFilter();
            filter.setStatus(Status.TRUSTED);
            filter.setLifetime(Lifetime.VALID);

            X509Credential credential = this.credmanager.getCredential();
            KeyStoreType truststore = this.credmanager.getTruststore();

            for (int i = 0; i < auths.length; i++) {
                log.info("Syncing with authority: " + auths[i].getServiceURI());
                List<TrustLevel> levels = null;
                TrustedAuthority[] trusted = null;
                try {
                    GTSPortType client = getGTSClient(auths[i].getServiceURI());

                    log.debug("Getting trust levels from authority: " + auths[i].getServiceURI());
                    levels = client.getTrustLevels(new GetTrustLevelsRequest()).getTrustLevel();
                    log.debug("Found (" + levels.size() + ") trust levels from authority: " + auths[i].getServiceURI());
                    FindTrustedAuthoritiesRequest parameters = new FindTrustedAuthoritiesRequest();
                    FindTrustedAuthoritiesRequest.Filter filterReq = new FindTrustedAuthoritiesRequest.Filter();
                    filterReq.setTrustedAuthorityFilter(filter);
                    parameters.setFilter(filterReq);
                    log.debug("Getting trusted authorities from authority: " + auths[i].getServiceURI());
                    FindTrustedAuthoritiesResponse taResp = client.findTrustedAuthorities(parameters);
                    trusted = taResp.getTrustedAuthority().toArray(new TrustedAuthority[]{});
                    log.debug("Found (" + trusted.length + ")  trusted authorities from authority: " + auths[i].getServiceURI());
                } catch (Exception ex) {
                    log.error("Error synchronizing with the authority " + auths[i].getServiceURI() + ": " + ex.getMessage(), ex);
                    continue;
                }

                // Synchronize the Trust Levels
                this.synchronizeTrustLevels(auths[i].getServiceURI(), levels);

                // Synchronize the Trusted Authorities
                this.synchronizeTrustedAuthorities(auths[i].getServiceURI(), trusted);
            }
        } else {
            log.info("No authorities to sync with.");
        }

    }
}
