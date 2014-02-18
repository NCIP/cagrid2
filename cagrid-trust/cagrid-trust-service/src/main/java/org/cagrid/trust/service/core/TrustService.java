package org.cagrid.trust.service.core;

import org.cagrid.core.xml.XMLUtils;
import org.cagrid.trust.model.SyncDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.util.List;

public class TrustService implements org.cagrid.trust.service.TrustService {

    private Synchronizer synchronizer;

    private String syncDescription;


    private TrustedCAManager trustedCAManager;

    private TrustServiceTrustManager trustManager;

    private Object syncMutex = new Object();

    private Logger log;

    public TrustService() {
        log = LoggerFactory.getLogger(this.getClass().getName());
        this.trustManager = new TrustServiceTrustManager();

    }

    public Synchronizer getSynchronizer() {
        return synchronizer;
    }

    public void setSynchronizer(Synchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    public String getSyncDescription() {
        return syncDescription;
    }

    public void setSyncDescription(String syncDescription) {
        this.syncDescription = syncDescription;
    }

    public TrustedCAManager getTrustedCAManager() {
        return trustedCAManager;
    }

    public void setTrustedCAManager(TrustedCAManager trustedCAManager) {
        this.trustedCAManager = trustedCAManager;
    }

    public X509TrustManager getTrustManager() {
        return trustManager;
    }


    public void syncWithTrustFabric() {
        long start = System.currentTimeMillis();
        log.info("Syncing with the trust fabric.....");
        if (getSynchronizer() != null) {
            synchronized (syncMutex) {
                File syncDescriptionFile = null;
                if (getSyncDescription() != null) {
                    syncDescriptionFile = new File(getSyncDescription());
                    SyncDescription des = (SyncDescription) XMLUtils.fromXMLFile(SyncDescription.class, syncDescriptionFile);
                    getSynchronizer().sync(des);
                } else {
                    log.warn("Cannot sync with the trust fabric, no sync description file configured");
                }
                reloadTrustManagers();
            }
        } else {
            log.warn("No synchronizer configured for the trust service.");
        }

        long end = System.currentTimeMillis();
        log.info("Successfully synced with the trust fabric in " + (end - start) + " milliseconds.");
    }

    protected void reloadTrustManagers() {
        if (getTrustedCAManager() != null) {
            List<TrustedCAEntry> list = getTrustedCAManager().getTrustedCertificateAuthorities();
            this.trustManager.reload(list);
        } else {
            log.warn("No Trusted CA Manager configured for the trust service");
        }
    }
}
