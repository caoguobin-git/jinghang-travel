/***********************************************
 * File Name: AdminServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 11:03
 ***********************************************/

package com.travel.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.common.entity.AdminEntity;
import com.travel.common.util.MD5HashUtils;
import com.travel.common.vo.PageObject;
import com.travel.mapper.AdminMapper;
import com.travel.realm.MyToken;
import com.travel.service.AdminService;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String doUserRegister(AdminEntity adminEntity) {
        AdminEntity byUsername = adminMapper.findByUsername(adminEntity.getUsername());
        if (byUsername!=null){
            return "用户已存在！";
        }
        String userId = MD5HashUtils.getRandomUUID();
        adminEntity.setUserId(userId);

        /**
         * 用户密码加密，采用Md5Hash 迭代三轮
         */
        String password = adminEntity.getPassword();
        String password1 = adminEntity.getPassword();
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        password = new Md5Hash(password, salt, 3).toString();

        adminEntity.setPassword(password);
        adminEntity.setSalt(salt);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        adminEntity.setCreateTime(timestamp);
        adminEntity.setModifiedTime(timestamp);
        System.out.println(adminEntity);
        int result = adminMapper.register(adminEntity);
        if (result>0) {
            login(adminEntity.getUsername(), password1, "admin");
            AdminEntity byUsername1 = adminMapper.findByUsername(adminEntity.getUsername());
            try {
                return new ObjectMapper().writeValueAsString(byUsername1);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return "注册失败！";
    }

    @Override
    public String login(String username, String password, String ADMIN_LOGIN_TYPE) {
        AdminEntity adminEntity = adminMapper.findByUsername(username);
        MyToken token = new MyToken(username, password,ADMIN_LOGIN_TYPE);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("用户登录模块");
        try {
            subject.login(token);
        }catch (Exception e){
            if(e instanceof IncorrectCredentialsException){
                System.out.println("密码错误");
                return "密码错误";

            }else if(e instanceof UnknownAccountException){
                System.out.println("该用户名不存在");
                return "用户不存在";

            }
        }
        if (adminEntity.isValid()) {
            String s = null;
            try {
                s = new ObjectMapper().writeValueAsString(adminEntity);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return s;
        }else {
            return "用户已被禁用";
        }
    }

    @Override
    public PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize) {
        if (pageSize == null) {
            pageSize = 20;
        }
        int pageCount = getPageCount(pageSize);
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(adminMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = adminMapper.doFindPageObjects(pageCurrent,new RowBounds(pageCurrent-1, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public void doValidById(String userId, Integer valid) {
        adminMapper.doValidById(userId,valid);
    }

    @Override
    public AdminEntity findByUsername(String username) {
        return adminMapper.findByUsername(username);
    }

    public int getPageCount(int pageSize) {
        return adminMapper.getPageCount() / pageSize + 1;
    }
}
