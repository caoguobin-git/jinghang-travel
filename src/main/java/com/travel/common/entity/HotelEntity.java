/***********************************************
 * File Name: HotelEntity
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:11
 ***********************************************/

package com.travel.common.entity;

import java.sql.Timestamp;

public class HotelEntity {
    private int id;
    private String hotelId;
    private String sceneryId;
    private String sceneryName;
    private String cityId;
    private String cityName;
    private String hotelName;
    private double hotelPrice;
    private String hotelPic;
    private String hotelDesc;
    private Timestamp createTime;
    private Timestamp modifiedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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

    public String getHotelPic() {
        return hotelPic;
    }

    public void setHotelPic(String hotelPic) {
        this.hotelPic = hotelPic;
    }

    public String getHotelDesc() {
        return hotelDesc;
    }

    public void setHotelDesc(String hotelDesc) {
        this.hotelDesc = hotelDesc;
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
        sb.append(",\"hotelId\":\"")
                .append(hotelId).append('\"');
        sb.append(",\"sceneryId\":\"")
                .append(sceneryId).append('\"');
        sb.append(",\"sceneryName\":\"")
                .append(sceneryName).append('\"');
        sb.append(",\"cityId\":\"")
                .append(cityId).append('\"');
        sb.append(",\"cityName\":\"")
                .append(cityName).append('\"');
        sb.append(",\"hotelName\":\"")
                .append(hotelName).append('\"');
        sb.append(",\"hotelPrice\":")
                .append(hotelPrice);
        sb.append(",\"hotelPic\":\"")
                .append(hotelPic).append('\"');
        sb.append(",\"hotelDesc\":\"")
                .append(hotelDesc).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"modifiedTime\":\"")
                .append(modifiedTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
