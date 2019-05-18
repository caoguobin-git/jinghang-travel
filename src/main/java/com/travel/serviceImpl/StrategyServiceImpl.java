package com.travel.serviceImpl;

import com.google.common.base.Strings;
import com.travel.common.PicPaths;
import com.travel.common.entity.StrategyEntity;
import com.travel.common.util.FilePathUtil;
import com.travel.common.util.MD5HashUtils;
import com.travel.common.vo.PageObject;
import com.travel.mapper.StrategyMapper;
import com.travel.service.StrategyService;
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
public class StrategyServiceImpl implements StrategyService {
    private final String ROOT_PATH = PicPaths.ROOT_PATH;
    private final String CHILD_PATH = PicPaths.STRATEGY_PIC_PATH;

    @Autowired
    private StrategyMapper strategyMapper;

    @Override
    public PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize, String sceneryName) {
        if (pageSize == null) {
            pageSize = 6;
        }
        int pageCount = getPageCount(pageSize, sceneryName);
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(strategyMapper.getTotalCount(sceneryName));
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = strategyMapper.doFindPageObjects(sceneryName, new RowBounds((pageCurrent - 1) * pageSize, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public StrategyEntity doFindObjectById(String id) {
        StrategyEntity strategyEntity = strategyMapper.doFindObjectById(id);
        return strategyEntity;
    }


    @Override
    public String doDeleteObject(String strategyId) {
        StrategyEntity strategyEntity = strategyMapper.doFindObjectById(strategyId);
        if (strategyEntity == null) {
            return "记录不存在";
        }
        //删除图片

        String strategyPic = strategyEntity.getStrategyPic();
        String[] split = strategyPic.split(",");
        for (String s : split) {
            String prePicPath = ROOT_PATH + s;
            File preFile = new File(prePicPath);
            if (preFile.exists()) {
                preFile.delete();
            }
        }

        int a = strategyMapper.doDeleteObject(strategyId);
        if (a > 0) {
            return "ok";
        }
        return "删除失败，没有权限或记录不存在";
    }

    @Override
    public PageObject getStrategysByCityName(Integer pageCurrent, Integer pageSize, String cityName) {
        if (pageSize == null) {
            pageSize = 20;
        }
        int cityId = strategyMapper.getCityIdByCityPY(cityName);
        int total = strategyMapper.getPageCountByCityId(cityId);
        int pageCount = getPageCountByCityName(total, pageSize);
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
        List<Object> list = strategyMapper.getStrategysByCityName(pageCurrent, cityName, new RowBounds((pageCurrent - 1) * pageSize, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public String doSaveObject(String sceneryName, String strategyTitle, String strategyContent, MultipartFile[] strategyPicFile) throws IOException {
        String sceneryId = strategyMapper.getSceneryId(sceneryName);
        String strategyId = MD5HashUtils.getRandomUUID();
        StringBuffer strategyPicPath = new StringBuffer();
        if (strategyPicFile.length > 0) {
            for (MultipartFile multipartFile : strategyPicFile) {
                String fileName = multipartFile.getOriginalFilename();
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
                File file = new File(ROOT_PATH + CHILD_PATH + s);
                System.out.println(CHILD_PATH + s);
                strategyPicPath.append(CHILD_PATH + s+",");
                multipartFile.transferTo(file);
            }
        }
        System.out.println(strategyPicPath);
        StrategyEntity strategyEntity=new StrategyEntity();
        strategyEntity.setSceneryId(sceneryId);
        strategyEntity.setStrategyId(strategyId);
        strategyEntity.setStrategyTitle(strategyTitle);
        strategyEntity.setStrategyPic(strategyPicPath.toString());
        strategyEntity.setStrategyContent(strategyContent);
        int result = strategyMapper.doSaveObject(strategyEntity);
        if (result>0){
            return "ok";
        }
        return "新增攻略失败";
    }

    @Override
    public List<String> getSceneryOptions(String cityName) {
        List<String> result = strategyMapper.getSceneryOptions(cityName);
        return result;
    }

    @Override
    public String doUpdateObject(String strategyId, String sceneryName, String strategyTitle, String strategyContent, MultipartFile[] strategyPicFile) throws IOException {
        //查找原来的图片进行删除操作，节省磁盘空间
        StrategyEntity strategyEntity1 = strategyMapper.doFindObjectById(strategyId);
        if (strategyPicFile.length>0) {
            String strategyPic = strategyEntity1.getStrategyPic();
            String[] split = strategyPic.split(",");
            for (String s : split) {
                String prePicPath = ROOT_PATH + s;
                File preFile = new File(prePicPath);
                if (preFile.exists()) {
                    preFile.delete();
                }
            }
        }
        StringBuffer strategyPicPath = new StringBuffer();
        if (strategyPicFile.length > 0) {
            for (MultipartFile multipartFile : strategyPicFile) {
                String fileName = multipartFile.getOriginalFilename();
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
                File file = new File(ROOT_PATH + CHILD_PATH + s);
                System.out.println(CHILD_PATH + s);
                strategyPicPath.append(CHILD_PATH + s+",");
                multipartFile.transferTo(file);
            }
        }
        StrategyEntity strategyEntity = new StrategyEntity();
        String sceneryId = strategyMapper.getSceneryId(sceneryName);
        strategyEntity.setSceneryId(sceneryId);
        strategyEntity.setStrategyId(strategyId);
        strategyEntity.setStrategyTitle(strategyTitle);
        if (!Strings.isNullOrEmpty(strategyPicPath.toString())) {
            strategyEntity.setStrategyPic(strategyPicPath.toString());
        }
        strategyEntity.setStrategyContent(strategyContent);
        int a = strategyMapper.doUpdateObject(strategyEntity);
        if (a > 0) {
            return "ok";
        }
        return "修改失败";
    }


    private int getPageCountByCityName(int total, Integer pageSize) {
        return total / pageSize + 1;
    }


    public int getPageCount(int pageSize, String sceneryName) {
        return strategyMapper.getTotalCount(sceneryName) / pageSize + 1;
    }
}
