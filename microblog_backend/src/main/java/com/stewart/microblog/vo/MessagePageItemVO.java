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
public class MessagePageItemVO {
    @JsonProperty("personPic")
    private String personPic;
    @JsonProperty("time")
    private String time;
    @JsonProperty("content")
    private String content;
    @JsonProperty("type")
    private String type;
}
