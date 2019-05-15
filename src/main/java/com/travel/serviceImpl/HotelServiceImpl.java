/***********************************************
 * File Name: HotelServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:10
 ***********************************************/

package com.travel.serviceImpl;

import com.travel.common.PicPaths;
import com.travel.common.entity.HotelEntity;
import com.travel.common.util.FilePathUtil;
import com.travel.common.util.MD5HashUtils;
import com.travel.common.vo.PageObject;
import com.travel.mapper.HotelMapper;
import com.travel.service.HotelService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final String ROOT_PATH = PicPaths.ROOT_PATH;
    private final String CHILD_PATH = PicPaths.HOTEL_PIC_PATH;


    @Autowired
    private HotelMapper hotelMapper;

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
        pageObject.setTotal(hotelMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = hotelMapper.doFindPageObjects(pageCurrent, new RowBounds(pageCurrent - 1, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public HotelEntity doFindObjectById(String id) {
        HotelEntity hotelEntity = hotelMapper.doFindObjectById(id);
        return hotelEntity;
    }


    @Override
    public String doUpdateObject(String hotelId, String sceneryName, String hotelName, String hotelDesc, double hotelPrice, String hotelAddr, String hotelTel, MultipartFile hotelPicFile) throws IOException {
        //查找原来的图片进行删除操作，节省磁盘空间
        HotelEntity hotelEntity = hotelMapper.doFindObjectById(hotelId);
        if (hotelPicFile != null) {
            String hotelPic = hotelEntity.getHotelPic();
            String prePicPath = ROOT_PATH + hotelPic;
            File preFile = new File(prePicPath);
            if (preFile.exists()) {
                preFile.delete();
            }
        }
        String hotelPicPath = null;
        if (hotelPicFile != null) {
            String fileName = hotelPicFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            hotelPicPath = CHILD_PATH + s;
            hotelPicFile.transferTo(file);
        }
        String sceneryId = hotelMapper.getSceneryId(sceneryName);
        System.out.println(sceneryId);
        HotelEntity hotelEntity1=new HotelEntity();
        hotelEntity1.setHotelId(hotelId);
        hotelEntity1.setHotelName(hotelName);
        hotelEntity1.setHotelDesc(hotelDesc);
        hotelEntity1.setHotelPic(hotelPicPath);
        hotelEntity1.setSceneryId(sceneryId);
        hotelEntity1.setHotelPrice(hotelPrice);
        hotelEntity1.setHotelAddr(hotelAddr);
        hotelEntity1.setHotelTel(hotelTel);

        int a = hotelMapper.doUpdateObject(hotelEntity1);
        if (a > 0) {
            return "ok";
        }
        return null;
    }

    @Override
    public String doDeleteObject(String hotelId) {
        HotelEntity hotelEntity = hotelMapper.doFindObjectById(hotelId);
        if (hotelEntity == null) {
            return "记录不存在";
        }
        //删除图片
        String hotelPic = hotelEntity.getHotelPic();
        String prePicPath = ROOT_PATH + hotelPic;
        File preFile = new File(prePicPath);
        if (preFile.exists()) {
            preFile.delete();
        }

        int a = hotelMapper.doDeleteObject(hotelId);
        if (a > 0) {
            return "ok";
        }
        return "删除失败，没有权限或记录不存在";
    }

    @Override
    public List<String> getSceneryOptions(String cityName) {
        List<String> result = hotelMapper.getSceneryOptions(cityName);
        return result;
    }

    @Override
    public String doSaveObject(String sceneryName, String hotelName, String hotelDesc, double hotelPrice, String hotelAddr, String hotelTel, MultipartFile hotelPicFile) throws IOException {
        String hotelPicPath = null;
        if (hotelPicFile != null) {
            String fileName = hotelPicFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            hotelPicPath = CHILD_PATH + s;
            hotelPicFile.transferTo(file);
        }
        String sceneryId = hotelMapper.getSceneryId(sceneryName);
        System.out.println(sceneryId);
        HotelEntity hotelEntity = new HotelEntity();
        String hotelId = MD5HashUtils.getRandomUUID();
        hotelEntity.setHotelId(hotelId);
        hotelEntity.setSceneryId(sceneryId);
        hotelEntity.setHotelName(hotelName);
        hotelEntity.setHotelPrice(hotelPrice);
        hotelEntity.setHotelPic(hotelPicPath);
        hotelEntity.setHotelDesc(hotelDesc);
        hotelEntity.setHotelAddr(hotelAddr);
        hotelEntity.setHotelTel(hotelTel);

        int a = hotelMapper.doSaveObject(hotelEntity);
        if (a > 0) {
            return "ok";
        }
        return null;
    }


    public int getPageCount(int pageSize) {
        return hotelMapper.getPageCount() / pageSize + 1;
    }
}
