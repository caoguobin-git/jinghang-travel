/***********************************************
 * File Name: UserService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 13 05 2019 14:05
 ***********************************************/
package com.travel.service;

import com.travel.common.entity.UserEntity;

public interface UserService {
    String register(UserEntity userEntity);

    String login(String username, String password, String userLoginType);

    UserEntity findByUsername(String username);
}
