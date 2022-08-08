package com.stewart.microblog.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * session信息过期策略接口实现，用于实现单登录
 * @author 王松涛
 */
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Resource
    private CustomizeAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        //获取用户名
        UserDetails userDetails =
                (UserDetails)event.getSessionInformation().getPrincipal();

        AuthenticationException exception =
                new AuthenticationServiceException(
                        String.format("[%s] 用户在另外一台电脑登录,您已被下线", userDetails.getUsername()));

        // 当用户在另外一台电脑登录后,交给失败处理器回到认证页面
        event.getRequest().setAttribute("toAuthentication" , true);
        myAuthenticationFailureHandler
                .onAuthenticationFailure(event.getRequest(), event.getResponse(), exception);
    }

}
