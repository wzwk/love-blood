package com.wengzw.loveblood.service.user;

import cn.hutool.core.util.StrUtil;
import com.wengzw.blood.common.dao.mybaits.UserDao;
import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.exception.JsonException;
import com.wengzw.blood.common.service.user.UserService;
import com.wengzw.blood.common.utils.SecureUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author wengzw
 * @date 2022/7/30 11:32
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public int register(AuthUser authUser) {
        String username = authUser.getUsername();
        String password = SecureUtils.getPassword(authUser.getPassword());

        //非空判断
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            throw new JsonException(20001,"输入的账号密码不能为空");
        }

        int result = userDao.addUser(username, password);
        return result;
    }
}
