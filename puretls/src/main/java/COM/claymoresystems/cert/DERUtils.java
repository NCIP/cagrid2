/**
   DERUtils.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat Jul 24 21:24:06 1999

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


   $Id: DERUtils.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.cert;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Date;
import java.io.*;

/** Simple DER encode utilities. Eventually we'd like to use one
 common ASN.1 engine for everything */
public class DERUtils {
     public static final byte BOOLEAN=1;     
     public static final byte INTEGER=2;
     public static final byte BIT_STRING=3;
     public static final byte OCTET_STRING=4;          
     public static final byte OID=6;
     public static final byte SET=(byte)0x31;      // Constructed SET
     public static final byte SEQUENCE=(byte)0x30; // Constructed SEQUENCE
     public static final byte IA5STRING=(byte)0x16;
     public static final byte PRINTABLE_STRING=(byte)0x13;
     public static final byte T61STRING=(byte)(0x14);
     public static final byte UTCTIME=(byte)(0x17);

     private static void writeLength(int length, OutputStream os)
       throws IOException {
       if(length<128){
	 os.write((byte)length);
       }
       else{
	 ByteArrayOutputStream bs=new ByteArrayOutputStream();
	 int i;
	 
	 for(i=1;length>0;i++){
	   bs.write((byte)(length & 0xff));
	   length>>=8;
	 }

	 byte[] b=bs.toByteArray();
	 os.write((byte)(0x80 | b.length));

	 for(i=0;i<b.length;i++){
	   os.write(b[b.length-(i+1)]);
	 }
       }
     }

     private static void encodeBytes(byte type, byte[] in, OutputStream os)
       throws IOException{
       os.write(type);
       writeLength(in.length,os);
       os.write(in);
     }

     public static void encodeInteger(BigInteger i, OutputStream os)
       throws IOException {
       byte[] i_bytes=i.toByteArray();

       if(i_bytes[0]==0 && i_bytes.length>1 && ((i_bytes[1] & 0x80) == 0)){
	 byte[] n_bytes=new byte[i_bytes.length-1];
	 System.arraycopy(i_bytes,1,n_bytes,0,n_bytes.length);
	 i_bytes=n_bytes;
       }
       encodeBytes(INTEGER,i_bytes,os);
     }

     public static void encodeSequence(byte[] in,OutputStream os)
       throws IOException{
       encodeBytes(SEQUENCE,in,os);
     }

     public static void encodeSequence(ByteArrayOutputStream is,
       OutputStream os)
       throws IOException {
       byte[] in=is.toByteArray();
       encodeSequence(in,os);
     }

     public static void encodeSet(byte[] in,OutputStream os)
       throws IOException{
       encodeBytes(SET,in,os);
     }

     public static void encodeSet(ByteArrayOutputStream is,
       OutputStream os)
       throws IOException {
       byte[] in=is.toByteArray();
       encodeSet(in,os);
     }
     
     public static void encodeOID(byte[] in,OutputStream os)
       throws IOException {
       encodeBytes(OID,in,os);
     }

     private static void encodeBase128(int num,OutputStream os)
       throws IOException {
       byte tmp[]=new byte[6];
       int i;
       
       for(i=0;i<6;i++){
         tmp[i]=(byte)(num & 0x7f);
         num>>=7;

         if(num==0)
           break;
       }

       for(int j=i;j>=0;j--){
         if(j==0){
           os.write(tmp[j]);
         }
         else{
           os.write(tmp[j] | 0x80);
         }
       }
     }

     
     /* OID is passed in in .-ed format */
     public static void encodeOID(String oid,OutputStream os)
       throws IOException {
       ByteArrayOutputStream bos=new ByteArrayOutputStream();
       
       StringTokenizer tok=new StringTokenizer(oid,".");
       int []components=new int[tok.countTokens()];
       for(int i=0;i<components.length;i++){
         components[i]=Integer.parseInt(tok.nextToken());
       }

       bos.write(components[0]*40+components[1]); // Yuck!

       for(int i=2;i<components.length;i++){
         encodeBase128(components[i],bos);
       }

       byte[] tmp=bos.toByteArray();
       encodeOID(tmp,os);
     }
     
