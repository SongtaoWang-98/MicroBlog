package com.stewart.microblog.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
