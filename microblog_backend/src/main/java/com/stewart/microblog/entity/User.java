package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Administrator
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
