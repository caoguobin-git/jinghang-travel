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
        //读取tech文件
        //初始化tech Map
        File file = new File("D:\\jinghang-travel\\src\\main\\java\\com\\travel\\test\\teck.txt");
        InputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String a = null;
        while ((a = bufferedReader.readLine()) != null) {
            tech.put(a, new LinkedList<>());
        }
        //读取user文件
        //初始化user Map和result List
        File file1 = new File("D:\\jinghang-travel\\src\\main\\java\\com\\travel\\test\\target.txt");
        inputStream = new FileInputStream(file1);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((a = bufferedReader.readLine()) != null) {
            String[] s = a.split(" ");
            List<String> list = new LinkedList<>();
            list.add(s[1]);
            list.add(s[2]);
            user.put(s[0], list);
            result.put(s[0], new LinkedList<>());
        }
        //遍历user Map，初始化每个技术的选择人员
        user.forEach((k, v) -> {
            for (String s : v) {
                tech.get(s).add(k);
            }
        });
        //遍历tech Map，分为0，1，其他等情况，分别进行处理
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
        //遍历结果 Map，为不足两个技术的user补足两个技术
        result.forEach((k, v) -> {
            for (int j=0;j<2-v.size();){
                    int i = new Random().nextInt(lastTech.size());
                    String remove = lastTech.remove(i);
                    v.add(remove);
            }
        });
        //打印结果
        System.out.println("未被选中:");
        lastTech.forEach(item ->
            System.out.print(item + "  ")
        );
        System.out.println();
        System.out.println("结果：");
        result.forEach((k, v) -> {
            System.out.print(k + " : ");
            System.out.println(v.get(0) + "\t" + v.get(1));
        });
    }
}
