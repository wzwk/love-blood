package com.wengzw.blood.common.utils;

import com.wengzw.blood.common.dao.mybaits.UserDao;
import com.wengzw.blood.common.dao.redis.TokenDao;
import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.exception.JsonException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @author wengzw
 * @date 2022/7/30 10:34
 */
@Configuration
@Slf4j
public class JwtUtils {
    private final static int EXPIRATION_TIME = 60 * 60 * 24 * 30;
    private static String SECRET = "9786142333543";
    public static final String AUTHORIZATIONTOKEN = "Token";
    private final TokenDao tokenDao;
    private final UserDao userDao;

    public JwtUtils(TokenDao tokenDao, UserDao userDao) {
        this.tokenDao = tokenDao;
        this.userDao = userDao;
    }

    public static void setSecret(String secret) {
        JwtUtils.SECRET = secret;
    }

    /**
     * 生成一个包含了data作为负载信息的token
     *
     * @param data 要附加的数据
     * @param expr token有效时间，单位为秒
     * @return token字符串
     */
    public static String generateToken(AuthUser data, int expr) {

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Date expiration = null;
        if (expr > 0) {
            calendar.setTime(now);
            calendar.add(Calendar.SECOND, expr);
            expiration = calendar.getTime();
        }

        return Jwts.builder().
                //设置jwt头信息
                        setHeaderParam("typ", "JWT").
                        setHeaderParam("alg", "HS256").
                        claim("id", data.getId()).
                        claim("username", data.getUsername()).
                        setIssuedAt(now).
                        setExpiration(expiration).
                        signWith(SignatureAlgorithm.HS256, SECRET).
                        compact();
    }

    /**
     * 验证token的有效性
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        try {
            String username = JwtUtils.parse(token).get("username").toString();
            AuthUser userByUser = userDao.getUserByUser(username);
            if (username != null) {
                AuthUser user = new AuthUser();
                if (tokenDao.isTokenValid(userByUser.getId(), token)) {
                    return new UsernamePasswordAuthenticationToken(username, "", user.getAuthorities());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析一个token中的负载数据的json
     * @param token 输入的token
     * @return json字符串
     */
    public static HashMap<String,String> parse(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            HashMap<String,String> map = new HashMap<>();
            map.put("id",body.get("id").toString());
            map.put("username",body.get("username").toString());
            return map;
        } catch (ExpiredJwtException e) {
            throw new JsonException("token已过期");
        } catch (Exception e) {
            throw new JsonException("token无效");
        }
    }

}

