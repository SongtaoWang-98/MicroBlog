package com.stewart.microblog.enums;

import lombok.Getter;

/**
 * @author stewartwang
 */

@Getter
public enum StatusCode {
    //成功
    SUCCESS(200,"成功"),
    //失败
    FAIL(500,"失败");
    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
