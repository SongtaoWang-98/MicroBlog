package com.stewart.microblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 博客-话题实体类
 * @author 王松涛
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BlogTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer blogId;
    private Integer topicId;
    private Boolean deleted;
}
