package com.rockeycui.learn.wechat.service;


import com.rockeycui.learn.common.bean.ResultInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author RockeyCui
 */
public interface WxService {
    /**
     * 接入微信
     *
     * @param request  1
     * @param response 1
     * @author cuishilei
     * @date 2018/10/24
     */
    void accessWx(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取token
     *
     * @return java.lang.String
     * @author cuishilei
     * @date 2018/8/2
     */
    ResultInfo getAccessToken();

    /**
     * 创建菜单
     *
     * @param json 数据
     * @return com.kangkang.shop.bean.ResultInfo
     * @author cuishilei
     * @date 2018/9/3
     */
    ResultInfo createMenu(String json);

    /**
     * 获取菜单
     *
     * @return com.kangkang.shop.bean.ResultInfo<java.lang.String>
     * @author cuishilei
     * @date 2018/9/20
     */
    ResultInfo<String> getMenu();


    /**
     * 获取微信用户授权 token
     *
     * @param code 前置 code
     * @return com.kangkang.shop.bean.ResultInfo
     * @author cuishilei
     * @date 2018/10/24
     */
    ResultInfo getAccessToken(String code);
}
