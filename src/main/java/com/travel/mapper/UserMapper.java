/***********************************************
 * File Name: UserMapper
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 9:59
 ***********************************************/
package com.travel.mapper;

import com.travel.common.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserMapper {
    UserEntity findByUsername(String username);

    int register(UserEntity userEntity);

    int getPageCount();

    List<Object> doFindPageObjects(Integer pageCurrent, RowBounds rowBounds);

    void doValidById(@Param("userId") String userId,@Param("valid") Integer valid);

    List test();
}
