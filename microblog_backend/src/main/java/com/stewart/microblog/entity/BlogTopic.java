package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 博客-话题实体类
 * @author 王松涛
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
