package com.stewart.microblog.security;

import com.alibaba.fastjson.JSON;
import com.stewart.microblog.enums.LoginCode;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功接口实现
 * @author 王松涛
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException {
        ResultVO result = ResultVOUtil.success(LoginCode.SUCCESS);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}

