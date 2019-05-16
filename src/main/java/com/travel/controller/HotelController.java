/***********************************************
 * File Name: HotelController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 13:45
 ***********************************************/

package com.travel.controller;

import com.travel.common.entity.HotelEntity;
import com.travel.common.entity.SceneryEntity;
import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
import com.travel.service.HotelService;
import com.travel.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@SuppressWarnings("ALL")
@RequestMapping("/hotel")
@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/doHotelListUI")
    public String doSceneryListUI() {
        return "sys/hotel_list";
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
        PageObject pageObject = hotelService.doFindPageObjects(pageCurrent, pageSize);
        return new JsonResult(pageObject);
    }

    @RequestMapping("/doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(String id){
        HotelEntity hotelEntity=hotelService.doFindObjectById(id);
        return new JsonResult(hotelEntity);
    }

    @RequestMapping("/doHotelEditUI")
    public String doSceneryEditUI(){
        return "sys/hotel_edit";
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(String sceneryName, String hotelName,String hotelDesc,double hotelPrice,String hotelAddr,String hotelTel, MultipartFile hotelPicFile) throws IOException {

        String result=hotelService.doSaveObject(sceneryName,hotelName,hotelDesc,hotelPrice,hotelAddr,hotelTel,hotelPicFile);
        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","请重试");
        }
    }
    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(String hotelId,String sceneryName, String hotelName,String hotelDesc,double hotelPrice,String hotelAddr,String hotelTel, MultipartFile hotelPicFile) throws IOException {
        String result=hotelService.doUpdateObject(hotelId,sceneryName,hotelName,hotelDesc,hotelPrice,hotelAddr,hotelTel,hotelPicFile);

        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","请重试");
        }
    }

    @RequestMapping("/getSceneryOptions")
    @ResponseBody
    public JsonResult getSceneryOptions(String cityName){
        List<String> scenerys=hotelService.getSceneryOptions(cityName);
        return new JsonResult(scenerys);
    }

    @RequestMapping("/doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(String hotelId){
        String result = hotelService.doDeleteObject(hotelId);
        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","删除失败，记录可能已经不存在");
        }
    }

    @RequestMapping("/getHotelListBySceneryId")
    @ResponseBody
    public JsonResult getHotelListBySceneryId(Integer pageCurrent,Integer pageSize,String sceneryId){
        if (pageCurrent == null) {
            pageCurrent = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        PageObject pageObject = hotelService.getHotelListBySceneryId(pageCurrent, pageSize,sceneryId);
        return new JsonResult(pageObject);
    }
}
