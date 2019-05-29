package com.travel.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Course {
    public static void main(String[] args) {
        Map<String,String> map = initChooseCourse();//学生选课的初始数据
        List<String> list = initAllCourse();//所有课程
        int n = list.size()-2*map.size();//剩余课程个数
        Map<String,String> rmap = new HashMap<>();//最后选课结果

        Map<String,String> nmap = reverseMap(map,list); //key-value颠倒，重复的key拼接到新value里

        List<String> clist = noSelectCourse(list,n);//剩余课程

        /*课程- 学生 集合 转换成 学生-课程集合，并随机指定一位学生选择多个学生选择的课程，
         * 其余学生去掉此课程，没有课程的学生制空*/
        distinctMap(nmap,rmap);

        buqiCourse(rmap,list);	//每个学生补齐至2门课

        System.out.println("\t\t\t学员的技术学习清单:");
        print(map,rmap);

        System.out.println("\n\n未被指定给学员的技术清单:");
        System.out.println("\t"+clist);
    }
    //每个学生补齐至2门课
    public static void buqiCourse(Map<String,String> rmap,List<String>list){
        Set<String> set = rmap.keySet();
        for (String s : set) {
            if(rmap.get(s)==null) {
                rmap.put(s, list.get(0)+" "+list.get(1));
                list.remove(0);
                list.remove(0);
            }else if(rmap.get(s)!=null&&!rmap.get(s).contains(" ")) {
                rmap.put(s, rmap.get(s)+" "+list.get(0));
                list.remove(0);
            }
        }
    }
    /*课程- 学生 集合 转换成 学生-课程集合，并随机指定一位学生选择多个学生选择的课程，
     * 其余学生去掉此课程，没有课程的学生制空*/
    public static void distinctMap(Map<String,String> nmap,Map<String,String> rmap){
        Set<String> nset = nmap.keySet();
        Random r = new Random();
        for (String ns : nset) {
            String[] str = nmap.get(ns).split(",");
            int temp = r.nextInt(str.length);
            for (int i = 0;i < str.length; i++) {
                if(i!=temp&&!rmap.containsKey(str[i]))
                    rmap.put(str[i], null);
                else if(i==temp) {
                    if(rmap.containsKey(str[i])) {
                        if(rmap.get(str[i])!=null)
                            rmap.put(str[i], rmap.get(str[i])+" "+ns);
                        else
                            rmap.put(str[i], ns);
                    }else {
                        rmap.put(str[i], ns);
                    }
                }
            }
        }
    }
    //学生-课程 集合 转换成 课程- 学生集合
    public static Map<String,String> reverseMap(Map<String,String> map,List<String> list){
        Map<String,String> nmap = new HashMap<>();
        Set<String> set = map.keySet();
        for (String s : set) {
            String[] str = map.get(s).split(" ");
            for (String ss : str) {
                if(nmap.containsKey(ss)) {
                    nmap.put(ss, nmap.get(ss)+","+s);
                }else {
                    nmap.put(ss, s);
                }
                list.remove(ss);
            }
        }
        return nmap;
    }
    //打印map集合
    public static void print(Map<String,String> map,Map<String,String> rmap) {
        Set<String> set = map.keySet();
        System.out.println("开始\t\t\t\t最终");
        for (String s : set) {
            System.out.println(s+" "+map.get(s)+"\t\t\t"+s+" "+rmap.get(s));
        }
    }
    //为所有课程不够的学生指定的课程
    public static List<String> noSelectCourse(List<String> list,int n){
        List<String> l = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int m = r.nextInt(list.size());
            l.add(list.get(m));
            list.remove(m);
        }
        return l;
    }
    //初始选课
    public static Map<String,String> initChooseCourse(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("吕鹏飞","ElasticSearch Redis");
        map.put("丁虎","Redis SpringBoot");
        map.put("梁秀斗","Hadoop HDFS");
        map.put("李文鹏","Docker Kafka ");
        map.put("苗桓飞","Lucene Solr");
        map.put("佘昊","Solr Redis");
        map.put("杜世阳","ActiveMQ Hadoop");
        map.put("刘翩","SpringBoot ActiveMQ");
        map.put("史建智","Docker Lucene");
        map.put("王帅","Cassandra Spark");
        map.put("张昌昌","SpringBoot MongoDB");
        map.put("王腾飞","SpringBoot Spark");
        map.put("杨小平","WebSocket RabbitMQ");
        return map;
    }
    //所有课程
    public static List<String> initAllCourse(){
        List<String> list = new ArrayList<>();
        list.add("VirtualBox");
        list.add("Vagrant");
        list.add("WebSocket");
        list.add("JSONP");
        list.add("Redis");
        list.add("MongoDB");
        list.add("Cassandra");
        list.add("RabbitMQ");
        list.add("ActiveMQ");
        list.add("Kafka");
        list.add("Lucene");
        list.add("Solr");
        list.add("ElasticSearch");
        list.add("Hadoop");
        list.add("HDFS");
        list.add("PIG");
        list.add("HIVE");
        list.add("Mahout");
        list.add("HBase");
        list.add("Spark");
        list.add("Guava");
        list.add("Protobuf");
        list.add("Avro");
        list.add("Thrift");
        list.add("Motan");
        list.add("Docker");
        list.add("DynamoDB");
        list.add("Scala");
        list.add("Groovy");
        list.add("SpringBoot");
        return list;
    }
}