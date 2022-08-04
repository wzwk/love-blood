package com.wengzw.blood.poster.controller;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.utils.JwtUtils;
import com.wengzw.blood.poster.entity.AppointBlood;
import com.wengzw.blood.poster.service.poster.AppointBloodService;
import com.wengzw.blood.poster.service.poster.HelpPosterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author wengzw
 * @date 2022/8/4 23:24
 */

@RestController
@RequestMapping(AppointBloodController.PREFIX)
@Validated
@RequiredArgsConstructor
@Slf4j
public class AppointBloodController {
    public static final String PREFIX = "/blood/appointBlood";

    private final AppointBloodService appointBloodService;

    /**
     * 预约献血
     *
     * @param token
     * @param appointblood
     * @return
     */
    @PostMapping("appointBlood")
    @ResponseBody
    public ResponseResult appointBlood(@RequestHeader("token") String token, @RequestBody AppointBlood appointblood) {
        String id = JwtUtils.parse(token).get("id");
        appointblood.setId(Integer.valueOf(id));
        appointblood.setGmtCreate(new Date());
        return appointBloodService.appointBlood(appointblood);
    }
}
