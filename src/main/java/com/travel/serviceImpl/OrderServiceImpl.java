/***********************************************
 * File Name: OrderServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 15 05 2019 16:43
 ***********************************************/

package com.travel.serviceImpl;

import com.travel.common.util.MD5HashUtils;
import com.travel.common.vo.PageObject;
import com.travel.mapper.OrderMapper;
import com.travel.service.OrderService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageObject doFindFoodOrderPageObjects(Integer pageCurrent, Integer pageSize) {
        if (pageSize == null) {
            pageSize = 20;
        }
        int pageCount = getPageCount(pageSize,"food");
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(orderMapper.getPageCount("food"));
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = orderMapper.doFindFoodOrderPageObjects(pageCurrent,"food", new RowBounds(pageCurrent - 1, pageSize));

        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public String doDeleteObject(String orderId) {
        int a = orderMapper.doDeleteObject(orderId);
        if (a>0){
            return "ok";
        }
        return "订单删除失败";
    }

    @Override
    public PageObject doFindHotelOrderPageObjects(Integer pageCurrent, Integer pageSize) {
        if (pageSize == null) {
            pageSize = 20;
        }
        int pageCount = getPageCount(pageSize,"food");
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(orderMapper.getPageCount("hotel"));
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = orderMapper.doFindHotelOrderPageObjects(pageCurrent,"hotel", new RowBounds(pageCurrent - 1, pageSize));

        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public PageObject doFindTicketOrderPageObjects(Integer pageCurrent, Integer pageSize, String ticketType) {
        if (pageSize == null) {
            pageSize = 20;
        }
        int pageCount = getPageCount(pageSize,ticketType);
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(orderMapper.getPageCount("hotel"));
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = orderMapper.doFindTicketOrderPageObjects(pageCurrent,ticketType, new RowBounds(pageCurrent - 1, pageSize));

        pageObject.setRecords(list);
        return pageObject;
    }

    private int getPageCount(Integer pageSize,String orderType) {
            return orderMapper.getPageCount(orderType) / pageSize + 1;
    }


}
