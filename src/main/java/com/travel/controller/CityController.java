/***********************************************
 * File Name: CityController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 10 05 2019 16:33
 ***********************************************/

package com.travel.controller;

import com.travel.common.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/city")
public class CityController {

    @RequestMapping("/getCityInfo")
    public String getCityInfo(Integer city) {
        String cityName = null;
        switch (city) {
            case 0:
                cityName = "beijing";
                break;
            case 1:
                cityName = "suqian";
                break;
            case 2:
                cityName = "tianjin";
                break;
            case 3:
                cityName = "huaian";
                break;
                default:cityName="beijing";
        }
        return "city/" + cityName;
    }

}
