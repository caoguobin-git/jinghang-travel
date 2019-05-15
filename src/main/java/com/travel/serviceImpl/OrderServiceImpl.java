/***********************************************
 * File Name: OrderServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 15 05 2019 16:43
 ***********************************************/

package com.travel.serviceImpl;

import com.travel.common.util.MD5HashUtils;
import com.travel.mapper.OrderMapper;
import com.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public String createOrder(String userId, String orderType, String productId, int amount, String guest, String tel) {
        String orderId= MD5HashUtils.getRandomUUID();
        int result=orderMapper.createOrder(orderId,userId,orderType,productId,amount,guest,tel);
        if (result>0){
            return "ok";
        }else {
            return "预定失败";
        }
    }

}
