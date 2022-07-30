package com.wengzw.blood.gateway.security.service;

import com.wengzw.blood.common.dao.mybaits.UserDao;
import com.wengzw.blood.common.entity.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author wengzw
 * @date 2022/7/30 10:48
 */
@Service
@RequiredArgsConstructor
public class SecurityUserDetailService implements ReactiveUserDetailsService {

    private final UserDao userDao;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        try {
            final AuthUser userByUser = userDao.getUserByUser(username);
            //需要通过用户名查找密码，并进行校验
            if (userByUser != null) {
                UserDetails user = User.withUsername(username).password(userByUser.getPassword())
                        .roles("admin", "super").authorities("admin").build();
                return Mono.just(user);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
