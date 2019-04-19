package com.rockeycui.learn.wechat.api.error;

/**
 * @author RockeyCui
 */
public enum WxErrorCode {
    /**/
    WX_SUCCESS("WX000000000", "交易成功"),
    WX_ERROR("WX999999999", "交易失败"),
    WX_DATA_ERROR("WX999999999", "报文转换失败"),
    WX_TRAN_CODE_ERROR("WX000000050", "不支持的交易"),
    WX_FILE_ERROR("WX000000051", "下载文件失败"),
    ;

    public String code;
    public String desc;

    private WxErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
