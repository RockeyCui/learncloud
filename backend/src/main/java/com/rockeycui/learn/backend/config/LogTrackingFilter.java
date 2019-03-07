package com.rockeycui.learn.backend.config;

import com.rockeycui.learn.common.bean.GatewayConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class LogTrackingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader(GatewayConstant.LOG_TRACK_ID);
        //放置关联id
        MDC.put(GatewayConstant.LOG_TRACK_ID, header);
        //缓存关联id
        UserContextHolder.getContext().setLogTrackId(header);
        log.debug("日志关联 id: {}", UserContextHolder.getContext().getLogTrackId());
        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}