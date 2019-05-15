/***********************************************
 * File Name: FoodMapper
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 10:53
 ***********************************************/
package com.travel.mapper;

import com.travel.common.entity.FoodEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface FoodMapper {
    int getPageCount();

    List<Object> doFindPageObjects(Integer pageCurrent, RowBounds rowBounds);

    FoodEntity doFindObjectById(String id);

    List<String> getSceneryOptions(String cityName);

    int doSaveObject(FoodEntity foodEntity);

    String getSceneryId(String sceneryName);

    int doDeleteObject(String foodId);

    int doUpdateObject(FoodEntity foodEntity1);

    List<Object> getFoodListBySceneryId(Integer pageCurrent, @Param("sceneryId") String sceneryId, RowBounds rowBounds);
}
