package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Administrator
 */
@Data
@Entity
public class Group {
    @Id
    private Integer id;
    private Integer userId;
    private String name;
    private Boolean deleted;
}
