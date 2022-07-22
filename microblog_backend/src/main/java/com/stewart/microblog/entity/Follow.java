package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 关注实体类
 * @author 王松涛
 */
@Data
@Entity
public class Follow {
    @Id
    private Integer id;
    private Integer userId;
    private Integer followingId;
    private Boolean deleted;
}
