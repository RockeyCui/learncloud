package com.rockeycui.learn.usercenter.serviceimpl;

import com.rockeycui.learn.common.bean.ResultInfo;
import com.rockeycui.learn.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author cuishilei
 * @date 2018/12/20
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResultInfo sayHi() {
        log.info("------------say-----------");
        ResultInfo<String> resultInfo = new ResultInfo<>();
        resultInfo.setData("hi");
        return resultInfo;
    }
}
