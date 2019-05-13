///***********************************************
// * File Name: WebInterceptor
// * Author: caoguobin
// * mail: caoguobin@live.com
// * Created Time: 19 03 2019 15:28
// ***********************************************/
//
//package com.travel.interceptor;
//
//import com.google.common.base.Strings;
//import com.travel.common.util.CookieUtils;
//import org.apache.log4j.Logger;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//public class WebInterceptor implements HandlerInterceptor {
//
//
//    Logger logger = Logger.getLogger(WebInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        /**
//         * 判断cookie和redis中是否存在数据，如果一方不存在，则登录已失效，需跳转登录页面进行重新登录
//         *
//         * 进行参数完整性校验！！！排序并验证sign值
//         */
//        String cookieValue= CookieUtils.getCookieValue(request, "userToken");
//
//        if (Strings.isNullOrEmpty(cookieValue)){
//            response.setContentType("text/html;charset=utf-8");
//            response.setCharacterEncoding("UTF-8");
//            PrintWriter writer = response.getWriter();
//            writer.print("尚未登录，请登录后重试");
//            //设置3s跳转到登录页面
//            response.setHeader("refresh", "3;url=/login.do");
//
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
