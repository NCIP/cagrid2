/**
   RSASignature.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun Jun 20 19:27:27 1999

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

   $Id: RSASignature.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.provider;

import COM.claymoresystems.crypto.PKCS1Pad;
import java.security.Signature;
import java.security.PublicKey;
import java.security.PrivateKey;
import xjava.security.interfaces.CryptixRSAPrivateKey;
import xjava.security.interfaces.CryptixRSAPublicKey;
import xjava.security.interfaces.RSAFactors;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyException;
import java.security.SecureRandom;
import cryptix.provider.rsa.RSAAlgorithm;
import COM.claymoresystems.crypto.Blindable;
import COM.claymoresystems.util.Util;

import java.math.BigInteger;
/** This class implements RSA signature w/ PKCS#1 padding but
    no DigestInfo encoding because Cryptix doesn't
    and SSL requires it for Client Auth.
    <P>
    We assume that we already have an RSAEncryption primitive floating
    around.
*/
public class RSASignature extends Signature implements Blindable
{
     private byte[] data=null;
     private BigInteger n;
     private BigInteger exp;
     private BigInteger publicExp; /* Used only for private ops */
     private BigInteger p;
     private BigInteger q;
     private BigInteger u;
     private SecureRandom blindingRNG=null;
     public RSASignature() {
       super("RawRSAPKCS#1");
     }

     /** <b>SPI: </b> Initializes this object for verification
	 using the given key

	 @param key the public key
	 @exception InvalidKeyException if the key class doesn't
	 implement CryptixRSAPublicKey
     */
     protected void engineInitVerify(PublicKey key)
       throws InvalidKeyException  {
       if(!(key instanceof CryptixRSAPublicKey))
	 throw new InvalidKeyException(getAlgorithm() +
	   ": Not a RSA Public Key");

       CryptixRSAPublicKey rsa=(CryptixRSAPublicKey)key;
       n=rsa.getModulus();
       exp=rsa.getExponent();
     }

     /** <b>SPI: </b> Initializes this object for signing
	 using the private key

	 @param key the private key
	 @exception InvalidKeyException if the key class doesn't
	 implement than CryptixRSAPrivateKey
     */
     protected void engineInitSign(PrivateKey key)
       throws InvalidKeyException {
       if(!(key instanceof CryptixRSAPrivateKey))
	 throw new InvalidKeyException(getAlgorithm() +
	   ": Not a RSA Private Key");

       CryptixRSAPrivateKey rsa=(CryptixRSAPrivateKey)key;
       n=rsa.getModulus();
       exp=rsa.getExponent();

       if(key instanceof RSAFactors){
	 RSAFactors factors=(RSAFactors)key;
	 p = factors.getP();
	 q = factors.getQ();
	 u = factors.getInverseOfQModP();
       }
     } 

     /** <b>SPI: </b> Single byte updates are forbidden

	 @exception SignatureException if you do a single byte update
     */
     protected void engineUpdate(byte b)
       throws SignatureException {
       throw new SignatureException(getAlgorithm() +
	 ": Must be called with a complete input");
     }
     
     /** <b>SPI: </b> Updates the data. This can
	 only be called once with a single data value
	 or we throw an error

	 @param b the buffer containing the data
	 @param off the offset into the buffer
	 @param len the length 

	 @exception SignatureException either if the algorithm
	 was improperly initialized or you try to call update
	 more than once
     */
     protected void engineUpdate(byte[] b,int off,int len)
       throws SignatureException{
       if(state != VERIFY && state != SIGN)
	 throw new SignatureException(getAlgorithm() + ": Not initialized");
       if(data!=null)
	 throw new SignatureException(getAlgorithm() +
	   ": Raw RSA may only be updated once");
       data=new byte[len];
       System.arraycopy(b,off,data,0,len);
     }

