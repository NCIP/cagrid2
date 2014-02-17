package org.cagrid.trust.service.core;

/**
 * Created by langella on 2/16/14.
 */
public class RevocationEnabledTrustManager  extends  AbstractTrustManager{

    @Override
    public boolean isRevocationEnabled() {
        return true;
    }
}
