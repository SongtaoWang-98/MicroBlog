package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 评论实体类
 * @author 王松涛
 */
@Data
@Entity
public class Comment {
    @Id
    private Integer id;
    private Integer blogId;
    private Integer senderId;
    private String content;
    private Date time;
    private Boolean isReply;
    private Integer replyCommentId;
    private Boolean deleted;
}
