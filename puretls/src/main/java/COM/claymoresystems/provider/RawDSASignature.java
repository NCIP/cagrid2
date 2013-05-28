/**
   RawDSASignature.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Mon Jun  7 13:05:29 1999

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

   $Id: RawDSASignature.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


/** A class to perform DSA signature and verification.
    <P>
    This is necessary because Sun's provider does not do raw mode. 
    <P>
    See FIPS PUB 186, ANSI X9.57
    
    @author EKR
*/
package COM.claymoresystems.provider;

import java.security.Signature;
import java.security.PublicKey;
import java.security.interfaces.DSAPublicKey;
import java.security.PrivateKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAParams;
import java.security.SecureRandom;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.security.InvalidParameterException;

import java.math.BigInteger;

public class RawDSASignature extends Signature
{
     private BigInteger p;
     private BigInteger q;
     private BigInteger g;          
     private BigInteger XY; // X or Y
     byte[] digest=null;
     SecureRandom rng;

     public RawDSASignature(String name){
       super(name);
     }
     
     public RawDSASignature() {
       super("RawDSA");
     }

     /** <b>SPI: </b> Initializes this object for verification
	 using the given key

	 @param key the public key
	 @exception InvalidKeyException if the key class doesn't
	 implement than DSAPublicKey
     */
     protected void engineInitVerify(PublicKey key)
       throws InvalidKeyException {
       if(!(key instanceof DSAPublicKey))
	 throw new InvalidKeyException(getAlgorithm() +
	   ": Not a DSA Public Key");

       DSAPublicKey dsa=(DSAPublicKey)key;
       extractParams(dsa.getParams());
       XY=dsa.getY();
     }


     /** <b>SPI: </b> Initializes this object for signing
	 using the private key

	 @param key the private key
	 @exception InvalidKeyException if the key class doesn't
	 implement than DSAPrivateKey
     */
     protected void engineInitSign(PrivateKey key)
       throws InvalidKeyException {
       if(!(key instanceof DSAPrivateKey))
	 throw new InvalidKeyException(getAlgorithm() +
	   ": Not a DSA Private Key");

       DSAPrivateKey dsa=(DSAPrivateKey)key;
       extractParams(dsa.getParams());
       XY=dsa.getX();
     }

     /** <b>SPI: </b> Single byte updates are forbidden for
	 Raw DSA.

	 @exception SignatureException bad input
     */
     protected void engineUpdate(byte b)
       throws SignatureException {
       throw new SignatureException(getAlgorithm() +
	 ": Must be called with a SHA-1 digest for input");
     }
     
     /** <b>SPI: </b> Updates the digest. Since we're doing
	 raw DSA, this must be a 20 byte string. This can
	 only be called once with a single digest value
	 or we throw an error

	 @param b the buffer containing the digest
	 @param off the offset into the buffer
	 @param len the length (must be 20)

	 @exception SignatureException either if the algorithm
	 was improperly initialized or you try to call update
	 more than once
     */
     protected void engineUpdate(byte[] b,int off,int len)
       throws SignatureException{
       if(state != VERIFY && state != SIGN)
	 throw new SignatureException(getAlgorithm() + ": Not initialized");
       if(digest!=null)
	 throw new SignatureException(getAlgorithm() +
	   ": Raw DSA may only be updated once");
       if(len!=20)
	 throw new SignatureException(getAlgorithm() +
	   ": Raw DSA must have a 20 byte input");

       digest=new byte[20];
       System.arraycopy(b,off,digest,0,len);
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
       if(state != SIGN)
	 throw new SignatureException(getAlgorithm() + ": Not initialized");
       if(digest == null)
	 throw new SignatureException(getAlgorithm() + ": Must supply digest");

       if(rng==null)
	 rng=new SecureRandom();

       // Generate k,k-1,r
       // 
       // TODO: Rewrite this so that the initial G value
       // is chosen following FIPS-186 S 3.2
       // We generate a 180 bit G and then take mod q.
       // The standard says 160 but this produces a bias in k which
       // is potentially exploitable (see Bleichenbacher). Using 180 bits
       // reduces the bias by 2^20 which should be very safe.
       BigInteger G=new BigInteger(180,rng);
       BigInteger k=G.mod(q);
       BigInteger kinv=k.modInverse(q);

       // Now compute the signature following S 5
       //  r = (gk mod p) mod q    and
       BigInteger r=g.modPow(k,p).mod(q);

       //  s = (k-1(SHA(M) + xr)) mod q.
       BigInteger digestBig=new BigInteger(1,digest);
       BigInteger tmp=digestBig.add(XY.multiply(r));
       tmp=tmp.multiply(kinv);
       BigInteger s=tmp.mod(q);

       // Wrap this a la ANSI X9.57
       byte[] r_bytes=r.toByteArray();
       byte[] s_bytes=s.toByteArray();

       byte[] encoding=new byte[r_bytes.length + s_bytes.length + 6];


       // SEQUENCE OF
       encoding[0]=0x30;
       encoding[1]=(byte)((r_bytes.length + s_bytes.length + 4)&0xff);

       int offset=2;

       // r INTEGER
       encoding[offset++]=2;
       encoding[offset++]=(byte)(r_bytes.length & 0xff	);
       System.arraycopy(r_bytes,0,encoding,offset,r_bytes.length);
       offset+=r_bytes.length;

       // s INTEGER
       encoding[offset++]=2;
       encoding[offset++]=(byte)(s_bytes.length & 0xff);
       System.arraycopy(s_bytes,0,encoding,offset,s_bytes.length);

//       COM.claymoresystems.Util.xdump("Signature encoding",encoding);
       return encoding;
     }

