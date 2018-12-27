package com.cpscs.omsadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cpscs.common.intercepter.AuthIntercepter;

@Configuration
public class AuthConfig extends WebMvcConfigurerAdapter {
    @Bean
    public AuthIntercepter authIntercepter() {
        return new AuthIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(authIntercepter());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/test**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
}
