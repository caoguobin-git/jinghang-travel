/***********************************************
 * File Name: UserController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 11:23
 ***********************************************/

package com.travel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.common.LoginType;
import com.travel.common.entity.AdminEntity;
import com.travel.common.util.IPUtils;
import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
import com.travel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final String ADMIN_LOGIN_TYPE= LoginType.ADMIN.toString();

    @Autowired
    private AdminService adminService;

    @RequestMapping("/doUserListUI")
    public String doUserListUI() {
        return "sys/user_list";
    }

//    @ResponseBody
//    @RequestMapping("/register")
//    public JsonResult register(HttpServletRequest request, AdminEntity adminEntity) {
//        String ipAddress = IPUtils.getIpAddress(request);
//        adminEntity.setLastIp(ipAddress);
//        String result = adminService.register(adminEntity);
//        if ("ok".equalsIgnoreCase(result)) {
//            return new JsonResult("注册成功");
//        } else {
//            return new JsonResult("401", "注册失败", result);
//        }
//    }
    @ResponseBody
    @RequestMapping("/doUserRegister")
    public JsonResult doUserRegister(HttpServletRequest request, AdminEntity adminEntity) {
        String ipAddress = IPUtils.getIpAddress(request);
        adminEntity.setLastIp(ipAddress);
        String result = adminService.doUserRegister(adminEntity);
        if (result.contains("username")) {
            try {
                AdminEntity adminEntity1 = new ObjectMapper().readValue(result, adminEntity.getClass());
                return new JsonResult(adminEntity1);

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
//        Map<String, Object> login = adminService.login(username, password);
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
        String login = adminService.login(username, password,ADMIN_LOGIN_TYPE);
        if (login.contains("username")){
            AdminEntity adminEntity = null;
            try {
                adminEntity = new ObjectMapper().readValue(login, AdminEntity.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new JsonResult(adminEntity);
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
        adminService.doValidById(userId,valid);
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
        PageObject pageObject = adminService.doFindPageObjects(pageCurrent, pageSize);
        return new JsonResult(pageObject);
    }


}
