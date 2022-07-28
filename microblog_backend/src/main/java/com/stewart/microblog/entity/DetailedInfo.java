package com.stewart.microblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 个人信息实体类
 * @author 王松涛
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DetailedInfo {
    @Id
    private Integer userId;
    private String realName;
    private String gender;
    private Date birthday;
    private String company;
    private String job;
    private String university;
    private String telNumber;
    private String hobby;
    private String province;
}
