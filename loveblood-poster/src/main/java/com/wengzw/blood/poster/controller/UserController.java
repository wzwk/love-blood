package com.wengzw.blood.poster.controller;

import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.service.user.UserService;
import com.wengzw.blood.common.validator.annotations.Phone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


/**
 * @author wengzw
 * @date 2022/7/30 11:30
 */

@RestController
@RequestMapping(UserController.PREFIX)
@Validated
@RequiredArgsConstructor
@Slf4j
public class UserController {
    public static final String PREFIX = "/user";

    private final UserService userService;

    /**
     * 注册
     * @param email
     * @param mobile
     * @param password
     * @param userName
     * @param code
     * @return
     */
    @PostMapping("register")
    public ResponseResult registerUser(@RequestParam("email") @Valid @Email String email,
                                       @RequestParam("mobile") @Valid @Phone String mobile,
                                       @RequestParam("password") @Length(min = 6) String password,
                                       @RequestParam("userName") @NotBlank String userName,
                                       @RequestParam("code") @NotBlank String code) {
        return userService.register(email,mobile,password,userName,code);
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("getUserInfo")
    @ResponseBody
    public ResponseResult getUserInfo(@RequestHeader("token") String token) {
        return userService.getUserInfo(token);
    }

    /**
     * 修改用户信息
     *
     * @param token
     * @return
     */
    @PutMapping("modifyUserInfo")
    @ResponseBody
    public ResponseResult modifyUserInfo(@RequestHeader("token") String token, @RequestBody AuthUser user) {
        return userService.modifyUserInfo(token,user);
    }
}
