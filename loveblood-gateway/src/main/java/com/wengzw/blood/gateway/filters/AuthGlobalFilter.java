package com.wengzw.blood.gateway.filters;

/**
 * @author wengzw
 * @date 2022/7/30 11:28
 */

import cn.hutool.core.util.StrUtil;
import com.wengzw.blood.common.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * todo 需要防止xss攻击和sql注入问题
 * <p>
 * 自定义全局过滤器 必须实现GlobalFilter, Ordered
 * 作用：统一鉴权,用户是否经过认证都必须进过这一步
 * 经过认证，那么进行请求转发
 * 未经过认证，跳转到security进行认证
 * 白名单的数据不需要携带token 也就是不需要拦截
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static final String[] excludeAuthPages = {
            "/auth/**",
            "/user/register"
    };

    private String[] sqlPatternList = {"%'", "select ", "insert ", "delete ", " from",
            "count\\(", "drop table", "update ", "truncate ", "asc\\(",
            "mid\\(", "char\\(", "xp_cmdshell ", "exec ",
            "netlocalgroup administrators", "net user", " or ", " and ", "'"};

    private String[] paramNamePattern = {"[", "]", "<", ">"};

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 过滤器逻辑，用户发送的请求是否携带token或token是否正确
     * 正确放行，错误返回错误信息
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * String path = exchange.getRequest().getURI().getPath();
         if (antPathMatcher.match("mess/**",path)) {
         System.out.println("使用nginx配置时，可能会出现统一的前缀，那么就可以使用这个");
         }
         */

        //指定编码，否则在浏览器中会中文乱码
        exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String hostName = exchange.getRequest().getRemoteAddress().getHostName();
        //判断访问的地址是否出现在黑名单
        Boolean ip = redisTemplate.opsForSet().isMember("blackIp", hostName);
        if (ip) {
            return exchange.getResponse().setComplete();
        }

        //防止sql注入
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        for (String key : queryParams.keySet()) {
            //参数名特殊字符判断
            for (String item : paramNamePattern) {
                if (key.contains(item)) {
                    return exchange.getResponse().setComplete();
                }
            }

            String param = queryParams.getFirst(key);
            if (param != null) {
                //sql注入校验
                if (valid(param)) {
                    return exchange.getResponse().setComplete();
                }
            }
        }

        // 注册功能不可能携带token 所以有白名单路径必须进行区分
        RequestPath path = exchange.getRequest().getPath();
        for (String excludeAuthPage : excludeAuthPages) {
            if (StrUtil.isNotBlank(String.valueOf(path)) && String.valueOf(path).equals(excludeAuthPage)) {
                return chain.filter(exchange);
            }
        }
        String token = exchange.getRequest().getHeaders().getFirst(JwtUtils.AUTHORIZATIONTOKEN);
        if (StrUtil.isBlank(token)) {
            log.info("没有携带token,需要进行认证");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 标识过滤器优先级
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

    private boolean valid(String value) {
        for (String item : sqlPatternList) {
            if (value.contains(item)) {
                return true;
            }
        }
        return false;
    }
}

