package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Administrator
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
