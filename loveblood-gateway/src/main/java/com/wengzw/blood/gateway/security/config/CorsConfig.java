package com.wengzw.blood.gateway.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 统一解决跨域
 *
 * @author wengzw
 * @date 2022/7/30 9:49
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }

    /**
     * 设置统一的响应头
     * @param response
     */
    public void setResponseHead(ServerHttpResponse response) {
        //设置headers
        HttpHeaders headers = response.getHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("Cache-Control", "no-store,no-cache,must-revalidate,max-age-8");
        headers.add("Access-Control-Allow-Origin","*");
    }
}
