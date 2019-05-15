/***********************************************
 * File Name: FoodService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:10
 ***********************************************/
package com.travel.service;

import com.travel.common.entity.FoodEntity;
import com.travel.common.vo.PageObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FoodService {
    PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize);

    FoodEntity doFindObjectById(String id);

    String doUpdateObject(String foodId, String sceneryName, String foodName, String foodDesc, double foodPrice, MultipartFile foodPicFile) throws IOException;

    String doDeleteObject(String foodId);

    List<String> getSceneryOptions(String cityName);

    String doSaveObject(String sceneryName, String foodName, String foodDesc, double foodPrice, MultipartFile foodPicFile) throws IOException;

    PageObject getFoodListBySceneryId(Integer pageCurrent, Integer pageSize, String sceneryId);
}
