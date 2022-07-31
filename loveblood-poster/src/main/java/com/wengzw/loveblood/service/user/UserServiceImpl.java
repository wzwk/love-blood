package com.wengzw.loveblood.service.user;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wengzw.blood.common.dao.mybaits.UserDao;
import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.common.error.AccountError;
import com.wengzw.blood.common.exception.JsonException;
import com.wengzw.blood.common.service.user.UserService;
import com.wengzw.blood.common.utils.SecureUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author wengzw
 * @date 2022/7/30 11:32
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserDao, AuthUser> implements UserService {

    private final UserDao userDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户注册
     *
     * @param email    电子邮件
     * @param mobile   手机号
     * @param password 密码
     * @param userName 用户名
     * @param code     验证码
     */
    @Override
    public ResponseResult register(String email, String mobile, String password, String userName, String code) {

        password = SecureUtils.getPassword(password);
        //非空判断
        if (StrUtil.isBlank(mobile) || StrUtil.isBlank(password)
                || StrUtil.isBlank(code) || StrUtil.isBlank(userName)
                || StrUtil.isBlank(email)) {
            return new ResponseResult(RespStatusEnum.FAIL,AccountError.USER_INFORMATION_INCOMPLETE.getMessage());
        }

        //判断验证码
        String redisCode = redisTemplate.opsForValue().get(email);
        if (!code.equals(redisCode)) {
            return new ResponseResult(RespStatusEnum.FAIL,AccountError.EMAIL_CODE_ERROR.getMessage());
        }

        // 判断手机号是否重复
        QueryWrapper<AuthUser> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            return new ResponseResult(RespStatusEnum.FAIL,AccountError.USER_EXIST.getMessage());
        }

        String avatar = "https://disk.xiaotao2333.top:344/api/static/static/defaultAvatar.png";
        boolean disabled = false;

        int insert = userDao.addUser(userName, password, mobile, email, avatar, disabled);
        if (insert < 1) {
            throw new JsonException(400, "注册失败");
        }

        // 删除缓存
        redisTemplate.delete(email);
        return new ResponseResult(RespStatusEnum.SUCCESS,"注册成功");
    }
}
