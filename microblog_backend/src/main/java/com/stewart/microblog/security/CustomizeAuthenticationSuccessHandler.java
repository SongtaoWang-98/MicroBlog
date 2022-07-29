package com.stewart.microblog.security;

import com.alibaba.fastjson.JSON;
import com.stewart.microblog.enums.LoginCode;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author stewartwang
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user", name);
        ResultVO result = ResultVOUtil.success(name);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}

