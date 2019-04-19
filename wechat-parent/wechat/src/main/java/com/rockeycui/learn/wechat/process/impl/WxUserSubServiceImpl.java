package com.rockeycui.learn.wechat.process.impl;

import com.rockeycui.learn.wechat.api.dto.req.WxReq;
import com.rockeycui.learn.wechat.process.AbstractDataMessageProcessService;
import com.rockeycui.learn.wechat.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 名称： 关注微信时的动作<br>
 * 作者: 崔石垒<br>
 * 日期: 2017/06/22<br>
 * <p/>
 * 修改记录：14:11 新建 A
 *
 * @author RockeyCui
 */
@Slf4j
@Service
public class WxUserSubServiceImpl extends AbstractDataMessageProcessService {
    public static final String TRAN_CODE = "subscribe";
    @Resource
    private WxUserService userServiceImpl;

    @Override
    public String invoke(HttpServletRequest request, HttpServletResponse response, WxReq req) {
        return userServiceImpl.returnTxtMsg(req);
    }

    @Override
    public String getTranCode() {
        return TRAN_CODE;
    }
}
