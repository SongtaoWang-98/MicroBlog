package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户页视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageVO {
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
    @JsonProperty("following")
    private Boolean following;
    @JsonProperty("blogs")
    private List<BlogVO> blogVOList;
}
