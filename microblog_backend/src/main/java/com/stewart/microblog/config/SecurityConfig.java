package com.stewart.microblog.config;

import com.stewart.microblog.security.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

/**
 * Spring Security配置类
 * @author 王松涛
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private CustomizeLogoutSuccessHandler logoutSuccessHandler;
    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy informationExpiredStrategy(){
        return new MySessionInformationExpiredStrategy();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //登录
                .formLogin()
                .permitAll()
                //登录成功处理
                .successHandler(authenticationSuccessHandler)
                //登录失败处理
                .failureHandler(authenticationFailureHandler)
                //登出
                .and().logout()
                .permitAll()
                //登出成功处理
                .logoutSuccessHandler(logoutSuccessHandler)
                //登出之后删除cookie
                .deleteCookies("JSESSIONID")
                .and().authorizeRequests()
                //登录用户权限
                .antMatchers("/home/friends").hasAuthority("USER")
                .antMatchers("/home/group").hasAuthority("USER")
                .antMatchers("/home/new").hasAuthority("USER")
                .antMatchers("/blog/**").hasAuthority("USER")
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/message/**").hasAuthority("USER")
                .antMatchers("/photo/**").hasAuthority("USER")
                //管理员权限
                .antMatchers("/manage/**").hasAuthority("MANAGER")
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().cors()
                //关闭跨站请求伪造攻击拦截
                .and().csrf().disable();
        //单点登录
        http.sessionManagement().maximumSessions(1).expiredSessionStrategy(sessionInformationExpiredStrategy);
    }
}

