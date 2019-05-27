/***********************************************
 * File Name: MessageHandler
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 27 05 2019 12:13
 ***********************************************/

package com.travel.test;

import redis.clients.jedis.JedisPubSub;

public class MessageHandler extends JedisPubSub {
    private String userToken;

    public MessageHandler(String userToken) {
        this.userToken = userToken;
    }

    /*
     * channel频道接收到新消息后，执行的逻辑
     */
    @Override
    public void onMessage(String channel, String message) {
        // 执行逻辑
        System.out.println(channel + "频道发来消息：" + message);
        // 如果消息为 close channel， 则取消此频道的订阅
        if((userToken).equals(message)){
            this.unsubscribe(channel);
        }
    }

    /*
     * channel频道有新的订阅者时执行的逻辑
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(channel + "频道新增了"+ subscribedChannels +"个订阅者");
    }

    /*
     * channel频道有订阅者退订时执行的逻辑
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(channel + "频道退订成功");
    }
}
