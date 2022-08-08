package com.stewart.microblog.exception;

/**
 * 服务接口类
 * @author 王松涛
 */
public interface BaseErrorInfoInterface {

    /**
     * 错误码
     * @return
     * String类型状态码
     */
    String getResultCode();

    /**
     * 错误描述
     * @return
     * String类型状态信息
     */
    String getResultMsg();
}
