package com.rockeycui.learn.wechat.process;


import com.rockeycui.learn.wechat.api.dto.req.WxReq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DataMessageProcessService {

    /**
     * 执行方法
     *
     * @param request
     * @param response
     * @param req
     * @return java.lang.String
     * @author cuishilei
     * @date 2018/9/25
     */
    String invokeMethod(HttpServletRequest request, HttpServletResponse response, WxReq req);

    /**
     * 服务标记
     *
     * @return java.lang.String
     * @author cuishilei
     * @date 2018/9/25
     */
    String getTranCode();
}
