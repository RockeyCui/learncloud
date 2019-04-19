package com.rockeycui.learn.wechat.api.error;


/**
 * @author RockeyCui
 */
public class WxAppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String errorMsg;

    public WxAppException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public WxAppException(WxErrorCode errorCode) {
        this.errorCode = errorCode.code;
        this.errorMsg = errorCode.desc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
