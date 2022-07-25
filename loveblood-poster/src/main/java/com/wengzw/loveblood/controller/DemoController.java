package com.wengzw.loveblood.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengzw
 * @date 2022/7/26 0:14
 */

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "hello world";
    }

}
