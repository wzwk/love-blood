package com.wengzw.loveblood.controller;

import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("register")
    public ResponseResult registerUser(@RequestBody AuthUser authUser) {
        userService.register(authUser);
        return ResponseResult.success();
    }

}
