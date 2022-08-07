package com.wengzw.blood.common.service.user;

import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.ResponseResult;
import org.springframework.validation.annotation.Validated;

/**
 * @author wengzw
 * @date 2022/7/30 11:31
 */
@Validated
public interface UserService {

    ResponseResult register(String email, String mobile, String password, String userName, String code);

    ResponseResult getUserInfo(String token);

    ResponseResult modifyUserInfo(String token, AuthUser user);
}
