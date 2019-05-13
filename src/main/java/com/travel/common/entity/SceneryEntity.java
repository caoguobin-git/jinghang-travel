package com.travel.common.entity;

import java.sql.Timestamp;

public class SceneryEntity {
    private int id;
    private String sceneryId;
    private int cityId;
    private String cityName;
    private String sceneryPic;
    private String sceneryName;
    private String sceneryDesc;
    private Timestamp createTime;
    private Timestamp modifiedTime;


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(String sceneryId) {
        this.sceneryId = sceneryId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSceneryPic() {
        return sceneryPic;
    }

    public void setSceneryPic(String sceneryPic) {
        this.sceneryPic = sceneryPic;
    }

    public String getSceneryName() {
        return sceneryName;
    }

    public void setSceneryName(String sceneryName) {
        this.sceneryName = sceneryName;
    }

    public String getSceneryDesc() {
        return sceneryDesc;
    }

    public void setSceneryDesc(String sceneryDesc) {
        this.sceneryDesc = sceneryDesc;
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
        return "SceneryEntity{" +
                "id=" + id +
                ", sceneryId='" + sceneryId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", sceneryPic='" + sceneryPic + '\'' +
                ", sceneryName='" + sceneryName + '\'' +
                ", sceneryDesc='" + sceneryDesc + '\'' +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
