package com.rockeycui.learn.wechat.api.dto.res;

import lombok.Data;

/**
 * WxOautnAccessTokenRes
 *
 * @author cuishilei
 * @date 2018/9/21
 */
@Data
public class WxOautnAccessTokenRes extends WxBaseResBean {

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;

}
