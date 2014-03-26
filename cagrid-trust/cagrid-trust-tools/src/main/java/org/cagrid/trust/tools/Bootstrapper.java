package org.cagrid.trust.tools;

import org.apache.commons.lang.StringUtils;
import org.cagrid.core.commandline.BaseCommandLine;
import org.cagrid.core.xml.XMLUtils;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.trust.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by langella on 3/25/14.
 */
public class Bootstrapper extends BaseCommandLine {


    private static final String PROPERTIES_FILE = "src/main/resources/bootstrapper.properties";
    private static final String TRUST_SERVICE_DIR = "cagrid-trust";
    private static final String CERTIFICATES_DIR = "certificates";
    private static final String SYNC_DESCRIPTION_FILE = "sync-description.xml";
    private static final String TRUST_SERVICE_CFG = "cagrid.trust.service.cfg";

    private static final String TRUSTED_CA_PROMPT = "Please enter the location of a trust root certificate authority";
    private static final String TRUSTED_CA_PROPERTY = "cagrid.trust.ca.certificate.";

    private static final String TRUSTSTORE_FILE_NAME = "truststore.jks";
    private static final String TRUSTSTORE_LOCATION_PROPERTY_VALUE = "${karaf.base}/etc/" + TRUST_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
    private static final String TRUSTSTORE_LOCATION_PROPERTY = "cagrid.trust.service.truststore.file";
    private static final String TRUSTSTORE_PASSWORD_PROMPT = "Please enter a password for the truststore";
    private static final String TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.trust.service.truststore.password";

    private static final String GTS_URL_PROMPT = "Please enter the URL of the GTS you want the trust service to sync with";
    private static final String GTS_URL_PROPERTY = "cagrid.trust.service.gts.url";

    private static final String GTS_IDENTITY_PROMPT = "Please enter the identity of the GTS you want the trust service to sync with";
    private static final String GTS_IDENTITY_PROPERTY = "cagrid.trust.service.gts.identity";

    private static final String GTS_EXPIRATION_HOURS_PROMPT = "Please enter the number of hours a certificate authority should be trusted for without getting an update from the GTS";
    private static final String GTS_EXPIRATION_HOURS_PROPERTY = "cagrid.trust.service.gts.expiration.hours";

    private static final String GTS_EXPIRATION_MINUTES_PROMPT = "Please enter the number of minutes a certificate authority should be trusted for without getting an update from the GTS";
    private static final String GTS_EXPIRATION_MINUTES_PROPERTY = "cagrid.trust.service.gts.expiration.minutes";

    private static final String GTS_EXPIRATION_SECONDS_PROMPT = "Please enter the number of seconds a certificate authority should be trusted for without getting an update from the GTS";
    private static final String GTS_EXPIRATION_SECONDS_PROPERTY = "cagrid.trust.service.gts.expiration.seconds";


    private List<X509Certificate> certificates;

    private File trustEtcDir;


    private Properties properties;

    public Bootstrapper(File propertiesFile) throws Exception {
        super(propertiesFile);

    }

    public Bootstrapper(Properties properties) throws Exception {
        super(properties);

    }