     /** <b>SPI: </b> Raw Verify

	 @exception SignatureException bad input
     */
     protected boolean engineVerify(byte[] signature)
       throws SignatureException{
       int offset=0;
       int left=signature.length;

       if(state != VERIFY)
	 throw new SignatureException(getAlgorithm() + ": Not initialized");
       if(digest == null)
	 throw new SignatureException(getAlgorithm() + ": Must supply digest");
       BigInteger M=new BigInteger(1,digest);
	 
       // First crack the encoding
       // TODO: Check to see what the right exception is
       encodeAssert(signature,offset++,0x30,"Tag: expecting sequence");
       encodeAssert(signature,offset++,signature.length-2,"length");
       encodeAssert(signature,offset++,0x2,"Tag: expecting integer");

       int r_length=signature[offset++];
       left-=4;       
       if(r_length>left)
	 throw new SignatureException("r longer than total encoding");
       byte[] r_bytes=new byte[r_length];
       System.arraycopy(signature,offset,r_bytes,0,r_length);

       left-=r_length;
       offset+=r_length;

       encodeAssert(signature,offset++,0x2,"Tag: expecting integer");
       int s_length=signature[offset++];
       left-=2;       
       if(s_length!=left)
	 throw new SignatureException("incorrect length for than total encoding");
       byte[] s_bytes=new byte[s_length];
       System.arraycopy(signature,offset,s_bytes,0,s_length);

       BigInteger r=new BigInteger(r_bytes);
       BigInteger s=new BigInteger(s_bytes);

       BigInteger zero=new BigInteger("0");
       // Initial range checks
       if(r.compareTo(zero)<=0) return false;
       if(r.compareTo(q)>=0) return false;
       if(s.compareTo(zero)<=0) return false;       
       if(s.compareTo(q)>=0) return false;
	 
       // Now verify as per FIPS-186 S 2.
       BigInteger w=s.modInverse(q);
       BigInteger u1=M.multiply(w);
       BigInteger u1q=u1.mod(q);
       BigInteger u2=r.multiply(w);
       BigInteger u2q=u2.mod(q);
       
       BigInteger gu1=g.modPow(u1q,p);
       BigInteger yu2=XY.modPow(u2q,p);
       BigInteger vtmp=gu1.multiply(yu2);
       BigInteger vtmpp=vtmp.mod(p);
       BigInteger v=vtmpp.mod(q);

       // Wow, this is simple after all that
       return v.equals(r);
     }

     /**
	<b> SPI: </b> There aren't any parameters
     */
     protected void engineSetParameter(String param, Object value)
       throws InvalidParameterException {
       if(param.equals("SecureRandom")){
	 rng=(SecureRandom)value;
	 return;
       }
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

     // Internals
     private void encodeAssert(byte[] arr,int offset, int value, String error)
       throws SignatureException {
       if(arr[offset]!=value)
	 throw new SignatureException(getAlgorithm() + ": Encoding error. Bad "+ error);

     }

     private void extractParams(DSAParams params){
       p=params.getP();
       q=params.getQ();
       g=params.getG();
     }
}
