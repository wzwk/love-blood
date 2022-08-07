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
        appointblood.setUserId(Integer.valueOf(id));
        appointblood.setGmtCreate(new Date());
        return appointBloodService.appointBlood(appointblood);
    }

    /**
     * 查询预约信息
     * @param token
     * @return
     */
    @GetMapping("getAppointBloodInfo")
    public ResponseResult getAppointBloodInfo(@RequestHeader("token") String token) {
        return appointBloodService.getAppointBloodInfo(token);
    }

    /**
     * 修改预约献血状态
     *
     * @return
     */
    @PutMapping("updateStatus/{id}")
    @ResponseBody
    public ResponseResult updateStatus(@PathVariable Integer id) {
        return appointBloodService.updateStatus(id);
    }

    /**
     * 删除预约信息
     * @param id
     * @return
     */
    @DeleteMapping("deleteAppointBlood/{id}")
    public ResponseResult deleteAppointBlood(@PathVariable Integer id) {
        return appointBloodService.deleteAppointBlood(id);
    }
}
