/***********************************************
 * File Name: CommentEntity
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:11
 ***********************************************/

package com.travel.common.entity;

import java.sql.Timestamp;

public class CommentEntity {
    private int id;
    private String commentId;
    private String userId;
    private String username;
    private String sceneryId;
    private String sceneryName;
    private String cityId;
    private String cityName;
    private String commentTitle;
    private String commentContent;
    private String commentPic;
    private Timestamp createTime;
    private Timestamp modifiedTime;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentPic() {
        return commentPic;
    }

    public void setCommentPic(String commentPic) {
        this.commentPic = commentPic;
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
        sb.append(",\"commentId\":\"")
                .append(commentId).append('\"');
        sb.append(",\"sceneryId\":\"")
                .append(sceneryId).append('\"');
        sb.append(",\"sceneryName\":\"")
                .append(sceneryName).append('\"');
        sb.append(",\"cityId\":\"")
                .append(cityId).append('\"');
        sb.append(",\"cityName\":\"")
                .append(cityName).append('\"');
        sb.append(",\"commentTitle\":\"")
                .append(commentTitle).append('\"');
        sb.append(",\"commentContent\":\"")
                .append(commentContent).append('\"');
        sb.append(",\"commentPic\":\"")
                .append(commentPic).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"modifiedTime\":\"")
                .append(modifiedTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