     public static void encodeIA5String(String in, OutputStream os)
       throws IOException{
       encodeBytes(IA5STRING,in.getBytes(),os);
     }

     public static void encodePrintableString(String in, OutputStream os)
       throws IOException{
       encodeBytes(PRINTABLE_STRING,in.getBytes(),os);
     }

     // Legal characters other than the basic letters and numbers
     static private final int legals[]={
          ' ','\'', '(', ')', '+', ',', '-', '.',
          '/', ':', '=', '?'
     };

     private static boolean isPrintableString(String str){
       byte[] in=str.getBytes();
       
       for(int i=0;i<in.length;i++){
         if(in[i] >= 48 && in[i] <= 57) continue; // Digits
         if(in[i] >= 65 && in[i] <= 90) continue; // Capitals
         if(in[i] >= 97 && in[i] <= 122) continue; // lower case

         // Check legal non-alphanums
         for(int j=0;j<legals.length;j++)
           if(in[i] == legals[j]) continue;

         return false;
       }

       return true;
     }
       
     /* Scan the string to see if it contains any impermissible
        characters and encode as required. You're taking your life
        in your hands if you pass in anything but ASCII here */
     public static void encodeUnknownString(String in,OutputStream os)
       throws IOException {
       if(isPrintableString(in)){
         encodePrintableString(in,os);
       }
       else{
         encodeIA5String(in,os);
       }
     }

     public static void encodeBitString(byte[] in,OutputStream os)
       throws IOException {
       os.write(BIT_STRING);
       writeLength(in.length+1,os);
       os.write(0);
       os.write(in);
     }

     private static void put2digits(byte[] buf,int val,int offset)
       {
         if(val>99)
           throw new InternalError("Illegal value");
         if(val<0)
           throw new InternalError("Illegal value");
           
         buf[offset++]=(byte)(((val/10) + 48) & 0xff); // Who asked Java to require the & 0xff
         buf[offset++]=(byte)(((val%10) + 48) & 0xff);
       }
            
     public static void encodeUTCTime(long time,OutputStream os)
       throws IOException {
       byte[] tbuf=new byte[13];
       int offset=0;
       
       Date d=new Date(time);
       TimeZone tz=TimeZone.getTimeZone("GMT");
       Calendar cal=new GregorianCalendar(tz);
       cal.setTime(d);

       /* Now fill in the data */
       int year=cal.get(Calendar.YEAR);
       int month=cal.get(Calendar.MONTH);
       month+=1; // compensate for zero numbering 
       int day=cal.get(Calendar.DAY_OF_MONTH);
       int hr=cal.get(Calendar.HOUR_OF_DAY);
       int min=cal.get(Calendar.MINUTE);
       int sec=cal.get(Calendar.SECOND);

       if(year<1950)
         throw new InternalError("Bad time to encode "+cal.toString());
       if(year>2049)
         throw new InternalError("Bad time to encode "+cal.toString());
       if(year<2000){
         year-=1900;
       }
       else{
         year-=2000;
       }

       put2digits(tbuf,year,offset); offset+=2;
       put2digits(tbuf,month,offset); offset+=2;
       put2digits(tbuf,day,offset); offset+=2;
       put2digits(tbuf,hr,offset); offset+=2;
       put2digits(tbuf,min,offset); offset+=2;
       put2digits(tbuf,sec,offset); offset+=2;
       tbuf[offset]=(byte)'Z';

       encodeBytes(UTCTIME,tbuf,os);
     }
       
     public static byte[] decodeSequence(InputStream is)
       throws IOException {
       return(readTLV(SEQUENCE,is));
     }

     public static byte[] decodeOID(InputStream is)
       throws IOException {
       return(readTLV(OID,is));
     }

