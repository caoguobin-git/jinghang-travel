/***********************************************
 * File Name: MyModularRealmAuthenticator
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 13 05 2019 15:49
 ***********************************************/

package com.travel.realm;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;

public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {
    private static final Logger logger = LoggerFactory.getLogger(MyModularRealmAuthenticator.class);
    private Map<String, Object> definedRealms;


    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("执行到验证登录方法啦 啊啊啊啊啊 ");
        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的CustomizedToken
        MyToken token = (MyToken) authenticationToken;
        // 找到当前登录人的登录类型
        String loginType = token.getLoginType();
        // 所有Realm
        Collection<Realm> realms= getRealms();

        // 找到登录类型对应的指定Realm
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().toLowerCase().contains(loginType))
                typeRealms.add(realm);
        }

        System.out.println("h**************************************");
        // 判断是单Realm还是多Realm
        if (typeRealms.size() == 1)
            return doSingleRealmAuthentication(typeRealms.iterator().next(), token);
        else
            return doMultiRealmAuthentication(typeRealms, token);
    }


    /**
     * 判断realm是否为空
     */
    @Override
    protected void assertRealmsConfigured() throws IllegalStateException {
        this.definedRealms = this.getDefinedRealms();
        if (CollectionUtils.isEmpty(this.definedRealms)) {
            throw new ShiroException("值传递错误!");
        }
    }

    public Map<String, Object> getDefinedRealms() {
        return definedRealms;
    }

    public void setDefinedRealms(Map<String, Object> definedRealms) {
        this.definedRealms = definedRealms;
    }
}

