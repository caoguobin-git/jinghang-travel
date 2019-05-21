package com.travel.test;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Teck {
    public static void main(String[] args) throws IOException {
        Map<String, List<String>> user = new ConcurrentHashMap<>();
        Map<String, List<String>> tech = new ConcurrentHashMap<>();
        Map<String, List<String>> result = new ConcurrentHashMap<>();
        List<String> lastTech = new LinkedList<>();
        File file = new File("D:\\jinghang-travel\\src\\main\\java\\com\\travel\\test\\teck.txt");
        InputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String a = null;
        while ((a = bufferedReader.readLine()) != null) {
            tech.put(a, new LinkedList<>());
        }
        File file1 = new File("D:\\jinghang-travel\\src\\main\\java\\com\\travel\\test\\target.txt");
        inputStream = new FileInputStream(file1);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        //读取
        while ((a = bufferedReader.readLine()) != null) {
            String[] s = a.split(" ");
            List<String> list = new LinkedList<>();
            list.add(s[1]);
            list.add(s[2]);
            user.put(s[0], list);
            result.put(s[0], new LinkedList<>());
        }
        user.forEach((k, v) -> {
            for (String s : v) {
                tech.get(s).add(k);
            }
        });

        tech.forEach((k, v) -> {
            switch (v.size()) {
                case 0:
                    lastTech.add(k);
                    break;
                case 1:
                    result.get(v.get(0)).add(k);
                    tech.remove(k);
                    break;
                default:
                    result.get((v.get(new Random().nextInt(v.size())))).add(k);
                    tech.remove(k);
                    break;
            }
        });
        result.forEach((k, v) -> {
            for (int j=0;j<2-v.size();j++){
                for (int index = 0; index < j; index++) {
                    int i = new Random().nextInt(lastTech.size());
                    String remove = lastTech.remove(i);
                    v.add(remove);
                }
            }
//            if (v.size() == 1) {
//                int i = new Random().nextInt(lastTech.size());
//                String remove = lastTech.remove(i);
//                v.add(remove);
//            } else if (v.size() == 0) {
//
//            }
        });
        System.out.println("未被选中:");
        lastTech.forEach(item -> {
            System.out.print(item + "  ");
        });
        System.out.println();
        System.out.println("结果：");
        result.forEach((k, v) -> {
            System.out.println(k + " :");
            System.out.println(v.get(0) + "\t" + v.get(1));
        });
    }
}
