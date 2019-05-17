/***********************************************
 * File Name: CommentServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:10
 ***********************************************/

package com.travel.serviceImpl;

import com.travel.common.PicPaths;
import com.travel.common.entity.CommentEntity;
import com.travel.common.util.FilePathUtil;
import com.travel.common.util.MD5HashUtils;
import com.travel.common.vo.PageObject;
import com.travel.mapper.CommentMapper;
import com.travel.service.CommentService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class CommentServiceImpl implements CommentService {

    private final String ROOT_PATH = PicPaths.ROOT_PATH;
    private final String CHILD_PATH = PicPaths.COMMENT_PIC_PATH;


    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize) {
        if (pageSize == null) {
            pageSize = 20;
        }
        int pageCount = getPageCount(pageSize);
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(commentMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = commentMapper.doFindPageObjects(pageCurrent, new RowBounds(pageCurrent - 1, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public CommentEntity doFindObjectById(String id) {
        CommentEntity commentEntity = commentMapper.doFindObjectById(id);
        return commentEntity;
    }


    @Override
    public String doUpdateObject(String commentId,String sceneryName, String commentName,String commentDesc,double commentPrice, MultipartFile commentPicFile) throws IOException {
        //查找原来的图片进行删除操作，节省磁盘空间
        CommentEntity commentEntity = commentMapper.doFindObjectById(commentId);
        if (commentPicFile != null) {
            String commentPic = commentEntity.getCommentPic();
            String prePicPath = ROOT_PATH + commentPic;
            File preFile = new File(prePicPath);
            if (preFile.exists()) {
                preFile.delete();
            }
        }
        String commentPicPath = null;
        if (commentPicFile != null) {
            String fileName = commentPicFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            commentPicPath = CHILD_PATH + s;
            commentPicFile.transferTo(file);
        }
        String sceneryId = commentMapper.getSceneryId(sceneryName);
        System.out.println(sceneryId);
        CommentEntity commentEntity1=new CommentEntity();
        commentEntity1.setCommentId(commentId);
        commentEntity1.setCommentPic(commentPicPath);
        commentEntity1.setSceneryId(sceneryId);

        int a = commentMapper.doUpdateObject(commentEntity1);
        if (a > 0) {
            return "ok";
        }
        return null;
    }

    @Override
    public String doDeleteObject(String commentId) {
        CommentEntity commentEntity = commentMapper.doFindObjectById(commentId);
        if (commentEntity == null) {
            return "记录不存在";
        }
        //删除图片
        String commentPic = commentEntity.getCommentPic();
        String prePicPath = ROOT_PATH + commentPic;
        File preFile = new File(prePicPath);
        if (preFile.exists()) {
            preFile.delete();
        }

        int a = commentMapper.doDeleteObject(commentId);
        if (a > 0) {
            return "ok";
        }
        return "删除失败，没有权限或记录不存在";
    }

    @Override
    public List<String> getSceneryOptions(String cityName) {
        List<String> result = commentMapper.getSceneryOptions(cityName);
        return result;
    }



    @Override
    public PageObject getCommentListBySceneryId(Integer pageCurrent, Integer pageSize, String sceneryId) {
        if (pageSize == null) {
            pageSize = 5;
        }
        int pageCount = getPageCount(pageSize);
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(commentMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        System.out.println("后台pageCUrrent"+pageCurrent);
        System.out.println("pagesize"+pageSize);
        List<Object> list = commentMapper.getCommentListBySceneryId(sceneryId, new RowBounds((pageCurrent - 1)*pageSize, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public String doSaveObject(String sceneryId, String userId, String commmentTitle, String commentContent, MultipartFile commentPic) throws IOException {
        String commentPicPath = null;
        if (commentPic != null) {
            String fileName = commentPic.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            commentPicPath = CHILD_PATH + s;
            commentPic.transferTo(file);
        }
        CommentEntity commentEntity = new CommentEntity();
        String commentId = MD5HashUtils.getRandomUUID();
        commentEntity.setCommentId(commentId);
        commentEntity.setSceneryId(sceneryId);
        commentEntity.setUserId(userId);
        commentEntity.setCommentTitle(commmentTitle);
        commentEntity.setCommentContent(commentContent);
        commentEntity.setCommentPic(commentPicPath);

        int a = commentMapper.doSaveObject(commentEntity);
        if (a > 0) {
            return "ok";
        }
        return "评论失败";
    }


    public int getPageCount(int pageSize) {
        return commentMapper.getPageCount() / pageSize + 1;
    }
}
