package com.rockeycui.learn.wechat.rest;

import com.rockeycui.learn.common.bean.ResultInfo;
import com.rockeycui.learn.wechat.api.constant.WxConstants;
import com.rockeycui.learn.wechat.api.dto.req.WxReq;
import com.rockeycui.learn.wechat.api.error.WxAppException;
import com.rockeycui.learn.wechat.api.error.WxErrorCode;
import com.rockeycui.learn.wechat.process.DataMessageProcessService;
import com.rockeycui.learn.wechat.process.DataMessageProcessorMap;
import com.rockeycui.learn.wechat.service.WxService;
import com.rockeycui.learn.wechat.util.XMLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * GoodController
 *
 * @author cuishilei
 * @date 2018/5/23
 */
@Slf4j
@RestController
public class WxController {
    @Resource
    private WxService wxService;

    @Resource
    private DataMessageProcessorMap dataMessageProcessorMap;

    @GetMapping("/wx-req")
    public void accessWx(HttpServletRequest request, HttpServletResponse response) {
        wxService.accessWx(request, response);
    }

    /**
     * 处理请求
     *
     * @throws Exception
     */
    @PostMapping("/wx-req")
    public void acceptReq(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("接收到微信交易请求");
        long begin = System.currentTimeMillis();
        DataMessageProcessService processService;
        String result = "";
        WxReq req = new WxReq();
        try {
            Map<String, String> reqMap = XMLUtil.asMap(request);
            log.info("收到消息类型为：{}", reqMap.get(req.getMsgType()));
            log.info("收到的OpenID为：{}", reqMap.get(WxConstants.FromUserName));
            //2.转为bean对象
            req.setFromUserName(reqMap.get(WxConstants.FromUserName));
            req.setToUserName(reqMap.get(WxConstants.ToUserName));
            req.setMsgType(reqMap.get(WxConstants.MsgType));
            req.setCreateTime(reqMap.get(WxConstants.CreateTime));
            req.setEvent(reqMap.get(WxConstants.Event));
            req.setContent(reqMap.get(WxConstants.Content));
            req.setPicUrl(reqMap.get(WxConstants.PicUrl));
            req.setMediaId(reqMap.get(WxConstants.MediaId));
            //3.获得业务实现类，推送
            if ("event".equals(req.getMsgType())) {
                processService = dataMessageProcessorMap.getService(req.getEvent());
            } else {
                processService = dataMessageProcessorMap.getService(req.getMsgType());
            }
            if (processService == null) {
                log.error("不支持的交易类型");
                throw new WxAppException(WxErrorCode.WX_TRAN_CODE_ERROR.code, WxErrorCode.WX_TRAN_CODE_ERROR.desc);
            }
            result = processService.invokeMethod(request, response, req);
        } catch (WxAppException e) {
            log.error("交易出错:{},{}", e.getErrorCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("交易出错:", e);
        } finally {
            //4.返回推送消息
            response.setContentType(request.getContentType());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result == null ? "" : result);
            response.getWriter().flush();
            response.getWriter().close();
        }
        long end = System.currentTimeMillis();
        log.info("交易结束，用时{}毫秒", (end - begin));
    }

    @GetMapping(value = "/getAccessToken")
    public ResultInfo getAccessToken() {
        return wxService.getAccessToken();
    }

    @PostMapping(value = "/createMenu")
    public ResultInfo createMenu(String menu) {
        return wxService.createMenu(menu);
    }

    @GetMapping(value = "/getMenu")
    public ResultInfo getMenu() {
        return wxService.getMenu();
    }

    @GetMapping(value = "/getOauthAccessToken")
    public ResultInfo getOauthAccessToken(String code) {
        return wxService.getAccessToken(code);
    }
}
