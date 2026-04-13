package com.macro.mall.tiny.security.config;

import com.macro.mall.tiny.security.component.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * SpringSecurity 7.x新用法配置（Spring Boot 4.0适配）
 * 为避免循环依赖，仅用于配置HttpSecurity
 * Created by macro on 2019/11/5.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private DynamicAuthorizationManager dynamicAuthorizationManager;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 不需要保护的资源路径允许访问
                .authorizeHttpRequests(authorize -> {
                    for (String url : ignoreUrlsConfig.getUrls()) {
                        authorize.requestMatchers(url).permitAll();
                    }
                    // 允许跨域请求的OPTIONS请求
                    authorize.requestMatchers(HttpMethod.OPTIONS).permitAll();
                    // 任何请求需要身份认证
                    authorize.anyRequest().access(dynamicAuthorizationManager);
                })
                // 关闭跨站请求防护
                .csrf(csrf -> csrf.disable())
                // 不使用session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 自定义权限拒绝处理类
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedHandler(restfulAccessDeniedHandler)
                        .authenticationEntryPoint(restAuthenticationEntryPoint)
                )
                // 自定义权限拦截器JWT过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
