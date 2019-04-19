package com.rockeycui.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author RockeyCui
 * EnableCircuitBreaker 告诉 Spring Cloud 你将为你的服务使用 Hystrix
 * EnableFeignClients 在你的代码中，使用 FeignClient
 */
/*@EnableFeignClients
@EnableEurekaClient*/
/*@EnableCircuitBreaker*/
@SpringBootApplication
@MapperScan(basePackages = "com.rockeycui.learn.wechat.mapper")
public class WeChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatApplication.class, args);
    }

}

