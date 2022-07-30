package com.wengzw.blood.gateway.security.handler;

import com.wengzw.blood.gateway.security.config.CorsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author wengzw
 * @date 2022/7/30 10:47
 */
@Component
@Slf4j
public class TimingLogoutSuccessHandler implements ServerLogoutSuccessHandler {
    private final CorsConfig corsConfig;

    public TimingLogoutSuccessHandler(CorsConfig corsConfig) {
        this.corsConfig = corsConfig;
    }

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.info("超时退出");
        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        //设置headers
        corsConfig.setResponseHead(response);
        DataBuffer wrap = response.bufferFactory().wrap("success".getBytes());
        return response.writeWith(Mono.just(wrap));
    }
}
