package com.wengzw.loveblood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wengzw
 * @date 2022/7/26 0:15
 */
@SpringBootApplication
public class LoveBloodApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoveBloodApplication.class,args);
        System.out.println("================爱献血启动成功===================");
    }
}
