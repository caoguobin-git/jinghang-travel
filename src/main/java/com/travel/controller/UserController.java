/***********************************************
 * File Name: UserController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 13 05 2019 13:39
 ***********************************************/

package com.travel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.common.LoginType;
import com.travel.common.entity.AdminEntity;
import com.travel.common.entity.UserEntity;
import com.travel.common.util.IPUtils;
import com.travel.common.vo.JsonResult;
import com.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
private final String USER_LOGIN_TYPE=LoginType.USER.toString();


    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "/home/user_index";
    }

    @RequestMapping("/loadPage")
    public String loadPage(String url){
        return "/home/"+url;
    }
    @RequestMapping("/register")
    @ResponseBody
    public JsonResult register(HttpServletRequest request,UserEntity userEntity){
        String ipAddress = IPUtils.getIpAddress(request);
        userEntity.setLoginIp(ipAddress);
        String result = userService.register(userEntity);
        if (result.contains("username")) {
            try {
                UserEntity adminEntity1 = new ObjectMapper().readValue(result, userEntity.getClass());
                return new JsonResult(adminEntity1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return new JsonResult("401", "注册失败", result);
        }
        return null;
    }
    @RequestMapping("/loginPage")
    public String loginPage(){
        return "/home/user_login";
    }
    @RequestMapping("/registerPage")
    public String registerPage(){
        return "/home/user_register";
    }

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(HttpServletRequest request,String username,String password){
        HttpSession session = request.getSession();
        System.out.println("username: "+username);
        String login = userService.login(username, password,USER_LOGIN_TYPE);
        if (login.contains("username")){
            UserEntity userEntity = null;
            try {
                userEntity = new ObjectMapper().readValue(login, UserEntity.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            session.setAttribute("userInfo", userEntity);
            return new JsonResult(userEntity);
        }
        return new JsonResult("201","登录失败",login);
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public JsonResult getUserInfo(HttpServletRequest request){
        Object userInfo = request.getSession().getAttribute("userInfo");
        if (userInfo==null){
            return new JsonResult("201","尚未登录","尚未登录");
        }
        return new JsonResult(userInfo);
    }
}
