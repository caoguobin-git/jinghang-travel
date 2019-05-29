/***********************************************
 * File Name: Kaoshi
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 29 05 2019 13:25
 ***********************************************/

package com.travel.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Kaoshi {
    public static void main(String[] args) throws IOException {
        StringBuffer timu = new StringBuffer();

        File file=new File("D:\\jinghang-travel\\src\\main\\java\\com\\travel\\test\\5.txt");
        InputStream inputStream=new FileInputStream(file);
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String x =null;
        while ((x=bufferedReader.readLine())!=null){
            timu.append(x);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        LinkedHashMap map = objectMapper.readValue(timu.toString(), LinkedHashMap.class);
        Object d = map.get("d");
        ArrayList arrayList = (ArrayList) d;
        ArrayList timus = (ArrayList) arrayList.get(0);
        int i = 1;
        for (Object o : timus) {
            LinkedHashMap linkedHashMap = (LinkedHashMap) o;
            ArrayList answers = (ArrayList) linkedHashMap.get("Answer");
            System.out.println(i + ". 【" + linkedHashMap.get("QuestionType") + "】" + linkedHashMap.get("Topic"));
            int a = 65;
            LinkedList<Character> results = new LinkedList<>();
            String rightA = (String) linkedHashMap.get("AnswersIds");
            for (Object answer : answers) {
                LinkedHashMap answerMap = (LinkedHashMap) answer;
                if (rightA.contains(answerMap.get("AnswerID").toString())) {
                    results.add((char) a);
                }
                System.out.println("   " + (char) a + ". " + answerMap.get("AnswerContent"));
                a++;
            }
            System.out.println("  答案：" + results);
            System.out.println("  难易："+linkedHashMap.get("QuestionLevel"));
            System.out.println("  分析：" + linkedHashMap.get("AnalyticDesc"));
            System.out.println();
            i++;
        }
    }
}
