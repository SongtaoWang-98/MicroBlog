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
public class ReportFeedback {
    @Id
    private Integer id;
    private Integer senderId;
    private Date time;
    private String type;
    private String state;
    private String content;
    private String email;
}
