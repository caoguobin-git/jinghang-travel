/***********************************************
 * File Name: AdminMapper
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 9:59
 ***********************************************/
package com.travel.mapper;

import com.travel.common.entity.AdminEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface AdminMapper {
    AdminEntity findByUsername(String username);

    int register(AdminEntity adminEntity);

    int getPageCount();

    List<Object> doFindPageObjects(RowBounds rowBounds);

    int doValidById(@Param("userId") String userId, @Param("valid") Integer valid);

}
