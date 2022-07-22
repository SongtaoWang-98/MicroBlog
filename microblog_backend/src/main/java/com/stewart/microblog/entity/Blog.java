package com.stewart.microblog.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


/**
 * 博客实体类
 * @author 王松涛
 */
@Data
@Entity
public class Blog {
    @Id
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
