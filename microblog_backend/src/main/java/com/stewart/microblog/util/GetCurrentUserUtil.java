package com.stewart.microblog.util;

import com.stewart.microblog.entity.User;
import com.stewart.microblog.repository.UserRepository;

import javax.annotation.Resource;

/**
 * 获取当前用户信息工具类
 * @author 王松涛
 */
public class GetCurrentUserUtil {
    public static String getCurrentUserName() {
        return "songtao_wang@qq.com";
//        return "lisi@eastmoney.com";
    }
}
