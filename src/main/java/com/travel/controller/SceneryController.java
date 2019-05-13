package com.travel.controller;

import com.travel.common.entity.SceneryEntity;
import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
import com.travel.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/scenery")
public class SceneryController {

    @Autowired
    private SceneryService sceneryService;

    @RequestMapping("/doSceneryListUI")
    public String doSceneryListUI() {
        return "sys/scenery_list";
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
        PageObject pageObject = sceneryService.doFindPageObjects(pageCurrent, pageSize);
        return new JsonResult(pageObject);
    }

    @RequestMapping("/doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(String id){
        SceneryEntity sceneryEntity=sceneryService.doFindObjectById(id);
        return new JsonResult(sceneryEntity);
    }

    @RequestMapping("/doSceneryEditUI")
    public String doSceneryEditUI(){
        return "sys/scenery_edit";
    }
}
