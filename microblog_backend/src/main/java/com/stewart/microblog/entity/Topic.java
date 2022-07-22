package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 话题实体类
 * @author 王松涛
 */
@Data
@Entity
public class Topic {
    @Id
    private Integer id;
    private String name;
    private Integer heat;
    private Boolean deleted;
}
