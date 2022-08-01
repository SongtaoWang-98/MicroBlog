package com.stewart.microblog.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户实体类
 * @author 王松涛
 */
@Data
@Entity
@Document(indexName = "user")
public class UserInfo {
    @Id
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String type;
    private String state;
    @Field(name = "photo_id")
    private Integer photoId;
}
