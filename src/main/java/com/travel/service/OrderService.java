/***********************************************
 * File Name: OrderService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 15 05 2019 16:43
 ***********************************************/
package com.travel.service;

import com.travel.common.vo.PageObject;

public interface OrderService {
    String createOrder(String userId, String orderType, String productId, int amount, String guest, String tel);

    PageObject doFindFoodOrderPageObjects(Integer pageCurrent, Integer pageSize);

    String doDeleteObject(String orderId);

    PageObject doFindHotelOrderPageObjects(Integer pageCurrent, Integer pageSize);

    PageObject doFindTicketOrderPageObjects(Integer pageCurrent, Integer pageSize, String ticketType);
}
