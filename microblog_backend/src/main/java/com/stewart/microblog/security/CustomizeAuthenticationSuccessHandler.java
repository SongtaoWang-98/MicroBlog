package com.stewart.microblog.security;

import com.alibaba.fastjson.JSON;
import com.stewart.microblog.entity.UserInfo;
import com.stewart.microblog.enums.LoginCode;
import com.stewart.microblog.repository.UserInfoRepository;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author stewartwang
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user", name);
        UserInfo userInfo = userInfoRepository.findByUsername(name);
        userInfo.setLastTime(new Date());
        userInfoRepository.save(userInfo);
        ResultVO result = ResultVOUtil.success(userInfo.getType());
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}

