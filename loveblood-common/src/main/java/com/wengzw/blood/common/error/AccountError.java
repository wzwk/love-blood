package com.wengzw.blood.common.error;

import lombok.Getter;

/**
 * @author wengzw
 * @date 2022/7/30 10:30
 */
@Getter
public enum AccountError implements ErrorInfo{

    // 账号系统
    EMAIL_EXIST(10000, 400, "邮箱已被使用"),
    USER_EXIST(10001, 400, "用户已注册"),
    EMAIL_REG_DISABLE(10002, 400, "不允许邮件注册"),
    REG_CODE_DISABLE(10003, 400, "不允许邀请码注册"),
    LOGIN_CODE_ERROR(10004, 400, "权限不足"),
    EMAIL_CODE_ERROR(10005, 400, "邮箱验证码错误"),
    USER_NOT_EXIST(10006, 404, "用户不存在"),
    EMAIL_NOT_SET(10007, 404, "用户未设置邮箱"),
    USER_INFORMATION_INCOMPLETE(10008,400,"用户信息不完善");

    int code;
    int status;
    String message;

    AccountError(int code, int status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
