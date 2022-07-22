package com.stewart.microblog.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 热度-时间实体类
 * @author 王松涛
 */
@Data
@Entity
public class HeatTime {
    @Id
    private Integer id;
    private Integer topicId;
    private Integer heat;
    private Date time;
}
