package com.travel.common.entity;

import java.sql.Timestamp;

public class StrategyEntity {
    private int id;
    private String strategyId;
    private int cityId;
    private String cityName;
    private String sceneryId;
    private String sceneryName;
    private String strategyTitle;
    private String strategyPic;
    private String strategyContent;
    private Timestamp createTime;
    private Timestamp modifiedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getStrategyTitle() {
        return strategyTitle;
    }

    public void setStrategyTitle(String strategyTitle) {
        this.strategyTitle = strategyTitle;
    }

    public String getStrategyPic() {
        return strategyPic;
    }

    public void setStrategyPic(String strategyPic) {
        this.strategyPic = strategyPic;
    }

    public String getStrategyContent() {
        return strategyContent;
    }

    public void setStrategyContent(String strategyContent) {
        this.strategyContent = strategyContent;
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
        sb.append(",\"strategyId\":\"")
                .append(strategyId).append('\"');
        sb.append(",\"cityId\":")
                .append(cityId);
        sb.append(",\"cityName\":\"")
                .append(cityName).append('\"');
        sb.append(",\"sceneryId\":\"")
                .append(sceneryId).append('\"');
        sb.append(",\"sceneryName\":\"")
                .append(sceneryName).append('\"');
        sb.append(",\"strategyTitle\":\"")
                .append(strategyTitle).append('\"');
        sb.append(",\"strategyPic\":\"")
                .append(strategyPic).append('\"');
        sb.append(",\"strategyContent\":\"")
                .append(strategyContent).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"modifiedTime\":\"")
                .append(modifiedTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
