package com.rockeycui.learn.backend.rest;

import com.rockeycui.learn.backend.service.BackendService;
import com.rockeycui.learn.common.bean.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cuishilei
 * @date 2018/12/20
 */
@RestController
@RequestMapping("/hello")
public class HelloResource {
    @Resource
    private BackendService backendService;

    @RequestMapping("hi")
    public ResultInfo sayHi() {
        return backendService.sayHi();
    }
}

