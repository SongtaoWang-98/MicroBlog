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
 * 日志实体类
 * @author 王松涛
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SysAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String remoteAddress;
    private String url;
    private String method;
    private String params;
    private Integer localPort;
    private String sessionId;
    private String username;
    private String funcDesc;
    private String type;
    private Date time;
}
