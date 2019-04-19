package com.rockeycui.learn.wechat.serviceimpl;


import com.rockeycui.learn.common.util.DateTimeUtil;
import com.rockeycui.learn.wechat.api.dto.req.WxReplyMsg;
import com.rockeycui.learn.wechat.api.dto.req.WxReq;
import com.rockeycui.learn.wechat.service.WxUserService;
import com.rockeycui.learn.wechat.util.WxReplyTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RockeyCui
 */
@Slf4j
@Service
public class WxUserServiceImpl implements WxUserService {
    @Value("${wechat.custom.bind-info-url}")
    private String bindInfoUrl;

    @Value("${wechat.custom.bind-info-pic}")
    private String bindInfoPic;

    @Override
    public String returnBindMsg(WxReq req) {
        List<WxReplyMsg> list = new ArrayList<>();
        WxReplyMsg replyMsg = new WxReplyMsg();
        replyMsg.setTitle("请绑定信息");
        replyMsg.setDescription("点击绑定学员信息啦！");
        replyMsg.setUrl(bindInfoUrl + req.getFromUserName());
        replyMsg.setPicUrl(bindInfoPic);
        list.add(replyMsg);
        return WxReplyTools.getReplyNewsMsgXml(req, list);
    }

    @Override
    public String returnBindUpMsg(WxReq req) {
        List<WxReplyMsg> list = new ArrayList<>();
        WxReplyMsg replyMsg = new WxReplyMsg();
        replyMsg.setTitle("修改学生信息");
        replyMsg.setDescription("点击修改学生信息啦！");
        replyMsg.setUrl("");
        replyMsg.setPicUrl("");
        list.add(replyMsg);
        return WxReplyTools.getReplyNewsMsgXml(req, list);
    }

    @Override
    public String returnTxtMsg(WxReq req) {
        WxReq reply = new WxReq();
        reply.setFromUserName(req.getToUserName());
        reply.setToUserName(req.getFromUserName());
        reply.setCreateTime(DateTimeUtil.getNowDateStr(DateTimeUtil.DATETIME_FORMAT_YYYYMMDDHHMMSS));
        reply.setMsgType("text");
        String sb = "您好：\n" +
                "1.我是石家庄学而思数学老师刘丹\n" +
                "2.本订阅号现阶段只用于培训学员签到\n" +
                "3.回复【绑定】，绑定你的学员信息吧\n";
        reply.setContent(sb);
        return WxReplyTools.getReplyTextMsgXml(reply);
    }
}
