package com.travel.controller;

import com.google.common.base.Strings;
import com.travel.common.entity.SceneryEntity;
import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
import com.travel.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    public JsonResult doFindObjectById(String id) {
        SceneryEntity sceneryEntity = sceneryService.doFindObjectById(id);
        return new JsonResult(sceneryEntity);
    }

    @RequestMapping("/doSceneryEditUI")
    public String doSceneryEditUI() {
        return "sys/scenery_edit";
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(String cityName, String sceneryName, String sceneryDesc, MultipartFile sceneryPicFile) throws IOException {
        String result = sceneryService.doSaveObject(cityName, sceneryName, sceneryDesc, sceneryPicFile);
        System.out.println(cityName);
        System.out.println(sceneryName);
        System.out.println(sceneryDesc);
        System.out.println(sceneryPicFile);
        if ("ok".equals(result)) {
            return new JsonResult("OK");
        } else {
            return new JsonResult("201", "操作失败", "请重试");
        }
    }

    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(String sceneryId, String cityName, String sceneryName, String sceneryDesc, MultipartFile sceneryPicFile) throws IOException {
        String result = sceneryService.doUpdateObject(sceneryId, cityName, sceneryName, sceneryDesc, sceneryPicFile);
        System.out.println(sceneryId);
        System.out.println(cityName);
        System.out.println(sceneryName);
        System.out.println(sceneryDesc);
        System.out.println(sceneryPicFile);
        if ("ok".equals(result)) {
            return new JsonResult("OK");
        } else {
            return new JsonResult("201", "操作失败", "请重试");
        }
    }

    @RequestMapping("/doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(String sceneryId) {
        String result = sceneryService.doDeleteObject(sceneryId);
        if ("ok".equals(result)) {
            return new JsonResult("OK");
        } else {
            return new JsonResult("201", "操作失败", "删除失败，记录可能已经不存在");
        }
    }

    @RequestMapping("/getScenerysByCityName")
    @ResponseBody
    public JsonResult getScenerysByCityName(Integer pageCurrent, Integer pageSize,String cityName){
        if (pageCurrent == null) {
            pageCurrent = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageObject pageObject = sceneryService.getScenerysByCityName(pageCurrent, pageSize,cityName);
        return new JsonResult(pageObject);
    }
}
