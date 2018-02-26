package com.zxytech.wechat.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author xwxia
 * @date 2018/2/26 15:19
 */
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10)
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().csrf().disable().authorizeRequests()
                .antMatchers("/login", "/logout", "/mp/**", "/webjars/**").permitAll()
                .anyRequest().authenticated();
    }
}
