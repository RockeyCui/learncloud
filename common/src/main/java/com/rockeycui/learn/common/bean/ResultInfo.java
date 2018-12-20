package com.rockeycui.learn.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cuishilei
 * @date 2018/12/20
 */
@Data
public class ResultInfo<T> implements Serializable {
    private int errno;
    private String errmsg;
    private T data;

    public ResultInfo() {
        this.errno = 0;
        data = null;
    }

    public ResultInfo(int errno, T data) {
        this.errno = errno;
        this.data = data;
        if (errno != 0) {
            this.errmsg = data.toString();
        }
    }

    /**
     * 判断是否成功方法
     *
     * @return boolean
     * @author cuishilei
     * @date 2018/9/4
     */
    public boolean judgeIsSuccess() {
        return this.errno == 0;
    }

}
