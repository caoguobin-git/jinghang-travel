/***********************************************
 * File Name: RedisConsumer
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 27 05 2019 12:13
 ***********************************************/

package com.travel.test;

import redis.clients.jedis.Jedis;

public class RedisConsumer {
    public static void main(String[] args) {
        new Thread(() -> {
            Jedis jedis = new Jedis("118.190.156.52", 6379);
            jedis.auth("RedisDuochuangSMS");
            MessageHandler handler = new MessageHandler("1234");
            jedis.subscribe(handler, "channel");
        }).start();
    }
}
