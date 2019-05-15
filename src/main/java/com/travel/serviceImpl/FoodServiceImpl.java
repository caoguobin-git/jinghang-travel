/***********************************************
 * File Name: FoodServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:10
 ***********************************************/

package com.travel.serviceImpl;

import com.travel.common.PicPaths;
import com.travel.common.entity.FoodEntity;
import com.travel.common.util.FilePathUtil;
import com.travel.common.util.MD5HashUtils;
import com.travel.common.vo.PageObject;
import com.travel.mapper.FoodMapper;
import com.travel.service.FoodService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final String ROOT_PATH = PicPaths.ROOT_PATH;
    private final String CHILD_PATH = PicPaths.FOOD_PIC_PATH;


    @Autowired
    private FoodMapper foodMapper;

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
        pageObject.setTotal(foodMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = foodMapper.doFindPageObjects(pageCurrent, new RowBounds(pageCurrent - 1, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public FoodEntity doFindObjectById(String id) {
        FoodEntity foodEntity = foodMapper.doFindObjectById(id);
        return foodEntity;
    }


    @Override
    public String doUpdateObject(String foodId,String sceneryName, String foodName,String foodDesc,double foodPrice, MultipartFile foodPicFile) throws IOException {
        //查找原来的图片进行删除操作，节省磁盘空间
        FoodEntity foodEntity = foodMapper.doFindObjectById(foodId);
        if (foodPicFile != null) {
            String foodPic = foodEntity.getFoodPic();
            String prePicPath = ROOT_PATH + foodPic;
            File preFile = new File(prePicPath);
            if (preFile.exists()) {
                preFile.delete();
            }
        }
        String foodPicPath = null;
        if (foodPicFile != null) {
            String fileName = foodPicFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            foodPicPath = CHILD_PATH + s;
            foodPicFile.transferTo(file);
        }
        String sceneryId = foodMapper.getSceneryId(sceneryName);
        System.out.println(sceneryId);
        FoodEntity foodEntity1=new FoodEntity();
        foodEntity1.setFoodId(foodId);
        foodEntity1.setFoodName(foodName);
        foodEntity1.setFoodDesc(foodDesc);
        foodEntity1.setFoodPic(foodPicPath);
        foodEntity1.setSceneryId(sceneryId);
        foodEntity1.setFoodPrice(foodPrice);

        int a = foodMapper.doUpdateObject(foodEntity1);
        if (a > 0) {
            return "ok";
        }
        return null;
    }

    @Override
    public String doDeleteObject(String foodId) {
        FoodEntity foodEntity = foodMapper.doFindObjectById(foodId);
        if (foodEntity == null) {
            return "记录不存在";
        }
        //删除图片
        String foodPic = foodEntity.getFoodPic();
        String prePicPath = ROOT_PATH + foodPic;
        File preFile = new File(prePicPath);
        if (preFile.exists()) {
            preFile.delete();
        }

        int a = foodMapper.doDeleteObject(foodId);
        if (a > 0) {
            return "ok";
        }
        return "删除失败，没有权限或记录不存在";
    }

    @Override
    public List<String> getSceneryOptions(String cityName) {
        List<String> result = foodMapper.getSceneryOptions(cityName);
        return result;
    }

    @Override
    public String doSaveObject(String sceneryName, String foodName, String foodDesc, double foodPrice, MultipartFile foodPicFile) throws IOException {
        String foodPicPath = null;
        if (foodPicFile != null) {
            String fileName = foodPicFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String s = FilePathUtil.uploadFile(ROOT_PATH + CHILD_PATH, fileType);
            System.out.println(s);
            File file = new File(ROOT_PATH + CHILD_PATH + s);
            System.out.println(CHILD_PATH + s);
            foodPicPath = CHILD_PATH + s;
            foodPicFile.transferTo(file);
        }
        String sceneryId = foodMapper.getSceneryId(sceneryName);
        System.out.println(sceneryId);
        FoodEntity foodEntity = new FoodEntity();
        String foodId = MD5HashUtils.getRandomUUID();
        foodEntity.setFoodId(foodId);
        foodEntity.setSceneryId(sceneryId);
        foodEntity.setFoodName(foodName);
        foodEntity.setFoodPrice(foodPrice);
        foodEntity.setFoodPic(foodPicPath);
        foodEntity.setFoodDesc(foodDesc);

        int a = foodMapper.doSaveObject(foodEntity);
        if (a > 0) {
            return "ok";
        }
        return null;
    }

    @Override
    public PageObject getFoodListBySceneryId(Integer pageCurrent, Integer pageSize, String sceneryId) {
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
        pageObject.setTotal(foodMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = foodMapper.getFoodListBySceneryId(pageCurrent,sceneryId, new RowBounds(pageCurrent - 1, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }


    public int getPageCount(int pageSize) {
        return foodMapper.getPageCount() / pageSize + 1;
    }
}
