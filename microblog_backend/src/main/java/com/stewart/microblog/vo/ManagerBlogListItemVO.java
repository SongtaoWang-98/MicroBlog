package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员博文列表元素视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerBlogListItemVO {
    @JsonProperty("blogId")
    private Integer blogId;
    @JsonProperty("publisherName")
    private String publisherName;
    @JsonProperty("time")
    private String time;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("content")
    private String content;
    @JsonProperty("likeNum")
    private Integer likeNum;
    @JsonProperty("collectNum")
    private Integer collectNum;
    @JsonProperty("forwardNum")
    private Integer forwardNum;
    @JsonProperty("state")
    private String state;
    @JsonProperty("heat")
    private Integer heat;
    @JsonProperty("deleted")
    private String deleted;
}
