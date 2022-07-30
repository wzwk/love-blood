package com.wengzw.blood.common.service.user;

import com.wengzw.blood.common.entity.AuthUser;
import org.springframework.validation.annotation.Validated;

/**
 * @author wengzw
 * @date 2022/7/30 11:31
 */
@Validated
public interface UserService {

    int register(AuthUser authUser);
}
