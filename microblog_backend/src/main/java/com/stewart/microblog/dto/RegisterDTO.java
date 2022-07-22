package com.stewart.microblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 注册表单类
 * @author 王松涛
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String userName;
    private String password;
}
