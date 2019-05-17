/***********************************************
 * File Name: TicketServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:10
 ***********************************************/

package com.travel.serviceImpl;

import com.travel.common.PicPaths;
import com.travel.common.entity.TicketEntity;
import com.travel.common.util.FilePathUtil;
import com.travel.common.util.MD5HashUtils;
import com.travel.common.vo.PageObject;
import com.travel.mapper.TicketMapper;
import com.travel.service.TicketService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
@SuppressWarnings("ALL")
@Service
public class TicketServiceImpl implements TicketService {

    private final String ROOT_PATH = PicPaths.ROOT_PATH;
    private final String CHILD_PATH = PicPaths.TICKET_PIC_PATH;


    @Autowired
    private TicketMapper ticketMapper;

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
        pageObject.setTotal(ticketMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = ticketMapper.doFindPageObjects(pageCurrent, new RowBounds((pageCurrent-1)*pageSize, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public TicketEntity doFindObjectById(String id) {
        TicketEntity ticketEntity = ticketMapper.doFindObjectById(id);
        return ticketEntity;
    }


    @Override
    public String doUpdateObject(String ticketId, String sceneryName, String ticketName, String ticketDesc, double ticketPrice, String ticketType, String ticketTel, MultipartFile ticketPicFile) throws IOException {
        //查找原来的图片进行删除操作，节省磁盘空间
        TicketEntity ticketEntity = ticketMapper.doFindObjectById(ticketId);
        if (ticketPicFile != null) {
            String ticketPic = ticketEntity.getTicketPic();
            String prePicPath = ROOT_PATH + ticketPic;
            File preFile = new File(prePicPath);
            if (preFile.exists()) {
                preFile.delete();
            }
        }
        String ticketPicPath = null;
        if (ticketPicFile != null) {
            String fileName = ticketPicFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            ticketPicPath = CHILD_PATH + s;
            ticketPicFile.transferTo(file);
        }
        String sceneryId = ticketMapper.getSceneryId(sceneryName);
        System.out.println(sceneryId);
        TicketEntity ticketEntity1=new TicketEntity();
        ticketEntity1.setTicketId(ticketId);
        ticketEntity1.setTicketName(ticketName);
        ticketEntity1.setTicketDesc(ticketDesc);
        ticketEntity1.setTicketType(ticketType);
        ticketEntity1.setTicketTel(ticketTel);
        ticketEntity1.setTicketPic(ticketPicPath);
        ticketEntity1.setSceneryId(sceneryId);
        ticketEntity1.setTicketPrice(ticketPrice);


        int a = ticketMapper.doUpdateObject(ticketEntity1);
        if (a > 0) {
            return "ok";
        }
        return null;
    }

    @Override
    public String doDeleteObject(String ticketId) {
        TicketEntity ticketEntity = ticketMapper.doFindObjectById(ticketId);
        if (ticketEntity == null) {
            return "记录不存在";
        }
        //删除图片
        String ticketPic = ticketEntity.getTicketPic();
        String prePicPath = ROOT_PATH + ticketPic;
        File preFile = new File(prePicPath);
        if (preFile.exists()) {
            preFile.delete();
        }

        int a = ticketMapper.doDeleteObject(ticketId);
        if (a > 0) {
            return "ok";
        }
        return "删除失败，没有权限或记录不存在";
    }

    @Override
    public List<String> getSceneryOptions(String cityName) {
        List<String> result = ticketMapper.getSceneryOptions(cityName);
        return result;
    }

    @Override
    public String doSaveObject(String sceneryName, String ticketName, String ticketDesc, double ticketPrice, String ticketType, String ticketTel, MultipartFile ticketPicFile) throws IOException {
        String ticketPicPath = null;
        if (ticketPicFile != null) {
            String fileName = ticketPicFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            ticketPicPath = CHILD_PATH + s;
            ticketPicFile.transferTo(file);
        }
        String sceneryId = ticketMapper.getSceneryId(sceneryName);
        System.out.println(sceneryId);
        TicketEntity ticketEntity = new TicketEntity();
        String ticketId = MD5HashUtils.getRandomUUID();
        ticketEntity.setTicketId(ticketId);
        ticketEntity.setSceneryId(sceneryId);
        ticketEntity.setTicketName(ticketName);
        ticketEntity.setTicketPrice(ticketPrice);
        ticketEntity.setTicketPic(ticketPicPath);
        ticketEntity.setTicketType(ticketType);
        ticketEntity.setTicketTel(ticketTel);
        ticketEntity.setTicketDesc(ticketDesc);

        int a = ticketMapper.doSaveObject(ticketEntity);
        if (a > 0) {
            return "ok";
        }
        return null;
    }

    @Override
    public TicketEntity getTicketInfoInfoBySceneryId(String sceneryId, String ticketType) {
        TicketEntity ticketEntity = ticketMapper.getTicketInfoInfoBySceneryId(sceneryId,ticketType);
        return ticketEntity;
    }


    public int getPageCount(int pageSize) {
        return ticketMapper.getPageCount() / pageSize + 1;
    }
}
