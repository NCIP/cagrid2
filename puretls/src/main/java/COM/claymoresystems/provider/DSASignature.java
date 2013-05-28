/**
   DSASignature.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue Aug 17 16:41:03 1999

   This package is a SSLv3/TLS implementation written by Eric Rescorla
   <ekr\@rtfm.com> and licensed by Claymore Systems, Inc.

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


   $Id: DSASignature.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

/** A class to perform DSA signature and verification.
    <P>
    This uses RawDSASignature to perform DSASignature with SHA
    hashing
    <P>
    See FIPS PUB 186, ANSI X9.57
    
    @author EKR
*/
package COM.claymoresystems.provider;

import java.security.Signature;
import java.security.PublicKey;
import java.security.MessageDigest;
import java.security.interfaces.DSAPublicKey;
import java.security.PrivateKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAParams;
import java.security.SecureRandom;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.security.InvalidParameterException;

import java.math.BigInteger;

public class DSASignature extends RawDSASignature
{
     MessageDigest dig;
     
     public DSASignature(){
       super("DSA");
     }

     /** <b>SPI: </b> Initializes this object for verification
	 using the given key

	 @param key the public key
	 @exception InvalidKeyException if the key class doesn't
	 implement DSAPublicKey
     */
     protected void engineInitVerify(PublicKey key)
       throws InvalidKeyException {
       super.engineInitVerify(key);

       init();
     }


     /** <b>SPI: </b> Initializes this object for signing
	 using the private key

	 @param key the private key
	 @exception InvalidKeyException if the key class doesn't
	 implement than DSAPrivateKey
     */
     protected void engineInitSign(PrivateKey key)
       throws InvalidKeyException {
       super.engineInitSign(key);

       init();
     }


     /** <b>SPI: </b> Single byte update

	 @exception SignatureException in case of error
     */
     protected void engineUpdate(byte b)
       throws SignatureException {
       dig.update(b);
     }

     /** <b>SPI: </b> Update with a buffer

	 @exception SignatureException in case of error
     */
     protected void engineUpdate(byte[] b,int off,int len)
       throws SignatureException{
       dig.update(b,off,len);
     }

     /**
	Sign the input, following FIPS-186.
	The signature is encoded following ANSI X9.57:

	DSSSignature ::= SEQUENCE {
	  r INTEGER,
	  s INTEGER
	}

	@exception SignatureException if the engine isn't initialized
	properly
     */
     protected byte[] engineSign()
       throws SignatureException {
       finish();
       return super.engineSign();
     }

     /** <b>SPI: </b> Raw Verify

	 @exception SignatureException bad input
     */
     protected boolean engineVerify(byte[] signature)
       throws SignatureException{
       finish();
       return super.engineVerify(signature);
     }

     private void init() {
       try {
	 dig=MessageDigest.getInstance("SHA-1");
       } catch (java.security.NoSuchAlgorithmException e){
	 throw new InternalError("Hey, what happened to SHA-1???");
       }
     }

     private void finish(){
       digest=dig.digest();
     }
}     
