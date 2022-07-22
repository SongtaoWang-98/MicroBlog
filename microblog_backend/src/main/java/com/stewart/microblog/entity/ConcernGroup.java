package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 关注组实体类
 * @author 王松涛
 */
@Data
@Entity
public class ConcernGroup {
    @Id
    private Integer id;
    private Integer userId;
    private String name;
    private Boolean deleted;
}
