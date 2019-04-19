package com.rockeycui.learn.wechat.api.dto.req;

import lombok.Data;

@Data
public class WxReq {
	private String ToUserName;
	private String FromUserName;
	private String MsgType;
	private String Event;
	private String EventKey;
	private String Ticket;
	private String CreateTime;
	private String Content;
	private String MediaId;
	private String PicUrl;
}
