package com.wengzw.blood.gateway.security.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wengzw.blood.common.dao.mybaits.UserDao;
import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.common.utils.JwtUtils;
import com.wengzw.blood.common.utils.MapperHolder;
import com.wengzw.blood.gateway.dao.TokenDaoImpl;
import com.wengzw.blood.gateway.security.config.CorsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;

/**
 * @author wengzw
 * @date 2022/7/30 9:56
 */
@Component
@Slf4j
public class AuthenticationSuccessHandler extends WebFilterChainServerAuthenticationSuccessHandler {


    private final TokenDaoImpl tokenDao;

    private final CorsConfig corsConfig;

    private final UserDao userDao;

    public AuthenticationSuccessHandler(TokenDaoImpl tokenDao, CorsConfig corsConfig, UserDao userDao) {
        this.tokenDao = tokenDao;
        this.corsConfig = corsConfig;
        this.userDao = userDao;
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.info("认证成功");
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        //设置headers
        corsConfig.setResponseHead(response);

        byte[] dataBytes;
        try {
            User user = (User) authentication.getPrincipal();

            AuthUser authUser = buildUser(user);
            //生成token
            String jwtToken = JwtUtils.generateToken(authUser, 30 * 24 * 60 * 60);
            //存到redis里
            tokenDao.setToken(authUser.getId(), jwtToken);
            authUser.setToken(jwtToken);

            //设置body
            ResponseResult<AuthUser> result = new ResponseResult(RespStatusEnum.SUCCESS, authUser);
            dataBytes = MapperHolder.mapper.writeValueAsBytes(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            HashMap<String, Object> map = new HashMap<String, Object>(3) {{
                put("code", RespStatusEnum.UNAUTHORIZED.getCode());
                put("msg", RespStatusEnum.UNAUTHORIZED.getStatus());
            }};
            dataBytes = JSON.toJSONString(map).getBytes();
        }
        DataBuffer bodyDateBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDateBuffer));
    }

    private AuthUser buildUser(User user) {
        AuthUser authUserDetails = new AuthUser();
        AuthUser userByUser = userDao.getUserByUser(user.getUsername());
        // 获取数据库中的用户信息
        authUserDetails.setId(userByUser.getId());
        authUserDetails.setUserName(user.getUsername());
        authUserDetails.setPassword(user.getPassword());
        authUserDetails.setRole("admin");
        return authUserDetails;
    }
}


