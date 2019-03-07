package com.rockeycui.learn.backend.config;

import com.rockeycui.learn.common.bean.GatewayConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpServletRequest request = attrs.getRequest();
            String logTrackId = request.getHeader(GatewayConstant.LOG_TRACK_ID);
            log.info("feign 日志关联 id : {}", logTrackId);
            //传递关联id
            template.header(GatewayConstant.LOG_TRACK_ID, logTrackId);
        }
    }
}
