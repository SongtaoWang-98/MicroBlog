package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 私信列表视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageListItemVO {
    @JsonProperty("messageId")
    private Integer messageId;
    @JsonProperty("personId")
    private Integer personId;
    @JsonProperty("personPic")
    private String personPic;
    @JsonProperty("personName")
    private String personName;
    @JsonProperty("lastTime")
    private String lastTime;
    @JsonProperty("lastContent")
    private String lastContent;
    @JsonProperty("state")
    private String state;
}
