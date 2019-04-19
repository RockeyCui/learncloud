package com.rockeycui.learn.wechat.util;

import com.rockeycui.learn.wechat.api.error.WxAppException;
import com.rockeycui.learn.wechat.api.error.WxErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类说明
 */
@Slf4j
public class XMLUtil {
    /**
     * 该方法仅能处理这样的数据结构（text）：
     * <xml><ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[FromUser]]></FromUserName>
     * <CreateTime>123456789</CreateTime>
     * <MsgType><![CDATA[event]]></MsgType>
     * <Event><![CDATA[subscribe]]></Event>
     * <EventKey><![CDATA[qrscene_123123]]></EventKey>
     * <Ticket><![CDATA[TICKET]]></Ticket>
     * </xml>
     *
     * @param request
     * @return
     */
    public static Map<String, String> asMap(HttpServletRequest request) {
        try {
            InputStream inputStream = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);

            Map<String, String> map = new HashMap<>();
            if (null == document) {
                return map;
            }
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }
            return map;
        } catch (Exception e) {
            log.error("报文转换错误", e);
            throw new WxAppException(WxErrorCode.WX_DATA_ERROR.code, WxErrorCode.WX_DATA_ERROR.desc);
        }
    }
}
