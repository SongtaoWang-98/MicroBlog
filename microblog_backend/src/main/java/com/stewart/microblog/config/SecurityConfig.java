package com.stewart.microblog.config;

import com.stewart.microblog.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    CustomizeAuthenticationEntryPoint authenticationEntryPoint;
//
//    @Autowired
//    CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;
//
//    @Autowired
//    CustomizeAuthenticationFailureHandler authenticationFailureHandler;
//
//    @Autowired
//    CustomizeLogoutSuccessHandler logoutSuccessHandler;
//
//    @Autowired
//    UserDetailsServiceImpl userDetailsServiceImpl;

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .permitAll()
//                .successHandler(authenticationSuccessHandler)
//                .failureHandler(authenticationFailureHandler)
                .and().logout()
                .permitAll()
//                .logoutSuccessHandler(logoutSuccessHandler)
                //登出之后删除cookie
                .deleteCookies("JSESSIONID")
                .and().authorizeRequests()
                .antMatchers("/home/friends").hasAuthority("USER")
                .antMatchers("/blog/**").hasAuthority("USER")
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/manager/**").hasAuthority("MANAGER")
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and().exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
                .and().cors()
                //关闭跨站请求伪造攻击拦截
                .and().csrf().disable();
    }
}

