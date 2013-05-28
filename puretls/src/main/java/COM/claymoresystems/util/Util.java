/**
   Util.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue May 11 17:45:23 1999

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

   $Id: Util.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.util;

public class Util {
     static String hex[]={"0","1","2","3","4","5","6","7","8","9",
		   "a","b","c","d","e","f"};

     public static void xdump(String label,byte[] arr){
       System.out.println(label + "[" + arr.length + "]");

       xdump(arr);
     }

     public static void xdump(byte[] arr){
       int i;

       for(i=0;i<arr.length;i++){
	 if((i>0) && ((i%12)==0)) System.out.println("");
	 
	 System.out.print(hex[(arr[i]>>4)& 0x0f] + hex[arr[i] & 0x0f] + " ");
       }

       System.out.println("");
     }

     public static String toHex(byte[] arr){
       StringBuffer str=new StringBuffer();

       for(int i=0;i<arr.length;i++){
	 str.append(hex[(arr[i]>>4)&0x0f]);
	 str.append(hex[arr[i]&0x0f]);
       }

       return str.toString();
     }

     public static boolean areEqual(byte[] arr1, byte[] arr2){
       if(arr1.length != arr2.length)
	 return false;

       int al=arr1.length;
       
       for(int i=0;i<al;i++)
	 if(arr1[i]!=arr2[i]) return false;

       return true;
     }
     
     public static byte[] toBytes(long val){
       return toBytes(val,8);
     }

     public static byte[] toBytes(short val){
       return toBytes((long)val,2);
     }

     public static byte[] toBytes(long val,int bytes){
       byte[] retval=new byte[bytes];

       while(bytes-->0){
	 retval[bytes]=(byte)(val & 0xff);
	 val>>=8;
       }

       return retval;
     }

     public static int min(int a, int b){
       return (a>b)?b:a;
     }

     public static int max(int a, int b){
       return (a>b)?a:b;
     }

}
       
