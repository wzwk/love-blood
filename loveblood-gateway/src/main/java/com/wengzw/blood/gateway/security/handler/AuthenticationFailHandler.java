package com.wengzw.blood.gateway.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.gateway.security.config.CorsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author wengzw
 * @date 2022/7/30 9:50
 */
@Component
@Slf4j
public class AuthenticationFailHandler implements ServerAuthenticationFailureHandler {

    private final CorsConfig corsConfig;

    public AuthenticationFailHandler(CorsConfig corsConfig) {
        this.corsConfig = corsConfig;
    }

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException e) {
        log.info("鉴权失败");
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        //设置headers
        corsConfig.setResponseHead(response);
        //设置body
        ResponseResult<AuthUser> result = new ResponseResult<>(RespStatusEnum.FAIL);
        byte[] dataBytes = {};
        ObjectMapper mapper = new ObjectMapper();
        try {
            dataBytes = mapper.writeValueAsBytes(result);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        DataBuffer bodyDateBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDateBuffer));
    }
}


