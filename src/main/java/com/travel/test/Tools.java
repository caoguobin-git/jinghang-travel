package com.travel.test;


public class Tools {

    String str = new String("good");
    char[] ch = { 'a', 'b', 'c' };
    public static void main(String args[]) {
        Tools ex = new Tools();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
    }
    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'g';
    }
}
