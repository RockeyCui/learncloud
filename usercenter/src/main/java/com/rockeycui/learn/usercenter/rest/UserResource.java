package com.rockeycui.learn.usercenter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuishilei
 * @date 2018/12/20
 */
@RestController
@RequestMapping("user")
public class UserResource {

    @GetMapping("hi")
    public Object hi() {
        return "hi";
    }
}
