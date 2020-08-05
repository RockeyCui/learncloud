package com.rockeycui.learn.boot.rest;

import com.rockeycui.learn.boot.service.UserInfoService;
import com.rockeycui.learn.common.bean.ResultInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author cuishilei
 * @date 2018/12/20
 */
@RestController
@RequestMapping("/user")
public class UserInfoResource {
    @Resource
    private UserInfoService userInfoService;

    @PostMapping("auth")
    public ResultInfo<?> sayHi(@RequestParam(name = "account") String account, @RequestParam(name = "pwd") String pwd) {
        return userInfoService.auth(account, pwd);
    }

    @GetMapping("books")
    public ResultInfo<?> getBooks(@RequestParam(name = "userName", required = false) String userName,
                                  @RequestParam(name = "bookName", required = false) String bookName,
                                  @RequestParam(name = "page") Integer page,
                                  @RequestParam(name = "size") Integer size) {
        userName = StringUtils.isBlank(userName) ? null : userName;
        bookName = StringUtils.isBlank(bookName) ? null : bookName;

        return userInfoService.getBooks(userName, bookName, page, size);
    }
}

