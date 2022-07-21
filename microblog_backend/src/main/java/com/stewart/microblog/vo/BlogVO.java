package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogVO {
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
    @JsonProperty("collectNum")
    private Integer collectNum;
    @JsonProperty("likeNum")
    private Integer likeNum;
    @JsonProperty("commentNum")
    private Integer commentNum;
    @JsonProperty("comments")
    private List<String> someComments;
}
