/***********************************************
 * File Name: UserController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 11:23
 ***********************************************/

package com.travel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.common.entity.UserEntity;
import com.travel.common.util.CookieUtils;
import com.travel.common.util.IPUtils;
import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
import com.travel.service.UserService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/doUserListUI")
    public String doUserListUI() {
        return "sys/user_list";
    }

//    @ResponseBody
//    @RequestMapping("/register")
//    public JsonResult register(HttpServletRequest request, UserEntity userEntity) {
//        String ipAddress = IPUtils.getIpAddress(request);
//        userEntity.setLastIp(ipAddress);
//        String result = userService.register(userEntity);
//        if ("ok".equalsIgnoreCase(result)) {
//            return new JsonResult("注册成功");
//        } else {
//            return new JsonResult("401", "注册失败", result);
//        }
//    }
    @ResponseBody
    @RequestMapping("/doUserRegister")
    public JsonResult doUserRegister(HttpServletRequest request, UserEntity userEntity) {
        String ipAddress = IPUtils.getIpAddress(request);
        userEntity.setLastIp(ipAddress);
        String result = userService.doUserRegister(userEntity);
        if (result.contains("username")) {
            try {
                UserEntity userEntity1 = new ObjectMapper().readValue(result, userEntity.getClass());
                return new JsonResult(userEntity1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return new JsonResult("401", "注册失败", result);
        }
        return null;
    }

//    @ResponseBody
//    @RequestMapping("/login")
//    public JsonResult login(HttpServletRequest request, HttpServletResponse response, String username, String password) throws IOException {
//        Map<String, Object> login = userService.login(username, password);
//        ObjectMapper objectMapper = new ObjectMapper();
//        if ("ok".equalsIgnoreCase((String) login.get("code"))) {
//            String message = objectMapper.writeValueAsString(login.get("message"));
//            message = StringEscapeUtils.escapeJava(message);
//            CookieUtils.setCookie(request, response, "userToken", message, 5000000);
//            response.setHeader("refresh", "3;url=/index.do");
//            return new JsonResult(login);
//        } else {
//            return new JsonResult("403", "登录失败", login.get("message"));
//        }
//    }

    @ResponseBody
    @RequestMapping("/login")
    public JsonResult login(String username, String password){
        System.out.println("username: "+username);
        String login = userService.login(username, password);
        if (login.contains("username")){
            UserEntity userEntity = null;
            try {
                userEntity = new ObjectMapper().readValue(login, UserEntity.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new JsonResult(userEntity);
        }


        return new JsonResult("201","登录失败",login);
    }

    @RequestMapping("doUserEditUI")
    public String doUserEditUI() {
        return "sys/user_edit";
    }

    @RequestMapping("doValidById")
    @ResponseBody
    public JsonResult doValidById(String userId, Integer valid) {
        System.out.println(userId);
        System.out.println(valid);
        userService.doValidById(userId,valid);
        return new JsonResult("update ok");
    }


    @RequestMapping("/doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(Integer pageCurrent, Integer pageSize) {
        if (pageCurrent == null) {
            pageCurrent = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageObject pageObject = userService.doFindPageObjects(pageCurrent, pageSize);
        return new JsonResult(pageObject);
    }


}