     /** <b> SPI: </b> Sign the input
	 
	 @return a signature as a byte string
         @exception SignatureException bad input
     */
     protected byte[] engineSign()
       throws SignatureException {
       if(state != SIGN)
	 throw new SignatureException(getAlgorithm() + ": Not initialized");
       if(data == null)
	 throw new SignatureException(getAlgorithm() + ": Must supply input");

       byte[] toBeSigned=PKCS1Pad.pkcs1PadBuf(data,n,2);
//       COM.claymoresystems.util.Util.xdump("tobeSigned",toBeSigned);
       BigInteger padded=new BigInteger(1,toBeSigned);
       BigInteger sval=RSAAlgorithmBlind.rsa(padded,n,exp,publicExp,
         p,q,u,blindingRNG);
       byte[] signed=sigToBytes(sval);
//       COM.claymoresystems.util.Util.xdump("signed",signed);

       return signed;
     }

     /** <b> SPI: </b> Verify the input
	 
	 @return true or false
         @exception SignatureException bad input
     */
     protected boolean engineVerify(byte[] signature)
       throws SignatureException {
       if(state != VERIFY)
	 throw new SignatureException(getAlgorithm() + ": Not initialized");
       if(data == null)
	 throw new SignatureException(getAlgorithm() + ": Must supply input");

       if(signature.length!=modLength())
         throw new xjava.security.IllegalBlockSizeException("Wrong input length");
       
       BigInteger sval=new BigInteger(1,signature);
       if(sval.compareTo(n)>0)
         throw new xjava.security.IllegalBlockSizeException("Signature greater than modulus");

       byte[] buf=RSAAlgorithmBlind.rsa(sval,n,exp,null,p,q,u,null).toByteArray();

       byte[] data_prime=null;
       
       try {
         data_prime=PKCS1Pad.pkcs1UnpadBuf(buf,PKCS1Pad.VERIFY,n);
       } catch (xjava.security.IllegalBlockSizeException e){
         return false;
       }

/*       Util.xdump("DATA",data);
         Util.xdump("DATA PRIME",data_prime);*/ 
       
       return(cryptix.util.core.ArrayUtil.areEqual(data_prime,data));
     }


     /**
      * set the RNG for blinding
      *
      * @param rng a secure RNG
      */
     public void setBlindingInfo(SecureRandom rng,CryptixRSAPublicKey pubKey){
       blindingRNG = rng;
       publicExp=pubKey.getExponent();
     }

     /**
	<b> SPI: </b> There aren't any parameters
     */
     protected void engineSetParameter(String param, Object value)
       throws InvalidParameterException {
       throw new InvalidParameterException(getAlgorithm() +
	 ": No settable parameters");
     }

     /**
	<b> SPI: </b> There aren't any parameters
     */
     protected Object engineGetParameter(String param)
       throws InvalidParameterException {
       throw new InvalidParameterException(getAlgorithm() +
	 ": No settable parameters");
     }

     
     protected byte[] sigToBytes(BigInteger num){
       byte[] tmp=num.toByteArray();
       int length=modLength();
       int i;
       
       // Ensure that this is the right length
       if(tmp.length==length)
         return tmp;

       // If it's too short, pad with zeros on the right
       // If it's too long, remove zeros from the left.
       // if it's too long without zeros, throw an error.
       byte[] ntmp=new byte[length];
         
       if(tmp.length<length){

         for(i=0;i<(length-tmp.length);i++){
           ntmp[i]=0;
         }
         System.arraycopy(tmp,0,ntmp,i,tmp.length);
       }
       else {
         for(i=0;i<(tmp.length-length);i++){
           if(tmp[i]!=0)
             throw new InternalError("RSA signature error");
         }
         System.arraycopy(tmp,i,ntmp,0,length);
       }
       return ntmp;
     }

     private int modLength(){
       int length=n.bitLength()/8;
       length+=((n.bitLength()%8)>0)?1:0;

       return length;
     }
}
