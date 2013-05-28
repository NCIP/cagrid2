/**
   SSLDebug.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat Jun 19 08:39:13 1999

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

   $Id: SSLDebug.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.ptls;
import java.lang.Thread;

/** This class is intended for use in debugging PureTLS. 
    <P>
    By default, no debugging data is logged to the console, but you
    can exert some selective control over what types of data will
    be logged by using setDebug(). The taxonomy of data types is
    somewhat idiosyncratic and the exact places that debugging
    callouts have been inserted is somewhat dependent on where
    problems have been found in the past.
    <P>
    This class is largely useful for sending message traces to
    the developers.
*/    
public class SSLDebug {
     private boolean timeStamp=false;
     

     /** Print codec activities to the console. This allows you to
	 watch low-level PDUs and basic types being decoded*/
     public static final int DEBUG_CODEC= 1;

     /** This provides some basic diagnostics about message processing.
	 There isn't as much information being logged here as one would
	 like */
     public static final int DEBUG_MSG=2;

     /** Print out state changes in the handshake. This is useful
	 for diagnosing problems in the handshake state machine.
	 Unfortunately, these are currently printed as integers
	 which means you need to examine the source to see what's
	 going on*/
     public static final int DEBUG_STATE=4;

     /** Print out the input and output for crypto operations.
	 This is useful for comparing the operation of PureTLS
	 to another TLS implementation
     */
     public static final int DEBUG_CRYPTO=8;

     /** Print out commentary during the initialization phase,
	 including keyfile loading
     */
     public static final int DEBUG_INIT=16;

     /** Certificate processing debugging */
     public static final int DEBUG_CERT=32;

     /** Print out handshake results */
     public static final int DEBUG_HANDSHAKE=64;
     
     /**
	Print out all debugging information available. This produces
	incredibly copious output
      */
     public static final int DEBUG_ALL = 0xffff;

//     static int debugVal=DEBUG_CRYPTO;
     static int debugVal=0;


     /** Set the debugging value.

	 @param flag the bitwise OR of the debugging values of your choice or 0 for none
     */
     public static void setDebug(int flag){
       debugVal=flag;
     }

     public static boolean getDebug(int flag){
       return ((debugVal & flag)>0)?true:false;
     }
     
     public static void debug(int type,String val){
       if((debugVal & type) > 0){
         String tid=Thread.currentThread().toString();
	 System.out.println("Thread " + tid + val);
       }
     }

     public static void debug(int type,String label,byte[] hd){
       if((debugVal & type) > 0){
         String tid=Thread.currentThread().toString();
	 System.out.println("Thread " + tid);
	 COM.claymoresystems.util.Util.xdump(label,hd);
       }
     }
}
