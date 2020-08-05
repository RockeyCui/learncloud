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

    public ResultInfo(int errNo, String errMsg) {
        this.errNo = errNo;
        this.errMsg = errMsg;
    }
}
