/***********************************************
 * File Name: Test001
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 29 05 2019 10:56
 ***********************************************/

package com.travel.test;


public class Test001 {
    private static int count=0;
    private static void increment(){
        count++;
    }
    public static void main(String[] args) {
       for (int i=0;i<20;i++){
           new Thread(() -> {
               for (int i1=0;i1<100;i1++){
                   increment();
               }
           }).start();
       }
        System.out.println(count);
    }
}
