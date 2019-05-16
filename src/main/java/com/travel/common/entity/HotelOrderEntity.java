/***********************************************
 * File Name: FoodOrderEntity
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 16 05 2019 15:38
 ***********************************************/

package com.travel.common.entity;

import java.sql.Timestamp;

public class HotelOrderEntity {
    private String orderId;
    private String userId;
    private String username;
    private String hotelName;
    private double hotelPrice;
    private int orderAmount;
    private String orderGuest;
    private String orderTel;
    private Timestamp createTime;
    private double priceTotal;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderGuest() {
        return orderGuest;
    }

    public void setOrderGuest(String orderGuest) {
        this.orderGuest = orderGuest;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(double hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public double getPriceTotal() {
        return this.hotelPrice*this.orderAmount;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }
}
