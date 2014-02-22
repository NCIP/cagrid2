package org.cagrid.trust.service.core;

import java.io.File;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.xml.XMLUtils;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gts.model.Status;
import org.cagrid.gts.soapclient.GTSSoapClientFactory;
import org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesRequest;
import org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesRequest.Filter;
import org.cagrid.gts.wsrf.stubs.FindTrustedAuthoritiesResponse;
import org.cagrid.gts.wsrf.stubs.GTSPortType;
import org.cagrid.trust.model.AddedTrustedCAs;
import org.cagrid.trust.model.ExcludedCAs;
import org.cagrid.trust.model.Message;
import org.cagrid.trust.model.MessageType;
import org.cagrid.trust.model.Messages;
import org.cagrid.trust.model.RemovedTrustedCAs;
import org.cagrid.trust.model.SyncDescription;
import org.cagrid.trust.model.SyncDescriptor;
import org.cagrid.trust.model.SyncReport;
import org.cagrid.trust.model.TrustLevels;
import org.cagrid.trust.model.TrustedAuthority;
import org.cagrid.trust.model.TrustedAuthorityFilter;
import org.cagrid.trust.model.TrustedCA;
import org.globus.common.CoGProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class SyncGTS implements Synchronizer {
    private final static String CERTIFICATE_EXTENSION = "cert";
    private final static String CRL_EXTENSION = "crl";
    private final static String METADATA_EXTENSION = "xml";
    private Map<String, TrustedCAFileListing> caListings;

    private Logger log;
    private List<Message> messages;
    private HistoryManager historyManager;
    private File trustedCertificatesDirectory;
    private KeyStoreType truststore;

    public SyncGTS() {
        this.trustedCertificatesDirectory = new File(".");
        log = LoggerFactory.getLogger(this.getClass().getName());
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    public void setTrustedCertificatesDirectory(String dir) {
        this.trustedCertificatesDirectory = new File(dir);
    }

    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    public KeyStoreType getTruststore() {
        return truststore;
    }

    public void setTruststore(KeyStoreType truststore) {
        this.truststore = truststore;
    }

    private void reset() {
        this.caListings = null;

        messages = new ArrayList<Message>();
    }

    public SyncReport sync(SyncDescription description) {
        SyncReport report = new SyncReport();
        try {
            reset();
            Set<String> unableToSync = new HashSet<String>();
            report.setSyncDescription(description);

            Date now = new Date();
            report.setTimeOfSync(now);

            Map<String, TrustedCAListing> master = new HashMap<String, TrustedCAListing>();
            String error = null;
            List<SyncDescriptor> list = description.getSyncDescriptors();

            for (SyncDescriptor des : list) {
                String uri = des.getGTS();
                this.log.info("Syncing with the GTS " + uri);
                Set<org.cagrid.gts.model.TrustedAuthority> taSet = new HashSet<org.cagrid.gts.model.TrustedAuthority>();
                List<TrustedAuthorityFilter> filters = des.getTrustedAuthorityFilters();
                int filterCount = 0;
                for (TrustedAuthorityFilter f : filters) {
                    filterCount = filterCount + 1;
                    try {

                        GTSPortType client = GTSSoapClientFactory.createSoapClient(uri, getTruststore(), (KeyManagersType) null);

                        // TODO: Perform authorization
                        // if (des[i].isPerformAuthorization()) {
                        // IdentityAuthorization ia = new
                        // IdentityAuthorization(des[i].getGTSIdentity());
                        // client.setAuthorization(ia);
                        // }

                        FindTrustedAuthoritiesRequest req = new FindTrustedAuthoritiesRequest();
                        Filter requestFilter = new Filter();
                        requestFilter.setTrustedAuthorityFilter(convert(f));
                        req.setFilter(requestFilter);
                        FindTrustedAuthoritiesResponse res = client.findTrustedAuthorities(req);
                        List<org.cagrid.gts.model.TrustedAuthority> tas = res.getTrustedAuthority();
                        int length = 0;

                        this.log.debug("Successfully synced with " + uri + " using filter " + filterCount + " the search found " + length + " Trusted Authority(s)!!!");

                        for (org.cagrid.gts.model.TrustedAuthority ta : tas) {
                            taSet.add(ta);
                        }

                    } catch (Exception e) {
                        unableToSync.add(uri);
                        Message mess = new Message();
                        mess.setType(MessageType.ERROR);
                        mess.setValue("An error occurred syncing with " + uri + " using filter " + filterCount + "\n " + e.getMessage());
                        messages.add(mess);
                        log.error(mess.getValue(), e);
                    }
                }

                // Write all to the master list;

                Iterator<org.cagrid.gts.model.TrustedAuthority> itr = taSet.iterator();
                while (itr.hasNext()) {
                    org.cagrid.gts.model.TrustedAuthority ta = itr.next();
                    if (master.containsValue(ta.getName())) {
                        TrustedCAListing gta = (TrustedCAListing) master.get(ta.getName());
                        String msg = "Conflict Detected: The Trusted Authority " + ta.getName() + " was determined to be trusted by both " + gta.getService() + " and " + uri + ".";
                        Message mess = new Message();
                        mess.setType(MessageType.WARNING);
                        mess.setValue(msg);
                        messages.add(mess);
                    } else {
                        master.put(ta.getName(), new TrustedCAListing(uri, convert(ta), des));
                    }
                }
                this.log.debug("Done syncing with the GTS " + uri + " " + taSet.size() + " Trusted Authority(s) found!!!");

                if (error != null) {
                    break;
                }
            }

            // Create a list of exclude certificate authorities and remove
            // all excluded certificates from the master list.
            Set<String> excluded = new HashSet<String>();
            ExcludedCAs ex = description.getExcludedCAs();
            if (ex != null) {
                List<String> caSubjects = ex.getCASubjects();
                if (caSubjects != null) {
                    for (String subject : caSubjects) {
                        excluded.add(subject);

                        if (master.containsKey(subject)) {
                            master.remove(subject);
                            Message m = new Message();
                            m.setType(MessageType.WARNING);
                            m.setValue("Ignoring the CA " + subject + " that was obtained in the sync because it is the excludes list!!!");
                            log.warn(m.getValue());
                            this.messages.add(m);
                        }
                    }
                }
            }

            // Write the master list out and generate signing policy

            this.readInCurrentCADirectory(description);
            Set<String> completeCASetByName = new HashSet<String>();
            Set<String> completeCASetByHash = new HashSet<String>();
            // Remove all CAs from the ca directory and COMPLETE list that
            // except the following:
            // Any CA in the exclude list.
            // Any CA in the unable to sync list that is not in the master
            // list.
            int removeCount = 0;
            List<TrustedCA> removedList = new ArrayList<TrustedCA>();
            Iterator<TrustedCAFileListing> del = caListings.values().iterator();
            while (del.hasNext()) {
                TrustedCAFileListing fl = (TrustedCAFileListing) del.next();
                TrustedCA ca = new TrustedCA();

                X509Certificate cert = null;
                if (fl.getCertificate() != null) {
                    try {
                        cert = CertUtil.loadCertificate(fl.getCertificate());
                        ca.setName(cert.getSubjectDN().getName());
                        ca.setCertificateFile(fl.getCertificate().getAbsolutePath());
                        if (excluded.contains(ca.getName())) {
                            Message m = new Message();
                            m.setType(MessageType.INFO);
                            m.setValue("The CA " + ca.getName() + " was not removed because it is the exclude list.");
                            this.messages.add(m);
                            log.info(m.getValue());
                            completeCASetByName.add(cert.getSubjectDN().getName());
                            completeCASetByHash.add(fl.getName());
                            continue;
                        }
                    } catch (Exception exception) {
                        ca.setCertificateFile(fl.getCertificate().getAbsolutePath());
                        Message err = new Message();
                        err.setType(MessageType.ERROR);
                        err.setValue("Error loading the certificate, " + fl.getCertificate().getAbsolutePath() + ": \n" + exception.getMessage());
                        this.messages.add(err);
                        log.error(err.getValue());
                    }
                }

                if (fl.getMetadata() != null) {
                    try {
                        TrustedCA tca = (TrustedCA) XMLUtils.fromXMLFile(TrustedCA.class, fl.getMetadata());
                        ca.setDiscoveredOn(tca.getDiscoveredOn());
                        ca.setExpiresOn(tca.getExpiresOn());
                        if ((unableToSync.contains(tca.getGTS())) && (!master.containsKey(tca.getName()))) {
                            if (now.before(tca.getExpiresOn())) {
                                Message m = new Message();
                                m.setType(MessageType.WARNING);
                                m.setValue("Unable to communicate with the GTS " + tca.getGTS() + " did not remove the the CA " + tca.getName() + " because it was not expired.");
                                this.messages.add(m);
                                log.warn(m.getValue());
                                completeCASetByName.add(cert.getSubjectDN().getName());
                                completeCASetByHash.add(fl.getName());
                                continue;
                            }
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }
                removeCount = removeCount + 1;
                if (fl.getCertificate() != null) {
                    if (fl.getCertificate().delete()) {
                        log.debug("Removed the certificate (" + fl.getCertificate().getAbsolutePath() + ") for the CA " + ca.getName() + ".");
                    } else {
                        Message err = new Message();
                        err.setType(MessageType.ERROR);
                        err.setValue("Error removing the certificate (" + fl.getCertificate().getAbsolutePath() + ") for the CA " + ca.getName() + ".");
                        this.messages.add(err);
                        log.error(err.getValue());
                    }
                }

                if (fl.getCRL() != null) {
                    ca.setCRLFile(fl.getCRL().getAbsolutePath());
                    if (fl.getCRL().delete()) {
                        log.debug("Removed the CRL (" + fl.getCRL().getAbsolutePath() + ") for the CA " + ca.getName() + ".");
                    } else {
                        Message err = new Message();
                        err.setType(MessageType.ERROR);
                        err.setValue("Error removing the CRL (" + fl.getCRL().getAbsolutePath() + ") for the CA " + ca.getName() + ".");
                        this.messages.add(err);
                        log.error(err.getValue());
                    }
                }

                if (fl.getMetadata() != null) {
                    ca.setMetadataFile(fl.getMetadata().getAbsolutePath());
                    if (fl.getMetadata().delete()) {
                        log.debug("Removed the CA Metadata (" + fl.getMetadata().getAbsolutePath() + ") for the CA " + ca.getName() + ".");
                    } else {
                        Message err = new Message();
                        err.setType(MessageType.ERROR);
                        err.setValue("Error removing the metadata (" + fl.getMetadata().getAbsolutePath() + ") for the CA " + ca.getName() + ".");
                        this.messages.add(err);
                        log.error(err.getValue());
                    }
                }

                removedList.add(ca);

            }

            RemovedTrustedCAs rtc = new RemovedTrustedCAs();
            rtc.getTrustedCAs().addAll(removedList);
            report.setRemovedTrustedCAs(rtc);
            Message mess2 = new Message();
            mess2.setType(MessageType.INFO);
            mess2.setValue("Successfully removed " + removeCount + " Trusted Authority(s) from " + CoGProperties.getDefault().getCaCertLocations());
            messages.add(mess2);
            log.info(mess2.getValue());

            int taCount = 0;
            Iterator<TrustedCAListing> itr = master.values().iterator();
            List<TrustedCA> addedList = new ArrayList<TrustedCA>();

            while (itr.hasNext()) {
                taCount = taCount + 1;
                File caFile = null;
                File crlFile = null;
                File metadataFile = null;
                String caHash = null;
                String subject = null;
                try {
                    TrustedCAListing listing = (TrustedCAListing) itr.next();
                    TrustedAuthority ta = listing.getTrustedAuthority();
                    X509Certificate cert = CertUtil.loadCertificate(ta.getCertificate().getCertificateEncodedString());
                    caHash = CertUtil.getHashCode(cert);
                    TrustedCA ca = new TrustedCA();
                    subject = cert.getSubjectDN().getName();
                    ca.setName(subject);
                    ca.setGTS(listing.getService());

                    if ((completeCASetByName.contains(cert.getSubjectDN())) && (excluded.contains(cert.getSubjectDN()))) {
                        Message m = new Message();
                        m.setType(MessageType.WARNING);
                        m.setValue("Ignoring the CA " + ca.getName() + " obtained from the GTS " + ca.getGTS() + " because the CA is in the excludes list.");
                        this.messages.add(m);
                        log.warn(m.getValue());

                    } else if ((completeCASetByName.contains(cert.getSubjectDN())) && (!excluded.contains(cert.getSubjectDN()))) {
                        Message m = new Message();
                        m.setType(MessageType.WARNING);
                        m.setValue("Ignoring the CA " + ca.getName() + " obtained from the GTS " + ca.getGTS() + " because the CA is already trusted.");
                        this.messages.add(m);
                        log.warn(m.getValue());
                    } else if (completeCASetByHash.contains(caHash)) {
                        Message m = new Message();
                        m.setType(MessageType.WARNING);
                        m.setValue("Ignoring the CA " + ca.getName() + " obtained from the GTS " + ca.getGTS() + " because a CA with the hash " + caHash + " already exists.");
                        this.messages.add(m);
                        log.warn(m.getValue());

                    } else {
                        String filePrefix = this.trustedCertificatesDirectory.getAbsolutePath() + File.separator + caHash;
                        caFile = new File(filePrefix + "." + CERTIFICATE_EXTENSION);
                        crlFile = new File(filePrefix + "." + CRL_EXTENSION);
                        metadataFile = new File(filePrefix + "." + METADATA_EXTENSION);

                        ca.setMetadataFile(metadataFile.getAbsolutePath());
                        CertUtil.writeCertificate(cert, caFile);
                        ca.setCertificateFile(caFile.getAbsolutePath());
                        log.debug("Wrote out the certificate for the Trusted Authority " + ta.getName() + " to the file " + caFile.getAbsolutePath());
                        if (ta.getCRL() != null) {
                            if (ta.getCRL().getCrlEncodedString() != null) {
                                X509CRL crl = CertUtil.loadCRL(ta.getCRL().getCrlEncodedString());
                                CertUtil.writeCRL(crl, crlFile);
                                ca.setCRLFile(crlFile.getAbsolutePath());
                                log.debug("Wrote out the CRL for the Trusted Authority " + ta.getName() + " to the file " + crlFile.getAbsolutePath());
                            }
                        }
                        Calendar cal = new GregorianCalendar();
                        ca.setDiscoveredOn(cal.getTime());
                        if (listing.getDescriptor().getExpiration() != null) {
                            cal.add(Calendar.HOUR_OF_DAY, listing.getDescriptor().getExpiration().getHours());
                            cal.add(Calendar.MINUTE, listing.getDescriptor().getExpiration().getMinutes());
                            cal.add(Calendar.SECOND, listing.getDescriptor().getExpiration().getSeconds());
                            ca.setExpiresOn(cal.getTime());
                        } else {
                            ca.setExpiresOn(cal.getTime());
                        }
                        XMLUtils.toXMLFile(ca, metadataFile);
                        log.debug("Wrote out the metadata for the Trusted Authority " + ta.getName() + " to the file " + metadataFile.getAbsolutePath());
                        completeCASetByName.add(subject);
                        completeCASetByHash.add(caHash);
                        addedList.add(ca);
                    }
                } catch (Exception e) {
                    log.error("An unexpected error occurred writing out the Trusted Authorities!!!", e);
                    if (caFile != null) {
                        caFile.delete();
                    }
                    if (crlFile != null) {
                        crlFile.delete();
                    }
                    if (metadataFile != null) {
                        metadataFile.delete();
                    }
                    if (subject != null) {
                        completeCASetByName.remove(subject);

                    }
                    if (caHash != null) {
                        completeCASetByHash.remove(caHash);
                    }
                }
            }
            Message mess = new Message();
            mess.setType(MessageType.INFO);
            mess.setValue("Successfully wrote out " + taCount + " Trusted Authority(s) to " + this.trustedCertificatesDirectory.getAbsolutePath());
            messages.add(mess);
            log.info(mess.getValue());

            AddedTrustedCAs atc = new AddedTrustedCAs();
            atc.getTrustedCAs().addAll(addedList);
            report.setAddedTrustedCAs(atc);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Message error = new Message();
            error.setType(MessageType.FATAL);
            error.setValue(e.getMessage());
            messages.add(error);
        }

        Messages reportMessages = new Messages();
        reportMessages.getMessages().addAll(messages);
        report.setMessages(reportMessages);

        // Log Report;
        if (getHistoryManager() != null) {
            try {
                getHistoryManager().addReport(report);
                if (description.getCacheSize() != null) {
                    getHistoryManager().prune(description.getCacheSize());
                }

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } else {
            log.warn("A sync report was not logged, no history manager was configured.");
        }
        return report;
    }

    private void readInCurrentCADirectory(SyncDescription description) throws Exception {
        caListings = new HashMap<String, TrustedCAFileListing>();

        log.info("Taking Snapshot of Trusted CA Directory (" + this.trustedCertificatesDirectory.getAbsolutePath() + ")....");
        if (this.trustedCertificatesDirectory.exists()) {
            if (!this.trustedCertificatesDirectory.isDirectory()) {
                Message mess = new Message();
                mess.setType(MessageType.FATAL);
                mess.setValue("The Trusted Certificates directory, " + this.trustedCertificatesDirectory.getAbsolutePath() + " is not a directory.");
                messages.add(mess);
                throw new Exception(mess.getValue());
            }

        } else {
            boolean create = this.trustedCertificatesDirectory.mkdirs();
            if (!create) {
                Message mess = new Message();
                mess.setType(MessageType.FATAL);
                mess.setValue("The Trusted Certificates directory, " + this.trustedCertificatesDirectory.getAbsolutePath() + " does not exist and could not be created.");
                messages.add(mess);
                throw new Exception(mess.getValue());
            }
        }
        File[] list = this.trustedCertificatesDirectory.listFiles();
        for (int i = 0; i < list.length; i++) {
            String fn = list[i].getName();
            int index = fn.lastIndexOf(".");
            if (index == -1) {
                handleUnexpectedFile(description, list[i]);
                continue;
            }
            String name = fn.substring(0, index);
            String extension = fn.substring(index + 1);

            TrustedCAFileListing ca = (TrustedCAFileListing) this.caListings.get(name);
            if (ca == null) {
                ca = new TrustedCAFileListing(name);
                caListings.put(name, ca);
            }
            if (extension.equals(CERTIFICATE_EXTENSION)) {
                ca.setCertificate(list[i]);
            } else if (extension.equals(CRL_EXTENSION)) {
                ca.setCRL(list[i]);
            } else if (extension.equals(METADATA_EXTENSION)) {
                ca.setMetadata(list[i]);
            } else {
                handleUnexpectedFile(description, list[i]);
                continue;
            }

        }

        log.debug("Found " + caListings.size() + " Trusted CAs found!!!");

        Message mess = new Message();
        mess.setType(MessageType.INFO);
        mess.setValue("A pre synchronization snapshot of the Trusted CA Directory found " + caListings.size() + " Trusted CAs.");
        messages.add(mess);
        log.info("DONE -Taking Snapshot of Trusted CA Directory, " + caListings.size() + " Trusted CAs found!!!");
    }

    private void handleUnexpectedFile(SyncDescription description, File f) {
        if (description.isDeleteInvalidFiles()) {
            Message mess = new Message();
            mess.setType(MessageType.WARNING);
            mess.setValue("The file " + f.getAbsolutePath() + " is unexpected and will be removed!!!");
            messages.add(mess);
            log.warn(mess.getValue());
            f.delete();
        } else {
            Message mess = new Message();
            mess.setType(MessageType.WARNING);
            mess.setValue("The file " + f.getAbsolutePath() + " is unexpected and will be ignored!!!");
            messages.add(mess);
            log.warn(mess.getValue());
        }
    }

    public static TrustedAuthority convert(org.cagrid.gts.model.TrustedAuthority ta) {
        TrustedAuthority authority = new TrustedAuthority();
        authority.setAuthorityGTS(ta.getAuthorityGTS());

        if (ta.getCertificate() != null) {
            org.cagrid.gts.model.X509Certificate c = ta.getCertificate();
            org.cagrid.trust.model.X509Certificate cert = new org.cagrid.trust.model.X509Certificate();
            cert.setCertificateEncodedString(c.getCertificateEncodedString());
            authority.setCertificate(cert);
        }

        if (ta.getCRL() != null) {
            org.cagrid.gts.model.X509CRL c = ta.getCRL();
            org.cagrid.trust.model.X509CRL crl = new org.cagrid.trust.model.X509CRL();
            crl.setCrlEncodedString(c.getCrlEncodedString());
            authority.setCRL(crl);
        }

        authority.setExpires(ta.getExpires());
        authority.setIsAuthority(ta.isIsAuthority());
        authority.setLastUpdated(ta.getLastUpdated());
        authority.setName(ta.getName());
        authority.setSourceGTS(ta.getSourceGTS());
        if (ta.getStatus() != null) {
            authority.setStatus(org.cagrid.trust.model.Status.fromValue(ta.getStatus().value()));
        }
        if (ta.getTrustLevels() != null) {
            org.cagrid.gts.model.TrustLevels ls = ta.getTrustLevels();
            TrustLevels levels = new TrustLevels();
            List<String> list = ls.getTrustLevel();
            for (String l : list) {
                levels.getTrustLevels().add(l);
            }
            authority.setTrustLevels(levels);
        }

        return authority;
    }

    public static org.cagrid.gts.model.TrustedAuthorityFilter convert(TrustedAuthorityFilter f) {
        org.cagrid.gts.model.TrustedAuthorityFilter filter = new org.cagrid.gts.model.TrustedAuthorityFilter();
        filter.setAuthorityGTS(f.getAuthorityGTS());
        filter.setCertificateDN(f.getCertificateDN());
        filter.setName(f.getName());
        filter.setSourceGTS(f.getSourceGTS());

        if (f.getStatus() != null) {
            filter.setStatus(Status.fromValue(f.getStatus().value()));
        }

        if (f.getTrustLevels() != null) {
            TrustLevels ls = f.getTrustLevels();
            org.cagrid.gts.model.TrustLevels levels = new org.cagrid.gts.model.TrustLevels();
            List<String> list = ls.getTrustLevels();
            for (String l : list) {
                levels.getTrustLevel().add(l);
            }
            filter.setTrustLevels(levels);
        }

        return filter;
    }

}
