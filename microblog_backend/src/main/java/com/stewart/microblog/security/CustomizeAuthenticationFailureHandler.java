package com.stewart.microblog.security;

import com.alibaba.fastjson.JSON;
import com.stewart.microblog.enums.LoginCode;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现了认证失败处理接口，对不同认证失败异常进行处理
 * @author 王松涛
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {
        ResultVO result = null;
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = ResultVOUtil.fail(LoginCode.USER_CREDENTIALS_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = ResultVOUtil.fail(LoginCode.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            //账号锁定
            result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            if(e.getCause() != null) {
                if(e.getCause() instanceof LockedException){
                    result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_LOCKED);
                }
            }
            else {
                result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_NOT_EXIST);
            }
        } else if (e instanceof AuthenticationServiceException) {
            //账号被挤下线
            result = ResultVOUtil.fail(LoginCode.USER_ACCOUNT_USE_BY_OTHERS);
        }else{
            //其他错误
            result = ResultVOUtil.fail(LoginCode.COMMON_FAIL);
        }

        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }

}
