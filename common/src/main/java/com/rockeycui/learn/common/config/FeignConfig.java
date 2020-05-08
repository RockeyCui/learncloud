package com.rockeycui.learn.common.config;

import com.rockeycui.learn.common.bean.GatewayConstant;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FeignConfig {

    /***
     * feign 请求拦截器
     * @return RequestInterceptor
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            try {
                String reqUid = MDC.get(GatewayConstant.LOG_TRACK_ID);
                if (StringUtils.isNotBlank(reqUid)) {
                    requestTemplate.header(GatewayConstant.LOG_TRACK_ID, reqUid);
                }
            } catch (Exception e) {
                log.error("feign reqUid error ", e);
            }
        };
    }

}
