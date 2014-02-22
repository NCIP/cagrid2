package org.cagrid.trust.service.core;

import org.apache.commons.io.FileUtils;
import org.cagrid.gaards.pki.CRLEntry;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.trust.service.test.utils.CA;
import org.cagrid.trust.service.test.utils.Credential;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * Created by langella on 2/14/14.
 */


public class TrustServiceFileSystemCAManagerOnlyTest {

    private File trustedCADirectory = new File("trusted-certificate-authorities");
    private TrustService trustService;

    @Before
    public void setup() {
        try {
            FileUtils.deleteDirectory(trustedCADirectory);
            trustedCADirectory.mkdirs();
            this.trustService = new TrustService();
            this.trustService.setTrustedCAManager(new FileSystemTrustedCAManager(trustedCADirectory.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @After
    public void cleanup() {
        trustedCADirectory.mkdirs();
    }

    @Test
    public void testWithAndWithoutCRL() {
        try {


            String caDN1 = "O=Organization ABC,OU=Unit XYZ,CN=Certificate Authority";
            String caDN2 = "O=Organization 123,OU=Unit 456,CN=Certificate Authority";

            CA ca1 = new CA(caDN1);
            CA ca2 = new CA(caDN2);


            Credential ca1User1 = ca1.createIdentityCertificate("CA ABC User 1");

            Assert.assertFalse(isTrusted(ca1User1));
            writeTrustedCertificateAuthority("ABC", ca1);
            this.trustService.reloadTrustManagers();
            Assert.assertTrue(isTrusted(ca1User1));

            Credential ca2User1 = ca2.createIdentityCertificate("CA 123 User 1");
            Assert.assertFalse(isTrusted(ca2User1));

            writeTrustedCertificateAuthority("123", ca2);
            this.trustService.reloadTrustManagers();
            Assert.assertTrue(isTrusted(ca1User1));
            Assert.assertTrue(isTrusted(ca2User1));

            Credential ca2User2 = ca2.createIdentityCertificate("CA 123 User 2");
            Assert.assertTrue(isTrusted(ca1User1));
            Assert.assertTrue(isTrusted(ca2User1));
            Assert.assertTrue(isTrusted(ca2User2));

            ca2.updateCRL(new CRLEntry(ca2User2.getCertificate().getSerialNumber(), 9));
            writeTrustedCertificateAuthority("123", ca2);
            this.trustService.reloadTrustManagers();
            Assert.assertTrue(isTrusted(ca1User1));
            Assert.assertTrue(isTrusted(ca2User1));
            Assert.assertFalse(isTrusted(ca2User2));


            System.out.println(caDN1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    private boolean isTrusted(Credential c) {
        return isTrusted(getChain(c));
    }

    private boolean isTrusted(X509Certificate[] chain) {
        X509TrustManager[] managers = this.trustService.getTrustManagers();
        for (X509TrustManager tm : managers) {
            try {
                tm.checkServerTrusted(chain, "RSA");
                return true;
            } catch (CertificateException e) {

            }
        }
        return false;
    }

    private X509Certificate[] getChain(Credential c) {
        X509Certificate[] chain = new X509Certificate[1];
        chain[0] = c.getCertificate();
        return chain;
    }


    private void writeTrustedCertificateAuthority(String name, CA ca) throws Exception {
        File certFile = new File(this.trustedCADirectory.getAbsolutePath() + File.separator + name + ".cert");
        CertUtil.writeCertificate(ca.getCertificate(), certFile);
        if (ca.getCRL() != null) {
            File crlFile = new File(this.trustedCADirectory.getAbsolutePath() + File.separator + name + ".crl");
            CertUtil.writeCRL(ca.getCRL(), crlFile);
        }
    }

}
