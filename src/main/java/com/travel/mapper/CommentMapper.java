/***********************************************
 * File Name: CommentMapper
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 10:53
 ***********************************************/
package com.travel.mapper;

import com.travel.common.entity.CommentEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentMapper {
    int getPageCount();

    List<Object> doFindPageObjects(Integer pageCurrent, RowBounds rowBounds);

    CommentEntity doFindObjectById(String id);

    List<String> getSceneryOptions(String cityName);

    int doSaveObject(CommentEntity commentEntity);

    String getSceneryId(String sceneryName);

    int doDeleteObject(String commentId);

    int doUpdateObject(CommentEntity commentEntity1);

    List<Object> getCommentListBySceneryId(@Param("sceneryId") String sceneryId, RowBounds rowBounds);

    int getPageCountBySceneryId(@Param("sceneryId") String sceneryId);
}
