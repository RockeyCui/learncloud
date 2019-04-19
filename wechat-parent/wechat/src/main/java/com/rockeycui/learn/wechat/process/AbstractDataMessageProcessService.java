package com.rockeycui.learn.wechat.process;

import com.rockeycui.learn.wechat.api.dto.req.WxReq;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 崔石磊
 */
@Slf4j
public abstract class AbstractDataMessageProcessService implements DataMessageProcessService {
    @Override
    public String invokeMethod(HttpServletRequest request, HttpServletResponse response, WxReq req) {
        return invoke(request, response, req);
    }

    /**
     * 实际执行方法
     *
     * @param request
     * @param response
     * @param req
     * @return java.lang.String
     * @author cuishilei
     * @date 2018/9/25
     */
    public abstract String invoke(HttpServletRequest request, HttpServletResponse response, WxReq req);
}
