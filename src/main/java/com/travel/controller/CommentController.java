/***********************************************
 * File Name: CommentController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 13:45
 ***********************************************/

package com.travel.controller;

import com.travel.common.entity.CommentEntity;
import com.travel.common.vo.JsonResult;
import com.travel.common.vo.PageObject;
import com.travel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("ALL")
@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/doCommentListUI")
    public String doSceneryListUI() {
        return "sys/comment_list";
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
        PageObject pageObject = commentService.doFindPageObjects(pageCurrent, pageSize);
        return new JsonResult(pageObject);
    }

    @RequestMapping("/doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(String id){
        CommentEntity commentEntity=commentService.doFindObjectById(id);
        return new JsonResult(commentEntity);
    }

    @RequestMapping("/doCommentEditUI")
    public String doSceneryEditUI(){
        return "sys/comment_edit";
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(String sceneryId, String userId,String commentTitle,String commentContent, MultipartFile commentPic) throws IOException {
        System.out.println(sceneryId);
        System.out.println(userId);
        System.out.println(commentTitle);
        System.out.println(commentContent);
        System.out.println(commentPic);

        String result=commentService.doSaveObject(sceneryId,userId,commentTitle,commentContent,commentPic);
        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","请重试");
        }
    }
    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(String commentId,String sceneryName, String commentName,String commentDesc,double commentPrice, MultipartFile commentPicFile) throws IOException {
        String result=commentService.doUpdateObject(commentId,sceneryName,commentName,commentDesc,commentPrice,commentPicFile);

        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","请重试");
        }
    }

    @RequestMapping("/getSceneryOptions")
    @ResponseBody
    public JsonResult getSceneryOptions(String cityName){
        List<String> scenerys=commentService.getSceneryOptions(cityName);
        return new JsonResult(scenerys);
    }

    @RequestMapping("/getCommentListBySceneryId")
    @ResponseBody
    public JsonResult getCommentListBySceneryId(Integer pageCurrent,Integer pageSize,String sceneryId){
        if (pageCurrent == null) {
            pageCurrent = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        PageObject pageObject = commentService.getCommentListBySceneryId(pageCurrent, pageSize,sceneryId);
        return new JsonResult(pageObject);
    }

    @RequestMapping("/doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(String commentId){
        String result = commentService.doDeleteObject(commentId);
        if ("ok".equals(result)){
            return new JsonResult("OK");
        }else {
            return new JsonResult("201","操作失败","删除失败，记录可能已经不存在");
        }
    }
}
