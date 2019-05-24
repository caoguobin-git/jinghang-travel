/***********************************************
 * File Name: RabbitConsummer
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 03 2019 15:36
 ***********************************************/

package com.travel.test;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RabbitConsummer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws java.io.IOException,
            ShutdownSignalException, ConsumerCancelledException,
            InterruptedException {

         //打开连接和创建频道，与发送端一样
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("www.fxyilan.cn");
        factory.setPort(5672);
        factory.setUsername("duochuang");
        factory.setPassword("duochuangRabbit");
        factory.setVirtualHost("vhost_duochuang");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 指定消费队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true) {
            // nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'"+new Date().toLocaleString());
        }
    }
}
