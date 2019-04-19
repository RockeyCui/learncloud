package com.rockeycui.learn.wechat.api.dto.res;

import lombok.Data;

import java.util.List;

/**
 * WxOautnAccessTokenRes
 *
 * @author cuishilei
 * @date 2018/9/21
 */
@Data
public class WxOauthUserInfoRes extends WxBaseResBean {
    private String openid;
    private String nickname;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<String> privilege;
}
