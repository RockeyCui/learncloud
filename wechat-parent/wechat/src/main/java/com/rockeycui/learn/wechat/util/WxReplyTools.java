package com.rockeycui.learn.wechat.util;


import com.rockeycui.learn.wechat.api.dto.req.WxReplyMsg;
import com.rockeycui.learn.wechat.api.dto.req.WxReq;
import com.rockeycui.learn.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class WxReplyTools {

	/**
	 * 微信 被动回复 文本消息
	 */
	public static String getReplyTextMsgXml(WxReq wxReq) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");

		sb.append("<ToUserName><![CDATA[");
		sb.append(wxReq.getToUserName());
		sb.append("]]></ToUserName>");

		sb.append("<FromUserName><![CDATA[");
		sb.append(wxReq.getFromUserName());
		sb.append("]]></FromUserName>");

		sb.append("<CreateTime><![CDATA[");
		sb.append(wxReq.getCreateTime());
		sb.append("]]></CreateTime>");

		sb.append("<MsgType><![CDATA[");
		sb.append(wxReq.getMsgType());
		sb.append("]]></MsgType>");

		sb.append("<Content><![CDATA[");
		sb.append(wxReq.getContent());
		sb.append("]]></Content>");
		sb.append("</xml>");

		return sb.toString();
	}

	/**
	 * 微信 被动回复 图片消息
	 */
	public static String getReplyImageMsgXml(Map<String, String> map) {
		// 回复文本消息
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");

		sb.append("<ToUserName><![CDATA[");
		sb.append(map.get("ToUserName"));
		sb.append("]]></ToUserName>");

		sb.append("<FromUserName><![CDATA[");
		sb.append(map.get("FromUserName"));
		sb.append("]]></FromUserName>");

		sb.append("<CreateTime><![CDATA[");
		sb.append(map.get("CreateTime"));
		sb.append("]]></CreateTime>");

		sb.append("<MsgType><![CDATA[");
		sb.append(map.get("MsgType"));
		sb.append("]]></MsgType>");
		// 图片消息内容
		sb.append("<Image>");
		sb.append("<MediaId><![CDATA[");
		sb.append(map.get("MediaId"));
		sb.append("]]></MediaId>");
		sb.append("</Image>");

		sb.append("</xml>");
		return sb.toString();

	}

	/**
	 * 微信 被动回复 语音消息
	 */
	public static String getReplyVoiceMsgXml(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");

		sb.append("<ToUserName><![CDATA[");
		sb.append(map.get("ToUserName"));
		sb.append("]]></ToUserName>");

		sb.append("<FromUserName><![CDATA[");
		sb.append(map.get("FromUserName"));
		sb.append("]]></FromUserName>");

		sb.append("<CreateTime><![CDATA[");
		sb.append(map.get("CreateTime"));
		sb.append("]]></CreateTime>");

		sb.append("<MsgType><![CDATA[");
		sb.append(map.get("MsgType"));
		sb.append("]]></MsgType>");

		sb.append("<Voice>");
		sb.append("<MediaId><![CDATA[");
		sb.append(map.get("MediaId"));
		sb.append("]]></MediaId>");
		sb.append("</Voice>");

		sb.append("</xml>");
		return sb.toString();

	}

	/**
	 * 微信 被动回复 视频消息
	 */
	public static String getReplyVideoMsgXml(Map<String, String> map) {
		// 回复文本消息
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");

		sb.append("<ToUserName><![CDATA[");
		sb.append(map.get("ToUserName"));
		sb.append("]]></ToUserName>");

		sb.append("<FromUserName><![CDATA[");
		sb.append(map.get("FromUserName"));
		sb.append("]]></FromUserName>");

		sb.append("<CreateTime><![CDATA[");
		sb.append(map.get("CreateTime"));
		sb.append("]]></CreateTime>");

		sb.append("<MsgType><![CDATA[");
		sb.append(map.get("MsgType"));
		sb.append("]]></MsgType>");

		sb.append("<Video>");

		sb.append("<MediaId><![CDATA[");
		sb.append(map.get("MediaId"));
		sb.append("]]></MediaId>");

		sb.append("<Title><![CDATA[");
		sb.append(map.get("Title"));
		sb.append("]]></Title>");

		sb.append("<Description><![CDATA[");
		sb.append(map.get("Description"));
		sb.append("]]></Description>");

		sb.append("</Video>");

		sb.append("</xml>");
		return sb.toString();

	}

	/**
	 * 微信 被动回复 音乐消息
	 */
	public static String getReplyMusicMsgXml(Map<String, String> map) {

		// 回复文本消息
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");

		sb.append("<ToUserName><![CDATA[");
		sb.append(map.get("ToUserName"));
		sb.append("]]></ToUserName>");

		sb.append("<FromUserName><![CDATA[");
		sb.append(map.get("FromUserName"));
		sb.append("]]></FromUserName>");

		sb.append("<CreateTime><![CDATA[");
		sb.append(map.get("CreateTime"));
		sb.append("]]></CreateTime>");

		sb.append("<MsgType><![CDATA[");
		sb.append(map.get("MsgType"));
		sb.append("]]></MsgType>");

		sb.append("<Music>");

		sb.append("<Title><![CDATA[");
		sb.append(map.get("Title"));
		sb.append("]]></Title>");

		sb.append("<Description><![CDATA[");
		sb.append(map.get("Description"));
		sb.append("]]></Description>");

		sb.append("<MusicUrl><![CDATA[");
		sb.append(map.get("MusicUrl"));
		sb.append("]]></MusicUrl>");

		sb.append("<HQMusicUrl><![CDATA[");
		sb.append(map.get("HQMusicUrl"));
		sb.append("]]></HQMusicUrl>");

		sb.append("<ThumbMediaId><![CDATA[");
		sb.append(map.get("ThumbMediaId"));
		sb.append("]]></ThumbMediaId>");

		sb.append("</Music>");

		sb.append("</xml>");
		return sb.toString();

	}

	/**
	 * 微信 被动回复 图文消息
	 */
	public static String getReplyNewsMsgXml(WxReq req, List<WxReplyMsg> list) {
		// 回复文本消息
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");

		sb.append("<ToUserName><![CDATA[");
        //这里是回复，所以要互转
		sb.append(req.getFromUserName());
		sb.append("]]></ToUserName>");

		sb.append("<FromUserName><![CDATA[");
        //这里是回复，所以要互转
		sb.append(req.getToUserName());
		sb.append("]]></FromUserName>");

		sb.append("<CreateTime><![CDATA[");
		sb.append(req.getCreateTime());
		sb.append("]]></CreateTime>");

		sb.append("<MsgType><![CDATA[");
		sb.append("news");
		sb.append("]]></MsgType>");

		if (list == null || list.size() <= 0) {
			// 回复文本消息
			sb.append("<Content><![CDATA[");
			sb.append("数据库未配置模板");
			sb.append("]]></Content>");
		} else {
			// 回复图文消息
			sb.append("<ArticleCount>");
			sb.append(list.size() + "");
			sb.append("</ArticleCount>");

			sb.append("<Articles>");
			for (int i = 0; i < list.size(); i++) {
				WxReplyMsg wxReplyMsg = list.get(i);
				sb.append("<item><Title><![CDATA[");
				sb.append(StringUtil.isEmpty(wxReplyMsg.getTitle()) ? "" : wxReplyMsg.getTitle());
				sb.append("]]></Title><Description><![CDATA[");
				sb.append(StringUtil.isEmpty(wxReplyMsg.getDescription()) ? "" : wxReplyMsg.getDescription());
				sb.append("]]></Description><PicUrl><![CDATA[");
				String picUrl = wxReplyMsg.getPicUrl();
				sb.append(StringUtil.isEmpty(picUrl) ? "" : picUrl);
				sb.append("]]></PicUrl><Url><![CDATA[");
				String url = wxReplyMsg.getUrl();
				sb.append(StringUtil.isEmpty(url) ? "" : url);
				sb.append("]]></Url></item>");
			}
			sb.append("</Articles>");
		}
		sb.append("</xml>");
		return sb.toString();
	}

}
