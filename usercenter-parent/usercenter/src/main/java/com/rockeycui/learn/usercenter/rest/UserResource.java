package com.rockeycui.learn.usercenter.rest;

import com.rockeycui.learn.common.bean.ResultInfo;
import com.rockeycui.learn.usercenter.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cuishilei
 * @date 2018/12/20
 */
@RestController
@RequestMapping("user")
public class UserResource {
    @Resource
    private UserService userService;

    @GetMapping("hi")
    public ResultInfo hi() {
        return userService.sayHi();
    }
}
