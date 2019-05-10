/***********************************************
 * File Name: UserServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 09 05 2019 11:03
 ***********************************************/

package com.travel.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.common.entity.UserEntity;
import com.travel.common.util.MD5HashUtils;
import com.travel.common.vo.PageObject;
import com.travel.mapper.UserMapper;
import com.travel.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String register(UserEntity userEntity) {
        UserEntity byUsername = userMapper.findByUsername(userEntity.getUsername());
        if (byUsername!=null){
            return "用户已存在！";
        }
        String userId = MD5HashUtils.getRandomUUID();
        userEntity.setUserId(userId);

        /**
         * 用户密码加密，采用Md5Hash 迭代三轮
         */
        String password = userEntity.getPassword();
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        password = new Md5Hash(password, salt, 3).toString();

        userEntity.setPassword(password);
        userEntity.setSalt(salt);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        userEntity.setCreateTime(timestamp);
        userEntity.setModifiedTime(timestamp);
        System.out.println(userEntity);
        int result = userMapper.register(userEntity);
        if (result>0) {
            return "ok";
        }
        return "注册失败！";
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        UserEntity userEntity = userMapper.findByUsername(username);
        Map<String,Object> result=new HashMap<>();
        if (userEntity==null){
            result.put("code", "user not exists");
            result.put("message", "用户不存在");
            return result;
        }
        String hashedPasswordFromSql = userEntity.getPassword();
        String salt = userEntity.getSalt();
        String hashedPassword = new Md5Hash(password, salt, 3).toString();
        if (hashedPassword.equals(hashedPasswordFromSql)){
            result.put("code", "ok");
                result.put("message", userEntity);

            return result;
        }else {
            result.put("code", "wrong password or username");
            result.put("message", "用户名或密码错误");
            return result;
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
        pageObject.setTotal(userMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = userMapper.doFindPageObjects(pageCurrent,new RowBounds(pageCurrent-1, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public void doValidById(String userId, Integer valid) {
        userMapper.doValidById(userId,valid);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public int getPageCount(int pageSize) {
        return userMapper.getPageCount() / pageSize + 1;
    }
}
