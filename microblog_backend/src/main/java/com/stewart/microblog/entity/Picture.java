package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Administrator
 */
@Data
@Entity
public class Picture {
    @Id
    private Integer id;
    private Integer photographId;
    private String url;
    private Boolean deleted;
}
