package com.rockeycui.learn.usercenter.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @author RockeyCui
 */
@Slf4j
public class LogTrackInterceptor implements HandlerInterceptor {

    private static final String REQ_UID = "ReqUID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //日志追踪
        String reqUid = request.getHeader(REQ_UID);
        if (StringUtils.isBlank(reqUid)) {
            reqUid = request.getServletPath() + "-" + UUID.randomUUID().toString();
        }
        MDC.put(REQ_UID, reqUid);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.remove(REQ_UID);
    }
}
