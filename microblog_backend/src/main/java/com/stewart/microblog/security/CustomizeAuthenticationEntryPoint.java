package com.stewart.microblog.security;

import com.alibaba.fastjson.JSON;
import com.stewart.microblog.enums.LoginCode;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证入口点接口实现类，认证异常返回相应Response
 * @author Administrator
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        ResultVO result = ResultVOUtil.fail(LoginCode.USER_NOT_LOGIN);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
