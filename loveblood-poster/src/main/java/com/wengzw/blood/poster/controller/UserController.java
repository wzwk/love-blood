package com.wengzw.blood.poster.controller;

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

    @PostMapping("register")
    public ResponseResult registerUser(@RequestParam("email") @Valid @Email String email,
                                       @RequestParam("mobile") @Valid @Phone String mobile,
                                       @RequestParam("password") @Length(min = 6) String password,
                                       @RequestParam("userName") @NotBlank String userName,
                                       @RequestParam("code") @NotBlank String code) {
        return userService.register(email,mobile,password,userName,code);
    }

}
