package com.wengzw.blood.gateway.dao;

import com.wengzw.blood.common.dao.mybaits.UserDao;
import com.wengzw.blood.common.dao.redis.RedisDao;
import com.wengzw.blood.common.dao.redis.TokenDao;
import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.error.AccountError;
import com.wengzw.blood.common.exception.JsonException;
import com.wengzw.blood.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author wengzw
 * @date 2022/7/30 10:22
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TokenDaoImpl implements TokenDao {
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisDao redisDao;
    private final UserDao userDao;

    @Override
    public String generateUserToken(Integer uid) {
        final AuthUser user = userDao.getUserById(uid);
        if (user == null) { throw new JsonException(AccountError.USER_NOT_EXIST); }
        return generateUserToken(user);
    }

    @Override
    public String generateUserToken(AuthUser user) {
        user.setPassword(null);
        final String token;
        try {
            token = JwtUtils.generateToken(user, 30 * 24 * 60 * 60);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        setToken(user.getId(), token);
        return token;
    }

    @Override
    public void setToken(Integer uid, String token) {
        redisTemplate.opsForValue().set(TokenDao.getTokenKey(uid, token), "1", Duration.ofDays(1));
    }

    @Override
    public void cleanUserToken(Integer uid) {
        redisTemplate.delete(redisDao.scanKeys("xyy::token::" + uid + "::*"));
    }

    @Override
    public boolean isTokenValid(Integer uid, String token) {
        return redisTemplate.opsForValue().get(TokenDao.getTokenKey(uid, token)) != null;
    }
}

