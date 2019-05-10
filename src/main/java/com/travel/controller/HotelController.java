/***********************************************
 * File Name: HotelController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 13:45
 ***********************************************/

package com.travel.controller;

import com.travel.common.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/hotel")
@Controller
public class HotelController {

    @RequestMapping("/saveyuding")
    @ResponseBody
    public JsonResult saveyuding(){
        System.out.println("hahahah");
        return new JsonResult("ok");
    }
}
