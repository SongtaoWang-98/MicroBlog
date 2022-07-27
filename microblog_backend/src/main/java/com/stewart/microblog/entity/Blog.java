package com.stewart.microblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


/**
 * 博客实体类
 * @author 王松涛
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer publisherId;
    private Date time;
    private String scope;
    private String content;
    private Integer photoId;
    private Integer likeNum;
    private Integer collectNum;
    private Integer forwardNum;
    private String state;
    private String type;
    private Integer sourceId;
    private Integer heat;
    private Boolean deleted;
}
