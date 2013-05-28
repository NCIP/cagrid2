/**
   DSATest.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Mon Jun  7 16:19:43 1999

   This package is a SSLv3/TLS implementation written by Eric Rescorla
   <ekr@rtfm.com> and licensed by Claymore Systems, Inc.

   Redistribution and use in source and binary forms, with or without
   modification, are permitted provided that the following conditions
   are met:
   1. Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
   2. Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
   3. All advertising materials mentioning features or use of this software
      must display the following acknowledgement:
      This product includes software developed by Claymore Systems, Inc.
   4. Neither the name of Claymore Systems, Inc. nor the name of Eric
      Rescorla may be used to endorse or promote products derived from this
      software without specific prior written permission.

   THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
   IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
   ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
   FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
   DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
   OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
   HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
   OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
   SUCH DAMAGE.

   $Id: DSATest.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.provider.test;

import java.io.*;
import java.security.*;
import COM.claymoresystems.crypto.RawDSAPublicKey;
import COM.claymoresystems.crypto.RawDSAParams;
import COM.claymoresystems.util.Bench;
import COM.claymoresystems.util.Util;
import sun.security.provider.DSAPublicKey;
import java.security.interfaces.DSAParams;

class DSATest extends Bench {

     private static final byte[] testString={0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,
				0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,
				0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,
				0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08,
				0x01,0x02,0x03,0x04,0x05,0x06,0x07,0x08
     };
				
     public static void main(String [] args){
       int iter=1;

       Security.addProvider(new COM.claymoresystems.provider.
	 ClaymoreProvider());
       
       if(args.length==0){
	 ;
       }
       else if(args.length==1){
	 iter=Integer.parseInt(args[0]);
       }
       else{
	 throw new InternalError("Bad arguments");
       }
	 
       int ss=register("SunSign");
       int sv=register("SunVerify");
       int cs=register("ClaymoreSign");
       int cv=register("ClaymoreVerify");

	 
       /* Test generating and verifying a DSA signature */
       try {
	 /* generate a key pair */
	 KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
	 keyGen.initialize(1024, new SecureRandom());
	 KeyPair pair = keyGen.generateKeyPair();


	 MessageDigest md=MessageDigest.getInstance("SHA-1");
	 md.update(testString);
	    
	 PrivateKey priv = pair.getPrivate();
	 byte[] digest=md.digest();

	 for(int i=0;i<iter;i++){
	   Signature ourDSASign = Signature.getInstance("RawDSA");
	   Signature ourDSAVerify = Signature.getInstance("RawDSA");
	   Signature stockDSAVerify = Signature.getInstance("DSA");
	   Signature stockDSASign = Signature.getInstance("DSA");

	   start(cs);
	   ourDSASign.initSign(priv);
	   ourDSASign.update(digest);
	   byte[] sig = ourDSASign.sign();
	   end(cs);

	   COM.claymoresystems.util.Util.xdump("signature",sig);

	   ourDSASign = Signature.getInstance("RawDSA");           
	   ourDSASign.initSign(priv);
	   ourDSASign.update(digest);
	   sig = ourDSASign.sign();
	   COM.claymoresystems.util.Util.xdump("second signature",sig);

           start(ss);
	   stockDSASign.initSign(priv);
	   stockDSASign.update(testString);
	   byte[] sig2=stockDSASign.sign();
	   end(ss);

	   if(!Util.areEqual(sig,sig2))
	     System.out.println("signatures don't match");
	     
	   PublicKey pub = pair.getPublic();
	   //Verify with our DSA implementation

	   start(cv);
	   ourDSAVerify.initVerify(pub);
	   ourDSAVerify.update(digest);
	   boolean verifies=ourDSAVerify.verify(sig);
	   end(cv);
	   
	   if(verifies) System.out.println("Verifies with our implementation");
	   else System.out.println("Failed our verify");
	   
	   // Verify with the stock DSA implementation
	   start(sv);
	   stockDSAVerify.initVerify(pub);
	   stockDSAVerify.update(testString);
	   verifies = stockDSAVerify.verify(sig);
	   end(sv);
	   if(verifies) System.out.println("Verifies with stock implementation");
	   else System.out.println("Failed stock verify");

           byte[] badSig=new byte[sig.length];
           System.arraycopy(sig,0,badSig,0,sig.length);
           badSig[0]++;
           ourDSAVerify = Signature.getInstance("RawDSA");

	   ourDSAVerify.initVerify(pub);
	   ourDSAVerify.update(digest);

           boolean caughtExcept=false;
           try {
             verifies=ourDSAVerify.verify(badSig);
           } catch (Exception e){
             caughtExcept=true;
           }

           if(!caughtExcept) System.out.println("Badly encoded sig Verifies with our implementation (BAD)")
                            ;
           else System.out.println("Badly encoded sig Failed our verify (OK)");
           
           // Bad signature
	   digest[digest.length-1]++;
          ourDSAVerify = Signature.getInstance("RawDSA");

	   ourDSAVerify.initVerify(pub);
	   ourDSAVerify.update(digest);
	   verifies=ourDSAVerify.verify(sig);
           if(verifies) System.out.println("Bad sig Verifies with our implementation (BAD)")
;
           else System.out.println("Bad sig Failed our verify (OK)");

	 }
       } catch (Exception e) {
	 System.err.println("Caught exception " + e.toString());
	 e.printStackTrace(System.out);
       }

       dump();
     }
}


