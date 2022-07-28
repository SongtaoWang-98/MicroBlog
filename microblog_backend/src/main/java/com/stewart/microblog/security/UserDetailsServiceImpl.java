package com.stewart.microblog.security;

import com.stewart.microblog.entity.UserInfo;
import com.stewart.microblog.repository.UserInfoRepository;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Administrator
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) {
        UserInfo userInfo = userInfoRepository.findByUsername(s);
        System.out.println(s);
        if(userInfo == null) {
            throw new InternalAuthenticationServiceException("用户不存在！");
        }
        else if("BANNED".equals(userInfo.getState())) {
            throw new LockedException("账户已被封禁！");
        }
        String role = userInfo.getType();
        return new User(userInfo.getUsername(), passwordEncoder.encode(userInfo.getPassword()),
                true, true, true, true,
                new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(role))));
    }
}
