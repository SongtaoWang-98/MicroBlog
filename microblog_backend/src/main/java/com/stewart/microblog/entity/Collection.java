package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 收藏实体类
 * @author 王松涛
 */
@Data
@Entity
public class Collection {
    @Id
    private Integer id;
    private Integer userId;
    private Integer blogId;
    private Boolean deleted;
}
