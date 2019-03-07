package com.rockeycui.learn.gateway.filters;

import com.netflix.zuul.context.RequestContext;
import com.rockeycui.learn.common.bean.GatewayConstant;

/**
 * @author cuishilei
 * @date 2019/1/2
 */
public class FilterUtil {
    public static String getLogTrackId() {
        RequestContext ctx = RequestContext.getCurrentContext();

        if (ctx.getRequest().getHeader(GatewayConstant.LOG_TRACK_ID) != null) {
            return ctx.getRequest().getHeader(GatewayConstant.LOG_TRACK_ID);
        } else {
            return ctx.getZuulRequestHeaders().get(GatewayConstant.LOG_TRACK_ID);
        }
    }

    public static void setLogTrackId(String correlationId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(GatewayConstant.LOG_TRACK_ID, correlationId);
    }
}
