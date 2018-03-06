package com.zxytech.wechat.config;

import com.zxytech.wechat.domain.account.ManagerRepository;
import com.zxytech.wechat.domain.account.ManagerService;
import com.zxytech.wechat.domain.account.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xwxia
 * @date 2018/2/26 15:19
 */
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10)
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private static final String REMEMBER_ME_KEY = "spwechat";

    private ManagerRepository managerRepository;

    @Autowired
    public SecurityConfig(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new ManagerService(managerRepository))
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/signin").defaultSuccessUrl("/admin/manager")
                .and()
                .rememberMe().tokenValiditySeconds(Integer.MAX_VALUE).key(REMEMBER_ME_KEY)
                .and()
                .logout().logoutSuccessUrl("/signin")
                .and().csrf().disable().authorizeRequests()
                .antMatchers("/login", "/logout", "/signin", "/mp/**", "/webjars/**", "/static/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole(UserRoleEnum.USER.getName(), UserRoleEnum.ADMIN.getName())
                .anyRequest().authenticated();
    }
}
