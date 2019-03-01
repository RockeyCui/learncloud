package com.rockeycui.learn.usercenter.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private static final String REQ_UID = "ReqUID";

    @Override
    public void apply(RequestTemplate template) {

        try {
            String reqUid = MDC.get(REQ_UID);
            if (StringUtils.isNotBlank(reqUid)) {
                template.header(REQ_UID, reqUid);
            }
        } catch (Exception e) {
            log.error("feign requid error ", e);
        }
    }
}
