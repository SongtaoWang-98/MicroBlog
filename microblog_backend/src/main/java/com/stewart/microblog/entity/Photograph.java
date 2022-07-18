package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Administrator
 */
@Data
@Entity
public class Photograph {
    @Id
    private Integer id;
    private String name;
    private Integer photoId;
    private Boolean deleted;
}
