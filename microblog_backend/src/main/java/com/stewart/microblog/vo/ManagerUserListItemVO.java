package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerUserListItemVO {
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("nickName")
    private String nickName;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("state")
    private String state;
    @JsonProperty("lastTime")
    private String lastTime;
}
