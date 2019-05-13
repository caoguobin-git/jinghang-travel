/***********************************************
 * File Name: MyToken
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 13 05 2019 15:48
 ***********************************************/

package com.travel.realm;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyToken extends UsernamePasswordToken {

    //登录类型，判断用户登录还是管理员登录
    private String loginType;

    public MyToken(final String username, final String password,String loginType) {
        super(username,password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}

