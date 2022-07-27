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
 * 评论实体类
 * @author 王松涛
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer blogId;
    private Integer senderId;
    private String content;
    private Date time;
    private Boolean isReply;
    private Integer replyCommentId;
    private Boolean deleted;
}
