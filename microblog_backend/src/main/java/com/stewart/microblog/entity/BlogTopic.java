package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Administrator
 */
@Data
@Entity
public class BlogTopic {
    @Id
    private Integer id;
    private Integer blogId;
    private Integer topicId;
    private Boolean deleted;
}
