package com.rockeycui.learn.boot.service.impl;

import com.rockeycui.learn.boot.dao.UserInfoDao;
import com.rockeycui.learn.boot.dao.entity.BookInfo;
import com.rockeycui.learn.boot.dao.entity.UserInfo;
import com.rockeycui.learn.boot.service.UserInfoService;
import com.rockeycui.learn.common.bean.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public ResultInfo<?> auth(String account, String pwd) {
        ResultInfo<UserInfo> resultInfo;
        UserInfo userInfo = userInfoDao.findByNameAndPwd(account, pwd);
        if (userInfo != null) {
            resultInfo = new ResultInfo<>(userInfo);
        } else {
            resultInfo = new ResultInfo<>(1, "账号或密码错误");
        }
        return resultInfo;
    }

    @Override
    public ResultInfo<?> getBooks(String userName, String bookName, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size, new Sort(Sort.Direction.DESC, "id"));
        Page<BookInfo> bookInfos = userInfoDao.queryBookPage(userName, bookName, pageable);
        return new ResultInfo<>(bookInfos);
    }
}
