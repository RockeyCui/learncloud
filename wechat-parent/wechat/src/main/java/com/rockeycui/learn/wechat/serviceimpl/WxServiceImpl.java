package com.rockeycui.learn.wechat.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rockeycui.learn.common.bean.ResultInfo;
import com.rockeycui.learn.common.util.DateTimeUtil;
import com.rockeycui.learn.wechat.domain.WxAccessToken;
import com.rockeycui.learn.wechat.domain.WxAccessTokenExample;
import com.rockeycui.learn.wechat.api.dto.res.WxOauthUserInfoRes;
import com.rockeycui.learn.wechat.api.dto.res.WxOautnAccessTokenRes;
import com.rockeycui.learn.wechat.mapper.WxAccessTokenMapper;
import com.rockeycui.learn.wechat.service.WxService;
import com.rockeycui.learn.wechat.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author RockeyCui
 */
@Slf4j
@Service
public class WxServiceImpl implements WxService {
    @Value("${wechat.app-id}")
    private String wxAppId;

    @Value("${wechat.app-secret}")
    private String wxAppSecret;

    @Value("${wechat.app-token}")
    private String wxToken;

    @Value("${wechat.access-token-url}")
    private String wxAccessTokenUrl;

    @Value("${wechat.create-menu-url}")
    private String wxCreateMenuUrl;

    @Value("${wechat.get-menu-url}")
    private String wxGetMenuUrl;

    @Value("${wechat.oauth2-token-url}")
    private String wxOauth2TokenUrl;

    @Value("${wechat.oauth2-user-info-url}")
    private String wxOauth2UserInfoUrl;

    @Resource
    private WxAccessTokenMapper wxAccessTokenMapper;

    @Override
    public void accessWx(HttpServletRequest request, HttpServletResponse response) {
        log.info("开始接入公众号");
        try {
            // 微信加密签名（token、timestamp、nonce。）
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            // 进行字典序排序
            String[] params = new String[]{wxToken, timestamp, nonce};
            Arrays.sort(params);
            log.info("请求参数.[{}]", JSON.toJSONString(params));
            // 进行SHA-1加密
            String clearText = params[0] + params[1] + params[2];
            String algorithm = "SHA-1";
            String sign = new String(Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));
            // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            if (signature.equals(sign)) {
                response.getWriter().print(echostr);
            }
        } catch (Exception e) {
            log.error("微信接入失败 ", e);
        }
        log.info("结束接入公众号");
    }

    @Override
    public ResultInfo getAccessToken() {
        String res = HttpUtil.doPost(wxAccessTokenUrl);
        JSONObject accessTokenObject = JSONObject.parseObject(res);
        String accessToken = accessTokenObject.getString("access_token");
        log.info("获取微信TOKEN成功 [{}]", accessToken);
        //删除没用的
        WxAccessTokenExample example = new WxAccessTokenExample();
        example.createCriteria().andAccessTokenNotEqualTo(accessToken);
        wxAccessTokenMapper.deleteByExample(example);
        //插入新增的
        WxAccessToken wxAccessToken = new WxAccessToken();
        wxAccessToken.setAccessToken(accessToken);
        wxAccessToken.setUpdateTime(DateTimeUtil.getNowDate());
        wxAccessTokenMapper.insertSelective(wxAccessToken);
        return new ResultInfo();
    }

    @Override
    public ResultInfo createMenu(String json) {
        ResultInfo resultInfo = new ResultInfo();

        WxAccessTokenExample example = new WxAccessTokenExample();
        example.createCriteria().andAccessTokenIsNotNull();
        List<WxAccessToken> wxAccessTokens = wxAccessTokenMapper.selectByExample(example);

        String url = wxCreateMenuUrl + wxAccessTokens.get(0).getAccessToken();
        String res = HttpUtil.doPostContent(json, url);
        JSONObject result = JSONObject.parseObject(res);
        log.info("微信返回数据：" + result.toString());
        if (!result.getInteger("errcode").equals(0)) {
            resultInfo = new ResultInfo(111, result.getString("errMsg"));
        }
        return resultInfo;
    }

    @Override
    public ResultInfo<String> getMenu() {
        ResultInfo<String> resultInfo = new ResultInfo<>();

        WxAccessTokenExample example = new WxAccessTokenExample();
        example.createCriteria().andAccessTokenIsNotNull();
        List<WxAccessToken> wxAccessTokens = wxAccessTokenMapper.selectByExample(example);
        String url = wxGetMenuUrl + wxAccessTokens.get(0).getAccessToken();
        String res = HttpUtil.doGet(url);
        resultInfo.setData(res);
        return resultInfo;
    }

    @Override
    public ResultInfo getAccessToken(String code) {
        ResultInfo<WxOauthUserInfoRes> resResultInfo = new ResultInfo<>();

        String resStr = HttpUtil.doPost(wxOauth2TokenUrl.replace("CODE", code));
        log.info(" token 请求结果 [{}]", resStr);
        WxOautnAccessTokenRes res = JSON.parseObject(resStr, WxOautnAccessTokenRes.class);
        if (res.getAccess_token() != null) {
            String userInfoUrl = MessageFormat.format(wxOauth2UserInfoUrl, res.getAccess_token(), res.getOpenid());
            String userInfoResStr = HttpUtil.doGet(userInfoUrl);
            log.info(" info 请求结果 [{}]", resStr);
            WxOauthUserInfoRes userInfoRes = JSON.parseObject(userInfoResStr, WxOauthUserInfoRes.class);
            resResultInfo.setData(userInfoRes);
        }
        return resResultInfo;
    }
}
