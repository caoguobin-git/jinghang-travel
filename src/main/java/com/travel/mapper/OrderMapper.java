/***********************************************
 * File Name: OrderMapper
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 15 05 2019 16:44
 ***********************************************/

package com.travel.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OrderMapper {
    int createOrder(@Param("orderId")String orderId,@Param("userId") String userId, @Param("orderType") String orderType, @Param("productId") String productId, @Param("orderAmount") int amount,@Param("guest") String guest,@Param("tel") String tel);

    int getPageCount(@Param("orderType") String orderType);

    List<Object> doFindFoodOrderPageObjects(Integer pageCurrent,@Param("orderType") String food, RowBounds rowBounds);

    int doDeleteObject(@Param("orderId") String orderId);

    List<Object> doFindHotelOrderPageObjects(Integer pageCurrent,@Param("orderType") String hotel, RowBounds rowBounds);

    List<Object> doFindTicketOrderPageObjects(Integer pageCurrent,@Param("orderType") String ticketType, RowBounds rowBounds);
}
