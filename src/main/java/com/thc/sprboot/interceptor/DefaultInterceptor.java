package com.thc.sprboot.interceptor;

import com.thc.sprboot.mapper.UserMapper;
import com.thc.sprboot.repository.UserRepository;
import com.thc.sprboot.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

public class DefaultInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TokenFactory tokenFactory;
    public DefaultInterceptor(TokenFactory tokenFactory){
        this.tokenFactory = tokenFactory;
    }

    //컨트롤러 진입 전에 호출되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle / request [{}]", request);
        String accessToken = request.getHeader("AccessToken");

        logger.info("accessToken : [{}]", accessToken);

        if(accessToken != null && !accessToken.isEmpty()){
            Long userId = tokenFactory.verifyAccessToken(accessToken);
            request.setAttribute("reqUserId", userId);
        }
        //request.setAttribute("test", "value1");
        /*String accesstoken = request.getHeader("Authorization");
        if(accesstoken != null){
            Long userId = tokenFactory.validateAccessToken(accesstoken);
            if(userId != null && userId > 0) {
                request.setAttribute("reqUserId", userId);
            }
        }*/
        return true;
    }

    //컨트롤러 실행 후에 호출되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle / request [{}]", request);
        System.out.println("postHandle afterCtrl :" + request.getAttribute("afterCtrl"));
    }

    //모든것을 마친 후 실행되는 메서드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion / request [{}]", request);
        System.out.println("afterCompletion afterCtrl :" + request.getAttribute("afterCtrl"));
    }

}
