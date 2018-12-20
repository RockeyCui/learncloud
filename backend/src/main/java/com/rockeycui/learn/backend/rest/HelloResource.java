package com.rockeycui.learn.backend.rest;

import com.rockeycui.learn.common.bean.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuishilei
 * @date 2018/12/20
 */
@RestController
@RequestMapping("/hello")
public class HelloResource {

    @RequestMapping("hi")
    public ResultInfo sayHi() {
        ResultInfo<String> resultInfo = new ResultInfo<>();
        resultInfo.setData("hi test");
        return resultInfo;
    }
}

