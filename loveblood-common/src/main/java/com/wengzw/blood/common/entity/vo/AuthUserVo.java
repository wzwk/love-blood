package com.wengzw.blood.common.entity.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author wengzw
 * @date 2022/8/14 16:18
 */

@Data
@ToString
public class AuthUserVo {
    private String userName;
    private String password;
    private String email;
}
