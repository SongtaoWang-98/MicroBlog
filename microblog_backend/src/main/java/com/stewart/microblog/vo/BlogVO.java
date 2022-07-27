package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 博客视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogVO {
    @JsonProperty("blogId")
    private Integer blogId;
    @JsonProperty("publisherId")
    private Integer publisherId;
    @JsonProperty("time")
    private String blogTime;
    @JsonProperty("publisherName")
    private String publisherName;
    @JsonProperty("publisherImg")
    private String publisherImg;
    @JsonProperty("content")
    private String blogContent;
    @JsonProperty("topics")
    private List<String> blogTopics;
    @JsonProperty("img")
    private String blogImg;
    @JsonProperty("repostNum")
    private Integer repostNum;
    @JsonProperty("collecting")
    private Boolean collecting;
    @JsonProperty("collectNum")
    private Integer collectNum;
    @JsonProperty("liking")
    private Boolean liking;
    @JsonProperty("likeNum")
    private Integer likeNum;
    @JsonProperty("commentNum")
    private Integer commentNum;
    @JsonProperty("comments")
    private List<String> comments;
}
