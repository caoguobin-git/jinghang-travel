package com.travel.mapper;

import com.travel.common.entity.SceneryEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SceneryMapper {
    int getPageCount();

    List<Object> doFindPageObjects(Integer pageCurrent, RowBounds rowBounds);

    SceneryEntity doFindObjectById(String id);

    int getCityId(String cityName);

    int doSaveObject(SceneryEntity sceneryEntity);

    int doUpdateObject(SceneryEntity sceneryEntity);

    int doDeleteObject(String sceneryId);

    List<Object> getScenerysByCityName(Integer pageCurrent, @Param("cityName") String cityName, RowBounds rowBounds);

    int getPageCountByCityId(@Param("cityId") int cityId);

    int getCityIdByCityPY(@Param("cityName") String cityName);
}
