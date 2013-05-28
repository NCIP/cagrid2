/**
   Bench.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Mon Sep 13 10:06:37 1999
*/


package COM.claymoresystems.util;


public class Bench {
     private static int size=10;
     private static int cur=0;
     protected static long accum[];
     protected static long mark[];
     protected static String names[];
     
     static {
       accum=new long[size];
       mark=new long[size];
       names=new String[size];
     }

     public static int register(String name){
       names[cur]=name;

       return cur++;
     }

     public static void clear(){
       for(int i=0;i<size;i++)
         accum[i]=0;
     }
         
     public static void clear(int acc){
       accum[acc]=0;
     }

     public static void start(int acc){
       mark[acc]=System.currentTimeMillis();
//       System.out.println("mark "+mark[acc]);
     }

     public static void end(int acc){
       long now=System.currentTimeMillis();
       long diff=now-mark[acc];
//       System.out.println("end "+diff);
       accum[acc]+=(now)-mark[acc];
     }

     public static long get(int acc){
       return accum[acc];
     }

     public static void dump(){
       dump(true);
     }
     
     public static void dump(boolean filter){
       System.out.println("Timing");
       for(int i=0;i<size;i++){
	 if(!filter || (names[i]!=null))
	   System.out.println(names[i]+"("+i+"): "+accum[i]);
       }
     }
}


