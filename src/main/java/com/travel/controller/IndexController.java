/***********************************************
 * File Name: IndexController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 9:59
 ***********************************************/

package com.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "home/user_index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/doPageUI")
    public String doPageUI(){
        return "common/page";
    }
    @RequestMapping("/adminIndex")
    public String doIndexUI(){
        return "starter";
    }
    @RequestMapping("/jump2page")
    public String jump2page(String url){
        return url;
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("logout")
    public String logout(){
        return "login";
    }

}
