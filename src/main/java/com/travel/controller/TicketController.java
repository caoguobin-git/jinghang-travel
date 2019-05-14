/***********************************************
 * File Name: TicketController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 13:45
 ***********************************************/

package com.travel.controller;

import com.sun.media.sound.SoftTuning;
import com.travel.common.entity.TicketEntity;
import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
import com.travel.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/ticket")
@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping("/doTicketListUI")
    public String doSceneryListUI() {
        return "sys/ticket_list";
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
        PageObject pageObject = ticketService.doFindPageObjects(pageCurrent, pageSize);
        return new JsonResult(pageObject);
    }

    @RequestMapping("/doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(String id){
        TicketEntity ticketEntity=ticketService.doFindObjectById(id);
        return new JsonResult(ticketEntity);
    }

    @RequestMapping("/doTicketEditUI")
    public String doSceneryEditUI(){
        return "sys/ticket_edit";
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(String sceneryName, String ticketName,String ticketDesc,double ticketPrice,String ticketType,String ticketTel, MultipartFile ticketPicFile) throws IOException {

        String result=ticketService.doSaveObject(sceneryName,ticketName,ticketDesc,ticketPrice,ticketType,ticketTel,ticketPicFile);
        System.out.println(sceneryName);
        System.out.println(ticketName);
        System.out.println(ticketDesc);
        System.out.println(ticketPrice);
        System.out.println(ticketPicFile);
        System.out.println(ticketType);
        System.out.println(ticketTel);
        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","请重试");
        }
    }
    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(String ticketId,String sceneryName, String ticketName,String ticketDesc,double ticketPrice,String ticketType,String ticketTel, MultipartFile ticketPicFile) throws IOException {
        String result=ticketService.doUpdateObject(ticketId,sceneryName,ticketName,ticketDesc,ticketPrice,ticketType,ticketTel,ticketPicFile);

        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","请重试");
        }
    }

    @RequestMapping("/getSceneryOptions")
    @ResponseBody
    public JsonResult getSceneryOptions(String cityName){
        List<String> scenerys=ticketService.getSceneryOptions(cityName);
        return new JsonResult(scenerys);
    }

    @RequestMapping("/doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(String ticketId){
        String result = ticketService.doDeleteObject(ticketId);
        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","删除失败，记录可能已经不存在");
        }
    }
}
