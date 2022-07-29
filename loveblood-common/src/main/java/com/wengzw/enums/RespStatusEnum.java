package com.wengzw.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author wengzw
 * @date 2022/7/29 15:39
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespStatusEnum {
    /**
     * 枚举的构造函数
     */
    SUCCESS(200, "操作成功"),
    FAIL(-1, "操作失败"),
    UNAUTHORIZED(401, "无效的授权信息");



    /**
     * 响应编码
     */
    private final Integer code;
    /**
     * 响应状态
     */
    private final String status;
}

