package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Follow {
    @Id
    private Integer id;
    private Integer userId;
    private Integer followingId;
    private Boolean deleted;
}
