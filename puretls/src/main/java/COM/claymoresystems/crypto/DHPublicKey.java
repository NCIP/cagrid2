/**
   DHPublicKey.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun May  9 16:53:29 1999

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

   $Id: DHPublicKey.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/



package COM.claymoresystems.crypto;
import java.math.BigInteger;

public class DHPublicKey
     implements java.security.PublicKey
{
     private BigInteger Y=null;
     private BigInteger g=null;
     private BigInteger p=null;

     public DHPublicKey(BigInteger g_,BigInteger p_,BigInteger Y_){
       g=g_;
       p=p_;
       Y=Y_;
     }

     public DHPublicKey(BigInteger Y_){
       Y=Y_;
     }

     public BigInteger getY(){
       return Y;
     }
     
     public BigInteger getg(){
       return g;
     }

     public BigInteger getp(){
       return p;
     }

     public byte[] getEncoded(){
       // TODO: Fix this
       return null;
     }

     public String getAlgorithm(){
       return "DH";
     }

     public String getFormat(){
       return "None";
     }
}     
