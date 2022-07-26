package com.wengzw.blood.poster.controller;

import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.entity.vo.AuthUserVo;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.common.validator.annotations.OperationLog;
import com.wengzw.blood.poster.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

/**
 * 提供第三方服务 oss 邮件  短信服务
 *
 * @author wengzw
 * @date 2022/7/31 11:02
 */

@RestController
@RequestMapping(EmailController.PREFIX)
@RequiredArgsConstructor
@Validated
@Slf4j
public class EmailController {
    public static final String PREFIX = "/service";

    @Autowired
    private EmailService emailService;

    /**
     * 发送验证码并保存到缓存中
     *
     * @param email 邮箱地址
     * @return
     */
    @GetMapping("emailSend/{email}")
    @OperationLog(operationType = "发送验证码", operationDescription = "调用邮箱接口发送验证码")
    public ResponseResult emailSend(@PathVariable @Email String email) {
        if (emailService.emailSend(email)) {
            return ResponseResult.success("邮件发送成功");
        }
        return ResponseResult.fail("邮件发送失败");
    }

    /**
     * 给用户发送邮件 是否注册成功 被poster服务调用
     *
     * @param user
     * @return
     */
    @PostMapping("registerSuccess")
    public ResponseResult registerSuccess(@RequestBody AuthUserVo user) {
        boolean flag = emailService.registerSuccess(user);
        if (flag) {
            return new ResponseResult(RespStatusEnum.SUCCESS);
        }
        return new ResponseResult(RespStatusEnum.FAIL);
    }

}
