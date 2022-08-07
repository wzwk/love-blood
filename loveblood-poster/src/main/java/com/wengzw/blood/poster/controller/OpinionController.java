package com.wengzw.blood.poster.controller;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.poster.service.poster.OpinionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wengzw
 * @date 2022/8/7 16:51
 */

@RestController
@RequestMapping(OpinionController.PREFIX)
@Validated
@RequiredArgsConstructor
@Slf4j
public class OpinionController {

    public static final String PREFIX = "/blood/opinion";

    private final OpinionService opinionService;

    @PostMapping("insertOpinionInfo")
    @ResponseBody
    public ResponseResult insertOpinionInfo(@Param("name") String name,
                                            @Param("phone") String phone,
                                            @Param("content") String content) {
        return opinionService.insertOpinionInfo(name,phone,content);
    }
}
