/***********************************************
 * File Name: OrderController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 15 05 2019 16:37
 ***********************************************/

package com.travel.controller;

import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
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


    @RequestMapping("/doFoodOrderListUI")
    public String doFoodOrderListUI(){
        return "sys/food_order_list";
    }

    @RequestMapping("/doHotelOrderListUI")
    public String doHotelOrderListUI(){
        return "sys/hotel_order_list";
    }

    @RequestMapping("/doAdmissionOrderListUI")
    public String doAdmissionOrderListUI(){
        return "sys/admission_order_list";
    }

    @RequestMapping("/doSteamerOrderListUI")
    public String doSteamerOrderListUI(){
        return "sys/steamer_order_list";
    }

    @RequestMapping("/doFindHotelOrderPageObjects")
    @ResponseBody
    public JsonResult doFindHotelOrderPageObjects(Integer pageCurrent, Integer pageSize) {
        if (pageCurrent == null) {
            pageCurrent = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageObject pageObject = orderService.doFindHotelOrderPageObjects(pageCurrent, pageSize);
        return new JsonResult(pageObject);
    }

    @RequestMapping("/doFindTicketOrderPageObjects")
    @ResponseBody
    public JsonResult doFindTicketOrderPageObjects(Integer pageCurrent, Integer pageSize,String ticketType) {
        if (pageCurrent == null) {
            pageCurrent = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageObject pageObject = orderService.doFindTicketOrderPageObjects(pageCurrent, pageSize,ticketType);
        return new JsonResult(pageObject);
    }

    @RequestMapping("/doFindFoodOrderPageObjects")
    @ResponseBody
    public JsonResult doFindFoodOrderPageObjects(Integer pageCurrent, Integer pageSize) {
        if (pageCurrent == null) {
            pageCurrent = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageObject pageObject = orderService.doFindFoodOrderPageObjects(pageCurrent, pageSize);
        return new JsonResult(pageObject);
    }


    @RequestMapping("doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(String orderId){
        String result= orderService.doDeleteObject(orderId);
        if ("ok".equalsIgnoreCase(result)){
            return new JsonResult("订单删除成功！");
        }else {
            return new JsonResult("402","订单删除失败",result);
        }
    }
}
