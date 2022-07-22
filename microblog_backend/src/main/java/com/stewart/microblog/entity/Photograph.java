package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 相册实体类
 * @author 王松涛
 */
@Data
@Entity
public class Photograph {
    @Id
    private Integer id;
    private String name;
    private Boolean deleted;
}
