package com.stewart.microblog.util;

import com.stewart.microblog.service.impl.BlogServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取当前用户信息工具类
 * @author 王松涛
 */
public class GetCurrentUserUtil {
    private GetCurrentUserUtil() {}
    private static final Logger logger = LoggerFactory.getLogger(GetCurrentUserUtil.class);

    public static String getCurrentUserName() {
        String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(currentName);
        return currentName;
//        return "songtao_wang@qq.com";
    }
}
