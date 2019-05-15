/***********************************************
 * File Name: OrderController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 15 05 2019 16:37
 ***********************************************/

package com.travel.controller;

import com.travel.common.vo.JsonResult;
import com.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/createOrder")
    @ResponseBody
    public JsonResult createOrder(String userId,String orderType,String productId,int amount,String guest,String tel){
        String result=orderService.createOrder(userId,orderType,productId,amount,guest,tel);
        if ("ok".equalsIgnoreCase(result)){
            return new JsonResult("预定成功");
        }else {
            return new JsonResult("201","预定失败","预定失败");
        }
    }
}
