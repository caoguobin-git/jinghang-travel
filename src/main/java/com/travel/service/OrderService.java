/***********************************************
 * File Name: OrderService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 15 05 2019 16:43
 ***********************************************/
package com.travel.service;

public interface OrderService {
    String createOrder(String userId, String orderType, String productId, int amount, String guest, String tel);
}
