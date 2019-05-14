/***********************************************
 * File Name: FoodEntity
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:11
 ***********************************************/

package com.travel.common.entity;

import java.sql.Timestamp;

public class FoodEntity {
    private int id;
    private String foodId;
    private String sceneryId;
    private String sceneryName;
    private String cityId;
    private String cityName;
    private String foodName;
    private double foodPrice;
    private String foodPic;
    private String foodDesc;
    private Timestamp createTime;
    private Timestamp modifiedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
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
        sb.append(",\"foodId\":\"")
                .append(foodId).append('\"');
        sb.append(",\"sceneryId\":\"")
                .append(sceneryId).append('\"');
        sb.append(",\"sceneryName\":\"")
                .append(sceneryName).append('\"');
        sb.append(",\"cityId\":\"")
                .append(cityId).append('\"');
        sb.append(",\"cityName\":\"")
                .append(cityName).append('\"');
        sb.append(",\"foodName\":\"")
                .append(foodName).append('\"');
        sb.append(",\"foodPrice\":")
                .append(foodPrice);
        sb.append(",\"foodPic\":\"")
                .append(foodPic).append('\"');
        sb.append(",\"foodDesc\":\"")
                .append(foodDesc).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"modifiedTime\":\"")
                .append(modifiedTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
