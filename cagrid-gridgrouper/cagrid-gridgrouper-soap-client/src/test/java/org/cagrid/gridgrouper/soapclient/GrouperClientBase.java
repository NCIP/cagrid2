package org.cagrid.gridgrouper.soapclient;

import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.gridgrouper.wsrf.stubs.GridGrouperPortType;

import javax.net.ssl.KeyManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public abstract class GrouperClientBase {

	public final static String LOCAL_URL = "https://localhost:7737/gridgrouper";

	protected GridGrouperPortType gridGrouper;

	GrouperClientBase(String url) throws Exception {
		KeyStoreType truststore = new KeyStoreType();
//		truststore.setUrl(getClass().getClassLoader().getResource("truststore.jks").toString());
        truststore.setUrl("file:///Users/cmelean/Documents/Developer/source/cagrid/apache-servicemix-4.5.1/etc/gridgrouper/truststore.jks");
        truststore.setType("JKS");
        truststore.setPassword("inventrio");

        KeyManager keyManager = getKeyManager(
                "/Users/cmelean/Documents/Developer/source/cagrid/apache-servicemix-4.5.1/etc/gridgrouper/host.jks",
                "inventrio",
                "tomcat",
                "inventrio");


        gridGrouper = GridGrouperSoapClientFactory.createSoapClient(url, truststore, keyManager);
	}

    protected KeyManager getKeyManager(String keyStorePath, String storePass, String keyAlias, String keyPass) throws IOException, GeneralSecurityException {

        KeyStore keystore = getKeyStore(keyStorePath, storePass.toCharArray());

        Key key = keystore.getKey(keyAlias, keyPass.toCharArray());
        Certificate[] certAry = keystore.getCertificateChain(keyAlias);
        X509Certificate[] chain = new X509Certificate[certAry.length];
        for(int i=0; i<certAry.length; i++) {
            chain[i] = (X509Certificate)certAry[i];
        }
        return new SingleEntityKeyManager(keyAlias, chain, (PrivateKey) key);
    }

    protected static KeyStore getKeyStore(String fileURL, char[] storePass) throws IOException, GeneralSecurityException {
        File f = new File(fileURL);
        return getKeyStore(f, storePass);
    }

    protected static KeyStore getKeyStore(File file, char[] storePass) throws IOException, GeneralSecurityException {
        FileInputStream ksInputStream = new FileInputStream(file);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(ksInputStream, storePass);
        return ks;
    }
}
