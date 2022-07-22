package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户实体类
 * @author 王松涛
 */
@Data
@Entity
public class User {
    @Id
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String type;
    private String state;
    private Integer photoId;
}
