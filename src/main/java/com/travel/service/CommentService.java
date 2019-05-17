/***********************************************
 * File Name: CommentService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:10
 ***********************************************/
package com.travel.service;

import com.travel.common.entity.CommentEntity;
import com.travel.common.vo.PageObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize);

    CommentEntity doFindObjectById(String id);

    String doUpdateObject(String commentId, String sceneryName, String commentName, String commentDesc, double commentPrice, MultipartFile commentPicFile) throws IOException;

    String doDeleteObject(String commentId);

    List<String> getSceneryOptions(String cityName);


    PageObject getCommentListBySceneryId(Integer pageCurrent, Integer pageSize, String sceneryId);

    String doSaveObject(String sceneryId, String userId, String commmentTitle, String commentContent, MultipartFile commentPic) throws IOException;
}
