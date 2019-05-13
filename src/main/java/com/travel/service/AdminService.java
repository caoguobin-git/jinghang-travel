/***********************************************
 * File Name: AdminService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 11:02
 ***********************************************/
package com.travel.service;

import com.travel.common.entity.AdminEntity;
import com.travel.common.vo.PageObject;

public interface AdminService {
    String doUserRegister(AdminEntity adminEntity);

    String login(String username, String password, String ADMIN_LOGIN_TYPE);

    PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize);

    void doValidById(String userId, Integer valid);

    AdminEntity findByUsername(String username);


}
