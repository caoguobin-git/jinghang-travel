/***********************************************
 * File Name: RedisProducer
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 27 05 2019 12:11
 ***********************************************/

package com.travel.test;

import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Scanner;

public class RedisProducer {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("118.190.156.52", 6379);
        jedis.auth("RedisDuochuangSMS");
        // 向“channel1”的频道发送消息, 返回订阅者的数量
        while (true){
            String a = new Scanner(System.in).nextLine();
            if (a.equals("1")){
                jedis.publish("channel", "hello");
            }else {
                long x = jedis.publish("channel", a + "  " + new Date().toLocaleString());
                System.out.println("一共有" + x + "个订阅者");
            }
        }

    }
}
