/***********************************************
 * File Name: HotelMapper
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 10:53
 ***********************************************/
package com.travel.mapper;

import com.travel.common.entity.HotelEntity;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface HotelMapper {
    int getPageCount();

    List<Object> doFindPageObjects(Integer pageCurrent, RowBounds rowBounds);

    HotelEntity doFindObjectById(String id);

    List<String> getSceneryOptions(String cityName);

    int doSaveObject(HotelEntity hotelEntity);

    String getSceneryId(String sceneryName);

    int doDeleteObject(String hotelId);

    int doUpdateObject(HotelEntity hotelEntity1);
}
