/***********************************************
 * File Name: UserService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 11:02
 ***********************************************/
package com.travel.service;

import com.travel.common.entity.UserEntity;
import com.travel.common.vo.PageObject;

import java.util.List;

public interface UserService {
    String doUserRegister(UserEntity userEntity);

    String login(String username, String password);

    PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize);

    void doValidById(String userId, Integer valid);

    UserEntity findByUsername(String username);

    List test();
}
