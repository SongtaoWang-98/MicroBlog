package com.stewart.microblog.enums;

import lombok.Getter;

@Getter
public enum RegisterCode {
    SUCCESS(200,"成功"),

    USERNAME_EXISTS(1001,"用户名已存在"),

    FAIL(2001,"注册失败");


    private Integer code;
    private String msg;

    RegisterCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
