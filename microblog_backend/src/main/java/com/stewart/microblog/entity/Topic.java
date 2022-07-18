package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Administrator
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
