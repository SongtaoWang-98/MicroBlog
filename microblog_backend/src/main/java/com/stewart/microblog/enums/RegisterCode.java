package com.stewart.microblog.enums;

import lombok.Getter;

/**
 * 注册返回代码枚举类
 * @author 王松涛
 */

@Getter
public enum RegisterCode {
    /* 成功*/
    SUCCESS(200,"成功"),

    USERNAME_EXISTS(1001,"用户名已存在"),

    FAIL(2001,"注册失败");

    private final Integer code;
    private final String msg;

    RegisterCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
