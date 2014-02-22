package org.cagrid.trust.service.core;

import java.util.List;

/**
 * Created by langella on 2/16/14.
 */
public interface TrustedCAManager {

    public List<TrustedCAEntry> getTrustedCertificateAuthorities();
}