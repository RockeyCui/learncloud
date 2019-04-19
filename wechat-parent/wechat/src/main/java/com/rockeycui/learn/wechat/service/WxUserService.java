package com.rockeycui.learn.wechat.service;


import com.rockeycui.learn.wechat.api.dto.req.WxReq;

/**
 * @author RockeyCui
 */
public interface WxUserService {
    /***
     * 绑定学员信息
     * @param req 微信请求
     * @return 1
     */
    String returnBindMsg(WxReq req);

    /***
     * 更新绑定学员信息
     * @param req 微信请求
     * @return 1
     */
    String returnBindUpMsg(WxReq req);

    /***
     * 默认信息
     * @param req 微信请求
     * @return 1
     */
    String returnTxtMsg(WxReq req);
}
