package com.travel.mapper;

import com.travel.common.entity.StrategyEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface StrategyMapper {
    int getPageCount(String sceneryName);

    List<Object> doFindPageObjects(@Param("sceneryName") String sceneryName, RowBounds rowBounds);

    StrategyEntity doFindObjectById(String id);

    int getCityId(String cityName);

    int doSaveObject(StrategyEntity strategyEntity);

    int doUpdateObject(StrategyEntity strategyEntity);

    int doDeleteObject(String strategyId);

    List<Object> getStrategysByCityName(Integer pageCurrent, @Param("cityName") String cityName, RowBounds rowBounds);

    int getPageCountByCityId(@Param("cityId") int cityId);

    int getCityIdByCityPY(@Param("cityName") String cityName);

    int getTotalCount(@Param("sceneryName") String sceneryName);

    String getSceneryId(@Param("sceneryName") String sceneryName);

    List<String> getSceneryOptions(String cityName);
}
