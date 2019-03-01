package com.rockeycui.learn.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final FilterUtil filterUtil;

    @Autowired
    public TrackingFilter(FilterUtil filterUtil) {
        this.filterUtil = filterUtil;
    }


    @Override
    public String filterType() {
        //告诉 Zuul 过滤器是一个前置，路由或后置过滤器。
        return FilterUtil.PRE_FILTER_TYPE;
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
    public Object run() throws ZuulException {
        if (isCorrelationIdPresent()) {
            log.debug("tmx-correlation-id found in tracking filter: {}.", filterUtil.getCorrelationId());
        } else {
            filterUtil.setCorrelationId(generateCorrelationId());
            log.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtil.getCorrelationId());
        }
        RequestContext ctx = RequestContext.getCurrentContext();

        return null;
    }


    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    private boolean isCorrelationIdPresent() {
        return filterUtil.getCorrelationId() != null;
    }
}
