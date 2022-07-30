package com.wengzw.blood.common.utils;

/**
 * @author wengzw
 * @date 2022/7/30 12:03
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 使用自己的加密方法对密码进行编码加密验证
 *
 * @author wengzw
 * @date 2022/5/2 15:56
 */
@Component
@Slf4j
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return SecureUtils.getPassword(rawPassword.toString());
    }

    /**
     * @param rawPassword     数据库查询的密码
     * @param encodedPassword 自己加密过的
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

}
