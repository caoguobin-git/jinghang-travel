/***********************************************
 * File Name: TicketMapper
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 10:53
 ***********************************************/
package com.travel.mapper;

import com.travel.common.entity.TicketEntity;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TicketMapper {
    int getPageCount();

    List<Object> doFindPageObjects(Integer pageCurrent, RowBounds rowBounds);

    TicketEntity doFindObjectById(String id);

    List<String> getSceneryOptions(String cityName);

    int doSaveObject(TicketEntity ticketEntity);

    String getSceneryId(String sceneryName);

    int doDeleteObject(String ticketId);

    int doUpdateObject(TicketEntity ticketEntity1);
}
