package com.rockeycui.learn.usercenter.api.fegin;

import com.rockeycui.learn.common.bean.ResultInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cuishilei
 * @date 2018/12/21
 */
@FeignClient("usercenter")
public interface UserFeignService {

    @GetMapping("/user/hi")
    ResultInfo hi();
}
