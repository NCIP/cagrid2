/**
   X509Name.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue May 25 12:49:19 1999

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

   $Id: X509Name.java,v 1.2 2005/01/20 03:58:28 gawor Exp $

*/

package COM.claymoresystems.cert;

import COM.claymoresystems.sslg.DistinguishedName;

import cryptix.asn1.lang.ASNObject;
import cryptix.asn1.lang.ASNSpecification;
import cryptix.asn1.lang.Parser;
import cryptix.asn1.lang.ParseException;
import cryptix.asn1.lang.ASNBitString;
import cryptix.asn1.encoding.BaseCoder;
import cryptix.asn1.encoding.CoderOperations;
import cryptix.asn1.encoding.DER;
// import cryptix.asn1.encoding.SubVector;
import java.util.Vector;
import java.io.*;


/** An implementation of Distinguished Names
    <P>
    A DN is a SEQUENCE of SETs of "attribute value assertions" (AVAs)
    <P>
    Each AVA consists of a type and a value.
*/
public class X509Name implements DistinguishedName {
      private static String[][] OIDMAP={
 	  {"2.5.4.6","C"},
 	  {"2.5.4.8","ST"},
	  {"2.5.4.7","L"},
	  {"2.5.4.9","STREET"},
 	  {"2.5.4.10","O"},
 	  {"2.5.4.11","OU"},	  
 	  {"2.5.4.3","CN"},
	  {"1.2.840.113549.1.9.1","EmailAddress"},
	  {"2.5.4.5","SN"},
	  {"0.9.2342.19200300.100.1.25","DC"}
     };

     private byte[] nameDER=null;
     private Vector name=null;
     private String nameString=null;

     /** Create a name given the DER

	 @param nameDER the encoded form of the name
     */
     public X509Name(byte[] nameDER){
       this.nameDER=nameDER;
       name=rawNameToName(nameDER);
     }

     /* Create a name given the expanded form

        @param name the expanded form
     */
     public X509Name(Vector dn){
       Vector ndn=new Vector();

       for(int i=0;i<dn.size();i++){
         Vector nrdn=new Vector();

         Vector rdn=(Vector)dn.elementAt(i);

         for(int j=0;j<rdn.size();j++){
           String []nava=new String[2];
           String []ava=(String[])rdn.elementAt(j);

           nava[0]=new String(ava[0]);
           nava[1]=new String(ava[1]);

           nrdn.addElement(nava);
         }
         ndn.addElement(nrdn);
       }

       name=ndn;
     }
     
     /** Get the DER form of the name

	 @return the encoded form
     */
     public byte[] getNameDER(){

       try {
         if(nameDER==null){
           ByteArrayOutputStream bos=new ByteArrayOutputStream();

           for(int i=0;i<name.size();i++){
             Vector rdn=(Vector)name.elementAt(i);

             encodeRDN(rdn,bos);
           }

           byte[] tmp=bos.toByteArray();

           bos=new ByteArrayOutputStream();
           DERUtils.encodeSequence(tmp,bos);

           nameDER=bos.toByteArray();
         }
         return nameDER;
       } catch (IOException e){
         throw new InternalError("Problem encoding: "+e.toString());
       }
     }

     /* Encode an RDN */
     private void encodeRDN(Vector rdn,OutputStream out)
       throws IOException {
       ByteArrayOutputStream bos=new ByteArrayOutputStream();

       for(int i=0;i<rdn.size();i++){
         ByteArrayOutputStream bos2=new ByteArrayOutputStream();

         String[] ava=(String[])rdn.elementAt(i);
         
         DERUtils.encodeOID(lookupComponent(ava[0]),bos2);
         DERUtils.encodeUnknownString(ava[1],bos2);

         DERUtils.encodeSequence(bos2,bos);
       }

       DERUtils.encodeSet(bos,out);
     }
     
     /** Get the name in more or less unformatted form.
	 <P>
	 The outer SEQUENCE is represented by a Vector, each
	 element of which (a SET) is also a Vector. Each element
	 of the inner Vector (the AVA) is a String[2] with the first
	 element being the attribute and the second being the
	 value
	 <P>
	 @return the name in a Vector form
     */
     public Vector getName(){
       return name;
     }

     /** Get the name in a string form

	 @return a string formatted version of the name
     */
     public String getNameString(){
       StringBuffer str=new StringBuffer();

       if(nameString==null){
	 for(int i=0;i<name.size();i++){
	   Vector rdn=(Vector)name.elementAt(i);

	   if(i>0) str.append(",");
	   
	   for(int j=0;j<rdn.size();j++){
	     String[] ava=(String[])rdn.elementAt(j);

	     if(j>0) str.append("+");
	     str.append(ava[0]+"="+ava[1]);
	   }
	 }

	 nameString=str.toString();
       }

       return nameString;
       
     }

     private static String lookupOID(String oid){
       for(int i=0;i<OIDMAP.length;i++){
	 if(oid.equals(OIDMAP[i][0])){
	   return(OIDMAP[i][1]);
	 }
       }

       return "Unknown Attribute("+oid+")";
     }

     private static String lookupComponent(String component){
       for(int i=0;i<OIDMAP.length;i++){
	 if(component.equals(OIDMAP[i][1])){
	   return(OIDMAP[i][0]);
	 }
       }

       throw new InternalError("Unknown component type "+component);
     }
     
     private static Vector rawNameToName(byte[] rawName){
       ASNObject name;
       ASNObject tmp;
       Vector nRDNSequence=new Vector();
       
       synchronized(CertContext.getSpec()){
         name=CertContext.getSpec().getComponent("Name");
         CoderOperations der_coder=BaseCoder.getInstance("DER");
         InputStream is=new ByteArrayInputStream(rawName);

         der_coder.init(is);

         try {
           name.accept(der_coder,null);

           Vector rdnSequence=(Vector)name.getValue();
           vecDebug(rdnSequence);
           
           for(int i=0;i<rdnSequence.size();i++){
             Vector xxx=(Vector)rdnSequence.elementAt(i);
             vecDebug(xxx);
             Vector rDN=(Vector)xxx.elementAt(0);
             vecDebug(rDN);
             Vector nRDN=new Vector();
//             System.out.println("NRDN");
             for(int j=0;j<rDN.size();j++){
               Vector x=(Vector)rDN.elementAt(j);
               vecDebug(x);
               Vector y=(Vector)x.elementAt(0);
               vecDebug(y);               
               Vector w=(Vector)y.elementAt(0);
               vecDebug(w);               
               
               String type=(String)w.elementAt(0);
               String value=(String)w.elementAt(1);	     	     
//	     System.out.println(lookupOID(type)+"="+value);

               String[] aVA=new String[2];
               aVA[0]=lookupOID(type);
               aVA[1]=value;
               nRDN.addElement((Object)aVA);
               //             System.out.println("AVA"+aVA[0]+aVA[1]);
	     }
             nRDNSequence.addElement((Object)nRDN);
           }

         } catch (java.io.IOException e){
           e.printStackTrace(System.out);
           throw new InternalError(e.toString());
         }
       }	 
       return nRDNSequence;
     }


     /*
       static private void vecDebug(Vector x){
       if(x instanceof SubVector){
         SubVector s=(SubVector)x;
         System.out.println("SubVector "+s.name+" size "+s.size());
       }
       else {
         System.out.println("Vector");
       }
       }*/
     static private void vecDebug(Vector x){
       ;
     }
     
}
