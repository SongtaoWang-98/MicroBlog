package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 私信实体类
 * @author 王松涛
 */
@Data
@Entity
public class Message {
    @Id
    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private Date time;
    private String state;
    private String content;
    private Boolean deleted;
}
