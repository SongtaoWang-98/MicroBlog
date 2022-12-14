package com.stewart.microblog.enums;

import lombok.Getter;

/**
 * 状态代码枚举类
 * @author 王松涛
 */
@Getter
public enum StatusCode {
    //成功
    SUCCESS(200,"成功"),
    //失败
    FAIL(500,"失败");
    private final Integer code;
    private final String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
