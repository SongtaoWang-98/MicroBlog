package com.stewart.microblog.service;

import com.stewart.microblog.enums.RegisterCode;

/**
 * 用户注册接口
 * @author 王松涛
 */
public interface SignUpService {
    /**
     * 用户注册接口实现
     * @param username
     * 用户名
     * @param nickname
     * 用户昵称
     * @param password
     * 用户密码
     * @return RegisterCode
     */
    RegisterCode signUp(String username, String nickname, String password);
}
