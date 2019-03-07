package com.rockeycui.learn.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.rockeycui.learn.common.bean.GatewayConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author cuishilei
 * @date 2019/1/2
 */
@Slf4j
@Component
public class TrackingFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    @Override
    public String filterType() {
        //告诉 Zuul 过滤器是一个前置，路由或后置过滤器。
        // pre
        return GatewayConstant.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        //返回一个整型值表示 Zuul 应该通过不同的过滤器类型发送请求的顺序。
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        //返回一个布尔值，表示过滤器是否应该处于活动状态
        return SHOULD_FILTER;
    }

    @Override
    public Object run() {
        if (FilterUtil.getLogTrackId() != null) {
            log.debug("发现日志关联 id : {}.", FilterUtil.getLogTrackId());
        } else {
            FilterUtil.setLogTrackId(java.util.UUID.randomUUID().toString());
            log.debug("生成日志关联 id : {}.", FilterUtil.getLogTrackId());
        }
        return null;
    }
}
