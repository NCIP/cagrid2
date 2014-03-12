package org.cagrid.gts.ws.client;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.soapclient.AbstractSoapClient;
import org.cagrid.gts.model.*;
import org.cagrid.gts.service.exception.*;
import org.cagrid.gts.wsrf.service.GTSService;
import org.cagrid.gts.wsrf.stubs.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by langella on 2/18/14.
 */
public class GTSClient extends AbstractSoapClient<GTSService, GTSPortType> {


    public GTSClient(String url) {
        super(url, GTSService.class, GTSPortType.class);
    }

    public static void main(String[] args) {
        try {
            String url = "https://slavegts.training.cagrid.org:4443/gts";
            GTSClient client = new GTSClient(url);

            KeyStoreType ts = new KeyStoreType();
            ts.setFile("/Users/langella/Documents/caGrid/environments/keys/training-truststore.jks");
            ts.setPassword("changeit");

            client.setTruststore(ts);

            List<TrustedAuthority> list = client.findTrustedAuthorities(new TrustedAuthorityFilter());
            for (TrustedAuthority ta : list) {
                System.out.println(ta.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TrustedAuthority addTrustedAuthority(TrustedAuthority trustedAuthority) throws GTSInternalException,
            IllegalTrustedAuthorityException, PermissionDeniedException {
        AddTrustedAuthorityRequest req = new AddTrustedAuthorityRequest();
        AddTrustedAuthorityRequest.Ta ta = new AddTrustedAuthorityRequest.Ta();
        ta.setTrustedAuthority(trustedAuthority);
        req.setTa(ta);

        try {
            AddTrustedAuthorityResponse res = getClient().addTrustedAuthority(req);
            if (res != null) {
                return res.getTrustedAuthority();
            }
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalTrustedAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalTrustedAuthorityException(e.getFaultInfo(), e.getMessage());
        }
        return null;
    }

    public void updateTrustedAuthority(TrustedAuthority trustedAuthority) throws GTSInternalException,
            IllegalTrustedAuthorityException, InvalidTrustedAuthorityException, PermissionDeniedException {
        UpdateTrustedAuthorityRequest req = new UpdateTrustedAuthorityRequest();
        UpdateTrustedAuthorityRequest.Ta ta = new UpdateTrustedAuthorityRequest.Ta();
        ta.setTrustedAuthority(trustedAuthority);
        req.setTa(ta);
        try {
            getClient().updateTrustedAuthority(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalTrustedAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalTrustedAuthorityException(e.getFaultInfo(), e.getMessage());
        } catch (InvalidTrustedAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new InvalidTrustedAuthorityException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void removeTrustedAuthority(java.lang.String trustedAuthorityName) throws GTSInternalException,
            InvalidTrustedAuthorityException, PermissionDeniedException {
        try {
            RemoveTrustedAuthorityRequest req = new RemoveTrustedAuthorityRequest();
            req.setTrustedAuthorityName(trustedAuthorityName);
            getClient().removeTrustedAuthority(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (InvalidTrustedAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new InvalidTrustedAuthorityException(e.getFaultInfo(), e.getMessage());
        }
    }

    public List<TrustedAuthority> findTrustedAuthorities(TrustedAuthorityFilter f) throws GTSInternalException {
        try {
            FindTrustedAuthoritiesRequest req = new FindTrustedAuthoritiesRequest();
            FindTrustedAuthoritiesRequest.Filter filter = new FindTrustedAuthoritiesRequest.Filter();
            filter.setTrustedAuthorityFilter(f);
            req.setFilter(filter);
            FindTrustedAuthoritiesResponse res = getClient().findTrustedAuthorities(req);
            if (res != null) {
                return res.getTrustedAuthority();
            }

            return new ArrayList<TrustedAuthority>();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(null, e.getMessage());

        }
    }

    public List<Permission> findPermissions(PermissionFilter f) throws GTSInternalException,
            PermissionDeniedException {
        try {
            FindPermissionsRequest req = new FindPermissionsRequest();
            FindPermissionsRequest.Filter filter = new FindPermissionsRequest.Filter();
            filter.setPermissionFilter(f);
            req.setFilter(filter);
            FindPermissionsResponse res = getClient().findPermissions(req);
            if (res != null) {
                return res.getPermission();
            } else {
                return new ArrayList<Permission>();
            }
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void addPermission(Permission permission) throws GTSInternalException, IllegalPermissionException,
            PermissionDeniedException {
        try {
            AddPermissionRequest req = new AddPermissionRequest();
            AddPermissionRequest.Permission perm = new AddPermissionRequest.Permission();
            perm.setPermission(permission);
            req.setPermission(perm);
            getClient().addPermission(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalPermissionFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalPermissionException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void revokePermission(Permission permission) throws GTSInternalException, InvalidPermissionException,
            PermissionDeniedException {
        try {
            RevokePermissionRequest req = new RevokePermissionRequest();
            RevokePermissionRequest.Permission perm = new RevokePermissionRequest.Permission();
            perm.setPermission(permission);
            req.setPermission(perm);
            getClient().revokePermission(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (InvalidPermissionFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new InvalidPermissionException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void addTrustLevel(TrustLevel trustLevel) throws GTSInternalException, IllegalTrustLevelException,
            PermissionDeniedException {
        try {
            AddTrustLevelRequest req = new AddTrustLevelRequest();
            AddTrustLevelRequest.TrustLevel tl = new AddTrustLevelRequest.TrustLevel();
            tl.setTrustLevel(trustLevel);
            req.setTrustLevel(tl);
            getClient().addTrustLevel(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalTrustLevelFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalTrustLevelException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void updateTrustLevel(TrustLevel trustLevel) throws GTSInternalException, InvalidTrustLevelException, IllegalTrustLevelException,
            PermissionDeniedException {
        try {
            UpdateTrustLevelRequest req = new UpdateTrustLevelRequest();
            UpdateTrustLevelRequest.TrustLevel tl = new UpdateTrustLevelRequest.TrustLevel();
            tl.setTrustLevel(trustLevel);
            req.setTrustLevel(tl);
            getClient().updateTrustLevel(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (InvalidTrustLevelFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new InvalidTrustLevelException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalTrustLevelFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalTrustLevelException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void removeTrustLevel(String trustLevelName) throws GTSInternalException, InvalidTrustLevelException, IllegalTrustLevelException,
            PermissionDeniedException {
        try {
            RemoveTrustLevelRequest req = new RemoveTrustLevelRequest();
            req.setTrustLevelName(trustLevelName);
            getClient().removeTrustLevel(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (InvalidTrustLevelFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new InvalidTrustLevelException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalTrustLevelFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalTrustLevelException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void updateAuthorityPriorities(AuthorityPriorityUpdate update) throws GTSInternalException, IllegalAuthorityException,
            PermissionDeniedException {
        try {
            UpdateAuthorityPrioritiesRequest req = new UpdateAuthorityPrioritiesRequest();
            UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate apu = new UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate();
            apu.setAuthorityPriorityUpdate(update);
            req.setAuthorityPriorityUpdate(apu);
            getClient().updateAuthorityPriorities(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalAuthorityException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void removeAuthority(String serviceURI) throws GTSInternalException, InvalidAuthorityException,
            PermissionDeniedException {
        try {
            RemoveAuthorityRequest req = new RemoveAuthorityRequest();
            req.setServiceURI(serviceURI);
            getClient().removeAuthority(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (InvalidAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new InvalidAuthorityException(e.getFaultInfo(), e.getMessage());
        }

    }

    public void addAuthority(AuthorityGTS gts) throws GTSInternalException, IllegalAuthorityException,
            PermissionDeniedException {
        try {
            AddAuthorityRequest req = new AddAuthorityRequest();
            AddAuthorityRequest.AuthorityGTS auth = new AddAuthorityRequest.AuthorityGTS();
            auth.setAuthorityGTS(gts);
            req.setAuthorityGTS(auth);
            getClient().addAuthority(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalAuthorityException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void updateAuthority(AuthorityGTS gts) throws GTSInternalException, IllegalAuthorityException, InvalidAuthorityException,
            PermissionDeniedException {
        try {
            UpdateAuthorityRequest req = new UpdateAuthorityRequest();
            UpdateAuthorityRequest.AuthorityGTS auth = new UpdateAuthorityRequest.AuthorityGTS();
            auth.setAuthorityGTS(gts);
            req.setAuthorityGTS(auth);
            getClient().updateAuthority(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalAuthorityException(e.getFaultInfo(), e.getMessage());
        } catch (InvalidAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new InvalidAuthorityException(e.getFaultInfo(), e.getMessage());
        }
    }

    public void updateCRL(String trustedAuthorityName, X509CRL crl) throws GTSInternalException,
            IllegalTrustedAuthorityException, InvalidTrustedAuthorityException, PermissionDeniedException {
        try {
            UpdateCRLRequest req = new UpdateCRLRequest();
            req.setTrustedAuthorityName(trustedAuthorityName);
            UpdateCRLRequest.Crl ucrl = new UpdateCRLRequest.Crl();
            ucrl.setX509CRL(crl);
            req.setCrl(ucrl);
            getClient().updateCRL(req);
        } catch (GTSInternalFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(e.getFaultInfo(), e.getMessage());
        } catch (PermissionDeniedFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new PermissionDeniedException(e.getFaultInfo(), e.getMessage());
        } catch (IllegalTrustedAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new IllegalTrustedAuthorityException(e.getFaultInfo(), e.getMessage());
        } catch (InvalidTrustedAuthorityFaultFaultMessage e) {
            log.error(e.getMessage(), e);
            throw new InvalidTrustedAuthorityException(e.getFaultInfo(), e.getMessage());
        }
    }

    public List<TrustLevel> getTrustLevels() throws GTSInternalException {
        try {
            GetTrustLevelsRequest req = new GetTrustLevelsRequest();

            GetTrustLevelsResponse res = getClient().getTrustLevels(req);
            if (res != null) {
                return res.getTrustLevel();
            }

            return new ArrayList<TrustLevel>();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(null, e.getMessage());

        }
    }


    public List<AuthorityGTS> getAuthorities() throws GTSInternalException {
        try {
            GetAuthoritiesRequest req = new GetAuthoritiesRequest();

            GetAuthoritiesResponse res = getClient().getAuthorities(req);
            if (res != null) {
                return res.getAuthorityGTS();
            }

            return new ArrayList<AuthorityGTS>();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GTSInternalException(null, e.getMessage());

        }
    }


}
