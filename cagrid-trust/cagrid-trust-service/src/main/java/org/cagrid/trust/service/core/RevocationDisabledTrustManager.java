package org.cagrid.trust.service.core;

/**
 * Created by langella on 2/16/14.
 */
public class RevocationDisabledTrustManager extends AbstractTrustManager{
    @Override
    public boolean isRevocationEnabled() {
        return false;
    }
}
