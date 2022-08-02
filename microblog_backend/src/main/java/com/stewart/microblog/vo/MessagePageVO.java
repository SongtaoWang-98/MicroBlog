package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 单条私信视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePageVO {
    @JsonProperty("personName")
    private String personName;
    @JsonProperty("messages")
    List<MessagePageItemVO> messagePageItemVOList;
}
