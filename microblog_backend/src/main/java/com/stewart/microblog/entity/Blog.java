package com.stewart.microblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Field(name = "publisher_id")
    private Integer publisherId;
    @Field(type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date time;
    private String scope;
    private String content;
    @Field(name = "photo_id")
    private Integer photoId;
    @Field(name = "like_num")
    private Integer likeNum;
    @Field(name = "collect_num")
    private Integer collectNum;
    @Field(name = "forward_num")
    private Integer forwardNum;
    private String state;
    private String type;
    @Field(name = "source_id")
    private Integer sourceId;
    private Integer heat;
    private Boolean deleted;
}
