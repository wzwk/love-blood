package com.wengzw.blood.poster.controller;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.poster.service.poster.BloodStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengzw
 * @date 2022/8/6 23:06
 */

@RestController
@RequestMapping(BloodStoreController.PREFIX)
@Validated
@RequiredArgsConstructor
@Slf4j
public class BloodStoreController {
    public static final String PREFIX = "/blood/bloodStore";

    private final BloodStoreService bloodStoreService;

    /**
     * 获取血液库存信息
     * @param areaName 地区名称
     *
     * @return
     */
    @GetMapping("getBloodStore")
    @ResponseBody
    public ResponseResult getBloodStore(String areaName) {
        return bloodStoreService.getBloodStore(areaName);
    }

}
