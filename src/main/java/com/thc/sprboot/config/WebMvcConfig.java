package com.thc.sprboot.config;

import com.thc.sprboot.interceptor.DefaultInterceptor;
import com.thc.sprboot.util.TokenFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final TokenFactory tokenFactory;
    public WebMvcConfig(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    //인터셉터 설정을 위함
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultInterceptor(tokenFactory))
                .addPathPatterns("/api/**") //인터셉터가 실행되야 하는 url 패턴 설정
                .excludePathPatterns("/resources/**", "/api/auth/accessToken", "/api/user/signup", "/api/user/login"); //인터셉터가 실행되지 않아야 하는 url 패턴
    }

}