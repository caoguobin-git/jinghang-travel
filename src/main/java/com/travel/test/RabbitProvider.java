/***********************************************
 * File Name: RabbitProvider
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 12 03 2019 17:56
 ***********************************************/

package com.travel.test;



import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


import java.io.*;
import java.util.Timer;
import java.util.TimerTask;


public class RabbitProvider {
    private final static String QUEUE_NAME = "hello";


    public static void main(String[] argv) throws IOException {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        // 设置MabbitMQ所在主机ip或者主机名
        factory.setHost("www.fxyilan.cn");
        factory.setPort(5672);
        factory.setUsername("duochuang");
        factory.setPassword("duochuangRabbit");
        factory.setVirtualHost("vhost_duochuang");

        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个频道
        Channel channel = connection.createChannel();

        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 发送的消息
        String message = "hello world!";
        // 往队列中发出一条消息
        new Timer().scheduleAtFixedRate(new TimerTask() {
            long a=0;

            @Override
            public void run() {
                try {
                    channel.basicPublish("", QUEUE_NAME, null, (message+"   "+a).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(" [x] Sent '" + message + "'   "+a);
                a++;
            }
        }, 0, 1);


        // 关闭频道和连接
//        channel.close();
//        connection.close();
    }
}
