package com.stewart.microblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人信息表单类
 * @author 王松涛
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedInfoDTO {
    private String realName;
    private String gender;
    private String birthday;
    private String company;
    private String job;
    private String university;
    private String telNumber;
    private String hobby;
    private String province;
}
