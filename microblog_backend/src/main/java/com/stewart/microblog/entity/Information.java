package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
@Entity
public class Information {
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
