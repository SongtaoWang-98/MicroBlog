package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 关注组-关注实体类
 * @author 王松涛
 */
@Data
@Entity
public class GroupFollow {
    @Id
    private Integer id;
    private Integer groupId;
    private Integer followId;
    private Boolean deleted;
}
