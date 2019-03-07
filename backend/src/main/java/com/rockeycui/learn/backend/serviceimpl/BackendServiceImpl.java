package com.rockeycui.learn.backend.serviceimpl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rockeycui.learn.backend.service.BackendService;
import com.rockeycui.learn.common.bean.ResultInfo;
import com.rockeycui.learn.usercenter.api.fegin.UserFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author cuishilei
 * @date 2018/12/21
 */
@Slf4j
@Service
public class BackendServiceImpl implements BackendService {
    @Resource
    private UserFeignService userFeignService;


    @Override
    @HystrixCommand
    public ResultInfo sayHi() {
        log.info("------------say-----------");
        return userFeignService.hi();
    }
}
