package com.wengzw.blood.poster.service;

import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.vo.AuthUserVo;

/**
 * @author wengzw
 * @date 2022/7/31 14:05
 */
public interface EmailService {

    boolean emailSend(String email);

    boolean registerSuccess(AuthUserVo user);
}
