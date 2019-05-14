/***********************************************
 * File Name: FoodController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 13:45
 ***********************************************/

package com.travel.controller;

import com.travel.common.entity.FoodEntity;
import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
import com.travel.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/food")
@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    @RequestMapping("/doFoodListUI")
    public String doSceneryListUI() {
        return "sys/food_list";
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
        PageObject pageObject = foodService.doFindPageObjects(pageCurrent, pageSize);
        return new JsonResult(pageObject);
    }

    @RequestMapping("/doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(String id){
        FoodEntity foodEntity=foodService.doFindObjectById(id);
        return new JsonResult(foodEntity);
    }

    @RequestMapping("/doFoodEditUI")
    public String doSceneryEditUI(){
        return "sys/food_edit";
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(String sceneryName, String foodName,String foodDesc,double foodPrice, MultipartFile foodPicFile) throws IOException {

        String result=foodService.doSaveObject(sceneryName,foodName,foodDesc,foodPrice,foodPicFile);
        System.out.println(sceneryName);
        System.out.println(foodName);
        System.out.println(foodDesc);
        System.out.println(foodPrice);
        System.out.println(foodPicFile);
        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","请重试");
        }
    }
    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(String foodId,String sceneryName, String foodName,String foodDesc,double foodPrice, MultipartFile foodPicFile) throws IOException {
        String result=foodService.doUpdateObject(foodId,sceneryName,foodName,foodDesc,foodPrice,foodPicFile);

        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","请重试");
        }
    }

    @RequestMapping("/getSceneryOptions")
    @ResponseBody
    public JsonResult getSceneryOptions(String cityName){
        List<String> scenerys=foodService.getSceneryOptions(cityName);
        return new JsonResult(scenerys);
    }

    @RequestMapping("/doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(String foodId){
        String result = foodService.doDeleteObject(foodId);
        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","删除失败，记录可能已经不存在");
        }
    }
}
