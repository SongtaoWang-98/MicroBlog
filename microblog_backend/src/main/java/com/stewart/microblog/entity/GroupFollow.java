package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Administrator
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
