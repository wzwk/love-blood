package com.wengzw.blood.gateway.filters;

import cn.hutool.core.util.StrUtil;
import com.wengzw.blood.common.dao.mybaits.UserDao;
import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.utils.JwtUtils;
import com.wengzw.blood.gateway.dao.TokenDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author wengzw
 * @date 2022/7/30 11:01
 */
@Component
@Slf4j
public class JwtTokenAuthenticationFilter implements WebFilter {

    @Autowired
    private TokenDaoImpl tokenDao;

    @Resource
    private UserDao userDao;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        String token = serverWebExchange.getRequest().getHeaders().getFirst(JwtUtils.AUTHORIZATIONTOKEN);
        if (StrUtil.isNotBlank(token)) {
            // 验证token有效性
            Authentication authentication = getAuthentication(token);
            if (authentication != null) {
                return webFilterChain.filter(serverWebExchange)
                        .subscriberContext(ReactiveSecurityContextHolder.withAuthentication(authentication));
            }
        }
        return webFilterChain.filter(serverWebExchange);
    }

    /**
     * 验证token的有效性
     *
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        try {
            String username = JwtUtils.parse(token).get("username");
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

}


