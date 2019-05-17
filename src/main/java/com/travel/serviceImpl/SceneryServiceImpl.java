package com.travel.serviceImpl;

import com.travel.common.PicPaths;
import com.travel.common.entity.SceneryEntity;
import com.travel.common.util.FilePathUtil;
import com.travel.common.util.MD5HashUtils;
import com.travel.common.vo.PageObject;
import com.travel.mapper.SceneryMapper;
import com.travel.service.SceneryService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@Service
public class SceneryServiceImpl implements SceneryService {
    private final String ROOT_PATH = PicPaths.ROOT_PATH;
    private final String CHILD_PATH = PicPaths.SCENERY_PIC_PATH;

    @Autowired
    private SceneryMapper sceneryMapper;

    @Override
    public PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize) {
        if (pageSize == null) {
            pageSize = 6;
        }
        int pageCount = getPageCount(pageSize);
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(sceneryMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = sceneryMapper.doFindPageObjects(pageCurrent, new RowBounds((pageCurrent - 1) * pageSize, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public SceneryEntity doFindObjectById(String id) {
        SceneryEntity sceneryEntity = sceneryMapper.doFindObjectById(id);
        return sceneryEntity;
    }

    @Override
    public String doSaveObject(String cityName, String sceneryName, String sceneryDesc, MultipartFile sceneryPicFile) throws IOException {
        String sceneryPicPath = null;
        if (sceneryPicFile != null) {
            String fileName = sceneryPicFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            sceneryPicPath = CHILD_PATH + s;
            sceneryPicFile.transferTo(file);
        }
        int cityId = sceneryMapper.getCityId(cityName);
        System.out.println(cityId);
        SceneryEntity sceneryEntity = new SceneryEntity();
        String sceneryId = MD5HashUtils.getRandomUUID();
        sceneryEntity.setCityId(cityId);
        sceneryEntity.setSceneryId(sceneryId);
        sceneryEntity.setSceneryDesc(sceneryDesc);
        sceneryEntity.setSceneryName(sceneryName);
        sceneryEntity.setSceneryPic(sceneryPicPath);
        int a = sceneryMapper.doSaveObject(sceneryEntity);
        if (a > 0) {
            return "ok";
        }
        return null;
    }

    @Override
    public String doUpdateObject(String sceneryId, String cityName, String sceneryName, String sceneryDesc, MultipartFile sceneryPicFile) throws IOException {
        //查找原来的图片进行删除操作，节省磁盘空间
        SceneryEntity sceneryEntity1 = sceneryMapper.doFindObjectById(sceneryId);
        if (sceneryPicFile != null) {
            String sceneryPic = sceneryEntity1.getSceneryPic();
            String prePicPath = ROOT_PATH + sceneryPic;
            File preFile = new File(prePicPath);
            if (preFile.exists()) {
                preFile.delete();
            }
        }
        String sceneryPicPath = null;
        if (sceneryPicFile != null) {
            String fileName = sceneryPicFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            sceneryPicPath = CHILD_PATH + s;
            sceneryPicFile.transferTo(file);
        }
        int cityId = sceneryMapper.getCityId(cityName);
        System.out.println(cityId);
        SceneryEntity sceneryEntity = new SceneryEntity();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        sceneryEntity.setModifiedTime(timestamp);
        sceneryEntity.setCityId(cityId);
        sceneryEntity.setSceneryId(sceneryId);
        sceneryEntity.setSceneryDesc(sceneryDesc);
        sceneryEntity.setSceneryName(sceneryName);
        sceneryEntity.setSceneryPic(sceneryPicPath);
        int a = sceneryMapper.doUpdateObject(sceneryEntity);
        if (a > 0) {
            return "ok";
        }
        return "修改失败";
    }

    @Override
    public String doDeleteObject(String sceneryId) {
        SceneryEntity sceneryEntity = sceneryMapper.doFindObjectById(sceneryId);
        if (sceneryEntity == null) {
            return "记录不存在";
        }
        //删除图片
        String sceneryPic = sceneryEntity.getSceneryPic();
        String prePicPath = ROOT_PATH + sceneryPic;
        File preFile = new File(prePicPath);
        if (preFile.exists()) {
            preFile.delete();
        }

        int a = sceneryMapper.doDeleteObject(sceneryId);
        if (a > 0) {
            return "ok";
        }
        return "删除失败，没有权限或记录不存在";
    }

    @Override
    public PageObject getScenerysByCityName(Integer pageCurrent, Integer pageSize, String cityName) {
        if (pageSize == null) {
            pageSize = 20;
        }
        int cityId=sceneryMapper.getCityIdByCityPY(cityName);
        int total=sceneryMapper.getPageCountByCityId(cityId);
        int pageCount = getPageCountByCityName(total,pageSize);
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(total);
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = sceneryMapper.getScenerysByCityName(pageCurrent, cityName, new RowBounds((pageCurrent - 1)*pageSize, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    private int getPageCountByCityName(int total, Integer pageSize) {
        return total/pageSize+1;
    }


    public int getPageCount(int pageSize) {
        return sceneryMapper.getPageCount() / pageSize + 1;
    }
}
