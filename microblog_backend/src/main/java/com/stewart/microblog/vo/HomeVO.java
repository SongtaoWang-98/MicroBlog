package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 首页视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeVO {
    @JsonProperty("userImg")
    private String userImg;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("followingNum")
    private Integer followingNum;
    @JsonProperty("followerNum")
    private Integer followerNum;
    @JsonProperty("groups")
    private List<HomeGroupVO> groupNameList;
    @JsonProperty("blogs")
    private List<BlogVO> blogVOList;
    @JsonProperty("topics")
    private List<String> hotTopicList;
}