     public static byte[] decodeOctetString(InputStream is)
       throws IOException {
       return(readTLV(OCTET_STRING,is));
     }

     public static BigInteger decodeInteger(InputStream is)
       throws IOException {
       byte[] intb=readTLV(INTEGER,is);
       return new BigInteger(1,intb);
     }

            
     public static int decodeIntegerX(InputStream is)
       throws IOException {
       // This is a bit of a hack since bigger integers would work here
       BigInteger max=BigInteger.valueOf(0xfffffff);
       BigInteger b=decodeInteger(is);

       if(b.compareTo(max)>0)
         throw new IOException("Overlarge big integer");
       if(b.compareTo(BigInteger.ZERO)<0)
         throw new IOException("Tried to decode negative number");

       return b.intValue();
     }
         
         
     public static BitSet decodeBitStringX(InputStream is)
       throws IOException {
       byte[] b=decodeBitString(is);

       int shrt=b[0];

       BitSet ret=new BitSet();
       for(int i=1;i<b.length;i++){
	 int max=(i!=(b.length-1))?8:8-shrt;
	 int msk=0x80;
	   
	 for(int j=0;j<max;j++){
           int tmp=b[i] & 0xff;
	   if((msk & tmp)==msk){
	     ret.set(((i-1)*8)+j);
	   }
	   msk>>=1;
	 }
       }

       return ret;
     }
	    
       
     public static byte[] decodeBitString(InputStream is)
       throws IOException {
       return(readTLV(BIT_STRING,is));
     }

     public static boolean decodeBoolean(InputStream is)
       throws IOException {
       byte[] v=readTLV(BOOLEAN,is);
       if(v.length!=1)
	 throw new IOException("Bad encoding for boolean");
       // Is this right?
       switch(v[0]){
         case (byte)255:
           return true;
         case 0:
           return false;
         default:
           throw new IOException("Boolean must be either 0xff or 0");
       }
     }

     public static byte[] decodeAny(InputStream is)
       throws IOException {
       ByteArrayOutputStream os=new ByteArrayOutputStream();

       int t=decodeTag(is);

       byte[] b=readLV(is);
       encodeBytes((byte)t,b,os);

       return os.toByteArray();
     }
       
     private static byte[] readTLV(int type,InputStream is)
       throws IOException {
       decodeTagOrDie(type,is);

       byte[] ret=readLV(is);

       return ret;
     }

     private static byte[] readLV(InputStream is)
       throws IOException {
       int length=decodeLength(is);

       byte[] ret=new byte[length];

       if(length!=0){
         int actualLength=is.read(ret);
         
         if(actualLength!=length)
           throw new IOException("Bad encoding: short read");
       }

       return ret;
     }

  
     private static void decodeTagOrDie(int tag,InputStream is)
       throws IOException {
       if(!decodeTag(tag,is))
	 throw new IOException("Bad encoding: wrong tag");
     }

     private static int decodeTag(InputStream is)
       throws IOException {
       int t=is.read();

       return t;
     }
     
     // This only knows small number form
     private static boolean decodeTag(int tag,InputStream is)
       throws IOException {
       int t=decodeTag(is);

       if(t<0)
	 return false;

       if(t!=tag)
	 return false;

       return true;
     }

     public static boolean isTag(int tag,InputStream is)
       throws IOException {
       is.mark(1);
       boolean result=decodeTag(tag,is);
       is.reset();

       return result;
     }

     private static int decodeLength(InputStream is)
       throws IOException {
       int length=0;

       int l=is.read();

       if(l<0)
	 throw new IOException("Bad encoding: short read");

       if((l & 0x80)==0)
	 return l;
       else{
	 int ct=l & 0x7f;

	 while(ct-->0){
	   if((l=is.read())<0)
	     throw new IOException("Bad encoding: short read");
	   length*=256;
	   length+=l;
	 }

	 return length;
       }
     }
     
}

