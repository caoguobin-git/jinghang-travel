/***********************************************
 * File Name: LogoutFilter
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 10 05 2019 10:41
 ***********************************************/

package com.travel.filter;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Service
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //在这里执行退出系统前需要清空的数据
        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        try {
            subject.logout();
        } catch (SessionException ise) {
            ise.printStackTrace();
        }
        issueRedirect(request, response, redirectUrl);
//返回false表示不执行后续的过滤器，直接返回跳转到登录页面
        return false;
    }
}
