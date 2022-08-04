package com.wengzw.blood.poster.controller;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.utils.JwtUtils;
import com.wengzw.blood.poster.entity.HelpPoster;
import com.wengzw.blood.poster.service.poster.HelpPosterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

/**
 * @author wengzw
 * @date 2022/8/2 22:42
 */
@RestController
@RequestMapping(HelpPosterController.PREFIX)
@Validated
@RequiredArgsConstructor
@Slf4j
public class HelpPosterController {
    public static final String PREFIX = "/blood/helpPoster";

    private final HelpPosterService helpPosterService;

    @PostMapping("/insertHelpPoster")
    @ResponseBody
    public ResponseResult insertHelpPoster(@RequestHeader("token") String token, @RequestBody HelpPoster helpPoster) {
        String id = JwtUtils.parse(token).get("id");
        helpPoster.setUserId(Integer.valueOf(id));
        helpPoster.setGmtCreate(new Date());
        return helpPosterService.insertHelpPoster(helpPoster);
    }
}
