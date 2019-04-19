package com.rockeycui.learn.wechat.process;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cuishilei
 */
@Service
public class DataMessageProcessorMap {
    @Resource
    private DataMessageProcessService[] services;

    private Map<String, DataMessageProcessService> serviceMap = new HashMap<>();

    @PostConstruct
    public void init() {
        for (DataMessageProcessService service : services) {
            serviceMap.put(service.getTranCode(), service);
        }
    }

    public DataMessageProcessService getService(String tranCode) {
        return serviceMap.get(tranCode);
    }

}
