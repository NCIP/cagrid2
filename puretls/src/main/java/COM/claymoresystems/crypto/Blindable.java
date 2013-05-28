/**
   Blindable.java

   Copyright (C) 2003, RTFM, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri Apr 11 07:06:12 2003

   
*/




package COM.claymoresystems.crypto;
import xjava.security.interfaces.CryptixRSAPublicKey;

/**
 * This interface is implemented by algorithm objects that may be
 * use blinding to prevent timing attacks.
 *
 * It should be part of Cryptix but Cryptix is currently immutable
 * so it's part of PureTLS instead.
 *
 * <p>
 * <strong>
 * This interface
 * is not supported in JavaSoft's version of JCE.</strong>
 * <p>
 * @author  Eric Rescorla
 */

public interface Blindable {
     /**
      * Sets the random number generator to be used for blinding
      * Also turns on blinding
      *
      * @param rng the prng used to generate bliding factors
      *
      */
     public void setBlindingInfo(java.security.SecureRandom rng,
                                 CryptixRSAPublicKey pubKey);
}

