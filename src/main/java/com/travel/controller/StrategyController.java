package com.travel.controller;

import com.travel.common.entity.StrategyEntity;
import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
import com.travel.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("ALL")
@Controller
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    private StrategyService strategyService;

    @RequestMapping("/doStrategyListUI")
    public String doStrategyListUI() {
        return "sys/strategy_list";
    }

    @RequestMapping("/doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(Integer pageCurrent, Integer pageSize, String sceneryName) {

        if (pageCurrent == null) {
            pageCurrent = 1;
        }
        if (pageSize == null) {
            pageSize = 6;
        }
        PageObject pageObject = strategyService.doFindPageObjects(pageCurrent, pageSize, sceneryName);
        return new JsonResult(pageObject);
    }

    @RequestMapping("/doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(String id) {
        StrategyEntity strategyEntity = strategyService.doFindObjectById(id);
        return new JsonResult(strategyEntity);
    }
    @RequestMapping("/getSceneryOptions")
    @ResponseBody
    public JsonResult getSceneryOptions(String cityName){
        List<String> scenerys=strategyService.getSceneryOptions(cityName);
        return new JsonResult(scenerys);
    }


    @RequestMapping("/doStrategyEditUI")
    public String doStrategyEditUI() {
        return "sys/strategy_edit";
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(String sceneryName, String strategyTitle, String strategyContent,@RequestParam MultipartFile[] strategyPicFile) throws IOException {
        System.out.println(sceneryName);
        System.out.println(strategyTitle);
        System.out.println(strategyContent);
        System.out.println(strategyPicFile);
        System.out.println(strategyPicFile.length);
        String result = strategyService.doSaveObject(sceneryName,strategyTitle,strategyContent,strategyPicFile);

        if ("ok".equals(result)) {
            return new JsonResult("OK");
        } else {
            return new JsonResult("201", "操作失败", "请重试");
        }
    }

    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(String strategyId,String sceneryName, String strategyTitle, String strategyContent,@RequestParam MultipartFile[] strategyPicFile) throws IOException {
        String result = strategyService.doUpdateObject(strategyId,sceneryName,strategyTitle,strategyContent,strategyPicFile);
        if ("ok".equals(result)) {
            return new JsonResult("OK");
        } else {
            return new JsonResult("201", "操作失败", "请重试");
        }
    }

    @RequestMapping("/doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(String strategyId) {
        String result = strategyService.doDeleteObject(strategyId);
        if ("ok".equals(result)) {
            return new JsonResult("OK");
        } else {
            return new JsonResult("201", "操作失败", "删除失败，记录可能已经不存在");
        }
    }

    @RequestMapping("/getStrategysByCityName")
    @ResponseBody
    public JsonResult getStrategysByCityName(Integer pageCurrent, Integer pageSize, String cityName) {
        if (pageCurrent == null) {
            pageCurrent = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageObject pageObject = strategyService.getStrategysByCityName(pageCurrent, pageSize, cityName);
        return new JsonResult(pageObject);
    }
}
