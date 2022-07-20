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
    private List<String> groupNameList;
    @JsonProperty("blogs")
    private List<BlogVO> blogVOList;
    @JsonProperty("topics")
    private List<String> hotTopicList;
}