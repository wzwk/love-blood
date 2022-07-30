package com.wengzw.blood.gateway.security.handler;

import com.alibaba.fastjson.JSON;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.gateway.security.config.CorsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;

/**
 * @author wengzw
 * @date 2022/7/30 10:47
 */
@Component
@Slf4j
public class CustomHttpBasicServerAuthenticationEntryPoint extends HttpBasicServerAuthenticationEntryPoint {
    private final CorsConfig corsConfig;

    public CustomHttpBasicServerAuthenticationEntryPoint(CorsConfig corsConfig) {
        this.corsConfig = corsConfig;
    }

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        log.info("身份认证失败");
        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        corsConfig.setResponseHead(response);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        HashMap<String, Object> result = new HashMap<String, Object>(3) {{
            put("code", RespStatusEnum.UNAUTHORIZED.getCode());
            put("msg", RespStatusEnum.UNAUTHORIZED.getStatus());
        }};
        byte[] dataBytes = JSON.toJSONString(result).getBytes();
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));
    }
}

