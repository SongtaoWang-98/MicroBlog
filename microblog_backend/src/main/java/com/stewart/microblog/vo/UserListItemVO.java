package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户列表条目视图类
 * @author 王松涛
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListItemVO {
    @JsonProperty("userImg")
    private String userImg;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("userName")
    private String userName;
}
