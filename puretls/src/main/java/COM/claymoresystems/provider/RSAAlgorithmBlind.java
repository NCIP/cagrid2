/**
   RSAAlgorithmBlind.java

   Copyright (C) 2003, RTFM, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri Apr 11 08:07:26 2003
*/


package COM.claymoresystems.provider;

import java.math.BigInteger;
import cryptix.provider.rsa.RSAAlgorithm;
import java.security.SecureRandom;

/**
   Use RSA blinding to defeat timing attacks
*/
class RSAAlgorithmBlind {
     
     static BigInteger rsa(BigInteger X, BigInteger n, BigInteger exp,
       BigInteger otherExp, BigInteger p, BigInteger q, BigInteger u,
       SecureRandom blindingRNG){
       
       BigInteger r_to_e;
       BigInteger r_inv=null;
       BigInteger result;

       // Blind
       if(blindingRNG != null) {
            // Do blinding
          BigInteger r;
          
          r=new BigInteger(n.bitLength()-1,blindingRNG);
          r_to_e = r.modPow(otherExp,n); 
          X = X.multiply(r_to_e).mod(n); // Blind
          r_inv = r.modInverse(n);
       }

       // Call Cryptix to perform the RSA
       result=RSAAlgorithm.rsa(X,n,exp,p,q,u);

       // Unblind
       if(blindingRNG==null){
            return(result);
       }
       else{
         return(result.multiply(r_inv).mod(n));  // Unblind
       }
     }
}



       
       
    

     
