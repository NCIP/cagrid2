/**
   PKCS1PadTest.java

   Copyright (C) 2002, RTFM, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Thu Jun 20 19:59:20 2002
*/



package COM.claymoresystems.crypto;

import java.math.BigInteger;
import java.security.SecureRandom;

class PKCS1PadTest {
     static void test(BigInteger n,byte[] input,boolean expected,String name){
       byte[] result=null;
       String error="OK";
       
       try {

         result=PKCS1Pad.pkcs1UnpadBuf(input,PKCS1Pad.VERIFY,n);
       }
       catch (Exception e){
         if(expected==true) throw new InternalError("Should have succeeded");
         error=e.toString();
       }
       if(result!=null){
         if(expected==false) throw new InternalError("Should have failed");
       }

       System.out.println("Test " + name + " " + error + "(PASS)");
     }
     
     public static void main(String [] args){
       byte[] nb=new byte[128];

       for(int i=0;i<128;i++){
         nb[i]=0;
       }
       nb[0]=(byte)128;

       BigInteger n=new BigInteger(1,nb);

       byte[] tmp=new byte[127];

       tmp[0]=1;
       for(int i=1;i<127;i++){
         tmp[i]=(byte)0xff;
       }

       test(n,tmp,false,"No value");

       tmp[65]=0;

       test(n,tmp,true,"Good");

       tmp[64]=55;
       
       test(n,tmp,false,"Bad pad (not ff)");

       tmp[7]=0;
       test(n,tmp,false,"Short pad");

       tmp[0]=2;
       test(n,tmp,false,"Wrong block type pad");

       byte[] tmp2=new byte[126];
       test(n,tmp2,false,"Short block");       
     }
}
