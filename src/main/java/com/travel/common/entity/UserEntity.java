/***********************************************
 * File Name: UserEntity
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 13 05 2019 13:57
 ***********************************************/

package com.travel.common.entity;

import java.sql.Timestamp;

public class UserEntity {
    private String userId;
    private String username;
    private String password;
    private String salt;
    private Timestamp regTime;
    private boolean ifuse;
    private Timestamp loginTime;
    private String loginIp;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public boolean isIfuse() {
        return ifuse;
    }

    public void setIfuse(boolean ifuse) {
        this.ifuse = ifuse;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"username\":\"")
                .append(username).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"salt\":\"")
                .append(salt).append('\"');
        sb.append(",\"regTime\":\"")
                .append(regTime).append('\"');
        sb.append(",\"ifuse\":")
                .append(ifuse);
        sb.append(",\"loginTime\":\"")
                .append(loginTime).append('\"');
        sb.append(",\"loginIp\":\"")
                .append(loginIp).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
