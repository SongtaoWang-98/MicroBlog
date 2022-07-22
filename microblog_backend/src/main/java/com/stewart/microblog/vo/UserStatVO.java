package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户个人统计视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatVO {
    @JsonProperty("blogNum")
    private Integer totalBlogNum;
    @JsonProperty("heat")
    private Integer totalHeat;
    @JsonProperty("commentNum")
    private Integer totalCommentNum;
    @JsonProperty("likeNum")
    private Integer totalLikeNum;
    @JsonProperty("repostNum")
    private Integer totalRepostNum;
    @JsonProperty("collectNum")
    private Integer totalCollectNum;
    @JsonProperty("hottestBlog")
    private BlogVO hottestBlog;
}
