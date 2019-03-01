package com.rockeycui.learn.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author RockeyCui
 */
@Configuration
public class LogTrackInterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public LogTrackInterceptor logTrackInterceptor() {
        return new LogTrackInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器
        InterceptorRegistration ir = registry.addInterceptor(logTrackInterceptor());
        // 拦截
        ir.addPathPatterns("/**");
    }
}
