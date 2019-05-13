package com.travel;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class Test111 {
    public static void main(String[] args) throws IOException {
        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH)+1);
        System.out.println(calendar.get(Calendar.DATE));
    }
}
