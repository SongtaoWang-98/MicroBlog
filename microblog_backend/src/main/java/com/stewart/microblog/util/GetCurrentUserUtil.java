package com.stewart.microblog.util;

//import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取当前用户信息工具类
 * @author 王松涛
 */
public class GetCurrentUserUtil {
    private GetCurrentUserUtil() {}

    public static String getCurrentUserName() {
//        return SecurityContextHolder.getContext().getAuthentication().getName();
        return "songtao_wang@qq.com";
    }
}
