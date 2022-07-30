package com.wengzw.blood.common.utils;

/**
 * @author wengzw
 * @date 2022/7/30 10:24
 */

import org.springframework.util.DigestUtils;

/**
 * 安全与哈希相关的工具类
 */
public final class SecureUtils {

    final static private String SALT = "9786142333543";

    /**
     * 取原始密码加盐哈希值
     * @param originPwd 密码原文
     * @return  哈希运算后的结果
     */
    static public String getPassword(String originPwd) {
        return DigestUtils.md5DigestAsHex((SALT + originPwd).getBytes());
    }


    /**
     * 获取字符串的MD5
     * @param input 输入字符串
     * @return  MD5运算结果
     */
    static public String getMd5(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }
}

