/**
   CertVerify.java -- Unit test for certificates

   Copyright (C) 2001, RTFM, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue Jan  9 12:11:17 2001
*/



package COM.claymoresystems.cert;

import java.io.*;
import java.util.*;
import COM.claymoresystems.crypto.*;
import COM.claymoresystems.ptls.SSLDebug;
import COM.claymoresystems.ptls.LoadProviders;
import COM.claymoresystems.sslg.*;

public class CertVerify {
     public static CertContext cctx=new CertContext();
     public static void loadRoots(String file) throws Exception {
       FileInputStream fis=new FileInputStream(file);

       BufferedReader br=new BufferedReader(new InputStreamReader(fis));

       for(;;){
	 byte[] root=WrappedObject.loadObject(br,"CERTIFICATE",null);

	 if(root==null)
	   break;
         cctx.addRoot(root);
       }
     }

     public static byte[] loadCert(String file) throws Exception {
       FileInputStream fis=new FileInputStream(file);
       BufferedReader br=new BufferedReader(new InputStreamReader(fis));

       byte[] cert=WrappedObject.loadObject(br,"CERTIFICATE",null);

       return(cert);
     }


     
     public static void main(String args[]) throws Exception {
       LoadProviders.init();
       int argIndex=0;
       boolean damage=false;
       CertVerifyPolicyInt policy=new CertVerifyPolicyInt();
       
       while(argIndex<args.length){
         if(!args[argIndex].startsWith("-"))
           break;

         if(args[argIndex].equals("-debug"))
           SSLDebug.setDebug(SSLDebug.DEBUG_CERT | SSLDebug.DEBUG_CRYPTO);
         else if(args[argIndex].equals("-damage"))
           damage=true;
         else if(args[argIndex].equals("-checkbc"))
           policy.requireBasicConstraints(true);
         else if(args[argIndex].equals("-bccritical"))
           policy.requireBasicConstraintsCritical(true);
         else if(args[argIndex].equals("-checkdates"))
           policy.checkDates(true);
         else if(args[argIndex].equals("-checkkeyusage"))
           policy.requireKeyUsage(true);
         else
           throw new InternalError("Bogus argument " + args[argIndex]);
         
         argIndex++;
       }
       loadRoots(args[argIndex++]);

       Vector vec=new Vector();       
       byte[] cert_ber;
       while(argIndex<args.length){
         cert_ber=loadCert(args[argIndex]);
         if(damage && (argIndex==(args.length-1))){
           cert_ber[cert_ber.length-1]++;
         }
         X509Cert cert=new X509Cert(cert_ber);
         vec.addElement(cert);
         
         argIndex++;
       }

       Vector vchain=X509Cert.verifyCertChain(cctx,vec,policy);

       if(vchain==null){
         System.out.println("Couldn't verify chain");
       }
       else if(vchain.size()==1){
         System.out.println("IS ROOT! Didn't actually verify signature");
       }
       else{
         System.out.println("Verified successfully");

         for(int i=0;i<vchain.size();i++){
           X509Cert cert=(X509Cert)vchain.elementAt(i);
           
           System.out.println("Issuer "+cert.getIssuerName().getNameString());
           System.out.println("Subject "+cert.getSubjectName().getNameString());
           System.out.println("Serial "+cert.getSerial());
           System.out.println("Validity "+cert.getValidityNotBefore() +"-"+
             cert.getValidityNotAfter());
           
         }
       }
     }
}
