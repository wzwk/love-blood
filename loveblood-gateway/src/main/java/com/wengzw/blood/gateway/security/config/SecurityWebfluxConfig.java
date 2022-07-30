package com.wengzw.blood.gateway.security.config;

import com.wengzw.blood.common.utils.MyPasswordEncoder;
import com.wengzw.blood.gateway.filters.JwtTokenAuthenticationFilter;
import com.wengzw.blood.gateway.security.handler.AuthenticationFailHandler;
import com.wengzw.blood.gateway.security.handler.AuthenticationSuccessHandler;
import com.wengzw.blood.gateway.security.handler.CustomHttpBasicServerAuthenticationEntryPoint;
import com.wengzw.blood.gateway.security.handler.TimingLogoutSuccessHandler;
import com.wengzw.blood.gateway.security.service.SecurityUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author wengzw
 * @date 2022/7/30 12:02
 */
@EnableWebFluxSecurity
public class SecurityWebfluxConfig {

    private static final String LOGIN_URI = "/auth/login";

    @Resource
    private MyPasswordEncoder myPasswordEncoder;

    @Resource
    private CustomHttpBasicServerAuthenticationEntryPoint customHttpBasicServerAuthenticationEntryPoint;
    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailHandler authenticationFailHandler;
    @Resource
    private TimingLogoutSuccessHandler timingLogoutSuccessHandler;
    @Resource
    private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;
//    @Resource
//    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    /**
     * 白名单路径
     */
//    @Value("${authPath.excludeAuth}")
    private static final String[] excludeAuthPages = {
            "/auth/**",
            "/user/register"
    };

    /**
     * 验证账号密码
     *
     * @param userDetailsService
     * @return
     */
    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(SecurityUserDetailService userDetailsService) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(myPasswordEncoder);
        return authenticationManager;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         ReactiveAuthenticationManager reactiveAuthenticationManager) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) //配置HTTP基本认证
                .authenticationManager(reactiveAuthenticationManager) //配置默认身份验证管理器
                .exceptionHandling() //异常处理
                .authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)// 自定义authenticationEntryPoint
                .accessDeniedHandler((swe, e) -> { //验证通过但无权限
                    swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return swe.getResponse().writeWith(Mono.just(new DefaultDataBufferFactory().wrap("FORBIDDEN".getBytes())));
                })
                .and()
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange() //用于下面的授权操作
                .pathMatchers(excludeAuthPages).permitAll()// 白名单
                .pathMatchers(HttpMethod.OPTIONS).permitAll()// option请求默认放行
                .anyExchange()
                .authenticated()
                .and()
                .formLogin().loginPage(LOGIN_URI) //登录认证和登录失败后重定向到登录界面
                .authenticationSuccessHandler(authenticationSuccessHandler)// 自定义authenticationSuccessHandler
                .authenticationFailureHandler(authenticationFailHandler)// 自定义authenticationFailureHandler
                .and()
                .logout().logoutUrl("/auth/logout")
                .logoutSuccessHandler(timingLogoutSuccessHandler)// 自定义logoutSuccessHandler
                .and()
                .addFilterAt(jwtTokenAuthenticationFilter, SecurityWebFiltersOrder.HTTP_BASIC);
        return http.build();
    }

    /**
     * 获取已注册的控制器路由中允许匿名访问的URL
     * @return 允许匿名访问的URL
     */
    /**public String[] getAnonymousUrls() {
     Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
     Set<String> res = new HashSet<>();
     handlerMethods.forEach((info, method) -> {
     AllowAnonymous an = method.getMethod().getAnnotation(AllowAnonymous.class);
     if (an != null) {
     res.addAll(info.getDirectPaths());
     System.out.println(res);
     }
     });
     return res.toArray(new String[0]);
     } */

}

