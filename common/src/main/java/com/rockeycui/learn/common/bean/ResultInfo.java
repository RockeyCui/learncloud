package com.rockeycui.learn.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cuishilei
 * @date 2018/12/20
 */
@Data
public class ResultInfo<T> implements Serializable {
    private int errNo;
    private String errMsg;
    private T data;

    public ResultInfo() {
        this.errNo = 0;
        data = null;
    }

    public ResultInfo(T data) {
        this.errNo = 0;
        this.data = data;
    }

    public ResultInfo(int errNo, T data) {
        this.errNo = errNo;
        this.data = data;
        if (errNo != 0) {
            this.errMsg = data.toString();
        }
    }

    public void fail(String errorMsg) {
        errNo = 99;
        this.errMsg = errorMsg;
    }

    public void fail(int errNo, String errMsg) {
        this.errNo = errNo;
        this.errMsg = errMsg;
    }

    /**
     * 判断是否成功方法
     *
     * @return boolean
     * @author cuishilei
     * @date 2018/9/4
     */
    public boolean judgeIsSuccess() {
        return this.errNo == 0;
    }

}
