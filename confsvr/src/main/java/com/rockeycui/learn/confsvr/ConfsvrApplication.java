package com.rockeycui.learn.confsvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * @author RockeyCui
 */
@EnableConfigServer
@SpringBootApplication
public class ConfsvrApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfsvrApplication.class, args);
    }

}
