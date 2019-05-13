package com.travel.serviceImpl;

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
import java.util.List;

@Service
public class SceneryServiceImpl implements SceneryService {
    private final String ROOT_PATH="e:/travel";
    private final String CHILD_PATH="/sceneryPic";

    @Autowired
    private SceneryMapper sceneryMapper;

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
        pageObject.setTotal(sceneryMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = sceneryMapper.doFindPageObjects(pageCurrent,new RowBounds(pageCurrent-1, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public SceneryEntity doFindObjectById(String id) {
        SceneryEntity sceneryEntity=sceneryMapper.doFindObjectById(id);
        return sceneryEntity;
    }

    @Override
    public String doSaveObject(String cityName, String sceneryName, String sceneryDesc, MultipartFile sceneryPicFile) throws IOException {

        String fileName=sceneryPicFile.getOriginalFilename();
        String fileType=fileName.substring(fileName.lastIndexOf(".")+1);
        String s = FilePathUtil.uploadFile(ROOT_PATH+CHILD_PATH, fileType);
        System.out.println(s);
        File file=new File(ROOT_PATH+CHILD_PATH+s);
        System.out.println(CHILD_PATH+s);
        String sceneryPicPath=CHILD_PATH+s;
        sceneryPicFile.transferTo(file);
        int cityId=sceneryMapper.getCityId(cityName);
        System.out.println(cityId);
        SceneryEntity sceneryEntity=new SceneryEntity();
        String sceneryId= MD5HashUtils.getRandomUUID();
        sceneryEntity.setCityId(cityId);
        sceneryEntity.setSceneryId(sceneryId);
        sceneryEntity.setSceneryDesc(sceneryDesc);
        sceneryEntity.setSceneryName(sceneryName);
        sceneryEntity.setSceneryPic(sceneryPicPath);
        int a = sceneryMapper.doSaveObject(sceneryEntity);

        return null;
    }


    public int getPageCount(int pageSize) {
        return sceneryMapper.getPageCount() / pageSize + 1;
    }
}
