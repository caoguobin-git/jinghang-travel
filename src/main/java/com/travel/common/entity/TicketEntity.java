/***********************************************
 * File Name: TicketEntity
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:11
 ***********************************************/

package com.travel.common.entity;

import java.sql.Timestamp;

public class TicketEntity {
    private int id;
    private String ticketId;
    private String sceneryId;
    private String sceneryName;
    private String cityId;
    private String cityName;
    private String ticketName;
    private String ticketType;
    private double ticketPrice;
    private String ticketPic;
    private String ticketDesc;
    private String ticketTel;
    private Timestamp createTime;
    private Timestamp modifiedTime;


    public String getTicketTel() {
        return ticketTel;
    }

    public void setTicketTel(String ticketTel) {
        this.ticketTel = ticketTel;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(String sceneryId) {
        this.sceneryId = sceneryId;
    }

    public String getSceneryName() {
        return sceneryName;
    }

    public void setSceneryName(String sceneryName) {
        this.sceneryName = sceneryName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTicketName() {
        return ticketName;
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

    public String getTicketPic() {
        return ticketPic;
    }

    public void setTicketPic(String ticketPic) {
        this.ticketPic = ticketPic;
    }

    public String getTicketDesc() {
        return ticketDesc;
    }

    public void setTicketDesc(String ticketDesc) {
        this.ticketDesc = ticketDesc;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"ticketId\":\"")
                .append(ticketId).append('\"');
        sb.append(",\"sceneryId\":\"")
                .append(sceneryId).append('\"');
        sb.append(",\"sceneryName\":\"")
                .append(sceneryName).append('\"');
        sb.append(",\"cityId\":\"")
                .append(cityId).append('\"');
        sb.append(",\"cityName\":\"")
                .append(cityName).append('\"');
        sb.append(",\"ticketName\":\"")
                .append(ticketName).append('\"');
        sb.append(",\"ticketPrice\":")
                .append(ticketPrice);
        sb.append(",\"ticketPic\":\"")
                .append(ticketPic).append('\"');
        sb.append(",\"ticketDesc\":\"")
                .append(ticketDesc).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"modifiedTime\":\"")
                .append(modifiedTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
