package com.rockeycui.learn.common.config;

import com.rockeycui.learn.common.bean.GatewayConstant;
import com.rockeycui.learn.common.bean.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Configuration
public class FilterConfig {

    @Bean
    public Filter logTrackingFilter(){
        return (servletRequest, servletResponse, filterChain) -> {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String logTrackId = httpServletRequest.getHeader(GatewayConstant.LOG_TRACK_ID);
            //放置关联id
            MDC.put(GatewayConstant.LOG_TRACK_ID, logTrackId);
            //缓存关联id
            UserContextHolder.getContext().setLogTrackId(logTrackId);
            log.debug("日志关联 id: {}", UserContextHolder.getContext().getLogTrackId());
            filterChain.doFilter(httpServletRequest, servletResponse);
        };
    }
}
