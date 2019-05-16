/***********************************************
 * File Name: FoodOrderEntity
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 16 05 2019 15:38
 ***********************************************/

package com.travel.common.entity;

import java.sql.Timestamp;

public class TicketOrderEntity {
    private String orderId;
    private String userId;
    private String sceneryName;
    private String username;
    private String ticketName;
    private String ticketType;
    private double ticketPrice;
    private int orderAmount;
    private String orderGuest;
    private String orderTel;
    private Timestamp createTime;
    private double priceTotal;

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

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

    public String getTicketName() {
        return ticketName;
    }

    public String getSceneryName() {
        return sceneryName;
    }

    public void setSceneryName(String sceneryName) {
        this.sceneryName = sceneryName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
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

    public double getPriceTotal() {
        return this.ticketPrice*this.orderAmount;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }
}
