package com.wengzw.loveblood.controller;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengzw
 * @date 2022/7/26 0:14
 */

@RestController
public class DemoController {

    @GetMapping("/demo")
    public ResponseResult demo() {
        return new ResponseResult(RespStatusEnum.SUCCESS,"----");
    }

}
