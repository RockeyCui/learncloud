package com.rockeycui.learn.boot.service;

import com.rockeycui.learn.common.bean.ResultInfo;

/**
 * @author RockeyCui
 */
public interface UserInfoService {
    ResultInfo<?> auth(String account, String pwd);

    ResultInfo<?> getBooks(String userName, String bookName, Integer page, Integer size);
}
