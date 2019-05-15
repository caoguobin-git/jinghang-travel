/***********************************************
 * File Name: OrderMapper
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 15 05 2019 16:44
 ***********************************************/

package com.travel.mapper;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int createOrder(@Param("orderId")String orderId,@Param("userId") String userId, @Param("orderType") String orderType, @Param("productId") String productId, @Param("orderAmount") int amount,@Param("guest") String guest,@Param("tel") String tel);
}
