/***********************************************
 * File Name: UserMapper
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 13 05 2019 14:07
 ***********************************************/
package com.travel.mapper;

import com.travel.common.entity.UserEntity;

public interface UserMapper {
    int register(UserEntity userEntity);

    UserEntity findByUsername(String username);
}
