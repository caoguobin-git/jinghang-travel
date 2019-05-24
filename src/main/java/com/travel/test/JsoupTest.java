/***********************************************
 * File Name: JsoupTest
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 23 05 2019 13:43
 ***********************************************/

package com.travel.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class JsoupTest {
    public static void main(String[] args) throws IOException {
        Connection connect = Jsoup.connect("https://cdn-rili.jin10.com/data/2019/0524/economics.json?_="+new Date().getTime()).header("referer", "https://rili.jin10.com/?date=20190524").ignoreContentType(true);

        Connection.Response execute = connect.execute();
        String body = execute.body();
        System.out.println(body);
        ObjectMapper objectMapper=new ObjectMapper();
        LinkedList linkedList = objectMapper.readValue(body, LinkedList.class);
        for (Object o : linkedList) {
            LinkedHashMap map= (LinkedHashMap) o;
            map.forEach((k,v)->{
                System.out.print(k+"  =  ");
                System.out.println(v);
            });
        }
    }
}