    public static void main(String[] args) {
        try {
            Bootstrapper main = new Bootstrapper(new File(PROPERTIES_FILE));
            main.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void execute() throws Exception {
        System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("*                Trust Service Bootstrapper                 *");
        System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("");
        this.properties = new Properties();
        trustEtcDir = new File(getServiceMixEtc().getAbsolutePath() + File.separator + TRUST_SERVICE_DIR);
        trustEtcDir.mkdirs();
        buildTrustList();
        createAndConfigureTruststore();
        writeTrustedCertificates();
        writeSyncDescription();
    }

    private void writeSyncDescription() {

        String gtsURL = getValue(GTS_URL_PROMPT, GTS_URL_PROPERTY);
        String gtsIdentity = getValue(GTS_IDENTITY_PROMPT, GTS_IDENTITY_PROPERTY);

        SyncDescription des = new SyncDescription();
        SyncDescriptor descriptor = new SyncDescriptor();
        des.getSyncDescriptors().add(descriptor);
        descriptor.setGTS(gtsURL);

        if (StringUtils.isBlank(gtsIdentity)) {
            descriptor.setPerformAuthorization(false);
        } else {
            descriptor.setPerformAuthorization(true);
            descriptor.setGTSIdentity(gtsIdentity);
        }

        Integer hours = Integer.valueOf(getValue(GTS_EXPIRATION_HOURS_PROMPT, GTS_EXPIRATION_HOURS_PROPERTY));
        Integer minutes = Integer.valueOf(getValue(GTS_EXPIRATION_MINUTES_PROMPT, GTS_EXPIRATION_MINUTES_PROPERTY));
        Integer seconds = Integer.valueOf(getValue(GTS_EXPIRATION_SECONDS_PROMPT, GTS_EXPIRATION_SECONDS_PROPERTY));
        Expiration expiration = new Expiration();
        expiration.setHours(hours);
        expiration.setMinutes(minutes);
        expiration.setSeconds(seconds);
        descriptor.setExpiration(expiration);

        TrustedAuthorityFilter f = new TrustedAuthorityFilter();
        f.setStatus(Status.TRUSTED);
        f.setLifetime(Lifetime.VALID);
        descriptor.getTrustedAuthorityFilters().add(f);

        ExcludedCAs excluded = new ExcludedCAs();

        for (X509Certificate cert : this.certificates) {
            excluded.getCASubjects().add(cert.getSubjectDN().getName());
        }

        des.setExcludedCAs(excluded);
        des.setDeleteInvalidFiles(false);
        des.setNextSync(BigInteger.valueOf(600));
        DateFilter df = new DateFilter();
        df.setMonth(1);
        df.setYear(0);
        df.setDay(0);
        des.setCacheSize(df);
        File syncDescription = new File(this.trustEtcDir.getAbsolutePath() + File.separator + SYNC_DESCRIPTION_FILE);
        try {
            XMLUtils.toXMLFile(des, syncDescription);
            System.out.println("Successfully wrote the sync description to " + syncDescription.getAbsolutePath() + ".");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private void writeTrustedCertificates() {
        try {
            File dir = new File(this.trustEtcDir.getAbsolutePath() + File.separator + CERTIFICATES_DIR);
            dir.mkdirs();
            int count = 0;
            for (X509Certificate cert : this.certificates) {
                File caFile = new File(dir.getAbsolutePath() + File.separator + "trust-ca-" + count + ".cert");
                CertUtil.writeCertificate(cert, caFile);
                System.out.println("Successfully wrote the CA " + cert.getSubjectDN().getName() + " to " + caFile.getAbsolutePath() + ".");
                count = count + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void createAndConfigureTruststore() {
        try {
            File f = new File(this.trustEtcDir.getAbsolutePath() + File.separator + TRUSTSTORE_FILE_NAME);
            KeyStore keyStore = KeyStore.getInstance("jks");
            keyStore.load(null);
            int count = 0;
            for (X509Certificate cert : this.certificates) {
                String alias = "trustca" + count;
                keyStore.setEntry(alias, new KeyStore.TrustedCertificateEntry(cert), null);
                count = count + 1;
                System.out.println("Added the CA " + cert.getSubjectDN().getName() + " to the truststore under the alias " + alias + ".");
            }
            String password = getValue(TRUSTSTORE_PASSWORD_PROMPT, TRUSTSTORE_PASSWORD_PROPERTY);
            FileOutputStream fos = new FileOutputStream(f);
            keyStore.store(fos, password.toCharArray());
            fos.close();

            properties.setProperty(TRUSTSTORE_LOCATION_PROPERTY, TRUSTSTORE_LOCATION_PROPERTY_VALUE);
            properties.setProperty(TRUSTSTORE_PASSWORD_PROPERTY, password);

            System.out.println("Truststore created at " + f.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void buildTrustList() {
        certificates = new ArrayList<X509Certificate>();
        int count = 0;
        boolean requestAnother = true;
        String property = TRUSTED_CA_PROPERTY + count;
        while (requestAnother) {

            String certLocation = getValue(TRUSTED_CA_PROMPT, property);
            try {
                X509Certificate cert = CertUtil.loadCertificate(new File(certLocation));
                System.out.println("Successfully loaded the certificate " + cert.getSubjectDN().getName() + " from the file " + certLocation + ".");
                this.certificates.add(cert);
                count = count + 1;
                property = TRUSTED_CA_PROPERTY + count;

                if (getProperties().getProperty(property) == null) {
                    requestAnother = getBooleanValue("Would you like to add another trust root certificate authority (true|false)?", "foo");
                }
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }

}
