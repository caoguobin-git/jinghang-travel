/***********************************************
 * File Name: UserServiceImpl
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 13 05 2019 14:06
 ***********************************************/

package com.travel.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.common.entity.UserEntity;
import com.travel.common.util.MD5HashUtils;
import com.travel.mapper.UserMapper;
import com.travel.realm.MyToken;
import com.travel.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

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
        String password1 = userEntity.getPassword();
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        password = new Md5Hash(password, salt, 3).toString();

        userEntity.setPassword(password);
        userEntity.setSalt(salt);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        userEntity.setRegTime(timestamp);
        userEntity.setLoginTime(timestamp);
        System.out.println(userEntity);
        int result = userMapper.register(userEntity);
        if (result>0) {
//            login(adminEntity.getUsername(), password1);
            UserEntity byUsername1 = userMapper.findByUsername(userEntity.getUsername());
            try {
                return new ObjectMapper().writeValueAsString(byUsername1);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return "注册失败！";
    }

    @Override
    public String login(String username, String password, String userLoginType) {
        UserEntity userEntity = userMapper.findByUsername(username);
        MyToken token = new MyToken(username, password,userLoginType);
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
        if (userEntity.isIfuse()) {
            String s = null;
            try {
                s = new ObjectMapper().writeValueAsString(userEntity);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return s;
        }else {
            return "用户已被禁用";
        }
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
