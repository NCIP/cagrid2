/**
   SSLDHPrivateKey.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All 3
   Rights Reserved.

   ekr@rtfm.com  Sun May  9 16:31:12 1999

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

   $Id: SSLDHPrivateKey.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.ptls;

import java.security.SecureRandom;
import java.math.BigInteger;

import COM.claymoresystems.crypto.DHPrivateKey;
import COM.claymoresystems.crypto.DHPublicKey;

public class SSLDHPrivateKey extends DHPrivateKey
{
     private SecureRandom rand;
     private static int PRIME_CERTAINTY=80;
     
     public void initPrivateKey(BigInteger g_,BigInteger p_,SecureRandom rand_){
       g=g_;
       p=p_;
       rand=rand_;
       generatePrivate();
     }

/** Generate a DH private key.

    We generate the parameters in one of two modes:
    1. If sg is false, then we simply generate a large p
       and check that it's a generator using the trick of
       checking the p % 24 == 11 (From Phil Karn via OpenSSL).

    2. If sg is true, then we generate Sophie-Germain primes
       according to the procedures
       of RFC2412, except that our p is chosen randomly rather
       than via pi. 
    
    The text from RFC2412 describing the virtues of this procedure
    follows:

   The primes for groups 1 and 2 were selected to have certain
   properties.  The high order 64 bits are forced to 1.  This helps the
   classical remainder algorithm, because the trial quotient digit can
   always be taken as the high order word of the dividend, possibly +1.
   The low order 64 bits are forced to 1.  This helps the Montgomery-
   style remainder algorithms, because the multiplier digit can always
   be taken to be the low order word of the dividend.  The middle bits
   are taken from the binary expansion of pi.  This guarantees that they
   are effectively random, while avoiding any suspicion that the primes
   have secretly been selected to be weak.

   Because both primes are based on pi, there is a large section of
   overlap in the hexadecimal representations of the two primes.  The
   primes are chosen to be Sophie Germain primes (i.e., (P-1)/2 is also
   prime), to have the maximum strength against the square-root attack
   on the discrete logarithm problem.

   The starting trial numbers were repeatedly incremented by 2^64 until
   suitable primes were located.

   Because these two primes are congruent to 7 (mod 8), 2 is a quadratic
   residue of each prime.  All powers of 2 will also be quadratic
   residues.  This prevents an opponent from learning the low order bit
   of the Diffie-Hellman exponent (AKA the subgroup confinement
   problem).  Using 2 as a generator is efficient for some modular
   exponentiation algorithms.  [Note that 2 is technically not a
   generator in the number theory sense, because it omits half of the
   possible residues mod P.  From a cryptographic viewpoint, this is a
   virtue.]
*/    
     public void initPrivateKey(SecureRandom rand_,int keylength,boolean sg){
       BigInteger p_tmp=null;
       BigInteger q;
       BigInteger padd=null;
       BigInteger twofer=new BigInteger("24");
       BigInteger eleven=new BigInteger("11");
       BigInteger zero=new BigInteger("0");
       
       if((keylength%8)!=0)
	 throw new InternalError("keylength must be a multiple of 8");

       if(keylength<768)
	 throw new InternalError("Keylength must be minimum 768");

       int l=keylength/8; // length in bytes
       byte[] p_bytes=new byte[l];

       if(sg){
	 rand_.nextBytes(p_bytes);
	 
	 // Set the top and bottom 64 bits to 1
	 for(int i=0;i<8;i++){
	   p_bytes[i]=(byte)0xff;
	   p_bytes[l-(i+1)]=(byte)0xff;
	 }
	 
	 // We'll Increment by 2^64
	 BigInteger padd_tmp=new BigInteger("0");
	 padd=padd_tmp.setBit(64);
	 p_tmp=new BigInteger(1,p_bytes);
       }
       
       g=new BigInteger("2");
       
       do {
	 BigInteger p_hold;

	 // Generate Sophie-Germain primes
	 if(sg){
	   p_hold=p_tmp.add(padd);;
	   p_tmp=p_hold;

	   // Quickly check if p is prime
	   if(!p_tmp.isProbablePrime(2))
	     continue;
	   SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"p passes quick check");
	   q=p_tmp.shiftRight(1);
	   if(!q.isProbablePrime(PRIME_CERTAINTY))
	     continue;
	   SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"q is prime");	   
	 }
	 else{
	   rand_.nextBytes(p_bytes);
	   p_bytes[0]|=(byte)0x80;
	   p_bytes[l-1]|=1;
	   
	   p_tmp=new BigInteger(1,p_bytes);

	   // Arrange that p % 24==11
	   BigInteger md=p_tmp.mod(twofer);
	   BigInteger tmp=p_tmp.subtract(md);
	   p_tmp=eleven.add(tmp);
	 }

	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"p candidate",
	   p_tmp.toByteArray());
	 
	 if(!p_tmp.isProbablePrime(PRIME_CERTAINTY)){
	   SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"p not prime");
	   continue;
	 }

	 break;
       } while(true);
       
       rand=rand_;
       p=p_tmp;

       BigInteger mod24=p.mod(twofer);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"P is prime"+
         p.isProbablePrime(PRIME_CERTAINTY)+"mod 24="+mod24);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,
	 "p",p.toByteArray());

       generatePrivate();
     }
     
     public byte[] keyAgree(DHPublicKey pub,boolean check){
       BigInteger pY;
       BigInteger pg;
       BigInteger pp;

       if(check){
	 pp=pub.getp();
	 pg=pub.getg();

	 if(pg!=null || pp != null){
	   if(g.compareTo(pg)!=0)
	     throw new Error("DH parameters don't match (g)");
	   if(p.compareTo(pp)!=0)
	     throw new Error("DH parameters don't match (p)");
	 }
       }
       
       pY=pub.getY();

       return toBytes(pY.modPow(X,p));
     }
       
     private void generatePrivate(){
       int bits=p.bitLength();
       int bytelen=bits/8;
       int bitr=bits%8;
       int shift;

       if(bitr>0) bytelen++;
       
       byte[] buf=new byte[bytelen];

       // We want to make a number with the high bit low and
       // the others preserved
       rand.nextBytes(buf);

       shift=(bitr>0)?8-bitr:1;
       buf[0] &= (byte)(0xff>>shift);

       X=new BigInteger(1,buf);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"DH private",buf);
       Y=g.modPow(X,p);
     }
}

     
       
